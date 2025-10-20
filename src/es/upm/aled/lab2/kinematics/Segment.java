package es.upm.aled.lab2.kinematics;

import java.util.ArrayList;
import java.util.List;

import es.upm.aled.lab2.gui.Node;

// TODO: Implemente la clase

// COMO SE DOCUMENTA 2.2 
/**
 * Clase que representa un segmento de esqueleto. 
 * Tiene como atributos característicos: longitud (length); angulo (angle); y una lista de segmentos que se llama children. 
 * 
 * @author Alicia P Muñoz Gallego
 */
public class Segment {
	//ATB BIEN
	private double length;
	private double angle;
	private List<Segment> children;
	
	

	//CONSTRUCTOR BIEN
	
	/**
	 * Crea un nuevo segmento. 
	 * 
	 * @param length. Longitud de segmento
	 * @param angle. Ángulo entre segmentos (rad). 
	 */
	
	public Segment (double length, double angle) {
		this.length= length;
		this.angle=angle;
		this.children = new ArrayList<>();
	}
	
	/**
	 * obtener longitud
	 * 
	 * @return length (double)
	 */
	
	//GETTERS BIEN 
	
	public double getLength() {
		return length;
	}
	
	/**
	 * obtener ángulo
	 * 
	 * @return angle (double)
	 */
	public double getAngle() { 
		return angle;
	}
	
	
	/**
	 * obtener ángulo
	 * 
	 * @param nuevoangle (double)
	 */
	//SETTER BIEN ( No hace falta cosas raras)
	public void setAngle(double nuevoangle) {
		this.angle = nuevoangle;
	}   
	

	/**
	 * obtener lista de segmentos
	 * 
	 * @return children (list<Segment>) 
	 */
	public List<Segment> getChildren() { 
		return children;
	}
	
	/**
	 * añadir un segment child  
	 * 
	 * @param child (Segment) 
	 */
	//ADD BIEN 
	public void addChild(Segment child) {
		if (!children.contains(child))
			children.add(child);
	}


	// TODO BIEN PUEDO SEGUIR 
	
}
