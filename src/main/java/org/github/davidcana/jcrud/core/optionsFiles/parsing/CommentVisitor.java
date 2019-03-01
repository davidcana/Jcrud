package org.github.davidcana.jcrud.core.optionsFiles.parsing;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.BlockComment;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.FieldDeclaration;
import org.eclipse.jdt.core.dom.MarkerAnnotation;
import org.eclipse.jdt.core.dom.TypeDeclaration;
import org.github.davidcana.jcrud.core.optionsFiles.OptionsFile;
import org.github.davidcana.jcrud.core.optionsFiles.OptionsFileField;

class CommentVisitor extends ASTVisitor {
	
	private static final int MAX_DIFF_BETWEEN_CLASS_AND_ITS_COMMENT = 2;
	private CompilationUnit cu;
	private String source;
	
	private OptionsFile optionsFile = new OptionsFile();
	
	private int classLine;
	private Map<Integer, String> fields = new HashMap<>();
	
	public CommentVisitor(CompilationUnit cu, String source) {
		super();
		this.cu = cu;
		this.source = source;
	}
	
	public OptionsFile getOptionsFile() {
		return optionsFile;
	}
	
	/* Start of first pass */
	@Override
	public boolean visit(TypeDeclaration node) {
		
		NodeData nodeData = new NodeData(node, this.cu);

		this.classLine = nodeData.getStartLineNumber();
		String className = node.getName().toString();
		this.optionsFile.setEntityId(className);
		
		System.out.println(
				this.classLine + ": [Class] [" + className + "]"
		);
		return true;
	}
	
	@Override
	public boolean visit(FieldDeclaration node) {
		
		FieldDeclarationNodeData nodeData = new FieldDeclarationNodeData(node, this.cu);

		String fieldId = nodeData.getFieldId();
		int line = nodeData.getStartLineNumber();
		this.fields.put(line, fieldId);
		
		System.out.println(
				line + ": [Field] [" + fieldId + "]"
		);
		return true;
	}
	
	@Override
	public boolean visit(MarkerAnnotation node) {
		
		AnnotationNodeData nodeData = new AnnotationNodeData(node, this.cu);
		
		if (!nodeData.isId()) {
			return true;
		}
		
		int idAnnotationLine = nodeData.getStartLineNumber(); 
		String key = this.fields.get(idAnnotationLine);
		if (key == null) {
			throw new IllegalArgumentException("Error trying to locate key in class!");
		}
		this.optionsFile.setKey(key);
		
		System.out.println(
				idAnnotationLine + ": [IdAnnotation] [" + node + "]"
		);
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
		
		if (!searchInFields(comment, line) && !searchInClass(comment, line)) {
			throw new IllegalArgumentException("Error trying to link comment to field in class!");
		}
		
		System.out.println(
				line + ": [Comment] \n" + comment
		);
		return true;
	}
	/* End of second pass */

	private boolean searchInFields(String comment, int line) {
		
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
	
	private boolean searchInClass(String comment, int line) {
		
		if (line - this.classLine <= MAX_DIFF_BETWEEN_CLASS_AND_ITS_COMMENT) {
			this.optionsFile.setClassContents(comment);
			return true;
		}
		
		return false;
	}
}
