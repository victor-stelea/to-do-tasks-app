package com.isd.todo.service;

import com.isd.todo.domain.ToDoTask;
import com.isd.todo.exception.NoToDoTaskFoundException;
import com.isd.todo.repository.ToDoTaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ToDoTaskService {

    @Autowired
    private ToDoTaskRepository repository;

    public List<ToDoTask> getAllToDoTasks() {
        return (List<ToDoTask>) repository.findAll();
    }

    public ToDoTask getToDoTaskById(String id) throws NoToDoTaskFoundException {
        ToDoTask result = repository.findById(id).get();

        // cannot work with ".isPresent()" of Optional
        // I guess for some Redis reasons, ".findById()" always returns an entity, event if all its values are null
        if (result.getId() == null) {
            throw new NoToDoTaskFoundException("Could not find TO-DO task with ID: " + id);
        }
        return result;
    }

    public void saveNewToDoTask(String id, String name, String description, String status, String estimation) {
        ToDoTask taskToSave = ToDoTask.builder()
                .setId(id)
                .setName(name)
                .setDescription(description)
                .setStatus(status)
                .setEstimation(estimation)
                .build();
        repository.save(taskToSave);
    }
}
