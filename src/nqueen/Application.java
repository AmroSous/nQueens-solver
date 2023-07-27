/*
 * Created by JFormDesigner on Mon Jul 17 22:25:52 IDT 2023
 */

package nqueen;

import java.awt.*;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.stream.Stream;
import javax.swing.*;
import javax.swing.border.*;

/**
 *    This is the main Application class which extends JFrame class
 *    and contains control panels and the chess board.
 */
@SuppressWarnings("FieldCanBeLocal")
public class Application extends JFrame {

    /**
     * board components of class Board, is the main component in the application
     * which contains the algorithms implementation for solving the problem and
     * it paints the chess board on the application frame
     */

    private final Board board;
    public Application() {
        
        initComponents();
        
        this.setTitle("N-Queen Solver");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        board = new Board(this);
        chessBoardPanel.add(board);
        board.setBounds(5, 5, chessBoardPanel.getWidth() - 10, chessBoardPanel.getHeight() - 10);
        board.setBackground(chessBoardPanel.getBackground());
    }

    /**
     * setter to access the iteration field in the result panel
     * used by the board component to set the final iteration of algorithm.
     */
    public void setIterationField(int value) {
        iterationField.setText(value+"");
    }

    /**
     * setter to access the temperature field in the result panel
     * used by the board component to set the final temperature after
     * applying simulated annealing algorithm.
     */
    public void setTemperatureField(double value) {
        DecimalFormat df = new DecimalFormat("#." + "0000");
        temperatureField.setText(df.format(value));
    }

    /**
     * to make the panel which takes the parameters of simulated annealing algorithm enabled.
     */
    private void setAnnealingPanelEnable(boolean state) {
        Stream.of(simAnnealingPanel.getComponents()).forEach(c -> c.setEnabled(state));
    }

    /**
     * state changed listener for algorithm combo to enable sim-ann panel
     */
    private void algorithmComboItemStateChanged() {
        setAnnealingPanelEnable(algorithmCombo.getSelectedIndex() != 0);
    }

    /**
     * action listener for random button
     */
    private void initialize() {
        board.initializeState();
    }

    /**
     * action listener for run button, when pressed the method takes all inputs
     * from the control panel and make validation on it, then pass it to the
     * board component to run the algorithm
     */
    private void run() {
        messageLabel.setText("");
        String algorithm = (String) algorithmCombo.getSelectedItem();
        String initialT = initialTemp.getText().trim();
        String cooling = coolingRate.getText().trim();
        String finalT = finalTemp.getText().trim();

        String error = "";
        if (algorithm == null || algorithm.equals("")) {
            error = "Invalid Algorithm.";
        }
        else if (algorithm.equals("Simulated Annealing")) {
            String pattern = "^[0-9]{1,9}(.([0-9]{1,5}))?$";
            if (!initialT.matches(pattern) || !cooling.matches(pattern) || !finalT.matches(pattern)) {
                error = "Invalid inputs in fields.";
            }
        }

        if (!error.equals("")) {
            messageLabel.setText(error);
            return;
        }

        HashMap<String, String> data = new HashMap<>();
        data.put("algorithm", algorithm);
        data.put("initial_temp", initialT);
        data.put("cooling_rate", cooling);
        data.put("final_temp", finalT);
        board.runAlgorithm(data);
    }

    /**
     * listener for the spinner to change the board size
     */
    private void boardSizeSpinnerStateChanged() {
        board.setBoardSize((int)boardSizeSpinner.getValue());
    }

    /**
     * listener to clear button which clears all the inputs and message label
     */
    private void clear() {
        messageLabel.setText("");
        initialTemp.setText("");
        coolingRate.setText("");
        finalTemp.setText("");
    }

    /**
     * method to change theme to Light Theme
     */
    private void putLightTheme() {
        controlPanel.setBackground(Color.decode("#f2f2f2"));
        resultPanel.setBackground(Color.decode("#f2f2f2"));
        Stream.of(resultPanel.getComponents()).filter(c -> c instanceof JLabel).forEach(l -> l.setForeground(Color.BLACK));
        Stream.of(controlPanel.getComponents()).filter(c -> c instanceof JLabel).forEach(l -> l.setForeground(Color.BLACK));
        mainPanel.setBackground(Color.decode("#885577"));
        chessBoardPanel.setBackground(Color.decode("#885577"));
        board.setBackground(Color.decode("#885577"));
        board.setBoardTheme(Board.Theme.LIGHT);
    }

