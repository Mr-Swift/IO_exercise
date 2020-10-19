package com.apple.developer.classtest;

import java.io.*;

public class Pet {
    private String name;
    private String type;
    private String master;

    public Pet() {
    }

    public Pet(String name, String type, String master) {
        this.name = name;
        this.type = type;
        this.master = master;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMaster() {
        return master;
    }

    public void setMaster(String master) {
        this.master = master;
    }
}

class PetTest{
    public static void main(String[] args) {
        Pet p=new Pet("QQ","企鹅","jack");
        File in=new File("src/pet.template");
        File out=new File("src/petout.txt");

        BufferedReader br=null;
        BufferedWriter bw=null;
        BufferedWriter bw1=null;
        try {
            br=new BufferedReader(new FileReader(new File("src/pet.template")));
            StringBuffer sb=new StringBuffer();
            String read=null;
            while((read= br.readLine())!=null){
                sb.append(read+"\n");
            }
            String str = sb.toString();
            str=str.replaceAll("\\{name}",p.getName()).replaceAll("\\{type}",p.getType()).replaceAll("\\{master}",p.getMaster());
            bw = new BufferedWriter(new FileWriter(new File("src/pet.txt")));
            bw1 = new BufferedWriter(new FileWriter(new File("src/pet.template")));
            bw.write(str);
            bw1.write(str);
            bw1.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(br!=null){
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if(bw!=null){
                try {
                    bw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
