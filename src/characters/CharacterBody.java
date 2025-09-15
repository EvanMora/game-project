package characters;

import java.awt.Image;

import util.Block;
import util.Vector;

public abstract class CharacterBody {
    int health;
    Vector position;
    // Vector velocity;
    Block collider;
    Image sprite;  
    
    public abstract void handleMove();
    public abstract void attack();

    protected void die() {
        health = 0;
    }
}
