package com.verint.todoapi;

import com.verint.todoapi.model.ToDoDTO;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;

import static com.verint.todoapi.ToDoDTOBuilder.generateToDo;
import static com.verint.todoapi.ToDoDTOMatcher.toDoDTO;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;


@WebMvcTest
class ToDoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ToDoService toDoService;


    @Test
    void getToDos_callsService() throws Exception {
        mockMvc.perform(get("/todos"));

        verify(toDoService).getAll();
    }

    @Test
    void getToDos_noToDos_emptyArray() throws Exception {
        when(toDoService.getAll()).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/todos")).andExpect(content().json("[]"));
    }

    @Test
    void postToDo_callsServiceWithDTOGiven() throws Exception{
        ArgumentCaptor<ToDoDTO> toDoCaptor = ArgumentCaptor.forClass(ToDoDTO.class);
        String jsonString = """
                           {"name":"James S"}
                            """;

        mockMvc.perform(post("/todos")
                .contentType(APPLICATION_JSON)
                .content(jsonString));

        verify(toDoService).create(toDoCaptor.capture());
        assertThat(toDoCaptor.getValue(), is(toDoDTO(null,"James S")));
    }

    @Test
    void postToDo_returnsToDoDTO() throws Exception{
        ArgumentCaptor<ToDoDTO> argumentCaptor = ArgumentCaptor.forClass(ToDoDTO.class);

        when(toDoService.create(argumentCaptor.capture())).thenReturn(generateToDo(1L,"James S"));

        mockMvc.perform(post("/todos")
                .contentType(APPLICATION_JSON).content("""
                                                        {"name":"James S"}
                                                        """))
                .andExpect(content().json("""
                                             {"id":1,"name":"James S"}
                                            """));
    }

}