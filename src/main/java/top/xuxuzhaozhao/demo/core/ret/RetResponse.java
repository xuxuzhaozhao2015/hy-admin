package top.xuxuzhaozhao.demo.core.ret;

public class RetResponse {

    private final static String SUCCESS = "success";

    public static Object makeOKRsp() {
        return new RetResult().setCode(RetCode.SUCCESS).setMsg(SUCCESS);
    }

    public static Object makeOKRsp(Object data) {
        return new RetResult().setCode(RetCode.SUCCESS).setMsg(SUCCESS).setData(data);
    }

    public static Object makeErrRsp(String message) {
        return new RetResult().setCode(RetCode.FAIL).setMsg(SUCCESS);
    }

    public static Object makeRsp(int code, String msg) {
        return new RetResult().setCode(code).setMsg(msg);
    }

    public static Object makeRsp(int code, String msg, Object data) {
        return new RetResult().setCode(code).setMsg(msg).setData(data);
    }
}
