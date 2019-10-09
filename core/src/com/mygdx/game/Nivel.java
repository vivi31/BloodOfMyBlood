package com.mygdx.game;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.Viewport;

public abstract class Nivel implements Screen {
// Variables de Screen
    private final Juego juego;
    private OrthographicCamera camara;
    private Viewport vista;
    private SpriteBatch batch;
    // Escena de menu (botones)
    private Stage escenaHUD;

// Variables propios del nivel
    private int numeroNivel; //1, 2, 3...9
    private int dificultad; // 1,2,3,4,5
    //Texturas fondo y botones.
    private Texture texturaFondo;
    private Texture texturaBarraVida;
    private Texture texturaBotonPausa;
    private Texture texturaBotonAjustes;

    //Texturas personajes
    private Texture texturaPersonaje; //ESTO ES AQUI O EN PERSNOAJEEEEE?

    public Nivel(Juego juego){
        this.juego = juego;
    }
    private void setNumeroNivel(int numeroNivel){
        this.numeroNivel= numeroNivel;
    }

    private int getNumeroNivel(){
        return this.numeroNivel;
    }

    private void setDificultad(int dificultad){
        this.dificultad = dificultad;
    }
    private int getDificultad(){
        return this.dificultad;
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
