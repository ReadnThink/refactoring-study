package org.example.refactoring.ch01;

public class Play {

    private final String name;
    private final PlayType playType;

    public Play(String name, PlayType playType) {
        this.name = name;
        this.playType = playType;
    }

    public String getName() {
        return this.name;
    }

    public PlayType getType() {
        return this.playType;
    }
}