package org.github.davidcana.jcrud.core.optionsFiles.javaParsing;

import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.FieldDeclaration;

public class FieldDeclarationNodeData extends NodeData {

	private FieldDeclaration fieldNode;
	
	public FieldDeclarationNodeData(FieldDeclaration node, CompilationUnit cu) {
		super(node, cu);
		this.fieldNode = node;
	}

	public String getFieldId() {
		
		String text = this.fieldNode.toString();
		int start = 1 + text.lastIndexOf(' ');
		int end = text.lastIndexOf(';');
		
		return text.substring(start, end);
	}
}
