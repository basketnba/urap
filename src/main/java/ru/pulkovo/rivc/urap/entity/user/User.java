package ru.pulkovo.rivc.urap.entity.user;

import lombok.*;
import ru.pulkovo.rivc.urap.entity.AbstractEntity;

import javax.persistence.*;
import java.beans.ConstructorProperties;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Entity
@Table(name = User.TABLE_NAME)
public class User extends AbstractEntity {

    public static final String TABLE_NAME = "\"user\"";
    public static final String ID_COLUMN = "id";
    public static final String NAME_COLUMN = "name";
    public static final String DATE_OF_BIRTH_COLUMN = "date_of_birth";
    public static final String EMAIL_COLUMN = "email";
    public static final String USER_STATUS_COLUMN = "user_status";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = ID_COLUMN)
    private UUID id;

    @Column(name = NAME_COLUMN)
    private String name;

    @Column(name = DATE_OF_BIRTH_COLUMN)
    private LocalDateTime dateOfBirth;

    @Column(name = EMAIL_COLUMN)
    private String email;

    @Column(name = USER_STATUS_COLUMN)
    private UserStatus userStatus;

    @Builder
    @ConstructorProperties({
            ID_COLUMN,
            NAME_COLUMN,
            DATE_OF_BIRTH_COLUMN,
            EMAIL_COLUMN,
            USER_STATUS_COLUMN,
            CREATED_AT_COLUMN,
            CREATED_BY_COLUMN,
            UPDATED_AT_COLUMN,
            UPDATED_BY_COLUMN
    })
    @SuppressWarnings("squid:S00107")
    public User(UUID id,
                String name,
                LocalDateTime dateOfBirth,
                String email,
                UserStatus userStatus,
                LocalDateTime createdAt,
                String createdBy,
                LocalDateTime updatedAt,
                String updatedBy) {
        super(createdAt, createdBy, updatedAt, updatedBy);
        this.id = id;
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.email = email;
        this.userStatus = userStatus;
    }

    public static User.UserBuilder using(User source) {
        if (source == null) {
            return User.builder();
        } else {
            return User.builder()
                    .id(source.getId())
                    .name(source.getName())
                    .dateOfBirth(source.getDateOfBirth())
                    .email(source.getEmail())
                    .userStatus(source.getUserStatus())
                    .createdAt(source.getCreatedAt())
                    .createdBy(source.getCreatedBy())
                    .updatedAt(source.getUpdatedAt())
                    .updatedBy(source.getUpdatedBy());
        }
    }
}