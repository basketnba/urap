package ru.pulkovo.rivc.urap.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@MappedSuperclass
public abstract class AbstractEntity {

    public static final String CREATED_AT_COLUMN = "created_at";
    public static final String CREATED_BY_COLUMN = "created_by";
    public static final String UPDATED_AT_COLUMN = "updated_at";
    public static final String UPDATED_BY_COLUMN = "updated_by";

    @Column(name = CREATED_AT_COLUMN, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = CREATED_BY_COLUMN, updatable = false)
    private String createdBy;

    @Column(name = UPDATED_AT_COLUMN)
    private LocalDateTime updatedAt;

    @Column(name = UPDATED_BY_COLUMN)
    private String updatedBy;

    public AbstractEntity(LocalDateTime createdAt, String createdBy, LocalDateTime updatedAt, String updatedBy) {
        this.createdAt = createdAt;
        this.createdBy = createdBy;
        this.updatedAt = updatedAt;
        this.updatedBy = updatedBy;
    }
}