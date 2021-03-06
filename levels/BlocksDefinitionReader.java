package game.levels;

import geometry.Point;
import geometry.Rectangle;
import objects.Block;

import javax.imageio.ImageIO;
import java.awt.Color;
import java.awt.Image;
import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * @author Roee Zider.
 * The type Blocks definition reader.
 */
public class BlocksDefinitionReader {
    private static final String SDEF = "sdef";
    private static final String BDEF = "bdef";
    private static final String DEFAULT = "default";


    /**
     * From reader blocks from symbols factory.
     *
     * @param reader the reader
     * @return the blocks from symbols factory
     */
    public static BlocksFromSymbolsFactory fromReader(java.io.Reader reader) {
       /* Map<String,String> blocks = new TreeMap<>();
        Map<String,String> spacers = new TreeMap<>();
        Map<String, String> defaultProperty = new TreeMap<>();
        Map<String, Integer> spacerWidths;
        Map<String, BlockCreator> blockCreators;
    */

        Map<String, Map<String, String>> blocks = new TreeMap<>();
        Map<String, Map<String, String>> spacers = new TreeMap<>();
        Map<String, String> defaultDef = new TreeMap<>();
        Map<String, Integer> spacerWidths;
        Map<String, BlockCreator> blockCreators;
        List<String> lines = readBlocks(reader);

        if (lines == null) {
            System.exit(1);
        }
        parasBlocks(lines, blocks, spacers, defaultDef);
        try {
            completeBlock(blocks, defaultDef);
        } catch (Exception e) {
            return null;
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        }
        spacerWidths = createSpacer(spacers);
        blockCreators = createBlock(blocks);
        return new BlocksFromSymbolsFactory(spacerWidths, blockCreators);

    }

    /**
     * parseLine.
     *
     * @param line the string
     * @return Map<String, String>
     */
    private static Map<String, String> parseLine(String line) {
        Map<String, String> blockDef = new TreeMap<>();
        String[] splitLine = line.split(" ");

        for (String arg : splitLine) {
            if (!arg.contains(":")) {
                continue;
            }

            String[] splitArg = arg.split(":");
            blockDef.put(splitArg[0].trim(), splitArg[1].trim());
        }

        return blockDef;
    }

    /**
     * parasBlocks.
     *
     * @param lines      the lines
     * @param blocks     the blocks
     * @param spacers    the spacers
     * @param defaultDef the default
     */
    private static void parasBlocks(List<String> lines, Map<String, Map<String, String>> blocks,
                                    Map<String, Map<String, String>> spacers, Map<String, String> defaultDef) {
        for (String line : lines) {
            Map<String, String> blockDef = parseLine(line);

            if (line.startsWith(BDEF) || line.startsWith(SDEF)) {
                if (!blockDef.containsKey("symbol")) {
                    continue;
                }
                if (line.startsWith(BDEF)) {
                    blocks.put(blockDef.get("symbol"), blockDef);
                } else {
                    spacers.put(blockDef.get("symbol"), blockDef);
                }
            } else if (line.startsWith(DEFAULT)) {
                for (Map.Entry<String, String> def : blockDef.entrySet()) {
                    defaultDef.put(def.getKey(), def.getValue());
                }
            }
        }
    }

