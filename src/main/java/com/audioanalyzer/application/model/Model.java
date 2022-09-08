package com.audioanalyzer.application.model;

import com.audioanalyzer.application.data.AudioFile;
import com.audioanalyzer.application.data.audioparameters.AudioParameter;
import com.audioanalyzer.application.data.audioparameters.AudioParameterFactory;
import com.audioanalyzer.application.data.db.AudioFileService;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.util.Map;

@Component
public class Model {

    private AudioFile currentAudioFile;
    private final AudioFileService audioFileService = new AudioFileService();

    //TODO after connecting DB
    public void loadAudioFiles() {

    }

    public void processAudioFile(InputStream inputStream, String fileName, long size, String mimeType) {
        AudioFile audioFile = new AudioFile(inputStream, fileName, size, mimeType);

        setCurrentAudioFile(audioFile);
        audioFile.setAudioParameters(AudioParameterFactory.calculateAll(audioFile));
    }

    public AudioFile getCurrentAudioFile() {
        return currentAudioFile;
    }

    public void setCurrentAudioFile(AudioFile audioFile) {
        this.currentAudioFile = audioFile;
    }

    public Map<AudioParameter.Type, AudioParameter> getAudioParameters() {
        return currentAudioFile.getAudioParameters();
    }
}
