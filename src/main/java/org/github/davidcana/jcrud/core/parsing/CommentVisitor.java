package org.github.davidcana.jcrud.core.parsing;

import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.BlockComment;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.FieldDeclaration;
import org.eclipse.jdt.core.dom.TypeDeclaration;

class CommentVisitor extends ASTVisitor {
	CompilationUnit cu;
	String source;
 
	public CommentVisitor(CompilationUnit cu, String source) {
		super(true);
		this.cu = cu;
		this.source = source;
	}
	
	@Override
	public boolean visit(BlockComment node) {
		int start = node.getStartPosition();
		int end = start + node.getLength();
		String comment = source.substring(start, end);
        int startLineNumber = cu.getLineNumber(node.getStartPosition()) - 1;
        int endLineNumber = cu.getLineNumber(node.getStartPosition() + node.getLength()) - 1;
		System.out.println(startLineNumber + "-"+ endLineNumber + ": [Comment] " +comment);
		return true;
	}
	
	@Override
	public boolean visit(FieldDeclaration node) {
		int start = node.getStartPosition();
		int end = start + node.getLength();
		String comment = source.substring(start, end);
        int startLineNumber = cu.getLineNumber(node.getStartPosition()) - 1;
        int endLineNumber = cu.getLineNumber(node.getStartPosition() + node.getLength()) - 1;

		System.out.println(startLineNumber + ": [Field] " + node);
		return true;
	}
	
	@Override
	public boolean visit(TypeDeclaration node) {
		int start = node.getStartPosition();
		int end = start + node.getLength();
		String comment = source.substring(start, end);
        int startLineNumber = cu.getLineNumber(node.getStartPosition()) - 1;
        int endLineNumber = cu.getLineNumber(node.getStartPosition() + node.getLength()) - 1;

		System.out.println(startLineNumber + ": [Class] " + node.getName());
		return true;
	}
}
