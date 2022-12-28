package com.project.board.domain.board.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest//spring 기반의 test
@AutoConfigureMockMvc//mockMvc 를 자동으로 설정
class BoardControllerTest {

    @Autowired
    BoardController boardController;
    @Autowired
    protected MockMvc mockMvc;
    @Autowired
    protected ObjectMapper om;
//    @Test
//    void main() {
//        //given
//
//        mockMvc.perform()
//    }
}