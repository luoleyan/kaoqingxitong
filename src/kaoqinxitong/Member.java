package kaoqinxitong;

public class Member implements java.io.Serializable {   //成员类
    private static final long serialVersionUID = 1L;
    private String Name;    //职工姓名
    private String WorkNumber;      //职员工号
    private String Department;  //职员所属部门
    private String Posts;   //职员的职务
    private String Circumstance;    //职员考勤情况
    private String Date;    //职员考勤日期
    public Member(){    //JavaBean方法，协助外部类传输数据

    }
    public Member(String name, String worknumber, String department, String posts, String circumstance, String date){
        this.Name=name;
        this.WorkNumber=worknumber;
        this.Department=department;
        this.Posts=posts;
        this.Circumstance=circumstance;
        this.Date=date;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getWorkNumber() {
        return WorkNumber;
    }

    public void setWorkNumber(String workNumber) {
        WorkNumber = workNumber;
    }

    public String getDepartment() {
        return Department;
    }

    public void setDepartment(String department) {
        Department = department;
    }

    public String getPosts() {
        return Posts;
    }

    public void setPosts(String posts) {
        Posts = posts;
    }

    public String getCircumstance() {
        return Circumstance;
    }

    public void setCircumstance(String circumstance) {
        Circumstance = circumstance;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }
}
