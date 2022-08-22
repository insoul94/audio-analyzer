package com.audioanalyzer.application.model;

import com.audioanalyzer.application.data.AudioFile;
import org.springframework.stereotype.Component;

import java.io.InputStream;

@Component
public class Model {

    private AudioFile currentAudioFile;

    //TODO after connecting DB
    public void loadAudioFiles() {

    }

    //TODO after connecting DB
    public void loadAudioParameters(AudioFile file) {

    }

    public void processAudioFile(InputStream inputStream, String fileName, long size, String mimeType) {
        AudioFile audioFile = new AudioFile(inputStream, fileName);
        audioFile.setSize(size);
        audioFile.setMimeType(mimeType);

        setCurrentAudioFile(audioFile);
    }

    public void calculateAudioParameters(AudioFile file) {

    }

    public AudioFile getCurrentAudioFile() {
        return currentAudioFile;
    }

    public void setCurrentAudioFile(AudioFile audioFile) {
        this.currentAudioFile = audioFile;

    }
}
