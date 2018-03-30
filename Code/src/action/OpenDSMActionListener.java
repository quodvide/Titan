package action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;

import controller.FileLoadDSM;
import ui.GUI;

public class OpenDSMActionListener implements ActionListener {
	
	FileLoadDSM load;
	GUI gui;
	DefaultMutableTreeNode myroot;
	JTree tree;

	public OpenDSMActionListener(GUI gui, JTree mytree){
		this.gui = gui;
		tree = mytree;
		myroot = new DefaultMutableTreeNode("$root");
	}

	public void actionPerformed(ActionEvent e) {
		JFrame window = new JFrame();
		JFileChooser fileChooser = new JFileChooser(".");
		fileChooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("확장자 DSM","dsm"));
		//파일오픈 다이얼로그 를 띄움
		int result = fileChooser.showOpenDialog(window);

		if (result == JFileChooser.APPROVE_OPTION) {
			//선택한 파일의 경로 반환
			File selectedFile = fileChooser.getSelectedFile();
			this.gui.setDSMFile(selectedFile);    
			System.out.println("OpenDSM");
			load = new FileLoadDSM(gui);            

			try {
				load.ReadDependency(new Scanner(selectedFile));
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} 
			//경로 출력

			for(int i=0; i<gui.getOrigin().getSize(); i++) 
				gui.getRoot().add(new DefaultMutableTreeNode(gui.getOrigin().getNameList().get(i)));

			gui.setTable(gui.getOrigin().clone());
			gui.ReTree();
			gui.setButtonsEnable();
			System.out.println(selectedFile); 
			gui.getMyModel().setDataVector(gui.getTable());
		}
		gui.DeleteTable();
	}
}
