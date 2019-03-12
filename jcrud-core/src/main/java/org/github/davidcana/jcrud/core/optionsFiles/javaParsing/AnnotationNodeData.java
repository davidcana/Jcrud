package org.github.davidcana.jcrud.core.optionsFiles.javaParsing;

import java.util.Arrays;
import java.util.List;

import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.MarkerAnnotation;

public class AnnotationNodeData extends NodeData {

	public static final List<String> IDS =
		    Arrays.asList(
		        "@JCRUDId",
		        "@JDBCId"
		   );
	private MarkerAnnotation annotationNode;
	
	public AnnotationNodeData(MarkerAnnotation node, CompilationUnit cu) {
		super(node, cu);
		this.annotationNode = node;
	}

	public String getAnnotationId() {
		return this.annotationNode.toString();
	}
	
	public boolean isId() {
		return IDS.contains(
				this.getAnnotationId());
	}
}
