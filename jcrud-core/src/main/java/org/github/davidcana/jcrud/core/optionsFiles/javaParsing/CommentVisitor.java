package org.github.davidcana.jcrud.core.optionsFiles.javaParsing;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.BlockComment;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.FieldDeclaration;
import org.eclipse.jdt.core.dom.MarkerAnnotation;
import org.eclipse.jdt.core.dom.PackageDeclaration;
import org.eclipse.jdt.core.dom.TypeDeclaration;
import org.github.davidcana.jcrud.core.optionsFiles.OptionsFile;
import org.github.davidcana.jcrud.core.optionsFiles.OptionsFileField;

class CommentVisitor extends ASTVisitor {
	
	private CompilationUnit cu;
	private String source;
	
	private OptionsFile optionsFile = new OptionsFile();
	
	private String className;
	private Map<Integer, String> fields = new HashMap<>();
	private boolean print;
	
	public CommentVisitor(CompilationUnit cu, String source, boolean print) {
		super();
		this.cu = cu;
		this.source = source;
		this.print = print;
	}
	
	public OptionsFile getOptionsFile() {
		return optionsFile;
	}
	
	/* Start of first pass */
	@Override
	public boolean visit(TypeDeclaration node) {
		
		NodeData nodeData = new NodeData(node, this.cu);

		int line = nodeData.getStartLineNumber();
		this.className = node.getName().toString();
		this.optionsFile.setClassName(this.className);
		
		if (this.print){
			System.out.println(
					line + ": [Class] [" + this.className + "]"
			);
		}
		
		return true;
	}
	
	@Override
	public boolean visit(FieldDeclaration node) {
		
		FieldDeclarationNodeData nodeData = new FieldDeclarationNodeData(node, this.cu);

		String fieldId = nodeData.getFieldId();
		int line = nodeData.getStartLineNumber();
		this.fields.put(line, fieldId);
		
		if (this.print){
			System.out.println(
					line + ": [Field] [" + fieldId + "]"
			);
		}
		
		return true;
	}
	
	@Override
	public boolean visit(MarkerAnnotation node) {
		
		AnnotationNodeData nodeData = new AnnotationNodeData(node, this.cu);
		
		if (!nodeData.isId()) {
			return true;
		}
		
		int line = nodeData.getStartLineNumber(); 
		String key = this.fields.get(line);
		if (key == null) {
			throw new IllegalArgumentException("Error trying to locate key in class " + this.className + "!");
		}
		this.optionsFile.setKey(key);
		
		if (this.print){
			System.out.println(
					line + ": [IdAnnotation] [" + node + "]"
			);
		}
		
		return true;
	}
	
	@Override
	public boolean visit(PackageDeclaration node) {
		
		NodeData nodeData = new NodeData(node, this.cu);
		
		int line = nodeData.getStartLineNumber();
		String packageName = node.getName().toString();
		this.optionsFile.setPackageName(packageName);
		
		if (this.print){
			System.out.println(
					line + ": [Package] [" + packageName + "]"
			);
		}
		
		return true;
	}
	/* End of first pass */
	
	/* Start of second pass */
	@Override
	public boolean visit(BlockComment node) {
		
		BlockCommentNodeData nodeData = new BlockCommentNodeData(node, this.cu, this.source);
        
		if (!nodeData.isJCRUDComment()) {
			return true;
		}
		String comment = nodeData.getComment();
		int line = nodeData.getStartLineNumber();
		
		if (!searchInClass(nodeData, comment) && !searchInFields(nodeData, comment, line)) {
			throw new IllegalArgumentException("Error trying to link comment in class " + this.className + "!");
		}
		
		if (this.print){
			System.out.println(
					line + ": [Comment] \n" + comment
			);
		}
		
		return true;
	}
	/* End of second pass */

	private boolean searchInFields(BlockCommentNodeData nodeData, String comment, int line) {
		
		if (!nodeData.isJCRUDFieldComment()) {
			return false;
		}
		
		for (int c = line; c >= 0; --c) {
			if (this.fields.containsKey(c)) {
				String fieldId = this.fields.get(c);
				this.optionsFile.add(
						new OptionsFileField(fieldId, comment)
				);
				return true;
			}
		}
		
		return false;
	}
	
	private boolean searchInClass(BlockCommentNodeData nodeData, String comment) {
		
		if (!nodeData.isJCRUDClassComment()) {
			return false;
		}
		
		if (this.optionsFile.getClassContents() != null){
			throw new IllegalArgumentException("Error trying to link class comment: there are more than one class comment, only one is supported!");
		}
		
		this.optionsFile.setClassContents(comment);
		return true;
	}
}
