package com.mygdx.game;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

    public class Personaje {
        private Sprite sprite;
        private Texture texturaArriba;
        private Texture texturaAbajo;

        private int health = 100;


        private boolean vivo;

        private String nombre;

    private int salud;

    public Personaje (Texture textura, float x, float y){
        sprite = new Sprite(textura);
        sprite.setPosition(x,y);

    }

        public String getNombre() {
            return nombre;
        }

        public void setNombre(String nombre) {
            this.nombre = nombre;
        }

        private void isAlive(){

        if (health > 0){
            vivo = true;
        }
    }


    public void  atacar(){


        }

    public void setHealth(int health){
        this.health = health;
    }

    public void recibeDano(int danoRecibido){
        this.health = health - danoRecibido;
    }

    public void cure(int hp){
        this.health = health + hp;
    }



    public void mover (float dx){
        sprite.setX(sprite.getX()+dx);
    }


    public void render(SpriteBatch batch){
        sprite.draw(batch);
    }



}
