package net.mrx13415.searchcircle.event;

public interface SearchCircleListener {

	public void onButtonChange(SearchCircleChangeEvent event);

	public void onBarChange(SearchCircleChangeEvent event);

	public void onKeyHold(SearchCircleKeyEvent event);

	public void onKeyPressed(SearchCircleKeyEvent event);

	public void onKeyReleased(SearchCircleKeyEvent event);

	public void onKeyTyped(SearchCircleKeyEvent event);

	public void onMouseDragged(SearchCircleMouseEvent event);

	public void onMouseClicked(SearchCircleMouseEvent event);

	public void onMouseEntered(SearchCircleMouseEvent event);

	public void onMouseExited(SearchCircleMouseEvent event);

	public void onMousePressed(SearchCircleMouseEvent event);

	public void onMouseReleased(SearchCircleMouseEvent event);

	public void onMouseMoved(SearchCircleMouseEvent event);

}
