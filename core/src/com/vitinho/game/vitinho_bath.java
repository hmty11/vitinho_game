package com.vitinho.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import java.util.Locale;

import pl.mk5.gdx.fireapp.GdxFIRAnalytics;
import pl.mk5.gdx.fireapp.GdxFIRAuth;
import pl.mk5.gdx.fireapp.GdxFIRDatabase;
import pl.mk5.gdx.fireapp.auth.GdxFirebaseUser;



class vitinho_bath implements Screen {


    vitinho_game_main game;
    //camera
    private Camera camera;
    private Viewport viewport;

    Vector3 touch_pos = new Vector3();

    //graphics
    private SpriteBatch batch;
    private Texture background;
//  private Texture[] vitinhos;

    Texture vitinho_clean_bar_texture;
    private Vitinho vitinho;
    int vitinho_state = 1;
    int pause = 0;
    private final float WORLD_WIDTH = 72;
    private final float WORLD_HEIGHT = 128;
    private final float margin = 10;

    Texture vitinho_bath_buton_1;
    float vitinho_bath_buton_1_width = 10;
    float vitinho_bath_buton_1_height = 10;
    float vitinho_bath_buton_1_x_position = WORLD_WIDTH/2 - vitinho_bath_buton_1_width/2;
    float vitinho_bath_buton_1_y_position = WORLD_HEIGHT/20;

    Texture vitinho_bath_buton_2;
    float vitinho_bath_buton_2_width = 10;
    float vitinho_bath_buton_2_height = 10;
    float vitinho_bath_buton_2_x_position = WORLD_WIDTH - vitinho_bath_buton_2_width/2 - margin;
    float vitinho_bath_buton_2_y_position = WORLD_HEIGHT/20;

    //Water drop texture
    Texture bath_drop1;
    int wdrop_move;

    //Heads-Up Display
    BitmapFont font;
    float hudVerticalMargin, hudLeftX, hudRightX, hudCentreX, hudRow1Y, hudRow2y, hudRow2y_bar, hudSectionWidth;

    vitinho_bath()
    {
        this.game = game;
        camera = new OrthographicCamera();
        viewport = new StretchViewport(WORLD_WIDTH, WORLD_HEIGHT, camera);
        background = new Texture("mmbg.jpg");

        //set up vitinho
        vitinho = new Vitinho(0,5,0,0,0,0, 2, WORLD_WIDTH, WORLD_HEIGHT);
        batch = new SpriteBatch();

        vitinho_bath_buton_1 = new Texture("coin.png");
        vitinho_bath_buton_2 = new Texture("coin.png");
        vitinho_clean_bar_texture = new Texture("blank.png");
        bath_drop1 = new Texture("Waterdrop.png");

        prepareHUD();
    }

    private void prepareHUD()
    {
        //Create bitmapFont from our font file
        FreeTypeFontGenerator fontGenerator = new FreeTypeFontGenerator(Gdx.files.internal("EdgeOfTheGalaxyRegular-OVEa6.otf"));
        FreeTypeFontGenerator.FreeTypeFontParameter fontParameter = new FreeTypeFontGenerator.FreeTypeFontParameter();

        fontParameter.size = 72;
        fontParameter.borderWidth = 4f;
        fontParameter.color = new Color(1, 1, 1, 0.3f);
        fontParameter.borderColor = new Color(0, 0, 0, 0.3f);
        font = fontGenerator.generateFont(fontParameter);

        //Scale the font to fit world
        font.getData().setScale(0.1f);

        //Calculate hud margins, etc.
        hudVerticalMargin = font.getCapHeight()/2 ;
        hudLeftX = hudVerticalMargin;
        hudRightX = WORLD_WIDTH * 2 / 3 - hudLeftX;
        hudCentreX = WORLD_WIDTH / 3;
        hudRow1Y = WORLD_HEIGHT - hudVerticalMargin;
        hudRow2y = WORLD_HEIGHT - hudVerticalMargin - font.getCapHeight() - 1;
        hudRow2y_bar =  WORLD_HEIGHT - hudVerticalMargin - 13;
        hudSectionWidth = WORLD_WIDTH / 3;
    }

