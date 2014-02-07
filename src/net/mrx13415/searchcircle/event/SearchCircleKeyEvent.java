package net.mrx13415.searchcircle.event;

import java.awt.event.KeyEvent;

import net.mrx13415.searchcircle.swing.JSearchCircle;


public class SearchCircleKeyEvent extends SearchCircleEvent {

	private KeyEvent keyEvent;

	public SearchCircleKeyEvent(JSearchCircle searchCircle, KeyEvent keyEvent) {
		super(searchCircle);
		this.keyEvent = keyEvent;
	}

	public KeyEvent getKeyEvent() {
		return keyEvent;
	}

}
