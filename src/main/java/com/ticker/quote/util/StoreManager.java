package com.ticker.quote.util;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.ticker.quote.constans.QuoteConstants;
import com.ticker.quote.vo.LinkedQuote;
import com.ticker.quote.vo.Quote;

/**
 * create a HashMap with 11 elements
 * store quotes with 11 mins
 * @author xiatianyu
 *
 */
public class StoreManager {
	
	private Map<Long, LinkedQuote> map = new HashMap<>();
	private long startTime = System.currentTimeMillis();
	
	public static StoreManager instance = new StoreManager();
	
	public static StoreManager getInstance() {
		return instance;
	}
	
	private StoreManager() {
		for(long i=0 ; i<=QuoteConstants.LAST_MIN_TIME; i++) {
			map.put(i, new LinkedQuote(startTime + i * QuoteConstants.UNIT));
		}
		map.get(QuoteConstants.LAST_MIN_TIME).setHeadTimetamp(startTime);
	}
	
	private boolean isNewStart() {
		return duration > QuoteConstants.LAST_MIN_TIME * QuoteConstants.UNIT;
	}
	
	private boolean canRestStartTime() {
		return duration > (QuoteConstants.LAST_MIN_TIME + 1) * QuoteConstants.UNIT;
	}
	
	private long duration;
	
	private void doGetDuration() {
		long currentTime = System.currentTimeMillis();
		duration = currentTime - startTime;
	}
	
	private void doResetStartTime() {
		if(canRestStartTime()) {
			this.startTime += (this.duration/(QuoteConstants.LAST_MIN_TIME * QuoteConstants.UNIT) ) * (QuoteConstants.LAST_MIN_TIME * QuoteConstants.UNIT);
		}
	}
	
	private void doClearList() {
		if(duration / (QuoteConstants.LAST_MIN_TIME * QuoteConstants.UNIT) > 1) {
			for(long i = 0; i <= QuoteConstants.LAST_MIN_TIME; i++) {
				map.get(i).reset(this.startTime + i * QuoteConstants.UNIT);
			}
			map.get(QuoteConstants.LAST_MIN_TIME).setHeadTimetamp(startTime);
		}
	}
	
	private void add(long key, LinkedQuote linkedQuote, Quote quote) {
		if(isNewStart() && key > 0) {
			for(int i = 1; i <= key; i++) {
				linkedQuote.reset(this.startTime + i * QuoteConstants.UNIT);
			}
			map.get(0l).getQuoteList().clear();
			map.get(0l).getQuoteList().addAll(map.get(QuoteConstants.LAST_MIN_TIME).getQuoteList());
			map.get(QuoteConstants.LAST_MIN_TIME).getQuoteList().clear();
		}
		if(key == 0 && isNewStart()) {
			map.get(QuoteConstants.LAST_MIN_TIME).getQuoteList().add(quote);
		}else {
			linkedQuote.getQuoteList().add(quote);
		}
	}
	
	public Quote add(Quote quote) {
		this.doGetDuration();
		this.doResetStartTime();
		this.doClearList();
		long mod = (this.duration/QuoteConstants.UNIT) % QuoteConstants.LAST_MIN_TIME;
		//System.out.println("mod: " + mod);
		quote.setTimestamp(new Date());
		this.add(mod, map.get(mod), quote);
		
		return quote;
	}
	
	public Map<Long, LinkedQuote> getMap() {
		return map;
	}
	
}
