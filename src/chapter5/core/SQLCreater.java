package chapter5.core;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import static chapter5.core.StringUtils.*;

public class SQLCreater {

	protected TableInfoCache tableInfoCache = new TableInfoCache();
		
	/**
	 * 生成要插入的value字符串
	 * @param methods
	 * @param fields
	 * @param obj
	 * @return
	 */
	protected String getObjValues(Method[] methods, List<FieldInfo> fields, Object obj){
		StringBuffer sf = new StringBuffer();
		String values = null;
		
		sf.append("(");
		for (FieldInfo fieldInfo : fields) {
			Object value = getValue(methods, fieldInfo, obj);
						// 若是字符串加引号
						if (
								fieldInfo.getType().equals(TableAttribute.CHAR.getTemplate()) ||
								fieldInfo.getType().equals(TableAttribute.VARCHAR.getTemplate()) 
								) {
							
							sf.append("'").append(value).append("'").append(",");
						}else {
							sf.append(value).append(",");
						}
		}
		values = sf.substring(0, sf.length()-1) + ")";
		return values;
	}
	
	/**
	 * 生成要插入的域字符串
	 * @param fields
	 * @return
	 */
	protected String getObjFields(List<FieldInfo> fields){
		StringBuffer sf = new StringBuffer();
		String value = null;
		
		sf.append("(");
		for (FieldInfo fieldInfo : fields) {
			sf.append(fieldInfo.getColumnName()).append(",");
		}
		value = sf.substring(0, sf.length()-1) + ")";
		return value;
	}

	/**
	 * 获取对象某一个域的值
	 * @param methods
	 * @param fieldInfo
	 * @param obj
	 * @return
	 */
	protected Object getValue(Method[] methods, FieldInfo fieldInfo, Object obj){
		String getter = generateGetter(fieldInfo.getFieldName());
		Object value = null;
		
		for (Method method : methods) {
			if (method.getName().equals(getter)) {
				try {
					value = method.invoke(obj);
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return value;
	}
	
	protected String getUpdateDate(Method[] methods, List<FieldInfo> fieldInfos, Object obj) {
		StringBuffer sf = new StringBuffer();
		
		sf.append(TableAttribute.SET).append(" ");
		
		for (FieldInfo fieldInfo : fieldInfos) {
			Object value = getValue(methods, fieldInfo, obj);
			sf.append(fieldInfo.getColumnName())
			.append(TableAttribute.EQUALES.getTemplate());
			
			if (
					fieldInfo.getType().equals(TableAttribute.CHAR.getTemplate()) ||
					fieldInfo.getType().equals(TableAttribute.VARCHAR.getTemplate()) 
					) {
				
				sf.append("'").append(value).append("'").append(",");
			}else {
				sf.append(value).append(",");
			}
		}
		return sf.substring(0, sf.length()-1);
	}
	
	/**
	 * 组装插入语句
	 * @param obj
	 * @return
	 */
	public String insert(Object obj) {
		StringBuffer sf = new StringBuffer();
		
		Class<?> clazz = obj.getClass();
		TableInfo tableInfo = this.tableInfoCache.get(clazz);
		String tbName = tableInfo.getTableName();
		List<FieldInfo> fields = tableInfo.getFieldInfos();
		Method[] methods = clazz.getMethods();
		
		sf.append(TableAttribute.INSERT_INTO.getTemplate())
		.append(" ").append(tbName)
		.append(getObjFields(fields)).append(" ")
		.append(TableAttribute.VALUES.getTemplate()).append(" ")
		.append(getObjValues(methods, fields, obj))
		.append(";");
		
		return sf.toString();
	}
	
	/**
	 * 组装删除的sql语句
	 * @param obj
	 * @return
	 */
	public String delete(Object obj){
		FieldInfo pkFieldInfo = null;
		String tbName = null;
		StringBuffer sf = new StringBuffer();
		
		Class<?> clazz = obj.getClass();
		TableInfo tableInfo = this.tableInfoCache.get(clazz);
		tbName = tableInfo.getTableName();
		List<FieldInfo> fieldInfos = tableInfo.getFieldInfos();
		for (FieldInfo fieldInfo : fieldInfos) {
			if (fieldInfo.isPrimaryKey()) {
				pkFieldInfo = fieldInfo;
			}
		}
		
		sf.append(TableAttribute.DELETE.getTemplate()).append(" ")
		.append(TableAttribute.FROM.getTemplate()).append(" ")
		.append(tbName).append(" ")
		.append(TableAttribute.WHERE.getTemplate()).append(" ")
		.append(pkFieldInfo.getColumnName()).append(" = ")
		.append(getValue(clazz.getMethods(), pkFieldInfo, obj))
		.append(";");
		
		return sf.toString();
	}
	
	/**
	 * 组装更新语句
	 * @param obj
	 * @return
	 */
	public String update(Object obj){
		FieldInfo pkFieldInfo = null;
		StringBuffer sf = new StringBuffer();
		Class<?> clazz = obj.getClass();
		TableInfo tableInfo = this.tableInfoCache.get(clazz);
		List<FieldInfo> fieldInfos = tableInfo.getFieldInfos();
		Method[] methods = clazz.getMethods();
		String tbName = tableInfo.getTableName();

		
		for (FieldInfo fieldInfo : fieldInfos) {
			if (fieldInfo.isPrimaryKey()) {
				pkFieldInfo = fieldInfo;
			}
		}
		
		sf.append(TableAttribute.UPDATE).append(" ")
		.append(tbName).append(" ");
		
		String updFields = getUpdateDate(methods, fieldInfos, obj);
		
		sf.append(updFields).append(" ")
		.append(TableAttribute.WHERE.getTemplate()).append(" ")
		.append(pkFieldInfo.getColumnName()).append(" ")
		.append(TableAttribute.EQUALES.getTemplate()).append(" ")
		.append(getValue(methods, pkFieldInfo, obj))
		.append(";");
		
		return sf.toString();
	}
	
}
