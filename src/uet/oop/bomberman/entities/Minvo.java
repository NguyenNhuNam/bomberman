package uet.oop.bomberman.entities;

import javafx.scene.image.Image;
import uet.oop.bomberman.graphics.Maps;

public class Minvo extends Enemy{

    @Override
    public void update() {
        super.update();
    }

    public Minvo(int x, int y, Image img) {
        super(x, y, img);
    }

    @Override
    public void move() {

    }

    public int check() {
        int [][]brr = new int[403][];
        for(int i = 0; i < 403; i++) {
            brr[i] = new int[403];
        }
        for(int i = 0; i < 403; i++) {
            for(int j = 0;j < 403; j++) {
                brr[i][j] = 1000;
            }
        }
        for(int i = 1; i < 12; i++) {
            for(int j = 1; j < 30; j++) {
                if(Maps.map[i][j] != '#' && Maps.map[i][j] != '*') {
                    if(Maps.map[i-1][j] != '#' && Maps.map[i-1][j] != '*'){
                        brr[i][i] = 1;
                        brr[x + 31][x] = 1;
                    }
                    if(Maps.map[i+1][j] != '#' && Maps.map[i+1][j] != '*') {
                        brr[i][i+29] = 1;
                        brr[i+29][i] = 1;
                    }
                    if(Maps.map[i][j-1] != '#' && Maps.map[i][j-1] != '*') {
                        brr[i][j - 1] = 1;
                        brr[j-1][i] = 1;
                    }
                    if(Maps.map[i][j+1] != '#' && Maps.map[i][j+1] != '*') {
                        brr[i][j+1] = 1;
                        brr[j+1][i] = 1;
                    }
                }
            }
        }
        return 0;
    }
}
