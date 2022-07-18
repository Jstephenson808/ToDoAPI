package com.verint.todoapi;

import com.verint.todoapi.model.ToDoDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.mapstruct.factory.Mappers.getMapper;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ToDoServiceTest {

    @Mock
    private ToDoRepository toDoRepository;
    private ToDoService toDoService;

    @BeforeEach
    void setUp() {
        toDoService = new ToDoService(toDoRepository, getMapper(ToDoMapper.class));
    }

    @Test
    void getAll_shouldReturnSingleToDo() {
        ToDo toDo = new ToDo(1L, "James S");
        when(toDoRepository.findAll()).thenReturn(List.of(toDo));

        List<ToDoDTO> toDoDTOList = toDoService.getAll();

        assertThat(toDoDTOList, contains(ToDoDTOMatcher.toDoDTO(1L, "James S")));
    }

    @Test
    void create_shouldCallSave(){
        ArgumentCaptor<ToDo> argumentCaptor = ArgumentCaptor.forClass(ToDo.class);
        when(toDoRepository.save(any()))
                .thenReturn(new ToDo(1L,"James S"));

        toDoService.create(ToDoDTOBuilder.builder()
                .name("James S")
                .build());
        verify(toDoRepository).save(argumentCaptor.capture());
        assertThat(argumentCaptor.getValue(), is(ToDoMatcher.toDo(null, "James S")));
    }

    @Test
    void create_ToDoDTO_shouldReturnCreatedToDoDTO(){
        when(toDoRepository.save(any())).thenReturn(new ToDo(2L, "Get some milk"));

        ToDoDTO createdToDo = toDoService.create(ToDoDTOBuilder.builder()
                                                                .name("Get some milk")
                                                                .build());
        assertThat(createdToDo, is(ToDoDTOMatcher.toDoDTO(2L,"Get some milk")));
    }

    @Test
    void create_null_shouldReturnNull(){
        when(toDoRepository.save(any())).thenReturn(null);

        ToDoDTO createdToDo = toDoService.create(null);
        assertThat(createdToDo, is(nullValue()));
    }
}