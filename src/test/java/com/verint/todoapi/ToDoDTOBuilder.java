package com.verint.todoapi;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.verint.todoapi.model.ToDoDTO;

public class ToDoDTOBuilder {

    public static ToDoDTO generateToDoDTO(Long id, String name){
        ToDoDTO dto = new ToDoDTO();
        dto.setName(name);
        dto.setId(id);
        return dto;
    }

    public static String generateToDoDTOJson(Long id, String name) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(generateToDoDTO(id,name));
    }
}
