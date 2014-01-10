/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import Performance.Performance;
import application.Cartographer;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import javax.swing.ImageIcon;
import application.LosAlgoritmos;
import algorithms.Tools;
import java.io.IOException;

/**
 * Graphical User Interface.
 * @author EliAir
 */
public class GUI extends javax.swing.JFrame {
    private static final int MAPWIDTH = 500;
    private static final int MAPHEIGHT = 500;
    
    private Cartographer c;
    private ImageBuilder ie;
    private MapListModel mapListModel;
    private LosAlgoritmos la;
    private int[] start;
    private int[] goal;
    private char[][] charMatrix;
    private BufferedImage bf;
    private Tools Tools;
    
    /**
     * Creates new form GUI
     */
    public GUI() {        
        try { c = new Cartographer(new File("./maps/isound1.map"));
        } catch (FileNotFoundException ex) {}
        ie = new ImageBuilder();
        Tools = new Tools();
        mapListModel = new MapListModel();
        la = new LosAlgoritmos();
        initComponents();
        mapSizeLabel.setText("0x0");
        jTextArea1.setText("1. Choose map\n2. Change coordinates if desired\n "
                + "(hit when value is changed enter)\n3. Choose settings;\n "
                + "heuristics are for A* only,\n diagonal for all\n\n"
                + "HUOM! GUI ei tarkastele asioita\n"
                + "punaiset pisteet oltava valkoisella");
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        heuristicButtonGroup = new javax.swing.ButtonGroup();
        settingsPanel = new javax.swing.JPanel();
        mapScrollPane = new javax.swing.JScrollPane();
        mapList = new javax.swing.JList();
        startValPanel = new javax.swing.JPanel();
        startValField = new javax.swing.JTextField();
        goalValPanel = new javax.swing.JPanel();
        goalValField = new javax.swing.JTextField();
        dijkstraButton = new javax.swing.JButton();
        astarButton = new javax.swing.JButton();
        dijkstraDist = new javax.swing.JTextField();
        astarDist = new javax.swing.JTextField();
        dijkstraComps = new javax.swing.JTextField();
        astarComps = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        movementCheckBox = new javax.swing.JCheckBox();
        manhattanButton = new javax.swing.JRadioButton();
        chebyshev1Button = new javax.swing.JRadioButton();
        chebyshev2Button = new javax.swing.JRadioButton();
        euclideanButton = new javax.swing.JRadioButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jumpButton = new javax.swing.JButton();
        jumpDist = new javax.swing.JTextField();
        jumpComps = new javax.swing.JTextField();
        mapSizePanel = new javax.swing.JPanel();
        mapSizeLabel = new javax.swing.JLabel();
        randomButton = new javax.swing.JButton();
        mapLabel = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        settingsPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Map Settings"));

        mapList.setModel(mapListModel);
        mapList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        mapList.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                mapListValueChanged(evt);
            }
        });
        mapScrollPane.setViewportView(mapList);

        startValPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Start x,y"));

        startValField.setText("61,365");
        startValField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                startValFieldActionPerformed(evt);
            }
        });

        org.jdesktop.layout.GroupLayout startValPanelLayout = new org.jdesktop.layout.GroupLayout(startValPanel);
        startValPanel.setLayout(startValPanelLayout);
        startValPanelLayout.setHorizontalGroup(
            startValPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(startValPanelLayout.createSequentialGroup()
                .addContainerGap()
                .add(startValField, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 92, Short.MAX_VALUE)
                .addContainerGap())
        );
        startValPanelLayout.setVerticalGroup(
            startValPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(startValPanelLayout.createSequentialGroup()
                .addContainerGap()
                .add(startValField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(8, Short.MAX_VALUE))
        );

        goalValPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Goal x,y"));

        goalValField.setText("348,481");
        goalValField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                goalValFieldActionPerformed(evt);
            }
        });

        org.jdesktop.layout.GroupLayout goalValPanelLayout = new org.jdesktop.layout.GroupLayout(goalValPanel);
        goalValPanel.setLayout(goalValPanelLayout);
        goalValPanelLayout.setHorizontalGroup(
            goalValPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(goalValPanelLayout.createSequentialGroup()
                .addContainerGap()
                .add(goalValField, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 92, Short.MAX_VALUE)
                .addContainerGap())
        );
        goalValPanelLayout.setVerticalGroup(
            goalValPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(goalValPanelLayout.createSequentialGroup()
                .addContainerGap()
                .add(goalValField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(8, Short.MAX_VALUE))
        );

        dijkstraButton.setText("Dijkstra");
        dijkstraButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dijkstraButtonActionPerformed(evt);
            }
        });

        astarButton.setText("A-star");
        astarButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                astarButtonActionPerformed(evt);
            }
        });

        jLabel1.setText("Distance");

        jLabel2.setText("Comparisons");

        movementCheckBox.setSelected(true);
        movementCheckBox.setText("Allow Diagonal ");

        heuristicButtonGroup.add(manhattanButton);
        manhattanButton.setSelected(true);
        manhattanButton.setText("Manhattan");

        heuristicButtonGroup.add(chebyshev1Button);
        chebyshev1Button.setText("Chebyshev sqrt(2)");

        heuristicButtonGroup.add(chebyshev2Button);
        chebyshev2Button.setText("Chebyshev equal cost");

        heuristicButtonGroup.add(euclideanButton);
        euclideanButton.setText("Euclidean");

        jTextArea1.setEditable(false);
        jTextArea1.setColumns(20);
        jTextArea1.setFont(new java.awt.Font("Lucida Grande", 0, 11)); // NOI18N
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        jumpButton.setText("JPS");
        jumpButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jumpButtonActionPerformed(evt);
            }
        });

        mapSizePanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Map Size"));

        org.jdesktop.layout.GroupLayout mapSizePanelLayout = new org.jdesktop.layout.GroupLayout(mapSizePanel);
        mapSizePanel.setLayout(mapSizePanelLayout);
        mapSizePanelLayout.setHorizontalGroup(
            mapSizePanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 0, Short.MAX_VALUE)
            .add(mapSizePanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                .add(mapSizePanelLayout.createSequentialGroup()
                    .add(9, 9, 9)
                    .add(mapSizeLabel, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 86, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(9, Short.MAX_VALUE)))
        );
        mapSizePanelLayout.setVerticalGroup(
            mapSizePanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 42, Short.MAX_VALUE)
            .add(mapSizePanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                .add(mapSizePanelLayout.createSequentialGroup()
                    .add(7, 7, 7)
                    .add(mapSizeLabel, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 27, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(8, Short.MAX_VALUE)))
        );

        randomButton.setText("Random");
        randomButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                randomButtonActionPerformed(evt);
            }
        });

        org.jdesktop.layout.GroupLayout settingsPanelLayout = new org.jdesktop.layout.GroupLayout(settingsPanel);
        settingsPanel.setLayout(settingsPanelLayout);
        settingsPanelLayout.setHorizontalGroup(
            settingsPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(settingsPanelLayout.createSequentialGroup()
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .add(settingsPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jScrollPane1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 285, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(settingsPanelLayout.createSequentialGroup()
                        .add(mapScrollPane, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 147, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(settingsPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(settingsPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING, false)
                                .add(org.jdesktop.layout.GroupLayout.LEADING, startValPanel, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .add(org.jdesktop.layout.GroupLayout.LEADING, mapSizePanel, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .add(goalValPanel, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                    .add(settingsPanelLayout.createSequentialGroup()
                        .add(8, 8, 8)
                        .add(settingsPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(movementCheckBox)
                            .add(randomButton))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                        .add(settingsPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(chebyshev1Button)
                            .add(manhattanButton)
                            .add(chebyshev2Button)
                            .add(euclideanButton)))
                    .add(settingsPanelLayout.createSequentialGroup()
                        .add(settingsPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING, false)
                            .add(org.jdesktop.layout.GroupLayout.LEADING, astarButton, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .add(org.jdesktop.layout.GroupLayout.LEADING, dijkstraButton, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .add(jumpButton, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(settingsPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                            .add(settingsPanelLayout.createSequentialGroup()
                                .add(settingsPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                    .add(dijkstraDist, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 72, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                    .add(jLabel1))
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(settingsPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                    .add(jLabel2)
                                    .add(dijkstraComps, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 86, Short.MAX_VALUE)))
                            .add(settingsPanelLayout.createSequentialGroup()
                                .add(settingsPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING, false)
                                    .add(org.jdesktop.layout.GroupLayout.LEADING, jumpDist)
                                    .add(org.jdesktop.layout.GroupLayout.LEADING, astarDist, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 72, Short.MAX_VALUE))
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(settingsPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                    .add(astarComps)
                                    .add(jumpComps))))))
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        settingsPanelLayout.setVerticalGroup(
            settingsPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(settingsPanelLayout.createSequentialGroup()
                .addContainerGap()
                .add(settingsPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                    .add(settingsPanelLayout.createSequentialGroup()
                        .add(mapSizePanel, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .add(startValPanel, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(goalValPanel, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .add(mapScrollPane))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(settingsPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel1)
                    .add(jLabel2))
                .add(4, 4, 4)
                .add(settingsPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                    .add(settingsPanelLayout.createSequentialGroup()
                        .add(settingsPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(dijkstraButton)
                            .add(dijkstraDist, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(dijkstraComps, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                        .add(18, 18, 18)
                        .add(settingsPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(astarButton)
                            .add(astarDist, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(astarComps, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                        .add(41, 41, 41))
                    .add(settingsPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                        .add(jumpButton)
                        .add(jumpDist, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(jumpComps, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                .add(21, 21, 21)
                .add(settingsPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(manhattanButton)
                    .add(movementCheckBox))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(chebyshev1Button)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(settingsPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                    .add(chebyshev2Button)
                    .add(randomButton))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(euclideanButton)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jScrollPane1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 111, Short.MAX_VALUE)
                .addContainerGap())
        );

        mapLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        mapLabel.setAlignmentY(0.0F);

        jMenu1.setText("File");

        jMenuItem1.setText("ToFile");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Edit");
        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(mapLabel, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 613, Short.MAX_VALUE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(settingsPanel, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(mapLabel, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(settingsPanel, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Calls drawMap when goalValField event happens.
     * @param evt 
     */
    private void goalValFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_goalValFieldActionPerformed
        drawMap();
        
    }//GEN-LAST:event_goalValFieldActionPerformed
    /**
     * Calls drawMap when startValField event happens.
     * @param evt 
     */
    private void startValFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_startValFieldActionPerformed
        drawMap();
    }//GEN-LAST:event_startValFieldActionPerformed

    /**
     * Changes the map accordingly when mapList event happens.
     * Also resets astar and dijkstra text fields.
     * @param evt 
     */
    private void mapListValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_mapListValueChanged
        if (!evt.getValueIsAdjusting()) {
            System.out.println(mapList.getSelectedValue());
            drawMap();
            dijkstraDist.setText("-");
            dijkstraComps.setText("-");
            astarDist.setText("-");
            astarComps.setText("-");            
        }
    }//GEN-LAST:event_mapListValueChanged
    /**
     * Updates map with shortest route found by Dijkstra when event happens by calling updateMapWithRoute.
     * Reads the movementCheckBox to determine diagonal movement.
     * @param evt 
     */
    private void dijkstraButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dijkstraButtonActionPerformed
        updateMapWithRoute(true, LosAlgoritmos.DIJKSTRA,LosAlgoritmos.NO_HEURISTIC, movementCheckBox.isSelected());
    }//GEN-LAST:event_dijkstraButtonActionPerformed
    
    /**
     * Updates map with shortest route found by A* when event happens by calling updateMapWithRoute.
     * Reads the movementCheckBox to determine diagonal movement.
     * Reads the astarModeGroup Buttons to determine heuristic.
     * @param evt 
     */
    private void astarButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_astarButtonActionPerformed
        updateMapWithRoute(true, LosAlgoritmos.ASTAR, getChosenHeuristic(), movementCheckBox.isSelected());
    }//GEN-LAST:event_astarButtonActionPerformed

    private void jumpButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jumpButtonActionPerformed
        updateMapWithRoute(true, LosAlgoritmos.JPS, getChosenHeuristic(), true);
    }//GEN-LAST:event_jumpButtonActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        try {
            ie.exportImageToFile("exportImage", ie.updateImageWithPathAndComparisons(la.getVertexMatrix(), 2048, 2048));
        } catch (IOException ex) {
            
        }
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void randomButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_randomButtonActionPerformed
        // TODO add your handling code here:
        int[] arr = Tools.randomPoint(la.getVertexMatrix());
        this.startValField.setText(arr[1] + "," + arr[0]);
        arr = Tools.randomPoint(la.getVertexMatrix());
        this.goalValField.setText(arr[1] + "," + arr[0]);
        drawMap();
    }//GEN-LAST:event_randomButtonActionPerformed

    
    /**
     * Returns the right heuristic chosen from heuristicButtonGroup.
     */
    public int getChosenHeuristic(){
        int mode;
        if(manhattanButton.isSelected()) mode=LosAlgoritmos.MANHATTAN;
        else if(chebyshev1Button.isSelected()) mode=LosAlgoritmos.DIAGONAL;
        else if(chebyshev2Button.isSelected()) mode=LosAlgoritmos.DIAGONAL_EQUAL_COST;
        else mode=LosAlgoritmos.EUCLIDEAN;  
        return mode;
    }
    
    
    public int[] validCoordinate(int[] arr){
        return Tools.closestValidCoordinate(la.getVertexMatrix(), arr);
    }
    
    /**
     * Draws the currently loaded map. Uses the following information:
     * startValField x and y coordinates, goalValField x and y coordinates and 
     * the charMatrix loaded in the system. The map is return as a BufferedImage 
     * by ImageBuilder and drawn to mapLabel jPanel.
     */
    
    public void drawMap(){
        try {
            c.loadMap((File) mapList.getSelectedValue());
            charMatrix = c.toCharMatrix();            
            
        } catch (Exception ex) {
//            System.out.println(ex);
        }               
        int width = charMatrix[0].length;
        int height = charMatrix.length;
        mapSizeLabel.setText(""+width+"x"+height);
        
        la.loadCharMatrix(charMatrix);
        String[] sarr = startValField.getText().split(",");
        this.start = validCoordinate(new int[] {Integer.parseInt(sarr[1]),Integer.parseInt(sarr[0])});
        sarr = goalValField.getText().split(",");
        this.goal = validCoordinate(new int[] {Integer.parseInt(sarr[1]),Integer.parseInt(sarr[0])});
        
        this.startValField.setText(start[1] + "," + start[0]);
        this.goalValField.setText(goal[1] + "," + goal[0]);
        
        System.out.println(Arrays.toString(start));
        System.out.println(Arrays.toString(goal));
        
        bf = ie.buildImage(charMatrix, MAPWIDTH, MAPHEIGHT, start, goal);
        mapLabel.setIcon(new ImageIcon(bf));
    }    
    
    /**
     * Runs the routing algorithm and updates the current map with the best updateMapWithRoute.
     * Uses LosAlgoritmos class for routing and ImageBuilder to updateImageWithPath the graphical
     * representation of the map.
     */
    
    public void updateMapWithRoute(boolean comparisons, int algo, int heuristics, boolean diagonalMovement){
        try {
            la.route(start, goal, algo, heuristics, diagonalMovement);
        } catch (Exception ex) {
            System.out.println("ERROR: " + ex);
            ex.printStackTrace();
        }
        
        double d = (double) Math.round(la.getDistance() * 100) / 100;
        System.out.println("real distance: " + la.getDistance());
        if(algo==LosAlgoritmos.DIJKSTRA) {                
            dijkstraDist.setText(""+(d));   
            dijkstraComps.setText(""+la.comparisons());
        } else if (algo==LosAlgoritmos.ASTAR) {                        
            astarDist.setText(""+d);
            astarComps.setText(""+la.comparisons());
        } else if (algo==LosAlgoritmos.JPS) {
            jumpDist.setText(""+d);
            jumpComps.setText(""+la.comparisons());
        }
        
        
        
//        la.printAllDistances();
//        la.printAllToGoals();
        
                
        BufferedImage buff;
        if(comparisons) buff=ie.updateImageWithPathAndComparisons(la.getVertexMatrix(), MAPWIDTH, MAPHEIGHT);
        else buff=ie.updateImageWithPath(la.getBestroute(), MAPWIDTH, MAPHEIGHT);
        mapLabel.setIcon(new ImageIcon(buff));
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) throws Exception {

        
        
        
//        boolean performance = false;
        boolean performance = true;
        if(performance) new Performance().measure();
        else {
//[365, 61], [481, 348]
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new GUI().setVisible(true);
            }
        });
        }
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton astarButton;
    private javax.swing.JTextField astarComps;
    private javax.swing.JTextField astarDist;
    private javax.swing.JRadioButton chebyshev1Button;
    private javax.swing.JRadioButton chebyshev2Button;
    private javax.swing.JButton dijkstraButton;
    private javax.swing.JTextField dijkstraComps;
    private javax.swing.JTextField dijkstraDist;
    private javax.swing.JRadioButton euclideanButton;
    private javax.swing.JTextField goalValField;
    private javax.swing.JPanel goalValPanel;
    private javax.swing.ButtonGroup heuristicButtonGroup;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JButton jumpButton;
    private javax.swing.JTextField jumpComps;
    private javax.swing.JTextField jumpDist;
    private javax.swing.JRadioButton manhattanButton;
    private javax.swing.JLabel mapLabel;
    private javax.swing.JList mapList;
    private javax.swing.JScrollPane mapScrollPane;
    private javax.swing.JLabel mapSizeLabel;
    private javax.swing.JPanel mapSizePanel;
    private javax.swing.JCheckBox movementCheckBox;
    private javax.swing.JButton randomButton;
    private javax.swing.JPanel settingsPanel;
    private javax.swing.JTextField startValField;
    private javax.swing.JPanel startValPanel;
    // End of variables declaration//GEN-END:variables
}
