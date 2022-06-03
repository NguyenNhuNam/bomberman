
package uet.oop.bomberman.entities;

import javafx.scene.image.Image;
import java.util.ArrayList;
import uet.oop.bomberman.graphics.Maps;
import uet.oop.bomberman.graphics.Sprite;
import java.util.Random;

public class Balloom extends Enemy{
    public static int count = 1;
    int tt = 0;
    int check_ = 0;
    public Balloom(int x, int y, Image img) {
        super( x, y, img);
    }

    @Override
    public void update() {
        super.update();
    }
    public void move() {
        ArrayList so = new ArrayList();
        Random ran = new Random();
        if(tt == 0) {
            if( x/32 < 29 &&Maps.map[y/32][x/32 + 1] != '*' && Maps.map[y/32][x/32 + 1] != '#'
                    &&Maps.map[y/32][x/32 + 1] != '9') {
                so.add(1);
            }
            if(x/32 > 1 && Maps.map[y/32][x/32 -1] != '*' && Maps.map[y/32][x/32 - 1] != '#'
                    && Maps.map[y/32][x/32 -1] != '9') {
                so.add(2);
            }
            if(y/32 > 1 && Maps.map[y/32 -1][x/32] != '*' && Maps.map[y/32 -1][x/32] != '#'
                    && Maps.map[y/32 -1][x/32] != '9') {
                so.add(3);
            }
            if(y/32 <29 && Maps.map[y/32 +1][x/32] != '*' && Maps.map[y/32 +1][x/32] != '#'
                    &&Maps.map[y/32 +1][x/32] != '9') {
                so.add(4);
            }
            if(so.size() != 0) {
                int b = ran.nextInt(so.size());
                check_ = (int)so.get(b);
                tt = 32;
            }
        }
        else {
            if(check_ == 1) {
                img = Sprite.movingSprite(Sprite.balloom_left1, Sprite.balloom_left2, Sprite.balloom_left3,
                        x, 10).getFxImage();
                x += count;
                tt -= count;
            }
            if(check_ == 2) {
                img = Sprite.movingSprite(Sprite.balloom_right1, Sprite.balloom_right2, Sprite.balloom_right3,
                        x, 10).getFxImage();
                x -= count;
                tt -= count;
            }
            if(check_ == 3) {
                img = Sprite.movingSprite(Sprite.balloom_right1, Sprite.balloom_left2, Sprite.balloom_left3,
                        y, 10).getFxImage();
                y -= count;
                tt -= count;
            }
            if(check_ == 4) {
                img = Sprite.movingSprite(Sprite.balloom_left1, Sprite.balloom_left2, Sprite.balloom_left3,
                        y, 10).getFxImage();
                y+=count;
                tt -= count;
            }
        }
    }
}
