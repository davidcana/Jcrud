package org.github.davidcana.jcrud.core.model.storages;

import org.github.davidcana.jcrud.core.model.Form;
import org.github.davidcana.jcrud.core.model.Member;
import org.github.davidcana.jcrud.core.model.storages.MemberJDBCStorage;
import org.github.davidcana.jcrud.storages.JDBC.JDBCStorage;

public class MemberJDBCStorage extends JDBCStorage<Member, Integer, Form> {

	@Override
	public String getKeyFieldName() {
		return "id";
	}

	@Override
	public String getDefaultOrderFieldName() {
		return "id";
	}

	@Override
	public String getDefaultOrderType() {
		return "ASC";
	}

}
