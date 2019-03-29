package org.github.davidcana.jcrud.core.model.filters;

import org.github.davidcana.jcrud.core.model.Member;
import org.github.davidcana.jcrud.storages.JDBC.DefaultFilterManager;

public class FormFilterManager extends DefaultFilterManager<Member> {
	
	static private FormFilterManager instance;
	
	@Override
	protected int appendPart(String fieldName, String sqlName, String type, Object value, Integer c, StringBuilder sb) {
		
		if ("year".equals(fieldName)) {
			return this.appendYearPart(fieldName, sqlName, type, value, c, sb);
		}
		
		return super.appendPart(fieldName, sqlName, type, value, c, sb);
	}

	protected int appendYearPart(String fieldName, String sqlName, String type, Object value, Integer c, StringBuilder sb) {
		
		String operator = " AND ";
		
		this.appendOperator(sb, c, operator);
		sb.append(
				"DATE_PART('year', record_date_time) = ?"
		);
		
		return 1;
	}
	
	static public FormFilterManager getInstance() {
		
		if (instance == null){
			instance = new FormFilterManager();
		}
		
		return instance;
	}
}
