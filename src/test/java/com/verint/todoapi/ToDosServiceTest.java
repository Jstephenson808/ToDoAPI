package com.verint.todoapi;

import com.verint.todoapi.model.ToDoDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.is;
import static org.mapstruct.factory.Mappers.getMapper;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class ToDosServiceTest {

    @Mock
    private ToDoRepository toDoRepository;

    @ExtendWith(MockitoExtension.class)
    @Test
    void getAll_shouldReturnSingleToDo() {
        ToDosService toDosService = new ToDosService(toDoRepository, getMapper(ToDoMapper.class));
        ToDo toDo = new ToDo();
        toDo.setId(1L);
        toDo.setName("James S");
        when(toDoRepository.findAll()).thenReturn(List.of(toDo));
        List<ToDoDTO> toDoDTOList = toDosService.getAll();

        //matcher construction
        assertThat(toDoDTOList, contains(ToDoDTOMatcher.toDoDTO(1L, "James S")));
    }

    // create a class that can map from DTO -> entity

    @Test
    void create_shouldCallSave(){
        ArgumentCaptor<ToDo> argumentCaptor = ArgumentCaptor.forClass(ToDo.class);
        ToDosService toDosService = new ToDosService(toDoRepository, getMapper(ToDoMapper.class));
        ToDoDTO testToDoDTO = ToDoBuilder.builder()
                .name("James S")
                .build();
        when(toDoRepository.save(any()))
                .thenReturn(new ToDo(1L,"James S"));

        verify(toDoRepository.save(argumentCaptor.capture()));
        assertThat(argumentCaptor.getValue(), is(ToDoMatcher.toDo(null, "James S")));
    }


}