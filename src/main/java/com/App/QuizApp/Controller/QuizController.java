package com.App.QuizApp.Controller;

import com.App.QuizApp.DTO.QuizEntryDto;
import com.App.QuizApp.Models.Quiz;
import com.App.QuizApp.Service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/quizzes")
public class QuizController {
    @Autowired
    QuizService quizService;
    @PostMapping("/create")
    public String create(@RequestBody QuizEntryDto quizEntryDto){
        return quizService.create(quizEntryDto);
    }
    @GetMapping("/Active")
    public List<Quiz> getActiveQuizzes(){
        return quizService.getActiveQuizzes();
    }
    @GetMapping("/quizId/Result")
    public String getResult(@PathVariable int quizId){
        String ans="quiz not found";
        try{
           ans=getResult(quizId).toString();
        }
       catch(Exception e){
           System.out.println(e.getMessage());
       }
       return ans;
    }
    @GetMapping("/all")
    public List<Quiz> allQuizzes(){
       return quizService.allQuizzes();
    }

    public void Schedule(){
        quizService.updateStatus();
    }
}
