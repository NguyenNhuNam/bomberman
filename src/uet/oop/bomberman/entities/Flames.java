package uet.oop.bomberman.entities;

import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.graphics.Maps;
import uet.oop.bomberman.graphics.Sprite;


public class Flames extends Bomb{
    public static int widen = 0;
    public Flames(int x, int y, Image img) {
        super(x, y, img);
    }

    @Override
    public void update() {
    }
    public static void addFlames(int c, int a, int b) {
        if (c == 1) {
            Flames ex = new Flames(a, b, Sprite.bomb_exploded.getFxImage());
            BombermanGame.explosivesList.add(ex);
            if(Maps.map[b][a-1] != '#') {
                if(Maps.map[b][a-1] == '*') {
                    BombermanGame.explosivesList.add(new Flames(a - 1, b, Sprite.explosion_horizontal_left_last.getFxImage()));
                } else {
                    if(Maps.map[b][a-2] == '#') {
                        BombermanGame.explosivesList.add(new Flames(a - 1, b, Sprite.explosion_horizontal_left_last.getFxImage()));
                    } else {
                        BombermanGame.explosivesList.add(new Flames(a - 1, b, Sprite.explosion_horizontal.getFxImage()));
                        BombermanGame.explosivesList.add(new Flames(a - 2, b, Sprite.explosion_horizontal_left_last.getFxImage()));
                    }
                }
            }
            if(Maps.map[b][a+1] != '#') {
                if(Maps.map[b][a+1] == '*') {
                    BombermanGame.explosivesList.add(new Flames(a + 1, b, Sprite.explosion_horizontal_right_last.getFxImage()));
                } else {
                    if (Maps.map[b][a+2] == '#') {
                        BombermanGame.explosivesList.add(new Flames(a + 1, b, Sprite.explosion_horizontal_right_last.getFxImage()));
                    } else {
                        BombermanGame.explosivesList.add(new Flames(a + 1, b, Sprite.explosion_horizontal.getFxImage()));
                        BombermanGame.explosivesList.add(new Flames(a + 2, b, Sprite.explosion_horizontal_right_last.getFxImage()));
                    }
                }
            }
            if(Maps.map[b-1][a] != '#') {
                if(Maps.map[b-1][a] == '*') {
                    BombermanGame.explosivesList.add(new Flames(a, b - 1, Sprite.explosion_vertical_top_last.getFxImage()));
                } else {
                    if (Maps.map[b-2][a] == '#') {
                        BombermanGame.explosivesList.add(new Flames(a, b - 1, Sprite.explosion_vertical_top_last.getFxImage()));
                    } else {
                        BombermanGame.explosivesList.add(new Flames(a, b - 1, Sprite.explosion_vertical.getFxImage()));
                        BombermanGame.explosivesList.add(new Flames(a, b - 2, Sprite.explosion_vertical_top_last.getFxImage()));
                    }
                }
            }
            if(Maps.map[b+1][a] != '#') {
                if(Maps.map[b+1][a] == '*') {
                    BombermanGame.explosivesList.add(new Flames(a, b + 1, Sprite.explosion_vertical_down_last.getFxImage()));
                } else {
                    if (Maps.map[b+2][a] == '#') {
                        BombermanGame.explosivesList.add(new Flames(a, b + 1, Sprite.explosion_vertical_down_last.getFxImage()));
                    } else {
                        BombermanGame.explosivesList.add(new Flames(a, b + 1, Sprite.explosion_vertical.getFxImage()));
                        BombermanGame.explosivesList.add(new Flames(a, b + 2, Sprite.explosion_vertical_down_last.getFxImage()));
                    }
                }
            }
        } else {
            Flames ex = new Flames(a, b, Sprite.bomb_exploded.getFxImage());
            Flames r_ex = new Flames(a + 1, b, Sprite.explosion_horizontal_right_last.getFxImage());
            Flames l_ex = new Flames(a - 1, b, Sprite.explosion_horizontal_left_last.getFxImage());
            Flames u_ex = new Flames(a, b - 1, Sprite.explosion_vertical_top_last.getFxImage());
            Flames d_ex = new Flames(a, b + 1, Sprite.explosion_vertical_down_last.getFxImage());
            BombermanGame.explosivesList.add(ex);
            if (Maps.map[b][a + 1] != '#') BombermanGame.explosivesList.add(r_ex);
            if (Maps.map[b][a - 1] != '#') BombermanGame.explosivesList.add(l_ex);
            if (Maps.map[b - 1][a] != '#') BombermanGame.explosivesList.add(u_ex);
            if (Maps.map[b + 1][a] != '#') BombermanGame.explosivesList.add(d_ex);

        }
    }
}
