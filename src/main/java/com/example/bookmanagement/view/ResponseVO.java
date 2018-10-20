package com.example.bookmanagement.view;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseVO<T> {

    private int status;

    private String message;

    private T data;


}
