package com.joe.commons.app;

// +----------------------------------------------------------------------
// | Created by IntelliJ IDEA.
// +----------------------------------------------------------------------
// | Date: 2019/11/16
// +----------------------------------------------------------------------
// | Author: Joe
// +----------------------------------------------------------------------
// | Description: 
// +----------------------------------------------------------------------

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class CommonFunctions {

    /**
     * 通用的编号生成方法
     *
     * @param prefix 编号前缀
     * @return 返回编号(前缀 + yyyymmddhhmmss + 1位随机大写字母 + 5位随机数字)
     */
    public static String generateNo(String prefix) {
        // 获取日期时间字符串
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        String dateTimeStr = simpleDateFormat.format((new Date()).getTime());

        // 生成一位大写字母
        char randomLetter = (char) (Math.random() * 26 + 'A');
        Random random = new Random();

        // 生成物伪随机数
        int randomNum = (int) (random.nextDouble() * (99999 - 10000 + 1)) + 10000;// 获取5位随机数

        // 返回结果
        return prefix + dateTimeStr + randomLetter + randomNum;
    }

    /**
     * 生成加密后的密码
     *
     * @param password 明文密码
     * @param maskNo   掩码
     * @return 返回加密结果
     */
    public static String encryptPassword(String password, String maskNo) {
        // 拼接生成 rawPassword
        String rawPassword = password + maskNo;

        // 返回 MD5 加密的结果
        return DigestUtils.md5DigestAsHex(rawPassword.getBytes());
    }

    /**
     * 生成随机文件名
     *
     * @param ext 后缀名
     * @return 返回文件名
     */
    public static String getRandomFileName(String ext) {
        SimpleDateFormat simpleDateFormat;
        simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        Date date = new Date();
        String str = simpleDateFormat.format(date);
        Random random = new Random();
        int ranNum = (int) (random.nextDouble() * (99999 - 10000 + 1)) + 10000;// 获取5位随机数
        return ranNum + str + ext;// 当前时间
    }


    /**
     * 下载文件
     *
     * @param filePath 文件路径
     * @param fileName 下载后显示的文件名
     * @throws Exception 异常
     */
    public static ResponseEntity<byte[]> downloadFile(String filePath, String fileName) throws Exception {
        HttpHeaders headers = new HttpHeaders();
        File file = new File(filePath);

        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentDispositionFormData("attachment", new String(fileName.getBytes(StandardCharsets.UTF_8), "ISO8859-1"));

        return new ResponseEntity<>(FileUtils.readFileToByteArray(file), headers, HttpStatus.CREATED);
    }
}
