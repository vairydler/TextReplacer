package gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * ラベルとテキストフィールドのペア
 * @author vairy
 */
public class LabelComp<E extends JComponent> extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected JLabel				lbl;
	protected JComponent			comp;
	protected GridBagLayout			gbl = new GridBagLayout();
	protected GridBagConstraints 	lblgbc;
	protected GridBagConstraints 	compgbc;
	
	public LabelComp(final E comp){
		this(null,comp);
	}
	public LabelComp(final String label,final E comp){
		this(label,comp,null,null);
	}
	public LabelComp(final String label,final E comp,final GridBagConstraints labelgbc,final GridBagConstraints compgbc){
		this.lbl = new JLabel(label);
		this.comp = comp;
		this.setLayout(gbl);
		
		this.lblgbc = labelgbc;
		this.compgbc = compgbc;
		if(null == this.lblgbc)
		{
			this.lblgbc = new GridBagConstraints();
			this.lblgbc.gridx = 0;
			this.lblgbc.gridy = 0;
		}
		if(null == compgbc)
		{
			this.compgbc = new GridBagConstraints();
			this.compgbc.gridx = 1;
			this.compgbc.gridy = 0;
		}
		
		gbl.addLayoutComponent(this.lbl, this.lblgbc);
		gbl.addLayoutComponent(this.comp, this.compgbc);

		this.add(lbl);
		this.add(this.comp);
	}
	
	public void setLblvalue(String value){
		lbl.setText(value);
	}
	public final String getLblvalue(){
		return lbl.getText();
	}
	public void setLabel(JLabel lbl){
		this.lbl = lbl;
	}
	public final JLabel getLabel(){
		return lbl;
	}
	public void setComponent(E comp){
		if(this.comp != null){
			this.remove(this.comp);
		}
		this.comp = comp;
		gbl.addLayoutComponent(this.comp, this.compgbc);
		this.add(this.comp);
	}
	@SuppressWarnings("unchecked")
	public E getComponent(){
		return (E) this.comp;
	}
	public final GridBagLayout getLayout(){
		return this.gbl;
	}
	public final GridBagConstraints getLblConstraints(){
		return this.lblgbc;
	}
	public final GridBagConstraints getCompConstraints(){
		return this.compgbc;
	}
	public final void setLblConstraints(final GridBagConstraints gbc){
		this.lblgbc = gbc;
		this.gbl.setConstraints(lbl, lblgbc);
	}
	public final void setCompConstraints(final GridBagConstraints gbc){
		this.compgbc = gbc;
		this.gbl.setConstraints(comp, compgbc);
	}
}
