package com.audioanalyzer.application.data.audioparameters;


import com.audioanalyzer.application.data.AudioFile;

import java.io.InputStream;

public abstract class AudioParameter {

    public enum Type {
        PeakLevel,
        NoiseFloor,
        RMS,
        LUFS
    }

    private double value;
    private Type type;

    public AudioParameter(Type type) {
        this.type = type;
    }

    //    private Object limit;

    public double getValue() {
        return value;
    }

    public Type getType() {
        return type;
    }

    void setValue(double value) {
        this.value = value;
    }

    public abstract void calculate(InputStream data);
}
