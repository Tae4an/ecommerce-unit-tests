package edu.sm.dto;

import lombok.*;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class Category {
    private int categoryId;
    private int categoryId2;
    private String name;
}
