package com.apple.developer;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("all")
public class IOexercise {
    public static void main(String[] args) {
        File file = new File("src/IO流编程题.txt");
        Map<String, PhoneNumber> map = new HashMap();
        String num=null;
        String type=null;
        double time=0;
        BufferedReader bufferedReader=null;
        try {
            bufferedReader = new BufferedReader(new FileReader(file));
            String line=null;
            int lineNo=0;
            while ((line= bufferedReader.readLine())!=null && lineNo<15){
                lineNo++;
                if(lineNo>=3){
                    String[] split=line.split("\\|");
                    num=split[0];
                    type=split[2];
                    time=Integer.valueOf(split[3]);
                    if(!map.containsKey(num)){
                        map.put(num,new PhoneNumber(num,0));
                    }else {
                        switch (type){
                            case "1":
                                map.get(num).addMoney(1*time);
                                break;
                            case "2":
                                map.get(num).addMoney(2*time);
                                break;
                            case "3":
                                map.get(num).addMoney(10*time);
                                break;
                            default:
                                break;
                        }
                    }
                }
            }
            for(Map.Entry entry: map.entrySet()){
                System.out.println(entry.getValue());
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
    }
}
