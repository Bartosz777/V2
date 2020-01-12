package com.crud.restapp.tasks.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity(name = "tasks")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NotNull
    @Column(name = "id", unique = true)
    private Long id;

    @Column(name = "description")
    private String title;

    @Column(name = "name")
    private String content;

    @ManyToOne
    @JoinColumn(name = "userId")
    @NotNull
    private User user;

    @Column(name = "startDate")
    private LocalDate startDate;
}
