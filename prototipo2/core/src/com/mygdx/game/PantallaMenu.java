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

class PantallaMenu implements Screen {

    private final Juego juego;
    private OrthographicCamera camara;
    private Viewport vista;

    private SpriteBatch batch;

    //fondo
    private Texture texturaFondo;

    //escena de menu (botones)
    private Stage escenaMenu;



    public PantallaMenu(Juego juego) {
        this.juego = juego;   //this de pantalla inicio consrtuctor
    }

    @Override
    public void show() { //mostrar en pantalla fisica. ini items, texturas.
        configurarVista();
        cargarTexturas();
        crearMenu();

    }

    private void crearMenu() {
        escenaMenu = new Stage(vista);

        title = new Texture("title.png");

        TextureRegionDrawable btnJugar = new TextureRegionDrawable(new TextureRegion(new Texture("btnJugar.png")));
        TextureRegionDrawable btnJugarOprimido = new TextureRegionDrawable(new TextureRegion(new Texture("btnJugar2.png")));

        ImageButton btnSelec = new ImageButton(btnJugar,btnJugarOprimido);
        btnSelec.setPosition(Juego.ANCHO/2-btnSelec.getWidth()/2, 2*Juego.ALTO/5);

        //Evento de boton.
        btnSelec.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                //INSTRUCCIONES
                juego.setScreen(new PantallaSeleccion(juego));
            }
        });


        escenaMenu.addActor(btnSelec);

        Gdx.input.setInputProcessor(escenaMenu);

    }

    private void cargarTexturas() {
        texturaFondo = new Texture( "fondo.jpg");
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
        batch.draw(title, Juego.ANCHO/2-btnSelec.getWidth()/2, 2*Juego.ALTO/3);
        batch.end();
        escenaMenu.draw();
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
