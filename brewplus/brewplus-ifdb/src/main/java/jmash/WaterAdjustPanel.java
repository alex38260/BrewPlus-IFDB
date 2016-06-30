/*
 * WaterAdjustPanel.java
 *
 * Created on 28 settembre 2007, 18.37
 */

package jmash;

import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JInternalFrame;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.apache.log4j.Logger;
import org.jdom.Document;
import org.jdom.Element;
import java.awt.Dimension;

/**
 *
 * @author Alessandro
 */
public class WaterAdjustPanel extends javax.swing.JPanel {
	private static final long serialVersionUID = 1L;
	private static Logger LOGGER = Logger.getLogger(WaterAdjustPanel.class);
	private JInternalFrame parent;
	Picker waterPicker;

	/** Creates new form WaterAdjustPanel */
	public WaterAdjustPanel(JInternalFrame parent) {
		this.parent = parent;

		this.waterPicker = new Picker(Gui.waterPickerTableModel);
		initComponents();

		spinCalcio.setModel(0, 0, 100000, 1, "0.0", "WaterAdjustPanel.spinCalcio");
		spinCloruro.setModel(0, 0, 100000, 1, "0.0", "WaterAdjustPanel.spinCloruro");
		spinMagnesio.setModel(0, 0, 100000, 1, "0.0", "WaterAdjustPanel.spinMagnesio");
		spinSodio.setModel(0, 0, 100000, 1, "0.0", "WaterAdjustPanel.spinSodio");
		spinSolfato.setModel(0, 0, 100000, 1, "0.0", "WaterAdjustPanel.spinSolfato");
		spinCarb.setModel(0, 0, 100000, 1, "0.0", "WaterAdjustPanel.spinCarb");
		spinCarb1.setModel(0, 0, 100000, 1, "0.0", "WaterAdjustPanel.spinCarb1");
		spinCarb2.setModel(0, 0, 100000, 1, "0.0", "WaterAdjustPanel.spinCarb2");
		spinCalcio1.setModel(0, 0, 100000, 1, "0.0", "WaterAdjustPanel.spinCalcio1");
		spinCloruro1.setModel(0, 0, 100000, 1, "0.0", "WaterAdjustPanel.spinCloruro1");
		spinMagnesio1.setModel(0, 0, 100000, 1, "0.0", "WaterAdjustPanel.spinMagnesio1");
		spinSodio1.setModel(0, 0, 100000, 1, "0.0", "WaterAdjustPanel.spinSodio1");
		spinSolfato1.setModel(0, 0, 100000, 1, "0.0", "WaterAdjustPanel.spinSolfato1");
		spinCalcio2.setModel(0, 0, 100000, 1, "0.0", "WaterAdjustPanel.spinCalcio2");
		spinCloruro2.setModel(0, 0, 100000, 1, "0.0", "WaterAdjustPanel.spinCloruro2");
		spinMagnesio2.setModel(0, 0, 100000, 1, "0.0", "WaterAdjustPanel.spinMagnesio2");
		spinSodio2.setModel(0, 0, 100000, 1, "0.0", "WaterAdjustPanel.spinSodio2");
		spinSolfato2.setModel(0, 0, 100000, 1, "0.0", "WaterAdjustPanel.spinSolfato2");
		spnVolume.setModel(40, 1, 999999, 1, "0.0", "WaterAdjustPanel.spnVolume");

		spnCaCl2.setModel(0, 0, 999999, 1, "0.0", "WaterAdjustPanel.spnVolume");
		spnChalk.setModel(0, 0, 999999, 1, "0.0", "WaterAdjustPanel.spnChalk");
		spnEpsom.setModel(0, 0, 999999, 1, "0.0", "WaterAdjustPanel.spnEpsom");
		spnGypsum.setModel(0, 0, 999999, 1, "0.0", "WaterAdjustPanel.spnGypsum");
		spnNaCl.setModel(0, 0, 999999, 1, "0.0", "WaterAdjustPanel.spnNaCl");
		spnSoda.setModel(0, 0, 999999, 1, "0.0", "WaterAdjustPanel.spnSoda");
		
		spnLacticAcid.setModel(0, 0, 999999, 1, "0.0", "WaterAdjustPanel.spnLacticAcid");
		spnLacticAcidContent.setModel(0, 0, 100, 1, "0.0", "WaterAdjustPanel.spnLacticAcidContent");
		spnCitrusAcid.setModel(0, 0, 999999, 1, "0.0", "WaterAdjustPanel.spnCitrusAcid");
		spnCitrusAcidContent.setModel(0, 0, 100, 1, "0.0", "WaterAdjustPanel.spnCitrusAcidContent");
		spnAcidulatedMaltContent.setModel(0, 0, 100, 1, "0.0", "WaterAdjustPanel.spnAcidulatedMaltContent");
		
		setBackground(getBackground().darker());
		thread = new Thread() {
			@Override
			public void run() {
				List<WaterProfile> L = new ArrayList<WaterProfile>();
				while (flag) {
					if (work) {
						WaterProfile source = new WaterProfile(spinCalcio.getIntegerValue(),
								spinMagnesio.getIntegerValue(), spinSolfato.getIntegerValue(),
								spinCloruro.getIntegerValue(), spinSodio.getIntegerValue(), spinCarb.getIntegerValue());
						if (flagRes) {
							flagRes = false;
							res = null;
							L.clear();
						}
						if (res != null)
							source = res;
						WaterProfile dest = new WaterProfile(spinCalcio1.getIntegerValue(),
								spinMagnesio1.getIntegerValue(), spinSolfato1.getIntegerValue(),
								spinCloruro1.getIntegerValue(), spinSodio1.getIntegerValue(),
								spinCarb1.getIntegerValue());
						double LITRI = spnVolume.getVolume();

						source.setPCalcio(pCalcio.getValue());
						source.setPSolfato(pSolfato.getValue());
						source.setPSodio(pSodio.getValue());
						source.setPCloruro(pCloruro.getValue());
						source.setPMagnesio(pMagnesio.getValue());
						source.setPCarbonato(pCarbonato.getValue());

						source.setUseGypsum(useGypsum.isSelected());
						source.setUseEpsom(useEpsom.isSelected());
						source.setUseSale(useNaCl.isSelected());
						source.setUseCalciumChloride(useCaCl2.isSelected());
						source.setUseChalk(useChalk.isSelected());
						source.setUseSoda(useSoda.isSelected());

						res = source.target(dest, LITRI, "", 100, L, 100);
						setTreatment(res);
					}
					try {
						sleep(500);
					} catch (InterruptedException ex) {
						LOGGER.error(ex.getMessage(), ex);
					}
				}
			}
		};
		thread.start();
	}

