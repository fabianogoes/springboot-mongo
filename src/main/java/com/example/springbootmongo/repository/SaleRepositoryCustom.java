package com.example.springbootmongo.repository;

import com.example.springbootmongo.dto.TopSaleDTO;

import java.util.List;

public interface SaleRepositoryCustom {
    List<TopSaleDTO> topSale();
}
