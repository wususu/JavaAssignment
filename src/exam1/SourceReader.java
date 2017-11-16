package exam1;

import java.util.Map;

public interface SourceReader {

	
	Map<String, BeanInfo> loadBeans(String filePath);
	
}
