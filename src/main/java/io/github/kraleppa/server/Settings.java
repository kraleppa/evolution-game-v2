package io.github.kraleppa.server;

import io.github.kraleppa.util.Vector2D;
import lombok.AllArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@ToString
public class Settings {
    public final Vector2D upperRight;
    public final Vector2D jungleLowerLeft;
    public final Vector2D jungleUpperRight;
    public final int days;
    public final int animalsNumber;
    public final int movementCost;      //2
    public final int maxEnergy;       //120
    public final int plantEnergy; //40
    public final int time;
}
