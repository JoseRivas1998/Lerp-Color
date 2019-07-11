package com.tcg.colorlerp;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Ellipse;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.tcg.colorlerp.input.MyInput;

public class MySlider {

    private Ellipse slider;
    private Rectangle rectangle;

    private Color sliderColor;
    private boolean clicked;

    public MySlider(float x, float y, float width, float height) {
        rectangle = new Rectangle(x, y, width, height);
        slider = new Ellipse(x, y + (height * 0.5f), height * 1.75f, height * 1.75f);
        sliderColor = Color.WHITE;
        clicked = false;
    }

    public void handleInput(Viewport viewport) {
        Vector2 worldMouse = MyInput.worldMouse(viewport);
        if(MyInput.keyPressed(MyInput.CLICK) && (slider.contains(worldMouse) || rectangle.contains(worldMouse))) {
            clicked = true;
            sliderColor = Color.WHITE;
        }
        if(MyInput.keyDown(MyInput.CLICK) && clicked) {
            sliderColor = new Color(0xD2_FF_FF_FF);
            slider.x = MathUtils.clamp(worldMouse.x, rectangle.x, rectangle.x + rectangle.width);
        } else if(clicked) {
            clicked = false;
            sliderColor = Color.WHITE;
        }
    }

    public float percentage() {
        return (slider.x - rectangle.x) / rectangle.width;
    }

    public Vector2 getPos() {
        return new Vector2(rectangle.x, rectangle.y);
    }

    public void draw(ShapeRenderer sr) {
        sr.setColor(Color.GRAY);
        sr.rect(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
        sr.setColor(sliderColor);
        sr.ellipse(slider.x - slider.width * 0.5f, slider.y - slider.height * 0.5f, slider.width, slider.height);
        sr.setColor(Color.WHITE);
    }

}
