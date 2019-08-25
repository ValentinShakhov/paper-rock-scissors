package com.imc.game.entity;

public enum RoundOutcome {
    WIN {
        @Override
        public String toString() {
            return "Player wins";
        }
    },
    TIE {
        @Override
        public String toString() {
            return "Tie";
        }
    },
    LOOSE {
        @Override
        public String toString() {
            return "Computer wins";
        }
    }
}