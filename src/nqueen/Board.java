package nqueen;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Random;

/**
 * Board class extends JPanel used to paint the chess board
 * and solve the n-queen problem using specified algorithms.
 */
public class Board extends JPanel {

    /**
     * attributes to represent the current state of the problem
     * size: size of the squared board
     * state: array represents the columns and each value represents the row index
     *          of the queen in that column
     */
    private int size = 8;
    private int[] state = {0, 0, 0, 0, 0, 0, 0, 0};

    /**
     * pointer to Application object that initialize this component
     * used to access the parent fields
     */
    private final Application parent;

    public Board(Application parent) {
        this.parent = parent;
    }

    /**
     * override paint method to paint the chess board
     * and queens on it using the state field
     */
    @Override
    public void paintComponent(Graphics g) {

        super.paintComponent(g);
        boolean white = true;
        int squareSize = 50;
        int xi = (this.getWidth() - (size * squareSize)) / 2;
        int yi = (this.getHeight() - (size * squareSize)) / 2;

        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                g.setColor(white ? Color.white : Color.black);
                g.fillRect(xi + col * squareSize, yi + row * squareSize, squareSize, squareSize);
                white = !white;
            }
            if (size % 2 == 0) white = !white;
        }

        Image queenImage = new ImageIcon("resources/queenIcon2.png").getImage();
        for (int i = 0; i < size; i++) {
            g.drawImage(queenImage, xi + i * squareSize, yi + state[i] * squareSize,
                    squareSize, squareSize, null);
        }
    }

    /**
     * this method set random state to chess board
     */
    public void initializeState() {
        Random rand = new Random();
        for (int i = 0; i < size; i++) {
            state[i] = rand.nextInt(size);
        }
        this.repaint();
    }

    /**
     * method to run solving problem
     * takes data attribute specifies the algorithm used and
     * the parameters of simulated annealing
     */
    public void runAlgorithm(HashMap<String, String> data) {
        String algorithm = data.get("algorithm");
        if (algorithm.equals("Hill Climbing")) {
            hillClimbing();
        }
        else {
            double initialTemp = Double.parseDouble(data.get("initial_temp"));
            double coolingRate = Double.parseDouble(data.get("cooling_rate"));
            double finalTemp = Double.parseDouble(data.get("final_temp"));

            simulatedAnnealing(initialTemp, coolingRate, finalTemp);
        }
    }

    /**
     * method used to calculate the heuristic of given state
     * takes the state as int array parameter
     * this algorithm is optimized using HashMaps to make the time
     * complexity of the algorithm --- O(n) ---
     * the heuristic is computed by calculate the number of attacks
     * between queens on the board
     */
    private int getHeuristic(int[] stateArr) {
        int ans = 0;
        int s = stateArr.length;
        HashMap<Integer, Integer> slope1 = new HashMap<>();
        HashMap<Integer, Integer> slope2 = new HashMap<>();
        HashMap<Integer, Integer> row = new HashMap<>();

        for (int i = 0; i < s; i++) {
            ans -= slope1.compute(stateArr[i] - i, (key, val) -> val == null ? 0 : val);
            ans -= slope2.compute(stateArr[i] + i, (key, val) -> val == null ? 0 : val);
            ans -= row.compute(stateArr[i], (key, val) -> val == null ? 0 : val);
            slope1.compute(stateArr[i] - i, (key, val) -> val == null ? 1 : val + 1);
            slope2.compute(stateArr[i] + i, (key, val) -> val == null ? 1 : val + 1);
            row.compute(stateArr[i], (key, val) -> val == null ? 1 : val + 1);
        }
        return ans;
    }

    /**
     * method to get random successor of given state
     * used by simulated annealing algorithm
     */
    private int[] getRandomSuccessor(int[] parent_state) {
        Random rand = new Random();
        int[] temp_state = parent_state.clone();
        int sz = parent_state.length;

        for (int i = 0; i < sz; i++) {
            temp_state[i] = rand.nextInt(sz);
        }
        return temp_state;
    }

    /**
     * the implementation of Hill Climbing algorithm
     */
    private void hillClimbing() {

        int[] temp_state = state.clone();
        int curr_value = getHeuristic(temp_state);

        int temp_value, best_successor = Integer.MIN_VALUE;
        int avoid, xBest = 0, yBest = 0, iteration = 0;

        while (true) {
            for (int i = 0; i < size; i++) {
                avoid = temp_state[i];

                for (int j = 0; j < size; j++) {
                    temp_state[i] = j;
                    if (j == avoid) continue;   // original state
                    temp_value = getHeuristic(temp_state);
                    if (temp_value >= best_successor) {
                        best_successor = temp_value;
                        xBest = i;
                        yBest = j;
                    }
                }

                temp_state[i] = avoid;
            }

            iteration++;

            if (best_successor > curr_value) {
                curr_value = best_successor;
                temp_state[xBest] = yBest;
                state[xBest] = yBest;
                if (curr_value == 0) break;      // the goal
            }
            else {
                break;
            }
        }

        parent.setIterationField(iteration);
        this.repaint();
    }

    /**
     * the implementation of Simulated Annealing algorithm
     * takes initial and final temperature and cooling rate
     */
    private void simulatedAnnealing(double initialTemp, double coolingRate, double finalTemp) {

        int[] curr_state = state.clone();
        int[] best_state = curr_state.clone();
        int[] next_state;

        double tc = initialTemp;
        int err;
        Random rand = new Random();
        int iteration = 0;

        while (!(tc <= finalTemp)) {
            iteration++;
            tc *= coolingRate;
            next_state = getRandomSuccessor(curr_state);
            err = getHeuristic(next_state) - getHeuristic(curr_state);
            if (err > 0) {
                curr_state = next_state;
                if (getHeuristic(best_state) < getHeuristic(curr_state)) best_state = curr_state.clone();
            } else if (Math.exp(-err / tc) > rand.nextDouble()) {
                curr_state = next_state;
            }
        }

        state = best_state;
        parent.setTemperatureField(tc);
        parent.setIterationField(iteration);
        this.repaint();
    }

    /**
     * set board size and repaint the board
     */
    public void setBoardSize(int value) {
        this.size = value;
        this.state = new int[size];
        this.repaint();
    }
}
