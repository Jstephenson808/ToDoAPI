package com.verint.todoapi;

import com.verint.todoapi.model.TodosPostRequestBodyDTO;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;


// TODO refactor API schema to use DTO schema
@WebMvcTest
class ToDosControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ToDosService toDosService;


    @Test
    void getToDos_callsService() throws Exception {
        mockMvc.perform(get("/todos"));

        verify(toDosService).getAll();
    }

    // methodUnderTest_scenario_expectedResult
    @Test
    void getToDos_noToDos_emptyArray() throws Exception {
        when(toDosService.getAll()).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/todos")).andExpect(content().json("[]"));
    }

    @Test
    void postToDo_callsService() throws Exception{
        ArgumentCaptor<TodosPostRequestBodyDTO> argumentCaptor = ArgumentCaptor.forClass(TodosPostRequestBodyDTO.class);
        String jsonString = ToDosPostRequestBodyDTOBuilder.generatePostRequestJson("James S");

        mockMvc.perform(post("/todos").contentType(APPLICATION_JSON).content(jsonString));

        verify(toDosService).save(argumentCaptor.capture());
    }

    @Test
    void postToDo_callsServiceWithDTOGiven() throws Exception{
        ArgumentCaptor<TodosPostRequestBodyDTO> argumentCaptor = ArgumentCaptor.forClass(TodosPostRequestBodyDTO.class);
        TodosPostRequestBodyDTO testContent = ToDosPostRequestBodyDTOBuilder.generatePostRequestDTO("James S");
        String jsonString = ToDosPostRequestBodyDTOBuilder.generatePostRequestJson("James S");

        mockMvc.perform(post("/todos")
                .contentType(APPLICATION_JSON).content(jsonString));

        verify(toDosService).save(argumentCaptor.capture());
        TodosPostRequestBodyDTO dto = argumentCaptor.getValue();
        assert(dto.getName().equals(testContent.getName()));
    }

    @Test
    void postToDo_returnsToDoDTO() throws Exception{
        ArgumentCaptor<TodosPostRequestBodyDTO> argumentCaptor = ArgumentCaptor.forClass(TodosPostRequestBodyDTO.class);
        String postedJsonString = ToDosPostRequestBodyDTOBuilder.generatePostRequestJson("James S");
        String returnDtoJson = ToDoDTOBuilder.generateToDoDTOJson(1L,"James S");

        when(toDosService.save(argumentCaptor.capture())).thenReturn(ToDoDTOBuilder.generateToDoDTO(1L,"James S"));

        mockMvc.perform(post("/todos")
                .contentType(APPLICATION_JSON).content(postedJsonString))
                .andExpect(content().json(returnDtoJson));
    }

}