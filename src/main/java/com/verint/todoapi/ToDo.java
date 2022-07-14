package com.verint.todoapi;

import lombok.*;

import javax.persistence.*;

/*
 what is the best approach with the constructors?
 also had to remove final fields
 "no-args constructor required by JPA spec"
 */
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ToDo {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(nullable = false)
    private String name;
}
