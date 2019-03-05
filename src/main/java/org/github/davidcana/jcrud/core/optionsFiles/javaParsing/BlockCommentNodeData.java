package org.github.davidcana.jcrud.core.optionsFiles.javaParsing;

import org.eclipse.jdt.core.dom.BlockComment;
import org.eclipse.jdt.core.dom.CompilationUnit;

public class BlockCommentNodeData extends NodeData {
	
	private static final String COMMENT_START = "/*jcrud";
	private String fullComment;
	
	public BlockCommentNodeData(BlockComment node, CompilationUnit cu, String source) {
		super(node, cu);
		this.fullComment = source.substring(this.getStart(), this.getEnd());
	}

	public boolean isJCRUDComment() {
		return this.fullComment.trim().startsWith(COMMENT_START);
	}
	
	public String getComment() {
		
		String lines[] = this.fullComment.split("[\\r\\n]+");
		String newLine = "\n";
		
		StringBuilder sb = new StringBuilder();
		for (int c = 1; c < lines.length - 1; ++c) {
			sb.append(
					lines[c] + newLine
			);
		}
		
		return sb.toString();
	}
}
