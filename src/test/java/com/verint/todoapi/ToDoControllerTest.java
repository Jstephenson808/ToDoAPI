package com.verint.todoapi;

import com.verint.todoapi.model.ToDoDTO;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.HeaderResultMatchers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Collections;

import static com.verint.todoapi.ToDoDTOBuilder.generateToDo;
import static com.verint.todoapi.ToDoDTOMatcher.toDoDTO;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;


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
    void postToDo_callsServiceCreateWithDTOGiven() throws Exception{
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

    @Test
    void deleteToDo_callServiceDeleteWithIdGiven() throws Exception{
        ArgumentCaptor<ToDoDTO> argumentCaptor = ArgumentCaptor.forClass(ToDoDTO.class);
        when(toDoService.delete(argumentCaptor.capture())).thenReturn(true);

        mockMvc.perform(delete("/todos")
                .contentType(APPLICATION_JSON)
                .content("""
                         {"id":1}
                        """));

        verify(toDoService).delete(argumentCaptor.capture());
        assertThat(argumentCaptor.getValue(), is(toDoDTO(1L,null)));
    }

    @Test
    void deleteToDo_IDinDatabase_returnsSuccessMessage() throws Exception{
        ArgumentCaptor<ToDoDTO> argumentCaptor = ArgumentCaptor.forClass(ToDoDTO.class);
        when(toDoService.delete(argumentCaptor.capture())).thenReturn(true);
        ResponseEntity response = ResponseEntity.noContent().build();

        mockMvc.perform(delete("/todos")
                .contentType(APPLICATION_JSON)
                .content("""
                         {"id":1}
                        """)).andExpect(MockMvcResultMatchers.status().is(204));

    }

}