package com.audioanalyzer.application.data.audioparameters;

public abstract class AudioParameter {

    private float value;

    private AudioParameterType type;

    public AudioParameter(AudioParameterType type) {
        this.type = type;
    }

    public float getValue() {
        return value;
    }

    protected void setValue(float value) {
        this.value = value;
    }

    public AudioParameterType getType() {
        return type;
    }

    public abstract void calculate(float[] data);
}
