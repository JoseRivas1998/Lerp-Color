package com.tcg.colorlerp;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.MathUtils;

public interface Helpers {

    static Color color(int r, int g, int b, int a) {
        return new Color(r / 255f, g / 255f, b / 255f, a / 255f);
    }

    static HSVTriple rgbToHsv(Color color) {
        return rgbToHsv(color.r, color.g, color.b);
    }

    static HSVTriple rgbToHsv(float r, float g, float b) {
        float cMax = Math.max(Math.max(r, g), b);
        float cMin = Math.min(Math.min(r, g), b);
        float delta = cMax - cMin;
        float h = 0;
        float s;
        float v;
        if(Float.compare(delta, 0) == 0) {
            h = 0;
        } else if(Float.compare(cMax, r) == 0) {
            h = 60 * (((g - b) / delta) % 6f);
        } else if(Float.compare(cMax, g) == 0) {
            h = 60 * (((b - r) / delta) + 2);
        } else if(Float.compare(cMax, b) == 0) {
            h = 60 * (((r - g) / delta) + 4);
        }
        if(h < 0) {
            h = 360f + h;
        }
        if(Float.compare(cMax, 0) == 0) {
            s = 0;
        } else {
            s = delta / cMax;
        }
        v = cMax;
        return new HSVTriple(h, s, v);
    }

    static Color hsvToRgb(HSVTriple hsvTriple) {
        return hsvToRgb(hsvTriple.h, hsvTriple.s, hsvTriple.v);
    }

    static Color hsvToRgb(float h, float s, float v) {
        float c = v * s;
        float x = c * (1 - (Math.abs(((h / 60) % 2) - 1)));
        float m = v - c;
        float r = 0;
        float g = 0;
        float b = 0;
        if(h >= 300f) {
            r = c;
            g = 0;
            b = x;
        } else if(h >= 240f) {
            r = x;
            g = 0;
            b = c;
        } else if(h >= 180f) {
            r = 0;
            g = x;
            b = c;
        } else if(h >= 120f) {
            r = 0;
            g = c;
            b = x;
        } else if(h >= 60f) {
            r = x;
            g = c;
            b = 0;
        } else if(h >= 0) {
            r = c;
            g = x;
            b = 0;
        }
        return new Color(r + m, g + m, b + m, 1);
    }

    static int rgba8888(Color color) {
        int r = (int) (color.r * 0xFF);
        int g = (int) (color.g * 0xFF);
        int b = (int) (color.b * 0xFF);
        int a = (int) (color.a * 0xFF);
        int rg = (r << 8) | g;
        int rgb = rg << 8 | b;
        return rgb << 8 | a;
    }

}
