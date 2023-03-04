# SpringBoot

## 一、简介

### 背景分析

在传统**JAVAEE**应用体系中创建一个项目,需要手动添加大量的依赖,还要考虑版本的兼容性,还有繁重的配置、负载的项目部署,会高度影响开发效率,即使是使用Spring进行资源整合,也存在同样的这些问题。还有就是现在的软件生态应用也已经形成一定的规模,整个软件架构体系在变化，企业对技术的要求也在变化，现在的企业更注重技术的开箱即用，更注重技术在生态圈中的深度融合，更注重轻量级的运维。由此由此spring boot诞生。

### 解决问题

Spring Boot是一个全新的Java软件开发框架（很多人现在把它理解为一个脚手架），其设计目的是用来简化Spring项目的初始搭建以及开发过程,并为后面的Spring Cloud 微服务实践提供更加便利条件。该框架使用了特定的注解方式来进行配置，从而使开发人员不再需要大量的xml配置。不再需要大量的手动依赖管理。Spring Boot基于快速构建理念，通过约定大于配置，开箱即用的方式，希望能够在蓬勃发展的快速应用开发领域成为其领导者。

### 核心特性

SpringBoot 框架诞生后，之所以能得到软件开发行业的高度认可，自然离不开它提供给我们的一些关键特性，例如：

1. 起步依赖(Starter Dependency)-创建项目时，会默认添加基础依赖，简化我们自己查找依赖的过程。
2. 自动配置(Auto Configuration)-创建项目时，springboot工程添加的默认依赖中提供了很多默认的配置，简化了我们对资源的配置过程。
3. 健康检查(Actator)-监控-springboot工程运行时，我们可以打开actuator特性，基于此特性监控spring中的bean，连接池，jvm内存等
4. 嵌入式服务(Tomcat,Jetty)-springboot工程支持内嵌的web服务，可以将tomcat或jetty这样的服务直接嵌套到web依赖中，简化部署过程

## 二、项目如何启动

### 关键注解@SpringBootApplication

SpringBoot 工程中由@SpringBootApplication注解描述的类为然后启动运行，其启动过程如下：

