package chapter3.one;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.Buffer;

/**
 * 文件操作工具类
 * @author janke
 *
 */
public class FileUtils {

	public static void save(String path, String content) throws IOException {
		FileWriter fw = null;
		//如果文件存在，则追加内容；如果文件不存在，则创建文件
		File f=new File(path);
		fw = new FileWriter(f, true);
		PrintWriter pw = new PrintWriter(fw);
		pw.println(content);
		pw.flush(); 
		fw.flush();
		pw.close();
		fw.close();
	}
	
	public static void create(String path, String content) throws IOException
	{
		File file = new File(path);
		if (file.exists()) {
			file.delete();
		}
		if (!file.getParentFile().exists()) {
			file.getParentFile().mkdirs();
		}
		file.createNewFile();
		FileWriter fWriter = new FileWriter(file, true);
		BufferedWriter bw = new BufferedWriter(fWriter);
		bw.write(content);
		bw.flush();
		bw.close();
		fWriter.close();
		
		FileReader fr = new FileReader(file);
		BufferedReader br = new BufferedReader(fr);
		String string = br.readLine();
	}

	
	public static void main(String[] args) {
		File directory = new File(".");//设定为当前文件夹
		try{
		    String rootPath = directory.getCanonicalPath();//获取标准的路径
		    String filePath = rootPath + "/Person2.java";
		    create(filePath, "hahaaah");
		}catch (Exception e) {
			// TODO: handle exception
		}	
	}
}
