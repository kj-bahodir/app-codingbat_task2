package uz.pdp.appcodingbat_task2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.appcodingbat_task2.entity.Category;
import uz.pdp.appcodingbat_task2.entity.Task;
import uz.pdp.appcodingbat_task2.payload.ApiResponse;
import uz.pdp.appcodingbat_task2.repository.CategoryRepository;
import uz.pdp.appcodingbat_task2.repository.TaskRepository;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    @Autowired
    TaskRepository taskRepository;
    @Autowired
    CategoryRepository categoryRepository;

    public List<Task> getTasks() {
        List<Task> tasks = taskRepository.findAll();
        return tasks;

    }

    public Task getTaskById(Integer id) {
        Optional<Task> optionalTask = taskRepository.findById(id);
        return optionalTask.orElse(null);

    }

    public ApiResponse deleteById(Integer id) {
        try {
            taskRepository.deleteById(id);
            return new ApiResponse("Deleted", true);

        } catch (Exception e) {
            return new ApiResponse("Task not found", false);
        }
    }

    public ApiResponse addTask(Task task) {
        Optional<Category> optionalCategory = categoryRepository.findById(task.getCategory().getId());
        if (!optionalCategory.isPresent()) {
            return new ApiResponse("Categoriya topilmadi", false);
        }
        Category category = optionalCategory.get();
        Task task1 = new Task();
        task1.setName(task.getName());
        task1.setCategory(category);
        task1.setDiscription(task.getDiscription());
        task1.setSolution(task.getSolution());

        taskRepository.save(task1);

        return new ApiResponse("Successfully added", true);

    }

    public ApiResponse editTask(Integer id,Task task) {

        Optional<Task> optionalTask = taskRepository.findById(id);
        if (!optionalTask.isPresent()) {
            return new ApiResponse("Task not found", false);
        }
        Task task1 = optionalTask.get();
        Optional<Category> optionalCategory = categoryRepository.findById(task.getCategory().getId());
        if (!optionalCategory.isPresent()) {
            return new ApiResponse("Categoriya topilmadi", false);
        }
        Category category = optionalCategory.get();
        Task editedTask = optionalTask.get();
        editedTask.setCategory(category);
        editedTask.setDiscription(task1.getDiscription());
        editedTask.setSolution(task1.getSolution());
        editedTask.setName(task1.getName());

        taskRepository.save(editedTask);
        return new ApiResponse("Successfully edited",true);
    }


}
