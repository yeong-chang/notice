package com.pcwk.ehr.notice.service;

import com.pcwk.ehr.notice.entity.Board;
import com.pcwk.ehr.notice.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class BoardService {

    private final BoardRepository boardRepository;

    @Autowired
    public BoardService(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    // 게시물 저장
    public Board saveBoard(Board board) {
        board.setCreatedDate(LocalDateTime.now());
        return boardRepository.save(board);
    }

    // 모든 게시물 조회
    public List<Board> getAllBoards() {
        return boardRepository.findAll();  // 데이터베이스에서 모든 게시물 가져오기
    }

    // 게시물 ID로 조회
    public Board getBoardById(Long id) {
        return boardRepository.findById(id).orElse(null);  // ID로 게시물 조회
    }

    // 게시물 삭제
    public void deleteBoard(Long id) {
        boardRepository.deleteById(id);  // 게시물 삭제
    }
}
