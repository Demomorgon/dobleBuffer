package com.example.doblebuffer;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import javax.microedition.khronos.opengles.GL10;
public class triangulofan2 {
	/**
	* 2
	* /\
	* / \
	* / \
	* / \
	* /________\
	* 0 1
	*/
	/* Coordenadas cartesianas (x, y) */
	private float vertices[];
	FloatBuffer bufVertices;
	public triangulofan2(float ver[]) {
	/* Lee los vértices */
		vertices=ver;
	ByteBuffer bufByte = ByteBuffer.allocateDirect(vertices.length * 4);
	bufByte.order(ByteOrder.nativeOrder()); // Utiliza el orden del byte nativo
	bufVertices = bufByte.asFloatBuffer(); // Convierte de byte a float
	bufVertices.put(vertices);
	bufVertices.rewind(); // puntero al principio del buffer
	}
	public void dibuja(GL10 gl) {
	/* Se habilita el acceso al arreglo de vértices */
	gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
	/* Se especifica los datos del arreglo de vértices */
	gl.glVertexPointer(2, GL10.GL_FLOAT, 0, bufVertices);
	/* Se establece el color en (r,g,b,a) */
	//gl.glColor4f(1, 1, 1, 0);
	/* Dibuja el triángulo */
	gl.glDrawArrays(GL10.GL_TRIANGLE_FAN, 0, vertices.length/2);
	/* Se deshabilita el acceso al arreglo de vértices */
	gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
	}

}
