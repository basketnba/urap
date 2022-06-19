package ru.pulkovo.rivc.urap.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import ru.pulkovo.rivc.urap.dto.user.*;
import ru.pulkovo.rivc.urap.entity.AbstractEntity;
import ru.pulkovo.rivc.urap.entity.user.User;
import ru.pulkovo.rivc.urap.entity.user.UserStatus;
import ru.pulkovo.rivc.urap.mapper.UserMapper;
import ru.pulkovo.rivc.urap.repository.UserRepository;
import ru.pulkovo.rivc.urap.service.UserService;
import ru.pulkovo.rivc.urap.util.DateTimeUtil;

import java.time.LocalDate;
import java.time.Period;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    public static final String USER_ID_SHOULD_BE_NULL_FOR_NEW_USER = "User Id should be null for new user.";
    public static final String USER_DTO_NULL_MESSAGE = "User object should not be null.";
    public static final String USER_ID_NULL_MESSAGE = "User Id should not be null.";
    public static final String USER_STATUS_NULL_MESSAGE = "User status should not be null.";

    private final UserRepository userRepository;

    @Override
    @Transactional
    public UserDto createUser(UserDto userDto) {
        Assert.notNull(userDto, USER_DTO_NULL_MESSAGE);
        Assert.isNull(userDto.getId(), USER_ID_SHOULD_BE_NULL_FOR_NEW_USER);

        User userEntity = UserMapper.INSTANCE.toEntity(userDto);
        updateCreated(userEntity);
        updateUpdated(userEntity);
        final User savedUser = userRepository.save(userEntity);

        return UserMapper.INSTANCE.toDto(savedUser);
    }

    @Override
    @Transactional(readOnly = true)
    public UserInfoDto getUser(UUID id) {
        Assert.notNull(id, USER_ID_NULL_MESSAGE);

        Optional<User> optionalDbUser = userRepository.findById(id);
        if (optionalDbUser.isEmpty()) {
            return null;
        }

        final User dbUser = optionalDbUser.get();
        Period between = Period.between(dbUser.getDateOfBirth().toLocalDate(),
                DateTimeUtil.getCurrentTimeInUTC().toLocalDate());

        return UserMapper.INSTANCE.toDto(dbUser, between.getYears());
    }

    @Override
    @Transactional
    public UserStatisticDto updateStatus(UUID id, UserStatusDto newStatus) {
        Assert.notNull(id, USER_ID_NULL_MESSAGE);
        Assert.notNull(newStatus, USER_STATUS_NULL_MESSAGE);

        Optional<User> optionalDbUser = userRepository.findById(id);
        if (optionalDbUser.isEmpty()) {
            return null;
        }

        final User dbUser = optionalDbUser.get();
        User updateUser = User.using(dbUser)
                .userStatus(UserStatus.valueOf(newStatus.name()))
                .build();
        updateUpdated(updateUser);
        UserStatus oldStatus = dbUser.getUserStatus();
        UserStatus updateStatus = updateUser.getUserStatus();
        userRepository.save(updateUser);

        return UserMapper.INSTANCE.toDto(updateUser, oldStatus, updateStatus);
    }

    @Override
    @Transactional
    public ServerStatisticDto getStatistic(UserStatusDto status, Boolean adult) {
        final List<User> users = userRepository.findAll();
        final Integer number = users.size();
        List<UserDto> usersByStatus = Objects.isNull(status) ? null :
                users.stream()
                        .filter(user -> user.getUserStatus() == UserStatus.valueOf(status.name()))
                        .map(UserMapper.INSTANCE::toDto)
                        .collect(Collectors.toList());
        List<UserDto> usersByAdult = null;
        Double averageAge = null;
        if (Objects.nonNull(adult)) {
            LocalDate now = DateTimeUtil.getCurrentTimeInUTC().toLocalDate();
            if (adult) {
                usersByAdult = users.stream()
                        .filter(user -> Period.between(user.getDateOfBirth().toLocalDate(), now).getYears() >= 18)
                        .map(UserMapper.INSTANCE::toDto)
                        .collect(Collectors.toList());
            } else {
                usersByAdult = users.stream()
                        .filter(user -> Period.between(user.getDateOfBirth().toLocalDate(), now).getYears() < 18)
                        .map(UserMapper.INSTANCE::toDto)
                        .collect(Collectors.toList());
            }
            averageAge = usersByAdult.stream()
                    .mapToInt(user -> Period.between(user.getDateOfBirth().toLocalDate(), now).getYears())
                    .average()
                    .orElse(0);
        }

        return ServerStatisticDto.builder()
                .number(number)
                .usersByStatus(usersByStatus)
                .usersByAdult(usersByAdult)
                .averageAge(Objects.isNull(averageAge) ? null : Double.valueOf(String.format("%.1f", averageAge)))
                .build();
    }

    public void updateCreated(AbstractEntity entity) {
        entity.setCreatedAt(DateTimeUtil.getCurrentTimeInUTC());
        entity.setCreatedBy("testUser");//supposed to be security
    }

    public void updateUpdated(AbstractEntity entity) {
        entity.setUpdatedAt(DateTimeUtil.getCurrentTimeInUTC());
        entity.setUpdatedBy("testUser");//supposed to be security
    }
}