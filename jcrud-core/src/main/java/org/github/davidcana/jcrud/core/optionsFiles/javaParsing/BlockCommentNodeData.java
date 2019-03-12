package org.github.davidcana.jcrud.core.optionsFiles.javaParsing;

import org.eclipse.jdt.core.dom.BlockComment;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.github.davidcana.jcrud.core.Constants;

public class BlockCommentNodeData extends NodeData {
	
	private String fullComment;
	
	public BlockCommentNodeData(BlockComment node, CompilationUnit cu, String source) {
		super(node, cu);
		this.fullComment = source.substring(this.getStart(), this.getEnd());
	}

	public boolean isJCRUDComment() {
		return this.fullComment.trim().startsWith(Constants.COMMENT_START);
	}
	public boolean isJCRUDClassComment() {
		return this.fullComment.trim().startsWith(Constants.CLASS_COMMENT_START);
	}
	public boolean isJCRUDFieldComment() {
		return this.fullComment.trim().startsWith(Constants.FIELD_COMMENT_START);
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
