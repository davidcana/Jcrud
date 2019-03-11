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
		
		String fragment = this.fieldNode.fragments().get(0).toString();
		int equals = fragment.lastIndexOf('='); 
		return equals == -1? 
				fragment:
				fragment.substring(0, equals);
	}
}
