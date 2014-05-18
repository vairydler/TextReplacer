package gui;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

import net.miginfocom.swing.MigLayout;

public class VFrame extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4140819993719829413L;
	private JTextField textField;
	private JPanel ChooserBox;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	public VFrame() {
		SpringLayout springLayout = new SpringLayout();
		getContentPane().setLayout(springLayout);
		
		JPanel LabelComp = LabelCompFactory.createJPanel(new JLabel(), new JTextField(), new JButton());
		springLayout.putConstraint(SpringLayout.NORTH, LabelComp, 10, SpringLayout.NORTH, getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, LabelComp, 10, SpringLayout.WEST, getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, LabelComp, 45, SpringLayout.NORTH, getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, LabelComp, -184, SpringLayout.EAST, getContentPane());
		getContentPane().add(LabelComp);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		
		JPanel panel = new JPanel();
		springLayout.putConstraint(SpringLayout.NORTH, panel, 6, SpringLayout.SOUTH, LabelComp);
		springLayout.putConstraint(SpringLayout.WEST, panel, 0, SpringLayout.WEST, LabelComp);
		springLayout.putConstraint(SpringLayout.EAST, panel, -184, SpringLayout.EAST, getContentPane());
		getContentPane().add(panel);
		panel.setLayout(new MigLayout("", "[95.00][275.00][grow]", "[]"));
		
		JLabel label = new JLabel("\u30EA\u30D7\u30EC\u30A4\u30B9\u30D5\u30A1\u30A4\u30EB");
		panel.add(label, "cell 0 0,alignx left");
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		panel.add(textField_2, "cell 1 0,growx");
		
		JButton button = new JButton("New button");
		panel.add(button, "cell 2 0");
		
		JPanel panel_1 = new JPanel();
		springLayout.putConstraint(SpringLayout.NORTH, panel_1, 6, SpringLayout.SOUTH, panel);
		springLayout.putConstraint(SpringLayout.WEST, panel_1, 0, SpringLayout.WEST, LabelComp);
		springLayout.putConstraint(SpringLayout.EAST, panel_1, 0, SpringLayout.EAST, LabelComp);
		getContentPane().add(panel_1);
		panel_1.setLayout(new MigLayout("", "[87.00][grow][]", "[]"));
		
		JLabel label_1 = new JLabel("\u4FDD\u5B58\u5148");
		panel_1.add(label_1, "cell 0 0,alignx trailing");
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		panel_1.add(textField_3, "cell 1 0,growx");
		
		JButton button_1 = new JButton("New button");
		panel_1.add(button_1, "cell 2 0");
		
		ChooserBox = new JPanel();
		ChooserBox.setLayout(new MigLayout("", "[91px][][][][]", "[21px]"));
		
		textField = new JTextField();
		ChooserBox.add(textField, "cell 0 0 4 1,grow");
		textField.setText("\u3000");
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("New button");
		ChooserBox.add(btnNewButton, "cell 4 0,grow");
	}

	public JPanel getChooserBox() {
		return ChooserBox;
	}
}
