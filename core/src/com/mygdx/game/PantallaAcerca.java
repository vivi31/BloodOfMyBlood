package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class PantallaAcerca implements Screen {
    private final Juego juego;

    private OrthographicCamera camara;
    private Viewport vista;
    private SpriteBatch batch;

    //fondo
    private Texture texturaFondo;

    //escena de menu (botones)
    private Stage escenaHUD;

    public PantallaAcerca (Juego juego) {

        this.juego = juego;   //this de pantalla inicio consrtuctor

    }
    @Override
    public void show() { //mostrar en pantalla fisica. ini items, texturas.
        configurarVista();
        cargarTexturas();
        crearHUD();
    }

    private void configurarVista() {
        camara = new OrthographicCamera();
        camara.position.set(Juego.ANCHO/2,Juego.ALTO/2,0);
        camara.update();

        vista = new StretchViewport(Juego.ANCHO, Juego.ALTO, camara);

        batch = new SpriteBatch(); //administra los trazos.
    }

    private void cargarTexturas() {texturaFondo = new Texture( "Fondos/FondoMenu.jpeg");
    }

    private void crearHUD() {
        escenaHUD = new Stage(vista);
        TextureRegionDrawable trdBack = new TextureRegionDrawable(new TextureRegion(new Texture("back.png")));
        TextureRegionDrawable trdBackPressed = new TextureRegionDrawable(new TextureRegion(new Texture("backPressed.png")));


        final ImageButton btnBack = new ImageButton(trdBack,trdBackPressed);
        btnBack.setPosition(0, Juego.ALTO - btnBack.getHeight());

        //Evento de boton.
        btnBack.addListener(new ClickListener(){
                                @Override
                                public void clicked(InputEvent event, float x, float y) {
                                    super.clicked(event, x, y);
                                    //INSTRUCCIONE
                                    juego.setScreen(new PantallaSeleccion(juego));
                                }
                            }
        );


        escenaHUD.addActor(btnBack);
        Gdx.input.setInputProcessor(escenaHUD);

    }
    @Override
    public void render(float delta) {
        borrarPantalla();
        //batch escalaTodo de acuerdo a la visat y la camara
        batch.setProjectionMatrix(camara.combined);

        batch.begin();
        batch.draw(texturaFondo, 0, 0);
        batch.end();
        escenaHUD.draw();
    }

    private void borrarPantalla() {
        Gdx.gl.glClearColor(1,1,1,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    }

    @Override
    public void resize(int width, int height) {
        vista.update(width,height);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {
        dispose();
    }

    @Override
    public void dispose() {
        texturaFondo.dispose(); //liberar
    }
}
