package com.audioanalyzer.application.controller;

import com.audioanalyzer.application.model.Model;

import java.io.InputStream;

@org.springframework.stereotype.Controller
public class Controller {

    Model model;

    public Controller(Model model) {
        this.model = model;
    }

    public void onAudioFileUpload(InputStream inputStream, String fileName, long size, String mimeType) {
        model.processAudioFile(inputStream, fileName, size, mimeType);
    }
}
