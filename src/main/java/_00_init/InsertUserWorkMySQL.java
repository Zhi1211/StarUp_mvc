
package _00_init;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.sql.Blob;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.web.store.model.WorksBean;

import _00_init.util.HibernateUtil;
import _00_init.util.SystemUtils2018;


public class InsertUserWorkMySQL {
	public static final String UTF8_BOM = "\uFEFF"; // 定義 UTF-8的BOM字元

	public static void main(String args[]) {
		int n = 0;
		SessionFactory factory = HibernateUtil.getSessionFactory();
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		String line = "";
		try {
			tx = session.beginTransaction();

			File file = new File("data/userWork.dat");
			// 由"data/Product.dat"逐筆讀入Product表格內的初始資料，然後依序新增
			// 到Product表格中
			try (FileInputStream fis = new FileInputStream(file);
					InputStreamReader isr55 = new InputStreamReader(fis, "UTF8");
					BufferedReader br = new BufferedReader(isr55);) 
		    {
				while ((line = br.readLine()) != null) {
					n = 0;
					// 去除 UTF8_BOM: \uFEFF
					if (line.startsWith(UTF8_BOM)) {
						line = line.substring(1);
					}
					String[] token = line.split("\\|");
					WorksBean w = new WorksBean();
					SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
					String date = df.format(new Date());// new Date()为获取当前系统时间
					w.setWorksName(token[0]);
					w.setWorksIntro(token[1]);
					w.setWorksImgName(token[2]);
					// 讀取圖片檔01
					Blob blob = SystemUtils2018.fileToBlob(token[3].trim());				
					w.setWorksImg(blob);
//					w.setCaption_1(token[4]);
					w.setDetail_1(token[5]);
					w.setCaptionImgName_1(token[6]);
					// 讀取圖片檔02
					Blob blob_1 = SystemUtils2018.fileToBlob(token[7].trim());
					w.setCaptionImg_1(blob_1);
//					w.setCaption_2(token[8]);
					w.setDetail_2(token[9]);
					w.setCaptionImgName_2(token[10]);
					// 讀取圖片檔02
					Blob blob_2 = SystemUtils2018.fileToBlob(token[11].trim());
					w.setCaptionImg_2(blob_2);
					w.setWorksUpDate(date);
					session.save(w);
					n++;
					System.out.println("新增一筆User紀錄是否成功=" + n);
				}
				// 印出資料新增成功的訊息
				System.out.println("User資料新增成功");
			} catch (Exception e) {
				System.err.println("新建User表格時發生IO例外: " + e.getMessage());
			}
			tx.commit();
		} catch (Exception ex) {
			ex.printStackTrace();
			if (tx != null) 
				tx.rollback();
		}
		factory.close();
	}
}
