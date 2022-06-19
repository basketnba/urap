package ru.pulkovo.rivc.urap.dto.user;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import ru.pulkovo.rivc.urap.dto.AbstractDto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Getter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class UserDto extends AbstractDto {

    private final UUID id;

    @NotBlank
    private final String name;

    @NotNull
    private final LocalDateTime dateOfBirth;

    @NotBlank
    @Email
    private final String email;

    private final UserStatusDto userStatusDto;

    @Builder
    @SuppressWarnings("squid:S00107")
    public UserDto(LocalDateTime createdAt,
                   String createdBy,
                   LocalDateTime updatedAt,
                   String updatedBy,
                   UUID id,
                   String name,
                   LocalDateTime dateOfBirth,
                   String email,
                   UserStatusDto userStatusDto) {
        super(createdAt, createdBy, updatedAt, updatedBy);
        this.id = id;
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.email = email;
        this.userStatusDto = Objects.isNull(userStatusDto) ? UserStatusDto.OFFLINE : userStatusDto;
    }
}