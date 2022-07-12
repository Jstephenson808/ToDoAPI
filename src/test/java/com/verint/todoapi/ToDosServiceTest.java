package com.verint.todoapi;

import com.verint.todoapi.model.ToDoDTO;
import lombok.RequiredArgsConstructor;
import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Objects;

import static com.verint.todoapi.ToDosServiceTest.ToDoMatcher.toDo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.mockito.Mockito.when;

class ToDosServiceTest {

    @Mock
    private ToDoRepository toDoRepository;

    @ExtendWith(MockitoExtension.class)
    @Test
    void getAll_shouldReturnSingleToDo() {
        ToDosService toDosService = new ToDosService(toDoRepository);
        when(toDoRepository.findAll()).thenReturn(List.of(new ToDo(1L,"James S")));
        List<ToDoDTO> toDoDTOList = toDosService.getAll();

        //matcher construction
        assertThat(toDoDTOList, contains(toDo(1L, "James S")));
    }

    @RequiredArgsConstructor
    static class ToDoMatcher extends TypeSafeMatcher<ToDoDTO> {

        private final Long id;
        private final String name;

        public static ToDoMatcher toDo(Long id, String name) {
            return new ToDoMatcher(id, name);
        }

        @Override
        protected boolean matchesSafely(ToDoDTO item) {
            return Objects.equals(id, item.getId()) &&
                    Objects.equals(name, item.getName());
        }

        @Override
        protected void describeMismatchSafely(ToDoDTO item, Description mismatchDescription) {
            describe(mismatchDescription, item.getId(), item.getName());
        }

        @Override
        public void describeTo(Description description) {
            describe(description, id, name);
        }

        private void describe(Description description, Long id, String name) {
            description.appendText("<ToDoDTO(id=").appendValue(id).appendText(", name=").appendValue(name).appendText(")>");
        }
    }
}