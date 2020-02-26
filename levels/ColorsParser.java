package game.levels;

import java.awt.Color;

/**
 * The type Colors parser.
 */
// parse color definition and return the specified color.
public class ColorsParser {
    /**
     * Color from string java . awt . color.
     *
     * @param string the string
     * @return the java . awt . color
     */
    public java.awt.Color colorFromString(String string) {
        if (string == null) {
            return null;
        }
        Color color = null;
        String temp = null;
        if (string.startsWith("color(RGB")) {
            temp = string.replace("color(RGB(", "").replace("))", "").trim();
            String[] rgbColor = temp.split(",");
            color = new Color(Integer.parseInt(rgbColor[0]), Integer.parseInt(rgbColor[1])
                    , Integer.parseInt(rgbColor[2]));
        } else if (string.startsWith("RGB")) {
            temp = string.substring(4, string.length() - 1);
            String[] rgbColor = temp.split(",");
            color = new Color(Integer.parseInt(rgbColor[0]), Integer.parseInt(rgbColor[1])
                    , Integer.parseInt(rgbColor[2]));
        } else {
            switch (string) {
                case "black":
                case "color(black)":
                    color = Color.BLACK;
                    break;
                case "red":
                case "color(red)":
                    color = Color.red;
                    break;
                case "blue":
                case "color(blue)":
                    color = Color.BLUE;
                    break;
                case "white":
                case "color(white)":
                    color = Color.white;
                    break;
                case "yellow":
                case "color(yellow)":
                    color = Color.YELLOW;
                    break;
                case "gray":
                case "color(gray)":
                    color = Color.gray;
                    break;
                case "green":
                case "color(green)":
                    color = Color.green;
                    break;
                case "orange":
                case "color(orange)":
                    color = Color.orange;
                    break;
                case "lightGray":
                case "color(lightGray)":
                    color = Color.lightGray;
                    break;
                case "pink":
                case "color(pink)":
                    color = Color.pink;
                    break;
                case "cyan":
                case "color(cyan)":
                    color = Color.cyan;
                    break;
                default:
                    break;
            }
        }
        return color;
    }
}
