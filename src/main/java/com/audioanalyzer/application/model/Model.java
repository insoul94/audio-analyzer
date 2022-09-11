package com.audioanalyzer.application.model;

import com.audioanalyzer.application.data.AudioFile;
import com.audioanalyzer.application.data.audioparameters.AudioParameter;
import com.audioanalyzer.application.data.audioparameters.AudioParameterFactory;
import com.audioanalyzer.application.data.audioparameters.AudioParameterType;
import com.audioanalyzer.application.data.db.AudioFileService;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

@Component
public class Model {

    private final AudioFileService audioFileService = new AudioFileService();

    /**
     * Current AudioFile being just uploaded and processed or selected in UI by user.
     */
    private AudioFile currentAudioFile;

    /**
     * Previously processed AudioFiles which are stored in DB for authenticated user.
     */
    private List<AudioFile> processedAudioFiles;

    public Model() {
        loadAudioFiles();
    }


    /**
     * Load AudioFiles from DB.
     */
    public void loadAudioFiles() {
        processedAudioFiles = audioFileService.getAll();
    }

    /**
     * Delete all stored AudioFiles for deleted user.
     */
    public void deleteAudioFiles() {
        audioFileService.deleteAll();
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

    public Map<AudioParameterType, AudioParameter> getAudioParameters() {
        return currentAudioFile.getAudioParameters();
    }
}
