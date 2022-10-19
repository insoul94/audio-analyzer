package com.audioanalyzer.application.data.audiofile;

import com.audioanalyzer.application.data.helper.AudioDataHelper;
import com.audioanalyzer.application.data.audioparameter.AudioParameter;
import com.audioanalyzer.application.data.audioparameter.AudioParameterFactory;
import com.audioanalyzer.application.data.audioparameter.AudioParameterType;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class AudioFile {

    private final String name;

    // TODO: initialize as AudioInputStream
    private final AudioInputStream audioInputStream;

    private final float[] data;

    private Map<AudioParameterType, AudioParameter> audioParameters;

    public AudioFile(String name, AudioInputStream audioInputStream)
            throws UnsupportedAudioFileException, IOException {

        this.name = Objects.requireNonNull(name, "name must not be null");
        this.audioInputStream = Objects.requireNonNull(audioInputStream, "audioInputStream must not be null");
        this.data = Objects.requireNonNull(AudioDataHelper.prepareAudioData(this.audioInputStream));
    }

    public String getName() {
        return name;
    }

    public AudioInputStream getAudioInputStream() {
        return audioInputStream;
    }

    public float[] getData() {
        return data;
    }

    public Map<AudioParameterType, AudioParameter> getAudioParameters() {
        if (audioParameters == null)
            audioParameters = new HashMap<>();
        return audioParameters;
    }

    public void setAudioParameters(Map<AudioParameterType, AudioParameter> audioParameters) {
        this.audioParameters = audioParameters;
    }

    public void calculateAudioParameters() {
        setAudioParameters(AudioParameterFactory.calculateAll(getData()));
    }
}
