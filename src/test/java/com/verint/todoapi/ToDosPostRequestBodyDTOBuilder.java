package com.verint.todoapi;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.verint.todoapi.model.TodosPostRequestBodyDTO;

public class ToDosPostRequestBodyDTOBuilder {

    public static TodosPostRequestBodyDTO generatePostRequestDTO(String name){
        TodosPostRequestBodyDTO dto = new TodosPostRequestBodyDTO();
        dto.setName(name);
        return dto;
    }

    public static String generatePostRequestJson(String name) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(generatePostRequestDTO(name));
    }
}