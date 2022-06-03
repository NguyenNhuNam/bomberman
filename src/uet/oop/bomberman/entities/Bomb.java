package uet.oop.bomberman.entities;

import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.graphics.Maps;
import uet.oop.bomberman.graphics.Sprite;
import java.util.Timer;
import java.util.TimerTask;

public class Bomb extends Entity {
    public static int numberBomb = 0;
    public Bomb(int x, int y, Image img) {
        super(x, y, img);
    }

    @Override
    public void update() {
    }
    public void change_Bomb() {
        Timer myTimer = new Timer( );
        myTimer.schedule(new TimerTask( ) {
            @Override
            public void run() {
                img = Sprite.bomb.getFxImage( );
                Timer myTimer1 = new Timer( );
                myTimer1.schedule(new TimerTask( ) {
                    @Override
                    public void run() {
                        img = Sprite.bomb_1.getFxImage( );
                        Timer myTimer1 = new Timer( );
                        myTimer1.schedule(new TimerTask( ) {
                            public void run() {
                                img = Sprite.bomb_2.getFxImage( );
                            }
                        }, 100);
                    }
                }, 100);
            }
        }, 100, 300);
    }
    public void explode(int l) {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Sound.play("res/sound/BOM_11_M.wav");
                int a = BombermanGame.list.get(0).x / 32;
                int b = BombermanGame.list.get(0).y / 32;
                BombermanGame.list.remove(0);
                Maps.map[b][a] = ' ';
                Flames.addFlames(Flames.widen, a, b);
                Timer timer1 = new Timer();
                timer1.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        for (int i = 0; i < BombermanGame.explosivesList.size(); i++) {
                            int a = BombermanGame.explosivesList.get(i).x/32;
                            int b = BombermanGame.explosivesList.get(i).y/32;
                            Brick.check(a, b);
                            if (BombermanGame.entities.get(0).x/32 == a && BombermanGame.entities.get(0).y/32 == b) {
                                BombermanGame.entities.get(0).changeBomber();
                                Bomber.die = 1;
                            }
                            for (int j = 0; j < BombermanGame.enemies.size(); j++) {
                                if (Math.abs(BombermanGame.enemies.get(j).x - a*32) < 20
                                        && Math.abs(BombermanGame.enemies.get(j).y-b*32) < 20) {
                                    BombermanGame.enemies.remove(j);
                                    j--;
                                }
                            }
                        }
                        BombermanGame.explosivesList.clear();
                    }
                }, 400);
            }
        }, l);
    }
}
