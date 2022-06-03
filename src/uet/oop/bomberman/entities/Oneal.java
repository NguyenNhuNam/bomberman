
package uet.oop.bomberman.entities;

import javafx.scene.image.Image;
import java.util.ArrayList;
import uet.oop.bomberman.graphics.Maps;
import uet.oop.bomberman.graphics.Sprite;
import java.util.Random;

public class Oneal  extends  Enemy{
    public Oneal(int x, int y, Image img) {
        super( x, y, img);
    }
    int check_ = 0;
    public static int count = 1;
    int tt = 0;
    int flag = 0;
    int dem = 0;
    int x1 = 0;
    int y1 = 0;
    public static int a = 0;
    public static int b = 0;
    int ch = 0;
    int n = 0;
    int m = 0;

    @Override
    public void update() {
        super.update();
    }

    @Override
    public void move() {
        ArrayList so = new ArrayList();

        if(a == x/32 && Math.abs(b - y/32) <= 5 && x % 32 == 0 && y%32 == 0) {
            if(y/32 < b) {
                n = y/32;
                m = b;
            }
            else {
                n = b;
                m = y/32;
            }

            for (int i = n + 1; i < m; i++) {
                if (Maps.map[i][x/32] == '*' || Maps.map[i][x/32] == '#' || Maps.map[i][x/32] == '9') {
                    flag = 1;
                    dem = 0;
                    break;
                }
            }
            if (flag == 0) {
                dem = Math.abs(m - n) + 1;
                x1 = a;
                y1 = b;
            }
            else {
                flag = 0;
            }
        }
        else {
            if(b == y/32 && Math.abs(a - x/32) <= 5 && x % 32 == 0 && y % 32 == 0) {
                if(x/32 < a) {
                    n = x/32;
                    m = a;
                }
                else {
                    n = a;
                    m = x/32;
                }

                for (int i = n + 1; i < m; i++) {
                    if (Maps.map[y/32][i] == '*' || Maps.map[y/32][i] == '#'|| Maps.map[y/32][i] == '9') {
                        flag = 1;
                        dem = 0;
                        break;
                    }
                }
                if (flag == 0) {
                    dem = Math.abs(m - n) + 1;
                    x1 = a;
                    y1 = b;
                }
                else {
                    flag = 0;
                }
            }
        }
        if(dem != 0) {
            if(y1 == y/32 && x1 <= x/32) {
                x-= 1;
            }
            if(y1 == y/32 && x1 > x/32) {
                x+= 1;
            }
            if(x1 == x/32  && y1 <= y/32) {
                y-= 1;
            }
            if(x1 == x/32 && y1 > y/32) {
                y+= 1;
            }
            if(x%32 == 0 && y % 32 == 0) {
                dem--;
            }
        }


        else {
            /*
            if (x > 1 && Maps.map[y][x - 1] != '#' && Maps.map[y][x - 1] != '*') {
                so.add(1);
            }
            if (x < 29 && Maps.map[y][x + 1] != '#' && Maps.map[y][x + 1] != '*') {
                so.add(2);
            }
            if (y > 1 && Maps.map[y - 1][x] != '#' && Maps.map[y - 1][x] != '*') {
                so.add(3);
            }
            if (y < 11 && Maps.map[y + 1][x] != '#' && Maps.map[y + 1][x] != '*') {
                so.add(4);
            }

            int k = g.nextInt(so.size());
            ch = (int) so.get(k);
            switch (ch) {
                case 1:
                    img = Sprite.movingSprite(Sprite.oneal_left1, Sprite.oneal_left2, Sprite.oneal_left3,
                            x, 3).getFxImage();
                    x--;
                    break;
                case 2:
                    img = Sprite.movingSprite(Sprite.oneal_right1, Sprite.oneal_right2, Sprite.oneal_right3,
                            x, 3).getFxImage();
                    x++;
                    break;
                case 3:
                    img = Sprite.movingSprite(Sprite.oneal_right1, Sprite.oneal_left2, Sprite.oneal_left3,
                            x, 3).getFxImage();
                    y--;
                    break;
                case 4:
                    img = Sprite.movingSprite(Sprite.oneal_left1, Sprite.oneal_left2, Sprite.oneal_left3,
                            x, 3).getFxImage();
                    y++;
                    break;
            }

             */

            Random ran = new Random();
            if(tt == 0) {
                if( x/32 < 29 &&Maps.map[y/32][x/32 + 1] != '*' && Maps.map[y/32][x/32 + 1] != '#'
                        && Maps.map[y/32][x/32 + 1] != '9' ) {
                    so.add(1);
                }
                if(x/32 > 1 && Maps.map[y/32][x/32 -1] != '*' && Maps.map[y/32][x/32 - 1] != '#'
                        && Maps.map[y/32][x/32 - 1] != '9') {
                    so.add(2);
                }
                if(y/32 > 1 && Maps.map[y/32 -1][x/32] != '*' && Maps.map[y/32 -1][x/32] != '#'
                        && Maps.map[y/32 -1][x/32] != '9') {
                    so.add(3);
                }
                if(y/32 <29 && Maps.map[y/32 +1][x/32] != '*' && Maps.map[y/32 +1][x/32] != '#'
                        && Maps.map[y/32 +1][x/32] != '9') {
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
                    img = Sprite.movingSprite(Sprite.oneal_left1, Sprite.oneal_left2, Sprite.oneal_left3,
                            x, 10).getFxImage();
                    x += count;
                    tt -= count;
                }
                if(check_ == 2) {
                    img = Sprite.movingSprite(Sprite.oneal_right1, Sprite.oneal_right2, Sprite.oneal_right3,
                            x, 10).getFxImage();
                    x -= count;
                    tt -= count;
                }
                if(check_ == 3) {
                    img = Sprite.movingSprite(Sprite.oneal_right1, Sprite.oneal_left2, Sprite.oneal_left3,
                            y, 10).getFxImage();
                    y -= count;
                    tt -= count;
                }
                if(check_ == 4) {
                    img = Sprite.movingSprite(Sprite.oneal_left1, Sprite.oneal_left2, Sprite.oneal_left3,
                            y, 10).getFxImage();
                    y+=count;
                    tt -= count;
                }
            }
        }
    }
}
