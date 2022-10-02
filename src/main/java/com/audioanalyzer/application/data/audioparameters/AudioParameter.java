package com.audioanalyzer.application.data.audioparameters;

import com.audioanalyzer.application.data.AudioFile;

import java.util.Objects;

public abstract class AudioParameter {

    private float value;

    private final AudioParameterType type;

    private final AudioFile source;

    public AudioParameter(AudioParameterType type, AudioFile source) {
        this.type = Objects.requireNonNull(type, "type must not be null");
        this.source = Objects.requireNonNull(source, "source must not be null");
        this.value = calculate(source.getData());
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

    public final AudioFile getSource() {
        return source;
    }

    public abstract float calculate(float[] data);
}
