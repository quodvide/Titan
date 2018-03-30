package action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JTree;

import controller.ClusterUtilities;
import ui.GUI;

public class LoadClusteringActionListener implements ActionListener {

	GUI a;
	JTree tree;
	
	public LoadClusteringActionListener(GUI a, JTree mytree){
		tree = mytree;
		this.a = a;
	}
	
	public void actionPerformed(ActionEvent e) {
		ClusterUtilities b = new ClusterUtilities();
		System.out.println("Load clustering");
		JFrame window = new JFrame();
		JFileChooser fileChooser = new JFileChooser(".");
		fileChooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("클러스터파일 CLSX","clsx"));
		//파일오픈 다이얼로그 를 띄움
		int result = fileChooser.showOpenDialog(window);

		if (result == JFileChooser.APPROVE_OPTION) {
			//선택한 파일의 경로 반환
			a.setCLSFile(fileChooser.getSelectedFile());
			a.setRoot(a.getLoadCluster().Load_Cluster(a.getCLSFile()));
			a.setTable(b.MatrixInit(a.getOrigin(), a.getRoot()));
			//경로 출력
			System.out.println(a.getCLSFile());
		}           
		tree = new JTree(a.getRoot());
		a.ReTree();
	}

}
