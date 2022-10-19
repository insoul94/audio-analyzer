package com.audioanalyzer.application.data.db;

import com.audioanalyzer.application.data.AudioFile;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

// Stub class
// This service will provide an access to DB which stores AudioFile IDs and their parameters.
// TODO: implement
@Service
public class AudioFileService {

    private static final Logger LOGGER = Logger.getLogger(AudioFileService.class.getName());

    public List<AudioFile> getAll() {
        return null;
    }

    public long count() {
        return 0L;
    }

    public void save(AudioFile audioFile) {
        if (audioFile == null) {
            LOGGER.log(Level.SEVERE, "AudioFile is null while saving.");
        }
    }

    public void deleteAll() {
    //TODO
    }
}
