package com.esprit.achat.rest;

import com.esprit.achat.persistence.entity.Question;
import com.esprit.achat.services.Interface.QuestionService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/question")
@AllArgsConstructor
public class QuestionController {

    private QuestionService questionService;

    @GetMapping
    List<Question> retrieveAll(){
        return questionService.retrieveAll();
    }

    @PostMapping("/add")
    void add(Question q){
        questionService.add(q);
    }

    @PutMapping("/edit")
    void update(Question q){
        questionService.update(q);
    }

    @DeleteMapping("/delete/{id}")
    void remove(Integer id){
        questionService.remove(id);
    }

    @GetMapping("/{id}")
    Question retrieve(Integer id){
        return questionService.retrieve(id);
    }
}
