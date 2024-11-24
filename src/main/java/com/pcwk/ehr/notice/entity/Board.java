package com.pcwk.ehr.notice.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
@Table(name = "board")
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 255, nullable = false)
    private String title; // 게시물 제목

    @Lob
    @Column(nullable = false)
    private String content; // 게시물 내용

    @Column(length = 50, nullable = false)
    private String writer; // 게시물 작성자

    @Column(name = "created_date", nullable = false)
    private LocalDateTime createdDate; // 작성일

    @Column(name = "updated_date")
    private LocalDateTime updatedDate; // 수정일

    // 작성일만 있는 생성자 (편의상 생성)
    public Board(String title, String content, String writer, LocalDateTime createdDate) {
        this.title = title;
        this.content = content;
        this.writer = writer;
        this.createdDate = createdDate;
    }

    public Board() {
    }

    // Getter / Setter
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public LocalDateTime getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(LocalDateTime updatedDate) {
        this.updatedDate = updatedDate;
    }

    // LocalDateTime을 'yyyy-MM-dd HH:mm:ss' 형식으로 포맷한 날짜 반환
    public String getFormattedCreatedDate() {
        if (createdDate != null) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            return createdDate.format(formatter);  // createdDate를 'yyyy-MM-dd HH:mm:ss' 형식으로 변환하여 반환
        }
        return "";  // 날짜가 없으면 빈 문자열 반환
    }
}
