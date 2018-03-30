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

import ui.GUI;
import controller.FileSaveDSM;
import model.Matrix;
import model.MatrixModel;

public class SaveDSMActionListener implements ActionListener {
	
	FileSaveDSM save;
	GUI DSM;
	MatrixModel model;
	Matrix Mat;
	
	public SaveDSMActionListener(GUI gui , MatrixModel model){
		this.DSM = gui;
		this.model = model;
	}

	public void actionPerformed(ActionEvent e) {
		JFrame window = new JFrame();
		Mat = model.getDSM();
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("확장자 DSM","dsm"));
		//파일오픈 다이얼로그 를 띄움
		int result = fileChooser.showSaveDialog(window);

		if (result == JFileChooser.APPROVE_OPTION) {
			//선택한 파일의 경로 반환
			File selectedFile = fileChooser.getSelectedFile();
			System.out.println("OpenDSM");
			save = new FileSaveDSM(Mat);

			try {
				System.out.println(Mat.getSize());
				System.out.println(save.toString());
				save.write(new PrintWriter(selectedFile + ".dsm"),Mat.getSize());
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			}
			
			if(DSM.getTable().getSize() != Mat.getSize())
				return;
			
			for(int i=0; i<DSM.getTable().getSize(); i++)
				if(!DSM.getTable().getNameList().get(i).equals(Mat.getNameList().get(i)))
					return;
			
			System.out.println("You Passed the Test!");
			DSM.setTable(Mat.clone());
		}
	}
}
