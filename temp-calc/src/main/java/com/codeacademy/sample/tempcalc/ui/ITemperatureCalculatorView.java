package com.codeacademy.sample.tempcalc.ui;

import java.util.EventListener;
import java.util.EventObject;

public interface ITemperatureCalculatorView {

	void setCelciusText(String text);

	void setFahrenheitText(String text);

	String getCelciusText();

	String getFahrenheitText();

	void showError(String message);

	void hideError();

	void addEventListener(FieldChangeListener listener);

	void removeEventListener(FieldChangeListener listener);

	void performWithNoEventFiring(Runnable runnable);
	
	public static interface FieldChangeListener extends EventListener {
		public void onChange(FieldChangeEvent fieldChangeEvent);
	}

	public static class FieldChangeEvent extends EventObject {

		private static final long serialVersionUID = 1L;

		public static enum ChangedField {
			FAHRENHEIT, CELCIUS
		};

		private final ChangedField changedField;

		FieldChangeEvent(Object source, ChangedField changedField) {
			super(source);
			this.changedField = changedField;
		}

		public ChangedField getChangedField() {
			return changedField;
		}
	}	

}