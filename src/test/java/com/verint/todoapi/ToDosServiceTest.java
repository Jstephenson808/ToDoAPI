package com.verint.todoapi;

import com.verint.todoapi.model.ToDoDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static com.verint.todoapi.Matchers.ToDoMatcher.toDo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.mockito.Mockito.when;

class ToDosServiceTest {

    @Mock
    private ToDoRepository toDoRepository;

    @ExtendWith(MockitoExtension.class)
    @Test
    void getAll_shouldReturnSingleToDo() {
        ToDosService toDosService = new ToDosService(toDoRepository);
        ToDo toDo = new ToDo();
        toDo.setID(1L);
        toDo.setName("James S");
        when(toDoRepository.findAll()).thenReturn(List.of(toDo));
        List<ToDoDTO> toDoDTOList = toDosService.getAll();

        //matcher construction
        assertThat(toDoDTOList, contains(toDo(1L, "James S")));
    }

}