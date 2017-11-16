package exam3;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FileUtils {

	  public static void readToBuffer(StringBuffer buffer, String filePath) throws IOException {
	        InputStream is = new FileInputStream(filePath);
	        String line; // 用来保存每行读取的内容
	        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
	        line = reader.readLine(); // 读取第一行
	        while (line != null) { // 如果 line 为空说明读完了
	            buffer.append(line); // 将读到的内容添加到 buffer 中
	         buffer.append(" ");
	            line = reader.readLine(); // 读取下一行
	        }
	        reader.close();
	        is.close();
	    }

	  
	  public static List<Integer> getWrolds(String path){
		  List<Integer> worlds = new ArrayList<>();
		  String[] wd = null;
		StringBuffer sf = new StringBuffer();
		 try {
			 readToBuffer(sf, path);
		 } catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		 if (sf.length() > 0) {
			wd = sf.toString().split(" ");
			for (String string : wd) {
				worlds.add(Integer.valueOf(string));
			}
		}
		 return worlds;
	  }
	  
	  public static List<Integer> getWorlds(){
		  File directory = new File("");
		  String path = null;
		  try {
			path = directory.getCanonicalPath() + "/src" + "/exam3" + "/worlds.txt";
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		  return getWrolds(path);
	  }
	  


	public static void main(String[] args) throws IOException {
		  File directory = new File("");
		  String path = directory.getCanonicalPath() + "/src" + "/exam3" + "/worlds.txt";
		  getWrolds(path);
	}
}
