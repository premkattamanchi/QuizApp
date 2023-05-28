package com.App.QuizApp.DTO;

import lombok.Data;

import java.time.LocalDateTime;
@Data
public class QuizEntryDto {
    private String question;
    private char[] options;
    private int rightAnswer;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
}
