package model;

import java.util.Vector;

import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;

public class MatrixModel extends DefaultTableModel {

	private Matrix DSMTable;
	private int[][] colorInfo = {};

	public MatrixModel() {

	}

	public MatrixModel(Vector<Vector<String>> dm, Vector<String> cm) {
		// TODO Auto-generated constructor stub
		super(dm,cm);
	}
	
	public Matrix getDSM() {
		if(DSMTable == null)
			return null;

		return DSMTable.clone();
	}
	
	public void insertDependency(String name, int index) {
		DSMTable.insertDependency(name, index);
	}

	public int itemIndex(String name){
		int index;
		index = DSMTable.itemIndex(name);
		return index;
	}

	@Override

	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return false;
	}
	
	public void setDataVector(Matrix matrix){
		DSMTable = matrix;
		super.setDataVector(DSMTable.getDependency(), DSMTable.getcm());
		colorInfo = new int[DSMTable.getSize()][DSMTable.getSize()];
	}

	public void setColorInfo(int[][] info) {
		colorInfo = info;
	}
	
	public int[][] getColorInfo(){
		return colorInfo;
	}
}
