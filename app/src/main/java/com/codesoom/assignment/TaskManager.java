package com.codesoom.assignment;


import com.codesoom.assignment.Exception.TaskNotFoundException;
import com.codesoom.assignment.models.Task;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

public class TaskManager {
    private Long nextId = 1L;
    private HashMap<Long, Task> tasks = new HashMap<>();

    public void create(String title) {
        Long newId = nextId++;
        Task newTask = new Task(newId, title);
        this.tasks.put(newId, newTask);
    }

    public List<Task> getAll() {
        return new ArrayList<Task>(this.tasks.values());
    }

    public Task getOne(Long id) throws TaskNotFoundException {
        if(!exist(id)) {
            throw new TaskNotFoundException("Not Found Task");
        }

        return this.tasks.get(id);
    }

    public Task getLast() throws TaskNotFoundException {
        return getOne(nextId - 1);
    }

    public void remove(Long id) throws TaskNotFoundException {
        if(!exist(id)) {
            throw new TaskNotFoundException("Not Found Task");
        }

        this.tasks.remove(id);
    }

    public void update(Long id, String title) throws TaskNotFoundException {
        Task updateTask = getOne(id);
        updateTask.setTitle(title);
    }

    private boolean exist(Long id) {
        return this.tasks.containsKey(id);
    }
}
