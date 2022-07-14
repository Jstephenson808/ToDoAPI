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

// todo change name for task
@ExtendWith(MockitoExtension.class)
class ToDoServiceTest {

    @Mock
    private ToDoRepository toDoRepository;

    @Test
    void getAll_shouldReturnSingleToDo() {
        ToDoService toDoService = new ToDoService(toDoRepository, getMapper(ToDoMapper.class));
        ToDo toDo = new ToDo(1L, "James S");
        when(toDoRepository.findAll()).thenReturn(List.of(toDo));
        List<ToDoDTO> toDoDTOList = toDoService.getAll();

        //matcher construction
        assertThat(toDoDTOList, contains(ToDoDTOMatcher.toDoDTO(1L, "James S")));
    }

    // create a class that can map from DTO -> entity

    @Test
    void create_shouldCallSave(){
        ToDoService toDoService = new ToDoService(toDoRepository, getMapper(ToDoMapper.class));
        ArgumentCaptor<ToDo> argumentCaptor = ArgumentCaptor.forClass(ToDo.class);
        when(toDoRepository.save(any()))
                .thenReturn(new ToDo(1L,"James S"));

        toDoService.create(ToDoDtoBuilder.builder()
                .name("James S")
                .build());
        verify(toDoRepository).save(argumentCaptor.capture());
        assertThat(argumentCaptor.getValue(), is(ToDoMatcher.toDo(null, "James S")));
    }

    @Test
    void create_shouldReturnCreatedToDoDto(){
        ToDoService toDoService = new ToDoService(toDoRepository, getMapper(ToDoMapper.class));
        when(toDoRepository.save(any())).thenReturn(new ToDo(2L, "Get some milk"));

        ToDoDTO createdToDo = toDoService.create(ToDoDtoBuilder.builder()
                                                                .name("Get some milk")
                                                                .build());
        assertThat(createdToDo, is(ToDoDTOMatcher.toDoDTO(2L,"Get some milk")));
    }
}