    @Override
    public void render(float delta)
    {
        batch.begin();
        //Draw background
        batch.draw(background,0,0, WORLD_WIDTH,WORLD_HEIGHT);
        //Draw vitinho
        vitinho.draw(batch);
        //Acquire touch position
        touch_pos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
        //translate the position (pixels) to cartesian coordinates
        camera.unproject(touch_pos);

        // render Button 1
        batch.draw(vitinho_bath_buton_1,  vitinho_bath_buton_1_x_position, vitinho_bath_buton_1_y_position, vitinho_bath_buton_1_width, vitinho_bath_buton_1_height);
        //Conditional to verify if the button 1 was pressed
        if(touched_button(touch_pos, vitinho_bath_buton_1_x_position, vitinho_bath_buton_1_y_position, vitinho_bath_buton_1_width, vitinho_bath_buton_1_height))
        {
            if (Vitinho.vitinho_clean<1)
            {
                if(wdrop_move < 26)
                {
                    wdrop_move++;
                }
                else
                {
                    wdrop_move = 0;
                }
                batch.draw(bath_drop1, 36, 90 - wdrop_move, WORLD_WIDTH/16, WORLD_HEIGHT/16);
                Vitinho.vitinho_clean += 0.01;
                GdxFIRAuth.inst().signInAnonymously().then(
                GdxFIRDatabase.inst()
                        .inReference("/bob-and-john").push()
                        .setValue("bob")
                );
            }
        }

        //render button 2
       // batch.draw(vitinho_bath_buton_2,  vitinho_bath_buton_2_x_position, vitinho_bath_buton_2_y_position, vitinho_bath_buton_2_width, vitinho_bath_buton_2_height);
       // if(touched_button(touch_pos, vitinho_bath_buton_2_x_position, vitinho_bath_buton_2_y_position, vitinho_bath_buton_2_width, vitinho_bath_buton_2_height))
       // {
       //     Vitinho.vitinho_age = Vitinho.vitinho_age + 10;
      //  }

        //hud rendering
        updateAndRenderHUD();

        batch.end();

    }

    private void updateAndRenderHUD()
    {   //render top row with labels
       // font.draw(batch,"LIMPO", hudLeftX, hudRow1Y, hudSectionWidth, Align.left, false);
        font.draw(batch,"LIMPO", hudCentreX, hudRow1Y, hudSectionWidth, Align.center, false);
       // font.draw(batch,"DOENCA", hudRightX, hudRow1Y, hudSectionWidth, Align.right, false);

        //render second row with values

        batch.draw(vitinho_clean_bar_texture, 24, hudRow2y_bar, (float) Vitinho.vitinho_clean * hudSectionWidth  , 5);
       // font.draw(batch, String.format(Locale.getDefault(), "%02d", Vitinho.vitinho_age), hudCentreX, hudRow2y, hudSectionWidth, Align.center, false);
        //font.draw(batch,String.format(Locale.getDefault(), "%02d", Vitinho.vitinho_diseases), hudRightX, hudRow2y, hudSectionWidth, Align.right, false);
    }

    private boolean touched_button(Vector3 touch_position,  float x_position, float y_position, float button_width, float button_height)
    {
        if((x_position < touch_position.x && touch_position.x < x_position + button_width) &&  //X
                (y_position  < touch_position.y && touch_position.y < y_position + button_height )) //Y
        {
            if (Gdx.input.isTouched())
            {
                return true;
            }
        }
        return false;
    }

    @Override
    public void resize(int width, int height)
    {
        viewport.update(width, height, true);
        batch.setProjectionMatrix(camera.combined);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose()
    {
        //.dispose();
    }

    @Override
    public void show()
    {

    }

}