    /**
     * Create a map of symbol key, and his block.
     *
     * @param blocks map of block definition
     * @return new map of block creator
     */
    private static Map<String, BlockCreator> createBlock(Map<String, Map<String, String>> blocks) {
        Map<String, BlockCreator> blocksCreator = new TreeMap<>();

        for (String key : blocks.keySet()) {

            Map<String, String> blockDef = blocks.get(key);
            int width = Integer.parseInt(blockDef.get("width"));
            int height = Integer.parseInt(blockDef.get("height"));
            int hitPoints = Integer.parseInt(blockDef.get("hit_points"));
            ColorsParser colorMachine = new ColorsParser();
            Color stroke = colorMachine.colorFromString(blockDef.get("stroke"));
            Map<Integer, Color> colors = new TreeMap<>();
            Map<Integer, Image> images = new TreeMap<>();
            for (String def : blockDef.keySet()) {
                if (def.trim().equals("fill")) {
                    if (blockDef.get(def).trim().startsWith("image")) {
                        String image = blockDef.get(def).replace("image(", "").replace(")", "");
                        try {
                            images.put(1, ImageIO.read(ClassLoader.getSystemClassLoader().getResourceAsStream(image)));

                        } catch (Exception e) {
                            System.out.println("Error loading image");
                            System.exit(2);
                        }
                    } else {
                        colors.put(1, colorMachine.colorFromString(blockDef.get(def)));
                    }
                } else if (def.startsWith("fill-")) {
                    int num = Integer.parseInt(def.replace("fill-", "").trim());
                    if (blockDef.get(def).trim().startsWith("image")) {
                        String image = blockDef.get(def).replace("image(", "").replace(")", "");
                        try {

                            images.put(num, ImageIO.read(ClassLoader.getSystemClassLoader()
                                    .getResourceAsStream(image)));
                        } catch (Exception e) {
                            System.out.println("Error loading image");
                            System.exit(3);
                        }
                    } else {
                        colors.put(num, colorMachine.colorFromString(blockDef.get(def)));
                    }
                }
            }
            //check
            BlockCreator blockCreator = new BlockCreator() {
                @Override
                public Block create(int xpos, int ypos) {
                    Rectangle rect = new Rectangle(new Point(xpos, ypos), width, height);
                    Block block = new Block(rect, colors, images);
                    block.setPointsOfHit(hitPoints);
                    if (stroke != null) {
                        block.setStroke(stroke);
                    }
                    return block;
                }
            };

            blocksCreator.put(key, blockCreator);
        }
        return blocksCreator;
    }

    /**
     * Create a map of spacers.
     *
     * @param spacers in string
     * @return new map with integer
     */
    private static Map<String, Integer> createSpacer(Map<String, Map<String, String>> spacers) {
        Map<String, Integer> spacerDef = new TreeMap<String, Integer>();
        for (String key : spacers.keySet()) {

            Map<String, String> spacer = spacers.get(key);
            spacerDef.put(key, Integer.parseInt(spacer.get("width")));
        }
        return spacerDef;
    }

    /**
     * complete block.
     *
     * @param blocks     map of blocks
     * @param defaultDef default map
     */
    private static void completeBlock(Map<String, Map<String, String>> blocks, Map<String, String> defaultDef) {
        for (Map<String, String> blockDef : blocks.values()) {
            String[] options = {"symbol", "height", "width", "hit_points"};
            for (String property : options) {
                if (!blockDef.containsKey(property)) {
                    if (!defaultDef.containsKey(property)) {
                        System.out.println("Block is miss a property!");
                        System.exit(4);
                    }
                    blockDef.put(property, defaultDef.get(property));
                }
            }
            if (!(blockDef.containsKey("fill-1") || blockDef.containsKey("fill"))) {
                if (!(defaultDef.containsKey("fill-1") || defaultDef.containsKey("fill"))) {
                    System.out.println("OBlock is miss a property!");
                    System.exit(5);
                }
                if (defaultDef.containsKey("fill-1")) {
                    blockDef.put("fill-1", defaultDef.get("fill-1"));
                } else {
                    blockDef.put("fill", defaultDef.get("fill"));
                }
            }
            if (!(blockDef.containsKey("stroke"))) {
                if (defaultDef.containsKey("stroke")) {
                    blockDef.put("stroke", defaultDef.get("stroke"));
                }
            }
        }
    }


    /**
     * read all blocks from the file.
     *
     * @param reader the file which will be read.
     * @return the needed lines.
     */
    private static List<String> readBlocks(java.io.Reader reader) {
        BufferedReader br = new BufferedReader(reader);
        List<String> lines = new ArrayList<>();
        try {
            String line = br.readLine();
            while (line != null) {
                if (line.startsWith(BDEF) || line.startsWith(SDEF) || line.startsWith(DEFAULT)) {
                    lines.add(line);
                }
                line = br.readLine();
            }
        } catch (Exception e) {
            System.out.println("Error- cannot loading file");
            System.exit(6);
        }
        return lines;
    }
}



