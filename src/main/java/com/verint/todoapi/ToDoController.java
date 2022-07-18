package com.verint.todoapi;

import com.verint.todoapi.api.TodosApi;
import com.verint.todoapi.model.ToDoDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
@RequiredArgsConstructor
public class ToDoController implements TodosApi {

    private final ToDoService toDoService;

    @Override
    public ResponseEntity<List<ToDoDTO>> getToDos() {
        return ResponseEntity.ok(toDoService.getAll());
    }

    @Override
    public ResponseEntity<ToDoDTO> createToDo(ToDoDTO body) {
        return ResponseEntity.ok(toDoService.create(body));
    }

}
