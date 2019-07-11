package com.tcg.colorlerp;

import java.util.Objects;

public final class HSVTriple {
    public final float h;
    public final float s;
    public final float v;


    public HSVTriple(float h, float s, float v) {
        this.h = h;
        this.s = s;
        this.v = v;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HSVTriple hsvTriple = (HSVTriple) o;
        return Float.compare(hsvTriple.h, h) == 0 &&
                Float.compare(hsvTriple.s, s) == 0 &&
                Float.compare(hsvTriple.v, v) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(h, s, v);
    }

    @Override
    public String toString() {
        return String.format("(%.2f, %.2f%%, %.2f%%)", h, s * 100, v * 100);
    }
}
