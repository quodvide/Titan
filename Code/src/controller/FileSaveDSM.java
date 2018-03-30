package controller;
import java.io.File;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.Vector;

import model.Matrix;

public class FileSaveDSM {

	private Matrix titan;

	public FileSaveDSM(Matrix titan) {
		this.titan = titan;
	}

	public void write(PrintWriter output, int size){
		output.println(size);

		for(int i=0; i<size; i++){
			for(int j=0; j<size; j++){
				if(titan.getDependency().get(i).get(j).equals("x")){
					output.print("1 ");
				}
				else{
					output.print("0 ");
				}

			}
			output.println("");
		}
		
		for(int i=0; i<size; i++)
			output.println(titan.getNameList().get(i));
		
		output.flush();
	}
}
