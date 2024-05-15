package com.roshan.todo.controller;

import com.roshan.todo.dto.TodoCreateDto;
import com.roshan.todo.entity.Todo;
import com.roshan.todo.service.TodoService;
import com.roshan.todo.utils.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/todos")
public class TodoController {

    private final TodoService todoService;

    @PostMapping
    public ResponseEntity<ApiResponse> create(@Valid @RequestBody TodoCreateDto todoCreateDto) {
        try {
            Todo todo = todoService.create(todoCreateDto);
            return new ResponseEntity<>(new ApiResponse(true, 201, "Todo created successfully", todo), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(new ApiResponse(false, 500, e.getMessage(), null), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping
    public ResponseEntity<ApiResponse> getAll(Pageable pageable) {
        try {
            Page<Todo> todos = todoService.getAll(pageable);
            return new ResponseEntity<>(new ApiResponse(true, 200, "Todos fetched successfully", todos), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ApiResponse(false, 500, e.getMessage(), null), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> getOne(@PathVariable Long id) {
        try {
            Todo todo = todoService.getOne(id);
            return new ResponseEntity<>(new ApiResponse(true, 200, "Todo fetched successfully", todo), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ApiResponse(false, 404, e.getMessage(), null), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> delete(@PathVariable Long id) {
        try {
            todoService.delete(id);
            return new ResponseEntity<>(new ApiResponse(true, 200, "Todo deleted successfully", null), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ApiResponse(false, 404, e.getMessage(), null), HttpStatus.NOT_FOUND);
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ApiResponse> update(@PathVariable Long id, @Valid @RequestBody TodoCreateDto todoCreateDto) {
        try {
            Todo todo = todoService.update(todoCreateDto, id);
            return new ResponseEntity<>(new ApiResponse(true, 200, "Todo updated successfully", todo), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ApiResponse(false, 404, e.getMessage(), null), HttpStatus.NOT_FOUND);
        }
    }

    @PatchMapping("/{id}/completed")
    public ResponseEntity<ApiResponse> updateCompleted(@PathVariable Long id) {
        try {
            Todo todo = todoService.completed(id);
            return new ResponseEntity<>(new ApiResponse(true, 200, "Todo completed", todo), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ApiResponse(false, 404, e.getMessage(), null), HttpStatus.NOT_FOUND);
        }
    }


}
