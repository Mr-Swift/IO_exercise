package com.apple.developer;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("all")
public class IOexercise {
    public static void main(String[] args) {
        File file = new File("src/IO流编程题.txt");
        Map<String, PhoneNumber> map = new HashMap();
        String num = null;
        String type = null;
        double time = 0;
        BufferedReader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader(new FileReader(file));
            String line = null;
            int lineNo = 0;
            while ((line = bufferedReader.readLine()) != null && lineNo < 15) {
                lineNo++;
                if (lineNo >= 3) {
                    String[] split = line.split("\\|");
                    num = split[0];
                    type = split[2];
                    time = Integer.valueOf(split[3]);
                    if (!map.containsKey(num)) {
                        map.put(num, new PhoneNumber(num, 0));
                    } else {
                        switch (type) {
                            case "1":
                                map.get(num).addMoney(1 * time);
                                break;
                            case "2":
                                map.get(num).addMoney(2 * time);
                                break;
                            case "3":
                                map.get(num).addMoney(10 * time);
                                break;
                            default:
                                break;
                        }
                    }
                }
            }
            for (Map.Entry entry : map.entrySet()) {
                System.out.println(entry.getValue());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        File fileOutput = new File("src/output.txt");
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter(fileOutput));
            for (Map.Entry entry : map.entrySet()) {
                writer.write(String.valueOf(entry.getValue()));
                writer.newLine();
            }
            System.out.println("计算结果已写入到output.txt文件");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        BufferedWriter writerContinue = null;
        try {
            writerContinue = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file, true)));
            writerContinue.newLine();
            writerContinue.newLine();
            writerContinue.newLine();
            writerContinue.write("话费清单如下：");
            writerContinue.newLine();
            for (Map.Entry entry : map.entrySet()) {
                writerContinue.write(String.valueOf(entry.getValue()));
                writerContinue.newLine();
            }
            System.out.println("计算结果已追加到IO流编程题.txt文件末尾......");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (writerContinue != null) {
                try {
                    writerContinue.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
