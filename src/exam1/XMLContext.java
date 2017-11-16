package exam1;

public class XMLContext extends AbstractBeanFactory{

	public XMLContext(String filePath) {
		// TODO Auto-generated constructor stub
		super(filePath);
		this.setSourceReader(new XMLSourceReader());
		this.registerBeans();
	}

	@Override
	protected void setSourceReader(SourceReader sourceReader) {
		// TODO Auto-generated method stub
		this.reader = sourceReader;
	}
}
