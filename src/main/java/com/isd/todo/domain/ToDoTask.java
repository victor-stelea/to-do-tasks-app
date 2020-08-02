package com.isd.todo.domain;

import org.springframework.data.redis.core.RedisHash;

import javax.persistence.Id;

@RedisHash
public class ToDoTask {

    @Id
    private String id;
    private String name;
    private String description;
    private String status;
    private String estimation;

    public ToDoTask(String id, String name, String description, String status, String estimation) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.status = status;
        this.estimation = estimation;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getEstimation() {
        return estimation;
    }

    public void setEstimation(String estimation) {
        this.estimation = estimation;
    }

    public static ToDoTaskBuilder builder() {
        return new ToDoTaskBuilder();
    }

    public static class ToDoTaskBuilder {
        private String id;
        private String name;
        private String description;
        private String status;
        private String estimation;

        public ToDoTaskBuilder setId(final String id) {
            this.id = id;
            return this;
        }

        public ToDoTaskBuilder setName(final String name) {
            this.name = name;
            return this;
        }

        public ToDoTaskBuilder setDescription(final String description) {
            this.description = description;
            return this;
        }

        public ToDoTaskBuilder setStatus(final String status) {
            this.status = status;
            return this;
        }

        public ToDoTaskBuilder setEstimation(final String estimation) {
            this.estimation = estimation;
            return this;
        }

        public ToDoTask build() {
            return new ToDoTask(id, name, description, status, estimation);
        }
    }
}
