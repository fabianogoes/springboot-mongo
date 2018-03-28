package com.example.springbootmongo.repository;

import com.example.springbootmongo.model.Sale;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SaleRepository extends MongoRepository<Sale, String> {
}
