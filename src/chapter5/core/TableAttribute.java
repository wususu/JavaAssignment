package chapter5.core;

import java.util.Set;

public enum TableAttribute {
	
	UNIQUE("UNIQUE (%s)"),
	PRIMARY_KEY("PRIMARY KEY (%s)"),
	NOT_NULL("NOT NULL"),
	AUTO_INCREMENT("AUTO_INCREMENT"),
	
	INSERT_INTO("INSERT INTO"),
	VALUES("VALUES"),
	SET("SET"),
	DELETE("DELETE"),
	FROM("FROM"),
	WHERE("WHERE"),
	AND("AND"),
	OR("OR"),
	EQUALES("="),
	UPDATE("UPDATE"),
	
	CHAR("CHAR"),
	VARCHAR( "VARCHAR(255)"),
	INT("INT(11)"),
	BIGINT("BIGINT(20)"),
	TINYINT("TINYINT(1)"),
	DOUBLE("DOUBLE"),
	FLOAT("FLOAT"),
	MEDIUMINT("MEDIUMINT(9)"),
	SMALLINT("SMALLINT"),
	DATETIME("DATETIME");
	
	private String template;
	
	private TableAttribute(String sql) {
		// TODO Auto-generated constructor stub
		this.template = sql;
	}
	
	public String getTemplate(){
		return this.template;
	}
}
