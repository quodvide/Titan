package controller;
import java.io.File;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.Vector;

import ui.GUI;

public class FileLoadDSM {

	private GUI titan;

	public FileLoadDSM(GUI titan) {
		this.titan = titan;
	}

	public void ReadDependency(Scanner input){
		titan.getOrigin().RemoveAll();
		titan.getRoot().removeAllChildren();
		int size = Integer.parseInt(input.nextLine());
		titan.getOrigin().setSize(size);
		String[] a;		 
		for(int i=0; i<size; i++){
			titan.getOrigin().getDependency().add(new Vector<String>());
			titan.getOrigin().getcm().add(i+1+"");
			String De = input.nextLine();
			a = De.split(" ");
			for(int j=0; j<size; j++){
				if(a[j].equals("1"))
					titan.getOrigin().getDependency().get(i).add("x");
				else
					titan.getOrigin().getDependency().get(i).add(" ");
			}
		}
		
		for(int i=0; i<size; i++)
			titan.getOrigin().getNameList().add(input.nextLine());
	}

	public int ReadSize(Scanner input){
		return Integer.parseInt(input.nextLine());		
	}
}
