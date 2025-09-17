package characters;

import java.awt.Graphics;
import javax.swing.ImageIcon;
import main.GamePanel;
import util.Block;
import util.Vector;

public class BasicEnemy extends CharacterBody {

    int speed = 3;

    GamePanel gp;

    public BasicEnemy(GamePanel gp) {
        this.health = 1;
        this.position = new Vector(32, 32);
        this.collider = new Block(32, 64);
        this.sprite = new ImageIcon("assets/ship.png").getImage();
        this.gp = gp;
    }

    boolean ok = true;
    @Override
    public void update(){
        if (position.getX() <= (gp.width - collider.getWidth()) && ok){
            position.setX(position.getX() + speed);
            if(position.getX() + speed >= (gp.width - collider.getWidth())){
                ok = false;
            }
        }else if (position.getX() >= 0 && ok == false){
            position.setX(position.getX() - speed);
            if(position.getX() - speed <= 0){
                ok = true;
            }
        }
    }
    @Override
    public void draw(Graphics g){
        g.drawImage(
                getSprite(),
                getPosition().getX(),
                getPosition().getY(),
                null);
    }
    @Override
    public void attack(){}
}   