package com.tcg.colorlerp.input;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;

public class MyInputProcessor extends InputAdapter {

    @Override
    public boolean keyDown(int keycode) {
        if(keycode == Input.Keys.RIGHT || keycode == Input.Keys.UP) {
            MyInput.setKey(MyInput.NEXT, true);
        }
        if(keycode == Input.Keys.LEFT || keycode == Input.Keys.DOWN) {
            MyInput.setKey(MyInput.PREV, true);
        }
        return true;
    }

    @Override
    public boolean keyUp(int keycode) {
        if(keycode == Input.Keys.RIGHT || keycode == Input.Keys.UP) {
            MyInput.setKey(MyInput.NEXT, false);
        }
        if(keycode == Input.Keys.LEFT || keycode == Input.Keys.DOWN) {
            MyInput.setKey(MyInput.PREV, false);
        }
        return true;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        MyInput.setMouse(screenX, screenY);
        MyInput.setKey(MyInput.CLICK, true);
        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        MyInput.setMouse(screenX, screenY);
        MyInput.setKey(MyInput.CLICK, false);
        return true;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        MyInput.setMouse(screenX, screenY);
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        MyInput.setMouse(screenX, screenY);
        return false;
    }
}
