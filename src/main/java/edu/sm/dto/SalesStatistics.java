package edu.sm.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SalesStatistics {
    private Integer statId;
    private Date date;
    private BigDecimal totalSales;
    private Integer orderCount;
    private BigDecimal avgOrderValue;
}