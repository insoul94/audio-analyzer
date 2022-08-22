package com.audioanalyzer.application.data;


public abstract class AudioParameter {

    public enum Type {
        PeakLevel,
        NoiseFloor,
        RMS,
        LUFS
    }

    private Object value;
    private Type type;

    public AudioParameter(Type type) {
        this.type = type;
        this.value = calculate();
    }

    //    private Object limit;

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public Type getType() {
        return type;
    }

    public abstract Object calculate();
}
