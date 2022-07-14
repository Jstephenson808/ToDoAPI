package com.verint.todoapi;

import com.verint.todoapi.model.ToDoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ToDosService {

    private final ToDoRepository toDoRepository;
    private final ToDoMapper toDoMapper;

    @Autowired
    public ToDosService(ToDoRepository toDoRepository, ToDoMapper toDoMapper) {
        this.toDoRepository = toDoRepository;
        this.toDoMapper = toDoMapper;
    }

    public List<ToDoDTO> getAll() {
        return toDoRepository.findAll().stream().map(ToDo -> {
            ToDoDTO toDoDTO = new ToDoDTO();
            toDoDTO.setId(ToDo.getId());
            toDoDTO.setName(ToDo.getName());
            return toDoDTO;
        }).toList();
    }

    public ToDoDTO create(ToDoDTO postedToDo) {
        return null;
    }
}
