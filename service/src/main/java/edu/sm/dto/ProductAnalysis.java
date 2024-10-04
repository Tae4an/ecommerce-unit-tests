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
public class ProductAnalysis {
    private Integer analysisId;
    private Integer productId;
    private Integer salesCount;
    private BigDecimal totalRevenue;
    private BigDecimal avgRating;

}