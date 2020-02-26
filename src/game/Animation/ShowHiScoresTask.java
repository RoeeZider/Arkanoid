package game.Animation;

import biuoop.KeyboardSensor;

/**
 * The type Show hi scores task.
 */
public class ShowHiScoresTask implements Task<Void> {
    private AnimationRunner runner;
    private Animation highScoresAnimation;
    private KeyboardSensor ks;

    /**
     * Instantiates a new Show hi scores task.
     *
     * @param runner              the runner
     * @param highScoresAnimation the high scores animation
     * @param ks                  the ks
     */
    public ShowHiScoresTask(AnimationRunner runner, Animation highScoresAnimation, KeyboardSensor ks) {
        this.runner = runner;
        this.highScoresAnimation = highScoresAnimation;
        this.ks = ks;
    }

    @Override
    public Void run() {
        //this.runner.run(this.highScoresAnimation);
        this.runner.run(new KeyPressStoppableAnimation(
                this.ks, this.ks.SPACE_KEY,
                this.highScoresAnimation));
        return null;
    }
}
