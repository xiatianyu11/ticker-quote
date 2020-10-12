package com.ticker.quote.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collector;

import org.springframework.stereotype.Service;

import com.ticker.quote.constans.QuoteConstants;
import com.ticker.quote.service.QuoteService;
import com.ticker.quote.util.StoreManager;
import com.ticker.quote.vo.LinkedQuote;
import com.ticker.quote.vo.Quote;

@Service
public class QuoteServiceImpl implements QuoteService {

	@Override
	public Quote save(Quote quote) {
		//store into database
		return StoreManager.getInstance().add(quote);
	}

	@Override
	public Map<String, Integer> getSymbolCount() {
		 Map<Long, LinkedQuote> quotesMap = StoreManager.getInstance().getMap();
		 Map<String, Integer> map = new HashMap<>();  
		 long beforeTime = this.getStartTime();
		 List<Quote> allOfQuotes = new ArrayList<>();
		 quotesMap.values().stream().map(o -> o.getQuoteList()).forEach(o -> allOfQuotes.addAll(o));
		 
		 allOfQuotes.stream().filter(o -> o.getTimestamp().getTime() > beforeTime)
		 					.forEach(o -> {
		 						if(map.containsKey(o.getSymbol())) {
		 							int temp = map.get(o.getSymbol());  
		 							System.out.println("-----------------temp--" + temp);
		 			                map.put(o.getSymbol(), temp + 1);  
		 						}else {
		 							map.put(o.getSymbol(), 1);
		 						}
		 					});
		 return map.entrySet().stream().sorted((o1, o2) -> o1.getValue() > o2.getValue() ? -1 : 1).collect(MappingFives.collector());
	}
	
	
	private static final class MappingFives {

	    private Map<String, Integer> map = new HashMap<>();

	    public void accept(Entry<String, Integer> entry) {
	        if(map.size() < 5) {
	        	
	        	map.put(entry.getKey(), entry.getValue());
	        }
	    }

	    public MappingFives combine(MappingFives other) {
	        throw new UnsupportedOperationException("Stream not supported");
	    }

	    public Map<String, Integer> finish() {
	        return map;
	    }

	    public static Collector<Entry<String, Integer>, ?, Map<String, Integer>> collector() {
	        return Collector.of(MappingFives::new, MappingFives::accept, MappingFives::combine, MappingFives::finish);
	    }

	}
	
	private long getStartTime() {
		long beforeTime = System.currentTimeMillis() - QuoteConstants.LAST_MIN_TIME * QuoteConstants.UNIT;
		 return beforeTime;
	}

}
