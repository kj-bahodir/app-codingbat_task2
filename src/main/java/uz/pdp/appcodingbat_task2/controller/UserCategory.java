package uz.pdp.appcodingbat_task2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appcodingbat_task2.entity.User;
import uz.pdp.appcodingbat_task2.payload.ApiResponse;
import uz.pdp.appcodingbat_task2.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserCategory {

    @Autowired
    UserService userService;

    @GetMapping
    public HttpEntity<?> getUsers() {
        List<User> users = userService.getUsers();
        return ResponseEntity.ok().body(users);
    }

    @GetMapping("/{id}")
    public HttpEntity<?> getUser(@PathVariable Integer id) {
        User user = userService.getUser(id);
        return ResponseEntity.ok().body(user);
    }

    @DeleteMapping("/{id}")
    public HttpEntity<?> delete(@PathVariable Integer id) {
        ApiResponse apiResponse = userService.deleteUser(id);
        return ResponseEntity.status(apiResponse.isSuccess() ? HttpStatus.ACCEPTED : HttpStatus.NOT_FOUND).body(apiResponse);
    }

    @PostMapping("/add")
    public HttpEntity<?> add(@RequestBody User user) {
        ApiResponse apiResponse = userService.add(user);
        return ResponseEntity.status(apiResponse.isSuccess() ? HttpStatus.CREATED : HttpStatus.CONFLICT).body(apiResponse);
    }

    @PutMapping("/{id}")
    public HttpEntity<?> edit(@PathVariable Integer id, @RequestBody User user) {
        ApiResponse apiResponse = userService.edit(id, user);
        return ResponseEntity.status(apiResponse.isSuccess() ? HttpStatus.ACCEPTED : HttpStatus.NOT_FOUND).body(apiResponse);
    }
}
