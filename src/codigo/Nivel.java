package codigo;

import java.awt.Color;
import java.awt.Font;

import acm.graphics.GLabel;
import acm.graphics.GRect;

public class Nivel extends GRect {
	
	GLabel nivel = new GLabel ("");
	int dificultad = 1;
	
	
	public Nivel(double width, double height) {
		super(width, height);
		
		setFilled (true);
		setFillColor(Color.WHITE);
		nivel.setLabel("1");
		nivel.setFont(new Font ("Arial", Font.BOLD, 18));
		
	}
	public void dibuja (Arkanoid _arkanoid){
		_arkanoid.add (this, _arkanoid.getWidth() - 30, getY()+300);
		_arkanoid.add(nivel, _arkanoid.getWidth() - 30, getY()+30);
	}
	public void actualizaNivel(int vida){
		dificultad += vida;
		nivel.setLabel("" + dificultad);
		
	}
}
