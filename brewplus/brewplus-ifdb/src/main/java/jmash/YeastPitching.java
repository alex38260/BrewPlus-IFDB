/*
 * YeastPitching.java
 *
 * Created on 14 maggio 2007, 21.10
 */

package jmash;

import jmash.tableModel.NumberFormatter;

/**
 *
 * @author  Alessandro
 */
public class YeastPitching extends javax.swing.JInternalFrame {
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 7869435271558476471L;

	/** Creates new form YeastPitching */
    public YeastPitching() {
        initComponents();
        this.spinA.setModelFormat(1000,1,999999,1,"0","YeastPitching.A");
        this.spinB.setModelFormat(1.040,0.9,2.0,0.001,"0.000","YeastPitching.B");
        this.spinC.setModelFormat(3,0,999999,0.1,"0.00","YeastPitching.C");
        this.spinD.setModelFormat(23,1,999999,0.5,"0.0","YeastPitching.D");
        this.spinE.setModelFormat(1,0.1,999,0.1,"0.0","YeastPitching.E");
        spinBStateChanged(null);
    }
    
    
    private void calc(){
        
        double slurry=this.spinA.getDoubleValue();
        double plato=this.spinC.getDoubleValue();
        double litri=this.spinD.getDoubleValue();
        double pitch=this.spinE.getDoubleValue();
        double Q=pitch*litri*plato*1000;
        double M=pitch*litri*plato*1000/slurry;
        
        this.jTextArea1.setText("Cellule necessarie (milioni) ="+NumberFormatter.format01(Q)+
                "\nMillilitri di fondo necessari="+NumberFormatter.format01(M));
    }
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jLabel1 = new javax.swing.JLabel();
        spinA = new jmash.component.JMashSpinner();
        jLabel2 = new javax.swing.JLabel();
        spinB = new jmash.component.JMashSpinner();
        jLabel3 = new javax.swing.JLabel();
        spinC = new jmash.component.JMashSpinner();
        jLabel4 = new javax.swing.JLabel();
        spinD = new jmash.component.JMashSpinner();
        jLabel5 = new javax.swing.JLabel();
        spinE = new jmash.component.JMashSpinner();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();

        setClosable(true);
        setIconifiable(true);
        setTitle("Inoculo Lievito - Yeast Pitcher");
        setFont(getFont());
        setPreferredSize(new java.awt.Dimension(350, 300));
        getContentPane().setLayout(new java.awt.GridBagLayout());

        jLabel1.setText("Slurry (milioni di cellule/millilitro)");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        getContentPane().add(jLabel1, gridBagConstraints);

        spinA.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                spinAStateChanged(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        getContentPane().add(spinA, gridBagConstraints);

        jLabel2.setText("Densità mosto (SG)");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        getContentPane().add(jLabel2, gridBagConstraints);

        spinB.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                spinBStateChanged(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        getContentPane().add(spinB, gridBagConstraints);

        jLabel3.setText("Densità mosto (plato)");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        getContentPane().add(jLabel3, gridBagConstraints);

        spinC.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                spinCStateChanged(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        getContentPane().add(spinC, gridBagConstraints);

        jLabel4.setText("Quantità mosto (litri)");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        getContentPane().add(jLabel4, gridBagConstraints);

        spinD.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                spinDStateChanged(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        getContentPane().add(spinD, gridBagConstraints);

        jLabel5.setText("Pitch rate (milioni di cellule/millilitro/°P)");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        getContentPane().add(jLabel5, gridBagConstraints);

        spinE.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                spinEStateChanged(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        getContentPane().add(spinE, gridBagConstraints);

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        getContentPane().add(jScrollPane1, gridBagConstraints);

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    private boolean flag=false;
    private void spinBStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_spinBStateChanged
        if(this.flag==true) {
			return;
		}
        this.flag=true;
        this.spinC.setDoubleValue(Utils.SG2Plato(this.spinB.getDoubleValue()));
        calc();
        this.flag=false;
    }//GEN-LAST:event_spinBStateChanged
    
    private void spinCStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_spinCStateChanged
        if(this.flag==true) {
			return;
		}
        this.flag=true;
        this.spinB.setDoubleValue(Utils.Plato2SG(this.spinC.getDoubleValue()));
        calc();
        this.flag=false;
    }//GEN-LAST:event_spinCStateChanged
    
    private void spinEStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_spinEStateChanged
        calc();
    }//GEN-LAST:event_spinEStateChanged
    
    private void spinDStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_spinDStateChanged
        calc();
    }//GEN-LAST:event_spinDStateChanged
    
    private void spinAStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_spinAStateChanged
        calc();
    }//GEN-LAST:event_spinAStateChanged
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    private jmash.component.JMashSpinner spinA;
    private jmash.component.JMashSpinner spinB;
    private jmash.component.JMashSpinner spinC;
    private jmash.component.JMashSpinner spinD;
    private jmash.component.JMashSpinner spinE;
    // End of variables declaration//GEN-END:variables
    
}
