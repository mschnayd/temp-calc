package com.codeacademy.sample.tempcalc.model;

import java.math.BigDecimal;
import java.util.function.Consumer;

public class TemperatureCalculatorModel implements ITemperatureCalculatorModel {
	
	private String celciusText;
	private String fahrenheitText;
	private String errorMessage;
	private TemperatureCalculator calculator = new TemperatureCalculator();
	
	/* (non-Javadoc)
	 * @see com.codeacademy.sample.tempcalc.ITemperatureCalculatorModel#getCelciusText()
	 */
	@Override
	public String getCelciusText() {
		return celciusText;
	}
			
	public void setCelciusText(String celciusText) {
		this.celciusText = celciusText;
		convertToBigDecimal(
			this.celciusText, 
			bd -> {
				BigDecimal fahrenheit = calculator.toFahrenheit(bd);
				this.fahrenheitText = fahrenheit != null ? fahrenheit.toPlainString() : "";
				this.errorMessage = null;				
			}, 
			nfe -> {				
				this.errorMessage = "Invalid celcius value: " + this.celciusText;				
			});
	}
	
	/* (non-Javadoc)
	 * @see com.codeacademy.sample.tempcalc.ITemperatureCalculatorModel#getFahrenheitText()
	 */
	@Override
	public String getFahrenheitText() {
		return fahrenheitText;
	}

	public void setFahrenheitText(String fahrenheightText) {
		this.fahrenheitText = fahrenheightText;
		convertToBigDecimal(
			this.fahrenheitText, 
			bd -> {
				BigDecimal celcius = calculator.toCelcius(bd);
				this.celciusText = celcius != null ? celcius.toPlainString() : "";
				this.errorMessage = null;				
			}, 
			nfe -> {				
				this.errorMessage = "Invalid farenheit value: " + this.fahrenheitText;				
			});
	}
	
	/* (non-Javadoc)
	 * @see com.codeacademy.sample.tempcalc.ITemperatureCalculatorModel#getErrorMessage()
	 */
	@Override
	public String getErrorMessage() {
		return errorMessage;
	}
	
	/* (non-Javadoc)
	 * @see com.codeacademy.sample.tempcalc.ITemperatureCalculatorModel#hasError()
	 */
	@Override
	public boolean hasError() {
		return errorMessage != null;
	}

	private void convertToBigDecimal(String text, Consumer<BigDecimal> convertedValueConsumer, Consumer<NumberFormatException> exceptionConsumer) {
		if (text == null || text.trim().isEmpty()) {
			convertedValueConsumer.accept(null);
		}
		try {
			convertedValueConsumer.accept(new BigDecimal(text));
		} catch (NumberFormatException nfe) {
			exceptionConsumer.accept(nfe);
		}
	}
	
}
