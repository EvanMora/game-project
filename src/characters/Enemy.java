package characters;

import zengine.GamePanel;
import zengine.domain.entities.CharacterBody;

public abstract class Enemy extends CharacterBody {

    public Enemy(GamePanel gp) {
        super(gp);
    }

}