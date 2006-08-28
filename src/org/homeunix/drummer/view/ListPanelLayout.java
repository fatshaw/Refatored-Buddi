/*
 * Created on May 6, 2006 by wyatt
 */
package org.homeunix.drummer.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.event.TreeExpansionEvent;
import javax.swing.event.TreeExpansionListener;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeCellRenderer;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;

import org.homeunix.drummer.Buddi;
import org.homeunix.drummer.Translate;
import org.homeunix.drummer.TranslateKeys;
import org.homeunix.drummer.controller.AccountListPanel.TypeTotal;
import org.homeunix.drummer.controller.model.PrefsInstance;
import org.homeunix.drummer.model.Account;
import org.homeunix.drummer.model.Category;
import org.homeunix.drummer.model.Source;
import org.homeunix.drummer.util.Formatter;
import org.homeunix.drummer.util.Log;

public abstract class ListPanelLayout extends AbstractBudgetPanel {
	public static final long serialVersionUID = 0;

	protected final JTree tree;
	protected final JButton newButton;
	protected final JButton editButton;
	protected final JButton deleteButton;
	protected final JButton openButton;
	protected final JPanel openButtonPanel;
	protected final JLabel balanceLabel;

	protected final DefaultMutableTreeNode root;
	protected final DefaultTreeModel treeModel; 

	protected Source selectedSource;

	protected ListPanelLayout(){
		root = new DefaultMutableTreeNode();
		treeModel = new DefaultTreeModel(root);
		tree = new JTree(treeModel);
		tree.setRootVisible(false);
		tree.setShowsRootHandles(true);
		SourceCellRenderer renderer = new SourceCellRenderer();
		tree.setCellRenderer(renderer);
		tree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
		JScrollPane listScroller = new JScrollPane(tree);

		newButton = new JButton(Translate.getInstance().get(TranslateKeys.NEW));
		editButton = new JButton(Translate.getInstance().get(TranslateKeys.EDIT));
		deleteButton = new JButton(Translate.getInstance().get(TranslateKeys.DELETE));
		openButton = new JButton(Translate.getInstance().get(TranslateKeys.OPEN));
		balanceLabel = new JLabel();

		JPanel balanceLabelPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		balanceLabelPanel.add(balanceLabel);

		JPanel listScrollerPanel = new JPanel(new BorderLayout());
		listScrollerPanel.add(listScroller, BorderLayout.CENTER);
		listScrollerPanel.add(balanceLabelPanel, BorderLayout.SOUTH);

		Dimension buttonSize = new Dimension(100, newButton.getPreferredSize().height);
		newButton.setPreferredSize(buttonSize);
		editButton.setPreferredSize(buttonSize);
		deleteButton.setPreferredSize(buttonSize);
		openButton.setPreferredSize(buttonSize);

		JPanel buttonPanelRight = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		buttonPanelRight.add(editButton);
		buttonPanelRight.add(newButton);

		JPanel buttonPanelLeft = new JPanel(new FlowLayout(FlowLayout.LEFT));
		buttonPanelLeft.add(deleteButton);

		openButtonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		openButtonPanel.add(openButton);

		JPanel buttonPanel = new JPanel(new BorderLayout());
		//buttonPanel.setBorder(BorderFactory.createTitledBorder(""));
		buttonPanel.add(buttonPanelRight, BorderLayout.EAST);
		buttonPanel.add(buttonPanelLeft, BorderLayout.WEST);
		//buttonPanel.add(openButtonPanel, BorderLayout.NORTH);

		JPanel mainBorderPanel = new JPanel();
		mainBorderPanel.setLayout(new BorderLayout());
		mainBorderPanel.setBorder(BorderFactory.createTitledBorder(""));

		JPanel mainPanel = new JPanel(); 
		mainPanel.setLayout(new BorderLayout());
		mainPanel.setBorder(BorderFactory.createEmptyBorder(7, 17, 7, 17));

		mainBorderPanel.add(listScrollerPanel, BorderLayout.CENTER);
		mainBorderPanel.add(buttonPanel, BorderLayout.SOUTH);

		mainPanel.add(mainBorderPanel, BorderLayout.CENTER);
		mainPanel.add(openButtonPanel, BorderLayout.SOUTH);

		if (Buddi.isMac()){
			tree.putClientProperty("Quaqua.Tree.style", "striped");
			listScroller.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
			listScroller.putClientProperty("Quaqua.Component.visualMargin", new Insets(7,12,3,12));
//			balanceLabel.putClientProperty("Quaqua.Component.style", "mini");
		}

		//this.setDefaultButton(openButton);
		this.setPreferredSize(new Dimension(450, 300));
		this.setLayout(new BorderLayout());
		this.add(mainPanel, BorderLayout.CENTER);
		this.setVisible(true);

		//Call the method to add actions to the buttons
		initActions();
		updateContent();
		updateButtons();
	}

