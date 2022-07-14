package com.verint.todoapi;

import com.verint.todoapi.model.ToDoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ToDoService {

    private final ToDoRepository toDoRepository;
    private final ToDoMapper toDoMapper;

    @Autowired
    public ToDoService(ToDoRepository toDoRepository, ToDoMapper toDoMapper) {
        this.toDoRepository = toDoRepository;
        this.toDoMapper = toDoMapper;
    }

    public List<ToDoDTO> getAll() {
        return toDoRepository.findAll().stream().map(ToDo -> toDoMapper.entityToDto(ToDo)).toList();
    }

    public ToDoDTO create(ToDoDTO postedToDo) {
        return toDoMapper.entityToDto(toDoRepository.save(toDoMapper.dtoToEntity(postedToDo)));
    }
}
