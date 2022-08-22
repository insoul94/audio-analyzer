package com.audioanalyzer.application.model;

import com.audioanalyzer.application.data.AudioFile;
import org.springframework.stereotype.Component;

import java.io.InputStream;

@Component
public class Model {

    //TODO after connecting DB
    public void loadAudioFiles() {

    }

    //TODO after connecting DB
    public void loadAudioParameters(AudioFile file) {

    }

    public void processAudioFile(InputStream inputStream, String fileName, long size, String mimeType) {

    }

    public void calculateAudioParameters(AudioFile file) {

    }
}
