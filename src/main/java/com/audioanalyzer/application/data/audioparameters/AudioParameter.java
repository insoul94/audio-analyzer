package com.audioanalyzer.application.data.audioparameters;

import java.util.Objects;

public abstract class AudioParameter {

    private float value;

    private final AudioParameterType type;

    public AudioParameter(AudioParameterType type) {
        this.type = Objects.requireNonNull(type, "type must not be null");
    }

    public final float getValue() {
        return value;
    }

    public final void setValue(float value) {
        this.value = value;
    }

    public final AudioParameterType getType() {
        return type;
    }

    public abstract float calculate(float[] data);
}
