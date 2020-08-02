package com.isd.todo.controller;

import com.google.gson.Gson;
import com.isd.todo.domain.ToDoTask;
import com.isd.todo.exception.NoToDoTaskFoundException;
import com.isd.todo.service.ToDoTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/secured/to-do-tasks")
public class ToDoTaskRestController {

    @Autowired
    private ToDoTaskService toDoTaskService;

    @GetMapping("/all")
    public ResponseEntity<List<ToDoTask>> getAllToDoTasks() {
        return ResponseEntity.ok(toDoTaskService.getAllToDoTasks());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getToDoTaskById(@PathVariable("id") String id) {
        try {
            return ResponseEntity.ok(toDoTaskService.getToDoTaskById(id));
        } catch (NoToDoTaskFoundException ex) {
            ex.printStackTrace();
            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Type", "application/json; charset=utf-8");
            return new ResponseEntity<>(new Gson().toJson(ex.getMessage()), headers, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/save-new-task")
    public ResponseEntity<String> saveNewToDoTask(@RequestParam("id") String id,
                                                    @RequestParam("name") String name,
                                                    @RequestParam("description") String description,
                                                    @RequestParam("status") String status,
                                                    @RequestParam("estimation") String estimation) {

        toDoTaskService.saveNewToDoTask(id, name, description, status, estimation);
        return ResponseEntity.ok("New task saved...");
    }
}
