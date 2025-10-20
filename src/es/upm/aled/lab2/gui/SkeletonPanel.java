package es.upm.aled.lab2.gui;

import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;
import es.upm.aled.lab2.kinematics.*;

/**
 * Draws on a window an exoskeleton represented as a tree of Nodes.
 * 
 * @author rgarciacarmona
 */
public class SkeletonPanel extends JPanel {

	private static final long serialVersionUID = 8724301078499171545L;
	private Segment root; //segmento raíz

	/**
	 * Shows a window containing a 2D representation of an exoskeleton. Each joint
	 * in the kinematic chain is represented with a dot, and each segment with a
	 * line that connects two dots together. The end effector is also represented as
	 * a dot. It can be animated to show the exoskeleton's movement by calling the
	 * repaint() method after modifying the attributes of the kinematic chain's
	 * elements.
	 * 
	 * This class uses ForwardKinematics to compute the positions of every element
	 * in the kinematic chain.
	 * 
	 * @param root  The first Segment of the kinematic chain.
	 * @param sizeX The width of the window.
	 * @param sizeX The height of the window.
	 */
	public SkeletonPanel(Segment root, int sizeX, int sizeY) {
		this.root = root;
		// Setup Swing window
		JFrame frame = new JFrame("Recursive Skeleton - Forward Kinematics");
		frame.add(this);
		frame.setSize(600, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	} //Crea objeto de visualización:
	//CONFIGURA LA VISUALIZACIÓN (título, añade la clase para dibujarla, tamaño ventana, hace visible la ventana)
	//Si cierro ventana cierro aplicacion de visualización
	
	@Override //con la clase se puede 
	
	//Metodo fachada 
	protected void paintComponent(Graphics g) {
		// Draws the window
		super.paintComponent(g);
		float originX = getWidth() / 2;
		float originY = getHeight() / 2;

		// Computes the full tree of positions //calcula nodo inicial 
		Node nodeRoot = ForwardKinematics.computePositions(root, originX, originY);

		// Draw the tree of Nodes recursively Pinta segmentos y nodos desde el nodo inicial hasta nodos finales.  //EL NODE ROOT tiene nodos children que son los que se irán recorriendo. 
		drawSkeleton(g, originX, originY, nodeRoot);
	}

	
	//con un Skeleton Panel puedo pintar un segmento y nodo. 
	private void drawSkeleton(Graphics g, double parentX, double parentY, Node node) {
		
		
		// TODO: Ponga comentarios en este método
		//se dibuja un nodo en una posicion concreta con un ovalo. 
		g.fillOval((int) node.getX() - 4, (int) node.getY() - 4, 8, 8);
		
		//se dibuja una linea que vaya desde la art anterior a la nueva. 
		g.drawLine((int) parentX, (int) parentY, (int) node.getX(), (int) node.getY());
		
		
		// CASO BASE: Si el nodo actual no tiene hijos, significa que es un extremo. 
		if (node.getChildren().size() == 0) {
			return;
		}
		
		//PASO RECURSIVO: si el nodo actual tiene mas hijos, entonces ve dibujando para todos los node hijos.
		for (Node child : node.getChildren()) {
			drawSkeleton(g, node.getX(), node.getY(), child);
		}
	}
	
	
	
	
	//COMO FUNCIONA TODO?? 
	/**
	 * Tengo una clase que es SkeletonPanel que extiende (hereda) JPanel (entiendo que para poder dibujar). 
	 * Tiene un constructor que permite a partir de un segmento y unas dimensiones generar el dibujo.
	 * La manera de dibujar es mediante un método fachada (PaintComponent) que llama a un método recursivo(drawSkeleton)
	 * El método fachada calcula dónde está la articulación/nodo inicial y a partir de él llama al método recursivo DrawSkeleton. 
	 * El método recursivo SIEMPRE PINTA EL OVALO Y LA LINEA Y YA LUEGO SE METE EN CASO BASE O RECURSIVO
	 * (no es que para situacion base se haga una cosa y para paso recursivo otra como pasa en los ej de clase) 
	 */
}