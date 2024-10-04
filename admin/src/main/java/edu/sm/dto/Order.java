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
public class Order {
    private Integer orderId;
    private Integer custId;
    private Integer productCount;
    private Integer price;
    private Date orderDate;
    private String name;
    private String phone;
    private String address1;
    private String address2;
    private String zipCode;
    private String request;
    private String card;
    private Integer usedMileage;
}
