package com.game.slot.sprite;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.game.slot.base.Sprite;
import com.game.slot.math.Rect;

 public class Background extends Sprite {

     public Background( TextureRegion region ) {
        super( region );
    }

    @Override
    public void resize( Rect worldBounds ) {
        setHeightProportion( worldBounds.getHeight() );
        pos.set( worldBounds.pos );
    }
}
