package edu.sm.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerAnalysis {
    private Integer analysisId;
    private Integer custId;
    private Integer totalOrders;
    private BigDecimal totalSpent;
    private Integer favoriteCategoryId;
}