package com.apple.developer.classtest;


import java.io.*;

class CopyTest{
    public static void main(String[] args) {
        File fileIn = new File("src/output.txt");
        File fileOut = new File("src/output3.txt");
        new IOUtil().copy(fileIn,fileOut);
    }
}
public class IOUtil{
    public void copy(File src,File dec){
        InputStream in=null;
        OutputStream out=null;
        try {
            in=new FileInputStream(src);
            out=new FileOutputStream(dec);
//            int n=0;
//            while (-1!=(n=in.read())){
//                out.write(n);
//            }
            byte[] buffer=new byte[10];
            int length=0;
            while (-1!=(length=in.read(buffer))){
                out.write(buffer,0,length);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(in!=null){
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(out!=null){
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
