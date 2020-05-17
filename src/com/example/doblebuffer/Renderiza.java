package com.example.doblebuffer;

import java.util.Calendar;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;




import android.R.bool;
import android.content.Context;
import android.opengl.GLSurfaceView;
import android.opengl.GLSurfaceView.Renderer;
import android.opengl.GLU;
import android.view.MotionEvent;

public class Renderiza extends GLSurfaceView implements Renderer {
	/* Objeto */
	private int sw=1;
	private int sw2=1;
	
	//
	private float ancho=0.1f;
	private float add=0.1f;
	private boolean rsw=true;
	//
	private Rectangulo rectangulo;
	/* Angulo de la animación */
	private float angulo, incAngulo;
	/* Actividad Principal */
	private final MainActivity mActivity;
	/* Variables del tiempo de ejecución */
	private long inicio, fin, duracion;
	private float tiempo_real;
	private float tiempoRotacion;
	private final float PERIODO_DE_LA_ROTACION = 1; // en segundos
	private int hora, minutos, segundos;
	private Calendar calendario;

	public Renderiza(Context contexto, MainActivity activity) {
		super(contexto);
		/* Actividad Principal */
		this.mActivity = activity;
		/* Inicia el renderizado */
		this.setRenderer(this);
		/* La ventana solicita recibir una entrada */
		this.requestFocus();
		/* Establece que la ventana pueda detectar el modo táctil. */
		this.setFocusableInTouchMode(true);
		/* El renderizado se llama continuamente para renderizar la escena. */
		this.setRenderMode(GLSurfaceView.RENDERMODE_CONTINUOUSLY);
	}

	@Override
	public void onSurfaceCreated(GL10 gl, EGLConfig arg1) {
		rectangulo = new Rectangulo();
		/* Inicializa las variables */
		angulo = 0;
		incAngulo = 2;
		inicio = System.currentTimeMillis();
		tiempoRotacion = PERIODO_DE_LA_ROTACION;
		gl.glClearColor(1, 0, 1, 0);
	}

	@Override
	public void onDrawFrame(GL10 gl) {
		gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		/* Actualiza el texto */
		calendario = Calendar.getInstance();
		hora = calendario.get(Calendar.HOUR_OF_DAY);
		minutos = calendario.get(Calendar.MINUTE);
		segundos = calendario.get(Calendar.SECOND);
		mActivity.actualizaTexto(hora, minutos, segundos);
		/* Renderiza el rectángulo */
		gl.glPushMatrix();
		gl.glRotatef(angulo, 0, 0, 1);
		rectangulo.dibuja(gl);
		gl.glPopMatrix();
		
		
		
		_circulo((float) 1, 360, true, 1, 0, 0, 0, 0, 0, gl,sw);
		sw++;
	
		gl.glLoadIdentity();
		
		triangulofan2 f=new triangulofan2(new float []{
				-2,-6,
				2,-6,
				//2,-3,
				-2,-3
		});
		gl.glColor4f(1, 1, 0, 0);
		gl.glScalef(ancho, 1, 0);
		f.dibuja(gl);
		ancho=ancho+add;
		if(ancho>=2)
			add=add*-1;
		if(ancho<=0)
			add=add*-1;
		gl.glLoadIdentity();
		
		/* Obtiene el tiempo real */
		fin = System.currentTimeMillis();
		duracion = fin - inicio;
		tiempo_real = duracion / 1000f;
		inicio = fin;
		/* Incrementa y verifica el límite del tiempo */
		tiempoRotacion = tiempoRotacion - tiempo_real;
		if (tiempoRotacion < 0.001) {
			tiempoRotacion = PERIODO_DE_LA_ROTACION;
			angulo = angulo + incAngulo;
			if (angulo > 360)
				angulo = angulo - 360;
		}
		
		
	}
	private void _circulo(float radio, int i, boolean b, float col1,
			float col2, float col3, float col4, float x, float y, GL10 gl, int sw) {
		// TODO Auto-generated method stub
		Circulo circu = new Circulo((float) radio, i, b);
			gl.glColor4f(col1, col2, col3, 0);
			gl.glLoadIdentity();
			if (sw%100==0) {
				sw2=sw2*-1;
			}
			if(sw2>0){
				gl.glTranslatef(x, y, 0);
			}else{
				gl.glTranslatef(x+2, y+2, 0);
			}
			circu.dibuja(gl);
			
		

	}

	@Override
	public void onSurfaceChanged(GL10 gl, int w, int h) {
		gl.glViewport(0, 0, w, h);
		gl.glMatrixMode(GL10.GL_PROJECTION);
		gl.glLoadIdentity();
		GLU.gluOrtho2D(gl, -4, 4, -6, 6);
		gl.glMatrixMode(GL10.GL_MODELVIEW);
		gl.glLoadIdentity();
	}

	@Override
	public boolean onTouchEvent(MotionEvent e) {
		if (e.getAction() == MotionEvent.ACTION_UP) {
			/* Cambia el incremento del ángulo a 2 o 0 */
			incAngulo = incAngulo == 0 ? 2 : 0;
			requestRender(); // Llama por defecto
		}
		return true;
	}
}