package codigo;
/*
 * autor Jorge Cisneros

 * La clase pelota es la que vamos a utilizar para
 * el juego del arkanoid
 * Tiene dos constructores
 */
import java.awt.Color;


import acm.graphics.GObject;
import acm.graphics.GOval;

public class PelotaBonus extends GOval{

	double xVelocidad = 1; //velocidad de la bola en el eje X
	double yVelocidad = 1; //velocidad de la bola en el eje Y


	/**
	 * Este es el constructor básico, que es idéntico
	 * al de la clase GOval
	 * @param _ancho
	 * @param _alto
	 */
	public PelotaBonus(double _ancho, double _alto){
		super(_ancho, _alto);
	}

	/**
	 * Este es el constructor dabuti que permite
	 * pasar el color como parámetro 
	 * 
	 * @param _ancho indica el ancho y el alto de la bola
	 * @param _color
	 */
	public PelotaBonus(double _ancho, Color _color){
		super(_ancho, _ancho); //sabemos que una pelota es igual de ancho que de alto por eso no ponemos double _alto como esta reflejado arriba
		if (_ancho <=0){
			setSize(1, 1);//sirve para declarar el tamaño no obstante ya le declaramos el tamaño en arkanoid
		}
		setFillColor(_color);
		setFilled(true);
	}
	/**
	 * se encarga de mover a la pelota y chequear si ha habido colisiones
	 * 
	 */
	public void mueveteBonus(Arkanoid _arkanoid){ //para diferenciarlo de la pelota buena, es decir, la pelota que no es bonus
		//chequea si ha chocado con las paredes izq o derecha
		if (getX() + getWidth() >= _arkanoid.getWidth() - _arkanoid.espacioMenu
				|| getX()<0){
			xVelocidad *= -1; 
		}
		//chequea si ha chocado con el techo
		if (getY()<0){
			yVelocidad *= -1; 
		}
		
		
		//declaro las vidas que cuando choque con la pared de abajo, quite una vida y vuelvas a tener 
		//otra pelota en juego si te quedan vidas al perder la pelota al chochar con la pared de abajo
	
		
		
		
		if(chequeaColision(getX(), getY(), _arkanoid)){ 									//chequeo la esquina superior izquierda
			if(chequeaColision(getX()+getWidth(), getY(), _arkanoid)){ 						//chequeo la esquina superior derecha
				if(chequeaColision(getX(), getY()+getHeight(), _arkanoid)){ 				//chequeo la esquina inferior izquierda
					if(chequeaColision(getX()+getWidth(), getY()+getHeight(), _arkanoid)){ 	//chequeo la esquina inferior derecha


					}
				}
			}
		}
		


		//voy a crear un metodo chequeacolision generico
		//que sirva para comprobar los choques entre la bola y los ladrillos
		//y la bola y el cursor

		
		move(xVelocidad, yVelocidad);
		
	}

	

	private boolean chequeaColision(double posX, double posY, Arkanoid _arkanoid){
		boolean noHaChocado = true;
		GObject auxiliar;
		auxiliar = _arkanoid.getElementAt(posX, posY);

		if (auxiliar instanceof Ladrillo){
			//if (auxiliar.getY() >= posY && auxiliar.getY() + auxiliar.getHeight() <= posY){
			//}

			if (auxiliar.getY() < posY){
				yVelocidad *= -1;
			}
			if (auxiliar.getY() + auxiliar.getHeight() > posY){
				yVelocidad *= -1;
			}
			if (auxiliar.getX() < posX){
				xVelocidad *= -1;
			}
			if (auxiliar.getX() + auxiliar.getWidth() > posX){
				xVelocidad *= -1;
			}
			
			

			
				

			

			

			//else if(auxiliar.getX() <= posX && auxiliar.getX() + auxiliar.getWidth() >= posX){
			//  *= -1;
			//}
			_arkanoid.remove(auxiliar);
			_arkanoid.marcador.actualizaMarcador(1);
			noHaChocado = false;
		}
		else if (auxiliar instanceof Barra){
			
			
		
			//vamos a modificar el rebote de la bola con el cursor
			//para que no sea siempre igual


			//calculo la posción x del punto central 
			double centroBola = getX() + getWidth()/2;
			if (centroBola > auxiliar.getX() + auxiliar.getWidth()/3 //izquierda
					&& centroBola < auxiliar.getX() + 2* auxiliar.getWidth()/3) { //derecha 
				yVelocidad *= -1;
			}
			else{
				yVelocidad = -0.5; 
				noHaChocado = false;


			}




		}
		return noHaChocado;
		


	}





}











