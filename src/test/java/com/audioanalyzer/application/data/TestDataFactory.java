package com.audioanalyzer.application.data;

public class TestDataFactory {

    public static final int DEFAULT_ARRAY_LENGTH = 100000;
    public static final float[] SINE_SIGNAL;


    static {
        SINE_SIGNAL = populateFloats(0f, 7071f, 10000f, 7071f, 0f, -7071f, -10000f, -7071f);
    }


    public static float[] populateFloats(float... values) {
        if (values == null) {
            values = new float[]{0f};
        }
        float[] arr = new float[DEFAULT_ARRAY_LENGTH];
        for (int i = 0; i < arr.length; i++) {
            // Populate values onto arr consecutively.
            int idx = arr.length % values.length;
            arr[i] = values[idx];
        }
        return arr;
    }
}
