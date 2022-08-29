package com.verint.todoapi;

import com.verint.todoapi.model.ToDoDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

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

    public boolean delete(Long id) {
        try {
            toDoRepository.deleteById(id);
        } catch (EmptyResultDataAccessException emptyResultDataAccessException){
            return false;
        }
        return true;
    }
    //todo map over object and replace name
    public boolean edit(Long id, ToDoDTO patchedToDo) {

        Optional<ToDo> toDoOptional = toDoRepository.findById(id);
        if (toDoOptional.isPresent()){
            ToDo toDo = toDoOptional.get();
            toDo.setName(patchedToDo.getName());
            toDoRepository.save(toDo);
            return true;
        } else {
            return false;
        }
    }
}
