/*
 * Created on May 8, 2006 by wyatt
 * 
 * A utility class which allows editing of transactions.
 */
package org.homeunix.drummer.view.components;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Date;
import java.util.Vector;

import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import org.homeunix.drummer.TranslateKeys;
import org.homeunix.drummer.Translate;
import org.homeunix.drummer.controller.DataInstance;
import org.homeunix.drummer.controller.PrefsInstance;
import org.homeunix.drummer.model.Category;
import org.homeunix.drummer.model.Source;
import org.homeunix.drummer.model.Transaction;
import org.homeunix.drummer.util.Formatter;
import org.homeunix.drummer.util.Log;
import org.homeunix.drummer.view.components.autocomplete.AutoCompleteTextField;
import org.homeunix.drummer.view.layout.TransactionsFrameLayout;

public class EditableTransaction extends JPanel {
	public static final long serialVersionUID = 0;
	
	private final Vector<JComponent> components;

	private Transaction transaction; //Set when editing existing one; null otherwise
	
	private final JFormattedTextField date;
	private final JNumberField amount;
	private final JComboBox transferFrom;
	private final JComboBox transferTo;
	private final JTextField number;
	private final AutoCompleteTextField description;
	private final JTextArea memo;
	
	private final DefaultComboBoxModel toModel;
	private final DefaultComboBoxModel fromModel;
		
	private TransactionsFrameLayout parent;
	
	private boolean changed = false;
		
