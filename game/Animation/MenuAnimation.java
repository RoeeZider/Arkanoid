package game.Animation;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Menu animation.
 *
 * @param <T> the type parameter
 * @author Roee Zider. The type Menu animation.
 */
public class MenuAnimation<T> implements Menu<T> {

    //members
    private List<String> keys;
    private List<String> messages;
    private List<T> actions;
    private String name;
    private AnimationRunner animationRunner;
    private KeyboardSensor keyboardSensor;
    private T status;
    private Boolean stop;
    private List<Menu<T>> subMenuActions;
    private List<String> subKeys;
    private List<String> subMessages;
    //  private List<String> subMessages;
    //private List<String> subKeys;
    private List<Boolean> isSub;


    /**
     * Instantiates a new Menu animation.
     *
     * @param ar         the ar
     * @param ks         the ks
     * @param nameOfGame the name of game
     */
    public MenuAnimation(AnimationRunner ar, KeyboardSensor ks, String nameOfGame) {
        this.animationRunner = ar;
        this.keyboardSensor = ks;
        this.name = nameOfGame;
        this.keys = new ArrayList<>();
        this.messages = new ArrayList<>();
        this.actions = new ArrayList<>();
        this.stop = false;
        this.isSub = new ArrayList<>();
        this.subMessages = new ArrayList<>();
        this.subKeys = new ArrayList<>();
        this.isSub = new ArrayList<>();
        this.subMenuActions = new ArrayList<>();

    }

    @Override
    public void addSelection(String key, String message, T returnVal) {
        this.keys.add(key);
        this.messages.add(message);
        this.actions.add(returnVal);
        this.isSub.add(false);
        this.subMenuActions.add(null);
    }

    @Override
    public void addSubMenu(String key, String message, Menu<T> subMenu) {
        this.keys.add(key);
        this.messages.add(message);
        this.actions.add(null);
        this.subMenuActions.add(subMenu);
        this.isSub.add(true);
        // this.isSub=true;
    }

    @Override
    public T getStatus() {

        this.stop = false;
        T statusTemp = this.status;
        //for next press
        this.status = null;
        return statusTemp;
    }


    @Override
    public void doOneFrame(DrawSurface d) {

        for (int i = 0; i < this.actions.size(); ++i) {
            if (this.keyboardSensor.isPressed(this.keys.get(i))) {
                //sub menu
                if (!this.isSub.get(i)) {
                    this.status = this.actions.get(i);
                    this.stop = true;
                    //main menu
                } else {
                    this.animationRunner.run(this.subMenuActions.get(i));
                    this.status = this.subMenuActions.get(i).getStatus();
                    this.stop = true;
                }
                break;
            }
        }


        d.setColor(new Color(131, 255, 175));
        d.fillRectangle(0, 0, 800, 600);
        if (!stop) {
            d.setColor(new Color(21, 25, 100));
            d.drawText(250, 100, this.name, 70);
            d.setColor(new Color(62, 71, 100));


            for (int i = 0; i < this.actions.size(); i++) {
                // {
                //d.drawText(250, 200 + (60 * i), "(" + this.subKeys.get(i) + ")  " + this.subMessages.get(i), 25);
                //} else {
                d.drawText(250, 200 + (60 * i), "(" + this.keys.get(i) + ")  " + this.messages.get(i), 25);
            }
        }
    }


    @Override
    public boolean shouldStop() {
        return this.stop;
    }

    /**
     * Reset boolean.
     *
     * @return the boolean
     */
    // public boolean reset() {
    //   return this.stop = false;
    //}
}
