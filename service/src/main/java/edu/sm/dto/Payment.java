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
public class Payment {
    private Integer paymentId;
    private Integer orderId;
    private Integer price;
    private String method;
    private Date payDate;
}