package com.game.slot.screen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.game.slot.base.ActionListener;
import com.game.slot.base.Base2DScreen;
import com.game.slot.math.Rect;
import com.game.slot.sprite.Background;
import com.game.slot.sprite.LoadBar;

 public class LoaderScreen extends Base2DScreen implements ActionListener {

     private Texture bgTexture;

     private Background background;

     private Texture loadBarTexture;

     private LoadBar loadBar;

     private float loadBarWidth = 0.731f;

     private TextureAtlas textureAtlas;

     private AssetManager manager;

     private boolean isLoaded = false;

     private Game game;


     public LoaderScreen( Game game ) {
        super();
        this.game = game;
    }

    @Override
    public void show() {
        super.show();

        this.manager = new AssetManager ();
        this.manager.load("C:\\Users\\Timur\\Desktop\\bir shiy\\BestSlot\\assets\\mainbackground.png", Texture.class );
        this.manager.load("mainbackground.jpg", Texture.class );
        this.manager.load("bonus_background.jpg", Texture.class );
        this.manager.load("badlogic.jpg", Texture.class );
        this.manager.load("symbols-animations.png", Texture.class );
        this.manager.load("symbols-animations.tpack", TextureAtlas.class );
        this.manager.load("numbers-line.png", Texture.class );
        this.manager.load("numbers-line.tpack", TextureAtlas.class );

        this.loadBarTexture = new Texture("loadbar.png" );
        this.loadBar        = new LoadBar( new TextureRegion( this.loadBarTexture ) );

        this.bgTexture  = new Texture(Gdx.files.internal("loadscreen.jpg") );
        this.background = new Background( new TextureRegion( this.bgTexture ) );

    }

    @Override
    public void render( float delta ) {
        super.render(delta);

        this.update(delta);

        this.draw();
    }

     public void update( float delta ) {
        if( !this.manager.update() ) {
            if ( this.loadBarWidth > (float) this.manager.getProgress() ) {
                this.loadBar.setWidth( (float) this.manager.getProgress() );
            }
        }
        else {
            if ( !this.isLoaded ) {
                this.isLoaded = true;
                this.loadBar.setWidth( this.loadBarWidth );
                this.game.setScreen( new SlotsScreen( this.manager ) );
            }
        }
    }

     public void draw() {
        Gdx.gl.glClearColor(0.128f, 0.53f, 0.9f, 1 );
        Gdx.gl.glClear( GL20.GL_COLOR_BUFFER_BIT );

        this.batch.begin();

        this.background.draw( this.batch );
        this.loadBar.draw( this.batch );

        this.batch.end();
    }

    @Override
    public void resize( Rect worldBounds ) {
        System.out.println( "LoaderScreen => resize" );
        this.loadBar.resize( worldBounds );
        this.background.resize( worldBounds );
    }

    @Override
    public void dispose() {
        this.bgTexture.dispose();
        this.loadBarTexture.dispose();
        super.dispose();
    }

    @Override
    public boolean touchDown(Vector2 touch, int pointer ) {
        return false;
    }

    @Override
    public boolean touchUp( Vector2 touch, int pointer ) {
        return super.touchUp( touch, pointer );
    }

    @Override
    public void actionPerformed( Object src ) {
        /*
        if ( src == this.btnExit ) {
            Gdx.app.exit();
        }
        else if ( src == this.btnPlay ) {
            this.game.setScreen( new GameScreen( this.game ) );
        }
        */
    }

    @Override
    public void hide() {
        super.hide();
    }

    @Override
    public boolean scrolled(float amountX, float amountY) {
        System.out.println( "scrolled" );

        return false;
    }
}
