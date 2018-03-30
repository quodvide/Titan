package controller;
import javax.xml.parsers.*;
import org.w3c.dom.*;
import javax.swing.tree.DefaultMutableTreeNode;
import java.io.*;

public class ClusterLoadDSM {

	DefaultMutableTreeNode RootNode = new DefaultMutableTreeNode("$root");
	Document document;

	public DefaultMutableTreeNode Load_Cluster(File FileName) {

		try {
			DocumentBuilderFactory Fac = DocumentBuilderFactory.newInstance();
			DocumentBuilder parser = Fac.newDocumentBuilder();
			Document clsx_Doc = null;
			clsx_Doc = parser.parse(FileName);
			Element root = clsx_Doc.getDocumentElement();
			getchilds(root);

			if(root.getChildNodes().item(1).getNodeName().equals("group")) 		
				return (DefaultMutableTreeNode)RootNode.getChildAt(0);
			else
				return RootNode;

		} 	catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println(e.toString());           
		}

		System.out.println("Fail to load... ");
		return null;
	}

	public void getchilds(Node input) {		
		if ( input.hasChildNodes() ) {
			NodeList folder = input.getChildNodes();
			for (int i=0, j=0; i<folder.getLength(); i++) {
				Node Ele = folder.item(i);
				if ( !Ele.getNodeName().equals("#text")) {
					String NodeName = ((Element)Ele).getAttribute("name");
					RootNode.add(new DefaultMutableTreeNode(NodeName));
					RootNode = (DefaultMutableTreeNode)RootNode.getChildAt(j++);
					getchilds(Ele);
					RootNode = (DefaultMutableTreeNode)RootNode.getParent();
				} else {
					getchilds(Ele);
				}
			}
		}
	}	
}