	/**
	 * This method is called from within the constructor to initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is always
	 * regenerated by the Form Editor.
	 */
	// <editor-fold defaultstate="collapsed" desc="Generated
	// Code">//GEN-BEGIN:initComponents
	private void initComponents() {
		java.awt.GridBagConstraints gridBagConstraints;

		fromPanel = new javax.swing.JPanel();
		jLabel1 = new javax.swing.JLabel();
		spinCalcio = new jmash.component.JMashSpinner();
		jLabel2 = new javax.swing.JLabel();
		spinMagnesio = new jmash.component.JMashSpinner();
		jLabel3 = new javax.swing.JLabel();
		spinSolfato = new jmash.component.JMashSpinner();
		jLabel4 = new javax.swing.JLabel();
		spinCloruro = new jmash.component.JMashSpinner();
		jLabel5 = new javax.swing.JLabel();
		spinSodio = new jmash.component.JMashSpinner();
		jLabel28 = new javax.swing.JLabel();
		spinCarb = new jmash.component.JMashSpinner();
		btnA = new javax.swing.JButton();
		jButton1 = new javax.swing.JButton();
		destPanel = new javax.swing.JPanel();
		jLabel6 = new javax.swing.JLabel();
		spinCalcio1 = new jmash.component.JMashSpinner();
		jLabel7 = new javax.swing.JLabel();
		spinMagnesio1 = new jmash.component.JMashSpinner();
		jLabel8 = new javax.swing.JLabel();
		spinSolfato1 = new jmash.component.JMashSpinner();
		jLabel9 = new javax.swing.JLabel();
		spinCloruro1 = new jmash.component.JMashSpinner();
		jLabel10 = new javax.swing.JLabel();
		spinSodio1 = new jmash.component.JMashSpinner();
		jLabel29 = new javax.swing.JLabel();
		spinCarb1 = new jmash.component.JMashSpinner();
		btnB = new javax.swing.JButton();
		jButton2 = new javax.swing.JButton();
		jPanel4 = new javax.swing.JPanel();
		jLabel15 = new javax.swing.JLabel();
		spinCalcio2 = new jmash.component.JMashSpinner();
		jLabel16 = new javax.swing.JLabel();
		spinMagnesio2 = new jmash.component.JMashSpinner();
		jLabel17 = new javax.swing.JLabel();
		spinSolfato2 = new jmash.component.JMashSpinner();
		jLabel18 = new javax.swing.JLabel();
		spinCloruro2 = new jmash.component.JMashSpinner();
		jLabel19 = new javax.swing.JLabel();
		spinSodio2 = new jmash.component.JMashSpinner();
		jLabel30 = new javax.swing.JLabel();
		spinCarb2 = new jmash.component.JMashSpinner();
		jToggleButton1 = new javax.swing.JToggleButton();
		jButton3 = new javax.swing.JButton();
		jPanel5 = new javax.swing.JPanel();
		jPanelPh = new javax.swing.JPanel();
		jPanelAdjustPh = new javax.swing.JPanel();
		jLabel20 = new javax.swing.JLabel();
		pCalcio = new javax.swing.JSlider();
		pMagnesio = new javax.swing.JSlider();
		pSolfato = new javax.swing.JSlider();
		pCloruro = new javax.swing.JSlider();
		pSodio = new javax.swing.JSlider();
		jLabel21 = new javax.swing.JLabel();
		jLabel22 = new javax.swing.JLabel();
		jLabel23 = new javax.swing.JLabel();
		jLabel24 = new javax.swing.JLabel();
		pCarbonato = new javax.swing.JSlider();
		jLabel31 = new javax.swing.JLabel();
		jPanel1 = new javax.swing.JPanel();
		jLabel38 = new javax.swing.JLabel();
		jLabel39 = new javax.swing.JLabel();
		jLabel40 = new javax.swing.JLabel();
		jLabel41 = new javax.swing.JLabel();
		jLabel42 = new javax.swing.JLabel();
		jLabel43 = new javax.swing.JLabel();
		jLabel44 = new javax.swing.JLabel();
		jLabel45 = new javax.swing.JLabel();
		jLabel46 = new javax.swing.JLabel();
		jLabel47 = new javax.swing.JLabel();
		jLabel48 = new javax.swing.JLabel();
		jLabel49 = new javax.swing.JLabel();
		jLabel50 = new javax.swing.JLabel();
		jLabel11 = new javax.swing.JLabel();
		jLabel12 = new javax.swing.JLabel();
		jLabel13 = new javax.swing.JLabel();
		jLabel14 = new javax.swing.JLabel();
		jLabel26 = new javax.swing.JLabel();
		jLabel27 = new javax.swing.JLabel();
		jLabel32 = new javax.swing.JLabel();
		jLabel33 = new javax.swing.JLabel();
		jLabel34 = new javax.swing.JLabel();
		jLabel35 = new javax.swing.JLabel();
		jLabel36 = new javax.swing.JLabel();
		jLabel37 = new javax.swing.JLabel();
		useGypsum = new javax.swing.JCheckBox();
		useEpsom = new javax.swing.JCheckBox();
		useCaCl2 = new javax.swing.JCheckBox();
		useNaCl = new javax.swing.JCheckBox();
		useChalk = new javax.swing.JCheckBox();
		useSoda = new javax.swing.JCheckBox();
		spnGypsum = new jmash.component.JMashSpinner();
		spnEpsom = new jmash.component.JMashSpinner();
		spnCaCl2 = new jmash.component.JMashSpinner();
		spnNaCl = new jmash.component.JMashSpinner();
		spnChalk = new jmash.component.JMashSpinner();
		spnSoda = new jmash.component.JMashSpinner();
		spnVolume = new jmash.component.JVolumeSpinner();
		
		spnAcidulatedMaltContent = new jmash.component.JVolumeSpinner();
		spnLacticAcid = new jmash.component.JVolumeSpinner();
		spnLacticAcidContent = new jmash.component.JVolumeSpinner();
		spnCitrusAcid = new jmash.component.JVolumeSpinner();
		spnCitrusAcidContent = new jmash.component.JVolumeSpinner();
		
		txtAcidMalt = new JTextField();
		txtAcidMalt.setHorizontalAlignment(JTextField.RIGHT);
		txtRA = new JTextField();
		txtRA.setHorizontalAlignment(JTextField.CENTER);
		txtAlk = new JTextField();
		txtAlk.setHorizontalAlignment(JTextField.CENTER);
		txtPH = new JTextField();
		txtPH.setHorizontalAlignment(JTextField.CENTER);

		//setMaximumSize(new java.awt.Dimension(646, 409));
		//setMinimumSize(new java.awt.Dimension(646, 409));
		//setPreferredSize(new java.awt.Dimension(660, 65));
		setLayout(new java.awt.GridBagLayout());

		fromPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Partenza - ppm",
				javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.ABOVE_TOP,
				new java.awt.Font("Tahoma", 1, 11))); // NOI18N
		fromPanel.setLayout(new java.awt.GridBagLayout());

		jLabel1.setFont(jLabel1.getFont());
		jLabel1.setText("Calcio");
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.insets = new java.awt.Insets(4, 4, 4, 4);
		fromPanel.add(jLabel1, gridBagConstraints);

		spinCalcio.setPreferredSize(new java.awt.Dimension(80, 20));
		spinCalcio.addChangeListener(new javax.swing.event.ChangeListener() {
			public void stateChanged(javax.swing.event.ChangeEvent evt) {
				spinCalcioStateChanged(evt);
			}
		});
		fromPanel.add(spinCalcio, new java.awt.GridBagConstraints());

		jLabel2.setText("Magnesio");
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.insets = new java.awt.Insets(4, 4, 4, 4);
		fromPanel.add(jLabel2, gridBagConstraints);

		spinMagnesio.setPreferredSize(new java.awt.Dimension(80, 20));
		spinMagnesio.addChangeListener(new javax.swing.event.ChangeListener() {
			public void stateChanged(javax.swing.event.ChangeEvent evt) {
				spinMagnesioStateChanged(evt);
			}
		});
		fromPanel.add(spinMagnesio, new java.awt.GridBagConstraints());

		jLabel3.setText("Solfato");
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.insets = new java.awt.Insets(4, 4, 4, 4);
		fromPanel.add(jLabel3, gridBagConstraints);

		spinSolfato.setFont(spinSolfato.getFont());
		spinSolfato.setPreferredSize(new java.awt.Dimension(80, 20));
		spinSolfato.addChangeListener(new javax.swing.event.ChangeListener() {
			public void stateChanged(javax.swing.event.ChangeEvent evt) {
				spinSolfatoStateChanged(evt);
			}
		});
		fromPanel.add(spinSolfato, new java.awt.GridBagConstraints());

		jLabel4.setText("Cloruro");
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.insets = new java.awt.Insets(4, 4, 4, 4);
		fromPanel.add(jLabel4, gridBagConstraints);

		spinCloruro.setPreferredSize(new java.awt.Dimension(80, 20));
		spinCloruro.addChangeListener(new javax.swing.event.ChangeListener() {
			public void stateChanged(javax.swing.event.ChangeEvent evt) {
				spinCloruroStateChanged(evt);
			}
		});
		fromPanel.add(spinCloruro, new java.awt.GridBagConstraints());

		jLabel5.setFont(jLabel5.getFont());
		jLabel5.setText("Sodio");
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.insets = new java.awt.Insets(4, 4, 4, 4);
		fromPanel.add(jLabel5, gridBagConstraints);

		spinSodio.setFont(spinSodio.getFont());
		spinSodio.setPreferredSize(new java.awt.Dimension(80, 20));
		spinSodio.addChangeListener(new javax.swing.event.ChangeListener() {
			public void stateChanged(javax.swing.event.ChangeEvent evt) {
				spinSodioStateChanged(evt);
			}
		});
		fromPanel.add(spinSodio, new java.awt.GridBagConstraints());

		jLabel28.setText("Carb.");
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.insets = new java.awt.Insets(4, 4, 4, 4);
		fromPanel.add(jLabel28, gridBagConstraints);

		spinCarb.setPreferredSize(new java.awt.Dimension(80, 20));
		spinCarb.addChangeListener(new javax.swing.event.ChangeListener() {
			public void stateChanged(javax.swing.event.ChangeEvent evt) {
				spinCarbStateChanged(evt);
			}
		});
		fromPanel.add(spinCarb, new java.awt.GridBagConstraints());

		btnA.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jmash/images/filefind.png"))); // NOI18N
		btnA.setToolTipText("Apri...");
		btnA.setBorderPainted(false);
		btnA.setContentAreaFilled(false);
		btnA.setMaximumSize(new java.awt.Dimension(37, 35));
		btnA.setMinimumSize(new java.awt.Dimension(37, 35));
		btnA.setPreferredSize(new java.awt.Dimension(37, 35));
		btnA.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnAActionPerformed(evt);
			}
		});
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
		fromPanel.add(btnA, gridBagConstraints);

		jButton1.setText("?");
		jButton1.setPreferredSize(new java.awt.Dimension(35, 30));
		jButton1.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButton1ActionPerformed(evt);
			}
		});
		fromPanel.add(jButton1, new java.awt.GridBagConstraints());

		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 0;
		gridBagConstraints.gridwidth = 3;
		gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
		gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
		add(fromPanel, gridBagConstraints);

		destPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Target - ppm",
				javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.ABOVE_TOP,
				new java.awt.Font("Tahoma", 1, 11))); // NOI18N
		destPanel.setLayout(new java.awt.GridBagLayout());

		jLabel6.setText("Calcio");
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.insets = new java.awt.Insets(4, 4, 4, 4);
		destPanel.add(jLabel6, gridBagConstraints);

		spinCalcio1.setPreferredSize(new java.awt.Dimension(80, 20));
		spinCalcio1.addChangeListener(new javax.swing.event.ChangeListener() {
			public void stateChanged(javax.swing.event.ChangeEvent evt) {
				spinCalcio1StateChanged(evt);
			}
		});
		destPanel.add(spinCalcio1, new java.awt.GridBagConstraints());

		jLabel7.setText("Magnesio");
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.insets = new java.awt.Insets(4, 4, 4, 4);
		destPanel.add(jLabel7, gridBagConstraints);

		spinMagnesio1.setPreferredSize(new java.awt.Dimension(80, 20));
		spinMagnesio1.addChangeListener(new javax.swing.event.ChangeListener() {
			public void stateChanged(javax.swing.event.ChangeEvent evt) {
				spinMagnesio1StateChanged(evt);
			}
		});
		destPanel.add(spinMagnesio1, new java.awt.GridBagConstraints());

		jLabel8.setText("Solfato");
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.insets = new java.awt.Insets(4, 4, 4, 4);
		destPanel.add(jLabel8, gridBagConstraints);

		spinSolfato1.setPreferredSize(new java.awt.Dimension(80, 20));
		spinSolfato1.addChangeListener(new javax.swing.event.ChangeListener() {
			public void stateChanged(javax.swing.event.ChangeEvent evt) {
				spinSolfato1StateChanged(evt);
			}
		});
		destPanel.add(spinSolfato1, new java.awt.GridBagConstraints());

		jLabel9.setText("Cloruro");
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.insets = new java.awt.Insets(4, 4, 4, 4);
		destPanel.add(jLabel9, gridBagConstraints);

		spinCloruro1.setPreferredSize(new java.awt.Dimension(80, 20));
		spinCloruro1.addChangeListener(new javax.swing.event.ChangeListener() {
			public void stateChanged(javax.swing.event.ChangeEvent evt) {
				spinCloruro1StateChanged(evt);
			}
		});
		destPanel.add(spinCloruro1, new java.awt.GridBagConstraints());

		jLabel10.setText("Sodio");
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.insets = new java.awt.Insets(4, 4, 4, 4);
		destPanel.add(jLabel10, gridBagConstraints);

		spinSodio1.setPreferredSize(new java.awt.Dimension(80, 20));
		spinSodio1.addChangeListener(new javax.swing.event.ChangeListener() {
			public void stateChanged(javax.swing.event.ChangeEvent evt) {
				spinSodio1StateChanged(evt);
			}
		});
		destPanel.add(spinSodio1, new java.awt.GridBagConstraints());

		jLabel29.setText("Carb.");
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.insets = new java.awt.Insets(4, 4, 4, 4);
		destPanel.add(jLabel29, gridBagConstraints);

		spinCarb1.setPreferredSize(new java.awt.Dimension(80, 20));
		spinCarb1.addChangeListener(new javax.swing.event.ChangeListener() {
			public void stateChanged(javax.swing.event.ChangeEvent evt) {
				spinCarb1StateChanged(evt);
			}
		});
		destPanel.add(spinCarb1, new java.awt.GridBagConstraints());

		btnB.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jmash/images/filefind.png"))); // NOI18N
		btnB.setToolTipText("Apri...");
		btnB.setBorder(null);
		btnB.setBorderPainted(false);
		btnB.setContentAreaFilled(false);
		btnB.setMaximumSize(new java.awt.Dimension(37, 35));
		btnB.setMinimumSize(new java.awt.Dimension(37, 35));
		btnB.setPreferredSize(new java.awt.Dimension(37, 35));
		btnB.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnBActionPerformed(evt);
			}
		});
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
		destPanel.add(btnB, gridBagConstraints);

		jButton2.setText("?");
		jButton2.setPreferredSize(new java.awt.Dimension(35, 30));
		jButton2.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButton2ActionPerformed(evt);
			}
		});
		destPanel.add(jButton2, new java.awt.GridBagConstraints());

		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 1;
		gridBagConstraints.gridwidth = 3;
		gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
		gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
		add(destPanel, gridBagConstraints);

		jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Miglior approssimazione trovata - ppm",
				javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.ABOVE_TOP,
				new java.awt.Font("Tahoma", 1, 11))); // NOI18N
		jPanel4.setLayout(new java.awt.GridBagLayout());

		jLabel15.setText("Calcio");
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.insets = new java.awt.Insets(4, 4, 4, 4);
		jPanel4.add(jLabel15, gridBagConstraints);

		spinCalcio2.setEnabled(false);
		spinCalcio2.setPreferredSize(new java.awt.Dimension(80, 20));
		jPanel4.add(spinCalcio2, new java.awt.GridBagConstraints());

		jLabel16.setText("Magnesio");
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.insets = new java.awt.Insets(4, 4, 4, 4);
		jPanel4.add(jLabel16, gridBagConstraints);

		spinMagnesio2.setEnabled(false);
		spinMagnesio2.setPreferredSize(new java.awt.Dimension(80, 20));
		jPanel4.add(spinMagnesio2, new java.awt.GridBagConstraints());

		jLabel17.setText("Solfato");
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.insets = new java.awt.Insets(4, 4, 4, 4);
		jPanel4.add(jLabel17, gridBagConstraints);

		spinSolfato2.setEnabled(false);
		spinSolfato2.setPreferredSize(new java.awt.Dimension(80, 20));
		jPanel4.add(spinSolfato2, new java.awt.GridBagConstraints());

		jLabel18.setText("Cloruro");
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.insets = new java.awt.Insets(4, 4, 4, 4);
		jPanel4.add(jLabel18, gridBagConstraints);

		spinCloruro2.setEnabled(false);
		spinCloruro2.setPreferredSize(new java.awt.Dimension(80, 20));
		jPanel4.add(spinCloruro2, new java.awt.GridBagConstraints());

		jLabel19.setText("Sodio");
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.insets = new java.awt.Insets(4, 4, 4, 4);
		jPanel4.add(jLabel19, gridBagConstraints);

		spinSodio2.setEnabled(false);
		spinSodio2.setPreferredSize(new java.awt.Dimension(80, 20));
		jPanel4.add(spinSodio2, new java.awt.GridBagConstraints());

		jLabel30.setText("Carb.");
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.insets = new java.awt.Insets(4, 4, 4, 4);
		jPanel4.add(jLabel30, gridBagConstraints);

		spinCarb2.setEnabled(false);
		spinCarb2.setPreferredSize(new java.awt.Dimension(80, 20));
		spinCarb2.addChangeListener(new javax.swing.event.ChangeListener() {
			public void stateChanged(javax.swing.event.ChangeEvent evt) {
				spinCarb2StateChanged(evt);
			}
		});
		jPanel4.add(spinCarb2, new java.awt.GridBagConstraints());

		jToggleButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jmash/images/gear.png"))); // NOI18N
		jToggleButton1.setToolTipText("KEY work! : RB jmash/lang");
		jToggleButton1.setMargin(new java.awt.Insets(2, 4, 2, 4));
		jToggleButton1.setMaximumSize(new java.awt.Dimension(37, 35));
		jToggleButton1.setMinimumSize(new java.awt.Dimension(37, 35));
		jToggleButton1.setPreferredSize(new java.awt.Dimension(37, 35));
		jToggleButton1.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jToggleButton1ActionPerformed(evt);
			}
		});
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
		gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
		jPanel4.add(jToggleButton1, gridBagConstraints);

		jButton3.setText("?");
		jButton3.setPreferredSize(new java.awt.Dimension(35, 30));
		jButton3.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButton3ActionPerformed(evt);
			}
		});
		jPanel4.add(jButton3, new java.awt.GridBagConstraints());

		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 2;
		gridBagConstraints.gridwidth = 3;
		gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
		gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
		add(jPanel4, gridBagConstraints);

		jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Priorità di approssimazione",
				javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.ABOVE_TOP,
				new java.awt.Font("Tahoma", 1, 11))); // NOI18N
		jPanel5.setLayout(new java.awt.GridBagLayout());

		jLabel20.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
		jLabel20.setText("Calcio");
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
		jPanel5.add(jLabel20, gridBagConstraints);

		pCalcio.setMajorTickSpacing(10);
		pCalcio.setMinorTickSpacing(5);
		pCalcio.setSnapToTicks(true);
		pCalcio.setPreferredSize(new java.awt.Dimension(280, 24));
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 1;
		gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
		jPanel5.add(pCalcio, gridBagConstraints);

		pMagnesio.setMajorTickSpacing(10);
		pMagnesio.setMinorTickSpacing(5);
		pMagnesio.setSnapToTicks(true);
		pMagnesio.setPreferredSize(new java.awt.Dimension(280, 24));
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 1;
		gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
		jPanel5.add(pMagnesio, gridBagConstraints);

		pSolfato.setMajorTickSpacing(10);
		pSolfato.setMinorTickSpacing(5);
		pSolfato.setSnapToTicks(true);
		pSolfato.setPreferredSize(new java.awt.Dimension(280, 24));
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 1;
		gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
		jPanel5.add(pSolfato, gridBagConstraints);

		pCloruro.setMajorTickSpacing(10);
		pCloruro.setMinorTickSpacing(5);
		pCloruro.setSnapToTicks(true);
		pCloruro.setPreferredSize(new java.awt.Dimension(280, 24));
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 1;
		gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
		jPanel5.add(pCloruro, gridBagConstraints);

		pSodio.setMajorTickSpacing(10);
		pSodio.setMinorTickSpacing(5);
		pSodio.setSnapToTicks(true);
		pSodio.setPreferredSize(new java.awt.Dimension(280, 24));
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 1;
		gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
		jPanel5.add(pSodio, gridBagConstraints);

		jLabel21.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
		jLabel21.setText("Magnesio");
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
		jPanel5.add(jLabel21, gridBagConstraints);

		jLabel22.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
		jLabel22.setText("Solfato");
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
		jPanel5.add(jLabel22, gridBagConstraints);

		jLabel23.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
		jLabel23.setText("Cloruro");
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
		jPanel5.add(jLabel23, gridBagConstraints);

		jLabel24.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
		jLabel24.setText("Sodio");
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
		jPanel5.add(jLabel24, gridBagConstraints);

		pCarbonato.setMajorTickSpacing(10);
		pCarbonato.setMinorTickSpacing(5);
		pCarbonato.setSnapToTicks(true);
		pCarbonato.setPreferredSize(new java.awt.Dimension(280, 24));
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 1;
		gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
		jPanel5.add(pCarbonato, gridBagConstraints);

		jLabel31.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
		jLabel31.setText("Carbonato");
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
		jPanel5.add(jLabel31, gridBagConstraints);

		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 3;
		gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
		gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
		add(jPanel5, gridBagConstraints);

		jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Ottenuta tramite queste aggiunte",
				javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.ABOVE_TOP,
				new java.awt.Font("Tahoma", 1, 11))); // NOI18N
		jPanel1.setLayout(new java.awt.GridBagLayout());

		jLabel38.setText("Quantità");
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridx = 0;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
		gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
		jPanel1.add(jLabel38, gridBagConstraints);

		jLabel11.setText("Gypsum");
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 1;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
		gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
		jPanel1.add(jLabel11, gridBagConstraints);

		jLabel12.setText("Epsom");
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 2;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
		gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
		jPanel1.add(jLabel12, gridBagConstraints);

		jLabel13.setText("Cloruro di calcio");
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 3;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
		gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
		jPanel1.add(jLabel13, gridBagConstraints);

		jLabel14.setText("Cloruro di sodio");
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 4;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
		gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
		jPanel1.add(jLabel14, gridBagConstraints);

		jLabel26.setText("Carbonato di calcio");
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 5;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
		gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
		jPanel1.add(jLabel26, gridBagConstraints);

		jLabel27.setText("Bicarbonato di sodio");
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 6;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
		gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
		jPanel1.add(jLabel27, gridBagConstraints);
		
		jLabel32.setText("grammi");
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 2;
		gridBagConstraints.gridy = 1;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
		gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
		jPanel1.add(jLabel32, gridBagConstraints);

		jLabel33.setText("grammi");
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 2;
		gridBagConstraints.gridy = 2;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
		gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
		jPanel1.add(jLabel33, gridBagConstraints);

		jLabel34.setText("grammi");
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 2;
		gridBagConstraints.gridy = 3;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
		gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
		jPanel1.add(jLabel34, gridBagConstraints);

		jLabel35.setText("grammi");
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 2;
		gridBagConstraints.gridy = 4;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
		gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
		jPanel1.add(jLabel35, gridBagConstraints);

		jLabel36.setText("grammi");
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 2;
		gridBagConstraints.gridy = 5;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
		gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
		jPanel1.add(jLabel36, gridBagConstraints);

		jLabel37.setText("grammi");
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 2;
		gridBagConstraints.gridy = 6;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
		gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
		jPanel1.add(jLabel37, gridBagConstraints);

		useGypsum.setSelected(true);
		useGypsum.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
		useGypsum.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				useGypsumActionPerformed(evt);
			}
		});
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 3;
		gridBagConstraints.gridy = 1;
		gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
		jPanel1.add(useGypsum, gridBagConstraints);

		useEpsom.setSelected(true);
		useEpsom.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
		useEpsom.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				useEpsomActionPerformed(evt);
			}
		});
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 3;
		gridBagConstraints.gridy = 2;
		gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
		jPanel1.add(useEpsom, gridBagConstraints);

		useCaCl2.setSelected(true);
		useCaCl2.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
		useCaCl2.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				useCaCl2ActionPerformed(evt);
			}
		});
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 3;
		gridBagConstraints.gridy = 3;
		gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
		jPanel1.add(useCaCl2, gridBagConstraints);

		useNaCl.setSelected(true);
		useNaCl.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
		useNaCl.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				useNaClActionPerformed(evt);
			}
		});
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 3;
		gridBagConstraints.gridy = 4;
		gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
		jPanel1.add(useNaCl, gridBagConstraints);

		useChalk.setSelected(true);
		useChalk.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
		useChalk.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				useChalkActionPerformed(evt);
			}
		});
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 3;
		gridBagConstraints.gridy = 5;
		gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
		jPanel1.add(useChalk, gridBagConstraints);

		useSoda.setSelected(true);
		useSoda.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
		useSoda.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				useSodaActionPerformed(evt);
			}
		});
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 3;
		gridBagConstraints.gridy = 6;
		gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
		jPanel1.add(useSoda, gridBagConstraints);
		

		spnGypsum.setPreferredSize(new Dimension(77, 22));
		spnGypsum.addChangeListener(new javax.swing.event.ChangeListener() {
			public void stateChanged(javax.swing.event.ChangeEvent evt) {
				spnGypsumStateChanged(evt);
			}
		});
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 1;
		gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
		gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
		jPanel1.add(spnGypsum, gridBagConstraints);

		spnEpsom.setPreferredSize(new java.awt.Dimension(64, 22));
		spnEpsom.addChangeListener(new javax.swing.event.ChangeListener() {
			public void stateChanged(javax.swing.event.ChangeEvent evt) {
				spnEpsomStateChanged(evt);
			}
		});
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 2;
		gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
		gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
		jPanel1.add(spnEpsom, gridBagConstraints);

		spnCaCl2.setPreferredSize(new java.awt.Dimension(64, 22));
		spnCaCl2.addChangeListener(new javax.swing.event.ChangeListener() {
			public void stateChanged(javax.swing.event.ChangeEvent evt) {
				spnCaCl2StateChanged(evt);
			}
		});
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 3;
		gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
		gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
		jPanel1.add(spnCaCl2, gridBagConstraints);

		spnNaCl.setPreferredSize(new java.awt.Dimension(64, 22));
		spnNaCl.addChangeListener(new javax.swing.event.ChangeListener() {
			public void stateChanged(javax.swing.event.ChangeEvent evt) {
				spnNaClStateChanged(evt);
			}
		});
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 4;
		gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
		gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
		jPanel1.add(spnNaCl, gridBagConstraints);

		spnChalk.setPreferredSize(new java.awt.Dimension(64, 22));
		spnChalk.addChangeListener(new javax.swing.event.ChangeListener() {
			public void stateChanged(javax.swing.event.ChangeEvent evt) {
				spnChalkStateChanged(evt);
			}
		});
		spnChalk.addAncestorListener(new javax.swing.event.AncestorListener() {
			public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
			}

			public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
				spnChalkAncestorAdded(evt);
			}

			public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
			}
		});
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 5;
		gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
		gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
		jPanel1.add(spnChalk, gridBagConstraints);

		spnSoda.setPreferredSize(new java.awt.Dimension(64, 22));
		spnSoda.addChangeListener(new javax.swing.event.ChangeListener() {
			public void stateChanged(javax.swing.event.ChangeEvent evt) {
				spnSodaStateChanged(evt);
			}
		});
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 6;
		gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
		gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
		jPanel1.add(spnSoda, gridBagConstraints);

		spnVolume.addChangeListener(new javax.swing.event.ChangeListener() {
			public void stateChanged(javax.swing.event.ChangeEvent evt) {
				spnVolumeStateChanged(evt);
			}
		});
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 0;
		gridBagConstraints.gridwidth = 3;
		gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
		gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
		jPanel1.add(spnVolume, gridBagConstraints);

		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 3;
		gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
		gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
		add(jPanel1, gridBagConstraints);
		
		
		jPanelPh.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Dati pH",
				javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.ABOVE_TOP,
				new java.awt.Font("Tahoma", 1, 11))); // NOI18N
		
