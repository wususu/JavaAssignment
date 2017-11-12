package chapter5;

import chapter5.core.CreateTableSchema;
import chapter5.core.SQLCreater;
import chapter5.core.TableInfo;
import chapter5.core.TableInfoCache;

public class Index {

	public static void main(String[] args) {
		TableInfo stuInfo = new CreateTableSchema().getTableInfo(Student.class);
		TableInfoCache.cache.put(Student.class.getName(), stuInfo);
		
		// 建表
		System.out.println(new CreateTableSchema().create(stuInfo) + "\n");
		
		Student stu = (new Student((long)2, "janke", "华南农业大学"));
		
		// 插入
		System.out.println(new SQLCreater().insert(stu) + "\n");
		
		// 删除
		System.out.println(new SQLCreater().delete(stu) + "\n");
		
		//更新
		System.out.println(new SQLCreater().update(stu) + "\n");
	}
}
