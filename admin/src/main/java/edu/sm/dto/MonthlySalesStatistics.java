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
public class MonthlySalesStatistics {
    private String month;
    private BigDecimal totalSales;
    private Integer orderCount;
    private BigDecimal avgOrderValue;
}