package action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.*;

import ui.GUI;

public class SaveClusteringActionListener implements ActionListener {

	GUI MyGui;

	public SaveClusteringActionListener(GUI gui) {
		MyGui = gui;
	}

	public void actionPerformed(ActionEvent e) {
		//jMenuItem6ActionPerformed(evt);
		System.out.println("Save clustering");
		MyGui.getSaveCluster().Save_Cluster(MyGui.getCLSFile(), MyGui.getRoot());
	}
}
