package com.verint.todoapi;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.verint.todoapi.model.ToDoDTO;
import lombok.Builder;

public class ToDoDTOBuilder {

    @Builder(builderClassName = "ToDoDTOBuilderImpl")
    public static ToDoDTO generateToDo(Long id, String name){
        ToDoDTO dto = new ToDoDTO();
        dto.setName(name);
        dto.setId(id);
        return dto;
    }
}
