package com.audioanalyzer.application.data;

import com.audioanalyzer.application.data.audioparameters.AudioParameter;
import com.audioanalyzer.application.data.audioparameters.AudioParameterType;
import com.audioanalyzer.application.data.db.AbstractEntity;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class AudioFile extends AbstractEntity {

    private String name = "";

    private InputStream data;

    private Map<AudioParameterType, AudioParameter> audioParameters = new HashMap<>();

    public AudioFile(String name, InputStream inputStream) {
        this.name = name;
        this.data = inputStream;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<AudioParameterType, AudioParameter> getAudioParameters() {
        return audioParameters;
    }

    public void setAudioParameters(Map<AudioParameterType, AudioParameter> parameters) {
        this.audioParameters = parameters;
    }

    public InputStream getInputStream() {
        return data;
    }
}
