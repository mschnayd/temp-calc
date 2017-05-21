package com.codeacademy.sample.tempcalc.model;

public interface ITemperatureCalculatorModel {

	void setCelciusText(String text);

	void setFahrenheitText(String text);

	String getCelciusText();

	String getFahrenheitText();
	
	String getErrorMessage();

	boolean hasError();

}