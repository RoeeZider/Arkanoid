package objects.score;

/**
 * The type Counter.
 *
 * @author Roee Zider The type Counter.
 */
public class Counter {

    /**
     * The Counter.
     */
    private int counter;

    /**
     * Instantiates a new Counter.
     */
    public Counter() {
        this.counter = 0;
    }

    /**
     * Instantiates a new Counter.
     *
     * @param count the count
     */
    public Counter(int count) {
        this.counter = count;
    }

    /**
     * Increase.
     *
     * @param number the number
     */
// add number to current count.
    public void increase(int number) {
        this.counter += number;
    }

    /**
     * Decrease.
     *
     * @param number the number
     */
// subtract number from current count.
    public void decrease(int number) {
        this.counter -= number;
    }

    /**
     * Gets value.
     *
     * @return the value
     */
// get current count.
    public int getValue() {
        return this.counter;
    }
}
