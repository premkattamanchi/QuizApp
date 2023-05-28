package com.App.QuizApp.Service;

import com.App.QuizApp.DTO.QuizEntryDto;
import com.App.QuizApp.Enums.Status;
import com.App.QuizApp.Models.Quiz;
import com.App.QuizApp.Repository.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class QuizService {
    @Autowired
    QuizRepository quizRepository;
    @Scheduled(cron="*/01 * * * * *")
    public void updateStatus(){
       List<Quiz> quizzes=quizRepository.findAll();
       for(Quiz quiz:quizzes){
           LocalDateTime start=quiz.getStartDate();
           LocalDateTime end=quiz.getEndDate();
           LocalDateTime cur=LocalDateTime.now();
           if (cur.isAfter(start) && cur.isBefore(end)) {
               quiz.setStatus(Status.ACTIVE);
           } else if(cur.isAfter(end)) {
               quiz.setStatus(Status.FINISHED);
           }
           else{
               quiz.setStatus((Status.INACTIVE));
           }
       }
    }
    public String create(QuizEntryDto quizEntryDto){
        Quiz quiz= Quiz.builder().question(quizEntryDto.getQuestion())
                .options(quizEntryDto.getOptions())
                .rightAnswer(quizEntryDto.getRightAnswer())
                .startDate(quizEntryDto.getStartDate())
                .endDate(quizEntryDto.getEndDate()).build();
            quizRepository.save(quiz);
            return "quiz created successfully";
    }
    public List<Quiz> getActiveQuizzes(){
        List<Quiz> ActiveQuizzes=new ArrayList<>();
        List<Quiz> quizzes=quizRepository.findAll();
        for(Quiz quiz:quizzes){
            if(quiz.getStatus().equals(Status.ACTIVE))
                ActiveQuizzes.add(quiz);
        }
        return ActiveQuizzes;
    }
    public char getResult(int quizId){
        Quiz quiz=quizRepository.findById(quizId).get();
        int index=quiz.getRightAnswer();
        return quiz.getOptions()[index];
    }
    public List<Quiz> allQuizzes(){
        return quizRepository.findAll();
    }


}
