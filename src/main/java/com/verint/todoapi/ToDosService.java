package com.verint.todoapi;

import com.verint.todoapi.model.ToDoDTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class ToDosService {

    List<ToDoDTO> toDos;

    public ToDosService(){
        this.toDos = new ArrayList<>();
    }

    public List<ToDoDTO> getAll() {
        return Collections.emptyList();
    }

    public void setToDos(List<ToDoDTO> toDos){
        this.toDos = toDos;
    }
}
