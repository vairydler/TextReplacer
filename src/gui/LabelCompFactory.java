package gui;
import java.awt.Component;

import javax.swing.JPanel;

import net.miginfocom.swing.MigLayout;

public final class LabelCompFactory {
	/**
	 * @wbp.factory
	 * @wbp.factory.parameter.source comp lblNewLabel_1
	 * @wbp.factory.parameter.source comp_1 textField_1
	 * @wbp.factory.parameter.source comp_2 btnNewButton_1
	 */
	public static JPanel createJPanel(Component comp, Component comp_1, Component comp_2) {
		JPanel panel = new JPanel();
		panel.setLayout(new MigLayout("", "[81px][266px,grow][]", "[31px]"));
		panel.add(comp, "cell 0 0,alignx left");
		panel.add(comp_1, "cell 1 0,growx");
		panel.add(comp_2, "cell 2 0,growx");
		return panel;
	}
}