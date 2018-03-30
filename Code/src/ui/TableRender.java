package ui;

import javax.swing.JLabel;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.JTree;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.util.Vector;
import javax.swing.table.TableColumn;
import javax.swing.JTextField;
import javax.swing.table.TableCellRenderer;
import javax.swing.tree.DefaultMutableTreeNode;
import model.MatrixModel;
import model.Tree;

public class TableRender implements TableCellRenderer{

	private boolean bChargeFlag;
	private Tree tree;
	JLabel jl;
	JTextField jt;
	int iDisValue = 0;
	int iMaxValue = 0; //상한
	int iMinValue = 0; //하한
	boolean bTitle = false;
	float color = 10;
	int k = 0;
	float depth=0;
	float[][] Chart = {{0,0}, {(float)0.66,(float)0.5}, {(float)0.33,(float)0.5}, {(float)0.08,(float)0.5}, {(float)0.92,(float)0.5}, {(float)0.18,(float)0.5}, {(float)0.5,(float)0.5} };

	public TableRender(Tree tree){
		this.tree = tree;
	}

	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column){
		jl = new JLabel(value == null || value.toString().equals("-1") ? "" : value + "");
		//jl.setFont(new java.awt.Font("Dialog", 1, 13));
		jl.setHorizontalAlignment(SwingConstants.CENTER); //가운데 정렬
		jl.setOpaque(true);
		int num = tree.getCoreNodeNum();
		DefaultMutableTreeNode node = (DefaultMutableTreeNode) tree.getModel().getRoot();
		int[][] colorInfo = new int[100][100];
		colorInfo = ((MatrixModel)(table.getModel())).getColorInfo();
		jl.setBackground(Color.getHSBColor(Chart[colorInfo[column][row]%7][0], Chart[colorInfo[column][row]%7][1], 1));

		return jl;
	}

	public void setColColor(JTable jTable) {
		TableColumn tc1Model;
		for (int ik = 0; ik < jTable.getColumnCount(); ik++) {
			tc1Model = jTable.getColumnModel().getColumn(ik);
			tc1Model.setCellRenderer(new TableRender(tree));
		}
	} //테이블 Color색상 지정 함수

	public int allchildcount(DefaultMutableTreeNode node) {
		int count=0;
		for(int i=0; i<node.getChildCount(); i++){
			if(node.getChildAt(i).isLeaf())
				count++;
			else if(!tree.isExpanded(node.getLevel()))
				count++;
		}
		
		return count;
	}
}




