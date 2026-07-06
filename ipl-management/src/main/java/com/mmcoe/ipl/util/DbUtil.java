package com.mmcoe.ipl.util;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DbUtil {
	
	private static final Logger logger = LogManager.getLogger(DbUtil.class);
	
    private static Connection con;
    static {
        try {
            Properties p = new Properties();
            InputStream is = DbUtil.class.getClassLoader()
                                         .getResourceAsStream("mysql.info");
            p.load(is);
            Class.forName(p.getProperty("driver"));
            con = DriverManager.getConnection(
                    p.getProperty("url"),
                    p.getProperty("user"),
                    p.getProperty("pass"));
            logger.info("Database connection established.");
        } catch (Exception e) {
        	logger.error("Failed to establish database connection.", e);
        }
    }
    public static Connection getConnection() {
        return con;
    }
}