package com.verint.todoapi;


import com.verint.todoapi.model.ToDoDTO;
import lombok.Generated;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingConstants.ComponentModel;

import javax.swing.*;

import static org.mapstruct.MappingConstants.ComponentModel.*;

@Generated
@Mapper(componentModel = SPRING)
public interface ToDoMapper {
    ToDoDTO entityToDto(ToDo toDo);
    ToDo dtoToEntity(ToDoDTO toDoDTO);
}
