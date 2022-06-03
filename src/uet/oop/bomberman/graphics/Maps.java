package uet.oop.bomberman.graphics;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Maps {
    public static char[][] map = new char[13][31];
    public static void loadmap(String s) throws IOException {
        try {
            FileReader fileReader = new FileReader(s);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            bufferedReader.readLine();
            for (int i = 0; i < 13; i++) {
                for (int j = 0; j < 31; j++) {
                    map[i][j] = (char) bufferedReader.read();
                }
                bufferedReader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
