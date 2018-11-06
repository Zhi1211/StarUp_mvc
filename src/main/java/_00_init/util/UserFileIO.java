package _00_init.util;
 
import java.io.*;
import java.sql.Clob;
import java.sql.SQLException;


public class UserFileIO {
	String filePath;

	public UserFileIO(String filePath) {
		this.filePath = filePath;
		String dir = filePath.substring(0, filePath.lastIndexOf("/"));
		File f = new File(dir);
		if ( !f.exists() ) {
			f.mkdirs();
		}
	}
	public UserFileIO() {
		super();
	}	
	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public void insertUser(Clob introduction) throws IOException {
		FileWriter fw = null;
		PrintWriter pw = null;

		try {
			fw = new FileWriter(filePath, true);
			pw = new PrintWriter(fw);
			pw.println(introduction);
		} finally {
			if (pw != null) {
				pw.close();
			}
			if (fw != null) {
				try {
					fw.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	
}