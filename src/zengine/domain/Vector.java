package zengine.domain;

/*
 * To represent positions on the screen or
 * velocity 
 */
public class Vector {
    private double x, y;

    public Vector(double x, double y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return String.format("Vector(x: %f, y: %f)", x, y);
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

    public void normalize() {
        x /= magnitude();
        y /= magnitude();
    }

    public void add(Vector v) {
        x += v.x;
        y += v.y;
    }

    public void subtract(Vector v) {
        x -= v.x;
        y -= v.y;
    }

    public Vector product(double scalar) {
        x *= scalar;
        y *= scalar;
        return this;
    }
}
