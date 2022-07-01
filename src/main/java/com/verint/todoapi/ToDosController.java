package com.verint.todoapi;

import com.verint.todoapi.api.TodosApi;
import com.verint.todoapi.model.ToDoDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ToDosController implements TodosApi{

    private final ToDosService toDosService;


    public ToDosController(ToDosService toDosService){
        this.toDosService = toDosService;
    }

    @Override
    public ResponseEntity<List<ToDoDTO>> getTodos() {
        return ResponseEntity.ok(toDosService.getAll());
    }
}
