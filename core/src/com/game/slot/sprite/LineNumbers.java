package com.game.slot.sprite;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.game.slot.decorator.SpriteSymbolsDecorator;

import java.util.*;

 public class LineNumbers extends SpriteSymbolsDecorator {

     private TextureAtlas symbolTextures = new TextureAtlas();

     private List<SpriteSymbolsDecorator> numbers = new ArrayList<SpriteSymbolsDecorator>();

     private Map<String, List<SpriteSymbolsDecorator>> hashMap;


     private float startRightX = 0.602f;

     private float startLeftX = -0.647f;

     private float startY = -0.32f;

     private float offsetY = 0.08f;

     public LineNumbers ( AssetManager manager ) {
        this.manager = manager;
        this.addNumbers();
    }

     private LineNumbers ( TextureAtlas atlas, int cellNumber, float side ) {
        super( atlas.findRegion(cellNumber + "" ), cellNumber );
        this.resize( cellNumber, side );
    }

     public void showWinNumber ( int[] winNums ) {

    }

     public void showAllNumber () {
        for ( Map.Entry<String, List<SpriteSymbolsDecorator>> entry : this.hashMap.entrySet() ) {
            for (Iterator<SpriteSymbolsDecorator> iter = entry.getValue().iterator(); iter.hasNext(); ) {
                Sprite sprite = iter.next();
                sprite.setAlpha( 1.0f );
            }
        }
    }

     public void hideAllNumber () {
        for ( Map.Entry<String, List<SpriteSymbolsDecorator>> entry : this.hashMap.entrySet() ) {
            for (Iterator<SpriteSymbolsDecorator> iter = entry.getValue().iterator(); iter.hasNext(); ) {
                Sprite sprite = iter.next();
                sprite.setAlpha( 0.4f );
            }
        }
    }

     public LineNumbers getNumbers () {
        return this;
    }

     public void draw ( SpriteBatch batch ) {
        for ( Map.Entry<String, List<SpriteSymbolsDecorator>> entry : this.hashMap.entrySet() ) {

            for (Iterator<SpriteSymbolsDecorator> iter = entry.getValue().iterator(); iter.hasNext(); ) {
                Sprite sprite = iter.next();
                sprite.draw( batch );
            }
        }
    }

     public void dispose () {
        this.symbolTextures.dispose();
    }

     private void resize( int i, float startPosX ) {

        float height = 0.05f;
        float aspect  = this.getRegionWidth() / (float) this.getRegionHeight();
        float[] positionsY = { -0.29f, -0.215f, -0.145f, -0.05f, 0.02f, 0.08f, 0.18f, 0.25f, 0.32f };

        this.setSize(height * aspect , height );
        this.setPosition( startPosX, positionsY[( i - 1 )] );
    }

     private void addNumbers () {

        this.symbolTextures = new TextureAtlas("numbers-line.tpack" );

        this.hashMap = new HashMap<String, List<SpriteSymbolsDecorator>>();

        this.numbers = new ArrayList<SpriteSymbolsDecorator>();
        for ( int j = 1; j < 10; j++ ) {

            this.numbers.add( new LineNumbers( this.symbolTextures, j, this.startLeftX ) );
        }

        this.hashMap.put( "left", this.numbers );

        this.numbers = new ArrayList<SpriteSymbolsDecorator>();
        for ( int j = 1; j < 10; j++ ) {

            this.numbers.add( new LineNumbers( this.symbolTextures, j, this.startRightX ) );
        }

        this.hashMap.put( "right", this.numbers );
    }
}
