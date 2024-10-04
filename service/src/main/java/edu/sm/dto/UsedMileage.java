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
public class UsedMileage {
    private Integer usedMileageId;
    private Integer custId;
    private Date usedDate;
    private Integer amount;
}