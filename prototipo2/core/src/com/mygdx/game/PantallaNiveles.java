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

class PantallaNiveles implements Screen {
    private final Juego juego;
    private final String sexo;
    private OrthographicCamera camara;
    private Viewport vista;

    private SpriteBatch batch;


    //fondo
    private Texture texturaFondo;

    //escena de menu (botones)
    private Stage escenaHUD;

    //personaje
    private Personaje personaje;
    private Movimiento estadoPersonaje = Movimiento.QUIETO;
    private Texture texturaPersonaje;

    public PantallaNiveles(Juego juego, String sexo) {
        this.juego = juego;
        this.sexo = sexo;
    }

    @Override
    public void show() { //mostrar en pantalla fisica. ini items, texturas.
        configurarVista();
        cargarTexturas();
        crearHUD();
        crearPersonaje();

    }

    private void crearPersonaje() {
        if(sexo == "hombre"){
            Texture texturaPersonaje = new Texture("kirito.png");
            personaje = new Personaje(texturaPersonaje, juego.ANCHO/2 , 40);
        }else{
            Texture texturaPersonaje = new Texture("asuna.png");
            personaje = new Personaje(texturaPersonaje, juego.ANCHO/2 , 40);
        }

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

        //botones para mover
        TextureRegionDrawable trdDerecha = new TextureRegionDrawable(new TextureRegion(new Texture("flechaDerecha.png")));
        TextureRegionDrawable trdIzquierda  = new TextureRegionDrawable(new TextureRegion(new Texture("flechaIzquierda.png")));
        ImageButton  btnDerecha = new ImageButton(trdDerecha);
        ImageButton  btnIzquierda = new ImageButton(trdIzquierda);
        btnDerecha.setPosition(juego.ANCHO-btnDerecha.getWidth() - 80 ,0);
        btnIzquierda.setPosition( 10 + btnDerecha.getWidth(),0);

        //Listeners
        btnDerecha.addListener(new ClickListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button){
                estadoPersonaje = Movimiento.DERECHA;
                return true;
            }
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button){
                estadoPersonaje = Movimiento.QUIETO;
            }


        });

        btnIzquierda.addListener(new ClickListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button){
                estadoPersonaje = Movimiento.IZQUIERDA;
                return true;
            }
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button){
                estadoPersonaje = Movimiento.QUIETO;
            }


        });



        escenaHUD.addActor(btnDerecha);
        escenaHUD.addActor(btnIzquierda);

        Gdx.input.setInputProcessor(escenaHUD);

    }

    private void cargarTexturas() {
        texturaFondo = new Texture( "fondo2.jpg");
    }

    private void configurarVista() {
        camara = new OrthographicCamera();
        camara.position.set(Juego.ANCHO/2,Juego.ALTO/2,0);
        camara.update();

        vista = new StretchViewport(Juego.ANCHO, Juego.ALTO, camara);

        batch = new SpriteBatch(); //administra los trazos.
    }


    @Override
    public void render(float delta) {
        //ACTUALIZAR NAVE
        actualizarPersonaje();

        borrarPantalla();
        //batch escalaTodo de acuerdo a la visat y la camara
        batch.setProjectionMatrix(camara.combined);

        batch.begin();
        batch.draw(texturaFondo, 0, 0);
        personaje.render(batch);
        batch.end();
        escenaHUD.draw();
    }

    private void actualizarPersonaje() {
        switch(estadoPersonaje){
            case DERECHA:
                personaje.mover(10);
                break;
            case IZQUIERDA:
                personaje.mover(-10);
                break;
        }
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

    private enum Movimiento {
        QUIETO,
        DERECHA,
        IZQUIERDA
    }
}
