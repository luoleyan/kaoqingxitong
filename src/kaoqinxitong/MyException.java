package kaoqinxitong;

public class MyException extends RuntimeException { //自定义异常
    public MyException() {

    }

    public MyException(String s) {
        super(s);
    }

}
