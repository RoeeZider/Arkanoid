package listeners;

/**
 * The interface Hit notifier.
 *
 * @author Roee Zider The interface Hit notifier.
 */
public interface HitNotifier {
    /**
     * Add hit listener.
     * Add hl as a listener to hit events.
     *
     * @param hl the hit listener
     */

    void addHitListener(HitListener hl);

    /**
     * Remove hit listener.
     * Remove hl from the list of listeners to hit events.
     *
     * @param hl the hit listener
     */
    void removeHitListener(HitListener hl);
}