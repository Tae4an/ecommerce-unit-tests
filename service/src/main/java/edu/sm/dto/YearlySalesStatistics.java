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
public class YearlySalesStatistics {
    private Integer year;
    private BigDecimal totalSales;
    private Integer orderCount;
    private BigDecimal avgOrderValue;
}