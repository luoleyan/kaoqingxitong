package kaoqinxitong;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class IdInput  { //数据读写程序
    int chance=1;   //判断文件是否是首次读写的标识
    Scanner sc=new Scanner(System.in);
    public void ClassInput()throws Exception{   //储存输入数据，并传给成员变量，将成员类写入文件
        System.out.println("请输入姓名：");
        String name=sc.next();
        System.out.println("请输入工号：");
        String workNumber=sc.next();
        System.out.println("请输入所属部门：");
        String Department=sc.next();
        System.out.println("请输入职位：");
        String Posts=sc.next();
        System.out.println("请输入考勤情况：");
        String Circumstance=sc.next();
        System.out.println("请输入星期序号：");
        String date= sc.next();
        Member m = new Member(name,workNumber,Department,Posts,Circumstance,date);  //将输入数据传输给成员变量
        InputtoFile(m); //调用将数据写入文件方法，将新的数据写入文件
    }
    public void InputtoFile(Member member)throws Exception{ //将数据写入文件方法
        File file=new File("kaoqing.txt");  //打开考勤文件
        if(!file.exists()){ //判断考勤文件是否存在，若不存在则创建新的考勤文件并且标识为首次读写状态
            try {
                file.createNewFile();   //创建新的考勤文件
                chance=0;   //标识为首次读写
            }catch (IOException e){
                e.printStackTrace();
            }
        }
        if(chance==0){  //首次将数据写入文件
           ObjectOutputStream firstwrite=new ObjectOutputStream(new FileOutputStream("kaoqing.txt"));   //创建对象流打开并写入对象到文件
           firstwrite.writeObject(member);  //写入对象到文件
           firstwrite.writeObject(null);    //设计文件末尾标识
           chance=1;    //更改文件读写次数标识符，改为非首次读写
           firstwrite.close();  //关闭文件
        }
        else {  //非首次将数据写入文件
            allInfooperate unfirsttofile=new allInfooperate();  //创建文件读写类成员变量，变量为非首次写入文件
            ArrayList<Member> members=unfirsttofile.allRead();  //创建成员类链表接收文件内所有成员对象
            members.add(member);    //将新数据添加到成员链表末尾
            unfirsttofile.allInfowrite(members);    //将新的成员链表覆写入文件
        }
    }
}

