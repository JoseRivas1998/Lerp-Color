package com.tcg.colorlerp;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.MathUtils;

public enum LerpAlgoritms {
    LERP_RGB((c1, c2, percentage) -> {
        float r = MathUtils.lerp(c1.r, c2.r, percentage);
        float g = MathUtils.lerp(c1.g, c2.g, percentage);
        float b = MathUtils.lerp(c1.b, c2.b, percentage);
        return new Color(r, g, b, 1);
    }),
    LERP_HSV((c1, c2, percentage) -> {
        HSVTriple h1 = Helpers.rgbToHsv(c1);
        HSVTriple h2 = Helpers.rgbToHsv(c2);
        float h = MathUtils.lerp(h1.h, h2.h, percentage);
        float s = MathUtils.lerp(h1.s, h2.s, percentage);
        float v = MathUtils.lerp(h1.v, h2.v, percentage);
        return Helpers.hsvToRgb(h, s, v);
    }),
    LERP_COLOR_CODE((c1, c2, percentage) -> {
       int rgba1 = Helpers.rgba8888(c1);
       int rgba2 = Helpers.rgba8888(c2);
       int rgba3 = (int) MathUtils.lerp(rgba1, rgba2, percentage);
       return new Color(rgba3);
    });

    public final LerpAlgorithm lerpAlgo;

    LerpAlgoritms(LerpAlgorithm lerpAlgo) {
        this.lerpAlgo = lerpAlgo;
    }

    @FunctionalInterface
    public interface LerpAlgorithm {
        Color lerp(Color c1, Color c2, float percentage);
    }

}
