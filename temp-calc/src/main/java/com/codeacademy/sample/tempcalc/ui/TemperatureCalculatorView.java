package com.codeacademy.sample.tempcalc.ui;

import javax.swing.event.EventListenerList;

public class TemperatureCalculatorView extends TemperatureCalculatorViewDesign implements ITemperatureCalculatorView {

	private static final long serialVersionUID = 1L;

	private EventListenerList fieldChangeListeners = new EventListenerList();

	private boolean eventsEnabled = true;

	/**
	 * Creates new form TemperatureCalculatorView
	 */
	public TemperatureCalculatorView(java.awt.Frame parent, boolean modal) {
		super(parent, modal);
		addControlEventListeners();
	}

	/* (non-Javadoc)
	 * @see com.codeacademy.sample.tempcalc.ITemperatureCalculatorView#setCelciusText(java.lang.String)
	 */
	@Override
	public void setCelciusText(String text) {
		getCelciusTextField().setText(text);
	}

	/* (non-Javadoc)
	 * @see com.codeacademy.sample.tempcalc.ITemperatureCalculatorView#setFahrenheitText(java.lang.String)
	 */
	@Override
	public void setFahrenheitText(String text) {
		getFahrenheitTextField().setText(text);
	}

	/* (non-Javadoc)
	 * @see com.codeacademy.sample.tempcalc.ITemperatureCalculatorView#getCelciusText()
	 */
	@Override
	public String getCelciusText() {
		return getCelciusTextField().getText();
	}

	/* (non-Javadoc)
	 * @see com.codeacademy.sample.tempcalc.ITemperatureCalculatorView#getFahrenheitText()
	 */
	@Override
	public String getFahrenheitText() {
		return getFahrenheitTextField().getText();
	}

	/* (non-Javadoc)
	 * @see com.codeacademy.sample.tempcalc.ITemperatureCalculatorView#showError(java.lang.String)
	 */
	@Override
	public void showError(String message) {
		getErrorTextField().setText(message);
		getErrorTextField().setVisible(true);
		pack();
	}

	/* (non-Javadoc)
	 * @see com.codeacademy.sample.tempcalc.ITemperatureCalculatorView#hideError()
	 */
	@Override
	public void hideError() {
		getErrorTextField().setVisible(false);
		pack();
	}

	/* (non-Javadoc)
	 * @see com.codeacademy.sample.tempcalc.ITemperatureCalculatorView#addEventListener(com.codeacademy.sample.tempcalc.TemperatureCalculatorView.FieldChangeListener)
	 */
	@Override
	public void addEventListener(FieldChangeListener listener) {
		fieldChangeListeners.add(FieldChangeListener.class, listener);
	}

	/* (non-Javadoc)
	 * @see com.codeacademy.sample.tempcalc.ITemperatureCalculatorView#removeEventListener(com.codeacademy.sample.tempcalc.TemperatureCalculatorView.FieldChangeListener)
	 */
	@Override
	public void removeEventListener(FieldChangeListener listener) {
		fieldChangeListeners.remove(FieldChangeListener.class, listener);
	}

	/* (non-Javadoc)
	 * @see com.codeacademy.sample.tempcalc.ITemperatureCalculatorView#performWithNoEventFiring(java.lang.Runnable)
	 */
	@Override
	public void performWithNoEventFiring(Runnable runnable) {
		this.eventsEnabled = false;
		try {
			runnable.run();
		} finally {
			this.eventsEnabled = true;
		}
	}

	private void addControlEventListeners() {
		getCelciusTextField().addActionListener(
				(ae) -> {
					fireFieldChangeEvent(new FieldChangeEvent(this,
							FieldChangeEvent.ChangedField.CELCIUS));
				});
		getFahrenheitTextField().addActionListener(
				(ae) -> {
					fireFieldChangeEvent(new FieldChangeEvent(this,
							FieldChangeEvent.ChangedField.FAHRENHEIT));
				});
	}

	private void fireFieldChangeEvent(FieldChangeEvent changeEvent) {
		if (!eventsEnabled) {
			return;
		}
		FieldChangeListener[] listeners = fieldChangeListeners
				.getListeners(FieldChangeListener.class);
		if (listeners != null) {
			for (FieldChangeListener listener : listeners) {
				listener.onChange(changeEvent);
			}
		}
	}

	/**
	 * @param args
	 *            the command line arguments
	 */
	public static ITemperatureCalculatorView createAndShow() {
		try {
			for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager
					.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					javax.swing.UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (ClassNotFoundException ex) {
			java.util.logging.Logger.getLogger(
					TemperatureCalculatorView.class.getName()).log(
					java.util.logging.Level.SEVERE, null, ex);
		} catch (InstantiationException ex) {
			java.util.logging.Logger.getLogger(
					TemperatureCalculatorView.class.getName()).log(
					java.util.logging.Level.SEVERE, null, ex);
		} catch (IllegalAccessException ex) {
			java.util.logging.Logger.getLogger(
					TemperatureCalculatorView.class.getName()).log(
					java.util.logging.Level.SEVERE, null, ex);
		} catch (javax.swing.UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(
					TemperatureCalculatorView.class.getName()).log(
					java.util.logging.Level.SEVERE, null, ex);
		}

		TemperatureCalculatorView view = new TemperatureCalculatorView(
				new javax.swing.JFrame(), true);

		java.awt.EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {

				view.addWindowListener(new java.awt.event.WindowAdapter() {
					@Override
					public void windowClosing(java.awt.event.WindowEvent e) {
						System.exit(0);
					}
				});

				view.hideError();
				view.setVisible(true);

			}
		});

		return view;
	}

}
