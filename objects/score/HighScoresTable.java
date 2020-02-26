package objects.score;

import java.io.Serializable;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.FileInputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * The type High scores table.
 */
public class HighScoresTable implements Serializable {
    private int size;
    private List<ScoreInfo> highScores;
    /**
     * The constant SIZE.
     */
    public static final int SIZE = 6;

    /**
     * Instantiates a new High scores table.
     *
     * @param size the size
     */
// Create an empty high-scores table with the specified size.
    // The size means that the table holds up to size top scores.
    public HighScoresTable(int size) {
        this.size = size;
        this.highScores = new ArrayList<>();
    }

    /**
     * Add.
     *
     * @param score the score
     */
// Add a high-score.
    public void add(ScoreInfo score) {
        int rank = this.getRank(score.getScore());
        if (rank != 0) {
            highScores.add(rank - 1, score);

        }
        //remove the last index
        if (this.highScores.size() > this.size) {
            this.highScores.remove(this.size);
        }
    }

    /**
     * Size int.
     *
     * @return the int
     */
// Return table size.
    public int size() {
        return this.highScores.size();
    }

    /**
     * Gets high scores.
     *
     * @return the high scores
     */
// Return the current high scores.
    // The list is sorted such that the highest
    // scores come first.
    public List<ScoreInfo> getHighScores() {
        return this.highScores;
    }

    /**
     * Gets rank.
     *
     * @param score the score
     * @return the rank
     */
// return the rank of the current score: where will it
    // be on the list if added?
    // Rank 1 means the score will be highest on the list.
    // Rank `size` means the score will be lowest.
    // Rank > `size` means the score is too low and will not
    //      be added to the list.
    public int getRank(int score) {
        /*int length = this.highScores.size();
        if (length == 0) {
            return 1;
        }
        int i;
        for (i = 0; i < length; i++) {
            if (this.highScores.get(i).getScore() < score) {
                return i + 1;
            }
        }
        //check this

        if(i+1<size) {
            return i+2;
        }
        //for lowest score
        return 0;
        */
        int i;
        for (i = 0; i < this.highScores.size(); i++) {
            if (this.highScores.get(i).getScore() < score) {
                break;
            }
        }
        if (i < this.size) {
            return i + 1;
        }
        return 0;
    }

    /**
     * Clear.
     */
// Clears the table
    public void clear() {
        this.highScores.clear();
    }

    /**
     * Load.
     *
     * @param filename the filename
     * @throws IOException the io exception
     */
// Load table data from file.
    // Current table data is cleared.
    public void load(File filename) throws IOException {
        HighScoresTable highScoreTable = loadFromFile(filename);
        if (highScoreTable != null) {
            this.highScores = highScoreTable.getHighScores();
            this.size = highScoreTable.size;
        }
    }

    /**
     * Save.
     *
     * @param filename the filename
     * @throws IOException the io exception
     */
// Save table data to the specified file.
    public void save(File filename) throws IOException {
        FileOutputStream writer = null;

        try {
            writer = new FileOutputStream(filename);
            ObjectOutputStream objectWriter = new ObjectOutputStream(writer);
            objectWriter.writeObject(this);
        } catch (IOException e) {
            System.out.println("ERROR in save file");
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    System.out.println("ERROR at closing file");
                }
            }
        }
    }

    /**
     * Load from file high scores table.
     *
     * @param filename the filename
     * @return the high scores table
     */
// Read a table from file and return it.
    // If the file does not exist, or there is a problem with
    // reading it, an empty table is returned.
    public static HighScoresTable loadFromFile(File filename) {
        FileInputStream reader = null;
        try {
            reader = new FileInputStream(filename);
            ObjectInputStream objectReader = new ObjectInputStream(reader);
            return (HighScoresTable) objectReader.readObject();
        } catch (Exception e) {
            return new HighScoresTable(SIZE);
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    System.out.println("ERROR at closing file");
                }
            }
        }

    }

}
