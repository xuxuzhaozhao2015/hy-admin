package top.xuxuzhaozhao.demo.core.ret;

public enum RetCode {
    SUCCESS(200),

    FAIL(400),

    UNAUTHORIZED(401),

    NOTFOUND(404),

    INTERNAL_SERVER_ERROR(500);

    public int code;
    RetCode(int code) {
        this.code = code;
    }
}
