package com.audioanalyzer.application.data.audioparameters;

public abstract class AudioParameter {

    public enum Type {
        PeakLevel,
        NoiseFloor,
        RMS,
        LUFS
    }

    private float value;
    private Type type;

    public AudioParameter(Type type) {
        this.type = type;
    }

    public float getValue() {
        return value;
    }

    public Type getType() {
        return type;
    }

    protected void setValue(float value) {
        this.value = value;
    }

    public abstract void calculate(float[] data);
}
