package com.game.slot.base;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Matrix3;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector2;
import com.game.slot.math.MatrixUtils;
import com.game.slot.math.Rect;

public abstract class Base2DScreen implements Screen, InputProcessor {

    protected SpriteBatch batch;

    private Rect screenBounds;

    protected Rect worldBounds;

    private Rect glBounds;

    protected Matrix4 worldToGl;

    protected Matrix3 screenToWorld;

    private Vector2 touch;

    @Override
    public void show() {
        System.out.println( "show" );

        this.batch = new SpriteBatch();
        Gdx.input.setInputProcessor( this );

        this.screenBounds = new Rect();
        this.worldBounds  = new Rect();
        this.glBounds  = new Rect(0, 0, 1f, 1f );
        this.worldToGl = new Matrix4();
        this.screenToWorld = new Matrix3();
        this.touch = new Vector2();
    }

    @Override
    public void render( float delta ) {}

    @Override
    public void resize( int width, int height ) {

        System.out.println( "resize w = " + width + " h = " + height );

        this.screenBounds.setSize( width, height );
        this.screenBounds.setLeft( 0 );
        this.screenBounds.setBottom( 0 );

        float aspect = width / (float) height;
        this.worldBounds.setHeight( 1f );
        this.worldBounds.setWidth( 1f * aspect );

        MatrixUtils.calcTransitionMatrix( this.worldToGl, this.worldBounds, this.glBounds );
        this.batch.setProjectionMatrix( this.worldToGl );
        MatrixUtils.calcTransitionMatrix( this.screenToWorld, this.screenBounds, this.worldBounds );
        resize( this.worldBounds );
    }

    public void resize( Rect worldBounds ) {};

    @Override
    public void pause() {
        System.out.println( "pause" );
    }

    @Override
    public void resume() {
        System.out.println( "resume" );
    }

    @Override
    public void hide() {
        System.out.println( "hide" );
    }

    @Override
    public void dispose() {
        System.out.println( "dispose" );
    }

    @Override
    public boolean keyDown( int keycode ) {
        System.out.println( "keyDown" );
        return false;
    }

    @Override
    public boolean keyUp( int keycode ) {
        System.out.println( "keyDown" );
        return false;
    }

    @Override
    public boolean keyTyped( char character ) {
        System.out.println( "keyTyped character = " + character );
        return false;
    }

    @Override
    public boolean touchDown( int screenX, int screenY, int pointer, int button ) {
        //System.out.println( "touchDown" );

        this.touch.set( screenX, this.screenBounds.getHeight() - screenY).mul( this.screenToWorld );
        touchDown( this.touch, pointer );

        return false;
    }

    public boolean touchDown( Vector2 touch, int pointer ) {
        //System.out.println( "touchDown touch.x = " + touch.x + " touch.y = " + touch.y );
        return false;
    }

    @Override
    public boolean touchUp( int screenX, int screenY, int pointer, int button ) {
        //System.out.println( "touchUp" );
        //this.touch.set( screenX, this.screenBounds.getHeight() - screenY).mul( this.screenToWorld );
        touchUp( this.touch, pointer );
        return false;
    }

    public boolean touchUp( Vector2 touch, int pointer ) {
        //System.out.println( "touchUp touch.x = " + touch.x + " touch.y = " + touch.y );

        return false;
    }

    @Override
    public boolean touchDragged( int screenX, int screenY, int pointer ) {
        System.out.println( "touchDragged" );
        return false;
    }

    @Override
    public boolean mouseMoved( int screenX, int screenY ) {
        //System.out.println( "mouseMoved" );
        return false;
    }

//    @Override
//    public boolean scrolled( int amount ) {
//        System.out.println( "scrolled" );
//        return false;
//    }
}
