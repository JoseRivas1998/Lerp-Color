package com.tcg.colorlerp;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.math.Vector2;

public class Text {

    public static final byte TOP = 1 << 0;
    public static final byte MIDDLE = 1 << 1;
    public static final byte BOTTOM = 1 << 2;

    public static final byte LEFT = 1 << 3;
    public static final byte CENTER = 1 << 4;
    public static final byte RIGHT = 1 << 5;

    public static final byte TOP_LEFT = TOP | LEFT;
    public static final byte TOP_RIGHT = TOP | RIGHT;
    public static final byte BOTTOM_LEFT = BOTTOM | LEFT;
    public static final byte BOTTOM_RIGHT = BOTTOM | RIGHT;
    public static final byte MIDDLE_CENTER = MIDDLE | CENTER;

    private String text;
    private byte align;

    private BitmapFont font;
    private GlyphLayout gl;

    private Vector2 pos;
    private Vector2 textPos;

    public Text() {
        align = TOP_LEFT;
        pos = new Vector2();
        textPos = new Vector2();
        text = "";
        FreeTypeFontGenerator.FreeTypeFontParameter param = new FreeTypeFontGenerator.FreeTypeFontParameter();
        param.size = 24;
        param.color = Color.WHITE;
        FreeTypeFontGenerator freeTypeFontGenerator = new FreeTypeFontGenerator(Gdx.files.internal("pixeled.ttf"));
        font = freeTypeFontGenerator.generateFont(param);
        freeTypeFontGenerator.dispose();
        gl = new GlyphLayout();
    }

    private float getWidth() {
        gl.setText(font, text);
        return gl.width;
    }

    private float getHeight() {
        gl.setText(font, text);
        return gl.height;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setText(Object obj) {
        setText(obj.toString());
    }

    public void setPos(float x, float y) {
        pos.set(x, y);
    }

    public void setAlign(byte align) {
        this.align = align;
    }

    private void setTextPos() {
        if((align & TOP) != 0) {
            textPos.y = pos.y;
        } else if((align & MIDDLE) != 0) {
            textPos.y = pos.y + (getHeight() * 0.5f);
        } else if((align & BOTTOM) != 0) {
            textPos.y = pos.y + getHeight();
        }
        if((align & LEFT) != 0) {
            textPos.x = pos.x;
        } else if((align & CENTER) != 0) {
            textPos.x = pos.x - (getWidth() * 0.5f);
        } else if((align & RIGHT) != 0) {
            textPos.x = pos.x - getWidth();
        }
    }

    public void draw(SpriteBatch sb) {
        setTextPos();
        font.draw(sb, text, textPos.x, textPos.y);
    }

}
