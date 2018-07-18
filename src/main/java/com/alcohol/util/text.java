package com.alcohol.util;

import java.io.*;
import java.io.FileOutputStream;
import java.io.FileInputStream;
public class text {
    public static void main(String[] args) {
        File file = new File("F:/壁纸/6c7f6f4833ecec9f.jpg");
        try {
            InputStream fis = new FileInputStream(file);//图片所在位置
            OutputStream fos = new FileOutputStream("F:/photo.jpg");//想要把图片copy到某处的位置
            byte []buf = new byte[(int) file.length()];//避免空间浪费
            int len = 0;
            while((len = fis.read(buf))!= -1){
                fos.write(buf, 0, len);
            }
            fis.close();
            fos.close();
            System.out.print("复制成功");
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
