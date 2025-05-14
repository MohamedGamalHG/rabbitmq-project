package com.example.rabbitmq_project.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class Message {
    private String status;
    private LocalDateTime localDateTime;
}
