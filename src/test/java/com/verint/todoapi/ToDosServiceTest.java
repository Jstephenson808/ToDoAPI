package com.verint.todoapi;

import com.verint.todoapi.model.ToDoDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.mapstruct.factory.Mappers.getMapper;
import static org.mockito.Mockito.when;

class ToDosServiceTest {

    @Mock
    private ToDoRepository toDoRepository;
    private ToDoMapper toDoMapper = getMapper(ToDoMapper.class);

    @ExtendWith(MockitoExtension.class)
    @Test
    void getAll_shouldReturnSingleToDo() {
        ToDosService toDosService = new ToDosService(toDoRepository, toDoMapper);
        ToDo toDo = new ToDo();
        toDo.setID(1L);
        toDo.setName("James S");
        when(toDoRepository.findAll()).thenReturn(List.of(toDo));
        List<ToDoDTO> toDoDTOList = toDosService.getAll();

        //matcher construction
        assertThat(toDoDTOList, contains(ToDoDTOMatcher.toDoDTO(1L, "James S")));
    }

    // create a class that can map from DTO -> entity

    @Test
    void create_shouldReturnDtoWithId(){
        ToDosService toDosService = new ToDosService(toDoRepository, toDoMapper);
        ToDoDTO toDoDTO = ToDoBuilder.generateToDo(1L,"James S");

    }


}