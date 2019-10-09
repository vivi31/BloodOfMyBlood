package com.mygdx.game;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Enemigo {
    private float salud = 100;
    private float ataque = 20;

    private Sprite sprite;
    private TextureRegion texturaCompleta;
    private TextureRegion[][] texturas;

    EstadosEnemigo estadosEnemigo = EstadosEnemigo.NEUTRAL;



    public Enemigo(Texture textura, float x, float y ){
        texturaCompleta = new TextureRegion(textura);
        texturas = texturaCompleta.split(200,300);
        sprite = new Sprite(texturas[0][0]);
        sprite.setPosition(x,y);
    }

    public void atacar(Personaje personaje){

    }

    public void perseguir(Personaje personaje){
        float xP = personaje.getX();
        if ((sprite.getX() > xP)){
            sprite.setX(sprite.getX() - 6);
        } else{
            sprite.setX(sprite.getX() + 6);
        }
    }

    public void render(SpriteBatch batch){
        sprite.draw(batch);
    }

    enum EstadosEnemigo{
        NEUTRAL,
        PRE,
        ACTIVE,
        RECOVERY,
        STUNNED
    }
}


