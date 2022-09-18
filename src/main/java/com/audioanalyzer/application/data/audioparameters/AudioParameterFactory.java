package com.audioanalyzer.application.data.audioparameters;

import com.audioanalyzer.application.data.AudioDataUtils;
import com.audioanalyzer.application.data.AudioFile;
import com.audioanalyzer.application.data.audioparameters.impl.LUFS;
import com.audioanalyzer.application.data.audioparameters.impl.NoiseFloor;
import com.audioanalyzer.application.data.audioparameters.impl.PeakLevel;
import com.audioanalyzer.application.data.audioparameters.impl.RMS;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class AudioParameterFactory {

    private AudioParameterFactory() {
    }

    public static Map<AudioParameterType, AudioParameter> calculateAll(AudioFile audioFile)
            throws UnsupportedAudioFileException, IOException {

        float[] audioData;
        if (audioFile != null) {
            audioData = prepareAudioData(audioFile);
        } else {
            return null;
        }

        Map<AudioParameterType, AudioParameter> parameters = new HashMap<>();

        parameters.put(AudioParameterType.RMS, new RMS());
        parameters.put(AudioParameterType.LUFS, new LUFS());
        parameters.put(AudioParameterType.NoiseFloor, new NoiseFloor());
        parameters.put(AudioParameterType.PeakLevel, new PeakLevel());

        for (AudioParameter parameter : parameters.values()) {
            parameter.calculate(audioData);
        }

        return parameters;
    }

    // Extract audio data from AudioInputStream byte array stored in AudioFile, parse Endian value into int,
    // then into relative float value (range -1.0:1.0)
    public static float[] prepareAudioData(AudioFile audioFile) throws UnsupportedAudioFileException, IOException {

        // TODO: enable MP3 support and other formats than WAV
        // Construct AudioInputStream specifically for audio data which includes file headers.
        try (AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(audioFile.getInputStream())) {

            AudioFormat format = audioInputStream.getFormat();
            boolean isBigEndian = format.isBigEndian();
            int bitrate = format.getSampleSizeInBits();
            byte[] audioDataBytes = readData(audioInputStream);

            // Sample size in bytes. (16-bit PCM - 2 bytes, 24-bit PCM - 3 bytes, etc.)
            int sampleSize = bitrate / Byte.SIZE;

            // Length of new array of full sample values has to match actual amount of samples read.
            int samplesLength = audioDataBytes.length % sampleSize == 0 ?
                    audioDataBytes.length / sampleSize :
                    audioDataBytes.length / sampleSize + 1;

            int[] samples = new int[samplesLength];

            // Array of relative sample amplitude values (from -1.0 to 1.0).
            float[] samplesRelative = new float[samplesLength];
            int maxValue = (int) Math.pow(2, bitrate);

            // Validation code:
            // java.nio.ShortBuffer shortBuffer = java.nio.ByteBuffer.wrap(audioDataBytes).order(java.nio.ByteOrder.LITTLE_ENDIAN).asShortBuffer();

            int n = 0;
            if (!isBigEndian) {
                for (int i = 0; i <= audioDataBytes.length - sampleSize; i += sampleSize) {
                    for (int j = 0; j < sampleSize; j++) {

                        // Shift byte according to Little Endian order
                        n = audioDataBytes[i + j] << j * 8;

                        // If MSB, preserve negativeness, if not MSB - use window to keep byte and zero out the rest
                        if (j != sampleSize - 1) {
                            n &= n & (0xFF << j * 8);
                        }

                        samples[i / sampleSize] |= n;
                    }

                    samplesRelative[i / sampleSize] = samples[i / sampleSize] * 1.0f / maxValue;
                }

            } else {

                // TODO: check correctness
                for (int i = 0; i <= audioDataBytes.length - sampleSize; i += sampleSize) {
                    for (int j = 0; j < sampleSize; j++) {
                        n = audioDataBytes[i + j] << (sampleSize - 1 - j) * 8;
                        samples[i / sampleSize] |= n;
                    }
                }
            }

            // TODO: add support for more channels
            if (format.getChannels() == 2) {
                samplesRelative = AudioDataUtils.stereoToMono(samplesRelative);
            }

            return samplesRelative;
        }
    }

    private static byte[] readData(InputStream is) throws IOException {

        int nRead;
        byte[] data = new byte[1024*8];

        try (ByteArrayOutputStream buffer = new ByteArrayOutputStream()){

            while ((nRead = is.read(data, 0, data.length)) != -1) {
                buffer.write(data, 0, nRead);
            }
            return buffer.toByteArray();

        }
    }
}
