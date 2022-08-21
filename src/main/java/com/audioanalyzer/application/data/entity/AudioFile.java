package com.audioanalyzer.application.data.entity;

import com.audioanalyzer.application.data.AbstractEntity;
import com.audioanalyzer.application.data.AudioParameter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.Map;

//@Entity
public class AudioFile extends AbstractEntity {

//    @NotNull
//    @NotEmpty
    private String name = "";


    private Map<AudioParameter.Type, AudioParameter> audioParameters = new HashMap<>();

    public AudioFile() {
    }

    public AudioFile(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<AudioParameter.Type, AudioParameter> getAudioParameters() {
        return audioParameters;
    }

    public void setAudioParameters(Map<AudioParameter.Type, AudioParameter> parameters) {
        this.audioParameters = parameters;
    }
}
