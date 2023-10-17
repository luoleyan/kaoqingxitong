package kaoqinxitong;


import java.util.*;

public class Search {   //搜索类
    public int arrSearch(ArrayList<Member> list,String name,String content){    //按工号或姓名，考勤情况搜索目标职员位置方法
        for (int m=0;m<list.size();m++){
            if(list.get(m).getWorkNumber().equals(name)||list.get(m).getName().equals(name)&&list.get(m).getCircumstance().equals(content)) //按工号或姓名，考勤情况搜索目标职员位置
                return m;   //搜索成功，返回目标职员位置
        }
        return -1;  //搜索失败
    }
    public ArrayList<Member> ManberandWorknumberSearch(String nameorid) throws Exception {  //按职员姓名，工号搜索职员考勤信息
        allInfooperate allread=new allInfooperate();    //调用文件读写类
        ArrayList<Member> alllist=allread.allRead();    //调用文件读写类的读取文件方法，存入成员链表
        ArrayList<Member>members=new ArrayList<Member>();   //创建新的成员链表作为搜索目标职员信息盒子
        for (int i=0;i<alllist.size();i++){ //遍历读取文件成员链表并进行搜索
            if(alllist.get(i).getWorkNumber().equals(nameorid)||alllist.get(i).getName().equals(nameorid)){ //匹配读取文件成员链表的姓名和工号的成员变量
                members.add(alllist.get(i));    //将匹配成功的数据添加到目标职员信息盒子
            }
        }
        return members; //返回目标职员信息盒子
    }
    public int[] membertimes(ArrayList<Member> list) {  //统计职员考勤情况及其不同情况的次数方法
        int []frequency=new int[5];
        Arrays.fill(frequency,0);   //初始化统计数组
        for(int i=0;i<list.size();i++){
            if(list.get(i).getCircumstance().equals("未缺勤"))
                frequency[0]++;
            if(list.get(i).getCircumstance().equals("迟到"))
                frequency[1]++;
            if (list.get(i).getCircumstance().equals("早退"))
                frequency[2]++;
            if (list.get(i).getCircumstance().equals("请假"))
                frequency[3]++;
            if (list.get(i).getCircumstance().equals("旷工"))
                frequency[4]++;
        }
        return frequency;   //返回目标职员考勤情况统计次数

    }
    public void dpkgSearch(int sDate,int eDate,int flag) throws Exception { //再一段时间内搜索不同职员或部门的旷工情况方法
        ArrayList<Member> list=allInfooperate.allRead();    //调用文件读写类的文件读取方法
        HashMap<String,Integer>hashMap=new HashMap<String,Integer>();   //创建哈希表，key为部门或职员姓名，value为其旷工次数
        for(int m=0;m<list.size();m++) {    //遍历读取文件成员链表
            if (list.get(m).getCircumstance().equals("旷工")) {   //匹配成员旷工词条
                if (flag == 1) {    //按部门统计
                    if (hashMap.containsKey(list.get(m).getDepartment()) && Integer.parseInt(list.get(m).getDate()) >= sDate && Integer.parseInt(list.get(m).getDate()) <= eDate) { //检测该部门是否旷工情况是否再统计时间段内，是否是首次出现
                        hashMap.put(list.get(m).getDepartment(), hashMap.get(list.get(m).getDepartment()) + 1); //非首次出现，次数累加
                    } else {    //该部门首次出现
                        if( Integer.parseInt(list.get(m).getDate()) >= sDate && Integer.parseInt(list.get(m).getDate()) <= eDate)   //检测该部门是否旷工情况是否再统计时间段内
                            hashMap.put(list.get(m).getDepartment(), 1);    //将首次出现的部门写入哈希表，次数累加到1
                    }
                }
                if (flag == 2) {    //按职员姓名统计
                    if (hashMap.containsKey(list.get(m).getName()) && Integer.parseInt(list.get(m).getDate()) >= sDate && Integer.parseInt(list.get(m).getDate()) <= eDate) {   //检测该职员是否旷工情况是否再统计时间段内，是否是首次出现
                        hashMap.put(list.get(m).getName(), hashMap.get(list.get(m).getName()) + 1); //非首次出现，次数累加
                    } else {    //该职员为首次出现
                        if( Integer.parseInt(list.get(m).getDate()) >= sDate && Integer.parseInt(list.get(m).getDate()) <= eDate)   //检测该职员是否旷工情况是否再统计时间段内
                         hashMap.put(list.get(m).getName(), 1); //将首次出现的职员写入哈希表，次数累加到1
                    }
                }
            }
        }

                Frequencysorting(hashMap,flag); //调用旷工情况排序方法
    }
    public void Frequencysorting(HashMap <String,Integer>hashMap,int flag){ //旷工情况排序方法
      List<Map.Entry<String,Integer>> listDesc=new ArrayList<>(hashMap.entrySet()); //创建链表包裹哈希表
      listDesc.sort((e1,e2)->e2.getValue()-e1.getValue());  //重写链表排序方法
            if(flag==1) {   //按部门输出
                System.out.println("旷工频率从高到低的部门分别是：");
                    listDesc.forEach(e->System.out.println("部门："+e.getKey()+"次数："+e.getValue()));
            }
            if(flag==2){    //按职员输出
                System.out.println("旷工频率从高到低的员工分别是：");

                    listDesc.forEach(e->System.out.println("员工姓名："+e.getKey()+"次数："+e.getValue()));
            }
            hashMap.clear();    //清空哈希表
    }
}
