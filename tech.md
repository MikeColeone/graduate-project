- 为什么不使用原来的application.properties,而是再次创建yml文件
  - 原因是前者是逐行的配置 后者是嵌套式配置更好理解 简洁易于维护和管理 更加简洁
- Raw use of parameterized class 'ArrayList' 
  - 添加泛型作为限制提高安全性
  - ```java
            ArrayList<ArrayList<Object>>list =new ArrayList<>();
```
- Call to 'printStackTrace()' should probably be replaced with more robust loggin
  - 换成 log.error("exception message", e); 
