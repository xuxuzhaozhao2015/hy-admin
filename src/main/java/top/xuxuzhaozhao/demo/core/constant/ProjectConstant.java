package top.xuxuzhaozhao.demo.core.constant;

public class ProjectConstant {
    // 项目基础包名称
    public static final String BASE_PACKAGE = "top.xuxuzhaozhao.demo";

    // Model所在包
    public static final String MODEL_PACKAGE = BASE_PACKAGE + ".domain";

    // Mapper所在包
    public static final String MAPPER_PACKAGE = BASE_PACKAGE + ".dao";

    // Service所在包
    public static final String SERVICE_PACKAGE = BASE_PACKAGE + ".service";

    // ServiceImpl所在包
    public static final String SERVICE_IMPL_PACKAGE = SERVICE_PACKAGE + ".impl";

    // Controller所在包
    public static final String CONTROLLER_PACKAGE = BASE_PACKAGE + ".controller";

    // Mapper插件基础接口的完全限定名
    public static final String MAPPER_INTERFACE_REFERENCE = BASE_PACKAGE + ".core.universal.Mapper";

    // 文件上传根目录
    public static final String SAVE_FILE_PATH = "F://文件上传//img";
}
