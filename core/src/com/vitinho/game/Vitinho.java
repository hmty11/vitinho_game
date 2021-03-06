package com.vitinho.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;

class Vitinho
{

    //Vitinho hungry
    static double vitinho_hungry = 1; // 0: starving, 1: full.

    //Vitinho Age
    static int vitinho_age;

    //Vitinho Bath
    static double vitinho_clean = 1; // 0: dirty, 1: clean.

    //Vitinho diseases
    static int vitinho_diseases; //need to creat a bunch of diseases.

    //Vitinho position
    float vitinho_position_x;
    float vitinho_position_y;

    //screen_size
    float screen_width;
    float screen_height;

    int pause = 0;

    //Vitinho texture
    int vitinho_state;
    int vitinho_move;
    private Texture[] vitinhos;
    private Texture[] bath;

    public Vitinho(int vitinho_hungry, int vitinho_age, int vitinho_clean, int vitinho_diseases, float vitinho_position_x , float vitinho_position_y, int vitinho_state, float screen_width, float screen_height)
    {
        this.vitinho_state = vitinho_state;
        this.vitinho_position_x = vitinho_position_x;
        this.vitinho_position_y = vitinho_position_y;
        this.screen_width = screen_width;
        this.screen_height = screen_height;
        this.vitinho_hungry = vitinho_hungry;
        this.vitinho_age = vitinho_age;
        this.vitinho_diseases = vitinho_diseases;
        this.vitinho_clean = vitinho_clean;

        //case vitinho_state = 1 textures
        vitinhos = new Texture[4];
        vitinhos[0] = new Texture("frame-1.png");
        vitinhos[1] = new Texture("frame-2.png");
        vitinhos[2] = new Texture("frame-3.png");
        vitinhos[3] = new Texture("frame-4.png");

    }

    public void draw(Batch batch)
    {
        switch(vitinho_state)
        {
            case 1:
                if(pause < 8)
                {
                    pause++;
                }
                else
                {
                    pause = 0;
                    if(vitinho_move < 3)
                    {
                        vitinho_move++;
                    }
                    else
                    {
                        vitinho_move = 0;
                    }
                }
                batch.draw(vitinhos[vitinho_move], screen_width/2 - 10, screen_height/2  - 20, screen_width/4, screen_height/4);
                return;

            case 2:
                batch.draw(vitinhos[3], screen_width/2 - 10, screen_height/2  - 20, screen_width/4, screen_height/4);
                return;

            case 3:
                if(pause < 8)
                {
                    pause++;
                }
                else
                {
                    pause = 0;
                    if(vitinho_move < 3)
                    {
                        vitinho_move++;
                    }
                    else
                    {
                        vitinho_move = 0;
                    }
                }
                batch.draw(vitinhos[vitinho_move], screen_width/2 - 200, screen_height/2 - 200, screen_width/4, screen_height/4);
                return;
        }
    }
}
