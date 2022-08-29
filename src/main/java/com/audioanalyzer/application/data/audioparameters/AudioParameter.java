package com.audioanalyzer.application.data.audioparameters;


import com.audioanalyzer.application.data.AudioFile;

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
    }

    //    private Object limit;

    public Object getValue() {
        return value;
    }

    public Type getType() {
        return type;
    }

    void setValue(Object value) {
        this.value = value;
    }

    public abstract void calculate(byte[] data);
}
