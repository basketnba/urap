package ru.pulkovo.rivc.urap.dto.user;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import ru.pulkovo.rivc.urap.dto.AbstractDto;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class UserStatisticDto extends AbstractDto {

    private final UUID id;
    private final UserStatusDto oldUserStatusDto;
    private final UserStatusDto newUserStatusDto;

    @Builder
    @SuppressWarnings("squid:S00107")
    public UserStatisticDto(LocalDateTime createdAt,
                            String createdBy,
                            LocalDateTime updatedAt,
                            String updatedBy,
                            UUID id,
                            UserStatusDto oldUserStatusDto,
                            UserStatusDto newUserStatusDto) {
        super(createdAt, createdBy, updatedAt, updatedBy);
        this.id = id;
        this.oldUserStatusDto = oldUserStatusDto;
        this.newUserStatusDto = newUserStatusDto;
    }
}