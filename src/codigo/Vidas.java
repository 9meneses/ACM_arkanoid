package codigo;

import java.awt.Color;
import java.awt.Font;

import acm.graphics.GLabel;
import acm.graphics.GRect;

public class Vidas extends GRect {
	GLabel vidas = new GLabel ("");
	int corazones = 3;
	
	public Vidas(double width, double height) {
		super(width, height);
		
		setFilled (true);
		setFillColor(Color.WHITE);
		vidas.setLabel("3");
		vidas.setFont(new Font ("Arial", Font.BOLD, 18));
		
		
	}
	public void dibuja (Arkanoid _arkanoid){
		_arkanoid.add (this, _arkanoid.getWidth() - 30, getY()+90);
		_arkanoid.add(vidas, _arkanoid.getWidth() - 30, getY()+30);
	}
	
	public void actualizaVida (int vida){
		corazones -= vida;
		vidas.setLabel("" + corazones);
		
	}
}
