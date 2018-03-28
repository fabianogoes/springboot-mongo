package com.example.springbootmongo.dto;

import lombok.*;

@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class TopSaleDTO {
    private String description;
    private Integer count;
    private Double sum;
}
