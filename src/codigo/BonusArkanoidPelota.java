package codigo;

import java.awt.Color;


import acm.graphics.GRect;

/**
 * 
 * @author Roberto Meneses
 *
 * La clase Ladrillo sirve para dibujar los ladrillos
 * del juego, no obstante esta es la clase que dicho ladrillo dibujado tiene un Bonus en este caso la PelotaBonus
 */
public class BonusArkanoidPelota extends GRect{

	

	/**
	 * construye un ladrillo
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 * @param _color
	 * 
	 * futuros cambios: que admita número de golpes
	 */
	public BonusArkanoidPelota(double width, double height, Color _color) {
		super(width, height);
		setFilled(true);
		setFillColor(_color);
	}	

}

