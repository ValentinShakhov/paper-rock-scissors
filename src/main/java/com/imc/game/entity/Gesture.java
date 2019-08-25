package com.imc.game.entity;

public enum Gesture {
    PAPER("p") {
        @Override
        public String toString() {
            return "Open hand";
        }
    },
    ROCK("r") {
        @Override
        public String toString() {
            return "Fist";
        }
    },
    SCISSORS("s") {
        @Override
        public String toString() {
            return "Fingers";
        }
    },
    SUPER_GESTURE("x") {
        @Override
        public String toString() {
            return "Super gesture";
        }
    };

    private final String key;

    Gesture(final String key) {
        this.key = key;
    }

    public String getKey() {
        return this.key;
    }
}