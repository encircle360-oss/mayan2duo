package com.encircle360.oss.m2d.dto.mayan;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NewDocumentTagRequestDTO {

    @JsonProperty("tag_pk")
    private Long tagId;
}
