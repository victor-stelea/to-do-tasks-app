package com.isd.todo.repository;

import com.isd.todo.domain.ToDoTask;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ToDoTaskRepository extends CrudRepository<ToDoTask, String> {}
