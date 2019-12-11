# Aop日志记录

### 1、添加pom
```xml
<!-- AOP支持 -->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-aop</artifactId>
</dependency>
<!-- 算法摘要工具类 -->
<dependency>
    <groupId>org.apache.directory.studio</groupId>
    <artifactId>org.apache.commons.codec</artifactId>
    <version>1.8</version>
</dependency>
```

### 2、添加core->config
1、添加注解类AnnotationLog
```java
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface AnnotationLog {

    String remark() default "";
}
```

2、添加切面日志类AspectLog
```java
@Aspect
@Component
public class AspectLog {
    private static final Logger logger = LoggerFactory.getLogger(AspectLog.class);

//    @Resource
//    private SystemLogService systemLogService;

    @Resource
    private SystemLogQueue systemLogQueue;

    /**
     * 定义切入点，凡事标注有此注解的方法就记录日志
     */
    @Pointcut("@annotation(top.xuxuzhaozhao.demo.core.aop.AnnotationLog)")
    public void methodCachePointcut(){
    }

    @Before("methodCachePointcut()")
    public void doBefore(JoinPoint p) throws Exception{
        SystemLog systemLog = getSystemLoginit(p);
        systemLog.setLogType(SystemLog.LOGINFO);
        systemLogQueue.add(systemLog);
    }

    /**
     * 调用后的异常处理
     * @param p
     * @param e
     */
    @AfterThrowing(pointcut = "methodCachePointcut()", throwing = "e")
    public void doAfterThrowing(JoinPoint p, Throwable e) throws Throwable {
        //业务异常不用记录
        if(!(e instanceof ServiceException)) {
            try {
                SystemLog systemLog =getSystemLoginit(p);
                systemLog.setLogType(SystemLog.LOGERROR);
                systemLog.setExceptionCode(e.getClass().getName());
                systemLog.setExceptionDetail(e.getMessage());
                systemLogQueue.add(systemLog);
            } catch (Exception ex) {
                logger.error("==异常通知异常==");
                logger.error("异常信息:{}", ex.getMessage());
            }
        }
    }

    private SystemLog getSystemLoginit(JoinPoint p) {
        SystemLog systemLog = new SystemLog();
        try{
            //类名
            String targetClass = p.getTarget().getClass().toString();
            //请求的方法名
            String tartgetMethod = p.getSignature().getName();
            //获取类名  UserController
            String classType = p.getTarget().getClass().getName();
            Class<?> clazz = Class.forName(classType);
            String clazzName = clazz.getName();
            //请求参数名+参数值的map
            Map<String, Object> nameAndArgs = getFieldsName(this.getClass(), clazzName, tartgetMethod, p.getArgs());
            systemLog.setId(ApplicationUtils.getUUID());
            systemLog.setDescription(getMethodRemark(p));
            systemLog.setMethod(targetClass+"."+tartgetMethod);
            //大家可自行百度获取ip的方法
            systemLog.setRequestIp("192.168.1.104");
            systemLog.setParams(JSON.toJSONString(nameAndArgs));
            systemLog.setUserId(getUserId());
            systemLog.setCreateTime(new Date());
        }catch (Exception ex){
            logger.error("==异常通知异常==");
            logger.error("异常信息:{}", ex.getMessage());
        }
        return systemLog;
    }

    /**
     * 通过反射机制 获取被切参数名以及参数值
     *
     * @param cls
     * @param clazzName
     * @param methodName
     * @param args
     * @return
     * @throws NotFoundException
     */
    private Map<String, Object> getFieldsName(Class cls, String clazzName, String methodName, Object[] args) throws  NotFoundException {
        Map<String, Object> map = new HashMap<>();
        ClassPool pool = ClassPool.getDefault();
        ClassClassPath classPath = new ClassClassPath(cls);
        pool.insertClassPath(classPath);
        CtClass cc = pool.get(clazzName);
        CtMethod cm = cc.getDeclaredMethod(methodName);
        MethodInfo methodInfo = cm.getMethodInfo();
        CodeAttribute codeAttribute = methodInfo.getCodeAttribute();
        LocalVariableAttribute attr = (LocalVariableAttribute) codeAttribute.getAttribute(LocalVariableAttribute.tag);
        int pos = Modifier.isStatic(cm.getModifiers()) ? 0 : 1;
        for (int i = 0; i < cm.getParameterTypes().length; i++) {
            //HttpServletRequest 和HttpServletResponse 不做处理
            if(!(args[i] instanceof HttpServletRequest || args[i] instanceof HttpServletResponse)){
                //paramNames即参数名
                map.put(attr.variableName(i + pos), JSON.toJSONString(args[i]));
            }
        }
        return map;
    }

    /**
     * 获取方法的中文备注____用于记录用户的操作日志描述
     * @param joinPoint
     * @return
     * @throws Exception
     */
    private static String getMethodRemark(JoinPoint joinPoint) throws Exception {
        String targetName = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        Object[] arguments = joinPoint.getArgs();
        Class targetClass = Class.forName(targetName);
        Method[] method = targetClass.getMethods();
        String methode = "";
        for (Method m : method) {
            if (m.getName().equals(methodName)) {
                Class[] tmpCs = m.getParameterTypes();
                if (tmpCs.length == arguments.length) {
                    AnnotationLog methodCache = m.getAnnotation(AnnotationLog.class);
                    if (methodCache != null) {
                        methode = methodCache.remark();
                    }
                    break;
                }
            }
        }
        return methode;
    }

    private static String getUserId() {
        String userId = "";
        User userInfo = (User) SecurityUtils.getSubject().getPrincipal();
        if(userInfo != null){
            userId = userInfo.getId();
        }
        return userId;
    }
}
```

### 3、添加core->systemlog->SystemLogQueue及core->systemlog->SystemLogConsumer

### 4、添加mapper支持批量导入
```xml
<insert id="insertByBatch" parameterType="java.util.List">
    insert into system_log(
        id,description,method,log_type,request_ip,exception_code,
        exception_detail,params,user_id,create_time
    )values
    <foreach collection="list" item="item" index="index" separator=",">
      (
        #{item.id,jdbcType=VARCHAR},
        #{item.description,jdbcType=VARCHAR},
        #{item.method,jdbcType=VARCHAR},
        #{item.logType,jdbcType=VARCHAR},
        #{item.requestIp,jdbcType=VARCHAR},
        #{item.exceptionCode,jdbcType=VARCHAR},
        #{item.exceptionDetail,jdbcType=VARCHAR},
        #{item.params,jdbcType=VARCHAR},
        #{item.userId,jdbcType=VARCHAR},
        #{item.createTime,jdbcType=TIMESTAMP}
      )
    </foreach>
</insert>
```

### 注意事项：
1、由于使用了多数据源配置，在将mapper移到db1文件中的时候，记得修改mapper.xml中namespace否则：`异常信息:Invalid bound statement (not found): top.xuxuzhaozhao.demo.dao.db1.SystemLogMapper.insertByBatch`