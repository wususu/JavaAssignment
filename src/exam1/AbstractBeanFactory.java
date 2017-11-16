package exam1;

import java.lang.reflect.Method;
import java.util.Map;

public abstract class AbstractBeanFactory implements BeanFactory{
	
	private String filePath;
	
	private Map<String, BeanInfo> container;
	
	protected SourceReader reader;
	
	public AbstractBeanFactory(String filePath) {
		// TODO Auto-generated constructor stub
		this.filePath = filePath;
	}
	
	protected abstract void setSourceReader(SourceReader sourceReader);
	
	public void registerBeans(){
		this.container = this.reader.loadBeans(this.filePath);
	}
	
	@Override
	public Object getBean(String name) {
		// TODO Auto-generated method stub
		BeanInfo beanInfo = this.container.get(name);
		if (beanInfo == null) {
			return null;
		}
		
		return this.parseBean(beanInfo);
	}
	
	/**
	 * 解析并生成对象实例
	 * @param beanInfo
	 * @return
	 */
	protected Object parseBean(BeanInfo beanInfo){
		System.out.println(beanInfo);
		Class clazz;
		try{
			clazz = Class.forName(beanInfo.getType());
			Object bean = clazz.newInstance();
			Method[] methods = clazz.getMethods();
			
			for(String property: beanInfo.getProperties().keySet()){
				String setter = "set" + StringUtils.firstCharToUp(property);
				
				for(Method method: methods){
					String methodName = method.getName();
					if (methodName.equals(setter)) {
						Object value = beanInfo.getProperties().get(property);
						method.invoke(bean, value);
						continue;
					}
				}
			}
			return bean;
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}

}
