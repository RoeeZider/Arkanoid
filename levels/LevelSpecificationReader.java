package game.levels;

import game.LevelInformation;
import geometry.Velocity;
import objects.Block;
import objects.sprite.Sprite;

import javax.imageio.ImageIO;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * The type Level specification reader.
 */
public class LevelSpecificationReader {

    /**
     * From reader list.
     *
     * @param reader the reader
     * @return the list
     */
    public List<LevelInformation> fromReader(java.io.Reader reader) {

        List<LevelInformation> list = new ArrayList<>();
        BufferedReader bufferedReader = new BufferedReader(reader);
        List<String> level;

        level = readLevels(bufferedReader);
        while (level.size() != 0) {
            LevelInformation levelInformation = getPropertiesOfLevel(level);
            list.add(levelInformation);
            level = readLevels(bufferedReader);
        }

        return list;
    }

    /**
     * Read levels list.
     *
     * @param bufferedReader the buffered reader
     * @return the list
     */
    public List<String> readLevels(BufferedReader bufferedReader) {
        /* List<String> level = new ArrayList<>();
        try {
            String lineOfLevel = bufferedReader.readLine().trim();
            while (lineOfLevel != null) {
                if (lineOfLevel.equals("START_LEVEL")) {
                    break;
                }

                //change- dont stop?
                if (lineOfLevel.equals("END_LEVEL")) {
                    return level;
                }
                level.add(lineOfLevel);
                lineOfLevel = bufferedReader.readLine().trim();
            }
        } catch (IOException e) {
            e.printStackTrace();
            return level;
        } */
        List<String> lines = new ArrayList<>();
        try {
            String line = bufferedReader.readLine();
            while (line != null) {
                if (line.trim().equals("START_LEVEL")) {
                    break;
                }
                line = bufferedReader.readLine();
            }
            line = bufferedReader.readLine();
            while (line != null) {
                line = line.trim();

                if (line.equals("END_LEVEL")) {
                    break;
                }

                if (!line.isEmpty()) {
                    lines.add(line);
                }
                line = bufferedReader.readLine();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.exit(0);
        }
        return lines;

    }

    /**
     * Gets properties of level.
     *
     * @param level the level
     * @return the properties of level
     */
    public LevelInformation getPropertiesOfLevel(List<String> level) {
        Map<String, String> levelMap = new TreeMap<>();
        boolean flag = false;

        List<String> blocks = new ArrayList<>();
        for (int i = 0; i < level.size(); i++) {
            if (level.get(i).equals("START_BLOCKS")) {
                for (int j = i + 1; j < level.size() - 1; j++) {
                    blocks.add(level.get(j));
                    flag = true;
                }
            }
            if (!flag) {
                String[] splitLine = level.get(i).split(":");
                levelMap.put(splitLine[0].trim(), splitLine[1].trim());
            }
        }
        String levelName = levelMap.get("level_name").trim();
        Sprite background = getBackground(levelMap.get("background").trim());
        List<Velocity> velocities = splitVelocity(levelMap.get("ball_velocities").trim());
        int paddleSpeed = Integer.parseInt(levelMap.get("paddle_speed").trim());
        int paddleWidth = Integer.parseInt(levelMap.get("paddle_width").trim());
        List<Block> parseBlocks = readBlocks(blocks, levelMap);

        return new Level(levelName, background, velocities, paddleSpeed, paddleWidth, parseBlocks);

    }

    /**
     * Read blocks list.
     *
     * @param blocksLines the blocks lines
     * @param levelMap    the level map
     * @return the list
     */
    public List<Block> readBlocks(List<String> blocksLines, Map<String, String> levelMap) {
        List<Block> blocks = new ArrayList<>();
        int xpos = Integer.parseInt(levelMap.get("blocks_start_x").trim());
        int ypos = Integer.parseInt(levelMap.get("blocks_start_y").trim());
        int rowHeight = Integer.parseInt(levelMap.get("row_height").trim());
        BlocksFromSymbolsFactory blockFactory = null;
        try {
            URL url = ClassLoader.getSystemClassLoader().getResource(levelMap.get("block_definitions").trim());
            if (url == null) {
                System.out.println(
                        "Could not find block definition file '" + levelMap.get("block_definitions").trim() + "'");
                System.exit(0);
            }
            blockFactory = BlocksDefinitionReader.fromReader(new InputStreamReader(
                    ClassLoader.getSystemClassLoader().getResourceAsStream(levelMap.get("block_definitions").trim())));

        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.exit(0);
        }
        int currentX = xpos;
        int currentY = ypos;

        for (String line : blocksLines) {
            for (int i = 0; i < line.length(); i++) {
                String s = Character.toString(line.charAt(i));
                if (blockFactory.isSpaceSymbol(s)) {
                    currentX += blockFactory.getSpaceWidth(s);
                } else if (blockFactory.isBlockSymbol(s)) {
                    Block block = blockFactory.getBlock(s, currentX, currentY);
                    blocks.add(block);
                    currentX += block.getWidth();
                } else {
                    System.out.println("Error - invalid character");
                    System.exit(0);
                }
            }
            currentX = xpos;
            currentY += rowHeight;
        }
        return blocks;
    }


    /**
     * Gets background.
     *
     * @param background the background
     * @return the background
     */
    public Sprite getBackground(String background) {
        String substring = background.substring(6, background.length() - 1);
        if (background.startsWith("image")) {
            try {
                return new Background(ImageIO.read(ClassLoader.getSystemClassLoader().getResourceAsStream(substring)));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return new Background(new ColorsParser().colorFromString(substring));
    }


    /**
     * Split velocity list.
     *
     * @param velocityLine the velocity line
     * @return the list
     */
    public List<Velocity> splitVelocity(String velocityLine) {
        List<Velocity> velocityList = new ArrayList<>();
        String[] velocities = velocityLine.split(" ");
        for (String s : velocities) {
            String[] oneVelocity = s.split(",");
            velocityList.add(Velocity.fromAngleAndSpeed(Integer.parseInt(oneVelocity[0]),
                    Integer.parseInt(oneVelocity[1])));
        }
        return velocityList;
    }
}
