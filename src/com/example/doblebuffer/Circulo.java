package com.example.doblebuffer;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.opengles.GL10;

import android.provider.Browser.BookmarkColumns;

public class Circulo {
	private FloatBuffer bufVertices;
	private int segmentos;
	private boolean llenado;
	
	public Circulo(float radio,int seg,boolean lle) {
		// TODO Auto-generated constructor stub
		this.segmentos = seg;
		this.llenado=lle;
		
		
		ByteBuffer bufByte=ByteBuffer.allocateDirect(360*2*4);
		bufByte.order(ByteOrder.nativeOrder());
		bufVertices=bufByte.asFloatBuffer();
		
		int j=0;
		for (float i = 0; i < 360.0f; i=i+360.0f/segmentos) {
			bufVertices.put(j++,(float)Math.cos(Math.toRadians(i))*radio);
			bufVertices.put(j++,(float)Math.sin(Math.toRadians(i))*radio);
		}
		bufVertices.rewind();
	}
	public void dibuja(GL10 gl){
		/* Se habilita el acceso al arreglo de vértices */
		gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
		/* Se especifica los datos del arreglo de vértices */
		gl.glVertexPointer(2, GL10.GL_FLOAT, 0, bufVertices);
		/* Se establece el color en (r,g,b,a) */
		
		/* Renderiza las primitivas desde los datos del arreglo de vértices */
		gl.glDrawArrays((llenado) ? GL10.GL_TRIANGLE_FAN : GL10.GL_LINE_LOOP, 0, segmentos);
		/* Se deshabilita el acceso al arreglo de vértices */

		gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
	}

}
