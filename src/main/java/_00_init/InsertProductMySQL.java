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

import com.web.store.model.ProductBean;

import _00_init.util.HibernateUtil;
import _00_init.util.SystemUtils2018;

public class InsertProductMySQL {
	public static final String UTF8_BOM = "\uFEFF"; // 定義 UTF-8的BOM字元

	public static void main(String args[]) {
		int n = 0;
		SessionFactory factory = HibernateUtil.getSessionFactory();
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		String line = "";
		try {
			tx = session.beginTransaction();

			File file = new File("data/product.dat");
			// 由"data/Product.dat"逐筆讀入Product表格內的初始資料，然後依序新增
			// 到Product表格中
			try (FileInputStream fis = new FileInputStream(file);
					InputStreamReader isr = new InputStreamReader(fis, "UTF8");
					BufferedReader br = new BufferedReader(isr);) 
		    {
				while ((line = br.readLine()) != null) {
					n = 0;
					// 去除 UTF8_BOM: \uFEFF
					if (line.startsWith(UTF8_BOM)) {
						line = line.substring(1);
					}
					String[] token = line.split("\\|");
					ProductBean p = new ProductBean();
					SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
					String date = df.format(new Date());// new Date()为获取当前系统时间
					p.setProdName(token[0]);
					p.setProdType(token[1]);
					// 讀取圖片檔
					Blob blob = SystemUtils2018.fileToBlob(token[2].trim());
					p.setProdImg(blob);
					p.setProdImgName(token[3]);					
					p.setProdStock(Integer.parseInt(token[4].trim()));
					p.setProdPrice(Integer.parseInt(token[5].trim()));
					p.setProdCompany(token[6]);
					p.setProdIntro(token[7]);
					p.setProdCategory(token[8]);
					p.setProdUpDate(date);
					session.save(p);
					n++;
					System.out.println("新增一筆Product紀錄是否成功=" + n);
				}
				// 印出資料新增成功的訊息
				System.out.println("Product資料新增成功");
			} catch (Exception e) {
				System.err.println("新建Product表格時發生IO例外: " + e.getMessage());
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