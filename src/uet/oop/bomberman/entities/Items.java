package uet.oop.bomberman.entities;

import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.graphics.Maps;
import uet.oop.bomberman.graphics.Sprite;

import java.io.IOException;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

public class Items extends Entity{

    public Items(int x, int y, Image img) {
        super(x, y, img);
    }

    @Override
    public void update() {
        if (BombermanGame.entities.get(0).x/32 == BombermanGame.itemsList.get(0).x/32
                && BombermanGame.entities.get(0).y/32 == BombermanGame.itemsList.get(0).y/32) {
            int a = BombermanGame.itemsList.get(0).x/32;
            int b = BombermanGame.itemsList.get(0).y/32;
            BombermanGame.itemsList.set(0,new Items(0, 0, Sprite.wall.getFxImage()));
            BombermanGame.brickList.set(0, new Brick(a, b, Sprite.grass.getFxImage()));
            Sound.play("res/sound/Item.wav");
            Bomber.count = 4;
            Timer timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    Bomber.count = 2;
                }
            }, 40000);
        }
        if (BombermanGame.entities.get(0).x/32 == BombermanGame.itemsList.get(1).x/32
                && BombermanGame.entities.get(0).y/32 == BombermanGame.itemsList.get(1).y/32) {
            int a = BombermanGame.itemsList.get(1).x/32;
            int b = BombermanGame.itemsList.get(1).y/32;
            BombermanGame.itemsList.set(1,new Items(0, 0, Sprite.wall.getFxImage()));
            BombermanGame.brickList.set(22, new Brick(a, b, Sprite.grass.getFxImage()));
            Bomb.numberBomb += 1;
            Sound.play("res/sound/Item.wav");
            if (Bomb.numberBomb > 0) {
                Timer timer = new Timer();
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        Bomb.numberBomb -= 1;
                    }
                }, 40000);
            }
        }
        if (BombermanGame.entities.get(0).x/32 == BombermanGame.itemsList.get(2).x/32
                && BombermanGame.entities.get(0).y/32 == BombermanGame.itemsList.get(2).y/32) {
            int a = BombermanGame.itemsList.get(2).x/32;
            int b = BombermanGame.itemsList.get(2).y/32;
            BombermanGame.itemsList.set(2,new Items(0, 0, Sprite.wall.getFxImage()));
            BombermanGame.brickList.set(37, new Brick(a, b, Sprite.grass.getFxImage()));
            Sound.play("res/sound/Item.wav");
            Flames.widen = 1;
            Timer timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    Flames.widen = 0;
                }
            }, 40000);
        }
    }
}
