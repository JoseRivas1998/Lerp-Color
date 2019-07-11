package com.tcg.colorlerp.input;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.viewport.Viewport;

public class MyInput {

    private static boolean[] keys;
    private static boolean[] pKeys;

    private static final int NUM_KEYS = 3;
    public static final int CLICK = 0;
    public static final int NEXT = 1;
    public static final int PREV = 2;

    private static final Vector2 mouse;

    static {
        keys = new boolean[NUM_KEYS];
        pKeys = new boolean[NUM_KEYS];
        mouse = new Vector2();
    }

    public static void update() {
        for (int i = 0; i < NUM_KEYS; i++) {
            pKeys[i] = keys[i];
        }
    }

    static void setKey(int k, boolean b) {
        keys[k] = b;
    }

    static void setMouse(float screenX, float screenY) {
        mouse.set(screenX, screenY);
    }

    public static boolean keyDown(int key) {
        return keys[key];
    }

    public static boolean keyPressed(int key) {
        return keys[key] && !pKeys[key];
    }

    public static Vector2 screenMouse() {
        return new Vector2(mouse);
    }

    public static Vector2 worldMouse(Viewport viewport) {
        Vector3 world = viewport.getCamera().unproject(new Vector3(mouse, 0));
        return new Vector2(world.x, world.y);
    }

}
