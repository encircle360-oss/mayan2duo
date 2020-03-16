package com.encircle360.oss.m2d.dto.mayan;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.Instant;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class MayanDocumentDTO {

    private Long id;

    @JsonProperty("date_added")
    private Instant dateAdded;

    private String label;
    private String uuid;
}
