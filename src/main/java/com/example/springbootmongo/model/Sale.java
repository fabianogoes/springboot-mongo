package com.example.springbootmongo.model;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "sales")
public class Sale {

    @Id
    private String id;
    @CreatedDate
    private LocalDateTime createDate;
    private String description;
//    private Double total;
    private BigDecimal total;

}
