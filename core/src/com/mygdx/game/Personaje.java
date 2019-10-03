package com.mygdx.game;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

    public class Personaje {
        private Sprite sprite;

        //Texturas del personaje HOMBRE
        private Texture texturaArriba;
        private Texture texturaAbajo;

        //Texturas del personaje MUJER

        //Características.
        private int health = 100;
        private boolean vivo;
        private String nombre;
        public int daño; //cuanto daño hace. Esto lo va a recivir  enemigo para recibir daño.

    public Personaje (Texture textura, float x, float y){  //ESTO KKKKK?
        sprite = new Sprite(textura);
        sprite.setPosition(x,y);

    }

        public String getNombre() {
            return nombre;
        }

        public void setNombre(String nombre) {
            this.nombre = nombre;
        }

        private void isAlive(){   //Est hay qur ponerlo en estados enum.

        if (health > 0){
            vivo = true;
        }
    }


    public  int  atacar(){

        return 0;  //Regresa cuanto ataca
        }


    public void setHealth(int health){
        this.health = health;
    }

    public void recibeDano(int danoRecibido){
        this.health = health - Enemigo;
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
