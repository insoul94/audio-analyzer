package com.audioanalyzer.application.data;

import com.audioanalyzer.application.data.audioparameters.AudioParameter;
import com.audioanalyzer.application.data.audioparameters.AudioParameterType;

import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class AudioFile {

    private final String name;

    // TODO: initialize as AudioInputStream
    private final InputStream inputStream;

    private final float[] data;

    private Map<AudioParameterType, AudioParameter> audioParameters = new HashMap<>();

    public AudioFile(String name, InputStream inputStream) throws UnsupportedAudioFileException, IOException {
        this.name = Objects.requireNonNull(name, "name must not be null");
        this.inputStream = Objects.requireNonNull(inputStream, "inputStream must not be null");
        this.data = Objects.requireNonNull(AudioDataHelper.prepareAudioData(this.inputStream));
    }

    public String getName() {
        return name;
    }

    public InputStream getInputStream() {
        return inputStream;
    }

    public float[] getData() {
        return data;
    }

    public Map<AudioParameterType, AudioParameter> getAudioParameters() {
        return audioParameters;
    }

    public void setAudioParameters(Map<AudioParameterType, AudioParameter> parameters) {
        this.audioParameters = parameters;
    }
}
