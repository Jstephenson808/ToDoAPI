package com.verint.todoapi;

import com.verint.todoapi.api.TodosApi;
import com.verint.todoapi.model.ToDoDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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

    @Override
    public ResponseEntity<Void> deleteToDo(Long id) {
        boolean success = toDoService.delete(id);
        ResponseEntity<Void> response;
        if(success){
            response = ResponseEntity.noContent().build();
        }
        else{
            response = ResponseEntity.notFound().build();
        }
        return response;
    }

    @Override
    public ResponseEntity<ToDoDTO> editToDo(Long id, ToDoDTO body) {
        return ResponseEntity.ok(toDoService.edit(id,body));
    }

}
