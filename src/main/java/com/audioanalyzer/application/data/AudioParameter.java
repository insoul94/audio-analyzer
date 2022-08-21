package com.audioanalyzer.application.data;


public abstract class AudioParameter {

    public enum Type {
        PeakLevel,
        NoiseFloor,
        RMS,
        LUFS
    }

    private Object value;

//    private Object limit;

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public abstract Object calculate();
}
