package com.verint.todoapi;

import com.verint.todoapi.api.TodosApi;
import com.verint.todoapi.model.ToDoDTO;
import com.verint.todoapi.model.TodosPostRequestBodyDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
@RequiredArgsConstructor
public class ToDosController implements TodosApi {

    private final ToDosService toDosService;

    @Override
    public ResponseEntity<List<ToDoDTO>> getTodos() {
        return ResponseEntity.ok(toDosService.getAll());
    }

    @Override
    public ResponseEntity<ToDoDTO> postTodos(TodosPostRequestBodyDTO body) {
        toDosService.save();
        return null;
    }

}
