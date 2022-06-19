package ru.pulkovo.rivc.urap.dto.user;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ServerStatisticDto {

    private final Integer number;
    private final List<UserDto> usersByStatus;
    private final List<UserDto> usersByAdult;
    private final Double averageAge;
}