	@Override
	protected AbstractBudgetPanel initActions() {
		tree.addTreeSelectionListener(new TreeSelectionListener(){
			public void valueChanged(TreeSelectionEvent arg0) {
				DefaultMutableTreeNode node = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();

				if (node == null)
					return;

				if (node.getUserObject() instanceof Source){ 
					selectedSource = (Source) node.getUserObject();
					Log.debug(selectedSource);
				}
				else{
					Log.debug("Object not of type Source");
					selectedSource = null;
				}

				ListPanelLayout.this.updateButtons();
			}
		});

		tree.addTreeExpansionListener(new TreeExpansionListener(){
			public void treeCollapsed(TreeExpansionEvent arg0) {				
				Object o = arg0.getPath().getLastPathComponent();

				Log.debug("Rolled node: " + o.toString());

				if (o instanceof DefaultMutableTreeNode){
					DefaultMutableTreeNode node = (DefaultMutableTreeNode) o;
					PrefsInstance.getInstance().setListAttributes(node.getUserObject().toString(), false);
				}
				else {
					Log.error("Unknown object in treeExpansionListener: " + o);
				}
			}

			public void treeExpanded(TreeExpansionEvent arg0) {
				Object o = arg0.getPath().getLastPathComponent();

				Log.debug("Unrolled node: " + o.toString());

				if (o instanceof DefaultMutableTreeNode){
					DefaultMutableTreeNode node = (DefaultMutableTreeNode) o;
					PrefsInstance.getInstance().setListAttributes(node.getUserObject().toString(), true);
				}
				else {
					Log.error("Unknown object in treeExpansionListener: " + o);
				}				
			}
		});

		return this;
	}

	public AbstractBudgetPanel updateButtons(){
		if (selectedSource == null){
			editButton.setEnabled(false);
			deleteButton.setEnabled(false);
			openButton.setEnabled(false);
			deleteButton.setText(Translate.getInstance().get(TranslateKeys.DELETE));
		}
		else{
			editButton.setEnabled(true);
			deleteButton.setEnabled(true);
			openButton.setEnabled(true);

			if (selectedSource.isDeleted())
				deleteButton.setText(Translate.getInstance().get(TranslateKeys.UNDELETE));
			else
				deleteButton.setText(Translate.getInstance().get(TranslateKeys.DELETE));
		}
		return this;
	}

	public JTree getTree(){
		return tree;
	}

	public class SourceCellRenderer extends JLabel implements TreeCellRenderer {
		public static final long serialVersionUID = 0;

		public SourceCellRenderer(){
			super();
		}

