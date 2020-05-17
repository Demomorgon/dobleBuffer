package com.example.doblebuffer;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import javax.microedition.khronos.opengles.GL10;

public class Rectangulo {
	/**
	 * 3 ---------- 2 | / | | / | | / | | / | 0 ---------- 1
	 */
	/* Las coordenadas cartesianas (x, y) */
	private float vertices[] = new float[] { 
			-2, -2, // 0
			2, -2, // 1
			2, 2, // 2
			-2, 2 // 3
	};
	FloatBuffer bufVertices;

	public Rectangulo() {
		/* Lee los vértices */
		ByteBuffer bufByte = ByteBuffer.allocateDirect(vertices.length * 4);
		bufByte.order(ByteOrder.nativeOrder());
		bufVertices = bufByte.asFloatBuffer();
		bufVertices.put(vertices).rewind();
	}

	public void dibuja(GL10 gl) {
		/* Se habilita el acceso al arreglo de vértices */
		gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
		/* Se especifica los datos del arreglo de vértices */
		gl.glVertexPointer(2, GL10.GL_FLOAT, 0, bufVertices);
		/* Se establece el color en (r,g,b,a) */
		gl.glColor4f(0, 1, 0, 0);
		/* Dibuja el rectángulo */
		gl.glDrawArrays(GL10.GL_TRIANGLE_FAN, 0, 4);
		/* Se deshabilita el acceso al arreglo de vértices */
		gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
	}
}