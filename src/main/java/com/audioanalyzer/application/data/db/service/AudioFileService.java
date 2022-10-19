package com.audioanalyzer.application.data.db.service;

import com.audioanalyzer.application.data.audiofile.AudioFile;
import com.audioanalyzer.application.data.db.repository.AudioFileRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

// Stub class
// TODO: implement retrieval for authorized user

/**
 * This service will provide access to DB which stores AudioFile names and its calculated AudioParameters.
 * Data stored implements logging logic, thus, only Create, Read and Delete operations are supported.
 */
@Service
public class AudioFileService {

    private static final Logger LOGGER = Logger.getLogger(AudioFileService.class.getName());
    private final AudioFileRepository audioFileRepository;

    public AudioFileService(AudioFileRepository audioFileRepository) {
        this.audioFileRepository = audioFileRepository;
    }

    public List<AudioFile> getAll() {
        return audioFileRepository.findAll();
    }

    public void save(AudioFile audioFile) {
        if (audioFile == null) {
            LOGGER.log(Level.SEVERE, "AudioFile cannot be null.");
        }
    }

    public void delete(AudioFile audioFile) {
        audioFileRepository.delete(audioFile);
    }

    public void deleteAll() {
        audioFileRepository.deleteAll();
    }
}
