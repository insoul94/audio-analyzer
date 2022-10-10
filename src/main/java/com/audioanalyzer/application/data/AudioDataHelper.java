package com.audioanalyzer.application.data;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AudioDataHelper {

    private static final Logger LOGGER = Logger.getLogger(AudioDataHelper.class.getName());

    private AudioDataHelper() {}

    public static float[] stereoToMono(float[] stereoArray) {
        float[] monoArray = new float[stereoArray.length / 2];
        for (int i = 0; i < monoArray.length; i += 2) {
            monoArray[i / 2] = (stereoArray[i] + stereoArray[i + 1]) / 2;
        }
        return monoArray;
    }

    public static double linearToDecibel(double linear) {
        if (linear < 0)
            LOGGER.log(Level.WARNING, "linear value is negative :: linearToDecibel(): " + linear);
        return (20 * Math.log10(Math.abs(linear)));
    }

    public static byte[] readData(InputStream is) throws IOException {
        int nRead;
        byte[] data = new byte[1024*8];

        try (ByteArrayOutputStream buffer = new ByteArrayOutputStream()){
            while ((nRead = is.read(data, 0, data.length)) != -1) {
                buffer.write(data, 0, nRead);
            }
            return buffer.toByteArray();
        }
    }

    // Extract audio data from AudioInputStream byte array stored in AudioFile, parse Endian value into int,
    // then into relative float value (range -1.0:1.0)
    public static float[] prepareAudioData(AudioInputStream audioInputStream) throws IOException {
        AudioFormat format = audioInputStream.getFormat();
        boolean isBigEndian = format.isBigEndian();
        int bitrate = format.getSampleSizeInBits();
        byte[] audioDataBytes = readData(audioInputStream);

        // Single sample size in bytes. (16-bit PCM - 2 bytes, 24-bit PCM - 3 bytes, etc.)
        int ss = bitrate / Byte.SIZE;
        // Length of new array of full sample values has to match actual amount of samples read.
        // This is to protect from erroneous odd length of bytes array.
        int samplesLength = audioDataBytes.length % ss == 0 ?
                audioDataBytes.length / ss :
                audioDataBytes.length / ss + 1;
        int[] samples = new int[samplesLength];
        // Array of relative amplitude values (from -1.0 to 1.0).
        float[] samplesRelative = new float[samplesLength];
        // Has to be divided by 2 to take into account zero-axis shift: maxValue = [-max/2 : +max/2]
        int maxValue = (int) (Math.pow(2, bitrate) / 2);

        int b = 0;
        for (int i = 0; i <= audioDataBytes.length - ss; i += ss) {
            for (int j = 0; j < ss; j++) {
                if (!isBigEndian) {
                    // Shift byte to its original place according to Little Endian order
                    b = (int) audioDataBytes[i + j] << j * 8;
                    // If MSB, preserve negativeness, if not MSB - use window to keep byte and dump the rest
                    if (j != ss - 1) {
                        b &= getByteWindow(j);
                    }
                } else {
                    b = (int) audioDataBytes[i + j] << (ss - 1 - j) * 8;
                    if (j != 0) {
                        b &= getByteWindow(3 - j);
                    }
                }
                // Append byte to a resulting integer
                samples[i / ss] |= b;
            }
            samplesRelative[i / ss] = samples[i / ss] * 1.0f / maxValue;
        }

        if (format.getChannels() == 2) {
            samplesRelative = stereoToMono(samplesRelative);
        }

        return samplesRelative;
    }

    private static int getByteWindow(int pos) {
        switch (pos) {
            case 0 -> {return 0x000000FF;}
            case 1 -> {return 0x0000FF00;}
            case 2 -> {return 0x00FF0000;}
            case 3 -> {return 0xFF000000;}
            default -> {throw new IllegalArgumentException("Byte position inside integer has to be in bounds [0:3]");}
        }
    }
}