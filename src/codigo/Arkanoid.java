package codigo;

import java.awt.Color;
import java.awt.Font;

import acm.graphics.*;
import acm.util.RandomGenerator;

import java.awt.event.MouseEvent;

/*
 * Autor: Jorge Cisneros
 * 
 * El Arkanoid pero orientado a objetos
 */

public class Arkanoid extends acm.program.GraphicsProgram{

	RandomGenerator random = new RandomGenerator();


	Pelota pelota1 = new Pelota(7, Color.GREEN);
	PelotaBonus pelota2 = new PelotaBonus(7, Color.BLUE);
	Barra barra1 = new Barra(60, 15, Color.RED);
	int anchoLadrillo = 25;
	int altoLadrillo = 15;
	int espacioMenu = 100;

	GLabel numero = new GLabel("");//mejorar esteticamente la puntuacion indicando que ese rectangulo es la puntuacion


	//el sistema del marcador

	Marcador marcador = new Marcador (25, 40);
	Vidas vidas = new Vidas (25,40); //creo el rectangulo de vidas 

	Nivel nivel = new Nivel (20,40); //creo el rectangulo de niveles




	public void init(){
		addMouseListeners();
		setSize(500, 600);

		add(pelota1, 0, getHeight()*0.60 - pelota1.getHeight());
		
	


		add(barra1, 0 , getHeight()*0.80);



		GRect lateral = new GRect (3, getHeight());
		lateral.setFilled(true);
		add (lateral, getWidth() - espacioMenu - lateral.getWidth() - pelota1.getWidth(), 0);
		numero.setLabel("puntuacion");
		numero.setFont(new Font ("Arial", Font.ITALIC,10));//esteticamente puntuacion
		add(numero, getWidth()-100,20);	//esteticamente puntuacion


	}

	public void run(){
		marcador.dibuja(this); //puntuacion
		vidas.dibuja(this);	//vidas
		nivel.dibuja(this); //nivel marcador







		dibujaNivel01();
		while (vidas.corazones <=3 && vidas.corazones >=0){
			pelota1.muevete(this);
			

			barra1.mueveBarra((int)pelota1.getX(), getWidth() - espacioMenu);
			pause(0); //esta puesta a 2
			actualizaNivel();
			bonusPelota();







		}
	}







	//add (marcador.texto, 0,20);


	//este es el método que mueve la barra
	public void mouseMoved (MouseEvent evento){
		barra1.mueveBarra(evento.getX(), getWidth() - espacioMenu);
	}

	private void dibujaNivel01(){
		int numLadrillos = 14; 
		for (int j=0; j < numLadrillos; j++){
			for(int i=j; i < numLadrillos; i++){
				Ladrillo miLadrillo =
						new Ladrillo(anchoLadrillo*i - anchoLadrillo*j/2,
								altoLadrillo*j,
								anchoLadrillo, 
								altoLadrillo, 
								random.nextColor());

				add(miLadrillo);
				pause(7);
			}
		}
	}

	private void dibujaNivel02(){
		int numLadrillos = 10; 
		for (int j=0; j < numLadrillos; j++){
			for(int i=j; i < numLadrillos; i++){
				Ladrillo miLadrillo =
						new Ladrillo(anchoLadrillo*i + anchoLadrillo*j/2,
								altoLadrillo*j,
								anchoLadrillo, 
								altoLadrillo, 
								random.nextColor());

				add(miLadrillo);
				pause(7);
			}
		}

	}
	private void dibujaNivel03(){
		int numLadrillos = 12; 
		for (int j=0; j < numLadrillos; j++){
			for(int i=j; i < numLadrillos; i++){
				Ladrillo miLadrillo =
						new Ladrillo(anchoLadrillo*i - anchoLadrillo*j/2,
								altoLadrillo*j,
								anchoLadrillo, 
								anchoLadrillo, //consigo que se apile uno encima de otro
								random.nextColor());

				add(miLadrillo);
				pause(7);
			}
		}
	}

	private void actualizaNivel(){

		if(marcador.puntuacion >= 105){ //pasa al siguiente nivel
			dibujaNivel02();
			nivel.actualizaNivel(1); //indicador del nivel
			pelota1.setLocation (0, getHeight()*0.60 - pelota1.getHeight());// reinicio la pelota


			while (vidas.corazones <=3 && vidas.corazones >=0){ //arranca el nivel 


				pelota1.muevete(this);

				//barra1.mueveBarra((int)pelota1.getX(), getWidth()- espacioMenu);
				pause(2);

				
					if (marcador.puntuacion >= 120){
						add(pelota2, 0, getHeight()*0.20 - pelota2.getHeight()); 
						while (vidas.corazones <=3 && vidas.corazones >=0){
							pelota1.muevete(this);
							pelota2.mueveteBonus(this);
							pause(2);

					if(marcador.puntuacion >= 160){ //pasa al siguiente nivel
						dibujaNivel03();
						nivel.actualizaNivel(1);	//indicador del nivel
						pelota1.setLocation (0, getHeight()*0.60 - pelota1.getHeight());// reinicio la pelota

						while (vidas.corazones <=3 && vidas.corazones >=0){ //arranca el nivel 

							pelota1.muevete(this);
							pelota2.mueveteBonus(this);

							barra1.mueveBarra((int)pelota1.getX(), getWidth()- espacioMenu);
							pause(2);
						}

					}
				}

			}

		}
	}
	}
	private void bonusPelota(){
		if(marcador.puntuacion >= 120){
			dibujaPelotaBonus();
			while (true){
				pelota2.mueveteBonus(this);
			}


		}

	}
	private void dibujaPelotaBonus(){
		
		add(pelota2, 0, getHeight()*0.70 - pelota2.getHeight()); 
		pause (7);
	}
}











