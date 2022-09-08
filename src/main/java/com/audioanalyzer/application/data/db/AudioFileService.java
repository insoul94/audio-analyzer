package com.audioanalyzer.application.data.db;

import com.audioanalyzer.application.data.AudioFile;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

// Stub class
// TODO: create DB caching AudioParameters connected to an AudioFile
@Service
public class AudioFileService {

    private static final Logger LOGGER = Logger.getLogger(AudioFileService.class.getName());

    private final AudioFileRepository audioFileRepository;

    public AudioFileService(AudioFileRepository audioFileRepository) {
        this.audioFileRepository = audioFileRepository;
    }

    public List<AudioFile> findAll(String stringFilter) {
        if (stringFilter == null || stringFilter.isEmpty()) {
            return audioFileRepository.findAll();
        } else {
            return audioFileRepository.search(stringFilter);
        }
    }

    public long count() {
        return audioFileRepository.count();
    }

    public void save(AudioFile audioFile) {
        if (audioFile == null) {
            LOGGER.log(Level.SEVERE, "AudioFile is null while saving.");
            return;
        }
        audioFileRepository.save(audioFile);
    }

}
