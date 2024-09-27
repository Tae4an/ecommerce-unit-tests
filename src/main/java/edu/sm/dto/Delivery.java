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
public class Delivery {
    private int deliveryId;
    private int orderId;
    private String status;
    private String trackingNumber;
    private Date  estimatedDelivery;
    private Date actualDelivery;
}

