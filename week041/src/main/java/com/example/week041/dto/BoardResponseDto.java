package com.example.week041.dto;

import com.example.week041.domain.Board;
import lombok.Getter;

import java.time.LocalDateTime;

;

public class BoardResponseDto {
    @Getter
    public static class BoardInfoDto {
        private Long id;
        private String title;
        private String username;
        private String content;
        private LocalDateTime createdAt;
        private LocalDateTime modifiedAt;

        public BoardInfoDto(Board board){
            this.id = board.getId();
            this.title = board.getTitle();
            this.username = board.getUsername();
            this.content = board.getContent();
            this.createdAt = board.getCreatedAt();
            this.modifiedAt = board.getModifiedAt();
        }
    }
}
