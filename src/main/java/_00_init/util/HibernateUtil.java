package _00_init.util;

import java.io.CharArrayWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.sql.Blob;
import java.sql.Clob;

import javax.sql.rowset.serial.SerialBlob;
import javax.sql.rowset.serial.SerialClob;

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
public class HibernateUtil {

	private static SessionFactory sessionFactory = buildSessionFactory();
	private static SessionFactory buildSessionFactory() {
		try {
			// 由組態檔(hibernate.cfg.xml)內的資訊來建立SessionFactory物件
			// Hibernate 5.x 的寫法
			StandardServiceRegistry standardRegistry = new StandardServiceRegistryBuilder()
			.configure("hibernateOnly.cfg.xml").build();
			// 以下為Mapping檔的寫法
			MetadataSources sources = new MetadataSources(standardRegistry);
			Metadata metadata = sources.getMetadataBuilder().build();
			// 共同部分
			SessionFactory sessionFactory = metadata.getSessionFactoryBuilder().build();
			System.out.println("SessionFactory Ready...");
			return sessionFactory; 
			
		} catch (Throwable ex) {
			System.err.println("Initial SessionFactory creation failed. " + ex);
			ex.printStackTrace();
			throw new ExceptionInInitializerError(ex);
		}
	}
	// 外界呼叫此靜態方法來取的 SessionFactory物件
	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public static void close() {
		getSessionFactory().close();
	}
	public static Clob fileToClob(String filename, String encoding) {
		Clob clob = null;
		try (
			FileInputStream fis = new FileInputStream(filename);
			InputStreamReader in = new InputStreamReader(fis, encoding);
			CharArrayWriter caw = new CharArrayWriter();
		) {
			int len = 0;
			char[] ca = new char[8192];
			while ((len = in.read(ca)) != -1) {
				caw.write(ca, 0, len);
			}
			clob = new SerialClob(caw.toCharArray());
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return clob;
	}

	public static Blob fileToBlob(String filename) {
		Blob blob = null;
		File f = new File(filename);
		int len = (int) f.length();
		byte[] b = new byte[len];
		try (
			FileInputStream fis = new FileInputStream(f);
		) {
			fis.read(b);
			blob = new SerialBlob(b);
		} catch (Exception ex) {
			ex.getLocalizedMessage();
		}
		return blob;
	}
}