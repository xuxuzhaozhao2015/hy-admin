#自动生成service、serviceimpl、controller
### 1、添加pom
```xml
<!-- 模版引擎 -->
<dependency>
    <groupId>org.freemarker</groupId>
    <artifactId>freemarker</artifactId>
    <version>2.3.29</version>
</dependency>
```
### 2、创建模版
在【test->java->resources->template】添加三个模版

### 3、修改CodeGenerator
