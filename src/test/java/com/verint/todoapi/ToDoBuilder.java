package com.verint.todoapi;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.verint.todoapi.model.ToDoDTO;
import lombok.Builder;

// ToDo name of this class
public class ToDoBuilder {

    @Builder
    public static ToDoDTO generateToDo(Long id, String name){
        ToDoDTO dto = new ToDoDTO();
        dto.setName(name);
        dto.setId(id);
        return dto;
    }

    public static String generateToDoDTOJson(Long id, String name) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(generateToDo(id,name));
    }

    public static String generateToDoDTOJson(String name) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(builder().name(name).build());
    }
}
