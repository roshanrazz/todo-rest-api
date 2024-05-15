package com.roshan.todo.service.impl;

import com.roshan.todo.dto.TodoCreateDto;
import com.roshan.todo.entity.Todo;
import com.roshan.todo.repository.TodoRepository;
import com.roshan.todo.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TodoServiceImpl implements TodoService {

    private final TodoRepository todoRepository;

    @Override
    public Todo create(TodoCreateDto todoCreateDto) {
        Todo todo = new Todo();
        todo.setTitle(todoCreateDto.getTitle());
        todo.setCompleted(false);
        return todoRepository.save(todo);
    }

    @Override
    public Page<Todo> getAll(Pageable pageable) {
        return todoRepository.findAll(pageable);
    }

    @Override
    public Todo getOne(Long id) {
        return todoRepository.findById(id).orElseThrow(() -> new RuntimeException("Todo not found"));
    }

    @Override
    public Todo update(TodoCreateDto todo, Long id) {
        Todo todoOld = getOne(id);
        todoOld.setTitle(todo.getTitle());
        return todoRepository.save(todoOld);
    }

    @Override
    public void delete(Long id) {
        Todo todo = getOne(id);
        todoRepository.delete(todo);
    }

    @Override
    public Todo completed(Long id) {
        Todo todo = getOne(id);
        todo.setCompleted(true);
        return todoRepository.save(todo);
    }
}
