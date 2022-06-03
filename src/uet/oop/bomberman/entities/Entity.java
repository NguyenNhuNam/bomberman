package uet.oop.bomberman.entities;

import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import uet.oop.bomberman.graphics.Sprite;

import java.io.IOException;

public abstract class Entity {
    public int x;
    public int y;
    protected Image img;

    public Entity(int x, int y, Image img) {
        this.x = x*Sprite.SCALED_SIZE;
        this.y = y*Sprite.SCALED_SIZE;
        this.img = img;
    }


    public void render(GraphicsContext gc) {
        /*
        SnapshotParameters params = new SnapshotParameters();
        params.setFill(Color.TRANSPARENT);

        ImageView iv = new ImageView(img);
        Image base = iv.snapshot(params, null);*/
        gc.drawImage(img, x, y);
    }
    public abstract void update() throws IOException;
}
