package io.github.kraleppa.server;

import com.google.gson.annotations.Expose;
import io.github.kraleppa.map.Field;

import java.util.Collection;


public class JSON {
    @Expose
    public final Collection<Field> fields;
    @Expose
    public final int day;
    @Expose
    public final int animals;

    public JSON(Collection<Field> fields, int day, int animals) {
        this.fields = fields;
        this.day = day;
        this.animals = animals;
    }
}
