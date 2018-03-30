package controller;
import javax.xml.parsers.*;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.*;
import javax.swing.tree.DefaultMutableTreeNode;
import java.io.*;

public class ClusterSaveDSM {

	DefaultMutableTreeNode RootNode = new DefaultMutableTreeNode("$root");
	Document document;

	public void Save_Cluster(File FileName, DefaultMutableTreeNode root) {

		try {
			System.out.println("Save ROotttttttttttttt " + root.toString());
			document = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
			RootNode = root;
			Node cluster = document.createElement("cluster");
			document.appendChild(cluster);
			MakeXML(RootNode, cluster);
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			DOMSource source = new DOMSource(document);
			StreamResult result = new StreamResult(FileName + ".clsx");
			transformer.transform(source, result);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println(e.toString());
		}
	}

	public void MakeXML(DefaultMutableTreeNode root, Node node) {
		System.out.println("Save root is" + root.toString());
		if ( root.isLeaf()) {
			System.out.println("is Leaf");
			Element leaf = document.createElement("item");
			leaf.setAttribute("name", root.toString());
			node.appendChild(leaf);
		} else {
			System.out.println("is Folder");
			Node folder = document.createElement("group");
			((Element)folder).setAttribute("name", root.toString());
			node.appendChild(folder);
			for (int i=0; i<root.getChildCount(); i++) 
				MakeXML((DefaultMutableTreeNode)root.getChildAt(i), folder);
		}
	}
}