	public EditableTransaction(TransactionsFrameLayout parent){
		this.parent = parent;
				
		date = new JFormattedTextField(Formatter.getInstance().getDateFormat());
		amount = new JNumberField(Formatter.getInstance().getDecimalFormat());
		transferFrom = new JComboBox();
		transferTo = new JComboBox();
		number = new JTextField();
		description = new AutoCompleteTextField(PrefsInstance.getInstance().getDescDict());
		memo = new JTextArea();
		
		components = new Vector<JComponent>();
		
		toModel = new DefaultComboBoxModel();
		fromModel = new DefaultComboBoxModel();
		
		transferTo.setModel(toModel);
		transferFrom.setModel(fromModel);
				
		number.setPreferredSize(new Dimension(34, number.getPreferredSize().height));
		description.setPreferredSize(new Dimension(100, number.getPreferredSize().height));
		
		Dimension comboDimension = new Dimension(50, transferFrom.getPreferredSize().height);
		transferFrom.setPreferredSize(comboDimension);
		transferTo.setPreferredSize(comboDimension);
		
		date.setToolTipText(Translate.inst().get(TranslateKeys.TOOLTIP_DATE));
		amount.setToolTipText(Translate.inst().get(TranslateKeys.TOOLTIP_AMOUNT));
		transferFrom.setToolTipText(Translate.inst().get(TranslateKeys.TOOLTIP_FROM));
		transferTo.setToolTipText(Translate.inst().get(TranslateKeys.TOOLTIP_TO));
		number.setToolTipText(Translate.inst().get(TranslateKeys.TOOLTIP_NUMBER));
		description.setToolTipText(Translate.inst().get(TranslateKeys.TOOLTIP_DESC));
		memo.setToolTipText(Translate.inst().get(TranslateKeys.TOOLTIP_MEMO));
		
		JPanel topPanel = new JPanel();
		JPanel bottomPanel = new JPanel();
		topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.X_AXIS));
		bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.X_AXIS));
						
		components.add(date);
		components.add(amount);
		components.add(transferFrom);
		components.add(transferTo);

		components.add(number);
		components.add(description);
		components.add(memo);
		
		topPanel.add(date);
		topPanel.add(description);
		topPanel.add(number);

		bottomPanel.add(amount);
		
		bottomPanel.add(transferFrom);
		bottomPanel.add(new JLabel(Translate.inst().get(TranslateKeys.TO)));
		bottomPanel.add(transferTo);		
		
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		mainPanel.add(topPanel);
		mainPanel.add(bottomPanel);
		
		JScrollPane memoScroller = new JScrollPane(memo);
		memoScroller.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		memoScroller.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		
		this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		this.add(mainPanel);
		this.add(memoScroller);
		
		int textHeight = date.getPreferredSize().height;
		date.setPreferredSize(new Dimension(80, textHeight));
		description.setPreferredSize(new Dimension(200, textHeight));
		number.setPreferredSize(new Dimension(80, textHeight));
		
		amount.setPreferredSize(new Dimension(250, textHeight));
		transferFrom.setPreferredSize(new Dimension(300, transferFrom.getPreferredSize().height));
		transferTo.setPreferredSize(transferFrom.getPreferredSize());
		
		memoScroller.setPreferredSize(new Dimension(300, memo.getPreferredSize().height));
				
		initActions();
		
		//updateContent();
	}
	
	public void setTransaction(Transaction transaction){
		//SimpleDateFormat sdf = new SimpleDateFormat(TransactionCell.dateFormat);
		date.setValue(transaction.getDate());
		
		number.setText(transaction.getNumber());
		description.setText(transaction.getDescription());
		//balance.setText("");
		memo.setText(transaction.getMemo());
		
		amount.setValue((double) transaction.getAmount() / 100.0);
		
		transferFrom.setSelectedItem(transaction.getFrom());
		transferTo.setSelectedItem(transaction.getTo());
		
		this.transaction = transaction;
		
		//Reset change flag
		setChanged(false);
	}
	
	public void clearTransaction(){
		if (date.getValue() == null)
			date.setValue(new Date());
		number.setText("");
		description.setText("");
		amount.setValue(0);
		//balance.setText("");
		transferTo.setSelectedIndex(0);
		transferFrom.setSelectedIndex(0);
		memo.setText("");
		
		date.requestFocus();
		
		//Reset change flag
		setChanged(false);
		
		//Reset transaction ID
		transaction = null;
	}
	
	protected void setChanged(){
		changed = true;
		parent.updateButtons();
	}
	
	protected void setChanged(boolean changed){
		this.changed = changed;		
	}
	
	public boolean isChanged(){
		return changed;
	}
	
	public Date getDate(){
		return (Date) date.getValue();
	}
	
	public String getNumber(){
		return number.getText();
	}
	
	public String getDescription(){
		return description.getText();
	}
	
	public Transaction getTransaction(){
		return transaction;
	}
	
	public int getAmount(){
		int amount = 0;
		
		//We record the amount in cents, to avoid decimal point issues
		amount = (int) (Double.parseDouble(this.amount.getValue().toString()) * 100);
		
		return amount;
	}
	
	public Source getTransferFrom(){
		if (transferFrom.getSelectedItem() instanceof Source) {
			return (Source) transferFrom.getSelectedItem();	
		}
		else if (transferFrom.getSelectedItem() != null)
			Log.error("Unknown object selected in TransferFrom combobox; returning null.");
		return null;
	}

	public Source getTransferTo(){
		if (transferTo.getSelectedItem() instanceof Source) {
			return (Source) transferTo.getSelectedItem();	
		}
		else if (transferTo.getSelectedItem() != null)
			Log.error("Unknown object selected in TransferTo combobox; returning null.");
		
		return null;
	}

	
	public String getMemo(){
		return memo.getText();
	}
	
	public void updateContent(){
		//Update the dropdown lists
		setEnabled(true);
		
		toModel.removeAllElements();
		fromModel.removeAllElements();
		toModel.addElement(null);
		fromModel.addElement(null);
		for (Source source : DataInstance.getInstance().getAccounts()) {
			toModel.addElement(source);
			fromModel.addElement(source);
		}
		
		for (Category c : DataInstance.getInstance().getCategories()){
			if (c.isIncome())
				fromModel.addElement(c);
			else
				toModel.addElement(c);	
		}
	}
	
	private void initActions(){
		for (JComponent c : components) {
			c.addKeyListener(new KeyAdapter() {
				public void keyTyped(KeyEvent arg0) {
					EditableTransaction.this.setChanged();
				}
			});
			
			c.addFocusListener(new FocusAdapter(){
				public void focusGained(FocusEvent arg0) {
					if (arg0.getSource() instanceof JTextField 
							|| arg0.getSource() instanceof JFormattedTextField){
						JTextField text = (JTextField) arg0.getSource();
						text.selectAll();
					}
				}
			});
		}
		
		transferFrom.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				EditableTransaction.this.setChanged();
				if (parent.getAccount() != null) {
					if (!parent.getAccount().equals(transferFrom.getSelectedItem())){
						transferTo.setSelectedItem(parent.getAccount());
					}
					
					if (parent.getAccount().equals(transferFrom.getSelectedItem())
							&& parent.getAccount().equals(transferTo.getSelectedItem())){
						transferTo.setSelectedItem(null);
					}
				}
			}
		});
		
		transferTo.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				EditableTransaction.this.setChanged();
				if (transferTo.getSelectedItem() instanceof Source) {
					if (!parent.getAccount().equals(transferTo.getSelectedItem())){
						transferFrom.setSelectedItem(parent.getAccount());
					}

					if (parent.getAccount().equals(transferFrom.getSelectedItem())
							&& parent.getAccount().equals(transferTo.getSelectedItem())){
						transferFrom.setSelectedItem(null);
					}
				}
			}
		});
	}

	@Override
	public void setEnabled(boolean arg0) {
		for (JComponent c : components) {
			c.setEnabled(arg0);
		}
		
	}
	
	public void select(){
		amount.requestFocus();
		amount.selectAll();
	}
}
