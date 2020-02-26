package game;

import biuoop.GUI;
import biuoop.KeyboardSensor;
import game.Animation.AnimationRunner;
import game.Animation.StartNewGame;
import game.Animation.QuitGame;
import game.Animation.Task;
import game.Animation.Menu;
import game.Animation.ShowHiScoresTask;
import game.Animation.MenuAnimation;
import game.Animation.HighScoresAnimation;
import game.levels.LevelSpecificationReader;
import objects.score.HighScoresTable;

import java.io.LineNumberReader;
import java.io.InputStreamReader;
import java.io.File;
import java.io.InputStream;
import java.util.List;

/**
 * The type Ass 7 game.
 *
 * @author Roee Zider. The type Ass 7 game.
 */
public class Ass7Game {

    /**
     * The constant GAME.
     */
    public static final String GAME = "Arkanoid";
    /**
     * The constant WIDTH.
     */
    public static final int WIDTH = 800;
    /**
     * The constant HEIGHT.
     */
    public static final int HEIGHT = 600;
    /**
     * The constant LIVES.
     */
    public static final int LIVES = 7;

    /**
     *
     * The entry point of game.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        GUI gui = new GUI(GAME, WIDTH, HEIGHT);

        AnimationRunner runner = new AnimationRunner(gui);
        HighScoresTable table = HighScoresTable.loadFromFile(new File("highscores"));
        Menu<Task> menu = new MenuAnimation<>(new AnimationRunner(gui), gui.getKeyboardSensor(),
                GAME);
        String fileName;

        if (args.length > 0) {
            fileName = args[0];
        } else {
            fileName = "level_sets.txt";
        }
        Menu<Task> levelsMenu = new MenuAnimation<>(runner, gui.getKeyboardSensor(), "Level sets");

        readLevelSets(levelsMenu, runner, gui.getKeyboardSensor(), fileName, table);

        menu.addSubMenu("s", "Start new game", levelsMenu);
        menu.addSelection("h", "High scores", new ShowHiScoresTask(runner,
                new HighScoresAnimation(table), gui.getKeyboardSensor()));
        menu.addSelection("q", "Quit Game", new QuitGame());


        while (true) {
            runner.run(menu);
            // wait for user selection
            Task<Void> task = menu.getStatus();
            task.run();
            //check
            //((MenuAnimation<Task>) menu).reset();
        }
    }

    /**
     * Read level sets.
     * read all levels from the file
     *
     * @param menu            the menu
     * @param ar              the animation runner
     * @param ks              the keyboard sensor
     * @param file            the file
     * @param highScoresTable the high scores table
     */
    public static void readLevelSets(Menu<Task> menu, AnimationRunner ar,
                                     KeyboardSensor ks, String file, HighScoresTable highScoresTable) {
        /* try {
            LineNumberReader reader = new LineNumberReader(
                    new InputStreamReader(ClassLoader.getSystemClassLoader().getResourceAsStream(file)));
            String key = null;
            String message = null;
            String line = reader.readLine();
            while (line != null) {
                if (reader.getLineNumber() % 2 == 1) {

        */
        try (LineNumberReader reader = new LineNumberReader(
                new InputStreamReader(ClassLoader.getSystemClassLoader().getResourceAsStream(file)))) {
            String line = reader.readLine();
            String key = null;
            String message = null;
            while (line != null) {
                if (reader.getLineNumber() % 2 == 1) {
                    if (!line.contains(":")) {
                        System.out.println("The level set file is not valid!");
                        System.exit(0);
                    }

                    String[] splitted = line.split(":");
                    key = splitted[0].trim();
                    message = splitted[1].trim();
                } else {
                    menu.addSelection(key, message,
                            new StartNewGame(getLevelSet(line.trim()), ks, ar, LIVES, highScoresTable));
                }

                line = reader.readLine();
            }
        } catch (Exception e) {
            System.out.println("Could not read the level set file!");
            System.exit(0);

        }
    }

    /**
     * get level set.
     *
     * @param file the file
     * @return List of level information.
     */
    private static List<LevelInformation> getLevelSet(String file) {
        /*List<LevelInformation> l = new ArrayList<>();
        */
        InputStream in = ClassLoader.getSystemClassLoader().getResourceAsStream(file);
        List<LevelInformation> list = null;

        try {
            LevelSpecificationReader reader = new LevelSpecificationReader();
            list = reader.fromReader(new InputStreamReader(in));
        } catch (Exception e) {
            System.out.println("Could not parse this level set file");
            System.exit(0);
        }

        return list;
    }


}
