package ru.pulkovo.rivc.urap.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import ru.pulkovo.rivc.urap.dto.user.UserDto;
import ru.pulkovo.rivc.urap.dto.user.UserInfoDto;
import ru.pulkovo.rivc.urap.dto.user.UserStatisticDto;
import ru.pulkovo.rivc.urap.entity.user.User;
import ru.pulkovo.rivc.urap.entity.user.UserStatus;

@Mapper
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mapping(target = "userStatusDto", source = "userStatus")
    UserDto toDto(User entity);

    @Mapping(target = "userStatusDto", source = "entity.userStatus")
    @Mapping(target = "age", source = "age")
    UserInfoDto toDto(User entity, Integer age);

    @Mapping(target = "oldUserStatusDto", source = "oldStatus")
    @Mapping(target = "newUserStatusDto", source = "newStatus")
    UserStatisticDto toDto(User entity, UserStatus oldStatus, UserStatus newStatus);

    @Mapping(target = "userStatus", source = "userStatusDto")
    User toEntity(UserDto dto);
}