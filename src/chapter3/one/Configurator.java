package chapter3.one;

import java.io.InputStreamReader;
import java.util.Properties;


public class Configurator {
	
    protected String path;

	protected Properties prop;

	public static Configurator getInstance(){
		return Inner.instance;
	}

	private static class Inner{
		public static Configurator instance = new Configurator();
	}

	public Configurator setPath(String path){
		this.path = path;
		return this;
	}
	
	private Configurator(){
		this.load();
	}

	public void load()
    {
		if (path == null) {
            return ;
		}
        try
        {
            InputStreamReader is;

          // PRIVATE
    		System.out.println(path);

            is = new InputStreamReader(
                this.getClass()
                    .getResourceAsStream(path),
                "utf-8"
            );

            this.prop = new Properties(this.prop);
            this.prop.load(is);
            is.close();
        }
        catch (Exception ex)
        {
            throw(new RuntimeException(ex.getMessage(), ex));
        }
    }
	
	public Properties getProperties(){
		Properties properties = new Properties(this.prop);
		return properties;
	}
	
	public String get(String key){
		return this.prop.getProperty(key);
	}
	
	public Integer getInt(String key){
		String value = this.get(key);
		try{
			return Integer.valueOf(value);
		}catch (Exception e) {
			// TODO: handle exception
//			logger.warn(e.toString());
			return null;
		}
	}
	
	public Long getLong(String key){
		String value = this.get(key);
		try{
			return Long.valueOf(value);
		}catch (Exception e) {
			// TODO: handle exception
//			logger.warn(e.toString());
			return null;
		}
	}
	
	public Character getChar(String key){
		String value = this.get(key);
		try{
			return Character.valueOf(value.toCharArray()[0]);
		}catch (Exception e) {
			// TODO: handle exception
//			logger.warn(e.toString());
			return null;
		}
	}
	
	public Byte getByte(String key){
		String value = this.get(key);
		try{
			return Byte.valueOf(value);
		}catch (Exception e) {
			// TODO: handle exception
//			logger.warn(e.toString());
			return null;
		}
	}
	
	public  Boolean getBoolean(String key) {
		String value = this.get(key);
		try{
			return Boolean.valueOf(value);
		}catch (Exception e) {
			// TODO: handle exception
//			logger.warn(e.toString());
			return null;
		}
	}
}
