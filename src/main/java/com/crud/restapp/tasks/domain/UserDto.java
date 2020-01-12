package com.crud.restapp.tasks.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDto {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("username")
    private String username;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("password")
    private String password;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("tasksDto")
    private List<TaskDto> tasksDto;

    @JsonProperty("role")
    private Roles role;

}
