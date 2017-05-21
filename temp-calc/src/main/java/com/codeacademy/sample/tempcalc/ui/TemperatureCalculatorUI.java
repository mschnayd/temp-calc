package com.codeacademy.sample.tempcalc.ui;

import com.codeacademy.sample.tempcalc.model.ITemperatureCalculatorModel;
import com.codeacademy.sample.tempcalc.model.TemperatureCalculatorModel;
import com.codeacademy.sample.tempcalc.ui.ITemperatureCalculatorView.FieldChangeEvent;

/**
 * Implementation of 
 * 
 * @author mshnayderman
 */
public class TemperatureCalculatorUI {

	private ITemperatureCalculatorView  view;
	private ITemperatureCalculatorModel model;
	
	public TemperatureCalculatorUI() {
		super();
		model = new TemperatureCalculatorModel();
	}

	public void createAndShow() {		
		this.view = TemperatureCalculatorView.createAndShow();
		this.view.addEventListener(fce -> {
			onFieldChange(fce);
		});
	}

	private void onFieldChange(FieldChangeEvent fce) {
		switch (fce.getChangedField()) {
			case FAHRENHEIT:
				model.setFahrenheitText(view.getFahrenheitText());
				break;
			case CELCIUS:
				model.setCelciusText(view.getCelciusText());
				break;
		};
		updateViewFromModel();
	}
	
	private void updateViewFromModel() {
		view.performWithNoEventFiring(() -> {
			view.setCelciusText(model.getCelciusText());
			view.setFahrenheitText(model.getFahrenheitText());
			if (model.hasError()) {
				view.showError(model.getErrorMessage());
			} else {
				view.hideError();
			}
		});
	}
		
}
