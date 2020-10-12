package com.ticker.quote.service;

import java.util.Map;

import com.ticker.quote.vo.Quote;

public interface QuoteService {
	
	Quote save(Quote quote);
	
	Map<String, Integer> getSymbolCount();
}
