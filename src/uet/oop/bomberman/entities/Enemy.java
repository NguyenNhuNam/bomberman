package uet.oop.bomberman.entities;

import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;

public abstract class Enemy extends Entity{
    public Enemy(int x, int y, Image img) {
        super(x, y, img);
    }

    @Override
    public void update() {
        for (int i = 0; i < BombermanGame.enemies.size(); i++) {
            if (Math.abs(BombermanGame.enemies.get(i).x - BombermanGame.entities.get(0).x) < 20
                    && Math.abs(BombermanGame.enemies.get(i).y - BombermanGame.entities.get(0).y) < 20) {
                BombermanGame.entities.get(0).changeBomber();
                Bomber.die = 1;
            }
        }
    }

    public abstract void move();
}
