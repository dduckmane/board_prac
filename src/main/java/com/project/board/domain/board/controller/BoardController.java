package com.project.board.domain.board.controller;

import com.project.board.domain.board.controller.init.BoardInit;
import com.project.board.domain.board.controller.request.ListParam;
import com.project.board.domain.board.domain.Address;
import com.project.board.domain.board.domain.Board;
import com.project.board.domain.board.domain.boardenum.Category;
import com.project.board.domain.board.domain.boardenum.Regions;
import com.project.board.domain.board.domain.boardenum.Tag;
import com.project.board.domain.board.domain.UploadFile;
import com.project.board.domain.board.controller.request.BoardDetailsDto;
import com.project.board.domain.board.controller.request.BoardDto;
import com.project.board.domain.board.controller.request.BoardSaveForm;
import com.project.board.domain.board.controller.request.BoardUpdateForm;
import com.project.board.domain.board.repository.BoardRepository;
import com.project.board.domain.board.search.BoardSearchCondition;
import com.project.board.domain.board.service.BoardService;
import com.project.board.domain.member.domain.Member;
import com.project.board.domain.member.service.MemberService;
import com.project.board.domain.page.PageMaker;
import com.project.board.global.config.auth.PrincipalDetails;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/user/board")
@RequiredArgsConstructor
@Log4j2
public class BoardController {
    private final BoardRepository boardRepository;
    private final BoardService boardService;
    private final QueryAdapterHandler adapterHandler;
    private final MemberService memberService;
    private final BoardInit boardInit;
    @Value("${custom.upload.path}")
    private String UPLOAD_PATH;

    @ModelAttribute("tags")
    public List<Tag> tag(){
        return boardInit.getTags();
    }
    @ModelAttribute("categories")
    public List<Category> categories(){
        return boardInit.getCategories();
    }
    @ModelAttribute("regions")
    public List<Regions> regions(){
        return boardInit.getRegions();
    }

    @GetMapping("/list")
    //제목으로 검색 추가
    public String main(
            @AuthenticationPrincipal PrincipalDetails principalDetails
            , @ModelAttribute("listParam") ListParam listParam
            , BoardSearchCondition searchCondition
            , @PageableDefault(page = 0, size = 4, sort = "id", direction = Sort.Direction.DESC) Pageable pageable
            , Model model
    ){
        Page<BoardDto> result = adapterHandler
                .service(listParam.getParam(), searchCondition, pageable)
                .map(BoardDto::new);

        memberService.collectInfo(principalDetails.getMember(),listParam,searchCondition);

        int nowPage = result.getPageable().getPageNumber() + 1;
        int startPage = Math.max(nowPage - 4, 1);
        int endPage = Math.min(nowPage + 5, result.getTotalPages());

        List<BoardDto> content = result.getContent();
        model.addAttribute("BoardDtoList",content);
        model.addAttribute("Param",listParam.getParam());
        model.addAttribute("requestParam",listParam.getRequest());
        model.addAttribute("title", listParam.getTitle(principalDetails.getMember()));


        PageMaker pageMaker = new PageMaker(
                nowPage
                , endPage
                , startPage
                , result.isFirst()
                , result.isLast()
                , result.getTotalPages());

        model.addAttribute("pageMaker",pageMaker);

        return "board/board-list";
    }
    @GetMapping("/{boardId}")
    public String board(
            @AuthenticationPrincipal PrincipalDetails principalDetails
            , @PathVariable Long boardId
            , HttpServletResponse response
            , HttpServletRequest request
            , Model model
    ){
        Optional<Board> findBoard = boardService.findOne(boardId, response, request);

        BoardDetailsDto boardDetailsDto = findBoard.map(BoardDetailsDto::new).orElseThrow();

        boolean checkMyself = boardService.checkMyself(principalDetails.getMember(), findBoard.orElseThrow());

        model.addAttribute("checkMySelf", checkMyself);
        model.addAttribute("boardDetailsDto",boardDetailsDto);

        return "board/board-detail";
    }
    @GetMapping("/save/{groupId}")
    public String saveForm(@PathVariable int groupId,Model model){
        model.addAttribute("groupId",groupId);
        return "board/board-write";}

    @PostMapping("/save/{groupId}")
    public String save(
            @AuthenticationPrincipal PrincipalDetails principalDetails
            , @Validated @ModelAttribute BoardSaveForm boardSaveForm
            , BindingResult bindingResult
            , @PathVariable int groupId
    ){
        UploadFile uploadFile = null;

        try{
            uploadFile=UploadFile.createUploadFile(boardSaveForm.getThumbNail(), UPLOAD_PATH);
        }catch (IllegalArgumentException e){
            bindingResult.addError(new FieldError("boardSaveForm","thumbNail", e.getMessage()));
        }

        if (bindingResult.hasErrors()){
            System.out.println("bindingResult = " + bindingResult);
            return "board/board-write";
        }

        Member member = principalDetails.getMember();

        List<UploadFile> uploadFiles = UploadFile.storeFiles(boardSaveForm.getAttachFiles(), UPLOAD_PATH);

        boardService.save(
                member
                , groupId
                , boardSaveForm.getTitle()
                , boardSaveForm.getContent()
                , uploadFile
                , new Address(
                        boardSaveForm.getRepresentativeArea()
                        ,boardSaveForm.getDetailArea())
                , uploadFiles
                , boardSaveForm.getPrice()
                , boardSaveForm.getTag()
        );
        return "redirect:/user/board/list?groupId={groupId}";
    }
    @GetMapping("/edit/{boardId}")
    public String editForm(@PathVariable Long boardId,Model model) {
        BoardUpdateForm BoardUpdateForm = boardRepository.findById(boardId).map(BoardUpdateForm::new).orElseGet(() -> new BoardUpdateForm());
        model.addAttribute("BoardUpdateForm",BoardUpdateForm);
        return "board/board-modify";
    }
    @PostMapping("/edit/{boardId}")
    public String edit(@AuthenticationPrincipal PrincipalDetails principalDetails,@RequestParam Long boardId,@ModelAttribute BoardUpdateForm boardUpdateForm,RedirectAttributes redirectAttributes){

        boardRepository.findMemberById(boardId).orElseThrow().checkMySelf(principalDetails.getUsername());


        boardService.update(boardId,boardUpdateForm.getContent());
        return "redirect:/user/board/{boardId}";
    }
    @GetMapping("/delete/{boardId}")
    //물어보기 외래키 제약조건??
    public String delete(@PathVariable Long boardId,RedirectAttributes redirectAttributes){
        boardService.delete(boardId);
        return "redirect:/";
    }

}
