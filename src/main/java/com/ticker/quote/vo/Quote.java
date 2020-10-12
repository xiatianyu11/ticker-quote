package com.ticker.quote.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;

public class Quote implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2680804464231108255L;

	private Date timestamp;
	
	private String symbol;
	
	private String sharesTraded;
	
	private BigDecimal priceTraded;
	
	private String changeDirection;
	
	private BigDecimal changeAmount;

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public String getSharesTraded() {
		return sharesTraded;
	}

	public void setSharesTraded(String sharesTraded) {
		this.sharesTraded = sharesTraded;
	}

	public BigDecimal getPriceTraded() {
		return priceTraded;
	}

	public void setPriceTraded(BigDecimal priceTraded) {
		this.priceTraded = priceTraded;
	}

	public String getChangeDirection() {
		return changeDirection;
	}

	public void setChangeDirection(String changeDirection) {
		this.changeDirection = changeDirection;
	}

	public BigDecimal getChangeAmount() {
		return changeAmount;
	}

	public void setChangeAmount(BigDecimal changeAmount) {
		this.changeAmount = changeAmount;
	}
	
	

}
