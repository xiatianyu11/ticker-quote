package com.ticker.quote.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ticker.quote.service.QuoteService;
import com.ticker.quote.vo.Quote;

@RestController
@RequestMapping("/quote")
public class QuoteController {
	
	@Autowired
	private QuoteService quoteService;
	
	@PostMapping("/save")
	public Quote add(@RequestBody Quote quote) {
		return quoteService.save(quote);
	}
	
	@GetMapping("/symbol/count")
	public Map<String, Integer> list() {
		return quoteService.getSymbolCount();
	}

}
