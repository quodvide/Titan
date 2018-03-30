package model;

import java.util.Vector;

import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreePath;

import model.Tree;

public class Tree extends JTree implements Cloneable {

	private int index = 0;
	private DefaultMutableTreeNode root;

	public Tree(DefaultMutableTreeNode proot){
		super(proot);
		root = (DefaultMutableTreeNode)proot;
	}

	public DefaultMutableTreeNode getRoot() {
		return root;
	}

	public Tree clone(DefaultMutableTreeNode input) {
		DefaultMutableTreeNode CloneRoot = (DefaultMutableTreeNode)root.clone();
		DefaultMutableTreeNode Temp = CloneRoot;
		GenerateTree(CloneRoot, root);

		while(Temp != null) {
			if(Temp.toString().equals(input.toString()))
				break;
			System.out.println("temp = " + Temp.toString());
			Temp = Temp.getNextNode();
		}

		Temp.removeFromParent();
		Tree CloneTree = new Tree(Temp);
		return CloneTree;
	}

	public void GenerateTree(DefaultMutableTreeNode input, DefaultMutableTreeNode Root) {
		for(int i=0; i<Root.getChildCount(); i++){
			input.add(new DefaultMutableTreeNode(Root.getChildAt(i).toString()));

			if(!Root.getChildAt(i).isLeaf())
				GenerateTree((DefaultMutableTreeNode)input.getChildAt(i), (DefaultMutableTreeNode)Root.getChildAt(i));
		}
	}

	public int getCoreNodeNum(){
		int count = 0;
		int dep = 0;
		int nodeN = 0;					
		DefaultMutableTreeNode tempRoot = root;
		tempRoot.removeFromParent();

		while(tempRoot != null){
			if(!this.isExpanded(count)){ //�׷��� �����ִٸ�
				count++;
				nodeN++;

				if(tempRoot.isRoot())
					return nodeN;

				while(tempRoot.getNextSibling() == null){
					tempRoot = (DefaultMutableTreeNode)tempRoot.getParent();

					if(tempRoot.isRoot())
						return nodeN;
				}

				tempRoot = tempRoot.getNextSibling();
			} else {//�׷��� �����ִٸ�... �����ִ� �׷� + �������
				DefaultMutableTreeNode realtemp = tempRoot;
				tempRoot = tempRoot.getNextNode();
				count++;			

				if(tempRoot == null)
					System.out.println("tempRoot = " + realtemp.toString() + " = null");

				if(tempRoot.isLeaf()) {		//��������� ���� ��
					tempRoot = tempRoot.getNextNode();
					count++;
					nodeN++;
				} else {								//�׷��̶��
					if(this.isExpanded(count)){		//Ȯ��Ǿ�������
						tempRoot = tempRoot.getNextNode();
						count++;
					} else {							//�����ִٸ�
						nodeN++;
						count++;
						while(tempRoot.getNextSibling() == null) {
							tempRoot = (DefaultMutableTreeNode)tempRoot.getParent();

							if(tempRoot.isRoot()) {
								//System.out.println(nodeN + "�� �Դϴ�.5");
								return nodeN;
							}
						}

						if(tempRoot != null) 
							tempRoot = tempRoot.getNextSibling();
					}
				}								
			}
		}
		
		return nodeN;
	}

	public int[][] getColorInfo(){
		int dep = 0;
		int count = 0;
		int nodeN = 0;
		int num = this.getCoreNodeNum();
		int [][] colorInfo = new int[num][num];					
		DefaultMutableTreeNode tempRoot = root;

		while(tempRoot != null){
			if (tempRoot.isLeaf()){
				System.out.println("Name : " + tempRoot.toString());
				count++;
				nodeN++;
				tempRoot = tempRoot.getNextNode();
			} else if(!this.isExpanded(count)){ //�׷��� �����ִٸ�
				System.out.println("Name : " + tempRoot.toString() + " and I'm closed");
				if(tempRoot.isRoot()){
					System.out.println("Name : " + tempRoot.toString() + " I'm Root");
					colorInfo[0][0] = 0;
					return colorInfo;
				}

				dep = tempRoot.getLevel();
				colorInfo[nodeN][nodeN] = dep; 

				while(tempRoot.getNextSibling() == null){
					System.out.println("Name : " + tempRoot.toString() + " Have no sibling");
					tempRoot = (DefaultMutableTreeNode)tempRoot.getParent();
					if(tempRoot.isRoot())
						break;
				}

				if(tempRoot.isRoot()){
					System.out.println("Name : " + tempRoot.toString() + " I'm breaking");
					break;
				}

				tempRoot = tempRoot.getNextSibling();
				count++; 
				nodeN++;
				
			} else if(tempRoot.isRoot()){
				System.out.println("��Ʈ���");
				tempRoot = tempRoot.getNextNode();
				count++;
			} else {			//�׷��� �����ִٸ�(��ĥ�� ���� ���ϱ� ����) �����ִ� �׷� + �������
				System.out.println("Name : " + tempRoot.toString() + " I'm Folder and opened");
				int range = 0;
				dep = tempRoot.getLevel();
				tempRoot = tempRoot.getNextNode();
				DefaultMutableTreeNode temp2 = tempRoot;
				count++;
				int temp = count;
				
				while(tempRoot.getLevel()>dep) {
					if(tempRoot != null) {						
						if(tempRoot.isLeaf()) {		//��������� ���� ��
							range++;
							tempRoot = tempRoot.getNextNode();
							temp++;
						} else {								//�׷��̶��
							if(this.isExpanded(temp)){		//Ȯ��Ǿ�������
								tempRoot = tempRoot.getNextNode();
								temp++;
							} else {							//�����ִٸ�
								range++;
								while(tempRoot.getNextSibling() == null) {
									tempRoot = (DefaultMutableTreeNode)tempRoot.getParent();

									if(tempRoot == null) {
										//System.out.println("It's null");
										break;
									}
								}

								if(tempRoot != null) 
									tempRoot = tempRoot.getNextSibling();

								temp++;
							}
						}						

					} else
						break;

					if(tempRoot == null)
						break;
				}

				tempRoot = temp2;
				System.out.println(" I have " + range + "children");
				for(int i=nodeN;i<nodeN+range;i++)
					for(int j=nodeN; j<nodeN+range; j++)
						colorInfo[i][j] = dep;
			}
		}
		
		return colorInfo;
	}
}