    /**
     * method to change theme to Dark Theme
     */
    private void putDarkTheme() {
        controlPanel.setBackground(Color.black);
        resultPanel.setBackground(Color.black);
        Stream.of(resultPanel.getComponents()).filter(c -> c instanceof JLabel).forEach(l -> l.setForeground(Color.cyan));
        Stream.of(controlPanel.getComponents()).filter(c -> c instanceof JLabel).forEach(l -> l.setForeground(Color.cyan));
        mainPanel.setBackground(Color.decode("#999999"));
        chessBoardPanel.setBackground(Color.decode("#999999"));
        board.setBackground(Color.decode("#999999"));
        board.setBoardTheme(Board.Theme.DARK);
    }

    /**
     * listener for light theme menu item
     */
    private void lightTheme() {
        putLightTheme();
    }

    /**
     * listener for dark theme menu item
     */
    private void darkTheme() {
        putDarkTheme();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        // Generated using JFormDesigner Evaluation license - Amro
        mainPanel = new JPanel();
        chessBoardPanel = new JPanel();
        controlPanel = new JPanel();
        label1 = new JLabel();
        label2 = new JLabel();
        boardSizeSpinner = new JSpinner();
        label3 = new JLabel();
        algorithmCombo = new JComboBox<>();
        simAnnealingPanel = new JPanel();
        label4 = new JLabel();
        initialTemp = new JTextField();
        label5 = new JLabel();
        coolingRate = new JTextField();
        label6 = new JLabel();
        finalTemp = new JTextField();
        initializeButton = new JButton();
        runButton = new JButton();
        messageLabel = new JLabel();
        clearButton = new JButton();
        menuBar1 = new JMenuBar();
        menu1 = new JMenu();
        darkTheme = new JMenuItem();
        lightTheme = new JMenuItem();
        resultPanel = new JPanel();
        label7 = new JLabel();
        iterationField = new JTextField();
        label8 = new JLabel();
        temperatureField = new JTextField();

        //======== this ========
        setBackground(new Color(0xcccccc));
        var contentPane = getContentPane();

        //======== mainPanel ========
        {
            mainPanel.setBackground(new Color(0x885577));
            mainPanel.setBorder (new javax. swing. border. CompoundBorder( new javax .swing .border .TitledBorder (new javax
            . swing. border. EmptyBorder( 0, 0, 0, 0) , "JFor\u006dDesi\u0067ner \u0045valu\u0061tion", javax. swing
            . border. TitledBorder. CENTER, javax. swing. border. TitledBorder. BOTTOM, new java .awt .
            Font ("Dia\u006cog" ,java .awt .Font .BOLD ,12 ), java. awt. Color. red
            ) ,mainPanel. getBorder( )) ); mainPanel. addPropertyChangeListener (new java. beans. PropertyChangeListener( ){ @Override
            public void propertyChange (java .beans .PropertyChangeEvent e) {if ("bord\u0065r" .equals (e .getPropertyName (
            ) )) throw new RuntimeException( ); }} );
            mainPanel.setLayout(null);

            //======== chessBoardPanel ========
            {
                chessBoardPanel.setBackground(new Color(0x885577));
                chessBoardPanel.setLayout(null);

                {
                    // compute preferred size
                    Dimension preferredSize = new Dimension();
                    for(int i = 0; i < chessBoardPanel.getComponentCount(); i++) {
                        Rectangle bounds = chessBoardPanel.getComponent(i).getBounds();
                        preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                        preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                    }
                    Insets insets = chessBoardPanel.getInsets();
                    preferredSize.width += insets.right;
                    preferredSize.height += insets.bottom;
                    chessBoardPanel.setMinimumSize(preferredSize);
                    chessBoardPanel.setPreferredSize(preferredSize);
                }
            }
            mainPanel.add(chessBoardPanel);
            chessBoardPanel.setBounds(10, 6, 465, 409);

            //======== controlPanel ========
            {
                controlPanel.setLayout(null);

                //---- label1 ----
                label1.setText("Board Size: ");
                label1.setFont(new Font("SimSun", Font.PLAIN, 18));
                controlPanel.add(label1);
                label1.setBounds(30, 90, 115, 40);
                label1.setBounds(35, 75, 115, 40);

                //---- label2 ----
                label2.setText("Control Panel");
                label2.setHorizontalAlignment(SwingConstants.CENTER);
                label2.setFont(new Font("Simplified Arabic Fixed", Font.BOLD, 20));
                controlPanel.add(label2);
                label2.setBounds(115, 15, 190, 35);

                //---- boardSizeSpinner ----
                boardSizeSpinner.setModel(new SpinnerNumberModel(8, 4, 8, 1));
                boardSizeSpinner.setFont(new Font("Segoe UI", Font.PLAIN, 16));
                boardSizeSpinner.addChangeListener(e -> boardSizeSpinnerStateChanged());
                controlPanel.add(boardSizeSpinner);
                boardSizeSpinner.setBounds(155, 95, 90, 32);

                //---- label3 ----
                label3.setText("Algorithm:");
                label3.setFont(new Font("SimSun", Font.PLAIN, 18));
                controlPanel.add(label3);
                label3.setBounds(30, 150, 115, 40);

                //---- algorithmCombo ----
                algorithmCombo.setModel(new DefaultComboBoxModel<>(new String[] {
                    "Hill Climbing",
                    "Simulated Annealing"
                }));
                algorithmCombo.setFont(new Font("SimSun", Font.PLAIN, 16));
                algorithmCombo.addItemListener(e -> algorithmComboItemStateChanged());
                controlPanel.add(algorithmCombo);
                algorithmCombo.setBounds(160, 155, 180, 35);

                //======== simAnnealingPanel ========
                {
                    simAnnealingPanel.setBackground(new Color(0xeeeeee));
                    simAnnealingPanel.setLayout(null);

                    //---- label4 ----
                    label4.setText("Initial Temperature:");
                    label4.setFont(new Font("SimSun", Font.PLAIN, 18));
                    label4.setEnabled(false);
                    simAnnealingPanel.add(label4);
                    label4.setBounds(15, 10, 195, 40);

                    //---- initialTemp ----
                    initialTemp.setFont(new Font("Segoe UI", Font.PLAIN, 16));
                    initialTemp.setEnabled(false);
                    initialTemp.setDisabledTextColor(new Color(0xcccccc));
                    simAnnealingPanel.add(initialTemp);
                    initialTemp.setBounds(220, 15, 105, 35);

                    //---- label5 ----
                    label5.setText("Cooling Rate:");
                    label5.setFont(new Font("SimSun", Font.PLAIN, 18));
                    label5.setEnabled(false);
                    simAnnealingPanel.add(label5);
                    label5.setBounds(75, 55, 130, 40);

                    //---- coolingRate ----
                    coolingRate.setFont(new Font("Segoe UI", Font.PLAIN, 16));
                    coolingRate.setEnabled(false);
                    coolingRate.setDisabledTextColor(new Color(0xcccccc));
                    simAnnealingPanel.add(coolingRate);
                    coolingRate.setBounds(220, 60, 105, 35);

                    //---- label6 ----
                    label6.setText("Final Temperature:");
                    label6.setFont(new Font("SimSun", Font.PLAIN, 18));
                    label6.setEnabled(false);
                    simAnnealingPanel.add(label6);
                    label6.setBounds(30, 100, 180, 40);

                    //---- finalTemp ----
                    finalTemp.setFont(new Font("Segoe UI", Font.PLAIN, 16));
                    finalTemp.setDisabledTextColor(new Color(0xcccccc));
                    finalTemp.setEnabled(false);
                    simAnnealingPanel.add(finalTemp);
                    finalTemp.setBounds(220, 105, 105, 35);

                    {
                        // compute preferred size
                        Dimension preferredSize = new Dimension();
                        for(int i = 0; i < simAnnealingPanel.getComponentCount(); i++) {
                            Rectangle bounds = simAnnealingPanel.getComponent(i).getBounds();
                            preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                            preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                        }
                        Insets insets = simAnnealingPanel.getInsets();
                        preferredSize.width += insets.right;
                        preferredSize.height += insets.bottom;
                        simAnnealingPanel.setMinimumSize(preferredSize);
                        simAnnealingPanel.setPreferredSize(preferredSize);
                    }
                }
                controlPanel.add(simAnnealingPanel);
                simAnnealingPanel.setBounds(25, 210, 345, 150);

                //---- initializeButton ----
                initializeButton.setText("Initialize");
                initializeButton.setFont(new Font("Sitka Text", Font.PLAIN, 16));
                initializeButton.addActionListener(e -> initialize());
                controlPanel.add(initializeButton);
                initializeButton.setBounds(135, 385, 105, 40);

                //---- runButton ----
                runButton.setText("Run");
                runButton.setFont(new Font("Sitka Text", Font.PLAIN, 16));
                runButton.addActionListener(e -> run());
                controlPanel.add(runButton);
                runButton.setBounds(265, 385, 95, 40);
              
                //---- messageLabel ----
                messageLabel.setForeground(Color.red);
                messageLabel.setFont(new Font("SimSun", Font.PLAIN, 16));
                messageLabel.setHorizontalAlignment(SwingConstants.CENTER);
                controlPanel.add(messageLabel);
                messageLabel.setBounds(25, 450, 350, 25);

                //---- clearButton ----
                clearButton.setText("Clear");
                clearButton.setFont(new Font("Sitka Text", Font.PLAIN, 16));
                clearButton.addActionListener(e -> clear());
                controlPanel.add(clearButton);
                clearButton.setBounds(25, 385, 90, 40);

                //======== menuBar1 ========
                {

                    //======== menu1 ========
                    {
                        menu1.setText("Theme >");
                        menu1.setBorder(new MatteBorder(1, 1, 1, 1, Color.black));

                        //---- darkTheme ----
                        darkTheme.setText("Dark");
                        darkTheme.setSelected(true);
                        darkTheme.addActionListener(e -> darkTheme());
                        menu1.add(darkTheme);

                        //---- lightTheme ----
                        lightTheme.setText("Light");
                        lightTheme.addActionListener(e -> lightTheme());
                        menu1.add(lightTheme);
                    }
                    menuBar1.add(menu1);
                }
                controlPanel.add(menuBar1);
                menuBar1.setBounds(305, 60, 70, 30);

                {
                    // compute preferred size
                    Dimension preferredSize = new Dimension();
                    for(int i = 0; i < controlPanel.getComponentCount(); i++) {
                        Rectangle bounds = controlPanel.getComponent(i).getBounds();
                        preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                        preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                    }
                    Insets insets = controlPanel.getInsets();
                    preferredSize.width += insets.right;
                    preferredSize.height += insets.bottom;
                    controlPanel.setMinimumSize(preferredSize);
                    controlPanel.setPreferredSize(preferredSize);
                }
            }
            mainPanel.add(controlPanel);
            controlPanel.setBounds(485, 10, 395, 480);

            //======== resultPanel ========
            {
                resultPanel.setLayout(null);

                //---- label7 ----
                label7.setText("Iteration:");
                label7.setFont(new Font("SimSun", Font.PLAIN, 18));
                resultPanel.add(label7);
                label7.setBounds(20, 20, 105, 30);

                //---- iterationField ----
                iterationField.setEnabled(false);
                iterationField.setDisabledTextColor(new Color(0x333333));
                resultPanel.add(iterationField);
                iterationField.setBounds(125, 20, 55, 30);

                //---- label8 ----
                label8.setText("Temperature:");
                label8.setFont(new Font("SimSun", Font.PLAIN, 18));
                resultPanel.add(label8);
                label8.setBounds(215, 20, 120, 30);

                //---- temperatureField ----
                temperatureField.setEnabled(false);
                temperatureField.setDisabledTextColor(new Color(0x333333));
                resultPanel.add(temperatureField);
                temperatureField.setBounds(340, 20, 100, 30);
              
                {
                    // compute preferred size
                    Dimension preferredSize = new Dimension();
                    for(int i = 0; i < resultPanel.getComponentCount(); i++) {
                        Rectangle bounds = resultPanel.getComponent(i).getBounds();
                        preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                        preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                    }
                    Insets insets = resultPanel.getInsets();
                    preferredSize.width += insets.right;
                    preferredSize.height += insets.bottom;
                    resultPanel.setMinimumSize(preferredSize);
                    resultPanel.setPreferredSize(preferredSize);
                }
            }
            mainPanel.add(resultPanel);
            resultPanel.setBounds(10, 420, 465, 70);
        }

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
            contentPaneLayout.createParallelGroup()
                .addComponent(mainPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup()
                .addComponent(mainPanel, GroupLayout.DEFAULT_SIZE, 499, Short.MAX_VALUE)
        );
        setSize(894, 537);
        setLocationRelativeTo(null);
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    // Generated using JFormDesigner Evaluation license - Amro
    private JPanel mainPanel;
    private JPanel chessBoardPanel;
    private JPanel controlPanel;
    private JLabel label1;
    private JLabel label2;
    private JSpinner boardSizeSpinner;
    private JLabel label3;
    private JComboBox<String> algorithmCombo;
    private JPanel simAnnealingPanel;
    private JLabel label4;
    private JTextField initialTemp;
    private JLabel label5;
    private JTextField coolingRate;
    private JLabel label6;
    private JTextField finalTemp;
    private JButton initializeButton;
    private JButton runButton;
    private JLabel messageLabel;
    private JButton clearButton;
    private JMenuBar menuBar1;
    private JMenu menu1;
    private JMenuItem darkTheme;
    private JMenuItem lightTheme;
    private JPanel resultPanel;
    private JLabel label7;
    private JTextField iterationField;
    private JLabel label8;
    private JTextField temperatureField;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
