package io.github.kraleppa.util;

import com.google.gson.annotations.Expose;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class Vector2D {
    @Expose
    final public int x;
    @Expose
    final public int y;

    public boolean precedes(Vector2D other){
        return (this.x < other.x && this.y < other.y);
    }

    public boolean follows(Vector2D other){
        return (this.x > other.x && this.y > other.y);
    }

    public Vector2D upperRight(Vector2D other){
        return new Vector2D(Math.max(this.x, other.x), Math.max(this.y, other.y));
    }

    public Vector2D lowerLeft(Vector2D other){
        return new Vector2D(Math.min(this.x, other.x), Math.min(this.y, other.y));
    }

    public Vector2D add(Vector2D other){
        return new Vector2D(this.x + other.x, this.y + other.y);
    }

    public Vector2D subtract(Vector2D other){
        return new Vector2D(this.x - other.x, this.y - other.y);
    }

    public Vector2D opposite(){
        return new Vector2D((-1) * this.x, (-1) * this.y);
    }


}
