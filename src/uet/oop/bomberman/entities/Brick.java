package uet.oop.bomberman.entities;

import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.graphics.Maps;
import uet.oop.bomberman.graphics.Sprite;

import java.util.Timer;
import java.util.TimerTask;

public class Brick extends Entity {

    public Brick(int x, int y, Image img) {
        super(x, y, img);
    }

    @Override
    public void update() {
    }
    public void changeBrick() {
        Timer myTimer = new Timer( );
        myTimer.schedule(new TimerTask( ) {
            @Override
            public void run() {
                img = Sprite.brick_exploded.getFxImage( );
                Timer myTimer1 = new Timer( );
                myTimer1.schedule(new TimerTask( ) {
                    @Override
                    public void run() {
                        img = Sprite.brick_exploded1.getFxImage( );
                        Timer myTimer1 = new Timer( );
                        myTimer1.schedule(new TimerTask( ) {
                            public void run() {
                                img = Sprite.brick_exploded2.getFxImage( );
                            }
                        }, 100);
                    }
                }, 100);
            }
        }, 100, 100);
    }
    public static void check(int a, int b) {
        for (int j = 0; j < BombermanGame.brickList.size(); j++) {
            if (BombermanGame.brickList.get(j).x/32 == a && BombermanGame.brickList.get(j).y/32 == b && Maps.map[b][a] == '*') {
                BombermanGame.brickList.get(j).changeBrick();
                Timer timer2 = new Timer();
                int finalJ = j;
                timer2.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        Maps.map[b][a] = ' ';
                        if (finalJ == 0) {
                            BombermanGame.brickList.set(finalJ, new Brick(a, b, Sprite.powerup_speed.getFxImage()));
                        } else if (finalJ == 22) {
                            BombermanGame.brickList.set(finalJ, new Brick(a, b, Sprite.powerup_bombs.getFxImage()));
                        } else if (finalJ == 37) {
                            BombermanGame.brickList.set(finalJ, new Brick(a, b, Sprite.powerup_flames.getFxImage()));
                        }
                        else {
                            BombermanGame.brickList.set(finalJ, new Brick(a, b, Sprite.grass.getFxImage()));
                        }
                    }
                }, 400);
            }
        }
    }
}
