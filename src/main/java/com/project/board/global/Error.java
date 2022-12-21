package com.project.board.global;

import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class Error {

    private int code;
    private String message;

}
