# 一、SSH

## 一、基本概念

SSH框架是JAVA EE中三种框架所集成，分别是Struts，Spring，Hibernate框架所组成，是当前比较流行的java web开源框架。

集成SSH框架的系统从职责上分为（Struts2--控制；spring--解耦；hibernate--操作数据库），以帮助开发人员在短期内搭建结构清晰、可服用好、维护方便的web应用程序。使用Struts作为系统的整体基础框架，负责MVC的分离，在Struts框架的模型部分，控制业务跳转，利用hibernate框架对持久层提供支持，spring做管理，管理Struts和hibernate。

## 二、Struts2

- 基本概念

  Struts2是一个基于MVC设计模式的web应用框架，相当于一个servlet，在MVC设计模式中，Struts2作为控制器（controller）来建立模型与视图的数据交互。Struts2在Struts1融合webwork。struts2以webwork为核心，采用拦截器的机制来处理用户的请求，这样的设计使得业务逻辑控制器能够与servletAPI完全脱离。

- Struts2框架的运行结构

  ![img](https://imgconvert.csdnimg.cn/aHR0cHM6Ly9vc2NpbWcub3NjaGluYS5uZXQvb3NjbmV0L3VwLWM0NmUyMDBmOTBlMzhlZDQ5NDc3ZTliNWIzNDBjNjY0NjcwLnBuZw?x-oss-process=image/format,png)

  解析：客户端发送请求（HttpServletRequest）到服务器，服务器接收到请求就先进入web.xml配置文件看看有没有配置过滤器，发现有有Struts2的过滤器，然后找到struts.xml配置文件，struts.xml配置文件里定义一个action，然后就去找到action类，此类继承ActionSupport接口，并且实现了execute()方法，返回一个字符串“success”给struts.xml配置文件，struts.xml配置文件的action会默认调用action类的execute()方法，result接收到返回的字符串，result就会调用你指定的jsp页面将结果呈现，最后响应给客户端。

- **Struts2的优势**

  1. 实现了MVC模式，层次结构清晰，使程序员只需要关注业务逻辑的实现。
  2. 丰富的标签库，大大提高了开发的效率。
  3. Struts2提供丰富的拦截器实现。
  4. 通过配置文件，就可以掌握整个系统各个部分之间的关系。
  5. 异常处理机制，只需在配置文件中配置异常的映射，即可对异常做响应的处理。
  6. Struts2的可扩展性高。
  7. 面向切面编程的思想在Struts2中也有了很好的体现。
  8. 体现了拦截器的使用，拦截器是一个一个的小功能模块，用户可以将这些拦截器合并成一个大的拦截器，这个合成的拦截器就像单独的拦截器一样，只要将它配置到一个Action中就可以。

- **Struts2的缺点**

  - 校验较繁琐，多字段出错返回不同。
  - 安全性太低
  - 获取传参时较麻烦

## 三、Spring

1. 基本概念

   spring是一个开源开发框架，是一个轻量级控制反转(IoC)和面向切面(AOP)的容器框架。

   spring主要用来开发java应用，构建J2EE平台的web应用。其核心就是提供一种新的机制管理业务对象及其依赖关系。

2. spring的流程图

   ![img](https://imgconvert.csdnimg.cn/aHR0cHM6Ly9vc2NpbWcub3NjaGluYS5uZXQvb3NjbmV0L3VwLThmMGJmZjA0OWYyMTUyY2Y3OWIzNzA4NjAzOTRiNmZlODY1LnBuZw?x-oss-process=image/format,png)

   解析：上面是在Struts结构图的基础上加入了spring流程图，在web.xml配置文件中加入了spring的监听器，在struts.xml配置文件中添加

   ```xml
   “<constant name="struts.objectFactory" value="spring" />”
   ```

   是告知Struts2运行时使用spring来管理对象，spring在其中主要做的就是注入实例，所有需要类的实例都由spring管理。

3. spring的优点

   - 容器：spring是一个容器，包含并管理对象的生命周期和配置。可以配置每个bean如何被创建，基于一个可配置原型prototype，你的bean可以创建一个单独的实例或者每次需要时都生成一个新的实例。
   - 支持AOP：spring提供对AOP的支持，它允许将一些通用任务，如安全、事物、日志等进行集中式处理，从而提高了程序的复用性。
   - 轻量级框架：spring是轻量级框架，其基本的版本大约2M。
   - 控制反转：spring通过控制反转实现松耦合。对象们给他们依赖，而不是对象本身，方便解耦，简化开发。
   - 方便程序测试：spring提供了Junit4的支持，可以通过注解方便的测试spring程序。
   - 降低java EE API的使用难度：spring对java EE开发中非常难用的一些API（比如JDBC），都提供了封装，使这些API应用难度大大降低。
   - 方便集成各种优秀框架：spring内部提供了对各种优秀框架（如Struts、mybatis）的直接支持。
   - 支持声明式事务处理：只需要通过配置就可以完成对事务的管理，而无须手动编程。

4. spring的缺点

   - 依赖反射，反射影响进程。
   - 太过于依赖设计模式。
   - 控制器过于灵活。
   - 不支持分布式应用。

## 四、Hibernate

1. 基本概念

   Hibernate是一个开放源代码的对象关系映射框架，它对JDBC进行了非常轻量级的对象封装，它将POJO与数据库表建立映射关系，是一个全自动的orm框架，hibernate可以自动生成SQL语句，自动执行，使得Java程序员可以随心所欲的使用对象编程思维来操纵数据库。 Hibernate可以应用在任何使用JDBC的场合，既可以在Java的客户端程序使用，也可以在Servlet/JSP的Web应用中使用，最具革命意义的是，Hibernate可以在应用EJB的J2EE架构中取代CMP，完成数据持久化的重任。

2. hibernate的核心构成和执行流程图

   ![img](https://imgconvert.csdnimg.cn/aHR0cHM6Ly9vc2NpbWcub3NjaGluYS5uZXQvb3NjbmV0L3VwLWY0ZWIyMmVkMDFkMWI5YzE3OTNkYzEzYzg5MjdhMDlkMWM0LnBuZw?x-oss-process=image/format,png)

   ![img](https://imgconvert.csdnimg.cn/aHR0cHM6Ly9vc2NpbWcub3NjaGluYS5uZXQvb3NjbmV0L3VwLTIxOWE3MWE4ODgyYzlkYTAxZDdjNWQ1M2M3OTRlYjQ2ZmYzLnBuZw?x-oss-process=image/format,png)

3. hibernate的优点

   - 对JDBC访问数据库的代码做了封装，大大简化了数据访问层繁琐的重复性代码。

   - Hibernate是一个优秀的ORM实现。他很大程度的简化DAO层的编码工作，将软件开发人员从大量相同的数据持久层相关编程工作中解放出来，使开发更对象化了。

   - 透明持久化（persistent）带有持久化状态的、具有业务功能的单线程对象，此对象生存期很短。这些对象可能是普通的javabeans/POJO，（POJO概念，plain ordinary java object，简单的java对象，可以简单理解为简单的实体类entity。）这个对象没有实现第三方框架或接口，唯一特殊的是他们正与session关联。一旦这个session被关闭，这些对象就会脱离持久化状态，这样就可被应用程序的任何层自由使用。

   - 事务transaction应用程序用来指定原子操作单元范围的对象，它是单线程的，生命周期很短。它通过抽象将应用从底层具体的JDBC、JTA（java transaction API，JTA允许应用程序执行分布式事务处理，在两个或多个网络计算机资源访问并且更新数据，JDBC驱动程序的JTA支持极大地增强了数据访问能力）以及CORBA（公用对象请求代理程序体系结构，common object request broker architecture，简而言之，CORB允许应用程序和其它的应用程序通讯）事务隔离开。某些情况下，一个session之内可能包含多个transaction对象，事务边界的开启与关闭时必不可少的。

   - 它没有侵入性，是轻量级框架。

   - 移植性好，支持各种数据库，如果换个数据库只要在配置文件中变换配置就可以了，不用改变hibernate代码。

   - 缓存机制，提供一级缓存和二级缓存。

     > 一级缓存：是session级别的缓存，一个session做了一个查询操作，它会把这个操作的结果放到一级缓存中，如果短时间内这个session又做了同一个操作，那么hibernate直接从一级缓存中拿出，而不会去连数据库取数据。
     >
     > 二级缓存：是sessionFactory级别的缓存，就是查询的时候会把结果缓存到二级缓存中，如果同一个sessionFactory创建的某个session执行了相同的操作，hibernate就会从二级缓存中拿出结果，而不会再去连接数据库。

4. hibernate的缺点

   - 持久层封装过于完整，导致开发人员无法对SQL进行优化，无法灵活应用原生SQL。
   - 批量数据处理的时候较为弱势。
   - 框架中使用ORM原则，导致配置过于复杂，遇到大项目，维护问题不断。

# 二、SSM

SSM架构，是三层结合所成的框架，分别是Spring、SpringMVC、MyBatis所组成。Spring依赖注入来管理各层，面向切面编程管理事务，日志和权限。SpringMVC代表了model、view、controller接收外部请求，进行开发和处理。mybatis是基于jdbc的框架，主要用来操作数据库，并且将业务实体和数据表联系起来。

## 一、SpringMVC

1. 基本概念

   属于spring框架的一部分，用来简化MVC架构的web应用程序开发。

2. SpringMVC的优点

   - 拥有强大的灵活性，非侵入性和可配置性
   - 提供了一个前端控制器dispatcherServlet，开发者无需额外开发控制器对象
   - 分工明确，包括控制器、验证器、命令对象、模型对象、处理程序映射视图解析器，每一个功能实现由一个专门的对象负责完成
   - 可以自动绑定用户输入，并正确的转换数据类型
   - 可重用的业务代码：可以使用现有的业务对象作为命令或表单对象，而不需要去扩展某个特定框架的基类。

3. SpringMVC的缺点

   - servlet API耦合难以脱离容器独立运行
   - 太过于细分，开发效率低

## 二、Mybatis

1. 基本概念

   mybatis是一个简化和实现了java数据持久层的开源框架，它抽象了大量的JDBC冗余代码，并提供了一个简单易用的API和数据库交互。

2. mybatis的优点

   - 与JDBC相比，减少了50%以上的代码量。
   - mybatis是最简单的持久化框架，小巧并且简单易学。
   - mybatis灵活，不会对应用程序或者数据库的限售设计强加任何影响，SQL写在XML里，从程序代码中彻底分离，降低耦合度，便于统一管理和优化，可重用。
   - 提供XML标签，支持编写动态SQL语句（XML中使用if,else）。
   - 提供映射标签，支持对象与数据库的ORM字段关系映射（在XML中配置映射关系，也可以使用注解）

3. mybatis的缺点

   - SQL语句的编写工作量较大，对开发人员的SQL语句编写有一定的水平要求。
   - SQL语句过于依赖数据库，不能随意更换数据库。
   - 拼接复杂SQL语句时不灵活。

# 三、Spring AOP

![img](https://img-blog.csdnimg.cn/20200717134934824.png)

## 一、AOP的基本概念

1. 什么是AOP？

   AOP（Aspect Oriented Programming）称为面向切面编程，在程序开发中主要用来解决一些系统层面上的问题，比如日志，事务，权限等等，Struts2的拦截器设计就是基于AOP的思想，是个比较经典的例子。

   在不改变原有逻辑的基础上，增加了一些额外的功能。代理也是这个功能，读写分离也是用AOP来实现的。

2. AOP与OOP

   AOP可以说是OOP（Object Oriented Programming，面向对象编程）的补充和完善。OOP引入封装、继承、多态等概念来建立一种对象层次结构，用于模拟公共行为的一个集合。不过OOP允许开发者定义纵向的关系，但并不适合定义横向的关系，例如日志功能。日志代码往往横向地散布在所有对象层次中，而与它对应的对象的核心功能毫无关系。对于其他类型的代码，如安全性、异常处理和透明的持续性也都是如此，这种散布在各处的无关的代码被称为横切（cross cutting），在OOP设计中，它导致了大量代码的重复，而不利于各个模块的重用。

3. AOP

   AOP技术恰恰相反，它利用一种称为“切面”的技术，剖解开封装的对象内部，并将那些影响了多个类的公共行为封装到一个可重用的模块，并将其命名为“Aspect”，即切面。所谓“切面”，简单说就是那些与业务无关，却被业务模块所共同调用的逻辑或责任封装起来，便于减少系统的重复代码，降低模块之间的耦合度，并有利于未来的可操作性和可维护性。

   使用“横切”技术，AOP把软件系统分为两个部分：核心关注点和横切关注点。业务处理的主要流程是核心关注点，与之关系不大的部分是横切关注点。横切关注点的一个特点是，他们经常发生在核心关注点的多出，而各处基本相似，比如权限认证、日志、事务。AOP的作用在于分离系统中的各种关注点，将核心关注点和横切关注点分离开来。
   ![img](https://img-blog.csdnimg.cn/2020071715183446.jpg?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2d1b3J1aV9qYXZh,size_16,color_FFFFFF,t_70)

## 二、AOP的相关概念

1. 横切关注点

   对哪些方法进行拦截，拦截后怎么处理，这些关注点称之为横切关注点；

2. **Aspect（切面）**

   通常是一个类，里面可以定义切入点和通知。

3. **JoinPoint（连接点）**

   程序执行过程中明确的点，一般是方法的调用，被拦截到的点。因为Spring只支持方法类型的连接点，所以在Spring中连接点指的就是被拦截到的方法，实际上连接点还可以是字段或者构造器。

4. **Advice（通知）**

   AOP在特定的切入点上执行的增强处理，有before（前置）、after（后置）、afterReturning（最终）、afterThrowing（异常）、around（环绕）。

5. **PointCut（切入点）**

   带有通知的连接点，在程序中主要体现在书写切入点表达式。

6. **weave(织入)**

   将切面应用到目标对象并导致代理对象创建的过程。

7. **introduction(引入)**

   在不修改代码的前提下，引入可以在运行期为类动态地增加一些方法或字段。

8. **AOP代理（AOP Proxy）**

   AOP框架创建的对象，代理就是目标对象的加强。Spring中的AOP代理可以是JDK动态代理，也可以是CGLIB代理，前者基于接口，后者基于子类。

9. **目标对象（Target Object）**

   包含连接点的对象，也被称作被通知或被代理对象，POJO。

## 三、Advice通知类型介绍

1. **@Before**
   在目标方法被调用之前做增强处理，@Before只需要指定切入点表达式即可。
2. **@After**
   在目标方法完成之后做增强，无论目标方法是否成功完成，@After可以指定一个切入点表达式。
3. **@AfterReturning**
   在目标方法正常完成后做增强，@AfterReturning除了指定切入点表达式外，还可以指定一个返回值形参名returning，代表目标方法的返回值。
4. **@AfterThrowing**
   主要用来处理程序中未处理的异常，@AfterThrowing除了指定切入点表达式外，还可以指定一个throwing的返回值形参名，可以通过该形参名来访问目标方法中所抛出的异常对象。
5. **@Around**
   环绕通知，在目标方法完成前后做增强处理，环绕通知是最重要的通知类型，像事务、日志等都是环绕通知，注意编程中的核心ProceedingJoinPoint。

## 四、AOP的使用场景

1、**Authentication 权限**
2、**Caching 缓存**
3、**Context passing 内容传递**
4、**Error handling 错误处理**
5、**Lazy loading　懒加载**
6、**Debugging　　调试**
7、l**ogging, tracing, profiling and monitoring　记录跟踪　优化　校准**
8、**Performance optimization　性能优化**
9、**Persistence　　持久化**
10、**Resource pooling　资源池**
11、**Synchronization　同步**
12、**Transactions 事务**

# 五、常用注解

Spring的一个核心功能是IOC，就是将Bean初始化加载到容器中，Bean是如何加载到容器的，可以使用Spring注解方式或者Spring XML配置方式。

Spring注解方式减少了配置文件内容，更加便于管理，并且使用注解可以大大提高了开发效率！

注解本身是没有功能的，和xml一样，注解和xml都是一种元数据，元数据即解释数据的数据，也就是所谓的配置。

## 1.xml和注解的最佳实践

1. xml用来管理bean。
2. 注解只负责完成属性的注入。

## 2。使用注解唯一需要注意的就是，必须开启注解的支持

```xml
<context:component-scan base-package="com.guo"></context:component-scan>
<context:annotation-config/>
```

## 3.Spring的常用注解

1. 给容器中注入组件

   - @Component：泛指各种组件

   - @Controller、@Service、@Repository都可以称为@Component。

     - @Controller：控制层
     - @Service：业务层
     - @Repository：数据访问层

   - @Bean 导入第三方包里面的注解

   - @Import(要导入到容器中的组件)；

     ```java
     @Import({Color.class, MyImportSelector.class, MyImportBeanDefinitionRegistrar.class})
     public class MainConfig2 {
     	@Scope("prototype")
     	@Bean("person")
     	public Person person() {
     		System.out.println("我是Person");
     		return new Person("素小暖",25);
     	}
     }
     ```

   - @ImportSelector：返回需要导入的组件的全类名数组；

     ```java
     public class MyImportSelector implements ImportSelector {
         //返回值就是导入容器的组件全类目
         // AnnotationMetadata 当前标注@Import注解的类的所有注解信息
         public String[] selectImports(AnnotationMetadata importingClassMetadata) {
             //importingClassMetadata.get
             return new String[]{"com.atguigu.bean.Blue","com.atguigu.bean.Red"};
         }
     }
     ```

   - @ImportBeanDefinitionRegistrar：手动注册bean到容器中；

     ```java
     public class MyImportBeanDefinitionRegistrar implements ImportBeanDefinitionRegistrar {
      
         /*
         * AnnotationMetadata：当前类的注解信息
         * BeanDefinitionRegistry：BeanDefinition注册类
         * 把所有需要添加到容器中的bean，调用BeanDefinitionRegistry.registerBeanDefinition手动注入
         *
         * */
         public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
             boolean definition = registry.containsBeanDefinition("com.atguigu.bean.Red");
             boolean definition2 = registry.containsBeanDefinition("com.atguigu.bean.Blue");
             if(definition && definition2){
                 //指定bean定义信息（bean的类型，bean的scope）
                 RootBeanDefinition rootBeanDefinition = new RootBeanDefinition(RainBow.class);
                 //注册一个bean，指定bean名
                 registry.registerBeanDefinition("rainBow",rootBeanDefinition);
             }
         }
     }
     ```

   - 使用spring提供的FactoryBean（工厂Bean）

     - 默认获取到的是工厂Bean调用getObject创建的对象
     - 要获取工厂Bean本身，需要在id前面加一个&

     ```java
     @Bean
     public ColorFactoryBean colorFactoryBean(){
         return new ColorFactoryBean();
     }
     ```

     ```java
     //创建一个spring定义的FactoryBean
     public class ColorFactoryBean implements FactoryBean<Color> {
         //返回一个Color对象，并将Color添加到容器中
         public Color getObject() throws Exception {
             System.out.println("ColorFactoryBean,getObject()");
             return new Color();
         }
      
         public Class<?> getObjectType() {
             return Color.class;
         }
      
         public boolean isSingleton() {
             return false;
         }
     }
     ```

2. 注入bean的注解

   1. Autowired：由bean提供
      - @Autowired可以作用在变量、setter方法、构造函数上；
      - 有个属性为required，可以配置为false；
   2. @Inject：由JSR-330提供
      - @Inject用法和@Autowired一样。
   3. @Resource：由JSR-250提供
   4. @Primary 让spring进行自动装配的时候，默认使用首选的bean，和@Qualifier一个效果。

   *@Autowired、@Inject是默认按照类型匹配的，@Resource是按照名称匹配的，@Autowired如果需要按照名称匹配需要和@Qualifier一起使用，@Inject和@Name一起使用。*

3.  @JsonIgnore

   1. 作用：在json序列化时将java bean中的一些属性忽略掉，序列化和反序列化都受影响。
   2. 使用方法：一般标记在属性或者方法上，返回的json数据即不包含该属性。
   3. 注解失效：如果注解失效，可能是因为你使用的是fastJson，尝试使用对应的注解来忽略字段，注解为：@JSONField(serialize = false)，使用方法一样。

4. 初始化和销毁方法

   1. 通过@Bean(initMethod="init",destoryMethod="destory")方法
   2. 通过bean实现InitializingBean来定义初始化逻辑，DisposableBean定义销毁逻辑
   3. 可以使用JSR250：@PostConstruct：初始化方法；@PreDestory：销毁方法。
   4. BeanPostProcessor：bean的后置处理器，在bean初始化前后进行一些处理工作

   postProcessBeforeInitialization：在初始化之前工作；

   postProcessAfterInitialization：在初始化工作之后工作；

5. java配置类相关注解

   1. @Configuration：声明当前类为配置类；
   2. @Bean：注解在方法上，声明当前方法的返回值为一个bean，替代xml中的方式；
   3. @ComponentScan：用于对Component进行扫描；

6. 切面（AOP）相关注解

   1. @Aspect 声明一个切面
   2. @After 在方法执行之后执行（方法上）
   3. @Before 在方法执行之前执行（方法上）
   4. @Around 在方法执行之前与之后执行（方法上）
   5. @PointCut 声明切点

   在java配置类中使用@EnableAspectJAutoProxy注解开启Spring对AspectJ代理的支持

7. @Bean的属性支持

   1. @Scope设置类型包括：设置Spring容器如何新建Bean实例（方法上，得有@Bean）
      1. Singleton：单例,一个Spring容器中只有一个bean实例，默认模式
      2. Protetype：每次调用新建一个bean
      3. Request：web项目中，给每个http request新建一个bean
      4. Session：web项目中，给每个http session新建一个bean
      5. GlobalSession：给每一个 global http session新建一个Bean实例

8. @Value注解

   1. 支持如下方式的注入：
      - 注入普通字符
      - 注入操作系统属性
      - 注入表达式结果
      - 注入其它bean属性
      - 注入文件资源
      - 注入网站资源
      - 注入配置文件
   2. @Value三种情况的用法。
      1. ${}是去找外部配置的参数，将值赋过来
      2. \#{}是SpEL表达式，去寻找对应变量的内容
      3. \#{}直接写字符串就是将字符串的值注入进去

9. 环境切换

   1. @Profile：指定组件在哪个环境的情况下才能被注册到容器中，不指定，任何环境下都能注册这个组件。
   2. @Conditional：通过实现Condition接口，并重写matches方法，从而决定该bean是否被实例化。

10. 异步相关

    1. @EnableAsync：配置类中通过此注解开启对异步任务的支持；
    2. @Async：在实际执行的bean方法使用该注解来声明其是一个异步任务（方法上或类上所有的方法都将异步，需要@EnableAsync开启异步任务）

11. 定时任务相关

    1. @EnableScheduling：在配置类上使用，开启计划任务的支持（类上）
    2. @Scheduled：来申明这是一个任务，包括cron,fixDelay,fixRate等类型（方法上，需先开启计划任务的支持）

12. Enable***注解说明

    - @EnableAspectAutoProxy：开启对AspectJ自动代理的支持；
    - @EnableAsync：开启异步方法的支持；
    - @EnableScheduling：开启计划任务的支持；
    - @EnableWebMvc：开启web MVC的配置支持；
    - @EnableConfigurationProperties：开启对@ConfigurationProperties注解配置Bean的支持；
    - @EnableJpaRepositories：开启对SpringData JPA Repository的支持；
    - @EnableTransactionManagement：开启注解式事务的支持；
    - @EnableCaching：开启注解式的缓存支持；

13. 测试相关注解

    1. @RunWith：运行器，Spring中通常用于对JUnit的支持

    2. @ContextConfiguration：用来加载配置配置文件，其中classes属性用来加载配置类。

       ```java
       @RunWith(SpringJUnit4ClassRunner.class)
       @ContextConfiguration(locations = {"classpath*:/*.xml"})
       public class CDPlayerTest {
        
       }
       ```

       @ContextConfiguration这个注解通常与@RunWith(SpringJUnit4ClassRunner.class)联合使用用来测试。

       @ContextConfiguration括号里的locations = {"classpath*:/*.xml"}就表示将classpath路径里所有的xml文件都包括进来，自动扫描的bean就可以拿到，此时就可以在测试类中使用@Autowired注解来获取之前自动扫描包下的所有bean。

14. @EqualsAndHashCode

    任意类的定义都可以添加@EqualsAndHashCode注解，让lombok帮你生成equals(Object other)和hashCode()方法的实现。默认情况下会使用非静态和非transient型字段来生成，但是你也通过在字段上添加 @EqualsAndHashCode.Include 或者@EqualsAndHashCode.Exclude 修改你使用的字段（甚至指定各种方法的输出）。或者你也可以通过在类上使用 @EqualsAndHashCode(onlyExplicitlyIncluded = true) ，且在特定字段或特定方法上添加 @EqualsAndHashCode.Include 来指定他们。

    如果将@EqualsAndHashCode添加到继承于另一个类的类上，这个功能会有点棘手。一般情况下，为这样的类自动生成equals和hashCode方法是一个坏思路，因为超类也有定义了一些字段，他们也需要equals/hashCode方法但是不会自动生成。通过设置callSuper=true，可以在生成的equals和hashCode方法里包含超类的方法。对于hashCode，·super.hashCode()·会被包含在hash算法内，而对于equals，如果超类实现认为它与传入的对象不一致则会返回false。注意：并非所有的equals都能正确的处理这样的情况。然而刚好lombok可以，若超类也使用lombok来生成equals方法，那么你可以安全的使用它的equals方法。如果你有一个明确的超类, 你得在callSuper上提供一些值来表示你已经斟酌过，要不然的话就会产生一条警告信息。

    当你的类没有继承至任何类（非java.lang.Object, 当然任何类都是继承于Object类的），而你却将callSuer置为true, 这会产生编译错误（译者注： java: Generating equals/hashCode with a supercall to java.lang.Object is pointless. ）。因为这会使得生成的equals和hashCode方法实现只是简单的继承至Object类的方法，只有相同的对象并且相同的hashCode才会判定他们相等。若你的类继承至另一个类又没有设置callSuper, 则会产品一个告警，因为除非超类没有（或者没有跟相等相关的）字段，否则lombok无法为你生成考虑超类声明字段的实现。

15. XmlAccessorType

    类级别的注解

    定义这个类中何种类型需要映射到XML。

    1. XmlAccessType.FIELD：映射这个类中的所有字段到XML
    2. XmlAccessType.PROPERTY：映射这个类中的属性（get/set方法）到XML
    3. XmlAccessType.PUBLIC_MEMBER：将这个类中的所有public的field或property同时映射到XML（默认）
    4. XmlAccessType.NONE：不映射

16. @SuppressWarnings

    Suppress  抑制；镇压；废止 Warnings警告 

    @SuppressWarnings("resource")是J2SE 提供的一个批注。该批注的作用是给编译器一条指令，告诉它对被批注的代码元素内部的某些警告保持静默。

    @SuppressWarnings 批注允许您选择性地取消特定代码段（即，类或方法）中的警告。其中的想法是当您看到警告时，您将调查它，如果您确定它不是问题，您就可以添加一个 @SuppressWarnings 批注，以使您不会再看到警告。
    虽然它听起来似乎会屏蔽潜在的错误，但实际上它将提高代码安全性，因为它将防止您对警告无动于衷 — 您看到的每一个警告都将值得注意。

## 四、springMVC常用注解

1. @EnableWebMvc ：在配置类中开启Web MVC的配置支持。

2. @Controller

3. @RequestMapping：用于映射web请求，包括访问路径和参数。

4. @ResponseBody：支持将返回值放到response内，而不是一个页面，通常用户返回json数据。

5. @RequestBody:允许request的参数在request体中，而不是在直接连接的地址后面。（放在参数前）

6. @PathVariable:用于接收路径参数，比如@RequestMapping(“/hello/{name}”)声明的路径，将注解放在参数前，即可获取该值，通常作为Restful的接口实现方法。

7. @RestController:该注解为一个组合注解，相当于@Controller和@ResponseBody的组合，注解在类上，意味着，该Controller的所有方法都默认加上了@ResponseBody。

8. @ControllerAdvice

   - 全局异常处理

     ```java
     package com.atguigu.commonutils.exceptionhandler;
      
     import com.atguigu.commonutils.R;
     import org.springframework.web.bind.annotation.ControllerAdvice;
     import org.springframework.web.bind.annotation.ExceptionHandler;
     import org.springframework.web.bind.annotation.ResponseBody;
      
     @ControllerAdvice
     public class GlobalExceptionHandler {
         @ExceptionHandler(Exception.class)
         @ResponseBody //为了能够返回数据
         public R error(Exception e){
             e.printStackTrace();
             return R.error().message("执行了全局异常处理");
         }
      
         @ExceptionHandler(ArithmeticException.class)
         @ResponseBody //为了能够返回数据
         public R error(ArithmeticException e){
             e.printStackTrace();
             return R.error().message("执行了ArithmeticException异常处理");
         }
     }
     ```

     在该类中，可以定义多个方法，不同的方法处理不同的异常，例如处理全部异常的方法、专门处理算术运算异常的方法...

     @ExceptionHandler 注解用来指明异常的处理类型，即如果这里指定为 ArithmeticException，则数组越界异常就不会进到这个方法中来。

   - 全局数据绑定

     全局数据绑定功能可以用来做一些初始化的数据操作，我们可以将一些公共的数据定义在添加了 @ControllerAdvice 注解的类中，这样，在每一个 Controller 的接口中，就都能够访问导致这些数据。

     ```java
     @ControllerAdvice
     public class MyGlobalExceptionHandler {
         @ModelAttribute(name = "md")
         public Map<String,Object> mydata() {
             Map<String, Object> map = new HashMap<>();
             map.put("id", 23);
             map.put("name", "素小暖");
             return map;
         }
     }
     ```

     使用 @ModelAttribute 注解标记该方法的返回数据是一个全局数据，默认情况下，这个全局数据的 key 就是返回的变量名，value 就是方法返回值，当然开发者可以通过 @ModelAttribute 注解的 name 属性去重新指定 key。

   - 全局数据预处理

     考虑我有两个实体类，Book 和 Author，分别定义如下：

     ```java
     public class Book {
         private String name;
         private Long price;
         //getter/setter
     }
     public class Author {
         private String name;
         private Integer age;
         //getter/setter
     }
     ```

     此时，如果我定义一个数据添加接口，如下：

     ```java
     @PostMapping("/book")
     public void addBook(Book book, Author author) {
         System.out.println(book);
         System.out.println(author);
     }
     ```

     这个时候，添加操作就会有问题，因为两个实体类都有一个 name 属性，从前端传递时 ，无法区分。此时，通过 @ControllerAdvice 的全局数据预处理可以解决这个问题

     解决步骤如下:

     1. 给接口中的变量取别名

        ```java
        @PostMapping("/book")
        public void addBook(@ModelAttribute("b") Book book, @ModelAttribute("a") Author author) {
            System.out.println(book);
            System.out.println(author);
        }
        ```

     2. 进行请求数据预处理

        ```java
        @InitBinder("b")
        public void b(WebDataBinder binder) {
            binder.setFieldDefaultPrefix("b.");
        }
        @InitBinder("a")
        public void a(WebDataBinder binder) {
            binder.setFieldDefaultPrefix("a.");
        }
        ```

        InitBinder("b") 注解表示该方法用来处理和Book和相关的参数,在方法中,给参数添加一个 b 前缀,即请求参数要有b前缀。

9. @ExceptionHandler：用于全局处理控制器里的异常。

10. @InitBinder：用来设置WebDataBinder，WebDataBinder用来自动绑定前台请求参数到Model中。

11. @ModelAttribute：

    1. @ModelAttribute注释方法 

       如果把@ModelAttribute放在方法的注解上时，代表的是：该Controller的所有方法在调用前，先执行此@ModelAttribute方法。可以把这个@ModelAttribute特性，应用在BaseController当中，所有的Controller继承BaseController，即可实现在调用Controller时，先执行@ModelAttribute方法。比如权限的验证（也可以使用Interceptor）等。

    2. @ModelAttribute注释一个方法的参数 

       当作为方法的参数使用，指示的参数应该从模型中检索。如果不存在，它应该首先实例化，然后添加到模型中，一旦出现在模型中，参数字段应该从具有匹配名称的所有请求参数中填充。

       ![img](https://img-blog.csdnimg.cn/20210106195653897.jpg?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2d1b3J1aV9qYXZh,size_16,color_FFFFFF,t_70)

       ![img](https://img-blog.csdnimg.cn/20210106195702208.jpg?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2d1b3J1aV9qYXZh,size_16,color_FFFFFF,t_70)

12. @Transactional 

    @Transactional 注解放在类级别时，表示所有该类的公共方法都配置相同的事务属性信息。EmployeeService 的所有方法都支持事务并且是只读。当类级别配置了@Transactional，方法级别也配置了@Transactional，应用程序会以方法级别的事务属性信息来管理事务，换言之，方法级别的事务属性信息会覆盖类级别的相关配置信息。

    ```java
    @Transactional(propagation= Propagation.SUPPORTS,readOnly=true)
    @Service(value ="employeeService")
    public class EmployeeService
    ```

## 五、其他注解

1. @Schema

   @schema注解标注在class上，表示此类对应的数据库表对应的schema。

   可以用如下语句判断某个实体类上是否带有@schema注解，从而得到schema。

   > bean.getClass().isAnnotationPresent(Schema.class)

## 六、json常用注解

1. @JsonIgnoreProperties

   此注解是类注解，作用是json序列化时将java bean中的一些属性忽略掉，序列化和反序列化都受影响。

   写法将此标签加在user类的类名上 ，可以多个属性也可以单个属性。

   ```java
   //生成json时将name和age属性过滤
   @JsonIgnoreProperties({"name"},{"age"})
   public class  user {
    
       private  String name;
       private int age;
   }
   ```

   

2. @JsonIgnore

   此注解用于属性或者方法上（最好是属性上），作用和上面的@JsonIgnoreProperties一样

   ```java
   //生成json 时不生成age 属性
   public class user {
    
       private String name;
       @JsonIgnore
       private int age;
   }
   ```

   

3. @JsonFormat

   此注解用于属性或者方法上（最好是属性上），可以方便的把Date类型直接转化为我们想要的模式，比如：

   ```java
   public class User{
       @JsonFormat(pattern = “yyyy-MM-dd HH-mm-ss”)
       private Date date;
   }
   ```

4. @JsonSerialize

   此注解用于属性或者getter方法上，用于在序列化时嵌入我们自定义的代码，比如序列化一个double时在其后面限制两位小数点。

5. @JsonDeserialize

   此注解用于属性或者setter方法上，用于在反序列化时可以嵌入我们自定义的代码，类似于上面的@JsonSerialize

6. @Transient

   如果一个属性并非数据库表的字段映射，就务必将其标示为@Transient，否则ORM框架默认其注解为@Basic；

7. @JsonIgnoreType

   标注在类上，当其他类有该类作为属性时，该属性将被忽略。

8. @JsonProperty

   @JsonProperty 可以指定某个属性和json映射的名称。例如我们有个json字符串为{“user_name”:”aaa”}，
   而java中命名要遵循驼峰规则，则为userName，这时通过@JsonProperty 注解来指定两者的映射规则即可。这个注解也比较常用。

   ```java
   public class SomeEntity {
       @JsonProperty("user_name")
       private String userName;
   }
   ```

9. 只在序列化情况下生效的注解

   1. @JsonPropertyOrder

      在将 java pojo 对象序列化成为 json 字符串时，使用 @JsonPropertyOrder 可以指定属性在 json 字符串中的顺序。

   2. @JsonInclude

      在将 java pojo 对象序列化成为 json 字符串时，使用 @JsonInclude 注解可以控制在哪些情况下才将被注解的属性转换成 json，例如只有属性不为 null 时。

      1. @JsonInclude(JsonInclude.Include.NON_NULL)

         这个注解放在类头上，返给前端的json里就没有null类型的字段，即实体类与json互转的时候 属性值为null的不参与序列化。
         另外还有很多其它的范围，例如 NON_EMPTY、NON_DEFAULT等

10. 在反序列化情况下生效的注解

    1. @JsonSetter

       @JsonSetter 标注于 setter 方法上，类似 @JsonProperty ，也可以解决 json 键名称和 java pojo 字段名称不匹配的问题。

# JDBC tempate 使用

## hibernate与spring jdbctemplate对比

hibernate批量处理**海量数据从性能上考虑是不可取的，浪费了很大的内存**。从它的机制上讲hibernate是先把符合条件的数据查询出来，放到内存中，然后再进行操作，一顿操作猛如虎，奈何性能不理想。

Spring JdbcTemplate和hibernate在处理简单查询操作时，效率基本相同，甚至hibernate的效率要略高一些。但是在执行批量操作，繁琐操作时，hibernate的效率能达到Spring JdbcTemplate的80%就不错了。但hibernate可以极大提高开发效率，像分页等较复杂的开发都是可以直接完成的，所以弥补了效率的不足。

追求执行效率用Spring JdbcTemplate，追求开发效率用hibernate，如果存在较多批量操作，建议使用spring JdbcTemplate。

## Spring JdbcTemplate相对于hibernate优势所在

本人在开发的过程中接触比较多的是hibernate、mybatis。两种持久层框架都感觉不尽如人意。hibernate就不说了为什么很多人不愿意用hibernate了?

mybatis是持久层的轻量级封装，在mybatis中如果需要进行某一个操作，首先需要定义mapper，然后再定义mapper.xml。在mapper.xml中需要完成model映射，需要写上接口相关的sql,这个过程作者认为重复性的工作比较多。如果能在java类中直接写sql，同时还能进行简单的对象操作，那么程序将即拥有hibernate的部分优点，又能拥有mybatis的部分优点，岂不是很香，Spring  JdbcTemplate应运而生。

## Spring JdbcTemplate简介

Spring对数据库的操作在jdbc上面做了深层次的封装，使用spring的注入功能，可以把DataSource注册到JdbcTemplate中。

## JdbcTemplate提供的方法

1. execute方法:可以用于执行任何SQL语句，一般用于执行DDL语句。
2. update方法和batchUpdate方法:update方法用于执行新增、修改、删除等语句；batchUpdate方法用于执行批量处理相关语句。
3. query方法及queryForXXX方法:用于执行查询相关语句。
4. call方法:用于执行存储过程、函数相关语句。

# REST中如何区别post和put的请求

## 幂等性

在编程中一个幂等操作的特点是其任意多次执行所产生的影响均与一次执行的影响相同。
幂等函数，或幂等方法，是指可以使用相同参数重复执行，并能获得相同结果的函数。这些函数不会影响系统状态，也不用担心重复执行会对系统造成改变。例如，“setTrue()”函数就是一个幂等函数,无论多次执行，其结果都是一样的更复杂的操作幂等保证是利用唯一交易号(流水号)实现。

## EST请求中哪些是幂等操作

GET，PUT，DELETE都是幂等操作，而POST不是，下面来分析一下。

- GET：请求很好理解，对资源做查询多次，此实现的结果都是一样的。
- PUT：请求的幂等性可以这样理解，将A修改为B，它第一次请求值变为了B，再进行多次此操作，最终的结果还是B，与第一次执行的结果是一样的，所以PUT是幂等操作。
- DELETE：DELETE同理，第一次将资源删除后，后面多次进行此删除请求，最终结果是一样的将资源删除掉了。
- POST：不是幂等操作，因为一次请求添加一份新资源，二次请求则添加了两份新资源，多次请求会产生不同的结果，因此POST不是幂等操作。

## 根据幂等性区分POST与PUT的使用

- 如果是更新，并不会产生新的数据，新的数据会覆盖老的数据，用put
- 如果是创建，会产生新的数据，则用post

# SQL注入

## 什么是sql注入

SQL注入是一种常见的安全漏洞攻击技术，攻击者利用该漏洞可以在应用程序中执行恶意的SQL语句，从而获取敏感数据或者修改数据。SQL注入攻击是通过将恶意SQL代码注入到Web应用程序输入框或其他用户可以提交数据的地方，然后在服务器上执行这些恶意代码。

例如，一个简单的用户登录表单，攻击者可以在用户名和密码输入框中输入恶意的SQL代码，例如输入" 'or '1'='1'"，这会让系统的SQL查询条件变为"SELECT * FROM users WHERE username='' or '1'='1' AND password=''"，这样就可以绕过原有的登录条件，成功登录到系统中。

为了防止SQL注入攻击，可以采取以下措施：

1. 使用参数化的SQL查询：参数化查询可以将用户输入的参数作为参数传递给SQL语句，而不是将用户输入直接拼接到SQL语句中，这样可以避免SQL注入攻击。
2. 对用户输入进行验证和过滤：对于用户输入的数据进行严格的验证和过滤，例如只允许输入数字、字母和特定字符等，避免输入特殊字符，可以降低SQL注入攻击的风险。
3. 使用安全的API和框架：使用经过安全验证和测试的API和框架，以及及时更新相关的软件和库，可以降低系统受到SQL注入攻击的风险。

总之，SQL注入攻击是一种严重的安全漏洞，对于Web应用程序和其他涉及数据库操作的应用程序来说，必须采取相应的措施来防范和避免SQL注入攻击。

## sql注入攻击的总体思路

SQL注入攻击的总体思路是通过在Web应用程序中输入恶意的SQL代码，从而绕过应用程序的验证和过滤机制，达到执行恶意操作的目的。攻击者利用应用程序的输入漏洞，向数据库服务器提交恶意的SQL语句，从而获取或篡改数据库中的数据。

SQL注入攻击一般可以分为以下几个步骤：

1. 找到目标：攻击者会先找到一个存在SQL注入漏洞的Web应用程序，通常是通过网络扫描等方式进行寻找。
2. 漏洞利用：攻击者会在Web应用程序中输入恶意的SQL代码，通过绕过应用程序的验证和过滤机制，提交恶意SQL语句到数据库服务器。
3. 数据库操作：恶意SQL语句被数据库服务器执行，攻击者可以利用漏洞查询或者篡改数据库中的数据，例如获取用户账号、密码等敏感信息，或者篡改数据表中的数据。
4. 掩盖攻击痕迹：攻击者通常会清除数据库中的攻击痕迹，以避免被发现。

> 为了防止SQL注入攻击，需要采取一系列的安全措施，包括在应用程序中对用户输入进行验证和过滤、使用参数化查询、限制数据库用户的权限、及时更新数据库服务器软件等。此外，开发人员需要加强对Web应用程序的安全性测试和代码审查，及时发现和修复潜在的安全漏洞。

## SQL 注入攻击实例

网站使用的数据库是MySQL，并且搜索功能的实现代码如下：

```javascript
String searchQuery = "SELECT * FROM products WHERE name = '" + userInput + "'";
```

在这个代码中，用户输入的数据被直接拼接到SQL查询语句中，没有进行任何过滤或者验证。如果攻击者在搜索框中输入以下内容：

```python
' OR '1'='1
```

那么最终的SQL查询语句将变成以下内容：

```mysql
SELECT * FROM products WHERE name = '' OR '1'='1'
```

这个SQL语句的意思是查询所有产品，因为 '1'='1' 这个条件永远为真，这样攻击者就可以绕过原有的查询条件，查询到所有的产品信息。

在这个例子中，攻击者利用了Web应用程序中输入漏洞，通过构造恶意的SQL语句，绕过了原有的查询条件，最终获取了所有的产品信息。要防止这种类型的攻击，应该对用户输入进行过滤和验证，或者使用参数化的SQL查询方式，而不是直接拼接用户输入到SQL语句中。

## 如何防御sql注入

1. 输入过滤：对用户输入进行过滤和验证，确保只有合法的字符被传递到数据库。可以使用一些内置的输入过滤函数或者正则表达式来实现输入过滤。
2. 参数化查询：使用参数化查询的方式构建SQL语句，而不是直接将用户输入拼接到SQL语句中。参数化查询方式可以保证SQL语句和用户输入分离，从而防止SQL注入攻击。
3. 最小化数据库权限：限制数据库用户的权限，确保用户只能访问他们需要的数据表和操作。例如，可以创建只有读取数据权限的用户，以避免敏感数据被恶意篡改。
4. 框架和库的安全措施：使用一些现成的Web框架和数据库库，这些框架和库通常都内置了安全措施，如参数化查询等，以减少SQL注入攻击的风险。
5. 及时更新数据库服务器：及时更新数据库服务器软件，以修复已知的安全漏洞，并加强数据库的安全性。
6. 安全审计：进行安全审计，发现和修复潜在的安全漏洞，以避免SQL注入攻击。

总之，防御SQL注入攻击需要从多个角度入手，包括输入过滤、参数化查询、限制数据库权限、使用框架和库的安全措施、及时更新数据库服务器软件等。在实际开发中，开发人员需要加强对Web应用程序的安全性测试和代码审查，及时发现和修复潜在的安全漏洞。

# mybatis-plus使用教程

## 一、简介

[MyBatis](https://so.csdn.net/so/search?q=MyBatis&spm=1001.2101.3001.7020)-Plus（简称MP），是mybatis的增强工具，在mybatis的基础上只做增强不做改变，为简化开发，提高效率而生。

## 二、优点

### 1、无侵入

MyBatis-Plus在MyBatis的基础上进行扩展，只做增强不做改变，引入MyBatis-Plus不会对现有的MyBatis框架产生任何影响，而且MP支持所有MyBatis原生的特性。

### 2、依赖少

仅仅依赖MyBatis以及MyBatis-Spring

### 3、损耗小

启动即会注入基本CRUD，性能基本无损耗，直接面向对象操作

### 4、预防SQL注入

内置SQL注入剥离器，有效预防sql注入攻击

### 5、通用CRUD操作

内置通用mapper、通用service，仅仅通过少量配置即可实现单表大部分CRUD操作，更有强大的条件构造器，满足各类使用需求，CRUD程序员的噩梦。

### 6、多种主键策略

支持多达4种主键策略（内含分布式唯一ID生成器），可自由配置，完美解决主键问题。

> - AUTO 数据库ID自增
> - INPUT 用户输入ID
> - ID_WORKER 全局唯一ID，Long类型的主键
> - ID_WORKER_STR 字符串全局唯一ID
> - UUID 全局唯一ID，UUID类型的主键
> - NONE 该类型为未设置主键类型
>
> 主键生成的几种方式：
>
> 1、数据库自增长
>
> 2、UUID
>
> 3、Redis生成id

### 7、支持热加载

mapper对应的xml支持热加载，对于简单的CRUD操作，甚至可以无xml启动。

`注意：3.0.6`版本上移除了该功能,不过最新快照版已加回来并打上废弃标识,预计`3.1.0`版本上完全移除

### 8、支持Active Record

Active Record（简称AR），是一种领域模型模式，特点是一个模型类对应关系型数据库中的一个表，而模型类的一个实例对应表中的一行记录。

MyBatis-Plus对AR的支持，仅需要继承Model类并实现主键指定方法即可。

### 9、支持代码生成

采用代码或者Maven插件可快速生成mapper、model、service、controller层代码，支持模板引擎，代码生成器类只需修改表名，并输入项目包结构即可，轻松惬意。

### 10、支持自定义全局通用操作

支持全局通用方法注入

### 11、支持关键词自动转义

支持数据库关键词自动转移，还可以自定义关键词

### 12、内置分页插件

基于Mybatis物理分页，开发者无需关心具体操作，配置好插件之后，写分页等同于写基本List查询。

分页？初级程序员的噩梦，现在？so easy！

### 13、内置性能分析插件

可输出sql语句及执行时间，建议开发测试阶段启用。

### 14、内置全局拦截插件

提供全表delete、update操作，智能分析阻断，预防误操作。

# mybatis的一级缓存和二级缓存

## 一、什么是缓存

缓存是存在于内存中的临时数据。
使用缓存减少和数据库的交互次数，提高执行效率。

### 1、适用于缓存

- 经常查询并且不经常改变的；
- 数据的正确与否对最终结果影响不大的；

### 2、不适用于缓存

- 经常改变的数据；
- 数据的正确与否对最终结果影响很大的；
- 例如：商品的库存，银行的汇率，股市的牌价；

## 二、mybatis一级缓存

### 1、一级缓存简介

一级缓存作用域是sqlsession级别的，同一个sqlsession中执行相同的[sql查询](https://so.csdn.net/so/search?q=sql查询&spm=1001.2101.3001.7020)（相同的sql和参数），第一次会去查询数据库并写到缓存中，第二次从一级缓存中取。

一级缓存是基于 PerpetualCache 的 HashMap 本地缓存，默认打开一级缓存。

### 2、何时清空一级缓存

如果中间sqlSession去执行commit操作（执行插入、更新、删除），则会清空SqlSession中的一级缓存，这样做的目的为了让缓存中存储的是最新的信息，避免[脏读](https://so.csdn.net/so/search?q=脏读&spm=1001.2101.3001.7020)。

一级缓存时执行commit，close，增删改等操作，就会清空当前的一级缓存；当对SqlSession执行更新操作（update、delete、insert）后并执行commit时，不仅清空其自身的一级缓存（执行更新操作的效果），也清空二级缓存（执行commit()的效果）。

### 3、一级缓存无过期时间，只有生命周期

MyBatis在开启一个数据库会话时，会创建一个新的SqlSession对象，SqlSession对象中会有一个Executor对象，Executor对象中持有一个PerpetualCache对象，见下面代码。当会话结束时，SqlSession对象及其内部的Executor对象还有PerpetualCache对象也一并释放掉。

## 三、mybatis二级缓存

### 1、二级缓存简介

它指的是Mybatis中SqlSessionFactory对象的缓存。由同一个SqlSessionFactory对象创建的SqlSession共享其缓存。

二级缓存是 mapper 映射级别的缓存，多个 SqlSession 去操作同一个 Mapper 映射的 sql 语句，多个SqlSession 可以共用二级缓存，二级缓存是跨 SqlSession 的。

### 2、二级缓存何时存入

在关闭sqlsession后(close)，才会把该sqlsession一级缓存中的数据添加到namespace的二级缓存中。

开启了二级缓存后，还需要将要缓存的pojo实现Serializable接口，为了将缓存数据取出执行反序列化操作，因为二级缓存数据存储介质多种多样，不一定只存在内存中，有可能存在硬盘中。

### 3、二级缓存有过期时间，但没有后台线程进行检测

需要注意的是，并不是key-value的过期时间，而是这个cache的过期时间，是flushInterval，意味着整个清空缓存cache，所以不需要后台线程去定时检测。

每当存取数据的时候，都有检测一下cache的生命时间，默认是1小时，如果这个cache存活了一个小时，那么将整个清空一下。

当 Mybatis 调用 Dao 层查询数据库时，先查询二级缓存，二级缓存中无对应数据，再去查询一级缓存，一级缓存中也没有，最后去数据库查找。

[SSM整合]: https://blog.csdn.net/guorui_java/article/details/105682019	"SSM整合"

