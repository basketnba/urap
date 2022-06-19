package ru.pulkovo.rivc.urap.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public abstract class AbstractDto {

    private final LocalDateTime createdAt;
    private final String createdBy;
    private final LocalDateTime updatedAt;
    private final String updatedBy;
}