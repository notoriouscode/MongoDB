package com.mongodb.repo;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.mongodb.documents.StockDocument;

public interface StockRepo extends MongoRepository<StockDocument, Integer>{

}
