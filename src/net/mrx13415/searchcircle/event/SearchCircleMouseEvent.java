package net.mrx13415.searchcircle.event;

import java.awt.event.MouseEvent;

import net.mrx13415.searchcircle.swing.JSearchCircle;


public class SearchCircleMouseEvent extends SearchCircleEvent {

	private MouseEvent mouseEvent;

	public SearchCircleMouseEvent(JSearchCircle searchCircle,
			MouseEvent mouseEvent) {
		super(searchCircle);
		this.mouseEvent = mouseEvent;
	}

	public MouseEvent getMouseEvent() {
		return mouseEvent;
	}
}
