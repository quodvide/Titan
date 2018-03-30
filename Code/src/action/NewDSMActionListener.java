package action; // 추가한거다

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;

import ui.GUI;

public class NewDSMActionListener implements ActionListener {

	GUI Mygui;
	int number;

	public NewDSMActionListener(GUI gui){
		Mygui = gui;
	}

	public void actionPerformed(ActionEvent e) {
		number = Integer.parseInt(JOptionPane.showInputDialog(null, "Number"));
		Mygui.getOrigin().RemoveAll();
		Mygui.getRoot().removeAllChildren();
		Mygui.setOriginal(Mygui.getOrigin().newMatrixDSM(number));
		Mygui.setTable(Mygui.getOrigin().newMatrixDSM(number));
		Mygui.getMyModel().setDataVector(Mygui.getOrigin().newMatrixDSM(number));

		for(int i=0; i<Mygui.getOrigin().getSize(); i++) 
			Mygui.getRoot().add(new DefaultMutableTreeNode(Mygui.getOrigin().getNameList().get(i)));

		Mygui.ReTree();
		Mygui.setButtonsEnable();
		System.out.println("Make New DSM");
		Mygui.DeleteTable();
	}

}
