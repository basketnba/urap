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
public class UserInfoDto extends AbstractDto {

    private final UUID id;
    private final String name;
    private final LocalDateTime dateOfBirth;
    private final Integer age;
    private final String email;
    private final UserStatusDto userStatusDto;

    @Builder
    @SuppressWarnings("squid:S00107")
    public UserInfoDto(LocalDateTime createdAt,
                       String createdBy,
                       LocalDateTime updatedAt,
                       String updatedBy,
                       UUID id,
                       String name,
                       LocalDateTime dateOfBirth,
                       Integer age,
                       String email,
                       UserStatusDto userStatusDto) {
        super(createdAt, createdBy, updatedAt, updatedBy);
        this.id = id;
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.age = age;
        this.email = email;
        this.userStatusDto = userStatusDto;
    }
}