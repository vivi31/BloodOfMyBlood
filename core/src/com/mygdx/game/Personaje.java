package com.mygdx.game;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

    public class Personaje {
        private Sprite sprite;
        private Texture texturaArriba;
        private Texture texturaAbajo;

        private int health = 100;



    public Personaje (Texture textura, float x, float y){
        sprite = new Sprite(textura);
        sprite.setPosition(x,y);

    }

    public void mover (float dx){
        sprite.setX(sprite.getX()+dx);
    }


    public void render(SpriteBatch batch){
        sprite.draw(batch);
    }
}
