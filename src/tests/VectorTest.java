package tests;

import zengine.domain.Vector;

public class VectorTest {
    public static void main(String[] args) {
        Vector v1 = new Vector(3, 4);
        Vector v2 = new Vector(1, 2);

        System.out.println("VECTOR TESTS");
        TestUtils.check("Magnitude of (3, 4) is 5", v1.magnitude() == 5);
        TestUtils.check("Vectors addition (3, 4) + (1, 2) = (4, 6)",
                v1.add(v2).equals(new Vector(4, 6)));
        TestUtils.check("Vector subtract (3, 4) - (1, 2) = (2, 2)",
                v1.subtract(v2).equals(new Vector(3, 4)));
        TestUtils.check("Vector scalar product 2 * (1, 2) = (2, 4)",
                v2.product(2).equals(new Vector(2, 4)));
    }
}
