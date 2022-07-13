package com.verint.todoapi;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.verint.todoapi.model.TodosPostRequestBodyDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.GsonJsonParser;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.GsonHttpMessageConverter;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Collections;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@WebMvcTest
class ToDosControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ToDosService toDosService;


    @Test
    void getToDos_callsService() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/todos"));

        verify(toDosService).getAll();
    }

    // methodUnderTest_scenario_expectedResult
    @Test
    void getToDos_noToDos_emptyArray() throws Exception {
        when(toDosService.getAll()).thenReturn(Collections.emptyList());

        mockMvc.perform(MockMvcRequestBuilders.get("/todos")).andExpect(content().json("[]"));
    }

    @Test
    void postToDo_callsService() throws Exception{
        TodosPostRequestBodyDTO testContent = new TodosPostRequestBodyDTO();
        testContent.setName("James S");
        ObjectMapper mapper = new ObjectMapper();
        String jsonString = mapper.writeValueAsString(testContent);
        mockMvc.perform(MockMvcRequestBuilders.post("/todos").contentType(MediaType.APPLICATION_JSON).content(jsonString));

        verify(toDosService).save();
    }

}