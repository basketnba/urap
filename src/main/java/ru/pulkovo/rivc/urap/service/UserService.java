package ru.pulkovo.rivc.urap.service;

import ru.pulkovo.rivc.urap.dto.user.*;
import ru.pulkovo.rivc.urap.entity.AbstractEntity;

import java.util.UUID;

public interface UserService {

    /**
     * Create user.
     *
     * @param userDto object UserDto which contains info for new user
     * @return created UserDto with id from the database
     */
    UserDto createUser(UserDto userDto);

    /**
     * Get user info by id.
     *
     * @param id unique identifier for user
     * @return UserInfoDto from the database with additional info
     */
    UserInfoDto getUser(UUID id);

    /**
     * Update user status by id.
     *
     * @param id        unique identifier for user
     * @param newStatus new status for user
     * @return UserStatisticDto with statistic about user's statuses
     */
    UserStatisticDto updateStatus(UUID id, UserStatusDto newStatus);

    /**
     * Get server statistic.
     *
     * @param status user status for statistic
     * @param adult  flag is user adult
     * @return ServerStatisticDto statistic from the database
     */
    ServerStatisticDto getStatistic(UserStatusDto status, Boolean adult);

    /**
     * Update created fields for entity.
     *
     * @param entity object inheritor of AbstractEntity which need to be updated "created fields"
     */
    void updateCreated(AbstractEntity entity);

    /**
     * Update updated fields for entity.
     *
     * @param entity object inheritor of AbstractEntity which need to be updated "updated fields"
     */
    void updateUpdated(AbstractEntity entity);
}