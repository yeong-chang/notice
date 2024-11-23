package com.pcwk.ehr.notice.controller;

import com.pcwk.ehr.notice.entity.Board;
import com.pcwk.ehr.notice.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller; // @RestController 대신 @Controller 사용
import org.springframework.ui.Model; // Model을 사용하여 데이터를 뷰로 전달
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Controller // REST API가 아닌 웹 페이지 컨트롤러로 지정
@RequestMapping("/boards") // 웹 페이지의 URL 매핑
public class BoardController {
    private final BoardService boardService;

    @Autowired
    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }


    @RequestMapping("/as")  // /boards/as 경로로 GET 요청 처리
    public String showBoards(Model model) {
        // 예시로 LocalDateTime을 생성
        LocalDateTime createdDate = LocalDateTime.now();

        // DateTimeFormatter를 사용하여 날짜 포맷
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDate = createdDate.format(formatter);

        // 포맷된 날짜를 모델에 추가
        model.addAttribute("formattedDate", formattedDate);

        return "showBoards";
    }

    // 게시물 조회 (웹 페이지로 반환)
    @GetMapping("/{id}")
    public String getBoardById(@PathVariable Long id, Model model) {
        Board board = boardService.getBoardById(id);  // 서비스에서 특정 게시물 가져옴
        model.addAttribute("board", board);  // "board" 이름으로 뷰에 전달
        return "boardDetail"; // boardDetail.html 페이지로 이동
    }

    // 게시물 생성 (웹 페이지에서 게시물 추가)
    @PostMapping
    public String createBoard(@ModelAttribute Board board) {
        boardService.saveBoard(board);  // 서비스에서 게시물 저장
        return "redirect:/boards";  // 게시물 저장 후 게시판 목록 페이지로 리다이렉트
    }

    // 게시물 삭제
    @DeleteMapping("/{id}")
    public void deleteBoard(@PathVariable Long id) {
        boardService.deleteBoard(id);  // 서비스에서 게시물 삭제
    }
}
