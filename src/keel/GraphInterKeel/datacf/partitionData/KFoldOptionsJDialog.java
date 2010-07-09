/**
 * <p>
 * @author Written by Pedro Antonio Gutiérrez and Juan Carlos Fernández (University of Córdoba) 23/10/2008
 * @version 1.0
 * @since JDK1.5
 * </p>
 */
package keel.GraphInterKeel.datacf.partitionData;

import javax.swing.JOptionPane;

public class KFoldOptionsJDialog extends javax.swing.JDialog {

    /**
     * <p>
     * Option dialog for K-Fold partition type
     * </p>
     */
    
    /**
     * <p>
     * Constructor that initializes the dialog
     * </p>
     */
    public KFoldOptionsJDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setLocationRelativeTo(parent);
    }

    /**
     * <p>
     * This method is called from within the constructor to
     * initialize the form.
     * </p>
     * <p>
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     * </p>
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        KFoldjPanel = new javax.swing.JPanel();
        this.setResizable(false);
        kFoldjLabel = new javax.swing.JLabel();
        kFoldjSpinner = new javax.swing.JSpinner();
        buttonsjPanel = new javax.swing.JPanel();
        acceptjButton = new javax.swing.JButton();
        canceljButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setName("Form"); // NOI18N
        setResizable(false);

        KFoldjPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "K-Fold"));
        KFoldjPanel.setName("KFoldjPanel"); // NOI18N

        kFoldjLabel.setText("K-Fold Cross Validation Value");
        kFoldjLabel.setName("kFoldjLabel"); // NOI18N

        kFoldjSpinner.setModel(new javax.swing.SpinnerNumberModel(Integer.valueOf(10), Integer.valueOf(1), null, Integer.valueOf(1)));
        kFoldjSpinner.setToolTipText("Number of Training/Test Pairs to be Generated");
        kFoldjSpinner.setName("kFoldjSpinner"); // NOI18N

        javax.swing.GroupLayout KFoldjPanelLayout = new javax.swing.GroupLayout(KFoldjPanel);
        KFoldjPanel.setLayout(KFoldjPanelLayout);
        KFoldjPanelLayout.setHorizontalGroup(
            KFoldjPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(KFoldjPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(kFoldjLabel)
                .addGap(35, 35, 35)
                .addComponent(kFoldjSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        KFoldjPanelLayout.setVerticalGroup(
            KFoldjPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(KFoldjPanelLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(KFoldjPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(kFoldjLabel)
                    .addComponent(kFoldjSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        buttonsjPanel.setName("buttonsjPanel"); // NOI18N
        buttonsjPanel.setLayout(new java.awt.GridBagLayout());

        acceptjButton.setText("Accept");
        acceptjButton.setToolTipText("Accept the Current Values");
        acceptjButton.setName("acceptjButton"); // NOI18N
        acceptjButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                acceptjButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.SOUTH;
        gridBagConstraints.insets = new java.awt.Insets(6, 39, 6, 41);
        buttonsjPanel.add(acceptjButton, gridBagConstraints);

        canceljButton.setText("Cancel");
        canceljButton.setToolTipText("Discard the Current Values");
        canceljButton.setName("canceljButton"); // NOI18N
        canceljButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                canceljButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(7, 37, 7, 37);
        buttonsjPanel.add(canceljButton, gridBagConstraints);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(buttonsjPanel, 0, 0, Short.MAX_VALUE)
            .addComponent(KFoldjPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(KFoldjPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(buttonsjPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(13, 13, 13))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

private void acceptjButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_acceptjButtonActionPerformed
    try {
        k = (Integer) kFoldjSpinner.getValue();
    } catch (Exception ex) {
        JOptionPane.showMessageDialog(this,
                "Insert an integer number as K value, please", "Error", 2);
        k = 10;
        return;
    }
    if (k <= 0) {
        JOptionPane.showMessageDialog(this,
                "Insert a positive number as K value, please", "Error", 2);
        k = 10;
        return;
    }

    setVisible(false);
}//GEN-LAST:event_acceptjButtonActionPerformed

private void canceljButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_canceljButtonActionPerformed
    setVisible(false);
    kFoldjSpinner.setValue(k);
}//GEN-LAST:event_canceljButtonActionPerformed

    /**
     * Main method
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                KFoldOptionsJDialog dialog = new KFoldOptionsJDialog(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {

                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel KFoldjPanel;
    private javax.swing.JButton acceptjButton;
    private javax.swing.JPanel buttonsjPanel;
    private javax.swing.JButton canceljButton;
    private javax.swing.JLabel kFoldjLabel;
    private javax.swing.JSpinner kFoldjSpinner;
    // End of variables declaration//GEN-END:variables
    
    /** Number of folds (k) */
    protected int k = 10;

    /**
     * <p>
     * Gets the number of folds
     * </p<
     * @return int Number of folds
     */
    public int getK() {
        return k;
    }
}
