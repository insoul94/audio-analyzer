package com.audioanalyzer.application.model;

import com.audioanalyzer.application.data.audiofile.AudioFile;
import com.audioanalyzer.application.data.audioparameter.AudioParameter;
import com.audioanalyzer.application.data.audioparameter.AudioParameterType;
import com.audioanalyzer.application.data.db.service.AudioFileService;
import org.springframework.stereotype.Component;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

@Component
public class Model {

    private final AudioFileService audioFileService;

    /**
     * Current AudioFile being just uploaded and processed or selected in UI by user.
     */
    private AudioFile currentAudioFile;

    /**
     * History of  processed AudioFiles which are stored in DB for authenticated user.
     */
    private List<AudioFile> processedAudioFiles;

    public Model(AudioFileService audioFileService) {
        this.audioFileService = audioFileService;
    }

    /**
     * Load history processed AudioFiles from DB.
     */
    public void loadAudioFiles() {
        processedAudioFiles = audioFileService.getAll();
    }

    public void deleteAudioFile(AudioFile audioFile) {
        audioFileService.delete(audioFile);
    }

    /**
     * Delete all stored AudioFiles for deleted user.
     */
    public void deleteAudioFiles() {
        audioFileService.deleteAll();
    }

    public void processAudioFile(String fileName, InputStream inputStream)
            throws UnsupportedAudioFileException, IOException {
        AudioFile audioFile = new AudioFile(fileName, AudioSystem.getAudioInputStream(inputStream));
        setCurrentAudioFile(audioFile);
        audioFile.calculateAudioParameters();
    }

    public AudioFile getCurrentAudioFile() {
        return currentAudioFile;
    }

    public void setCurrentAudioFile(AudioFile audioFile) {
        this.currentAudioFile = audioFile;
    }

    public Map<AudioParameterType, AudioParameter> getAudioParameters() {
        return currentAudioFile.getAudioParameters();
    }
}
