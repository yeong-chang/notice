package com.pcwk.ehr.notice.controller;

import com.pcwk.ehr.notice.entity.Board;
import com.pcwk.ehr.notice.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/boards")  // "/boards"로 URL 패턴을 통일
public class BoardController {
    private final BoardService boardService;

    // 기본 URL (/에 대한 요청을 처리)
    @GetMapping("/")
    public String showIndex() {
        return "index";  // index.html을 반환
    }

    // 기본 URL (/에 대한 요청을 처리)
    @GetMapping("/as")
    public String showMainPage() {
        return "mainPage";  // index.html을 반환
    }


    @Autowired
    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    // 게시판 목록 페이지 보여주기
    @GetMapping
    public String showBoards(Model model) {
        List<Board> boards = boardService.getAllBoards(); // DB에서 게시물 목록을 가져옵니다.
        model.addAttribute("boards", boards);  // 모델에 추가하여 Thymeleaf로 전달
        return "showBoards";  // showBoards.html로 데이터 전송
    }

    // 게시물 상세 정보를 반환하는 API
    @GetMapping("/{id}")  // URL을 "/boards/{id}"로 수정
    public ResponseEntity<Map<String, Object>> getBoard(@PathVariable Long id) {
        Board board = boardService.getBoardById(id);  // id를 Long으로 받음

        if (board != null) {
            Map<String, Object> response = new HashMap<>();
            response.put("id", board.getId());
            response.put("title", board.getTitle());
            response.put("content", board.getContent());
            response.put("writer", board.getWriter());
            response.put("formattedCreatedDate", board.getFormattedCreatedDate());
            return ResponseEntity.ok(response);
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

//    // 게시물 조회 (웹 페이지로 반환)
//    @GetMapping("/{id}")
//    public String getBoardById(@PathVariable Long id, Model model) {
//        Board board = boardService.getBoardById(id);  // 서비스에서 특정 게시물 가져옴
//        model.addAttribute("board", board);  // "board" 이름으로 뷰에 전달
//        return "boardDetail"; // boardDetail.html 페이지로 이동
//    }

    // 게시물 생성 (웹 페이지에서 게시물 추가)
    @PostMapping
    public String createBoard(@ModelAttribute Board board) {
        boardService.saveBoard(board);  // 서비스에서 게시물 저장
        // 게시물 저장 후 게시판 목록 페이지로 리다이렉트
        return "redirect:/boards";
    }

    // 게시물 삭제
    @DeleteMapping("/{id}")
    public void deleteBoard(@PathVariable Long id) {
        boardService.deleteBoard(id);  // 서비스에서 게시물 삭제
    }
}
