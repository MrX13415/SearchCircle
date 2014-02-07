package net.mrx13415.searchcircle.event;

import net.mrx13415.searchcircle.swing.JSearchCircle;

public class SearchCircleEvent {

	private JSearchCircle searchCircle;

	public SearchCircleEvent(JSearchCircle searchCircle) {
		this.searchCircle = searchCircle;
	}

	public JSearchCircle getSearchCircle() {
		return searchCircle;
	}
}
