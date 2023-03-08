package com.esprit.achat.rest;

import com.esprit.achat.persistence.entity.Question;
import com.esprit.achat.services.Interface.QuestionService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/question")
    @PreAuthorize("hasRole('User')")
@AllArgsConstructor
public class QuestionController {

    private QuestionService questionService;

    @GetMapping
    List<Question> retrieveAll(){
        return questionService.retrieveAll();
    }

    @PostMapping("/add")
    void add(@RequestBody Question q){
        questionService.add(q);
    }

    @PutMapping("/edit")
    void update(@RequestBody Question q){
        questionService.update(q);
    }

    @DeleteMapping("/delete/{id}")
    void remove(@PathVariable("id") Integer id){
        questionService.remove(id);
    }

    @GetMapping("/{id}")
    Question retrieve(@PathVariable("id") Integer id){
        return questionService.retrieve(id);
    }

}
