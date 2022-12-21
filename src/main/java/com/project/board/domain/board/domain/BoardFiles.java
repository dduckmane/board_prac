package com.project.board.domain.board.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BoardFiles {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "boardFiles_id", nullable = false)
    private Long id;
    @Embedded
    private UploadFile attachFiles;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    public Board board;
    public BoardFiles(UploadFile attachFiles) {
        this.attachFiles = attachFiles;
    }

    public void addBoard(Board board) {
        this.board = board;
    }
}
