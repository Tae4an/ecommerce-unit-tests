package edu.sm.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Address {
    private Integer addressId;
    private Integer custId;
    private String name;
    private String phone;
    private String address;
    private String addressDetail;
    private String zipCode;
    private String request;
    private String def;
}