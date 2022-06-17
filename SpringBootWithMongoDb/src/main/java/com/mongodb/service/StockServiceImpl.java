package com.mongodb.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mongodb.documents.StockDocument;
import com.mongodb.repo.StockRepo;

@Service
public class StockServiceImpl implements StockService {

	@Autowired
	StockRepo stockRepo;

	@Override
	public List<StockDocument> getAllStocks() {
		return stockRepo.findAll();
	}

	@Override
	public StockDocument getStockById(int stockId) {
		Optional<StockDocument> opStockEntity = stockRepo.findById(stockId);
		if(opStockEntity.isPresent()) {
			StockDocument stockEntity = opStockEntity.get();
			return stockEntity;
		}
		return null;
	}

	@Override
	public StockDocument createNewStock(StockDocument stock, String authToken) {
		StockDocument stockEntity = new StockDocument(stock.getId(),stock.getName(), stock.getMarketName(), stock.getPrice());
		stockEntity = stockRepo.save(stockEntity);
		return stockEntity;
	}

	@Override
	public boolean deleteStockById(int stockId) {
		if(stockRepo.existsById(stockId)) {
			stockRepo.deleteById(stockId);
			return true;
		}
		return false;
	}

	@Override
	public StockDocument updateStockById(int stockId, StockDocument stock) {
		Optional<StockDocument> opStockEntity = stockRepo.findById(stockId);
		if(opStockEntity.isPresent()) {
			StockDocument stockEntity = opStockEntity.get();
			stockEntity.setName(stock.getName());
			stockEntity.setMarketName(stock.getMarketName());
			stockEntity.setPrice(stock.getPrice());
			stockEntity = stockRepo.save(stockEntity);
			stock.setId(stockId);
			return stock;
		}
		return stock;
	}

}
