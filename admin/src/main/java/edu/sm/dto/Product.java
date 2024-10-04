package edu.sm.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Product {
    private Integer productId;
    private Integer categoryId;
    private String name;
    private Integer price;
    private Date regDate;
    private String description;
    private String img1;
    private String img2;
    private String img3;
    private String img4;
    private String img5;
    private Integer count;
    private boolean isPublic;
}