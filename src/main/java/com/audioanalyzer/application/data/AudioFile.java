package com.audioanalyzer.application.data;

import com.audioanalyzer.application.data.audioparameters.AudioParameter;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

//@Entity
public class AudioFile extends AbstractEntity {

//    @NotNull
//    @NotEmpty
    private static final Logger LOGGER = Logger.getLogger(AudioFile.class.getName());
    private String name = "";
    private byte[] data;
    private long size;
    private String mimeType;
    private Map<AudioParameter.Type, AudioParameter> audioParameters = new HashMap<>();

    public AudioFile(InputStream inputStream, String name, long size, String mimeType) {
        this.data = AudioFile.readFile(inputStream);
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

    public Map<AudioParameter.Type, AudioParameter> getAudioParameters() {
        return audioParameters;
    }

    public void setAudioParameters(Map<AudioParameter.Type, AudioParameter> parameters) {
        this.audioParameters = parameters;
    }

    public byte[] getData() {
        return data;
    }

    public long getSize() {
        return size;
    }

    public String getMimeType() {
        return mimeType;
    }

    public static byte[] readFile(InputStream inputStream) {
        try {
            byte[] buffer = inputStream.readAllBytes();
            inputStream.close();
            return buffer;
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Exception while reading InputStream in AudioFile: " + e.getMessage());
        }
        return null;
    }
}