![img](https://img-blog.csdnimg.cn/c5dddf6218344a8982ce29b512e10a8e.png)

### SpringBoot启动，底层执行的几个关键步骤

1. 加载Spring Boot配置：首先，Spring Boot会加载应用程序中的配置文件，包括application.properties/application.yml等。
2. 自动配置Spring应用程序：Spring Boot会自动配置Spring应用程序，并根据应用程序的类路径、jar包等信息，加载相应的配置文件。这些配置文件可以是Spring Boot的默认配置，也可以是用户自定义的配置。
3. 启动Spring应用程序：Spring Boot会启动Spring应用程序，并创建ApplicationContext容器。在容器中，Spring Boot会自动配置各种Bean，包括Controller、Service、Repository等。
4. 启动嵌入式Web服务器：Spring Boot默认集成了Tomcat、Jetty等嵌入式Web服务器，它会自动配置并启动这些服务器。如果需要，开发者也可以将应用程序打包为WAR包，部署到外部Web服务器中。
5. 注册Spring Boot内置的管理端点：Spring Boot内置了许多管理端点，用于监控和管理应用程序。例如，/actuator/health端点可以用于检查应用程序是否健康。这些端点可以通过配置文件进行开启和关闭。

## 三、SpringBoot之自定义starter

### 1.什么是SpringBoot starter机制

> SpringBoot中的starter是一种非常重要的机制(自动化配置)，能够抛弃以前繁杂的配置，将其统一集成进starter，应用者只需要在maven中引入starter依赖，SpringBoot就能自动扫描到要加载的信息并启动相应的默认配置。
>
> starter让我们摆脱了各种依赖库的处理，需要配置各种信息的困扰。SpringBoot会自动通过classpath路径下的类发现需要的Bean，并注册进IOC容器。SpringBoot提供了针对日常企业应用研发各种场景的spring-boot-starter依赖模块。
>
> 所有这些依赖模块都遵循着约定成俗的默认配置，并允许我们调整这些配置，即遵循“约定大于配置”的理念。

### 2.为什么要自定义starter

> 在我们的日常开发工作中，经常会有一些独立于业务之外的配置模块，我们经常将其放到一个特定的包下，然后如果另一个工程需要复用这块功能的时候，需要将代码硬拷贝到另一个工程，重新集成一遍，麻烦至极。
>
> 如果我们将这些可独立于业务代码之外的功配置模块封装成一个个starter，复用的时候只需要将其在pom中引用依赖即可，SpringBoot为我们完成自动装配，简直不要太爽

### 3.什么时候需要创建自定义starter

> 在我们的日常开发工作中，可能会需要开发一个通用模块，以供其它工程复用。SpringBoot就为我们提供这样的功能机制，我们可以把我们的通用模块封装成一个个starter，这样其它工程复用的时候只需要在pom中引用依赖即可，由SpringBoot为我们完成自动装配。
>
> **常见场景：**
>
> 1. 通用模块-短信发送模块
> 2. 基于AOP技术实现日志切面
> 3. 分布式雪花ID，Long-->string，解决精度问题
>       jackson2/fastjson
> 4. 微服务项目的数据库连接池配置
> 5. 微服务项目的每个模块都要访问redis数据库，每个模块都要配置redisTemplate
>       也可以通过starter解决

## 四、什么是OkHttp

OkHttp是一个开源的HTTP客户端库，它是由Square公司开发的，用于Android和Java应用程序。它支持HTTP/2协议、WebSocket和HTTP请求响应缓存。OkHttp封装了底层的网络通信和请求处理，提供了简单易用的API，可以帮助开发人员更轻松地进行网络通信操作。

OkHttp的特点包括：

1. 支持HTTP/2协议：OkHttp支持HTTP/2协议，可以更快地完成网络请求，提高应用程序的性能。
2. 简单易用的API：OkHttp提供了简单易用的API，可以方便地进行网络请求操作。
3. 自动重试机制：OkHttp自动支持请求失败时的重试机制，可以提高请求成功率。
4. 请求拦截器和响应拦截器：OkHttp支持请求拦截器和响应拦截器，可以方便地对请求和响应进行处理。
5. 支持WebSocket：OkHttp支持WebSocket协议，可以实现实时通信功能。

***SpringBoot + OkHttp 可以实现第三方登录***

## 五、springboot整合Redis 

### Redis简介

Redis（Remote Dictionary Server）是一个开源的内存数据存储系统，也被称为数据结构服务器。它支持多种数据结构，包括**字符串、哈希表、列表、集合、有序集**合等，并提供了丰富的操作命令，例如添加、删除、查找、排序等。Redis的数据存储在内存中，因此读写速度非常快，同时也支持数据的持久化到磁盘上，以保证数据不会因为宕机等原因丢失。

除了基本的数据存储功能，Redis还提供了一些高级特性，例如：

1. 发布/订阅机制：Redis支持发布/订阅机制，可以让多个客户端同时订阅某个频道，当有消息发布到这个频道时，所有订阅了该频道的客户端都可以收到消息。
2. Lua脚本支持：Redis支持执行Lua脚本，可以在服务端执行复杂的业务逻辑。
3. 事务支持：Redis支持事务，可以在多个操作之间保持原子性，保证数据的一致性。
4. 集群支持：Redis支持集群部署，可以横向扩展以应对高并发的访问量。

Redis是NoSQL技术体现之一

### NoSQL技术

NoSQL（Not Only SQL）是一种非关系型的数据库技术，与传统的关系型数据库不同，NoSQL数据库没有固定的表结构，也不依赖于 SQL 语言进行数据操作，而是采用键值对、文档、列族、图等不同的数据模型来存储和查询数据。

NoSQL数据库的优点包括：

1. 可扩展性：NoSQL数据库可以很容易地进行水平扩展，以适应大规模数据的存储和处理需求。
2. 高性能：NoSQL数据库通常采用内存存储和异步写入等技术，能够实现高速的读写操作。
3. 灵活性：NoSQL数据库不需要事先设计严格的表结构，能够灵活地存储各种类型的数据，也支持半结构化和非结构化数据。
4. 高可用性：NoSQL数据库通常采用分布式架构，能够在节点故障或网络故障的情况下仍然保持数据可用。
5. 低成本：由于NoSQL数据库通常是开源的，并且采用了廉价的硬件架构，因此相对于传统关系型数据库来说，成本较低。

常见的NoSQL数据库包括：

1. Key-Value型：例如Redis、Memcached等。
2. 文档型：例如MongoDB、Couchbase等。
3. 列族型：例如HBase、Cassandra等。
4. 图型：例如Neo4j、OrientDB等。

NoSQL数据库的选择应根据具体的场景和需求来考虑，需要权衡不同的特点和优缺点。

### SpringDataRedis

- 在学习之前，我们先了解了解SpringDataRedis。它是Spring中数据操作的模块，包含对各种数据库的集成，其中对Redis的集成模块就叫做SpringDataRedis。
- 它提供了RedisTemplate统一API来操作Redis、支持Redis的`发布订阅模型`、支持Redis`哨兵和Redis集群`、支持基于`JDK`、`JSON`、`字符串`、Spring`对象的数据序列化及反序列化`等等，功能非常的多。

### 入门实例

#### 1.引入依赖

```xml
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-data-redis</artifactId>
        </dependency>
        <dependency>
            <groupId>org.apache.commons</groupId>
            <artifactId>commons-pool2</artifactId> 
        </dependency>
```

#### 2.配置文件

```yml
spring:
  redis:
    # Redis服务器地址
    host: 19.1.5.11
    # Redis服务器端口号
    port: 6379
    # 使用的数据库索引，默认是0
    database: 0
    # 连接超时时间
    timeout: 1800000
     # 设置密码
    password: "123456"
    lettuce:
      pool:
        # 最大阻塞等待时间，负数表示没有限制
        max-wait: -1
        # 连接池中的最大空闲连接
        max-idle: 5
        # 连接池中的最小空闲连接
        min-idle: 0
        # 连接池中最大连接数，负数表示没有限制
        max-active: 20
```

#### 3.代码实例

```java
    @Test
    void testOne() {
        redisTemplate.opsForValue().set("name","卷心菜");
        String name = (String) redisTemplate.opsForValue().get("name");
        System.out.println(name); //卷心菜
    }
```

#### 4.自定义序列化方式

1. JSON序列化器

   ```java
   @Configuration
   public class RedisConfig {
       @Bean
       public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory)
               throws UnknownHostException {
           // 创建模板
           RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
           // 设置连接工厂
           redisTemplate.setConnectionFactory(redisConnectionFactory);
           // 设置序列化工具
           GenericJackson2JsonRedisSerializer jsonRedisSerializer =
                   new GenericJackson2JsonRedisSerializer();
           // key和 hashKey采用 string序列化
           redisTemplate.setKeySerializer(RedisSerializer.string());
           redisTemplate.setHashKeySerializer(RedisSerializer.string());
           // value和 hashValue采用 JSON序列化
           redisTemplate.setValueSerializer(jsonRedisSerializer);
           redisTemplate.setHashValueSerializer(jsonRedisSerializer);
           return redisTemplate;
       }
   }
   ```

   ```java
       void testTwo() {
           redisTemplate.opsForValue().set("person", new Person("卷心菜",21));
       }
   
   ```

   ![在这里插入图片描述](https://img-blog.csdnimg.cn/9a546dadc4a94f4a98812b9e90aea016.png)

2. String序列化器

   为了节省内存空间，我们并不会使用JSON序列化器来处理value，而是统一使用String序列化器，要求只能存储String类型的key和value。`当需要存储Java对象时，手动完成对象的序列化和反序列化`。

   ```java
       @Autowired
       private StringRedisTemplate redisTemplate;
       // JSON工具
       private static final ObjectMapper mapper = new ObjectMapper();
   
       @Test
       void testOne() {
           redisTemplate.opsForValue().set("name", "卷心菜");
       }
       @Test
       void testTwo() throws IOException {
           Person person = new Person("我是一棵卷心菜", 21);
           //  手动序列化
           String json = mapper.writeValueAsString(person);
           redisTemplate.opsForValue().set("person", json);
           String personJson = redisTemplate.opsForValue().get("person");
           // 反序列化
           Person person1 = mapper.readValue(personJson, Person.class);
           System.out.println(person1);
       }
   ```

## 六、springboot启动类原理解析

![img](https://upload-images.jianshu.io/upload_images/19619362-c0e930e2956f4c60.png?imageMogr2/auto-orient/strip|imageView2/2/w/1200/format/webp)

在Spring Boot应用程序的启动过程中，启动类会完成以下几个主要步骤：

1. 加载Spring Boot的核心配置类：Spring Boot会自动扫描classpath下的配置类，并根据其中的注解自动配置应用程序的各种特性。启动类需要加载这些配置类，并构建一个Spring上下文。
2. 创建Spring上下文：启动类会使用Spring的ApplicationContext接口来创建一个Spring上下文。Spring上下文是Spring应用程序的核心，它包含了所有Spring Bean的定义和实例。启动类通过加载配置类来构建Spring上下文，并在上下文中注册Bean定义。
3. 配置Spring Boot的自动配置：启动类会启用Spring Boot的自动配置特性，并根据应用程序所需的功能自动配置各种组件。自动配置是Spring Boot的核心特性之一，它可以大大简化应用程序的开发和部署过程。
4. 注册Spring MVC的DispatcherServlet：如果应用程序中包含了Web组件，启动类会注册Spring MVC的DispatcherServlet，该Servlet会负责处理HTTP请求和响应。
5. 启动应用程序：启动类最后会启动应用程序，让应用程序开始接受HTTP请求，并处理业务逻辑。

## 七、ApplicationContextAware理解

ApplicationContextAware是Spring框架中的一个接口，用于让Bean对象获得ApplicationContext实例。当Bean对象实现了ApplicationContextAware接口并注入到Spring容器中时，Spring容器会自动将ApplicationContext实例传递给该Bean对象，以便在Bean对象中使用ApplicationContext。

具体来说，当Bean对象实现了ApplicationContextAware接口并注入到Spring容器中时，Spring容器会调用该Bean对象的setApplicationContext()方法，并将ApplicationContext实例作为参数传递进去。该方法可以用于在Bean对象中获取ApplicationContext实例，并进行各种操作，例如获取其他Bean对象、获取应用程序上下文信息等。

#### 例子

```java
public class MyBean implements ApplicationContextAware {
 
    private ApplicationContext context;
 
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.context = applicationContext;
    }
 
    public void doSomething() {
        // 使用ApplicationContext实例进行操作
        MyOtherBean otherBean = context.getBean(MyOtherBean.class);
        // ...
    }
}
```

## 八、springboot配置多数据源

创建一个Spring配置类，定义两个DataSource用来读取`application.properties`中的不同配置。如下例子中，主数据源配置为`spring.datasource.primary`开头的配置，第二数据源配置为`spring.datasource.secondary`开头的配置。

```java
@Configuration
public class DataSourceConfig {

    @Bean(name = "primaryDataSource")
    @Qualifier("primaryDataSource")
    @ConfigurationProperties(prefix="spring.datasource.primary")
    public DataSource primaryDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "secondaryDataSource")
    @Qualifier("secondaryDataSource")
    @Primary
    @ConfigurationProperties(prefix="spring.datasource.secondary")
    public DataSource secondaryDataSource() {
        return DataSourceBuilder.create().build();
    }

}
```

application.properties

```properties
spring.datasource.primary.url=jdbc:mysql://localhost:3306/test1
spring.datasource.primary.username=root
spring.datasource.primary.password=root
spring.datasource.primary.driver-class-name=com.mysql.jdbc.Driver

spring.datasource.secondary.url=jdbc:mysql://localhost:3306/test2
spring.datasource.secondary.username=root
spring.datasource.secondary.password=root
spring.datasource.secondary.driver-class-name=com.mysql.jdbc.Driver
```

### JdbcTemplate支持

对JdbcTemplate的支持比较简单，只需要为其注入对应的datasource即可，如下例子，在创建JdbcTemplate的时候分别注入名为`primaryDataSource`和`secondaryDataSource`的数据源来区分不同的JdbcTemplate。

```java

    @Bean(name = "primaryJdbcTemplate")
    public JdbcTemplate primaryJdbcTemplate(
            @Qualifier("primaryDataSource") DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

    @Bean(name = "secondaryJdbcTemplate")
    public JdbcTemplate secondaryJdbcTemplate(
            @Qualifier("secondaryDataSource") DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }
```

接下来通过测试用例来演示如何使用这两个针对不同数据源的JdbcTemplate。

```java

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(Application.class)
public class ApplicationTests {

    @Autowired
    @Qualifier("primaryJdbcTemplate")
    protected JdbcTemplate jdbcTemplate1;

    @Autowired
    @Qualifier("secondaryJdbcTemplate")
    protected JdbcTemplate jdbcTemplate2;

    @Before
    public void setUp() {
        jdbcTemplate1.update("DELETE  FROM  USER ");
        jdbcTemplate2.update("DELETE  FROM  USER ");
    }

    @Test
    public void test() throws Exception {

        // 往第一个数据源中插入两条数据
        jdbcTemplate1.update("insert into user(id,name,age) values(?, ?, ?)", 1, "aaa", 20);
        jdbcTemplate1.update("insert into user(id,name,age) values(?, ?, ?)", 2, "bbb", 30);

        // 往第二个数据源中插入一条数据，若插入的是第一个数据源，则会主键冲突报错
        jdbcTemplate2.update("insert into user(id,name,age) values(?, ?, ?)", 1, "aaa", 20);

        // 查一下第一个数据源中是否有两条数据，验证插入是否成功
        Assert.assertEquals("2", jdbcTemplate1.queryForObject("select count(1) from user", String.class));

        // 查一下第一个数据源中是否有两条数据，验证插入是否成功
        Assert.assertEquals("1", jdbcTemplate2.queryForObject("select count(1) from user", String.class));

    }
}
```

### Spring-data-jpa支持

对于数据源的配置可以沿用上例中`DataSourceConfig`的实现。

新增对第一数据源的JPA配置，注意两处注释的地方，用于指定数据源对应的`Entity`实体和`Repository`定义位置，用`@Primary`区分主数据源。

```java
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef="entityManagerFactoryPrimary",
        transactionManagerRef="transactionManagerPrimary",
        basePackages= { "com.didispace.domain.p" }) //设置Repository所在位置
public class PrimaryConfig {

    @Autowired @Qualifier("primaryDataSource")
    private DataSource primaryDataSource;

    @Primary
    @Bean(name = "entityManagerPrimary")
    public EntityManager entityManager(EntityManagerFactoryBuilder builder) {
        return entityManagerFactoryPrimary(builder).getObject().createEntityManager();
    }

    @Primary
    @Bean(name = "entityManagerFactoryPrimary")
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryPrimary (EntityManagerFactoryBuilder builder) {
        return builder
                .dataSource(primaryDataSource)
                .properties(getVendorProperties(primaryDataSource))
                .packages("com.didispace.domain.p") //设置实体类所在位置
                .persistenceUnit("primaryPersistenceUnit")
                .build();
    }

    @Autowired
    private JpaProperties jpaProperties;

    private Map<String, String> getVendorProperties(DataSource dataSource) {
        return jpaProperties.getHibernateProperties(dataSource);
    }

    @Primary
    @Bean(name = "transactionManagerPrimary")
    public PlatformTransactionManager transactionManagerPrimary(EntityManagerFactoryBuilder builder) {
        return new JpaTransactionManager(entityManagerFactoryPrimary(builder).getObject());
    }

}
```

新增对第二数据源的JPA配置，内容与第一数据源类似，具体如下：

```java
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef="entityManagerFactorySecondary",
        transactionManagerRef="transactionManagerSecondary",
        basePackages= { "com.didispace.domain.s" }) //设置Repository所在位置
public class SecondaryConfig {

    @Autowired @Qualifier("secondaryDataSource")
    private DataSource secondaryDataSource;

    @Bean(name = "entityManagerSecondary")
    public EntityManager entityManager(EntityManagerFactoryBuilder builder) {
        return entityManagerFactorySecondary(builder).getObject().createEntityManager();
    }

    @Bean(name = "entityManagerFactorySecondary")
    public LocalContainerEntityManagerFactoryBean entityManagerFactorySecondary (EntityManagerFactoryBuilder builder) {
        return builder
                .dataSource(secondaryDataSource)
                .properties(getVendorProperties(secondaryDataSource))
                .packages("com.didispace.domain.s") //设置实体类所在位置
                .persistenceUnit("secondaryPersistenceUnit")
                .build();
    }

    @Autowired
    private JpaProperties jpaProperties;

    private Map<String, String> getVendorProperties(DataSource dataSource) {
        return jpaProperties.getHibernateProperties(dataSource);
    }

    @Bean(name = "transactionManagerSecondary")
    PlatformTransactionManager transactionManagerSecondary(EntityManagerFactoryBuilder builder) {
        return new JpaTransactionManager(entityManagerFactorySecondary(builder).getObject());
    }

}
```

*其他跟单体数据源一样*

## 九、mybatis逆向工程 

MyBatis官方为我们提供了一个逆向工程，通过这个逆向工程，只要建立好数据表，那么MyBatis就会根据这个表自动生成pojo类、dao接口、sql映射文件。

> 1. 加入Mybatis逆向工程的依赖
> 2. 设计数据表
> 3. 配置逆向工程配置信息
> 4. 运行逆向工程
> 5. 得到【pojo类、dao接口、sql映射文件】

### 优缺点

- 优点：帮助我们自动生成Java代码，大大加快了我们的开发效率。
- 缺点：生成的文件太过冗余，不必要的代码过多。尤其是sql映射文件，里面的配置内容太多，对于dao层，提供的方法比较有限，需要自行扩展。

![img](https://img-blog.csdnimg.cn/0e04558ce5e44990b40294f23ddf4dac.png)

## 十、SpringBoot解决跨域问题

### 1.为什么会出现跨域问题

跨域问题是由浏览器的同源策略导致的。同源策略是一种安全机制，它要求浏览器只能在当前网页的域名下发送请求，而不能访问其它域名下的资源。同源策略的目的是保护用户的隐私和安全，防止恶意网站获取用户的敏感信息。

同源指的是协议、域名和端口号都相同。

### 2.什么是跨域

当一个请求url的 协议、域名、端口 三者之间任意一个与当前页面url不同即为跨域

| 当前页面URL               | 被请求页面URL                   | 是否跨域 | 原因                           |
| ------------------------- | ------------------------------- | -------- | ------------------------------ |
| http://www.test.com/      | http://www.test.com/index.html  | 否       | 同源（协议、域名、端口号相同） |
| http://www.test.com/      | https://www.test.com/index.html | 跨域     | 协议不同（http/https）         |
| http://www.test.com/      | http://www.baidu.com/           | 跨域     | 主域名不同（test/baidu）       |
| http://www.test.com/      | http://blog.test.com/           | 跨域     | 子域名不同（www/blog）         |
| http://www.test.com:8080/ | http://www.test.com:7001/       | 跨域     | 端口号不同（8080/7001）        |

### 3.非同源限制

浏览器为了安全性，限制了一些请求无法访问非同源URL

- 无法读取非同源网页的 Cookie、LocalStorage 和 IndexedDB
- 无法接触非同源网页的 DOM
- 无法向非同源地址发送 AJAX 请求

### SpringBoot解决跨域问题的三种方法

1. 返回新的CorsFilter（全局跨域）

   ```java
   import lombok.extern.slf4j.Slf4j;
   import org.springframework.context.annotation.Bean;
   import org.springframework.context.annotation.Configuration;
   import org.springframework.web.cors.CorsConfiguration;
   import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
   import org.springframework.web.filter.CorsFilter;
   import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
   
   /**
     * 跨域前后端配置类
     * 赵毅梵
     * 2021/9/8
     **/
   @Configuration
   @Slf4j
   public class CorsConfig {
     @Bean
     public CorsFilter corsFilter() {
       // 1.添加CORS配置信息
       CorsConfiguration config = new CorsConfiguration();
       // 放行哪些原始域
       config.addAllowedOrigin("*");
       // 是否发送Cookie信息
       config.setAllowCredentials(true);
       // 放行哪些原始域(请求方式)
       config.addAllowedMethod("*");
       // 放行哪些原始域(头部信息)
       config.addAllowedHeader("*");
       // 暴露哪些头部信息（因为跨域访问默认不能获取全部头部信息）
       // config.addExposedHeader("*");
       config.addExposedHeader("Content-Type");
       config.addExposedHeader( "X-Requested-With");
       config.addExposedHeader("accept");
       config.addExposedHeader("Origin");
       config.addExposedHeader( "Access-Control-Request-Method");
       config.addExposedHeader("Access-Control-Request-Headers");
       // 2.添加映射路径
       UrlBasedCorsConfigurationSource configSource = new UrlBasedCorsConfigurationSource();
       configSource.registerCorsConfiguration("/**", config);
       // 3.返回新的CorsFilter.
       return new CorsFilter(configSource);
     }
   }
   ```

   如果使用的是**高版本SpringBoot2.4.4**则需要改动一下

   把**config.addAllowedOrigin("\*");**替换成**config.addAllowedOriginPattern("*");**

2. 重写WebMvcConfigurer（全局跨域）

   ```java
   import org.springframework.boot.SpringBootConfiguration;
   import org.springframework.web.servlet.config.annotation.CorsRegistry;
   import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
   
   /**
     * 跨域前后端配置类
     * 赵毅梵
     * 2021/9/8
     **/
   @SpringBootConfiguration
   public class CorsConfig implements WebMvcConfigurer {
     @Override
     public void addCorsMappings(CorsRegistry registry) {
       //添加映射路径
       registry.addMapping("/**")
       //是否发送Cookie
       .allowCredentials(true)
       //设置放行哪些原始域   SpringBoot2.4.4下低版本使用.allowedOrigins("*")    
       .allowedOriginPatterns("*")
       //放行哪些请求方式
       .allowedMethods(new String[]{"GET", "POST", "PUT", "DELETE"})
       //.allowedMethods("*") //或者放行全部
       //放行哪些原始请求头部信息
       .allowedHeaders("*")
       //暴露哪些原始请求头部信息
       .exposedHeaders("*");
     }
   }
   ```

3. 使用注解@CrossOrigin（局部跨域）

   - 在控制器（类上）使用@CrossOrigin注解，表示该类的所有方法允许跨域

     ```java
     @Controller
     @RequestMapping("/shop")
     @CrossOrigin(originPatterns = "*", methods = {RequestMethod.GET, RequestMethod.POST})
     public class ShopController {
       @GetMapping("/")
       @ResponseBody
       public Map<String, Object> findAll() {
         //返回数据
         return DataSchool.getStudents();
       }
     }
     ```

   - 我们也可以设置更小的粒度，在方法上设置跨域

     ```java
     @Controller
     @RequestMapping("/shop")
     public class ShopController {
       @GetMapping("/")
       @ResponseBody
       //更小的解决跨域 设置只能某些地址访问
       @CrossOrigin(originPatterns = "http://localhost:8080")
       public Map<String, Object> findAll() {
         //返回数据
         return DataSchool.getStudents();
       }
     }
     ```

## 十一、springboot整合sentinel

### 1.Sentinel是什么

Sentinel是一个开源的分布式系统的流量防卫兵，由阿里巴巴开发并维护，提供了流量控制、熔断降级、系统负载保护等功能。它可以帮助我们解决分布式系统中的流量控制、服务保护和资源限流等问题，从而提高系统的稳定性、可用性和可靠性。

Sentinel的特点包括：

- 支持实时的流量控制和熔断降级，能够在高并发、大流量的场景下保护系统。
- 提供了丰富的监控和统计功能，能够实时监控系统的状态和性能指标。
- 支持多种流量控制规则，可以根据不同的业务需求进行配置。
- 支持多种数据源，可以将规则和监控数据存储在本地文件、MySQL、Nacos等不同的数据源中。

Sentinel可以与Spring Cloud、Dubbo、gRPC等各种框架进行集成，使用起来非常灵活和方便。它是一个轻量级的框架，对系统性能影响较小，同时还具有非常高的稳定性和可靠性，被广泛应用于各种大规模分布式系统中。

### 2.初始化

用到的依赖及配置

```xml
<!-- Sentinel核心服务 -->
<dependency>
    <groupId>com.alibaba.csp</groupId>
    <artifactId>sentinel-core</artifactId>
    <version>1.8.6</version>
</dependency>
<!-- Sentinel核心服务 -->
<!-- Sentinel本地应用接入控制台 -->
<dependency>
    <groupId>com.alibaba.csp</groupId>
    <artifactId>sentinel-transport-simple-http</artifactId>
    <version>1.8.6</version>
</dependency>
<!-- Sentinel本地应用接入控制台 -->
<!-- Sentinel提供注解无侵入定义资源 -->
<dependency>
    <groupId>com.alibaba.csp</groupId>
    <artifactId>sentinel-annotation-aspectj</artifactId>
    <version>1.8.6</version>
</dependency>
<!-- Sentinel提供注解无侵入定义资源 -->
```

配置

```yml
spring:
  application:
    name: sentinel
```

**初始版**

```xml
<!-- 版本与控制台保持一致即可 -->
<dependency>
    <groupId>com.alibaba.csp</groupId>
    <artifactId>sentinel-core</artifactId>
    <version>1.8.6</version>
</dependency>
<!-- 版本与控制台保持一致即可 -->
```

```java

@RestController
@RequestMapping("/user")
public class UserController {

    @GetMapping("/add")
    public String create(){
        try {
            // 设置一个资源名称为 Hello
            Entry ignored = SphU.entry("AddUser");
            System.out.println("新建一个用户");
            return "新建一个用户";
        } catch (BlockException e) {
            System.out.println("系统繁忙，请稍后");
            e.printStackTrace();
            return "系统繁忙，请稍后";
        }
    }

    /**
     * 使用代码编写流控规则，项目中不推荐使用，这是硬编码方式
     *
     * 注解 @PostConstruct 的含义是：本类构造方法执行结束后执行
     */
    @PostConstruct
    public void initFlowRule() {
        /* 1.创建存放限流规则的集合 */
        List<FlowRule> rules = new ArrayList<>();
        /* 2.创建限流规则 */
        FlowRule rule = new FlowRule();
        /* 定义资源，表示 Sentinel 会对哪个资源生效 */
        rule.setResource("AddUser");
        /* 定义限流的类型(此处使用 QPS 作为限流类型) */
        rule.setGrade(RuleConstant.FLOW_GRADE_QPS);
        /* 定义 QPS 每秒通过的请求数 */
        rule.setCount(2);
        /* 3.将限流规则存放到集合中 */
        rules.add(rule);
        /* 4.加载限流规则 */
        FlowRuleManager.loadRules(rules);
    }
    @GetMapping("/edit")
    public String edit(){
        return "编辑一个用户";
    }
}
```

**搭建控制台**

下载 Sentinel 控制台 jar 包：https://github.com/alibaba/Sentinel/releases

启动 Sentinel 控制台，如下图所示

```shell
java -Dserver.port=9000 -jar sentinel-dashboard-1.8.6.jar
```

### 3.整合使用

```xml
<!-- Sentinel本地应用接入控制台，版本与控制台保持一致即可 -->
<dependency>
    <groupId>com.alibaba.csp</groupId>
    <artifactId>sentinel-transport-simple-http</artifactId>
    <version>1.8.6</version>
</dependency>
<!-- Sentinel本地应用接入控制台，版本与控制台保持一致即可 -->

```

idea启动参数

```properties
-Dcsp.sentinel.dashboard.server=127.0.0.1:9000   Sentinel控制台的地址和端口号
-Dproject.name=sentinel	  本地应用在控制台中的名称
```

![在这里插入图片描述](https://img-blog.csdnimg.cn/ea4fa894709b45a7b58b9c88e4b7aa80.png)

![在这里插入图片描述](https://img-blog.csdnimg.cn/b93953ec406b492494606b071fded962.png)

删除使用代码编写的流控规则，项目中`不推荐使用`，这是硬编码方式

```java
@RestController
@RequestMapping("/user")
public class UserController {

    @GetMapping("/add")
    public String create(){
        try {
            // 设置一个资源名称为 Hello
            Entry ignored = SphU.entry("AddUser");
            System.out.println("新建一个用户");
            return "新建一个用户";
        } catch (BlockException e) {
            System.out.println("系统繁忙，请稍后");
            e.printStackTrace();
            return "系统繁忙，请稍后";
        }
    }
    
    @GetMapping("/edit")
    public String edit(){
        return "编辑一个用户";
    }
}
```

### 4.注解方式无侵入定义资源（推荐使用）

`Sentinel `支持通过 `@SentinelResource` 注解来定义资源，并配置 `blockHandler` 函数来进行限流之后的处理

```xml
<dependency>
	<groupId>com.alibaba.csp</groupId>
	<artifactId>sentinel-annotation-aspectj</artifactId>
	<version>1.8.0</version>
</dependency>

```



```java
@Configuration
public class SentinelAspectConfiguration {

    @Bean
    public SentinelResourceAspect sentinelResourceAspect(){
        return new SentinelResourceAspect();
    }
}
```

```java
@RestController
@RequestMapping("/user")
public class UserController {

    // value：资源名称 blockHandler：设置限流或降级处理的类
    @SentinelResource(value = "AddUser", blockHandler = "exceptionHandler")
    @GetMapping("/add")
    public String create(){
        return "新增一个用户";
    }

    // 限流处理类
    public String exceptionHandler(@NotNull BlockException e) {
        e.printStackTrace();
        return "系统繁忙，请稍后再试";
    }
}


```

## 十二、SpringBoot常用注解

1. @SpringBootApplication：替代 @SpringBootConfiguration、@EnableAutoConfiguration、@ComponentScan

2. @ImportAutoConfiguration：导入配置类，一般做测试的时候使用，正常优先使用@EnableAutoConfiguration 

3. @SpringBootConfiguration：替代@Configuration

4. @ImportResource：将资源导入容器

5. @PropertySource：导入properties文件

6. PropertySources：@PropertySource 的集合

7. @Role：bean角色定义为ROLE_APPLICATION(默认值)、ROLE_SUPPORT(辅助角色)、ROLE_INFRASTRUCTURE(后台角色，用户无感)

8. @Scope：指定bean的作用域，默认singleton，其它包括prototype、request、session、globalSession

9. @Lazy：使bean懒加载，取消bean预初始化。

10. @Primary：自动装配时当出现多个Bean候选者时，被注解为@Primary的Bean将作为首选者，否者将抛出异常。

11. @Profile：指定Bean在哪个环境下被激活

12. @DependsOn：依赖的bean注册完成，才注册当前类，依赖bean不存在会报错。用于控制bean加载顺序

13. @PostConstruct：bean的属性都注入完毕后，执行注解标注的方式进行初始化工作

14. @Autowired：默认按类型装配，如果我们想使用按名称装配，可以结合@Qualifier注解一起使用。

15. @Lookup：根据方法返回的类型，去容器中捞出对应

16. @Qualifier：申明bean名字，且可以按bean名字加载bean

17. @Required：检查bean的属性setXXX()方法，要求属性砸死配置阶段必须已配置

18. @Description：添加bean的文字描述

19. @EnableAspectConfiguration：启动AspectJ自动配置

20. EnableLoadTimeWeaving：启动类加载器动态增强功能，使用instrumentation实现

21. @AutoConfigurationPackage：包含该注解的package会被AutoConfigurationPackages注册

22. @AutoConfigureBefore：在指定配置类初始化前加载

23. @AutoConfigureAfter：在指定配置类初始化后加载

24. @AutoConfigureOrder：指定配置类初始化顺序，越小初始化越早

25. @ModelAttribute：

    - 对方法使用 @ModelAttribute 注解：

      注解在方法上的`@ModelAttribute`说明了方法的作用是用于添加一个或多个属性到[model](https://so.csdn.net/so/search?q=model&spm=1001.2101.3001.7020)上。这样的方法能接受与`@RequestMapping`注解相同的参数类型，只不过不能直接被映射到具体的请求上。

      ```java
      @Controller
      public class ModelAttributeController {
       
      	@ModelAttribute
      	public void init(Model model) {
      		System.out.println("@RequestMapping方法");
      	}
       
      	@RequestMapping("/model-attribute")
      	public String get() {
      		System.out.println("@ModelAttribute方法");
       
      		return "model-attribute";
      	}
       
      }
      ```

      可以使用 @ModelAttribute 标注的方法来设置其他 @ReqeustMapping 方法的公用参数 

      使用 @ModelAttribute("key") 来显示指定属性名。

    - @ModelAttribute 和 @RequestMapping 注解在同一个方法上

      如果 @ModelAttribute 和 @RequestMapping 注解在同一个方法上，那么代表给这个请求单独设置 Model 参数。此时返回的值是 Model 的参数值，而不是跳转的地址。跳转的地址是根据请求的 url 自动转换而来的。比如下面的例子中跳转页面不是 HelloWorld.jsp 而是 model-attribute.jsp。并且参数只有在 model-attribute.jsp 中能够取得，而 demo.jsp 中不能取得。

    - 在方法参数上使用 @ModelAttribute 注解

      - 数据绑定

      - 和 BindingResult 配合使用

        使用 @ModelAttribute 进行数据绑定之后，可以使用 BindingResult 来返回数据验证结果。数据验证可以使用 hibernate validation 的 @Valid 标签或者 spring Validator 校验机制的 @Validated 配合 BindingResult 使用。 或者自定义校验器来返回 BindingResult 对象来进行校验。你可以通过Spring的 <errors> 表单标签来在同一个表单上显示错误信息。

        @Valid

        ```java
        @RequestMapping(path = "/owners/{ownerId}/pets/{petId}/edit", method = RequestMethod.POST)
        public String processSubmit(@Valid @ModelAttribute("pet") Pet pet, BindingResult result) {
         
            if (result.hasErrors()) {
                return "petForm";
            }
         
            // ...
         
        }
        ```

        @validated

        ```java
        @RequestMapping(path = "/owners/{ownerId}/pets/{petId}/edit", method = RequestMethod.POST)
        public String processSubmit(@Validated @ModelAttribute("pet") Pet pet, BindingResult result) {
         
            if (result.hasErrors()) {
                return "petForm";
            }
         
            // ...
         
        }
        ```

        自定义校验器

        ```java
        @RequestMapping(path = "/owners/{ownerId}/pets/{petId}/edit", method = RequestMethod.POST)
        public String processSubmit(@ModelAttribute("pet") Pet pet, BindingResult result) {
         
            // 自己编写一个校验方法来处理 result 对象
            new PetValidator().validate(pet, result);
            if (result.hasErrors()) {
                return "petForm";
            }
         
            // ...
         
        }
        ```

26. 选择器

    - @Conditional，当指定的条件都满足时，组件才被注册
    - @ConditionalOnBean，指定bean在上下文中时，才注册当前bean。用在方法上，则默认依赖类为方法的返回类型
    - @ConditionalOnClass，指定类在classpath上时，才初始化当前bean。用在方法上，则默认依赖类为方法的返回类型
    - @ConditionalOnCloudPlatform，在指定云平台才注册配置
    - @ConditionalOnExpression，指定spel为true时注册配置
    - @ConditionalOnJava，在指定java版本时注册配置
    - @ConditionalOnJndi
    - @ConditionalOnMissingBean，指定bean不在上下文中时，才初始化当前bean。用在方法上，则默认依赖类为方法的返回类型
    - @ConditionalOnMissingClass，指定类不在classpath上时，才初始化当前bean。用在方法上，则默认依赖类为方法的返回类型
    - @ConditionalOnNotWebApplication，不是在web环境才注册配置
    - @ConditionalOnProperty，配置文件中的值与指定值是否相等，相等才注册配置
    - @ConditionalOnResource，指定resources都在classpath上才注册配置
    - @ConditionalOnSingleCandidate，上下文中只有一个候选者bean时才注册配置
    - @ConditionalOnWebApplication，是在web环境才注册配置

27. 缓存

    - @EnableCaching，开启缓存配置，支持子类代理或者AspectJ增强
    - @CacheConfig，在一个类下，提供公共缓存配置
    - @Cacheable，放着方法和类上，缓存方法或类下所有方法的返回值
    - @CachePut，每次先执行方法，再将结果放入缓存
    - @CacheEvict，删除缓存
    - @Caching，可以配置@Cacheable、@CachePut、@CacheEvict

28. 定时器

    - @EnableScheduling，开启定时任务功能
    - @Scheduled，按指定执行周期执行方法
    - @Schedules，包含多个@Scheduled，可同时运行多个周期配置
    - @EnableAsync，开启方法异步执行的能力，通过@Async或者自定义注解找到需要异步执行的方法。通过实现AsyncConfigurer接口的getAsyncExecutor()和getAsyncUncaughtExceptionHandler()方法自定义Executor和异常处理。
    - @Async，标记方法为异步线程中执行

29. JPA

    - @Entity ，@Table(name="")

      表明这是一个实体类，一般用于jpa，这两个注解一块使用，但是如果表名和实体类名相同的话，@Table可以省略。

    - @MappedSuperClass

    - @NoRepositoryBean

    - @Column：如果字段名和列名相同，则可以省略。

    - @Id:主键

    - @Transient

      表示该属性并非一个到数据库表的字段的映射，ORM框架将忽略该属性。

      如果一个属性并非数据库表的字段映射，就务必将其标注为@Transient，否则，ORM框架默认将其注解为@Basic。

    - @Basic