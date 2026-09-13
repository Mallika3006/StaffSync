package com.mallika.EmployeeManagementSystem.service;

import com.mallika.EmployeeManagementSystem.exception.ResourceNotFoundException;
import com.mallika.EmployeeManagementSystem.model.Task;
import com.mallika.EmployeeManagementSystem.repository.TaskRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    // CREATE
    public Task createTask(Task task) {
        return taskRepository.save(task);
    }

    // GET ALL
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    // GET BY ID
    public Task getTaskById(Integer id) {
        return taskRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Task not found with id: " + id
                        ));
    }

    // UPDATE
    public Task updateTask(Integer id, Task taskDetails) {

        Task task = getTaskById(id);

        task.setTaskName(taskDetails.getTaskName());
        task.setDescription(taskDetails.getDescription());
        task.setPriority(taskDetails.getPriority());
        task.setStatus(taskDetails.getStatus());
        task.setStartDate(taskDetails.getStartDate());
        task.setDueDate(taskDetails.getDueDate());
        task.setProject(taskDetails.getProject());
        task.setAssignedTo(taskDetails.getAssignedTo());

        return taskRepository.save(task);
    }

    // DELETE
    public void deleteTask(Integer id) {

        Task task = getTaskById(id);

        taskRepository.delete(task);
    }

    // SEARCH BY TASK NAME
    public List<Task> searchByTaskName(String taskName) {
        return taskRepository
                .findByTaskNameContainingIgnoreCase(taskName);
    }

    // BY PROJECT
    public List<Task> getTasksByProject(Integer projectId) {
        return taskRepository
                .findByProjectProjectId(projectId);
    }

    // BY EMPLOYEE
    public List<Task> getTasksByEmployee(Integer employeeId) {
        return taskRepository
                .findByAssignedToEmployeeId(employeeId);
    }

    // BY STATUS
    public List<Task> getTasksByStatus(String status) {
        return taskRepository
                .findByStatusIgnoreCase(status);
    }

    // BY PRIORITY
    public List<Task> getTasksByPriority(String priority) {
        return taskRepository
                .findByPriorityIgnoreCase(priority);
    }

    // EMPLOYEE + STATUS
    public List<Task> getEmployeeTasksByStatus(
            Integer employeeId,
            String status) {

        return taskRepository
                .findByAssignedToEmployeeIdAndStatusIgnoreCase(
                        employeeId,
                        status
                );
    }

    // PROJECT + STATUS
    public List<Task> getProjectTasksByStatus(
            Integer projectId,
            String status) {

        return taskRepository
                .findByProjectProjectIdAndStatusIgnoreCase(
                        projectId,
                        status
                );
    }

    // BY DUE DATE
    public List<Task> getTasksByDueDate(LocalDate dueDate) {
        return taskRepository.findByDueDate(dueDate);
    }

    // BETWEEN DUE DATES
    public List<Task> getTasksBetweenDueDates(
            LocalDate startDate,
            LocalDate endDate) {

        return taskRepository.findByDueDateBetween(
                startDate,
                endDate
        );
    }

    // OVERDUE TASKS
    public List<Task> getOverdueTasks(LocalDate date) {
        return taskRepository
                .findByDueDateBeforeAndStatusNotIgnoreCase(
                        date,
                        "Completed"
                );
    }

    // SORT
    public List<Task> sortTasks(
            String field,
            String direction) {

        Sort sort = direction.equalsIgnoreCase("desc")
                ? Sort.by(field).descending()
                : Sort.by(field).ascending();

        return taskRepository.findAll(sort);
    }
}