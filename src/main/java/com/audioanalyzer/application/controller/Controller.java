package com.audioanalyzer.application.controller;

import com.audioanalyzer.application.data.AudioParameter;
import com.audioanalyzer.application.model.Model;
import com.audioanalyzer.application.ui.views.MainView;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@org.springframework.stereotype.Controller
public class Controller {

    Model model;

    public Controller(Model model) {
        this.model = model;
    }

    public void onAudioFileUpload(InputStream inputStream, String fileName, long size, String mimeType) {
        model.processAudioFile(inputStream, fileName, size, mimeType);
        getCurrentAudioFileName();
    }

    public String getCurrentAudioFileName() {
        return model.getCurrentAudioFile().getName();
    }

    public List<AudioParameter> getAudioParameters() {
        return new ArrayList<>(model.getAudioParameters().values());
    }
}
