package com.project.board.domain.board.domain;

import lombok.*;

import javax.persistence.Embeddable;

@Data
@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Address {
    private String representativeArea;
    private String detailArea;
}
