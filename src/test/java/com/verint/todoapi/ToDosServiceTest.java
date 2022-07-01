package com.verint.todoapi;

import com.verint.todoapi.model.ToDoDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


class ToDosServiceTest {

    ToDosService toDosService;

    @BeforeEach
    void setUp(){
        toDosService = new ToDosService();
    }

    @Test
    void getAll_withToDosEmpty_emptyList() {
        assertSame(Collections.emptyList(), toDosService.getAll());
    }

    @Test
    void setToDos_testDTO_ListTestDTO() {
        ToDoDTO testDTO = new ToDoDTO();
        testDTO.setId(Long.getLong("1"));
        testDTO.setName("James");
        List<ToDoDTO> testList = new ArrayList<>();
        testList.add(testDTO);

        toDosService.setToDos(testList);

        assertSame(toDosService.toDos, testList);
    }

//    @Test
//    void getAll_withToDos_toDosArray() {
//        ToDoDTO testDTO = new ToDoDTO();
//        testDTO.setId(Long.getLong("1"));
//        testDTO.setName("James");
//        List<ToDoDTO> testList = new ArrayList<ToDoDTO>();
//        testList.add(testDTO);
//
//
//        assertSame();
//    }
}