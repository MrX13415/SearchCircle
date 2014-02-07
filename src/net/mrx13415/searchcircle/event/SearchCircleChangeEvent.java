package net.mrx13415.searchcircle.event;

import net.mrx13415.searchcircle.swing.JSearchCircle;


public class SearchCircleChangeEvent extends SearchCircleEvent {

	private double valueBeforeChange;
	private double value;

	public SearchCircleChangeEvent(JSearchCircle searchCircle,
			double oldValue, double barValue) {
		super(searchCircle);
		this.valueBeforeChange = oldValue;
		this.value = barValue;
	}

	public double getValueBeforeChange() {
		return valueBeforeChange;
	}

	public double getValue() {
		return value;
	}
}
