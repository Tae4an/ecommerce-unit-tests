package edu.sm.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderDetail {
    private Integer orderDetailId;
    private Integer productId;
    private Integer orderId;
    private Integer price;
    private Integer count;

}