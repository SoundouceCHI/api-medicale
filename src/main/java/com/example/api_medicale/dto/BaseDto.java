package com.example.api_medicale.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BaseDto {
    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    private long id;
    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    private LocalDate dateCreation;
}
