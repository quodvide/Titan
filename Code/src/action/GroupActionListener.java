package action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreePath;

import ui.GUI;

public class GroupActionListener implements ActionListener {
	
	GUI myGui;
	String folderName;
	
	public GroupActionListener(GUI gui) {
		myGui = gui;
	}
	
	public void actionPerformed(ActionEvent e) {
		System.out.println("Group");
		folderName = JOptionPane.showInputDialog(null, "Group Name");
		int min = 1000;
		DefaultMutableTreeNode Folder = new DefaultMutableTreeNode(folderName);
		TreePath path[] = myGui.getJTree().getSelectionPaths();
		if (path == null) {
			System.out.println("Null");
			return;
		}
		
		int selectedNodeIndex[]= new int[path.length];
		MutableTreeNode node[]=new MutableTreeNode[path.length];

		for(int i=0; i<path.length; i++){
			node[i] =(MutableTreeNode) path[i].getLastPathComponent();
			selectedNodeIndex[i] = node[i].getParent().getIndex(node[i]);
			if(min>selectedNodeIndex[i])
				min = selectedNodeIndex[i];
		}

		for(int i=0; i<path.length; i++){
			for(int j=i+1; j<path.length; j++){
				if(selectedNodeIndex[i] > selectedNodeIndex[j]){
					MutableTreeNode tmpnode= node[i];
					int tmp = selectedNodeIndex[i];
					selectedNodeIndex[i] = selectedNodeIndex[j];
					selectedNodeIndex[j] = tmp;
					node[i] = node[j];
					node[j] = tmpnode;
				}
			}
		}	

		((DefaultMutableTreeNode)node[0].getParent()).insert(Folder, min);

		DefaultMutableTreeNode parentNode = (DefaultMutableTreeNode) node[0].getParent();
		int base = myGui.getTable().Whereis(node[0].toString());

		for(int i=0; i<path.length; i++){
			System.out.println("Group");
			DefaultTreeModel treeModel = (DefaultTreeModel) myGui.getJTree().getModel();
			treeModel.insertNodeInto(node[i], Folder, i);
			treeModel.nodeStructureChanged(parentNode);
			treeModel.nodeChanged(node[i]);
			myGui.getTable().InsertAtFrom(base+i, myGui.getTable().Whereis(node[i].toString()));
		}
	}
}
