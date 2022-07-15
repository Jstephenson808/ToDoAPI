package com.verint.todoapi;

import com.verint.todoapi.model.ToDoDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ToDoService {

    private final ToDoRepository toDoRepository;
    private final ToDoMapper toDoMapper;

    public List<ToDoDTO> getAll() {
        return toDoRepository.findAll().stream().map(toDoMapper::entityToDto).toList();
    }

    public ToDoDTO create(ToDoDTO postedToDo) {
        return toDoMapper.entityToDto(toDoRepository.save(toDoMapper.dtoToEntity(postedToDo)));
    }
}
