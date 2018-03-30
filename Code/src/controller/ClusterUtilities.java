package controller;

import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.MutableTreeNode;

import model.Matrix;
import ui.GUI;

public class ClusterUtilities {
	
	int k; //clusterInit를 위한 변수선언...
	int count=0; //clusterInit를 위한 변수선언...
	
	public void clusterSwap(int i, int j, DefaultMutableTreeNode RootNode){
		DefaultMutableTreeNode temp; //임시저장소
		temp = (DefaultMutableTreeNode) RootNode.getChildAt(i);
		RootNode.insert((MutableTreeNode) RootNode.getChildAt(j),i);
		RootNode.insert(temp,j);
	}

	public void clusterSort(DefaultMutableTreeNode RootNode){
		int i,j;
		for(j=0; j<RootNode.getChildCount(); j++){
			if(RootNode.getChildAt(j).isLeaf()!=true)
				clusterSort((DefaultMutableTreeNode) RootNode.getChildAt(j));
		}
		
		for(i=RootNode.getChildCount()-2; i>0; i--)
			for(j=0; j<=i; j++)
				if(((RootNode.getChildAt(j).toString()).toUpperCase()).compareTo((RootNode.getChildAt(j+1).toString()).toUpperCase())>0)
					clusterSwap(j,j+1,(DefaultMutableTreeNode)RootNode);
	}

	public void clusterInit(Matrix DSM , DefaultMutableTreeNode RootNode){ //Matrix DSM을 클러스터 순서에 따라 바뀌게 함 디펜던시랑, 이름 순서 다 바뀜 by AnJaeHeok
	
		int i=0;
		
		for(int j=0; j<RootNode.getChildCount(); j++){
			if(!RootNode.getChildAt(j).isLeaf()) // Folder
				clusterInit(DSM,(DefaultMutableTreeNode) RootNode.getChildAt(j));
			else{ // Leaf
				k=0;
				
				while(k < DSM.getSize()){
					if(RootNode.getChildAt(j).toString().equals( DSM.getNameList().get(k).toString()))
						i=k;
					k++;
				}

				DSM.MatrixSwap(i, count);
				count++;
			}
		}
	}

	public Matrix MatrixInit(Matrix DSMori, DefaultMutableTreeNode RootNode) {
		Matrix DSM = DSMori.clone();
		clusterInit(DSM, RootNode);
		System.out.println(RootNode.getLeafCount() + "/" + DSM.getSize());
		if(RootNode.getLeafCount() < DSM.getSize()-1)
			DSM.RemoveFromTo(RootNode.getLeafCount(), DSM.getSize()-1);
		return DSM;
	}

	public Matrix clusterArrange(GUI gui, JTree tree){
		DefaultMutableTreeNode Node = (DefaultMutableTreeNode) tree.getModel().getRoot();
		int count = 0;
		int pathcount = 0;

		Matrix temp = gui.getTable().clone();
		Node.removeFromParent();
		System.out.println("My count is " + temp.getSize());
		
		while(Node != null){
			if( !Node.isLeaf() ) {
				System.out.println("My count is " + pathcount);
				System.out.println("I am " + Node.toString());
				if(tree.isExpanded(pathcount)) {
					System.out.println("Expended");
					Node = (DefaultMutableTreeNode) Node.getNextNode();
				} else {
					System.out.println("notExpanded");
					temp.FolderClose(count, count+Node.getLeafCount()-1, Node.toString());

					if ( Node.getNextSibling() !=null ) 
						Node = (DefaultMutableTreeNode)Node.getNextSibling();
					else {
						Node = (DefaultMutableTreeNode)Node.getParent();
						
						if(Node == null){ break; }
						
						while (!Node.isRoot()) {
							if( Node.getNextSibling() == null) {
								Node =(DefaultMutableTreeNode)Node.getParent();
								continue;
							}

							Node = Node.getNextSibling();
							System.out.println("Break!!");
							break;
						}

						if(Node.isRoot()) {
							System.out.println("Loop Over" + temp);
							return temp;
						} 
					}
					count++;
				}
			} else {
				Node = (DefaultMutableTreeNode) Node.getNextNode();
				count++;
			}
			pathcount++;
		} 
		return temp;
	}
}
