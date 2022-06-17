package com.mongodb.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.mongodb.documents.StockDocument;
import com.mongodb.service.StockService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/mymarketplace") 
public class StockController {

	@Autowired
	StockService stockService;
	
	static List<StockDocument> stocks = new ArrayList<>();
	static int lastStockId=0;

	// create new resource(Stock) with authcentaction 
	@PostMapping(value = "/stock", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<StockDocument> createNewStock(@RequestBody StockDocument stock, @RequestHeader("auth-token") String authToken) { 
		StockDocument newStock = stockService.createNewStock(stock, authToken);
		return new ResponseEntity<StockDocument>(newStock, HttpStatus.OK);
	}
	 
	@GetMapping(value = "/stock", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<StockDocument> getAllStocks() {
		return stockService.getAllStocks();
	}
	
	//get stock by id by path variable
	//http://localhost:9000/mymarketplace/stock/1
	@GetMapping(value = "/stock/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public StockDocument getStockByIdBy(@PathVariable("id") int stockId) {
		return stockService.getStockById(stockId);

	}
	
	
	@DeleteMapping(value="/stock/{id}")
	public ResponseEntity<Boolean> deleteStockById(@PathVariable("id") int stockId) {
		return new ResponseEntity<Boolean>(stockService.deleteStockById(stockId), HttpStatus.OK);
	}

	@PutMapping(value="/stock/{id}", consumes=MediaType.APPLICATION_JSON_VALUE, 
			produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<StockDocument> updateStockById(@PathVariable("id") int stockId, @RequestBody StockDocument stock) {
		return new ResponseEntity<StockDocument>(stockService.updateStockById(stockId, stock), HttpStatus.OK);
	}
	
	//get stock by id by requestparam
	//http://localhost:9000/mymarketplace/stock?id=1
//	@GetMapping(value = "/stock", produces = MediaType.APPLICATION_JSON_VALUE)
//	public Stock getStockById(@RequestParam(name="id") int stockId) {
//		for(Stock stock:stocks)
//		{
//			if(stock.getId()==stockId) {
//				return stock;
//			}
//		}
//		return null;
//	}
}
