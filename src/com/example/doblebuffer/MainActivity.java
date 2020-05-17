package com.example.doblebuffer;

import android.app.Activity;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends Activity {
	private TextView texto1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		RelativeLayout pantalla = (RelativeLayout) getLayoutInflater().inflate(
				R.layout.activity_main, null);
		GLSurfaceView superficie = new Renderiza(this, this);
		pantalla.addView(superficie);
		/* Adiciona un texto a la ventana */
		texto1 = (TextView) pantalla.findViewById(R.id.texto1);
		texto1.bringToFront();
		setContentView(pantalla);
	}

	public void actualizaTexto(final int hora, final int minutos,
			final int segundos) {
		runOnUiThread(new Runnable() {
			@Override
			public void run() {
				texto1.setText(String.format("%02d:%02d:%02d", hora, minutos,
						segundos));
			}
		});
	}
}