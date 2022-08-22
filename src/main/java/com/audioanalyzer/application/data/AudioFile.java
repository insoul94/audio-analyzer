package com.audioanalyzer.application.data;

import java.io.File;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

//@Entity
public class AudioFile extends AbstractEntity {

//    @NotNull
//    @NotEmpty
    private String name = "";
    private InputStream data;
    private long size;
    private String mimeType;
    private Map<AudioParameter.Type, AudioParameter> audioParameters = new HashMap<>();

    public AudioFile(InputStream file, String name) {
        this.data = file;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<AudioParameter.Type, AudioParameter> getAudioParameters() {
        return audioParameters;
    }

    public void setAudioParameters(Map<AudioParameter.Type, AudioParameter> parameters) {
        this.audioParameters = parameters;
    }

    public InputStream getData() {
        return data;
    }

    public void setData(InputStream data) {
        this.data = data;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public String getMimeType() {
        return mimeType;
    }

    public void setMimeType(String mimeType) {
        this.mimeType = mimeType;
    }
}
