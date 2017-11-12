package chapter5.core;

import java.util.LinkedList;
import java.util.List;
import java.util.Vector;

public class TableInfo {

	private String typeName;
	
	private String tableName;
	
	private List<FieldInfo> fieldInfos;

	public TableInfo() {
		// TODO Auto-generated constructor stub
		this.fieldInfos = new LinkedList<FieldInfo>();
	}
	
	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public List<FieldInfo> getFieldInfos() {
		return fieldInfos;
	}

	public void setFieldInfos(List<FieldInfo> fieldInfos) {
		this.fieldInfos = fieldInfos;
	}
	
	public void addFieldInfo(FieldInfo fieldInfo){
		this.fieldInfos.add(fieldInfo);
	}

	@Override
	public String toString() {
		return "TableInfo [typeName=" + typeName + ", tableName=" + tableName + ", fieldInfos=" + fieldInfos + "]";
	}
	
	
}
