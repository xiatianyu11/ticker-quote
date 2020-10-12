package com.ticker.quote.vo;

import java.util.ArrayList;
import java.util.List;

public class LinkedQuote {
	
	private long headTimetamp;
	
	private List<Quote> quoteList;

	public LinkedQuote(long headTimetamp) {
		this.headTimetamp = headTimetamp;
		this.quoteList = new ArrayList<>();
	}

	public long getHeadTimetamp() {
		return headTimetamp;
	}

	public void setHeadTimetamp(long headTimetamp) {
		this.headTimetamp = headTimetamp;
	}

	public List<Quote> getQuoteList() {
		return quoteList;
	}
	
	public void reset(long headTimetamp) {
		this.headTimetamp = headTimetamp;
		this.quoteList.clear();
	}

	@Override
	public String toString() {
		return "[headTimetamp=" + headTimetamp + ", quoteList=" + quoteList + "]";
	}

}
