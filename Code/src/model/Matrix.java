package model;
import java.io.PrintWriter;
import java.util.Vector;
import java.util.Scanner;

public class Matrix implements Cloneable {

	private int Size;
	private Vector<String> NameList;
	private Vector<String> cm;
	private Vector<Vector<String>> Dependency;	

	public Matrix() {
		NameList = new Vector<String>();
		cm = new Vector<String>();
		Dependency = new Vector<Vector<String>>();
	}

	Matrix(Vector<String> Names, Vector<Vector<String>> Dependencies) {
		NameList = Names;
		Dependency = Dependencies;
	}

	public void setSize(int n){
		Size = n;
	}
	
	public int getSize(){
		return Size;
	}

	public void setNameList(Vector<String> NameList){
		this.NameList = NameList;
	}
	
	public Vector<String> getNameList(){
		return NameList;
	}

	public Vector<Vector<String>> getDependency(){
		return Dependency;
	}
	
	public void setDependency(Vector<Vector<String>> n){
		Dependency = n;
	}

	public Vector<String> getcm(){
		return cm;
	}
	
	public void setcm(Vector<String> n){
		cm = n;
	}
	
	public void insertDependency(String name, int index) {
		//예외처리 필요
		getDependency().insertElementAt(new Vector<String>(getSize()), index+1);
		getNameList().insertElementAt(name, index+1);
	
		for(int i=0; i<getSize(); i++)
			getDependency().get(index+1).add(" ");
		
		for(int i=0; i<getSize()+1; i++)
			getDependency().get(i).insertElementAt(" ", index+1); 
		
		getcm().add((getSize()+1)+"");
		setSize(getSize()+1);
	}

	public int itemIndex(String name){
	
		for(int i=0; i<getSize(); i++)
			if(name.equals(getNameList().get(i)))
				return i;
		
		return -1;
	}

	public Matrix clone() {
		Matrix MyMatrix = new Matrix();
		MyMatrix.setSize(this.getSize());
		MyMatrix.setNameList((Vector)this.getNameList().clone());
		MyMatrix.setcm((Vector)this.getcm().clone());
		Vector<Vector<String>> vector = new Vector<Vector<String>>();

		for(int i=0; i<this.getSize(); i++){
			vector.add(new Vector<String>());
			for(int j=0; j<this.getSize(); j++)
				vector.get(i).add(this.getDependency().get(i).get(j).toString());
		}

		MyMatrix.setDependency(vector);

		return MyMatrix;
	}

	public void RemoveFromTo(int i, int j) {
		for(; i<=j; j--)
			RemoveAt(i);
	}


	public void RemoveAt(int index) {
		Dependency.remove(index);
		NameList.remove(index);
		cm.remove(index);
		Size--;

		for(int i=0; i<Size; i++) {
			Dependency.get(i).remove(index);
		}
	}

	public int Whereis(String name) {
		for(int i=0; i<Size; i++)
			if(NameList.get(i).equals(name)) 
				return i;
		
		return -1;
	}

	public void InsertAtFrom(int at, int from) {
		for(int i=from; i>at; i--)
			MatrixSwap(i, i-1);
	}

	public void RemoveAll() {
		setSize(0);
		setNameList(new Vector<String>());
		setcm(new Vector<String>());
		setDependency(new Vector<Vector<String>>());
	}
	
	public void MatrixSwap(int i, int j){ // i번째와 j번째 자리 바꾸는 함수(NameList의 위치가 먼저 변경되고 그다음 Dependency를 바꿈) by 안재혁
		Vector<String> temp;
		temp = new Vector<String>();
		temp.add(NameList.get(i));
		NameList.set(i, NameList.get(j));
		NameList.set(j,temp.get(0));

		for(int k = 0; k<Size; k++){
			Vector<String> temp1;
			temp1 = new Vector<String>();
			temp1.add(Dependency.get(i).get(k));
			Dependency.get(i).set(k, Dependency.get(j).get(k));
			Dependency.get(j).set(k, temp1.get(0));
		}

		for(int k = 0; k<Size; k++){
			Vector<String> temp1;
			temp1 = new Vector<String>();
			temp1.add(Dependency.get(k).get(i));
			Dependency.get(k).set(i, Dependency.get(k).get(j));
			Dependency.get(k).set(j, temp1.get(0));
		}
	}

	public void Sort(int n, int m){ // Dsm 이름순으로 정렬하는 함수(MatrixSwap 사용) by 안재혁
		int i,j;
		for(i=m-1;i>n;i--)
			for(j=n; j<=i; j++)
				if(NameList.get(j).toLowerCase().compareTo(NameList.get(j+1).toLowerCase())>0)
					MatrixSwap(j,j+1);
	}

	public void ArrangeCM() {
		for(int b=0; b<Size; b++) 
			this.getcm().setElementAt(""+(b+1), b);
	}

	public Matrix FolderClose(int i, int j, String str) {
		System.out.println(i + " " + j);

		for(int b=0; b<Size; b++)
			for(int a=i; a<=j; a++) {
				if ( Dependency.get(a).get(b).equals(" ") ){
					Dependency.get(i).set(b, " ");
					continue;
				}
				
				Dependency.get(i).set(b, "x");
				break;
			}

		for(int b=0; b<Size; b++)
			for(int a=i; a<=j; a++){
				if ( Dependency.get(b).get(a).equals(" ") ){
					Dependency.get(b).set(i, " ");
					continue;
				}
				
				Dependency.get(b).set(i, "x");
				break;
			}
		

		for(int a=i+1; a<=j; a++){
			System.out.println(NameList.get(i+1));
			Dependency.remove(i+1);
			NameList.remove(i+1);
			this.getcm().remove(i+1);
		}

		for(int b=0; b<Size-(j-i); b++)
			for(int a=i+1; a<=j; a++)	
				Dependency.get(b).remove(i+1);

		for(int b=0; b<Size-(j-i); b++) 
			this.getcm().setElementAt(""+(b+1), b);


		this.getDependency().get(i).set(i, " ");
		NameList.set(i, str);
		this.setSize(Size-(j-i));

		return this;
	}
	
	public Matrix newMatrixDSM(int number){
		Matrix newMatrix = new Matrix();
		newMatrix.setSize(number);
		
		for(int i=0; i<newMatrix.getSize(); i++){
			newMatrix.getDependency().add(new Vector<String>());
			newMatrix.getcm().add(i+1+"");
			
			for(int j=0; j<newMatrix.getSize(); j++)
				newMatrix.getDependency().get(i).add(" ");
		}
		
		for(int i=0; i<newMatrix.getSize(); i++)
			newMatrix.getNameList().add("item"+i);
		
		return newMatrix;
	}
}
