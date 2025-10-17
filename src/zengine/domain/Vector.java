package zengine.domain;

/*
 * To represent positions on the screen or
 * velocity 
 */
public class Vector extends Object{
    private double x, y;

    public Vector(double x, double y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return String.format("Vector(x: %f, y: %f)", x, y);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        if (this == obj) {
            return true;
        }
        
        if (getClass() != obj.getClass()) {
            return false;
        }

        Vector v = (Vector) obj;
        return this.x == v.x && this.y == v.y;
    }

    public void set(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double magnitude() {
        return Math.sqrt(x*x + y*y);
    }

    public Vector normalize() {
        x /= magnitude();
        y /= magnitude();
        return this;
    }

    public Vector add(Vector v) {
        x += v.x;
        y += v.y;
        return this;
    }

    public Vector subtract(Vector v) {
        x -= v.x;
        y -= v.y;
        return this;
    }

    public Vector product(double scalar) {
        x *= scalar;
        y *= scalar;
        return this;
    }

    public static Vector directionTo(double angle) {
        angle *= Math.PI/180;
        return new Vector(Math.cos(angle), -Math.sin(angle));
    }
}
