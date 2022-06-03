package uet.oop.bomberman;

import javafx.animation.*;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import uet.oop.bomberman.entities.*;
import uet.oop.bomberman.graphics.Maps;
import uet.oop.bomberman.graphics.Sprite;
import java.io.IOException;
import java.util.*;

public class BombermanGame extends Application {

    public static final int WIDTH = 31;
    public static final int HEIGHT = 13;
    private int lv = 1;
    
    private GraphicsContext gc;
    private Canvas canvas;
    public static List<Bomber> entities = new ArrayList<>();
    private List<Entity> stillObjects = new ArrayList<>();
    public static List<Bomb> list = new ArrayList<>();
    public static List<Flames> explosivesList = new ArrayList<>();
    public static List<Brick> brickList = new ArrayList<>();
    public static List<Items> itemsList = new ArrayList<>();
    public static List<Enemy> enemies = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        Application.launch(BombermanGame.class);
    }

    @Override
    public void start(Stage stage) throws IOException {
        Maps.loadmap("res/levels/Level1.txt");
        // Tao Canvas
        Sound.startGame();
        canvas = new Canvas(Sprite.SCALED_SIZE * WIDTH, Sprite.SCALED_SIZE * HEIGHT);
        gc = canvas.getGraphicsContext2D();
        // Tao root container
        Group root = new Group();
        root.getChildren().add(canvas);
        Scene scene = new Scene(root);

        Image image = new Image(getClass().getResourceAsStream("/textures/start2.png"));
        ImageView imageView = new ImageView(image);
        imageView.setX(0);
        imageView.setY(0);
        imageView.setFitHeight(400);
        imageView.setFitWidth(600);
        Group root1 = new Group();
        root1.getChildren().add(imageView);
        Scene scene1 = new Scene(root1);

        Image image1 = new Image(getClass().getResourceAsStream("/textures/lose.png"));
        ImageView imageView1 = new ImageView(image1);
        imageView1.setX(0);
        imageView1.setY(0);
        imageView1.setFitHeight(400);
        imageView1.setFitWidth(500);
        Group root2 = new Group();
        root2.getChildren().add(imageView1);
        Scene scene2 = new Scene(root2);
        scene2.setOnKeyPressed(event -> {
            if (event.getCode().equals(KeyCode.ENTER)) {
                stage.setScene(scene);
                stage.show();
            }
        });

        scene1.setOnKeyPressed(event -> {
            if (event.getCode().equals(KeyCode.ENTER)) {
                stage.setScene(scene);
                stage.show();
            }
        });
        // Them scene vao stage
        scene.setOnKeyPressed(event -> {
            if (event.getCode().equals(KeyCode.D)) {
                Bomber.check = 1;
            }
            if (event.getCode().equals(KeyCode.A)) {
                Bomber.check = 2;
            }
            if (event.getCode().equals(KeyCode.W)) {
                Bomber.check = 3;
            }
            if (event.getCode().equals(KeyCode.S)) {
                Bomber.check = 4;
            }
            if (event.getCode().equals(KeyCode.SPACE) && list.size() <= Bomb.numberBomb) {
                Sound.play("res/sound/BOM_SET.wav");
                Bomb bomb = new Bomb(entities.get(0).x/32, entities.get(0).y/32, Sprite.bomb_1.getFxImage());
                list.add(bomb);
                Maps.map[bomb.y/32][bomb.x/32] = '9';
                bomb.change_Bomb();
                bomb.explode(3000);
            }
        });
        stage.setScene(scene1);
        stage.show();

        createMap();
        Bomber bomberman = new Bomber(1, 1, Sprite.player_right.getFxImage());
        entities.add(bomberman);
        render();

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                if (Bomber.die == 1) {
                    stage.setScene(scene2);
                    stage.show();
                }
                float a_ = (float)entities.get(0).x;
                Oneal.a = Math.round((float)(a_/32));
                float b_ = (float)entities.get(0).y;
                Oneal.b = Math.round((float)(b_/32));
                if (BombermanGame.entities.get(0).x/32 == BombermanGame.itemsList.get(3).x/32
                        && BombermanGame.entities.get(0).y/32 == BombermanGame.itemsList.get(3).y/32) {
                    if (BombermanGame.enemies.isEmpty() && lv == 1) {
                        try {
                            Maps.loadmap("res/levels/Level2.txt");
                            entities.set(0, new Bomber(1, 1, Sprite.player_right.getFxImage()));
                            Sound.play("res/sound/CRYST_UP.wav");
                            createMap();
                            lv = 2;
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    if (BombermanGame.enemies.isEmpty() && lv == 2) {
                        try {
                            Group group = new Group();
                            group.getChildren().add(new ImageView(new Image(getClass().getResourceAsStream("/texture/win.png"))));
                            Scene scene3 = new Scene(group);
                            stage.setScene(scene3);
                            stage.show();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
                update();
                render();
            }
        };
        timer.start();

        Timeline timeline = new Timeline();
        timeline.getKeyFrames().add(new KeyFrame(Duration.millis(40), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                enemies.forEach(Enemy::move);
            }
        }));
        timeline.setCycleCount(Animation.INDEFINITE);
        //timeline.setAutoReverse(true);
        timeline.play();

    }

    public void createMap() {
        for (int i = 0; i < 13; i++) {
            for (int j = 0; j < 31; j++) {
                if (Maps.map[i][j] == '3') {
                    Kondoria kondoria = new Kondoria(j, i, Sprite.kondoria_right1.getFxImage());
                    enemies.add(kondoria);
                }
                if (Maps.map[i][j] == '2') {
                    Oneal one = new Oneal(j, i, Sprite.oneal_right1.getFxImage());
                    enemies.add(one);
                }
                if(Maps.map[i][j] == '1') {
                    Balloom balloomer = new Balloom(j,i,Sprite.balloom_left1.getFxImage());
                    enemies.add(balloomer);
                }
                if (Maps.map[i][j] == '#') {
                    stillObjects.add(new Wall(j, i, Sprite.wall.getFxImage()));
                } else if (Maps.map[i][j] == '*') {
                    stillObjects.add(new Grass(j, i, Sprite.grass.getFxImage()));
                    brickList.add(new Brick(j, i, Sprite.brick.getFxImage()));
                }
                else {
                    stillObjects.add(new Grass(j, i, Sprite.grass.getFxImage()));
                }
            }
        }

        itemsList.add(new Items(brickList.get(0).x/32, brickList.get(0).y/32, Sprite.powerup_speed.getFxImage()));
        itemsList.add(new Items(brickList.get(22).x/32, brickList.get(22).y/32, Sprite.powerup_bombs.getFxImage()));
        itemsList.add(new Items(brickList.get(37).x/32, brickList.get(37).y/32, Sprite.powerup_flames.getFxImage()));
        itemsList.add(new Items(29, 11, Sprite.portal.getFxImage()));
    }

    public void update(){
        if (!entities.isEmpty()) entities.forEach(Bomber::update);
        list.forEach(Bomb::update);
        if (!itemsList.isEmpty()) itemsList.forEach(Items::update);
        if (!enemies.isEmpty()) enemies.forEach(Enemy::update);
    }

    public void render() {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        stillObjects.forEach(g -> g.render(gc));
        itemsList.forEach(g -> g.render(gc));
        if (!brickList.isEmpty()) brickList.forEach(g -> g.render(gc));
        if (!list.isEmpty()) list.forEach(g -> g.render(gc));
        enemies.forEach(g -> g.render(gc));
        if (!explosivesList.isEmpty()) explosivesList.forEach(g -> g.render(gc));
        if (!entities.isEmpty()) entities.forEach(g -> g.render(gc));
    }
}
