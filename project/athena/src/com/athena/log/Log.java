package com.athena.log;

import java.io.IOException;

import org.apache.log4j.DailyRollingFileAppender;
import org.apache.log4j.FileAppender;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;
import org.apache.log4j.SimpleLayout;

import com.athena.Initialization;

public final class Log {
	static Logger logger = Logger.getLogger(Initialization.class);        
    static PatternLayout layout = new PatternLayout();
    //HTMLLayout  layout = new HTMLLayout();
    static DailyRollingFileAppender appender = null;
    
    static {
    	try {
    		layout.setConversionPattern("%d %p - %m%n");
			appender = new DailyRollingFileAppender(layout,"d://athena/log.txt", "'.'yyyy-MM-dd");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	logger.addAppender(appender);//添加输出端
        logger.setLevel((Level)Level.DEBUG);//覆盖配置文件中的级别
    }
    
    public static void debug(String info){
    	logger.debug(info);
    }
    
    public static void info(String info){
    	logger.info(info);
    }
    
    public static void warn(String info){
    	logger.warn(info);
    }
    
    public static void error(String info){
    	logger.error(info);
    }
}
