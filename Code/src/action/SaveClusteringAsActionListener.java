package action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.tree.DefaultMutableTreeNode;

import ui.GUI;

public class SaveClusteringAsActionListener implements ActionListener {

	GUI MyGui;

	public SaveClusteringAsActionListener(GUI gui) {
		MyGui = gui;
	}

	public void actionPerformed(ActionEvent e) {
		//jMenuItem6ActionPerformed(evt);
		System.out.println("Save clustering as");
		File fileName;
		JFileChooser fc=new JFileChooser(".");
		int yn=fc.showSaveDialog(fc);
		if(yn!=JFileChooser.APPROVE_OPTION) {
			fileName=null;
		}
		fileName=fc.getSelectedFile();
		System.out.println("getroot " + MyGui.getRoot().toString());
		MyGui.getSaveCluster().Save_Cluster(fileName, MyGui.getRoot());
	}
}
