package com.App.QuizApp.Models;

import com.App.QuizApp.Enums.Status;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "quiz")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Quiz {
    @Id
    private int id;
    private String question;
    private char[] options;
    private int rightAnswer;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Status status;
}
