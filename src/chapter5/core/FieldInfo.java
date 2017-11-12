package chapter5.core;

public class FieldInfo {

	private String columnName;
	
	private String fieldName;
	
	private boolean isPrimaryKey;
	
	private boolean isUnique;
	
	private boolean isNull;
	
	private String type;
	
	private int length;
		
	public FieldInfo(String columnName, String fieldName, boolean isPrimaryKey, boolean isUnique, boolean isNull,
			String type, int length) {
		super();
		this.columnName = columnName;
		this.fieldName = fieldName;
		this.isPrimaryKey = isPrimaryKey;
		this.isUnique = isUnique;
		this.isNull = isNull;
		this.type = type;
		this.length = length;
	}
	
	public String getColumnName() {
		return columnName;
	}

	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public boolean isPrimaryKey() {
		return isPrimaryKey;
	}

	public void setPrimaryKey(boolean isPrimaryKey) {
		this.isPrimaryKey = isPrimaryKey;
	}

	public boolean isUnique() {
		return isUnique;
	}

	public void setUnique(boolean isUnique) {
		this.isUnique = isUnique;
	}

	public boolean isNull() {
		return isNull;
	}

	public void setNull(boolean isNull) {
		this.isNull = isNull;
	}
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	@Override
	public String toString() {
		return "FieldInfo [columnName=" + columnName + ", fieldName=" + fieldName + ", isPrimaryKey=" + isPrimaryKey
				+ ", isUnique=" + isUnique + ", isNull=" + isNull + ", type=" + type + ", length=" + length + "]";
	}
	
	
}
