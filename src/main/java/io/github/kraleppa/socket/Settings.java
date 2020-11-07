package io.github.kraleppa.socket;

import lombok.ToString;

@ToString
public class Settings {
    public final int animals;
    public final int days;

    public Settings(int animals, int days) {
        this.animals = animals;
        this.days = days;
    }
}
