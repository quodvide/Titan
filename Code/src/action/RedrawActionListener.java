package action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import controller.ClusterUtilities;
import model.Matrix;
import ui.GUI;

public class RedrawActionListener implements ActionListener {

	GUI MyGui;

	public RedrawActionListener(GUI gui) {
		MyGui = gui;
	}

	public void actionPerformed(ActionEvent e) {
		ClusterUtilities a = new ClusterUtilities();
		Matrix DSM = a.clusterArrange(MyGui, MyGui.getJTree());
		System.out.println("Redraw" + DSM);
		MyGui.redraw(DSM);
	}
}
