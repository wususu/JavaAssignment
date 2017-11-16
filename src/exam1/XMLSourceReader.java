package exam1;

import java.util.HashMap;
import java.util.Map;

public class XMLSourceReader implements SourceReader{

	@Override
	public Map<String, BeanInfo> loadBeans(String filePath) {
		// TODO Auto-generated method stub
		BeanInfo beanInfo = new BeanInfo();
		beanInfo.setId("Person");
		beanInfo.setType("exam1.Person");
		beanInfo.addProperty("name", "Janke");
		Map<String, BeanInfo> beans = new HashMap<>();
		beans.put("Person", beanInfo);

		return beans;
	}

}
