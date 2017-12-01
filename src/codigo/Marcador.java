package codigo;
import java.awt.Color;


import java.awt.Font;

import acm.graphics.*;

/**
 * 
 * @author Roberto Meneses
 * Esta clase sirve para añadir el marcador, es decir la puntuación
 * en un marco
 *
 */
public class Marcador extends GRect {

	
	GLabel texto = new GLabel("");
	int puntuacion = 0;
	
	

	
	public Marcador(double width, double height) {
		super(width, height);
		setFilled (true);
		setFillColor(Color.WHITE);
		texto.setLabel("0");
		texto.setFont(new Font ("Arial", Font.BOLD, 16));
		
		
		
		
		
	}
	
	public void dibuja (Arkanoid _arkanoid){
		 _arkanoid.add (this, _arkanoid.getWidth() - 30, getY());
		_arkanoid.add(texto, _arkanoid.getWidth() - 30, getY()+30);
		
		
		
		
		
	} 
	
	public void actualizaMarcador (int puntos){
		puntuacion += puntos;
		texto.setLabel("" + puntuacion);
		
	}
	
	

	
}
