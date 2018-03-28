package com.example.springbootmongo.repository.impl;

import com.example.springbootmongo.dto.TopSaleDTO;
import com.example.springbootmongo.model.Sale;
import com.example.springbootmongo.repository.SaleRepositoryCustom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.*;
import org.springframework.stereotype.Repository;

import java.util.List;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.*;

@Repository
public class SaleRepositoryImpl implements SaleRepositoryCustom {

    @Autowired
    private MongoTemplate mongo;

    @Override
    public List<TopSaleDTO> topSale() {

        GroupOperation group = group(fields()
                .and("description", "description"))
                .count().as("count")
                .sum("total").as("sum");

        SortOperation sort = sort(Sort.Direction.DESC, "count");

        ProjectionOperation project = new ProjectionOperation()
                .and("_id").as("description")
                .and("sum").as("sum")
                .and("count").as("count");

        Aggregation aggregation = newAggregation(
                group,
                project,
                sort,
                limit(5)
        );

        AggregationResults<TopSaleDTO> groupResults = mongo.aggregate(
                aggregation,
                Sale.class,
                TopSaleDTO.class
        );

        return groupResults.getMappedResults();
    }

}
