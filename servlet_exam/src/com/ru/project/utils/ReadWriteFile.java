package com.ru.project.utils;

import org.apache.log4j.Logger;

import java.io.*;

/**
 * Created by 成如 on 13-12-12.
 */
public class ReadWriteFile {
    static Logger log = Logger.getLogger(ReadWriteFile.class);
    /**
     * 读取文件第一行信息
     * @return
     */
    public static String ReadFile(String filePath){
        BufferedReader reader = null;
        String str = null;
        try {
            reader = new BufferedReader(new InputStreamReader(new FileInputStream(filePath)));
            while ((str =reader.readLine()) != null){
                return str;
            }
        }catch (Exception e){
            log.error("读取文件信息失败",e);
        }finally {
            if (reader != null){
                try {
                    reader.close();
                } catch (IOException e) {
                    log.error("读取文件信息---关闭资源失败", e);
                }
            } 
        }

        return str;
    }

    /**
     *向文件写信息
     * @param msg
     * @param filePath
     * @return
     */
    public static boolean writeFile(String msg, String filePath){
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(filePath);
            fileWriter.write(msg);
            return true;
        } catch (IOException e) {
            log.error("向文件写信息失败",e);
        }finally {
            if (fileWriter != null){
                try {
                    fileWriter.close();
                } catch (IOException e) {
                    log.error("向文件写信息 --- 关闭资源失败",e);
                }
            }
        }
        return false;
    }

}
