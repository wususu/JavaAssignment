package chapter5;

import chapter5.annotation.Column;
import chapter5.annotation.Id;
import chapter5.annotation.Table;
import chapter5.core.CreateTableSchema;
import chapter5.core.SQLCreater;
import chapter5.core.TableInfo;
import chapter5.core.TableInfoCache;

@Table
public class Student {
	
	@Id
	private Long uid;

	@Column(name="stu_name", length=10, unique=true, nullable=false)
	private String name;
	
	@Column
	private String address;
	
	public Student() {
		// TODO Auto-generated constructor stub
	}
	
	public Student(Long uid, String name, String address) {
		super();
		this.uid = uid;
		this.name = name;
		this.address = address;
	}

	public Long getUid() {
		return uid;
	}

	public void setUid(Long uid) {
		this.uid = uid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

}
