package com.cc.blog.config;


import org.apache.logging.log4j.Logger;
import org.apache.logging.slf4j.SLF4JLogger;

/**
 * @author cc
 * @date 18-3-27 下午12:04
 */
public class log {
   private static Logger logger;

   public static void info (Object o){
       Throwable throwable = new Throwable();
       StackTraceElement[] ste = throwable.getStackTrace();
       logger.info("--->>>log类名: " + ste[1].getClassName() + "  方法名: " + ste[1].getMethodName() + "  行数: " + ste[1].getLineNumber()+"："+o);
   }


   public  static  void info2(Object o){
       logger.info(o);
   }
   public static  void  error (Object o){
       Throwable throwable = new Throwable();
       StackTraceElement[] ste = throwable.getStackTrace();
        logger.error("--->>>log类名: " + ste[1].getClassName() + "  方法名: " + ste[1].getMethodName() + "  行数: " + ste[1].getLineNumber()+"："+o);
   }
}
