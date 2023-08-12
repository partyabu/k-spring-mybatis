# k-spring-mybatis
Spring与Mybatis整合


# Mybatis-Spring

1. 要和 Spring 一起使用 MyBatis，需要在 Spring 应用上下文中定义至少两样东西：

   - 一个 `SqlSessionFactory` 
   - 另一个是数据映射器类（即用于 JDBC 的 `DataSource`）

## 1.配置数据源


```xml
<bean id="sqlSessionFactory" class="org.mybatis.spring.SqlSessionFactoryBean">
    <!--指定数据源-->
    <property name="dataSource" ref="dataSource"/>
</bean>

<context:property-placeholder location="classpath:jdbc.properties"/>
	<!--配置数据源-->
<bean id="dataSource" class="com.alibaba.druid.pool.DruidDataSource">
    <property name="url" value="${jdbc.url}"/>
    <property name="driverClassName" value="${jdbc.driver}"/>
    <property name="username" value="${jdbc.username}"/>
    <property name="password" value="${jdbc.password}"/>
</bean>
```



## 2.发现映射器接口

- 使用 元素`<mybatis:scan/>`
- 使用 注解`@MapperScan`

> `base-package` 属性允许你设置映射器接口文件的基础包。通过使用逗号或分号分隔，你可以设置多个包。并且会在你所指定的包中递归搜索映射器。

```xml
<mybatis:scan base-package="com.abucloud.mapper"/>
```

或

```java
@Configuration
@MapperScan("com.abucloud.mapper")
public class AppConfig {
}
```

```markdown
`<context:component-scan/>` 无法发现并注册映射器。映射器的本质是接口，为了将它们注册到 Spring容器中，而使用功能@MapperScan能够帮助发现器找到每一个接口。
```



## 3.配置映射文件位置（其实可以省略的）

1. ==映射器接口 xxxMapper 在**相同的类路径下**有对应的 MyBatis XML 映射器配置文件，将会被自动解析。不需要在 MyBatis 配置文件中显式配置映射器。==**

2. 如果映射器配置文件与接口类不在同一个类路径下，映射配置文件不能自动被解析，但有两种解决办法：

   - 第一种是手动在 MyBatis 的全局配置文件中的 `<mappers>` 部分中指定 XML 文件的类路径；

   - 第二种是设置工厂 bean 的 `mapperLocations` 属性。

     > `mapperLocations` 属性接受多个资源位置。这个属性可以用来指定 MyBatis 的映射器 XML 配置文件的位置。属性的值是一个 ==**Ant 风格**==的字符串，可以指定加载一个目录中的所有文件，或者从一个目录开始递归搜索所有目录。比如:
     >
     > ```xml
     > <bean id="sqlSessionFactory" class="org.mybatis.spring.SqlSessionFactoryBean">
     > <property name="dataSource" ref="dataSource" />
     > <property name="mapperLocations" value="classpath*:sample/config/mappers/**/*.xml" />
     > </bean>
     > ```
     >
     > 这会从类路径下加载所有在 `sample.config.mappers` 包和它的子包中的 MyBatis 映射器 XML 配置文件。

***



## spring-mybatis所有配置

### 1. applicationContext.xml配置

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xmlns:mybatis="http://mybatis.org/schema/mybatis-spring"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
    http://www.springframework.org/schema/beans/spring-beans-4.3.xsd
    http://www.springframework.org/schema/context
    https://www.springframework.org/schema/context/spring-context.xsd
    http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd
    http://mybatis.org/schema/mybatis-spring http://mybatis.org/schema/mybatis-spring.xsd">

    <!--指定扫描的包到ioc容器中-->
    <context:component-scan base-package="com.abucloud"/>

    <!--配置sqlSessionFactory，可以用来创建sqlSession-->
    <bean id="sqlSessionFactory" class="org.mybatis.spring.SqlSessionFactoryBean">
        <!--指定数据源-->
        <property name="dataSource" ref="dataSource"/>
        <!--配置映射配置文件-->
        <property name="mapperLocations" value="classpath*:mapper/*.xml"/>

        <!--其他配置-->
        <property name="configuration">
            <bean class="org.apache.ibatis.session.Configuration">
                <property name="mapUnderscoreToCamelCase" value="true"/>
                <!--日志输出到控制台-->
                <property name="logImpl" value="org.apache.ibatis.logging.stdout.StdOutImpl"/>
            </bean>
        </property>

        <!--起别名-->
        <property name="typeAliasesPackage" value="com/abucloud/entity"/>
    </bean>

    <!---->
    <mybatis:scan base-package="com.abucloud.mapper"/>

    <context:property-placeholder location="classpath:jdbc.properties"/>
    <bean id="dataSource" class="com.alibaba.druid.pool.DruidDataSource">
        <property name="url" value="${jdbc.url}"/>
        <property name="driverClassName" value="${jdbc.driver}"/>
        <property name="username" value="${jdbc.username}"/>
        <property name="password" value="${jdbc.password}"/>
    </bean>

</beans>
```

### 2.jdbc.properties配置

```properties
jdbc.driver=com.mysql.cj.jdbc.Driver
jdbc.url=jdbc:mysql://localhost:3306/wopin-pro-demo?useUnicode=true&characterEncoding=UTF-8&serverTimeZone=Asia/shanghai
jdbc.username=root
jdbc.password=kang123***
```
