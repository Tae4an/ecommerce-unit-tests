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
public class Board {
    private Integer boardId;
    private Integer custId;
    private Integer productId;
    private String ntype;
    private String title;
    private Date regDate;
    private String content;
    private String img;
    private Integer rate;
}