package codigo;

import java.awt.Color;
import java.awt.Font;

import acm.graphics.*;
import acm.util.RandomGenerator;

import java.awt.event.MouseEvent;

/*
 * Autor: Roberto Meneses
 * 
 * El Arkanoid pero orientado a objetos
 */

public class Arkanoid extends acm.program.GraphicsProgram{

	RandomGenerator random = new RandomGenerator(); 


	Pelota pelota1 = new Pelota(7, Color.GREEN);
	PelotaBonus pelota2 = new PelotaBonus(7, Color.BLUE); //pelota que se genera como bonus


	BonusArkanoidPelota ladrilloBonus = new BonusArkanoidPelota (25,15,random.nextColor()); //ladrillo que cuando se rompa se cree la pelotaBonus

	BarraBonus barraBonus = new BarraBonus (25,15,Color.BLACK);

	int anchoLadrillo = 25;
	int altoLadrillo = 15;
	int espacioMenu = 100;
	int anchoBarra = 60;
	int altoBarra = 15;
	Barra barra1 = new Barra(anchoBarra, altoBarra, Color.RED); //acuerdate que si los valores que pones a un objeto estan declarados debajo, va dar error


	GLabel numero = new GLabel("");//mejorar esteticamente la puntuacion indicando que ese rectangulo es la puntuacion
	GLabel oportunidades = new GLabel(""); //mejorar esteticamente el indicador de vidas diciendo que ese rectangulo son las vidas
	GLabel dificultad = new GLabel(""); //mejorar esticamente el indicador de niveles diciendo que ese rectangulo indica los niveles

	
	Marcador marcador = new Marcador (25, 40); //creo el rectangulo de puntución
	Vidas vidas = new Vidas (25,40); //creo el rectangulo de vidas 

	Nivel nivel = new Nivel (20,40); //creo el rectangulo de niveles


	public void init(){
		addMouseListeners();
		setSize(500, 600);

		add(pelota1, 0, getHeight()*0.60 - pelota1.getHeight());

		add(barra1, 0 , getHeight()*0.80);

		//creo una linea estando a la derecha los datos del juego como las vidas,puntuacion y el nivel
		GRect lateral = new GRect (3, getHeight());
		lateral.setFilled(true);
		add (lateral, getWidth() - espacioMenu - lateral.getWidth() - pelota1.getWidth(), 0); 

		//indicador que es la puntuación
		numero.setLabel("puntuacion");
		numero.setFont(new Font ("Arial", Font.ITALIC,10));
		add(numero, getWidth()-100,20);	

		//indicador que son las vidas
		oportunidades.setLabel("vida"); 
		oportunidades.setFont(new Font ("Arial", Font.ITALIC,10)); 
		add(oportunidades, getWidth()-80,110);	

		//indicador que es el nivel
		dificultad.setLabel("nivel"); 
		dificultad.setFont(new Font ("Arial", Font.ITALIC,10)); 
		add(dificultad, getWidth()-80,320);	


	}

	public void run(){
		marcador.dibuja(this); //dibujo el rectángulo de la puntuacion, en el que dentro me dira mi puntuación en el juego
		vidas.dibuja(this);	//dibujo el rectángulo de las vidas, en el que dentro me dira las vidas que tengo en el juego
		nivel.dibuja(this); //dibujo el rectángulo de los niveles, en el que dentro me dira el nivel que estoy jugando

		//empiezo con el nivel01
		dibujaNivel01();
		//condicion que sirve para poder jugar un nivel, indicando que si tengo menos de 0 vidas no puedo jugar
		while (vidas.corazones <=3 && vidas.corazones >=0){
			pelota1.muevete(this);

			//barra1.mueveBarra((int)pelota1.getX(), getWidth() - espacioMenu); //hack
			pause(2); //esta puesta a 2
			actualizaNivel();

		}
	}


	//este es el método que mueve la barra, en función de donde desplaza el ratón sobre el ejeX
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
								Color.PINK);

				//if (random.nextInt(numLadrillos)==8){
				//add (barraBonus,anchoLadrillo*i + anchoLadrillo*j/2,
				//altoLadrillo*j); //consigo que se apile uno encima de otro

				//}
				add(miLadrillo);
				pause(7);

			}
		}
		//add (barraBonus);

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
				if (random.nextInt(numLadrillos)==8){ //ladrillo que indica que si se rompe dicho ladrillo se crea la pelotaBonus
					add (ladrilloBonus,anchoLadrillo*i - anchoLadrillo*j/2,
							altoLadrillo*j); 
				}

				add(miLadrillo);
				pause(7);
			}
		}
		add (ladrilloBonus);

	}

	private void actualizaNivel(){

		if(marcador.puntuacion >= 105){ //pasa al siguiente nivel
			dibujaNivel02();
			nivel.actualizaNivel(1); //indicador del nivel
			pelota1.setLocation (0, getHeight()*0.60 - pelota1.getHeight());// reinicio la pelota a su punto original para empezar el nivel

			while (vidas.corazones <=3 && vidas.corazones >=0){ //arranca el nivel 

				pelota1.muevete(this);

				//barra1.mueveBarra((int)pelota1.getX(), getWidth()- espacioMenu);
				pause(2);

				if(marcador.puntuacion >= 160){ //pasa al siguiente nivel
					dibujaNivel03();
					nivel.actualizaNivel(1);	//indicador del nivel
					pelota1.setLocation (0, getHeight()*0.60 - pelota1.getHeight()); //reinicio la pelota a su punto original para empezar el nivel
					while (vidas.corazones <=3 && vidas.corazones >=0){ //arranca el nivel 

						pelota1.muevete(this);
						pelota2.mueveteBonus(this);

						//barra1.mueveBarra((int)pelota1.getX(), getWidth()- espacioMenu);
						pause(2);
					}

				}
			}

		}

	}


	public void dibujaPelotaBonus(){ //condicion para añadir la pelota Bonus cuando rompa el ladrillo

		add(pelota2, 0, getHeight()*0.20 - pelota2.getHeight()); 
		pause (7);
	}
}











