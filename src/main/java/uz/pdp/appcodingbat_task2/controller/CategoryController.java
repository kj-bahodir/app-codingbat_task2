package uz.pdp.appcodingbat_task2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appcodingbat_task2.entity.Category;
import uz.pdp.appcodingbat_task2.payload.ApiResponse;
import uz.pdp.appcodingbat_task2.service.CategoryService;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    CategoryService categoryService;

    @GetMapping
    public HttpEntity< List<Category> >getCategories(){
        List<Category> categoryList = categoryService.getCategories();
      return ResponseEntity.ok().body(categoryList);
    }
    @GetMapping("/{id}")
    public HttpEntity<?> getCategory(@PathVariable Integer id){
        Category category=categoryService.getCategory(id);
        return ResponseEntity.ok().body(category);
    }
    @DeleteMapping("/{id}")
    public HttpEntity<?> delete(@PathVariable Integer id){
        ApiResponse apiResponse=categoryService.deleteById(id);
        return  ResponseEntity.status(apiResponse.isSuccess()? HttpStatus.ACCEPTED:HttpStatus.NOT_FOUND).body(apiResponse);
    }

    @PostMapping
    public HttpEntity<?> add(@RequestBody Category category){
        ApiResponse apiResponse=categoryService.add(category);
        return ResponseEntity.status(apiResponse.isSuccess()?HttpStatus.CREATED:HttpStatus.CONFLICT).body(apiResponse);
    }
    @PutMapping("/{id}")
    public HttpEntity<?> edit(@PathVariable Integer id, @RequestBody Category category){
        ApiResponse apiResponse=categoryService.edit(id, category);
        return ResponseEntity.status(apiResponse.isSuccess()?HttpStatus.ACCEPTED:HttpStatus.NOT_FOUND).body(apiResponse);
    }
}
