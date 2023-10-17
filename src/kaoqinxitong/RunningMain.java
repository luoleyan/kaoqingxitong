package kaoqinxitong;

import java.util.ArrayList;
import java.util.Scanner;

public class RunningMain {  //主函数类
    public static  void main(String []args)throws Exception  {
        Menu menu=new Menu();
        char ch1;   //程序运行与终止标识符
        String ch2; //程序操作指令
        String a;   //程序运行与终止标识符
        ch1='y';    //程序运行与终止标识符初始化
        Scanner sc=new Scanner(System.in);
        while(ch1=='y'||ch1=='Y'){  //维持程序持续运行
            menu.ShowMenu();    //输出菜单
            ch2=sc.next();  //接收操作指令
            switch (ch2){
                case "1":   //考勤录入
                    System.out.println("请输入考勤信息->");
                    IdInput idInput=new IdInput();
                        idInput.ClassInput();
                    break;
                case "2":   //修改员工考勤情况
                    System.out.println("请输入需要修改的员工信息和修改操作（1为删除，2为修改）->");
                    System.out.println("请输入员工姓名或者工号->");
                    String name=sc.next();
                    System.out.println("请输入需要操作的考勤情况->");
                    String memqk=sc.next();
                    System.out.println("请输入修改操作->");
                    int op= sc.nextInt();
                    ModifyInformation mdf=new ModifyInformation();
                    if(op==1)
                    {
                        mdf.mdf(name, memqk,op,null);
                    }
                    else if (op==2){
                        System.out.println("请输入修改内容->");
                        String mdfcontent=sc.next();
                        mdf.mdf(name,memqk,op,mdfcontent);
                    }else {
                        throw new MyException("输入操作为非法操作！");
                    }
                    break;
                case "3":   //搜索员工考勤情况
                    System.out.println("请输入需要查询的员工姓名或工号->");
                    String nameorid=sc.next();
                    Search workerSearch=new Search();
                        ArrayList<Member>memberArrayList=workerSearch.ManberandWorknumberSearch(nameorid);
                        int []times=workerSearch.membertimes(memberArrayList);
                        System.out.println("姓名："+memberArrayList.get(1).getName()+"  工号："+memberArrayList.get(1).getWorkNumber()+"  部门："+memberArrayList.get(1).getDepartment()+"  职位："+memberArrayList.get(1).getPosts()+"  未缺勤:"+times[0]+"次"+" 迟到"+times[1]+"次"+"  早退："+times[2]+"次"+"  请假："+times[3]+" 次"+"  旷工"+times[4]+"次");
                    break;
                case "4":   //按员工搜索旷工情况
                    System.out.println("请输入起止星期号码->");
                    System.out.println("开始星期号码");
                    int sDate=sc.nextInt();
                    System.out.println("截至星期号码");
                    int eDate= sc.nextInt();
                    Search kgzySearch=new Search();
                        kgzySearch.dpkgSearch(sDate,eDate,2);
                    break;
                case "5":   //按部门搜索旷工情况
                    System.out.println("请输入起止星期号码->");
                    System.out.println("开始星期号码");
                    int sDatew=sc.nextInt();
                    System.out.println("截至星期号码");
                    int eDatew= sc.nextInt();
                    Search wkSearch=new Search();
                        wkSearch.dpkgSearch(sDatew,eDatew,1);
                    break;
                case "6":   //考勤情况输出
                    allInfooperate aprint=new allInfooperate();
                        ArrayList<Member> ardlist=aprint.allRead();
                        for(int i=0;i<ardlist.size();i++){
                            System.out.println("姓名："+ardlist.get(i).getName()+"  工号："+ardlist.get(i).getWorkNumber()+"  部门："+ardlist.get(i).getDepartment()+"  职位："+ardlist.get(i).getPosts()+"  考勤情况："+ardlist.get(i).getCircumstance()+"  星期"+ardlist.get(i).getDate());
                        }
                    break;
                case "0":   //退出考勤系统
                    ch1='n';
                    break;
                default:
                    System.out.println("输入指令有误，请输入0-6进行选择");
            }
            if (!ch2.equals("0")){
                System.out.println("按回车继续，按任意键返回主菜单");
                a=sc.next();
                if(a.length()==0){
                    ch1='n';
                }
            }
        }
    }
}
