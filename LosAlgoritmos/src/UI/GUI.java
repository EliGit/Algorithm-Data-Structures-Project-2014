/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import application.Cartographer;
import application.ImageBuilder;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import javax.swing.ImageIcon;
import losalgoritmos.LosAlgoritmos;
import losalgoritmos.Vertex;

/**
 *
 * @author EliAir
 */
public class GUI extends javax.swing.JFrame {
    private static final int MAPWIDTH = 450;
    private static final int MAPHEIGHT = 450;
    
    private Cartographer c;
    private ImageBuilder ie;
    private MapListModel mapListModel;
    private LosAlgoritmos la;
    private int[] start;
    private int[] goal;
    private char[][] charMatrix;
    private BufferedImage bf;
    
    /**
     * Creates new form GUI
     */
    public GUI() {        
        try { c = new Cartographer(new File("./maps/isound1.map"));
        } catch (FileNotFoundException ex) {}
        ie = new ImageBuilder();
        mapListModel = new MapListModel();
        la = new LosAlgoritmos();
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
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
        mapLabel = new javax.swing.JLabel();

        org.jdesktop.layout.GroupLayout jPanel1Layout = new org.jdesktop.layout.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 100, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 100, Short.MAX_VALUE)
        );

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

        startValField.setText("35,35");
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
                .add(startValField)
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

        goalValField.setText("10,10");
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
                .add(goalValField, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 88, Short.MAX_VALUE)
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

        org.jdesktop.layout.GroupLayout settingsPanelLayout = new org.jdesktop.layout.GroupLayout(settingsPanel);
        settingsPanel.setLayout(settingsPanelLayout);
        settingsPanelLayout.setHorizontalGroup(
            settingsPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(settingsPanelLayout.createSequentialGroup()
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .add(settingsPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                    .add(settingsPanelLayout.createSequentialGroup()
                        .add(jLabel1)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                        .add(jLabel2))
                    .add(settingsPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                        .add(settingsPanelLayout.createSequentialGroup()
                            .add(mapScrollPane, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 139, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                            .add(settingsPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                                .add(goalValPanel, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .add(startValPanel, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .add(settingsPanelLayout.createSequentialGroup()
                            .add(settingsPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING, false)
                                .add(org.jdesktop.layout.GroupLayout.LEADING, astarButton, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .add(org.jdesktop.layout.GroupLayout.LEADING, dijkstraButton, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                            .add(settingsPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                .add(settingsPanelLayout.createSequentialGroup()
                                    .add(astarDist, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 72, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                    .add(astarComps))
                                .add(settingsPanelLayout.createSequentialGroup()
                                    .add(dijkstraDist, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 72, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                    .add(dijkstraComps))))))
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        settingsPanelLayout.setVerticalGroup(
            settingsPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(settingsPanelLayout.createSequentialGroup()
                .addContainerGap()
                .add(settingsPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                    .add(mapScrollPane, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 151, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(org.jdesktop.layout.GroupLayout.LEADING, settingsPanelLayout.createSequentialGroup()
                        .add(startValPanel, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(goalValPanel, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(settingsPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel1)
                    .add(jLabel2))
                .add(4, 4, 4)
                .add(settingsPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(dijkstraButton)
                    .add(dijkstraDist, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(dijkstraComps, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .add(18, 18, 18)
                .add(settingsPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(astarButton)
                    .add(astarDist, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(astarComps, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        mapLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        mapLabel.setAlignmentY(0.0F);

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(mapLabel, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 600, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(18, 18, 18)
                .add(settingsPanel, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                    .add(mapLabel, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 600, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(settingsPanel, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void goalValFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_goalValFieldActionPerformed
        drawMap();
        
    }//GEN-LAST:event_goalValFieldActionPerformed

    private void startValFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_startValFieldActionPerformed
        drawMap();
    }//GEN-LAST:event_startValFieldActionPerformed

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

    private void dijkstraButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dijkstraButtonActionPerformed
        updateMapWithRoute(true, 1);
    }//GEN-LAST:event_dijkstraButtonActionPerformed

    private void astarButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_astarButtonActionPerformed
        updateMapWithRoute(true, 2);
    }//GEN-LAST:event_astarButtonActionPerformed

    /**
     * Draws the currently loaded map. Uses the following information:
     * startValField x and y coordinates, goalValField x and y coordinates and 
     * the charMatrix loaded in the system. The map is return as a BufferedImage 
     * by ImageBuilder and drawn to mapLabel jPanel.
     */
    
    public void drawMap(){
        String[] sarr = startValField.getText().split(",");
        this.start = new int[] {Integer.parseInt(sarr[1]),Integer.parseInt(sarr[0])};
        sarr = goalValField.getText().split(",");
        this.goal = new int[] {Integer.parseInt(sarr[1]),Integer.parseInt(sarr[0])};
        
        System.out.println(Arrays.toString(start));
        System.out.println(Arrays.toString(goal));
        
        try {
            c.loadMap((File) mapList.getSelectedValue());
            charMatrix = c.toCharMatrix();
            bf = ie.buildImage(charMatrix, MAPWIDTH, MAPHEIGHT, start, goal);
            mapLabel.setIcon(new ImageIcon(bf));
        } catch (Exception ex) {
            System.out.println(ex);
        }        
    }    
    
    /**
     * Runs the routing algorithm and updates the current map with the best updateMapWithRoute.
     * Uses LosAlgoritmos class for routing and ImageBuilder to updateImageWithPath the graphical
     * representation of the map.
     */
    
    public void updateMapWithRoute(boolean comparisons, int algo){
        la.loadMap(charMatrix);
        la.loadStart(start[0], start[1]);
        la.loadGoal(goal[0], goal[1]);
        if(algo==1) {
            la.dijkstra();
            dijkstraDist.setText(""+(la.getBestroute().size()+1));   
            dijkstraComps.setText(""+la.comparisons());
        }
        else if(algo==2) {
            la.astar();
            astarDist.setText(""+(la.getBestroute().size()+1));
            astarComps.setText(""+la.comparisons());
        }
        
        la.printAllDistances();
        
        
        
        BufferedImage buff;
        if(comparisons) buff=ie.updateImageWithPathAndComparisons(la.getVertexMatrix(), MAPWIDTH, MAPHEIGHT);
        else buff=ie.updateImageWithPath(la.getBestroute(), MAPWIDTH, MAPHEIGHT);
        mapLabel.setIcon(new ImageIcon(buff));
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
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
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton astarButton;
    private javax.swing.JTextField astarComps;
    private javax.swing.JTextField astarDist;
    private javax.swing.JButton dijkstraButton;
    private javax.swing.JTextField dijkstraComps;
    private javax.swing.JTextField dijkstraDist;
    private javax.swing.JTextField goalValField;
    private javax.swing.JPanel goalValPanel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel mapLabel;
    private javax.swing.JList mapList;
    private javax.swing.JScrollPane mapScrollPane;
    private javax.swing.JPanel settingsPanel;
    private javax.swing.JTextField startValField;
    private javax.swing.JPanel startValPanel;
    // End of variables declaration//GEN-END:variables
}
