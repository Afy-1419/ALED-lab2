package es.upm.aled.lab2.kinematics;

import java.util.ArrayList;
import java.util.List;

import es.upm.aled.lab2.gui.Node;

/**
 * This class implements a forward kinematics algorithm using recursion. It
 * expects a tree of Segments (defined by its length and angle with respect to
 * the previous Segment in the tree) and returns a tree of Nodes (defined by
 * their absolute coordinates in a 2-dimensional space).
 * 
 * @author rgarciacarmona
 */
public class ForwardKinematics {

	/**
	 * Returns a tree of Nodes to be used by SkeletonPanel to draw the position of
	 * an exoskeleton. This method is the public facade to a recursive method that
	 * builds the result from a tree of Segments defined by their angle and length,
	 * and the relationship between them (which Segment is children of which).
	 * 
	 * @param root    The root of the tree of Segments.
	 * @param originX The X coordinate for the origin point of the tree.
	 * @param originY The Y coordinate for the origin point of the tree.
	 * @return The tree of Nodes that represent the exoskeleton position in absolute
	 *         coordinates.
	 */
	// Public method: returns the root of the position tree
	public static Node computePositions(Segment root, double originX, double originY) {
		long startTime = System.nanoTime(); //tiempo de empezar el método.
		
		//saca las posiciones a partir de la raíz las coordenads de origen y con acumulación inicial de 0. 
		Node nodoraiz= computePositions(root, originX, originY, 0); //esto nos genera un nodo
		
		//DESPUES DEL CODIGO JUSTO ANTES DEL RETURN
		long runningTime= System.nanoTime()-startTime; //tiempo en runnear= tiempo de ahora (nanoTime) - tiempo en el que empezó
		System.out.println("Tiempo de computePositions para un segmento con" + root.getChildren().size() + "hijos" + runningTime + "nanosegundos");
		// TODO: Implemente este método
		
		return nodoraiz; //hay que returnear el nodo
	}

	// Private helper method that implements the recursive algorithm
	private static Node computePositions(Segment link, double baseX, double baseY, double accumulatedAngle) {
		// TODO: Implemente este método
		
		//por lo general siempre creo un x e y que sea igual que el parámetro metido 
		double x=baseX; 
		double y=baseY;
		Node nodo=new Node(x,y);  //a partir de el creo un nodo por si hay que devolverlo (si es la ult articulacion)
	
		
		//CASO BASE. si no tiene segmentos hijos el segmento padre devuelve el nodo con las coordenadas de x e y del param metido. 
		if(link.getChildren().size()==0) { return nodo;
			//significa que ya no hay otra extremidad asi que no hay articulacion que las una.
			//Habrá que devolver el último nodo. 
		}
		
		
		//CASO RECURSIVO. Si tiene segmentos hijos, entonces actualiza bien x e y. Y el nodo igual, se debe actualizar. 
		x = baseX + link.getLength() * Math.cos(accumulatedAngle + Math.toRadians(link.getAngle()));
		y = baseY + link.getLength() * Math.sin(accumulatedAngle + Math.toRadians(link.getAngle()));
		accumulatedAngle= (accumulatedAngle + Math.toRadians(link.getAngle()));
		nodo= new Node(x,y); 
		
		//paso al siguiente segmento recorriendo la lista 
		//for para cambiar al siguiente segmento 
		for (Segment child : link.getChildren()) {
			Node nodonuevo=computePositions(child, x, y, accumulatedAngle); //nodo hijo 
			nodo.addChild(nodonuevo); //al nodo padre añado a su lista su nodo hijo. 
			}
		
		return nodo;
		}
}


		
		
	
