package com.mongodb.service;

import java.util.List;

import com.mongodb.documents.StockDocument;

public interface StockService {
	List<StockDocument> getAllStocks();
	StockDocument getStockById(int stockId);
	StockDocument createNewStock(StockDocument stock, String authToken);
	boolean deleteStockById(int stockId);
	StockDocument updateStockById(int stockId, StockDocument stock);
}
