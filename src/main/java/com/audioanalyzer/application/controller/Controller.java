package com.audioanalyzer.application.controller;

import com.audioanalyzer.application.data.audioparameter.AudioParameter;
import com.audioanalyzer.application.model.Model;

import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@org.springframework.stereotype.Controller
public class Controller {

    Model model;

    public Controller(Model model) {
        this.model = model;
    }

    public void onAudioFileUpload(String fileName, InputStream inputStream)
            throws UnsupportedAudioFileException, IOException {
        model.processAudioFile(fileName, inputStream);
        getCurrentAudioFileName();
    }

    public String getCurrentAudioFileName() {
        return model.getCurrentAudioFile().getName();
    }

    public List<AudioParameter> getAudioParameters() {
        return new ArrayList<>(model.getAudioParameters().values());
    }
}
