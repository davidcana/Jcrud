package org.github.davidcana.jcrud.core.optionsFiles.parsing;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.CompilationUnit;

public class NodeData {

	private ASTNode node;
	private CompilationUnit cu;
	
	public NodeData(ASTNode node, CompilationUnit cu) {
		this.node = node;
		this.cu = cu;
	}

	public int getStart() {
		return node.getStartPosition();
	}
	
	public int getEnd() {
		return this.getStart() + node.getLength();
	}
	
	public int getStartLineNumber() {
		 return cu.getLineNumber(node.getStartPosition()) - 1;
	}
	
	public int getEndLineNumber() {
		 return cu.getLineNumber(node.getStartPosition() + node.getLength()) - 1;
	}
}
