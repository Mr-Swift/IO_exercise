package com.apple.developer.classtest;

import java.io.*;
import java.util.Scanner;

public class Person implements Serializable {
    private String name;
    private String password;

    public Person() {
    }

    public Person(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}

class Test{
    public static void main(String[] args) {
        Person p=new Person("jack","jack123");
        ObjectOutputStream oos=null;
        try {
            oos=new ObjectOutputStream(new FileOutputStream(new File("src/user.txt")));
            oos.writeObject(p);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(oos!=null){
                try {
                    oos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }


        File fileU=new File("src/remember.txt");
        if(!fileU.exists()){
            Scanner input=new Scanner(System.in);
            System.out.print("please input the name:");
            String name=input.next();
            System.out.print("please input the password:");
            String password= input.next();

            ObjectInputStream ois=null;
            try {
                ois=new ObjectInputStream(new FileInputStream(new File("src/user.txt")));
                Person person=(Person)ois.readObject();
                System.out.println(person.toString());
                if(name.equals(person.getName())&& password.equals(person.getPassword())){
                    System.out.println("Login successfully!");
                    System.out.print(" 是否记住密码？（y/n）");
                    String choice = input.next();

                    File rem=new File("src/remember.txt");
                    if(choice.equals("n")){
                        rem.delete();
                    }else{
                        ObjectOutputStream oos1=new ObjectOutputStream(new FileOutputStream(rem));
                        oos1.writeObject(person);
                        oos1.close();
                    }
                }
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
            finally {
                if(ois!=null){
                    try {
                        ois.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            }
        }else{
            try {
                Scanner in=new Scanner(System.in);
                ObjectInputStream ois2=new ObjectInputStream(new FileInputStream(fileU));
                Person person2=(Person)ois2.readObject();
                System.out.println("用户名："+person2.getName());
                System.out.println("密码：******");
                System.out.print("是否登录？（y/n）");
                String choice2=in.next();
                if(choice2.equals("y")){
                    System.out.println("Login in successfully!");
                }else{
                    System.exit(0);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

    }
}
