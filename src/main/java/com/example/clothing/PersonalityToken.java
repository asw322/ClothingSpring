package com.example.clothing;

public class PersonalityToken {
    public String PERSONALITY_TYPE;
    public String PERSONALITY_NAME;
    public int PERSONALITY_COUNT;

    // ONLY FOR USER_PERSONALITY CLASSES
    public int PERSONALITY_STRENGTH;

    public PersonalityToken() {
        // Empty
    }

    public PersonalityToken(String _PERSONALITY_TYPE, String _PERSONALITY_NAME, int _PERSONALITY_COUNT) {
        PERSONALITY_TYPE = _PERSONALITY_TYPE;
        PERSONALITY_NAME = _PERSONALITY_NAME;
        PERSONALITY_COUNT = _PERSONALITY_COUNT;
    }
}
