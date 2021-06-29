package uz.pdp.appcodingbat_task2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appcodingbat_task2.entity.ProgramLanguage;
import uz.pdp.appcodingbat_task2.payload.ApiResponse;
import uz.pdp.appcodingbat_task2.repository.ProgramLanguageRepository;
import uz.pdp.appcodingbat_task2.service.ProgramLanguageService;

import java.util.List;

@RestController
@RequestMapping("/programLanguage")
public class ProgramLanguageController {

    @Autowired
    ProgramLanguageService programLanguageService;

    @GetMapping
    public HttpEntity<?> getProgramLanguages() {
        List<ProgramLanguage> programLanguages = programLanguageService.getProgramLanguages();
        return ResponseEntity.ok().body(programLanguages);
    }

    @GetMapping("/{id}")
    public HttpEntity<?>getProgramLanguage(@PathVariable Integer id){
        ProgramLanguage programLanguage=programLanguageService.getProgramLanguageById(id);
        return ResponseEntity.ok().body(programLanguage);
    }

    @DeleteMapping("/{id}")
    public HttpEntity<?> delete(@PathVariable Integer id){
        ApiResponse apiResponse=programLanguageService.deleteProgramLanguage(id);
        return ResponseEntity.status(apiResponse.isSuccess()? HttpStatus.ACCEPTED:HttpStatus.NOT_FOUND).body(apiResponse);
    }

    @PostMapping("/add")
    public HttpEntity<?> add(@RequestBody ProgramLanguage programLanguage){
        ApiResponse apiResponse=programLanguageService.add(programLanguage);
        return ResponseEntity.status(apiResponse.isSuccess()?HttpStatus.CREATED:HttpStatus.BAD_REQUEST).body(apiResponse);
    }

    @PutMapping("/{id}")
    public HttpEntity<?> edit(@PathVariable Integer id, @RequestBody ProgramLanguage programLanguage){
        ApiResponse apiResponse=programLanguageService.edit(id,programLanguage);
        return ResponseEntity.status(apiResponse.isSuccess()?HttpStatus.ACCEPTED:HttpStatus.NOT_FOUND).body(apiResponse);
    }




}
