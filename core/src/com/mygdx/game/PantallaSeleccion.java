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

class PantallaSeleccion implements Screen {
    private final Juego juego;
    private OrthographicCamera camara;
    private Viewport vista;
    private SpriteBatch batch;

    //fondo
    private Texture texturaFondo;

    //escena de menu (botones)
    private Stage escenaHUD;

    //Personaje.
    private Personaje personajeHombre;
    private Personaje personajeMujer;

    //acerca de
    private PantallaAcerca acerca;

    //Texturas
    private Texture textoSeleccionar;


    public PantallaSeleccion (Juego juego) {
        this.juego = juego;   //this de pantalla inicio consrtuctor
    }


    @Override
    public void show() { //mostrar en pantalla fisica. ini items, texturas.
        configurarVista();
        cargarTexturas();
        crearHUD();

    }

  /*  private void crearPersonaje() {
        Texture texturaPersonaje = new Texture("kiritoGrande.png");
        personajeHombre = new Personaje(texturaPersonaje, juego.ANCHO - 500 , 100);
        Texture texturaPersonajeMujer = new Texture("asunaGrande.png");
        personajeMujer = new Personaje(texturaPersonajeMujer,juego.ANCHO -1000,100);
    }*/


    private void crearHUD() {
        escenaHUD = new Stage(vista);
       // TextureRegionDrawable trdBack = new TextureRegionDrawable(new TextureRegion(new Texture("back.png")));
        //TextureRegionDrawable trdBackPressed = new TextureRegionDrawable(new TextureRegion(new Texture("backPressed.png")));


       // final ImageButton btnBack = new ImageButton(trdBack,trdBackPressed);
       // btnBack.setPosition(0, Juego.ALTO - btnBack.getHeight());

        //Evento de boton.
        /*btnBack.addListener(new ClickListener(){
                                @Override
                                public void clicked(InputEvent event, float x, float y) {
                                    super.clicked(event, x, y);
                                    //INSTRUCCIONE
                                    juego.setScreen(new PantallaMenu(juego));
                                }
                            }
        );*/


       // escenaHUD.addActor(btnBack);

        //botones para mover
        TextureRegionDrawable trdHombre = new TextureRegionDrawable(new TextureRegion(new Texture("Personajes/TexturaHombre.png")));
        TextureRegionDrawable trdMujer  = new TextureRegionDrawable(new TextureRegion(new Texture("Personajes/TxturaMujer.png")));

        //boton de info
        TextureRegionDrawable trdInfo = new TextureRegionDrawable(new TextureRegion(new Texture("acerca.png")));

        ImageButton  btnHombre = new ImageButton(trdHombre);
        ImageButton  btnMujer = new ImageButton(trdMujer);
        ImageButton  btnAcerca = new ImageButton(trdInfo);

        btnHombre.setPosition(juego.ANCHO - 500 , 100);
        btnMujer.setPosition( juego.ANCHO -1000,100);
        btnAcerca.setPosition(juego.ANCHO-btnAcerca.getWidth(), juego.ALTO-btnAcerca.getHeight());


        //Evento botones Sexo
        btnHombre.addListener(new ClickListener(){
                                @Override
                                public void clicked(InputEvent event, float x, float y) {
                                    super.clicked(event, x, y);
                                    //INSTRUCCIONE
                                    juego.setScreen(new PantallaMenuNiveles(juego, "hombre"));
                                }
                            }
        );
        btnMujer.addListener(new ClickListener(){
                                  @Override
                                  public void clicked(InputEvent event, float x, float y) {
                                      super.clicked(event, x, y);
                                      //INSTRUCCIONE
                                      juego.setScreen(new PantallaMenuNiveles(juego, "mujer"));
                                  }
                              }
        );
        btnAcerca.addListener(new ClickListener(){
                                  @Override
                                  public void clicked(InputEvent event, float x, float y) {
                                      super.clicked(event, x, y);
                                      //INSTRUCCIONEs
                                      juego.setScreen(new PantallaAcerca(juego));
                                  }
                              }
        );

        escenaHUD.addActor(btnHombre);
        escenaHUD.addActor(btnMujer);
        escenaHUD.addActor(btnAcerca);

        Gdx.input.setInputProcessor(escenaHUD);

    }

    private void cargarTexturas() {
        texturaFondo = new Texture( "Fondos/FondoMenu.jpeg");
        textoSeleccionar = new Texture("EscogeTuPersonaje.png");
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
        borrarPantalla();
        //batch escalaTodo de acuerdo a la visat y la camara
        batch.setProjectionMatrix(camara.combined);

        batch.begin();
        batch.draw(texturaFondo, 0, 0);

     /*   personajeHombre.render(batch);
        personajeMujer.render(batch);*/

        batch.draw(textoSeleccionar, Juego.ANCHO/2-(textoSeleccionar.getWidth()/2) , 2*Juego.ALTO/3);

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
