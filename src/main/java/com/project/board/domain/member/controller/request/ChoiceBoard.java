package com.project.board.domain.member.controller.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChoiceBoard {
    private Boolean choice;
    private Long boardId;
}
