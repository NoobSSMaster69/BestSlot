package com.game.slot.sprite;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
//import funkymonkey.com.base.Sprite;

import com.game.slot.math.Rect;

 public class Circle extends Sprite {

     public Circle( TextureRegion region ) {
        super( region );
    }

     public Circle( TextureRegion region, int rows, int cols, int frames ) {
        super( region );
    }
}
