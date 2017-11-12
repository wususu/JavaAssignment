package chapter3.one;

import java.util.Arrays;
import java.util.List;

/**
 * 类各部分字符串存储实体
 * @author janke
 *
 */
public class ClassBody {

	private String name;
	
	private String pkgNm;
	
	private String sprClsNm;
	
	private List<String> implNm;
	
	private List<Field> fields;
	
	private Class<?> clazz;
	
	public ClassBody(Class<?> clazz, String name, String pkgNm, String sprClsNm, List<String> implNm, List<Field> fields) {
		super();
		this.clazz = clazz;
		this.name = name;
		this.pkgNm = pkgNm;
		this.sprClsNm = sprClsNm;
		this.implNm = implNm;
		this.fields = fields;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPkgNm() {
		return pkgNm;
	}

	public void setPkgNm(String pkgNm) {
		this.pkgNm = pkgNm;
	}

	public String getSprClsNm() {
		return sprClsNm;
	}

	public void setSprClsNm(String sprClsNm) {
		this.sprClsNm = sprClsNm;
	}

	public List<String> getImplNm() {
		return implNm;
	}

	public void setImplNm(List<String> implNm) {
		this.implNm = implNm;
	}

	public List<Field> getFields() {
		return fields;
	}

	public void setFields(List<Field> fields) {
		this.fields = fields;
	}

	public Class<?> getClazz() {
		return clazz;
	}

	public void setClazz(Class<?> clazz) {
		this.clazz = clazz;
	}

	@Override
	public String toString() {
		return "ClassBody [\n pkgNm=" + pkgNm + ",\n  sprClsNm=" + sprClsNm + ",\n  implNm=" + (implNm)
				+ ",\n  fields=" + (fields) + "\n]";
	}
}
