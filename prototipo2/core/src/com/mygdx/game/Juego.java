package com.mygdx.game;
import com.badlogic.gdx.Game;


public class Juego extends Game {
	public static final float ANCHO= 1250;
	public static final float ALTO= 720;


	@Override
	public void create() {
		setScreen(new PantallaMenu(this)); //referencia del administracion  para pasar de pantalla a otra

	}
}
