package zengine.controller;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import zengine.domain.entities.Entity;

public class EntityManager {
    List<Entity> entities = new ArrayList<>();

    public void add(Entity entity) {
        entities.add(entity);
    }

    public void update() {
        for (int i = 0; i < entities.size(); i++) {
            if (!entities.get(i).isActive()) {
                entities.remove(i);
                i--;
                continue;
            }
            entities.get(i).update();
        }
    }

    public void checkCollisions() {
        for (int i = 0; i < entities.size(); i++) {
            Entity a = entities.get(i);
            for (int j = i + 1; j < entities.size(); j++) {
                Entity b = entities.get(j);
                if (a.getBounds().intersects(b.getBounds())) {
                    a.onCollision(b);
                    b.onCollision(a);
                }
            }
        }

    }

    public void drawAll(Graphics g) {
        for (Entity entity : entities)
            entity.draw(g);
    }

    public List<Entity> getEntities() {
        return entities;
    }
}
