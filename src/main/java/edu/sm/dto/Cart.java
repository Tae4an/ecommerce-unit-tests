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
public class Cart {
    private Integer cartId;
    private Integer custId;
    private Integer productId;
    private Integer count;
    private Date regDate;
}