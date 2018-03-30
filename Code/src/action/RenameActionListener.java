package action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;










import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;

import ui.GUI;
import model.Matrix;
import model.Tree;

public class RenameActionListener implements ActionListener {

	GUI gui;
	
	public RenameActionListener(GUI gui) {
		this.gui = gui;
	}
	
	public void actionPerformed(ActionEvent e) {
		System.out.println("RenameActionListener");
		String name;
		TreePath path = gui.getJTree().getSelectionPath();
		DefaultMutableTreeNode node = (DefaultMutableTreeNode) path.getLastPathComponent();
		name = JOptionPane.showInputDialog(null, "Rename");

		if(node.isLeaf())
			gui.getTable().getNameList().set(gui.getTable().itemIndex(node.toString()), name);
		        	
		node.setUserObject(name);   
		DefaultTreeModel model = (DefaultTreeModel)(gui.getJTree().getModel());
		model.reload(node);
	}
}
