package com.game.slot.base;

import com.badlogic.gdx.graphics.g2d.Sprite;
import aurelienribon.tweenengine.TweenAccessor;

 public class SpriteTween implements TweenAccessor<Sprite> { //** Tweening a Sprite **//

     public static final int POSITION_X = 1;

     public static final int POSITION_Y = 2;

    @Override
    public int getValues( Sprite target, int tweenType, float[] returnValues ) {

        switch( tweenType ) {
            case POSITION_X:
                // один случай для каждого объекта - возвращается один, так как изменяется только 1 значение
                returnValues[0] = target.getX();
                return 1;
            case POSITION_Y:
                // один случай для каждого объекта - возвращается один, так как изменяется только 1 значение
                returnValues[0] = target.getY();
                return 1;
            default:
                assert false; return -1;
        }
    }

    @Override
    public void setValues( Sprite target, int tweenType, float[] newValues ) {

        switch ( tweenType ) {
            case POSITION_X:
                target.setX( newValues[0] );
            break;
            case POSITION_Y:
                target.setY( newValues[0] );
            break;
            default:
                assert false;
            break;
        }
    }
}
