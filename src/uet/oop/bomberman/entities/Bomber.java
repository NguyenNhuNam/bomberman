package uet.oop.bomberman.entities;

import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.graphics.Maps;
import uet.oop.bomberman.graphics.Sprite;

import java.util.Timer;
import java.util.TimerTask;


public class Bomber extends Entity {

    public static int check = 0;
    public static int count = 2;
    public static int die = 0;

    public Bomber(int x, int y, Image img) {
        super( x, y, img);
    }

    @Override
    public void update() {
        if (die == 1) {
            Timer timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    BombermanGame.entities.set(0, new Bomber(1, 1, Sprite.player_right.getFxImage()));
                    die = 0;
                }
            }, 400);
        }
        double a = (double)x/32 - x/32;
        double b = (double)y/32 - y/32;
        if (check == 1) {
            if (b <= 0.25 && b > 0) {y = (y/32) *32;}
            if (b >= 0.75) {y = (y/32) *32+32;}
            int _x = 0;
            if (a <= 0.125) _x = x/32-1;
            else _x = x/32;
            img = Sprite.movingSprite(Sprite.player_right, Sprite.player_right_1, Sprite.player_right_2,
                    x, 20).getFxImage();
            if (x/32 < 29 && Maps.map[y/32][_x+1] != '#' && Maps.map[y/32][_x+1] != '*' && b == (int)b)
                x += count;
        }
        if(check == 2) {
            int _x = 0;
            if (b <= 0.25 && b > 0) {y = (y/32) *32;}
            if (b >= 0.75) {y = (y/32) *32+32;}
            img = Sprite.movingSprite(Sprite.player_left, Sprite.player_left_1, Sprite.player_left_2,
                    x, 20).getFxImage();
            if (a > 0.125 || a == 0) _x = x / 32;
            else  _x = x/32-1;
            if (Maps.map[y/32][_x] != '#' && Maps.map[y/32][_x] != '*' && b == 0)
                x -= count;

        }
        if (check == 3) {
            int _y = 0;
            img = Sprite.movingSprite(Sprite.player_up, Sprite.player_up_1, Sprite.player_up_2,
                        y, 20).getFxImage();
            if (a > 0.2 && a < 0.6) x = x - 4;
            if (a > 0.6) x = x + 4;
            if (b > 0.0625) _y = y/32+1;
            else if (b== 0) _y = y/32-1;
            else _y = y/32-1;
            if (Maps.map[_y][x/32] != '#' && Maps.map[_y][x/32] != '*' && a < 0.3)
                y -= count;

        }
        if(check == 4) {
            if (a > 0.2 && a < 0.6) x = x - 4;
            if (a > 0.6) x = x + 4;
            img = Sprite.movingSprite(Sprite.player_down, Sprite.player_down_1,Sprite.player_down_2,
                        y, 20).getFxImage();
            if (y/32 < 11 && Maps.map[y/32+1][x/32] != '#' && Maps.map[y/32+1][x/32] != '*' && a < 0.3)
                y+=count;
        }
        check = 0;
    }
    public void changeBomber() {
        Timer myTimer = new Timer( );
        myTimer.schedule(new TimerTask( ) {
            @Override
            public void run() {
                img = Sprite.player_dead1.getFxImage( );
                Timer myTimer1 = new Timer( );
                myTimer1.schedule(new TimerTask( ) {
                    @Override
                    public void run() {
                        img = Sprite.player_dead2.getFxImage( );
                        Timer myTimer1 = new Timer( );
                        myTimer1.schedule(new TimerTask( ) {
                            public void run() {
                                img = Sprite.player_dead3.getFxImage( );
                            }
                        }, 150);
                    }
                }, 150);
            }
        }, 150);
    }
}
