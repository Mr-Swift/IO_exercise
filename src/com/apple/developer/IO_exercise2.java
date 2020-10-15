package com.apple.developer;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("all")
public class IO_exercise2 {
    public static void main(String[] args) {
        File file = new File("src/IO流编程题.txt");
        Map<String, Double> map = new HashMap();
        String num="";
        String type="";
        double time=0;
        int lineNo=0;//记录读取的行号
        String line=null;
        int read=0;
        BufferedReader bufferedReader=null;
        try {
            bufferedReader = new BufferedReader(new FileReader(file));
            for(int i=0;i<2;i++){//跳过最前面的两行
                lineNo++;
                line=bufferedReader.readLine();
            }
            while (lineNo<15){
                num="";
                type="";
                lineNo++;
                while ((read= bufferedReader.read())!=124){//符号'|'的ASCII值为124，查ASCII码表可得
                    num=num+String.valueOf((char)read);
                }
                bufferedReader.skip(12);
//                while ((read= bufferedReader.read())!=124){
//
//                }
                while ((read= bufferedReader.read())!=124){
                    type=type+String.valueOf((char)read);
                }
                String str="";
                while ((read= bufferedReader.read())!=(int)'\n'){
                    str=str+String.valueOf((char)read);
                }
                double d=Double.parseDouble(str);
                time=d;
                if(!map.containsKey(num)){
                    map.put(num, 0.0);
                }else{
                    switch (type){
                        case "1":
                            map.put(num,map.get(num)+1*time);
                            break;
                        case "2":
                            map.put(num,map.get(num)+2*time);
                            break;
                        case "3":
                            map.put(num,map.get(num)+10*time);
                            break;
                        default:
                            break;
                    }
                }
            }
            for(Map.Entry entry:map.entrySet()){
                System.out.println("号码："+entry.getKey()+"--->"+"消费金额："+entry.getValue());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(bufferedReader!=null){
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        File fileOutput=new File("src/output2.txt");
        BufferedWriter writer=null;
        try {
            writer=new BufferedWriter(new FileWriter(fileOutput));
            for(Map.Entry entry:map.entrySet()){
                writer.write(String.valueOf("号码："+entry.getKey()+"--->"+"消费金额："+entry.getValue()));
                writer.newLine();
            }
            System.out.println("计算结果已写入到output2.txt文件");
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(writer!=null){
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }


        BufferedWriter writerContinue=null;
        try {
            writerContinue=new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file,true)));
            writerContinue.newLine();
            writerContinue.newLine();
            writerContinue.newLine();
            writerContinue.write("话费清单如下：");
            writerContinue.newLine();
            for(Map.Entry entry:map.entrySet()){
                writerContinue.write(String.valueOf("号码："+entry.getKey()+"--->"+"消费金额："+entry.getValue()));
                writerContinue.newLine();
            }
            System.out.println("计算结果已追加到IO流编程题.txt文件末尾......");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(writerContinue!=null){
                try {
                    writerContinue.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
