package kaoqinxitong;

import java.util.ArrayList;

public class ModifyInformation {    //修改文件内职员考勤信息类
    Search search=new Search(); //创建搜索类变量
    public void mdf(String mdfId,String goalcontent,int goal,String mdfcontent){    //职工考勤信息修改方法，需要接收职工姓名或工号，修改或删除对象，修改或删除指令，修改内容
    allInfooperate mdfoperate=new allInfooperate();    //创建文件读写类变量为修改文件指令
        try {
            ArrayList<Member>mdflist=mdfoperate.allRead();  //将文件所有内容传输到成员链表
            int local=search.arrSearch(mdflist,mdfId,goalcontent);  //调用搜索类的职员名字或工号搜索方法，将成员链表，成员名字或工号，目标考勤情况传输过去，返回搜索结果
            if(local==-1)   //为搜索到目标成员
                System.out.println("员工考勤信息不存在！");   //丢出搜索失败提示
            else {  //搜索到目标职员
                if (goal == 1) {    //删除目标职员考勤信息
                    mdflist.remove(local);
                    allInfooperate.allInfowrite(mdflist);
                }
                if (goal == 2) {    //修改目标职员考勤信息
                    Member m = mdflist.get(local);  //接收目标职员考勤数据
                    m.setCircumstance(mdfcontent);  //修改目标职员考勤信息
                    mdflist.set(local, m);  //将目标职员信息覆写入链表
                    allInfooperate.allInfowrite(mdflist);   //将修改好的成员链表重新覆写入文件
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);

        }

    }
}
