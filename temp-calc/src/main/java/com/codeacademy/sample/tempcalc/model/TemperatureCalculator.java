package com.codeacademy.sample.tempcalc.model;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

public class TemperatureCalculator {

	private static final BigDecimal THIRTY_TWO = BigDecimal.valueOf(32);
	private static final BigDecimal NINE_FIFTH = new BigDecimal("1.8");

	public BigDecimal toFahrenheit(BigDecimal celcius) {
		return twoDecimals(celcius != null ? celcius.multiply(NINE_FIFTH, MathContext.DECIMAL64).add(THIRTY_TWO) : null);
	}

	public BigDecimal toCelcius(BigDecimal fahrenheit) {
		return twoDecimals(fahrenheit != null ? fahrenheit.subtract(THIRTY_TWO).divide(NINE_FIFTH, MathContext.DECIMAL64) : null);
	}
	
	private BigDecimal twoDecimals(BigDecimal bd) {
		return bd != null ? bd.setScale(2, RoundingMode.HALF_UP) : null;
	}
	
}
