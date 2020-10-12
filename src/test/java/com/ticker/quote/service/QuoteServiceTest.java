package com.ticker.quote.service;

import java.util.Map;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import com.ticker.quote.service.impl.QuoteServiceImpl;
import com.ticker.quote.vo.Quote;


import mockit.Tested;

public class QuoteServiceTest {

	@Tested
	private QuoteServiceImpl quoteServiceImpl;

	@Tested
	private Quote quote;

	@Test
	public void doSaveTest()throws Exception {
		quoteServiceImpl.save(quote);
		Assert.assertNotNull(quote.getTimestamp());
	}
	
	@Test
	public void dogGtSymbolCountTest()throws Exception {
		quote.setSymbol("SR1");
		quoteServiceImpl.save(quote);
		Map<String, Integer> result = quoteServiceImpl.getSymbolCount();
		Assert.assertEquals(new Integer(1), result.get("SR1"));
	}

}
