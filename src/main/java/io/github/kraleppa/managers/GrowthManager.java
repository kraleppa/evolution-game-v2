package io.github.kraleppa.managers;

import io.github.kraleppa.map.Field;
import io.github.kraleppa.util.Vector2D;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;


public class GrowthManager {
    private final List<Field> emptyFieldsSteppe = new ArrayList<>();
    private final List<Field> emptyFieldsJungle = new ArrayList<>();
    private final Vector2D jungleLowerLeft;
    private final Vector2D jungleUpperRight;
    private final Random random = new Random();

    public GrowthManager(Vector2D jungleLowerLeft, Vector2D jungleUpperRight) {
        this.jungleLowerLeft = jungleLowerLeft;
        this.jungleUpperRight = jungleUpperRight;
    }

    public void addFields(Collection<Field> fields){
        for (Field field : fields){
            if (isFieldInJungle(field)){
                emptyFieldsJungle.add(field);
            } else {
                emptyFieldsSteppe.add(field);
            }
        }
    }

    public boolean isFieldInJungle(Field field){
        return field.vector2D.follows(jungleLowerLeft) && field.vector2D.precedes(jungleUpperRight);
    }

    public void addIfEmpty(Field field){
        if (field.getAnimals().size() == 0 && !field.isGrassed()){
            if (isFieldInJungle(field)){
                emptyFieldsJungle.add(field);
            } else {
                emptyFieldsSteppe.add(field);
            }
        }
    }

    public void removeFromEmpty(Field field){
        if (isFieldInJungle(field)){
            emptyFieldsJungle.remove(field);
        } else {
            emptyFieldsSteppe.remove(field);
        }
    }

    public void plantInJungle(){
        if (emptyFieldsJungle.size() == 0){
            return;
        }
        Field field = emptyFieldsJungle.remove(random.nextInt(emptyFieldsJungle.size()));
        field.addGrass();
    }

    public void plantInSteppe(){
        if (emptyFieldsSteppe.size() == 0){
            return;
        }
        Field field = emptyFieldsSteppe.remove(random.nextInt(emptyFieldsSteppe.size()));
        field.addGrass();
    }
}
