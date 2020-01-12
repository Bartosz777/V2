package com.crud.restapp.tasks.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class TaskDto {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("name")
    private String title;

    @JsonProperty("description")
    private String content;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("userDto")
    private UserDto userDto;


    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("startDate")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate startDate;
}