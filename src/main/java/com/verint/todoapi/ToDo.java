package com.verint.todoapi;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Getter
@Setter
public class ToDo {
    private final Long ID;
    private final String name;
}
