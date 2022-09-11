package com.audioanalyzer.application.data;

import com.audioanalyzer.application.data.audioparameters.AudioParameter;
import com.audioanalyzer.application.data.audioparameters.AudioParameterType;
import com.audioanalyzer.application.data.db.AbstractEntity;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

public class AudioFile extends AbstractEntity {

    private static final Logger LOGGER = Logger.getLogger(AudioFile.class.getName());

    private String name = "";

    private InputStream data;

    private long size;

    private String mimeType;

    private Map<AudioParameterType, AudioParameter> audioParameters = new HashMap<>();

    public AudioFile(InputStream inputStream, String name, long size, String mimeType) {
        this.data = inputStream;
        this.name = name;
        this.size = size;
        this.mimeType = mimeType;
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

    public long getSize() {
        return size;
    }

    public String getMimeType() {
        return mimeType;
    }
}
