package com.verint.todoapi;

import com.verint.todoapi.model.ToDoDTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class ToDosService {

    private List<ToDoDTO> toDos;

    //this doesn't seem right, you are not specifying an implementation? is that okay?
    public ToDosService(){
        this.toDos = new ArrayList<>();
    }

    //this function just calls a function?
    public List<ToDoDTO> getAll() {
        return getToDos();
    }

    public void setToDos(List<ToDoDTO> toDos){
        this.toDos = toDos;
    }

    public List<ToDoDTO> getToDos() {
        return toDos;
    }

}
