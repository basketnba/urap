package ru.pulkovo.rivc.urap.converter;

import ru.pulkovo.rivc.urap.entity.user.UserStatus;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.stream.Stream;

@Converter(autoApply = true)
public class UserStatusConverter implements AttributeConverter<UserStatus, String> {

    @Override
    public String convertToDatabaseColumn(UserStatus userStatus) {
        if (userStatus == null) {
            return null;
        }
        return userStatus.getCode();
    }

    @Override
    public UserStatus convertToEntityAttribute(String code) {
        if (code == null) {
            return null;
        }

        return Stream.of(UserStatus.values())
                .filter(s -> s.getCode().equals(code))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}