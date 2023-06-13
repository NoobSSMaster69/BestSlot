package com.game.slot.sprite;

import aurelienribon.tweenengine.*;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.utils.Timer;
import com.game.slot.decorator.SpriteSymbolsDecorator;
import com.game.slot.base.SpriteTween;
import com.game.slot.math.Rnd;
import java.util.*;


 public class Symbols extends SpriteSymbolsDecorator {

     private TextureAtlas symbolTextures = new TextureAtlas();

     private List<SpriteSymbolsDecorator> symbols = new ArrayList<SpriteSymbolsDecorator>();

     private Map<String, List<SpriteSymbolsDecorator>> hashMap;

     private final TweenManager tweenManager = new TweenManager();

     private static LineNumbers lineNumbers;

     private int cellNumber;

     private final float startX = -0.5655f;

     private final float startY = -0.291f;

     private final float offsetX = 0.233f;

     private final float offsetY = 0.231f;

     private List<Timeline> timelines;

     public Symbols( AssetManager manager, LineNumbers lineNumbers ) {
        this.manager = manager;
        this.lineNumbers = lineNumbers;
        this.addSymbols();
    }

     private Symbols ( TextureAtlas atlas, int symbolNumber, int i, int cellNumber ) {
        super( atlas.findRegion("symbol_animation-" + symbolNumber ), symbolNumber, cellNumber );
        this.cellNumber = cellNumber;
        this.resize( i );
    }

     public void update( float delta ) {
        this.tweenManager.update( delta );
    }

     public Symbols getSymbols () {
        return this;
    }

     private void addSymbols () {

        if ( this.symbols.size() == 21 ) {
            return;
        }

        this.symbolTextures = new TextureAtlas("symbols-animations.tpack" );

        this.hashMap = new HashMap<String, List<SpriteSymbolsDecorator>>();
        for ( int i = 0; i < 5; i++ ) {

            this.symbols = new ArrayList<SpriteSymbolsDecorator>();
            for ( int j = 0; j < 21; j++ ) {

                this.symbols.add( new Symbols( this.symbolTextures, Rnd.nextInt( 0, 10 ), i, j ) );
            }

            this.hashMap.put( "coll-" + i, this.symbols );
        }
    }

     public void startTwisting () {

        this.addSymbols();
        Tween.registerAccessor( Sprite.class, new SpriteTween() );

        this.timelines = new ArrayList<Timeline>();
        lineNumbers.hideAllNumber();

        float[] durations = { 1.0f, 1.1f, 1.2f, 1.3f, 1.4f };

        this.enableBlurSymbols();
        for ( int i = 0; i < durations.length; i++ ){
            for ( SpriteSymbolsDecorator sprite: this.hashMap.get( "coll-" + i ) ) {
                this.startTween( sprite, durations[i] );
            }
        }

        this.stopAnimateByTimer();
    }

     public void startTween ( final SpriteSymbolsDecorator sprite, float duration ) {

        this.timelines.add( Timeline.createSequence()
            .beginSequence()
                .push( Tween.to( sprite, SpriteTween.POSITION_Y, duration )
                    .target( - ( this.offsetX * 18 ) + sprite.getY() )
                    .ease( TweenEquations.easeNone ) )
                    .setCallbackTriggers( 1 )
                .push( Tween.to( sprite, SpriteTween.POSITION_Y, duration )
                    .target( - ( this.offsetY * 18 ) + ( sprite.getY() ) )
                    .ease( TweenEquations.easeOutElastic ) )
                    .setCallback((type, source) -> {
                        if ( sprite.id > 17 ) {
                            sprite.setAlpha( 1.0f );
                        }
                    })
            .end()
            .start( this.tweenManager ) );
    }

     public void stopAnimate ( ) {
        for ( Timeline timeline : this.timelines ) {
            timeline.update( 0.8f );
        }
    }

     public void draw ( SpriteBatch batch ) {
        for ( Map.Entry<String, List<SpriteSymbolsDecorator>> entry : this.hashMap.entrySet() ) {

            for ( Iterator<SpriteSymbolsDecorator> iter = entry.getValue().iterator(); iter.hasNext(); ) {
                Sprite sprite = iter.next();
                if ( ( sprite.getY() + this.offsetY ) <= this.startY ) {
                    iter.remove();
                }
                else {
                    sprite.draw( batch );
                }
            }
        }
    }

     public void dispose () {
        this.symbolTextures.dispose();
    }

     private void stopAnimateByTimer () {

        float delay = 1.28f; // seconds

        Timer.schedule(new Timer.Task(){
            @Override
            public void run() {
                System.out.println( "Stop symbol animation" );
                lineNumbers.showAllNumber();
            }
        }, delay);
    }

    private void enableBlurSymbols () {
        for ( Map.Entry<String, List<SpriteSymbolsDecorator>> entry : this.hashMap.entrySet() ) {
            for ( SpriteSymbolsDecorator sprite : entry.getValue() ) {
                sprite.setAlpha( 0.6f );
            }
        }
    }

     private void resize( int i ) {

        float height = 0.2f;
        float aspect  = this.getRegionWidth() / (float) this.getRegionHeight();

        this.setSize(height * aspect , height );
        this.setPosition(
            this.startX + ( this.offsetX* i ),
            this.startY + ( this.offsetY * this.cellNumber )
        );
    }
}
