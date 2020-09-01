package com.vitinho.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;

class Vitinho
{
    //Vitinho state
    int vitinho_hungry; // 0: not hungry, 3: starving.

    //Vitinho Age
    int vitinho_age;

    //Vitinho diseases
    int vitinho_diseases; //need to creat a bunch of diseases.

    //Vitinho position
    float vitinho_position_x;
    float vitinho_position_y;

    //Vitinho texture
    Texture vitinho_texture;

    public Vitinho(int vitinho_hungry, int vitinho_age, int vitinho_diseases, float vitinho_position_x , float vitinho_position_y, Texture vitinho_texture)
    {
        this.vitinho_texture = vitinho_texture;
        this.vitinho_position_x = vitinho_position_x;
        this.vitinho_position_y = vitinho_position_y;
        this.vitinho_hungry = vitinho_hungry;
        this.vitinho_age = vitinho_age;
        this.vitinho_diseases = vitinho_diseases;

    }

    public void draw(Batch batch)
    {
        batch.draw(vitinho_texture, vitinho_position_x, vitinho_position_y);
    }
}
