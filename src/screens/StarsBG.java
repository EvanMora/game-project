package screens;

import java.awt.Color;
import java.awt.Graphics;

import java.util.Random;

import zengine.Config;
import zengine.domain.Vector;

public class StarsBG {
    private final int numStars = 80;
    private final Vector[] stars;
    private final int[] speeds;
    private final Random rand = new Random();

    public StarsBG() {
        stars = new Vector[numStars];
        speeds = new int[numStars];

        for (int i = 0; i < numStars; i++) {
            stars[i] = new Vector(rand.nextInt(0, Config.width), rand.nextInt(0, Config.height));
            speeds[i] = 1 + rand.nextInt(2);
        }

    }

    public void updateStars() {
        for (int i = 0; i < numStars; i++) {
            stars[i].setY(stars[i].getY() + speeds[i]);
            if (stars[i].getY() > Config.height) {
                stars[i].setY(0);
                stars[i].setX(rand.nextInt(Config.height));
            }
        }
    }

    public void draw(Graphics g) {
        // g.setColor(new Color(0x6ceded));
        g.setColor(new Color(108, 237, 237, 70));

        for (Vector star : stars) {
            g.fillRect((int) star.getX(), (int) star.getY(), 2 * Config.scale, 2 * Config.scale);
        }
    }
}
