package kaoqinxitong;

import java.io.*;
import java.util.ArrayList;

public class allInfooperate {   //文件读写程序
    public static  ArrayList<Member> allRead()throws Exception {    //将文件里全部内容读取到程序中
            ObjectInputStream aread = new ObjectInputStream(new FileInputStream("kaoqing.txt")); //以对象流打开并读取文件
            Member member=new Member();     //成员类接收文件内容对象
            ArrayList<Member> list=new ArrayList<Member>();
           while ((member=(Member) aread.readObject())!=null)   //遍历文件，将成员类存放到成员链表中，直到读到文件末尾
               list.add(member);    //添加成员类
            aread.close();  //关闭文件
        return list;    //返回读取完文件的成员链表
    }
        public static void allInfowrite(ArrayList<Member> list)throws Exception {   //将程序里所有数据覆写入文件中
            ObjectOutputStream awrite = new ObjectOutputStream(new FileOutputStream("kaoqing.txt"));    //以对象流打开并写入文件
            for (int i=0;i<list.size();i++) //循环遍历并写入成员链表的成员类
                awrite.writeObject(list.get(i));    //写入成员类对象
            awrite.writeObject(null);   //标记文件末尾
            awrite.close(); //关闭文件
        }
    }
