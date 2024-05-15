package com.roshan.todo.service;

import com.roshan.todo.dto.TodoCreateDto;
import com.roshan.todo.entity.Todo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TodoService {

    Todo create(TodoCreateDto todoCreateDto);
    Page<Todo> getAll(Pageable pageable);
    Todo getOne(Long id);
    Todo update(TodoCreateDto todo, Long id);
    void delete(Long id);
    Todo completed(Long id);
}
