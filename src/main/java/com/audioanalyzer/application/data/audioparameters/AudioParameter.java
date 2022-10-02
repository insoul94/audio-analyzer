package com.audioanalyzer.application.data.audioparameters;

import com.audioanalyzer.application.data.AudioFile;
import org.apache.commons.lang3.Validate;

public abstract class AudioParameter {

    private float value;

    private final AudioParameterType type;

    private final AudioFile source;

    public AudioParameter(AudioParameterType type, AudioFile source) {
        this.type = Validate.notNull(type, "AudioParameterType should not be null");
        this.source = Validate.notNull(source, "Source AufioFile should not be null");
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

    public abstract void calculate(float[] data);
}
