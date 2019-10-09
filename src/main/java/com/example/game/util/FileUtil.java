package com.example.game.util;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class FileUtil {
    private static final Logger LOG = LoggerFactory.getLogger(FileUtil.class);

    public static void write(String path, String content) {
        BufferedWriter out = null;
        try {
            out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(path, true)));
            out.write(content);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
            } catch (IOException e) {
                LOG.error(e.getMessage(), e);
            }
        }
    }

    public static void write(File file, String content) {
        BufferedWriter out = null;
        try {
            out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file, true)));
            out.write(content);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
            } catch (IOException e) {
                LOG.error(e.getMessage(), e);
            }
        }
    }

    public static int getLineNumber(String filePath) throws IOException {
        FileReader fileReader = null;
        LineNumberReader lineNumberReader = null;
        File file = new File(filePath);
        if (file.exists()) {
            try {
                fileReader = new FileReader(file);
                lineNumberReader = new LineNumberReader(
                        fileReader);
                lineNumberReader.skip(Long.MAX_VALUE);
                int lines = lineNumberReader.getLineNumber();

                return lines;
            } finally {
                if (fileReader != null) {
                    fileReader.close();
                }
                if (lineNumberReader != null) {
                    lineNumberReader.close();
                }
            }
        }
        return 0;
    }


    public static boolean uploadToPath(MultipartFile txtFile, String targetFilePath, String fileName){
        File targetFile = new File(targetFilePath);
        if (!targetFile.exists()) {
            targetFile.mkdirs();
        }

        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(targetFilePath + File.separator + fileName);
            IOUtils.copy(txtFile.getInputStream(), fileOutputStream);
        } catch (IOException e) {
            LOG.error("上传文件失败", e);
            return false;
        } finally {
            try {
                fileOutputStream.flush();
                fileOutputStream.close();
            } catch (IOException e) {
                LOG.error("上传文件失败", e);
            }
        }
        return true;
    }

    public static String generateSuffix() {
        // 获得当前时间
        DateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
        // 转换为字符串
        String formatDate = format.format(new Date());
        // 随机生成文件编号
        int random = new Random().nextInt(10000);
        return new StringBuffer().append(formatDate).append("_").append(
                random).toString();
    }
}
