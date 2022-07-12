package com.verint.todoapi;

import com.verint.todoapi.model.ToDoDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ToDosService {

    private final ToDoRepository toDoRepository;

    public List<ToDoDTO> getAll() {
        return toDoRepository.findAll().stream().map(ToDo -> {
            ToDoDTO toDoDTO = new ToDoDTO();
            toDoDTO.setId(ToDo.getID());
            toDoDTO.setName(ToDo.getName());
            return toDoDTO;
        }).toList();
    }

}
