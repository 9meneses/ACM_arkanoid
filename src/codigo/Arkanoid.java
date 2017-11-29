package codigo;

import java.awt.Color;
import acm.graphics.*;
import java.awt.event.MouseEvent;

/*
 * Autor: Jorge Cisneros
 * 
 * El Arkanoid pero orientado a objetos
 */

public class Arkanoid extends acm.program.GraphicsProgram{


	Pelota pelota1 = new Pelota(7, Color.GREEN);
	//Pelota pelota2 = new Pelota(30, Color.BLUE);
	Barra barra1 = new Barra(60, 15, Color.RED);
	int anchoLadrillo = 25;
	int altoLadrillo = 15;
	int espacioMenu = 100;



	//el sistema del marcador

	Marcador marcador = new Marcador (20, 40);
	Vidas vidas = new Vidas (20,40);//creo el rectangulo de vidas 


	public void init(){
		addMouseListeners();
		setSize(500, 600);

		add(pelota1, 0, getHeight()*0.60 - pelota1.getHeight());
		//add(pelota2, 0, getHeight()*0.70 - pelota2.getHeight());

		add(barra1, 0 , getHeight()*0.80);
		

		GRect lateral = new GRect (3, getHeight());
		lateral.setFilled(true);
		add (lateral, getWidth() - espacioMenu - lateral.getWidth() - pelota1.getWidth(), 0);


	}

	public void run(){
		marcador.dibuja(this);
		vidas.dibuja(this);
	 	
		


		dibujaNivel01();
		while (true){
			pelota1.muevete(this);
			
			

			barra1.mueveBarra((int)pelota1.getX(), getWidth() - espacioMenu);
			pause(0); //esta puesta a 2

			if(marcador.puntuacion >= 105){ //pasa al siguiente nivel
				dibujaNivel02();
				pelota1.setLocation (0, getHeight()*0.60 - pelota1.getHeight());// reinicio la pelota

				while (true){ //arranca el nivel 

					pelota1.muevete(this);

					barra1.mueveBarra((int)pelota1.getX(), getWidth()- espacioMenu);
					pause(0);

					if(marcador.puntuacion >= 160){ //pasa al siguiente nivel
						dibujaNivel03();
						pelota1.setLocation (0, getHeight()*0.60 - pelota1.getHeight());// reinicio la pelota

						while (true){ //arranca el nivel 

							pelota1.muevete(this);

							barra1.mueveBarra((int)pelota1.getX(), getWidth()- espacioMenu);
							pause(0);
						}

					}
				}

			}


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
								Color.PINK);

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
								Color.PINK);

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
								Color.PINK);

				add(miLadrillo);
				pause(7);
			}
		}
	}
}











