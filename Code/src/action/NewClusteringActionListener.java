package action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.tree.DefaultMutableTreeNode;

import ui.GUI;

public class NewClusteringActionListener implements ActionListener {

	GUI MyGui;

	public NewClusteringActionListener(GUI gui) {
		MyGui = gui;
	}

	public void actionPerformed(ActionEvent e) {
		System.out.println("New Clustering..........");
		MyGui.setTable(MyGui.getOrigin());
		MyGui.getRoot().removeAllChildren();

		for(int i=0; i<MyGui.getOrigin().getSize(); i++) 
			MyGui.getRoot().add(new DefaultMutableTreeNode(MyGui.getOrigin().getNameList().get(i)));

		MyGui.ReTree();
		MyGui.DeleteTable();
	}
}
