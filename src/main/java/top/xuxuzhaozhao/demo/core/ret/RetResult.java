package top.xuxuzhaozhao.demo.core.ret;

public class RetResult {

    private int code;

    private String msg;

    private Object data;

    public static RetResult build(){
        return new RetResult();
    }

    public int getCode() {
        return code;
    }
    public RetResult setCode(RetCode retCode) {
        this.code = retCode.code;
        return this;
    }

    RetResult setCode(int retCode) {
        this.code = retCode;
        return this;
    }

    public String getMsg() {
        return msg;
    }

    public RetResult setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    public Object getData() {
        return data;
    }

    public RetResult setData(Object data) {
        this.data = data;
        return this;
    }

}