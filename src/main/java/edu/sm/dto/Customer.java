package edu.sm.dto;

import lombok.*;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Customer {
    private int custId;
    private String username;
    private String pw;
    private String name;
    private String pNumber;
    private Date signUpDate;
}