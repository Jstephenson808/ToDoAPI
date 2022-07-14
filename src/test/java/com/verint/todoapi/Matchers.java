package com.verint.todoapi;

import com.verint.todoapi.model.ToDoDTO;
import lombok.RequiredArgsConstructor;
import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;

import java.util.Objects;

// ToDo is this wise?
public class Matchers {
    @RequiredArgsConstructor
    public static class ToDoMatcher extends TypeSafeMatcher<ToDoDTO> {

        private final Long id;
        private final String name;

        public static ToDoMatcher generateToDoMatcher(Long id, String name) {
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