//		GridBagLayout gbl_panel = new GridBagLayout();
//		gbl_panel.columnWidths = new int[]{0, 0};
//		gbl_panel.rowHeights = new int[]{0, 0};
//		gbl_panel.columnWeights = new double[]{0.0, Double.MIN_VALUE};
//		gbl_panel.rowWeights = new double[]{0.0, Double.MIN_VALUE};
//		
		jPanelPh.setLayout(new GridBagLayout());
		
		
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 2;
		gridBagConstraints.gridy = 3;
		gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
		gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
		add(jPanelPh, gridBagConstraints);
		
		jLabel42.setText("Malti acidi");
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 0;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
		gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
		jPanelPh.add(jLabel42, gridBagConstraints);
		txtAcidMalt.setPreferredSize(new Dimension(77, 22));
		txtAcidMalt.setEditable(false);
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 0;
		gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
		gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
		jPanelPh.add(txtAcidMalt, gridBagConstraints);
		jLabel43.setText("grammi");
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 2;
		gridBagConstraints.gridy = 0;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
		gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
		jPanelPh.add(jLabel43, gridBagConstraints);
		spnAcidulatedMaltContent.setPreferredSize(new Dimension(77, 22));
		spnAcidulatedMaltContent.addChangeListener(new javax.swing.event.ChangeListener() {
			public void stateChanged(javax.swing.event.ChangeEvent evt) {
//				spnAcidulatedMaltContentStateChanged(evt);
			}
		});
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 3;
		gridBagConstraints.gridy = 0;
		gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
		gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
		jPanelPh.add(spnAcidulatedMaltContent, gridBagConstraints);
		jLabel44.setText("%");
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 4;
		gridBagConstraints.gridy = 0;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
		gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
		jPanelPh.add(jLabel44, gridBagConstraints);
		spnAcidulatedMaltContent.setDoubleValue(2.0);
		spnAcidulatedMaltContent.setEnabled(false);;
		
		jLabel45.setText("Acido lattico");
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 1;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
		gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
		jPanelPh.add(jLabel45, gridBagConstraints);
		spnLacticAcid.setPreferredSize(new java.awt.Dimension(64, 22));
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 1;
		gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
		gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
		jPanelPh.add(spnLacticAcid, gridBagConstraints);
		jLabel46.setText("ml");
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 2;
		gridBagConstraints.gridy = 1;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
		gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
		jPanelPh.add(jLabel46, gridBagConstraints);
		spnLacticAcidContent.setPreferredSize(new java.awt.Dimension(64, 22));
		spnLacticAcidContent.addChangeListener(new javax.swing.event.ChangeListener() {
			public void stateChanged(javax.swing.event.ChangeEvent evt) {
//				spnLacticAcidContentStateChanged(evt);
			}
		});
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 3;
		gridBagConstraints.gridy = 1;
		gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
		gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
		jPanelPh.add(spnLacticAcidContent, gridBagConstraints);
		jLabel47.setText("%");
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 4;
		gridBagConstraints.gridy = 1;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
		gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
		jPanelPh.add(jLabel47, gridBagConstraints);
		spnLacticAcidContent.setDoubleValue(88.0);
		spnLacticAcidContent.setEnabled(false);
		
		jLabel48.setText("Acido citrico");
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 2;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
		gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
		jPanelPh.add(jLabel48, gridBagConstraints);
		spnCitrusAcid.setPreferredSize(new java.awt.Dimension(64, 22));
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 2;
		gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
		gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
		jPanelPh.add(spnCitrusAcid, gridBagConstraints);
		jLabel49.setText("gr");
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 2;
		gridBagConstraints.gridy = 2;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
		gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
		jPanelPh.add(jLabel49, gridBagConstraints);
		spnCitrusAcidContent.setPreferredSize(new java.awt.Dimension(64, 22));
		spnCitrusAcidContent.addChangeListener(new javax.swing.event.ChangeListener() {
			public void stateChanged(javax.swing.event.ChangeEvent evt) {
//				spnCitrusAcidContentStateChanged(evt);
			}
		});
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 3;
		gridBagConstraints.gridy = 2;
		gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
		gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
		jPanelPh.add(spnCitrusAcidContent, gridBagConstraints);
		jLabel50.setText("%");
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 4;
		gridBagConstraints.gridy = 2;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
		gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
		jPanelPh.add(jLabel50, gridBagConstraints);
		spnCitrusAcidContent.setDoubleValue(88.0);
		spnCitrusAcidContent.setEnabled(false);
		
		
		jLabel39.setText("Alcalinità residua");
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 3;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
		gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
		gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
		jPanelPh.add(jLabel39, gridBagConstraints);
		txtRA.setEditable(false);
		txtRA.setPreferredSize(new java.awt.Dimension(64, 22));
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 3;
		gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
		gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
		jPanelPh.add(txtRA, gridBagConstraints);
		
		jLabel40.setText("Alcalinità effettiva");
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 4;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
		gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
		jPanelPh.add(jLabel40, gridBagConstraints);
		txtAlk.setEditable(false);
		txtAlk.setPreferredSize(new java.awt.Dimension(64, 22));
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 4;
		gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
		gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
		jPanelPh.add(txtAlk, gridBagConstraints);
		
		jLabel41.setText("pH");
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 5;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHEAST;
		gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
		gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
		jPanelPh.add(jLabel41, gridBagConstraints);
		txtPH.setEditable(false);
		txtPH.setPreferredSize(new java.awt.Dimension(64, 22));
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 5;
		gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
		gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
		jPanelPh.add(txtPH, gridBagConstraints);
		
		
		
	}// </editor-fold>//GEN-END:initComponents

	private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jButton3ActionPerformed
		WaterProfile wp = new WaterProfile(spinCalcio2.getIntegerValue(), spinMagnesio2.getIntegerValue(),
				spinSolfato2.getIntegerValue(), spinCloruro2.getIntegerValue(), spinSodio2.getIntegerValue(),
				spinCarb2.getIntegerValue());
		String txt = "HD\t= " + (2.5 * wp.getCalcio() + 4.16 * wp.getMagnesio());
		double alk = (wp.getCarbonato() * 50.0 / 61.0);
		txt += "\nAlk\t= " + alk;
		double RA = alk - 0.71 * wp.getCalcio() - 0.59 * wp.getMagnesio();
		txt += "\nRA\t= " + RA;
		Utils.showMsg(txt, parent);
	}// GEN-LAST:event_jButton3ActionPerformed

	private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jButton2ActionPerformed
		WaterProfile wp = new WaterProfile(spinCalcio1.getIntegerValue(), spinMagnesio1.getIntegerValue(),
				spinSolfato1.getIntegerValue(), spinCloruro1.getIntegerValue(), spinSodio1.getIntegerValue(),
				spinCarb1.getIntegerValue());
		String txt = "HD\t= " + (2.5 * wp.getCalcio() + 4.16 * wp.getMagnesio());
		double alk = (wp.getCarbonato() * 50.0 / 61.0);
		txt += "\nAlk\t= " + alk;
		double RA = alk - 0.71 * wp.getCalcio() - 0.59 * wp.getMagnesio();
		txt += "\nRA\t= " + RA;
		Utils.showMsg(txt, parent);
	}// GEN-LAST:event_jButton2ActionPerformed

	private void spnVolumeStateChanged(javax.swing.event.ChangeEvent evt) {// GEN-FIRST:event_spnVolumeStateChanged
		updateTreatment();
	}// GEN-LAST:event_spnVolumeStateChanged

	private void spinSodio1StateChanged(javax.swing.event.ChangeEvent evt) {// GEN-FIRST:event_spinSodio1StateChanged
		recalcTreatment();
	}// GEN-LAST:event_spinSodio1StateChanged

	private void spinCloruro1StateChanged(javax.swing.event.ChangeEvent evt) {// GEN-FIRST:event_spinCloruro1StateChanged
		recalcTreatment();
	}// GEN-LAST:event_spinCloruro1StateChanged

	private void spinSolfato1StateChanged(javax.swing.event.ChangeEvent evt) {// GEN-FIRST:event_spinSolfato1StateChanged
		recalcTreatment();
	}// GEN-LAST:event_spinSolfato1StateChanged

	private void spinMagnesio1StateChanged(javax.swing.event.ChangeEvent evt) {// GEN-FIRST:event_spinMagnesio1StateChanged
		recalcTreatment();
	}// GEN-LAST:event_spinMagnesio1StateChanged

	private void spinCalcio1StateChanged(javax.swing.event.ChangeEvent evt) {// GEN-FIRST:event_spinCalcio1StateChanged
		recalcTreatment();
	}// GEN-LAST:event_spinCalcio1StateChanged

	private void spnSodaStateChanged(javax.swing.event.ChangeEvent evt) {// GEN-FIRST:event_spnSodaStateChanged
		recalcTreatment();
	}// GEN-LAST:event_spnSodaStateChanged

	private void spnChalkStateChanged(javax.swing.event.ChangeEvent evt) {// GEN-FIRST:event_spnChalkStateChanged
		recalcTreatment();
	}// GEN-LAST:event_spnChalkStateChanged

	private void spnChalkAncestorAdded(javax.swing.event.AncestorEvent evt) {// GEN-FIRST:event_spnChalkAncestorAdded

	}// GEN-LAST:event_spnChalkAncestorAdded

	private void spnNaClStateChanged(javax.swing.event.ChangeEvent evt) {// GEN-FIRST:event_spnNaClStateChanged
		recalcTreatment();
	}// GEN-LAST:event_spnNaClStateChanged

	private void spnCaCl2StateChanged(javax.swing.event.ChangeEvent evt) {// GEN-FIRST:event_spnCaCl2StateChanged
		recalcTreatment();
	}// GEN-LAST:event_spnCaCl2StateChanged

	private void spnEpsomStateChanged(javax.swing.event.ChangeEvent evt) {// GEN-FIRST:event_spnEpsomStateChanged
		recalcTreatment();
	}// GEN-LAST:event_spnEpsomStateChanged

	private void spnGypsumStateChanged(javax.swing.event.ChangeEvent evt) {// GEN-FIRST:event_spnGypsumStateChanged
		recalcTreatment();
	}// GEN-LAST:event_spnGypsumStateChanged

	private boolean work = false;

	private void jToggleButton1ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jToggleButton1ActionPerformed
		work = jToggleButton1.isSelected();
	}// GEN-LAST:event_jToggleButton1ActionPerformed

	private void useSodaActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_useSodaActionPerformed
		flagRes = true;
	}// GEN-LAST:event_useSodaActionPerformed

	private void useChalkActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_useChalkActionPerformed
		flagRes = true;
	}// GEN-LAST:event_useChalkActionPerformed

	private void useNaClActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_useNaClActionPerformed
		flagRes = true;
	}// GEN-LAST:event_useNaClActionPerformed

	private void useCaCl2ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_useCaCl2ActionPerformed
		flagRes = true;
	}// GEN-LAST:event_useCaCl2ActionPerformed

	private void useEpsomActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_useEpsomActionPerformed
		flagRes = true;
	}// GEN-LAST:event_useEpsomActionPerformed

	private void useGypsumActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_useGypsumActionPerformed
		flagRes = true;
	}// GEN-LAST:event_useGypsumActionPerformed

	private void spinCarb2StateChanged(javax.swing.event.ChangeEvent evt) {// GEN-FIRST:event_spinCarb2StateChanged

	}// GEN-LAST:event_spinCarb2StateChanged

	private void spinCarb1StateChanged(javax.swing.event.ChangeEvent evt) {// GEN-FIRST:event_spinCarb1StateChanged
		recalcTreatment();
	}// GEN-LAST:event_spinCarb1StateChanged

	private void btnBActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnBActionPerformed
		waterPicker.startModal(parent);
		WaterProfile profile = (WaterProfile) this.waterPicker.getSelection();

		setDest(profile);
		recalcTreatment();
	}// GEN-LAST:event_btnBActionPerformed

	private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jButton1ActionPerformed
		WaterProfile wp = new WaterProfile(spinCalcio.getIntegerValue(), spinMagnesio.getIntegerValue(),
				spinSolfato.getIntegerValue(), spinCloruro.getIntegerValue(), spinSodio.getIntegerValue(),
				spinCarb.getIntegerValue());
		String txt = "HD\t= " + (2.5 * wp.getCalcio() + 4.16 * wp.getMagnesio());
		double alk = (wp.getCarbonato() * 50.0 / 61.0);
		txt += "\nAlk\t= " + alk;
		double RA = alk - 0.71 * wp.getCalcio() - 0.59 * wp.getMagnesio();
		txt += "\nRA\t= " + RA;
		Utils.showMsg(txt, parent);
	}// GEN-LAST:event_jButton1ActionPerformed

	private void btnAActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnAActionPerformed
		// loadFrom();
		waterPicker.startModal(parent);
		WaterProfile profile = (WaterProfile) this.waterPicker.getSelection();
		setSource(profile);
		res = null;
		flagRes = true;
		recalcTreatment();
	}// GEN-LAST:event_btnAActionPerformed

	private void spinCarbStateChanged(javax.swing.event.ChangeEvent evt) {// GEN-FIRST:event_spinCarbStateChanged
		flagRes = true;
		recalcTreatment();
	}// GEN-LAST:event_spinCarbStateChanged

	private void spinSodioStateChanged(javax.swing.event.ChangeEvent evt) {// GEN-FIRST:event_spinSodioStateChanged
		flagRes = true;
		recalcTreatment();
	}// GEN-LAST:event_spinSodioStateChanged

	private void spinCloruroStateChanged(javax.swing.event.ChangeEvent evt) {// GEN-FIRST:event_spinCloruroStateChanged
		flagRes = true;
		recalcTreatment();
	}// GEN-LAST:event_spinCloruroStateChanged

	private void spinSolfatoStateChanged(javax.swing.event.ChangeEvent evt) {// GEN-FIRST:event_spinSolfatoStateChanged
		flagRes = true;
		recalcTreatment();
	}// GEN-LAST:event_spinSolfatoStateChanged

	private void spinMagnesioStateChanged(javax.swing.event.ChangeEvent evt) {// GEN-FIRST:event_spinMagnesioStateChanged
		flagRes = true;
		recalcTreatment();
	}// GEN-LAST:event_spinMagnesioStateChanged

	private void spinCalcioStateChanged(javax.swing.event.ChangeEvent evt) {// GEN-FIRST:event_spinCalcioStateChanged
		flagRes = true;
		recalcTreatment();
	}// GEN-LAST:event_spinCalcioStateChanged

	// Variables declaration - do not modify//GEN-BEGIN:variables
	private javax.swing.JButton btnA;
	private javax.swing.JButton btnB;
	private javax.swing.JPanel destPanel;
	private javax.swing.JPanel fromPanel;
	private javax.swing.JButton jButton1;
	private javax.swing.JButton jButton2;
	private javax.swing.JButton jButton3;
	private javax.swing.JLabel jLabel1;
	private javax.swing.JLabel jLabel10;
	private javax.swing.JLabel jLabel11;
	private javax.swing.JLabel jLabel12;
	private javax.swing.JLabel jLabel13;
	private javax.swing.JLabel jLabel14;
	private javax.swing.JLabel jLabel15;
	private javax.swing.JLabel jLabel16;
	private javax.swing.JLabel jLabel17;
	private javax.swing.JLabel jLabel18;
	private javax.swing.JLabel jLabel19;
	private javax.swing.JLabel jLabel2;
	private javax.swing.JLabel jLabel20;
	private javax.swing.JLabel jLabel21;
	private javax.swing.JLabel jLabel22;
	private javax.swing.JLabel jLabel23;
	private javax.swing.JLabel jLabel24;
	private javax.swing.JLabel jLabel26;
	private javax.swing.JLabel jLabel27;
	private javax.swing.JLabel jLabel28;
	private javax.swing.JLabel jLabel29;
	private javax.swing.JLabel jLabel3;
	private javax.swing.JLabel jLabel30;
	private javax.swing.JLabel jLabel31;
	private javax.swing.JLabel jLabel32;
	private javax.swing.JLabel jLabel33;
	private javax.swing.JLabel jLabel34;
	private javax.swing.JLabel jLabel35;
	private javax.swing.JLabel jLabel36;
	private javax.swing.JLabel jLabel37;
	private javax.swing.JLabel jLabel38;
	private javax.swing.JLabel jLabel39;
	private javax.swing.JLabel jLabel40;
	private javax.swing.JLabel jLabel41;
	private javax.swing.JLabel jLabel42;
	private javax.swing.JLabel jLabel43;
	private javax.swing.JLabel jLabel44;
	private javax.swing.JLabel jLabel45;
	private javax.swing.JLabel jLabel46;
	private javax.swing.JLabel jLabel47;
	private javax.swing.JLabel jLabel48;
	private javax.swing.JLabel jLabel49;
	private javax.swing.JLabel jLabel50;
	private javax.swing.JLabel jLabel4;
	private javax.swing.JLabel jLabel5;
	private javax.swing.JLabel jLabel6;
	private javax.swing.JLabel jLabel7;
	private javax.swing.JLabel jLabel8;
	private javax.swing.JLabel jLabel9;
	private javax.swing.JPanel jPanel1;
	private javax.swing.JPanel jPanel4;
	private javax.swing.JPanel jPanel5;
	private javax.swing.JPanel jPanelPh;
	private javax.swing.JPanel jPanelAdjustPh;
	private javax.swing.JToggleButton jToggleButton1;
	private javax.swing.JSlider pCalcio;
	private javax.swing.JSlider pCarbonato;
	private javax.swing.JSlider pCloruro;
	private javax.swing.JSlider pMagnesio;
	private javax.swing.JSlider pSodio;
	private javax.swing.JSlider pSolfato;
	private jmash.component.JMashSpinner spinCalcio;
	private jmash.component.JMashSpinner spinCalcio1;
	private jmash.component.JMashSpinner spinCalcio2;
	private jmash.component.JMashSpinner spinCarb;
	private jmash.component.JMashSpinner spinCarb1;
	private jmash.component.JMashSpinner spinCarb2;
	private jmash.component.JMashSpinner spinCloruro;
	private jmash.component.JMashSpinner spinCloruro1;
	private jmash.component.JMashSpinner spinCloruro2;
	private jmash.component.JMashSpinner spinMagnesio;
	private jmash.component.JMashSpinner spinMagnesio1;
	private jmash.component.JMashSpinner spinMagnesio2;
	private jmash.component.JMashSpinner spinSodio;
	private jmash.component.JMashSpinner spinSodio1;
	private jmash.component.JMashSpinner spinSodio2;
	private jmash.component.JMashSpinner spinSolfato;
	private jmash.component.JMashSpinner spinSolfato1;
	private jmash.component.JMashSpinner spinSolfato2;
	private jmash.component.JMashSpinner spnCaCl2;
	private jmash.component.JMashSpinner spnChalk;
	private jmash.component.JMashSpinner spnEpsom;
	private jmash.component.JMashSpinner spnGypsum;
	private jmash.component.JMashSpinner spnNaCl;
	private jmash.component.JMashSpinner spnSoda;
	private jmash.component.JVolumeSpinner spnVolume;
	
	private javax.swing.JTextField txtRA;
	private javax.swing.JTextField txtAlk;
	private javax.swing.JTextField txtPH;
	private javax.swing.JTextField txtAcidMalt;
	private jmash.component.JVolumeSpinner spnAcidulatedMaltContent;
	private jmash.component.JVolumeSpinner spnLacticAcid;
	private jmash.component.JVolumeSpinner spnCitrusAcid;
	private jmash.component.JVolumeSpinner spnLacticAcidContent;
	private jmash.component.JVolumeSpinner spnCitrusAcidContent;
	
	
	private javax.swing.JCheckBox useCaCl2;
	private javax.swing.JCheckBox useChalk;
	private javax.swing.JCheckBox useEpsom;
	private javax.swing.JCheckBox useGypsum;
	private javax.swing.JCheckBox useNaCl;
	private javax.swing.JCheckBox useSoda;

	// End of variables declaration//GEN-END:variables
	private void loadFrom() {
		File file = Utils.pickFileToLoad(parent, Main.waterDir);
		if (file == null)
			return;
		Document doc = Utils.readFileAsXml(file.toString());
		if (doc == null) {
			return;
		}
		Element root = doc.getRootElement();
		WaterProfile profile = WaterProfile.fromXml(root);
		sourceName = profile.getNome();
		if (sourceName == null)
			sourceName = file.getName();
		profile.setNome(sourceName);
		setSource(profile);
		res = null;
		flagRes = true;
	}

	private boolean flagRes = false;

	public void saveFrom() {

		File file = Utils.pickFileToSave(parent, Main.waterDir);
		if (file == null) {
			return;
		}
		WaterProfile source = new WaterProfile(spinCalcio.getIntegerValue(), spinMagnesio.getIntegerValue(),
				spinSolfato.getIntegerValue(), spinCloruro.getIntegerValue(), spinSodio.getIntegerValue(),
				spinCarb.getIntegerValue());
		source.setNome(sourceName);
		if (!file.exists())
			source.setNome(file.getName());
		Document doc = new Document();
		Element root = source.toXml();
		doc.setRootElement(root);
		Utils.saveXmlAsFile(doc, file, parent);
	}

	private File destFile = null;
	private String sourceName = null;
	private String destName = null;

	private void loadDest() {
		File file = Utils.pickFileToLoad(parent, Main.waterDir);
		if (file == null)
			return;
		Document doc = Utils.readFileAsXml(file.toString());
		if (doc == null) {
			return;
		}
		Element root = doc.getRootElement();
		WaterProfile profile = WaterProfile.fromXml(root);
		destName = profile.getNome();
		if (destName == null)
			destName = file.getName();
		profile.setNome(destName);
		setDest(profile);
	}

	public void saveDest() {

		File file = Utils.pickFileToSave(parent, Main.waterDir);
		if (file == null) {
			return;
		}

		WaterProfile source = new WaterProfile(spinCalcio1.getIntegerValue(), spinMagnesio1.getIntegerValue(),
				spinSolfato1.getIntegerValue(), spinCloruro1.getIntegerValue(), spinSodio1.getIntegerValue(),
				spinCarb1.getIntegerValue());
		source.setNome(destName);
		if (!file.exists())
			source.setNome(file.getName());
		Document doc = new Document();
		Element root = source.toXml();
		doc.setRootElement(root);
		Utils.saveXmlAsFile(doc, file, parent);
	}

	private boolean flag = true;

	public void end() {
		flag = false;
	}

	private WaterProfile res = null;
	private Thread thread;

	public WaterProfile getSource() {
		WaterProfile source = new WaterProfile(spinCalcio.getIntegerValue(), spinMagnesio.getIntegerValue(),
				spinSolfato.getIntegerValue(), spinCloruro.getIntegerValue(), spinSodio.getIntegerValue(),
				spinCarb.getIntegerValue());
		source.setNome(sourceName);
		return source;
	}

	public WaterProfile getDest() {
		WaterProfile dest = new WaterProfile(spinCalcio1.getIntegerValue(), spinMagnesio1.getIntegerValue(),
				spinSolfato1.getIntegerValue(), spinCloruro1.getIntegerValue(), spinSodio1.getIntegerValue(),
				spinCarb1.getIntegerValue());
		dest.setNome(destName);
		return dest;
	}

	public WaterProfile getTreatment() {
		return res;
	}

	public void setSource(WaterProfile profile) {
		if (profile == null)
			return;
		sourceName = profile.getNome();
		((TitledBorder) fromPanel.getBorder()).setTitle("Origine - " + sourceName);
		spinCalcio.setIntegerValue((int) profile.getCalcio().doubleValue());
		spinSodio.setIntegerValue((int) profile.getSodio().doubleValue());
		spinSolfato.setIntegerValue((int) profile.getSolfato().doubleValue());
		spinMagnesio.setIntegerValue((int) profile.getMagnesio().doubleValue());
		spinCloruro.setIntegerValue((int) profile.getCloruro().doubleValue());
		spinCarb.setIntegerValue((int) profile.getCarbonato().doubleValue());
	}

	public void setDest(WaterProfile profile) {
		if (profile == null)
			return;
		destName = profile.getNome();
		((TitledBorder) destPanel.getBorder()).setTitle("Target - " + destName);
		spinCalcio1.setIntegerValue((int) profile.getCalcio().doubleValue());
		spinSodio1.setIntegerValue((int) profile.getSodio().doubleValue());
		spinSolfato1.setIntegerValue((int) profile.getSolfato().doubleValue());
		spinMagnesio1.setIntegerValue((int) profile.getMagnesio().doubleValue());
		spinCloruro1.setIntegerValue((int) profile.getCloruro().doubleValue());
		spinCarb1.setIntegerValue((int) profile.getCarbonato().doubleValue());
	}

	public void setTreatment(WaterProfile profile) {
		res = profile;
		updateTreatment();
	}

	private boolean skipRecalc = false;

	private void updateTreatment() {
		double LITRI = spnVolume.getVolume();
		if (res == null)
			return;
		skipRecalc = true;
		spnCaCl2.setDoubleValue(res.getCalciumChloride() * Utils.litToGal(LITRI) / 1000);
		spnChalk.setDoubleValue(res.getChalk() * Utils.litToGal(LITRI) / 1000);
		spnEpsom.setDoubleValue(res.getEpsom() * Utils.litToGal(LITRI) / 1000);
		spnGypsum.setDoubleValue(res.getGypsum() * Utils.litToGal(LITRI) / 1000);
		spnNaCl.setDoubleValue(res.getSale() * Utils.litToGal(LITRI) / 1000);
		spnSoda.setDoubleValue(res.getSoda() * Utils.litToGal(LITRI) / 1000);
		skipRecalc = false;
		spinCalcio2.setIntegerValue((int) res.getCalcioTotale());
		spinMagnesio2.setIntegerValue((int) res.getMagnesioTotale());
		spinSolfato2.setIntegerValue((int) res.getSolfatoTotale());
		spinCloruro2.setIntegerValue((int) res.getCloruroTotale());
		spinSodio2.setIntegerValue((int) res.getSodioTotale());
		spinCarb2.setIntegerValue((int) res.getCarbonatoTotale());
		
		fireStateChanged(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, ""));
	}

	private void recalcTreatment() {
		if (skipRecalc)
			return;
		double LITRI = spnVolume.getVolume();
		WaterProfile treat = new WaterProfile(spinCalcio.getIntegerValue(), spinMagnesio.getIntegerValue(),
				spinSolfato.getIntegerValue(), spinCloruro.getIntegerValue(), spinSodio.getIntegerValue(),
				spinCarb.getIntegerValue());
		res = treat;
		treat.setGypsum((spnGypsum.getDoubleValue() * 1000 / Utils.litToGal(LITRI)));
		treat.setEpsom((spnEpsom.getDoubleValue() * 1000 / Utils.litToGal(LITRI)));
		treat.setSale((spnNaCl.getDoubleValue() * 1000 / Utils.litToGal(LITRI)));
		treat.setCalciumChloride((spnCaCl2.getDoubleValue() * 1000 / Utils.litToGal(LITRI)));
		treat.setSoda((spnSoda.getDoubleValue() * 1000 / Utils.litToGal(LITRI)));
		treat.setChalk((spnChalk.getDoubleValue() * 1000 / Utils.litToGal(LITRI)));
		res = treat;
		updateTreatment();
	}

	@Override
	public void setEnabled(boolean F) {
		jToggleButton1.setEnabled(F);
		pCalcio.setEnabled(F);
		pCarbonato.setEnabled(F);
		pCloruro.setEnabled(F);
		pMagnesio.setEnabled(F);
		pSodio.setEnabled(F);
		pSolfato.setEnabled(F);
		spinCalcio.setEnabled(F);
		spinCalcio1.setEnabled(F);
		spinCalcio2.setEnabled(F);
		spinCarb.setEnabled(F);
		spinCarb1.setEnabled(F);
		spinCarb2.setEnabled(F);
		spinCloruro.setEnabled(F);
		spinCloruro1.setEnabled(F);
		spinCloruro2.setEnabled(F);
		spinMagnesio.setEnabled(F);
		spinMagnesio1.setEnabled(F);
		spinMagnesio2.setEnabled(F);
		spinSodio.setEnabled(F);
		spinSodio1.setEnabled(F);
		spinSodio2.setEnabled(F);
		spinSolfato.setEnabled(F);
		spinSolfato1.setEnabled(F);
		spinSolfato2.setEnabled(F);
		spnCaCl2.setEnabled(F);
		spnChalk.setEnabled(F);
		spnEpsom.setEnabled(F);
		spnGypsum.setEnabled(F);
		spnNaCl.setEnabled(F);
		spnSoda.setEnabled(F);
		spnVolume.setEnabled(F);
		useCaCl2.setEnabled(F);
		useChalk.setEnabled(F);
		useEpsom.setEnabled(F);
		useGypsum.setEnabled(F);
		useNaCl.setEnabled(F);
		useSoda.setEnabled(F);
		btnA.setEnabled(F);
		btnB.setEnabled(F);
		
	}

	public void setTotWater(double size) {
		spnVolume.setVolume(size);
	}

	public void fer() {
		WaterProfile wp = new WaterProfile(spinCalcio.getIntegerValue(), spinMagnesio.getIntegerValue(),
				spinSolfato.getIntegerValue(), spinCloruro.getIntegerValue(), spinSodio.getIntegerValue(),
				spinCarb.getIntegerValue());
		String txt = "HD = " + (2.5 * wp.getCalcio() + 4.16 * wp.getMagnesio());
		double alk = (wp.getCarbonato() * 50.0 / 61.0);
		txt += "\nAlk = " + alk;
		double RA = alk - 0.71 * wp.getCalcio() - 0.59 * wp.getMagnesio();
		txt += "\nRA = " + RA;
		Utils.showException(null, txt, parent);
	}

	public double getCalcio() {
		return spinCalcio.getDoubleValue();
	}

	public double getMagnesio() {
		return spinMagnesio.getDoubleValue();
	}

	public double getSolfato() {
		return spinSolfato.getDoubleValue();
	}

	public double getCloruro() {
		return spinCloruro.getDoubleValue();
	}

	public double getSodio() {
		return spinSodio.getDoubleValue();
	}

	public double getCarb() {
		return spinCarb.getDoubleValue();
	}

	public double getAdjustCarbonatoDiCalcio() {
		return spnChalk.getDoubleValue();
	}

	public double getAdjustBicarbonatoDiSodio() {
		return spnSoda.getDoubleValue();
	}

	public void addChangeListener(ChangeListener listener) {
		listenerList.add(ChangeListener.class, listener);
	}
	
	public void removeChangeListener(ChangeListener listener) {
	    listenerList.remove(ChangeListener.class, listener);
	}
	
	protected void fireStateChanged(ActionEvent actionEvent) {
	    ChangeListener[] listeners = listenerList.getListeners(ChangeListener.class);
	    if (listeners != null && listeners.length > 0) {
	        ChangeEvent evt = new ChangeEvent(actionEvent);
	        for (ChangeListener listener : listeners) {
	            listener.stateChanged(evt);
	        }
	    }
	}
	
	public void actionPerformed(ActionEvent evt) {
	    fireStateChanged(evt);
	}
	
	public void setRA(Double RA) {
		this.txtRA.setText(Utils.format(RA, "0.000"));
	}
	
	public void setAlk(Double alk) {
		this.txtAlk.setText(Utils.format(alk, "0.000"));
	}
	
	public void setPH(Double pH) {
		this.txtPH.setText(Utils.format(pH, "0.000"));
	}
	
	public void setTotalAcidGrainWeightGr(Double grammi)
	{
		this.txtAcidMalt.setText(Utils.format(grammi, "0.0"));
	}
	
	
	
	

}