		public Component getTreeCellRendererComponent(JTree tree, Object node, boolean isSelected, boolean expanded, boolean leaf, int row, boolean hasFocus) {
			Object obj;
			DefaultMutableTreeNode n;
			if (node instanceof DefaultMutableTreeNode) {
				n = (DefaultMutableTreeNode) node;
				obj = n.getUserObject();
			}
			else
				return this;

			this.setBorder(BorderFactory.createEmptyBorder());

			if (obj instanceof Category) {
				Category c = (Category) obj;
				StringBuffer sbOpen = new StringBuffer("<html>");
				StringBuffer sbClose = new StringBuffer("</html>");

				if (c.isDeleted()){
					sbOpen.append("<s>");
					sbClose.insert(0, "</s>");
				}

				if (!c.isIncome()){
					sbOpen.append("<font color='red'>");
					sbClose.insert(0, "</font>");
				}

				long amount;

				if (tree.isCollapsed(new TreePath(n.getPath())))
					amount = getTotalAmount(n);
				else
					amount = c.getBudgetedAmount();

				StringBuffer sb = new StringBuffer();


				sb.append("<html><table><tr><td width=180px>")
				.append(sbOpen.toString())
				.append(c.toString())
				.append(sbClose.toString())
				.append("</td><td width=70px>")
				.append(sbOpen.toString())
				.append(PrefsInstance.getInstance().getPrefs().getCurrencySymbol())
				.append(Formatter.getInstance().getDecimalFormat().format(Math.abs((double) amount / 100.0)))
				.append(sbClose.toString())
				.append("</td></tr></table></html>");

				this.setText(sb.toString());
			}
			else if (obj instanceof Account) {			
				Account a = (Account) obj;
				StringBuffer sbOpen = new StringBuffer();
				StringBuffer sbClose = new StringBuffer();

				if (a.isDeleted()){
					sbOpen.append("<s>");
					sbClose.insert(0, "</s>");
				}

				if (a.getBalance() < 0){
					sbOpen.append("<font color='red'>");
					sbClose.insert(0, "</font>");
				}

				StringBuffer sb = new StringBuffer();


				sb.append("<html><table><tr><td width=200px>")
				.append(sbOpen.toString())
				.append(a.toString());
				if (PrefsInstance.getInstance().getPrefs().isShowInterestRate()
						&& (a.getInterestRate() != 0)){
					sb.append(" (")
					.append(Formatter.getInstance().getDecimalFormat().format(((double) a.getInterestRate()) / 100))
					.append("%)");
				}
				sb.append(sbClose.toString())
				.append("</td><td width=70px>")
				.append(sbOpen.toString())
				.append(PrefsInstance.getInstance().getPrefs().getCurrencySymbol())
				.append(Formatter.getInstance().getDecimalFormat().format(Math.abs((double) a.getBalance() / 100.0)))
				.append(sbClose.toString())
				.append("</td></tr></table></html>");

				this.setText(sb.toString());
			}
			//This is a wrapper class for Type which includes the total for all accounts of this type
			else if (obj instanceof TypeTotal) {			
				TypeTotal t = (TypeTotal) obj;
				StringBuffer sbOpen = new StringBuffer();
				StringBuffer sbClose = new StringBuffer();

				if (t.getType().isCredit()){
					sbOpen.append("<font color='red'>");
					sbClose.insert(0, "</font>");
				}

				StringBuffer sb = new StringBuffer();


				sb.append("<html><table><tr><td width=200px>")
				.append(sbOpen.toString())
				.append(t.getType().toString())
				.append(sbClose.toString())
				.append("</td><td width=70px>")
				.append(sbOpen.toString())
				.append(PrefsInstance.getInstance().getPrefs().getCurrencySymbol())
				.append(Formatter.getInstance().getDecimalFormat().format(Math.abs((double) t.getAmount() / 100.0)))
				.append(sbClose.toString())
				.append("</td></tr></table></html>");

				this.setText(sb.toString());
			}

			if (!Buddi.isMac()){
				if (isSelected){
					this.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
				}
				else{
					this.setBorder(BorderFactory.createEmptyBorder(1, 1, 1, 1));
				}
			}


			return this;
		}

		private long getTotalAmount(DefaultMutableTreeNode node){
			long amount = 0;
			if (node.getUserObject() instanceof Category){
				amount = ((Category) node.getUserObject()).getBudgetedAmount();
			}

			for (int i = 0; i < node.getChildCount(); i++) {
				amount += getTotalAmount((DefaultMutableTreeNode) node.getChildAt(i));
			}

			return amount;
		}
	}
}