package com.audioanalyzer.application.data.audioparameter.impl;
import com.audioanalyzer.application.data.TestDataFactory;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RMSTest {

    @Test
    void Should_ReturnConstant_When_DCSignalPassed() {
        float constant = 1000f;
        float[] input = TestDataFactory.populateFloats(constant);

        float result = new RMS().calculate(input);

        assertEquals(constant, result);
    }

    @Test
    void Should_ReturnZero_When_SineSignalPassed() {
        float[] input = TestDataFactory.SINE_SIGNAL;

        float result = new RMS().calculate(input);

        assertEquals(0f, result);
    }

    @Test
    void Should_ReturnMaxFloat_When_DCMaxPassed() {
        float[] input = TestDataFactory.populateFloats(Float.MAX_VALUE);

        float result = new RMS().calculate(input);

        assertEquals(Float.MAX_VALUE, result);
    }

    @Test
    void Should_ReturnMinFloat_When_DCMinPassed() {
        float[] input = TestDataFactory.populateFloats(Float.MAX_VALUE);

        float result = new RMS().calculate(input);

        assertEquals(Float.MAX_VALUE, result);
    }
}