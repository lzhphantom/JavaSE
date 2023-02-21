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