package com.encircle360.oss.m2d.dto.mayan;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class MayanPageDTO<T> {

    private Long count;
    private String next;
    private String previous;
    private List<T> results;
}
