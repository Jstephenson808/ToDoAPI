package com.verint.todoapi;


import com.verint.todoapi.model.ToDoDTO;
import org.mapstruct.Mapper;

@Mapper
public interface ToDoMapper {
    ToDoDTO entityToDto(ToDo toDo);
    ToDo dtoToEntity(ToDoDTO toDoDTO);
}
