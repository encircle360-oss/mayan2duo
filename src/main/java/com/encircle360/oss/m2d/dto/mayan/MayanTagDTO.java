package com.encircle360.oss.m2d.dto.mayan;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class MayanTagDTO {

    private Long id;
    private String label;
}
