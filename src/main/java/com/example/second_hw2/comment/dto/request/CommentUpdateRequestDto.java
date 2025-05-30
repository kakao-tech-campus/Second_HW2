package com.example.second_hw2.comment.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class CommentUpdateRequestDto {

    @NotBlank(message = "댓글 내용은 필수 입력값입니다.")
    private String content;
}
