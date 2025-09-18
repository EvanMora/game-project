package objects;
import java.awt.Color;

public class NormalBullet extends Bullet {
    public NormalBullet(int x, int y) {
        super(x, y);
        this.speed = 8;
        this.width = 4;
        this.height = 6; 
        this.color = Color.WHITE;
    }

    @Override
    public void update() {
        position.setY(position.getY() - speed);
        if (position.getY() < 0) 
            active = false;
        
    }

}