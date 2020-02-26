package game.Animation;

/**
 * The type Quit game.
 */
public class QuitGame implements Task<Void> {
    @Override
    public Void run() {
        System.exit(0);
        return null;
    }
}
