package com.taotu.hv.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
public class NewsDTO {
    private Long id;

    @NotBlank(message = "新闻标题不能为空")
    private String title;

    @NotBlank(message = "新闻分类不能为空")
    private String category;

    private String summary;

    @NotBlank(message = "新闻内容不能为空")
    private String content;

    private String imageUrl;

    @NotNull(message = "发布日期不能为空")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate publishDate;
}