package nl.han.ica.SpaceInvaders2018;

import nl.han.ica.OOPDProcessingEngineHAN.Objects.GameObject;

public abstract class TextObject extends GameObject {
	
	protected String text;
	protected int textSize;
	
	public TextObject (String text, int textSize) {
		this.text = text;
		this.textSize = textSize;
	}
	
	public void setText(String text) {
		this.text = text;
	}
	
	public String getText() {
		return text;
	}

}
