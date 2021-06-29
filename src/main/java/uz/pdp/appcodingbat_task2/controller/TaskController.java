package uz.pdp.appcodingbat_task2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appcodingbat_task2.entity.Task;
import uz.pdp.appcodingbat_task2.payload.ApiResponse;
import uz.pdp.appcodingbat_task2.service.TaskService;

import java.util.List;

@RestController
@RequestMapping("task")
public class TaskController {

    @Autowired
    TaskService taskService;

    @GetMapping
    public HttpEntity<List<Task>> getTasks() {
        List<Task> tasks = taskService.getTasks();
        return ResponseEntity.ok().body(tasks);
    }

    @GetMapping("/{id}")
    public HttpEntity<?> getTask(@PathVariable Integer id) {
        Task task = taskService.getTaskById(id);
        return ResponseEntity.ok().body(task);
    }

    @DeleteMapping("/{id}")
    public HttpEntity<?> deleteTask(@PathVariable Integer id) {
        ApiResponse apiResponse = taskService.deleteById(id);
        return ResponseEntity.status(apiResponse.isSuccess() ? HttpStatus.ACCEPTED : HttpStatus.NOT_FOUND).body(apiResponse);
    }

    @PostMapping
    public  HttpEntity<?> addTask(@RequestBody Task task){
       ApiResponse apiResponse= taskService.addTask(task);
       return ResponseEntity.status(apiResponse.isSuccess()?HttpStatus.ACCEPTED:HttpStatus.NOT_FOUND).body(apiResponse);
    }

    @PutMapping("/{id}")
    public HttpEntity<?> editTask(@PathVariable Integer id,@RequestBody Task task){
       ApiResponse apiResponse= taskService.editTask(id,task);
       return ResponseEntity.status(apiResponse.isSuccess()?HttpStatus.ACCEPTED:HttpStatus.NOT_FOUND).body(apiResponse);

    }
}
