package com.project.board.global.testInit;

import com.project.board.global.helper.BoardHelper;
import com.project.board.global.helper.MemberHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
public class TestInit {
    @Autowired
    public MemberHelper memberHelper;
    @Autowired
    public BoardHelper boardHelper;

}
