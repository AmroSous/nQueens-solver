/*
 * Created by JFormDesigner on Mon Jul 17 22:25:52 IDT 2023
 */

package nqueen;

import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;
import java.util.stream.Stream;
import javax.swing.*;
import javax.swing.event.*;

/**
 * @author amroo
 */
public class Application extends JFrame {
    
    private Board board;
    public Application() {
        
        initComponents();
        
        this.setTitle("N-Queen Solver");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        board = new Board();
        chessBoardPanel.add(board);
        board.setBounds(15, 15, chessBoardPanel.getWidth() - 30, chessBoardPanel.getHeight() - 30);
        board.setBackground(chessBoardPanel.getBackground());
    }

    private void setAnnealingPanelEnable(boolean state) {
        Stream.of(simAnnealingPanel.getComponents()).forEach(c -> c.setEnabled(state));
    }

    private void algorithmComboItemStateChanged(ItemEvent e) {
        setAnnealingPanelEnable(algorithmCombo.getSelectedIndex() != 0);
    }

    private void initialize(ActionEvent e) {
        board.initializeState();
    }

    private void run(ActionEvent e) {
        HashMap<String, String> data = new HashMap<>();
        data.put("algorithm", (String) algorithmCombo.getSelectedItem());
        data.put("initial_temp", initialTemp.getText());
        data.put("cooling_rate", coolingRate.getText());
        data.put("final_temp", finalTemp.getText());
        board.runAlgorithm(data);
    }

    private void createUIComponents() {
        // TODO: add custom component creation code here
    }

    private void boardSizeSpinnerStateChanged(ChangeEvent e) {
        board.setBoardSize((int)boardSizeSpinner.getValue());
    }
    
    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        // Generated using JFormDesigner Evaluation license - Amro
        panel1 = new JPanel();
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

        //======== this ========
        setBackground(new Color(0xcccccc));
        var contentPane = getContentPane();

        //======== panel1 ========
        {
            panel1.setBackground(new Color(0xcccccc));
            panel1.setBorder ( new javax . swing. border .CompoundBorder ( new javax . swing. border .TitledBorder ( new javax . swing. border
            .EmptyBorder ( 0, 0 ,0 , 0) ,  "JF\u006frmDes\u0069gner \u0045valua\u0074ion" , javax. swing .border . TitledBorder. CENTER ,javax
            . swing. border .TitledBorder . BOTTOM, new java. awt .Font ( "D\u0069alog", java .awt . Font. BOLD ,
            12 ) ,java . awt. Color .red ) ,panel1. getBorder () ) ); panel1. addPropertyChangeListener( new java. beans
            .PropertyChangeListener ( ){ @Override public void propertyChange (java . beans. PropertyChangeEvent e) { if( "\u0062order" .equals ( e.
            getPropertyName () ) )throw new RuntimeException( ) ;} } );
            panel1.setLayout(null);

            //======== chessBoardPanel ========
            {
                chessBoardPanel.setBackground(new Color(0xcccccc));
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
            panel1.add(chessBoardPanel);
            chessBoardPanel.setBounds(10, 6, 480, 475);

            //======== controlPanel ========
            {
                controlPanel.setLayout(null);

                //---- label1 ----
                label1.setText("Board Size: ");
                label1.setFont(new Font("SimSun", Font.PLAIN, 18));
                controlPanel.add(label1);
                label1.setBounds(35, 70, 115, 40);

                //---- label2 ----
                label2.setText("Control Panel");
                label2.setHorizontalAlignment(SwingConstants.CENTER);
                label2.setFont(new Font("Simplified Arabic Fixed", Font.BOLD, 20));
                controlPanel.add(label2);
                label2.setBounds(115, 15, 190, 35);

                //---- boardSizeSpinner ----
                boardSizeSpinner.setModel(new SpinnerNumberModel(8, 4, 8, 1));
                boardSizeSpinner.setFont(new Font("Segoe UI", Font.PLAIN, 16));
                boardSizeSpinner.addChangeListener(e -> boardSizeSpinnerStateChanged(e));
                controlPanel.add(boardSizeSpinner);
                boardSizeSpinner.setBounds(160, 75, 90, 32);

                //---- label3 ----
                label3.setText("Algorithm:");
                label3.setFont(new Font("SimSun", Font.PLAIN, 18));
                controlPanel.add(label3);
                label3.setBounds(35, 120, 115, 40);

                //---- algorithmCombo ----
                algorithmCombo.setModel(new DefaultComboBoxModel<>(new String[] {
                    "Hill Climbing",
                    "Simulated Annealing"
                }));
                algorithmCombo.setFont(new Font("SimSun", Font.PLAIN, 16));
                algorithmCombo.addItemListener(e -> algorithmComboItemStateChanged(e));
                controlPanel.add(algorithmCombo);
                algorithmCombo.setBounds(160, 125, 180, 35);

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
                simAnnealingPanel.setBounds(35, 180, 345, 150);

                //---- initializeButton ----
                initializeButton.setText("Initialize");
                initializeButton.setFont(new Font("Sitka Text", Font.PLAIN, 16));
                initializeButton.addActionListener(e -> initialize(e));
                controlPanel.add(initializeButton);
                initializeButton.setBounds(60, 360, 120, 40);

                //---- runButton ----
                runButton.setText("Run");
                runButton.setFont(new Font("Sitka Text", Font.PLAIN, 16));
                runButton.addActionListener(e -> run(e));
                controlPanel.add(runButton);
                runButton.setBounds(225, 360, 120, 40);

                //---- messageLabel ----
                messageLabel.setForeground(Color.red);
                messageLabel.setFont(new Font("SimSun", Font.PLAIN, 16));
                messageLabel.setHorizontalAlignment(SwingConstants.CENTER);
                controlPanel.add(messageLabel);
                messageLabel.setBounds(40, 430, 335, 30);

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
            panel1.add(controlPanel);
            controlPanel.setBounds(500, 5, 411, 475);

            {
                // compute preferred size
                Dimension preferredSize = new Dimension();
                for(int i = 0; i < panel1.getComponentCount(); i++) {
                    Rectangle bounds = panel1.getComponent(i).getBounds();
                    preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                    preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                }
                Insets insets = panel1.getInsets();
                preferredSize.width += insets.right;
                preferredSize.height += insets.bottom;
                panel1.setMinimumSize(preferredSize);
                panel1.setPreferredSize(preferredSize);
            }
        }

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
            contentPaneLayout.createParallelGroup()
                .addComponent(panel1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup()
                .addComponent(panel1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        setSize(932, 532);
        setLocationRelativeTo(null);
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    // Generated using JFormDesigner Evaluation license - Amro
    private JPanel panel1;
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
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
