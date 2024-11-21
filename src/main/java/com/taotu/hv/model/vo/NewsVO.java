package com.taotu.hv.model.vo;

import lombok.Data;
import java.time.LocalDate;

@Data
public class NewsVO {
    private Long id;
    private String title;
    private String category;
    private String summary;
    private String imageUrl;
    private LocalDate publishDate;
    private String link;
}