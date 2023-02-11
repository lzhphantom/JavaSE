# JavaSE
java 基础复习 为了面试
## 一、JavaSE
### 1.java基础
### 2.java集合
### 3.java高并发

#### 1.java多线程基础知识总结

##### 	1.线程的五种状态

1. **新建状态（New）**：线程对象被创建后，就进入了新建状态。例如，Thread thread = new Thread()。
2. **就绪状态(Runnable):** 也被称为“可执行状态”。线程对象被创建后，其它线程调用了该对象的start()方法，从而来启动该线程。例如，thread.start()。处于就绪状态的线程，随时可能被CPU调度执行。
3. **运行状态(Running):** 线程获取CPU权限进行执行。需要注意的是，线程只能从就绪状态进入到运行状态。
4. **阻塞状态(Blocked):** 阻塞状态是线程因为某种原因放弃CPU使用权，暂时停止运行。直到线程进入就绪状态，才有机会转到运行状态。阻塞的情况分三种：
   - 等待阻塞 – 通过调用线程的wait()方法，让线程等待某工作的完成。
   -  同步阻塞 – 线程在获取synchronized同步锁失败(因为锁被其它线程所占用)，它会进入同步阻塞状态。
   - 其他阻塞 – 通过调用线程的sleep()或join()或发出了I/O请求时，线程会进入到阻塞状态。当sleep()状态超时、join()等待线程终止或者超时、或者I/O处理完毕时，线程重新转入就绪状态。
5. **死亡状态(Dead):** 线程执行完了或者因异常退出了run()方法，该线程结束[生命周期](https://so.csdn.net/so/search?q=生命周期&spm=1001.2101.3001.7020)。

##### 2.线程的停止方法

- 正常停止->利用次数，不建议死循环
- 使用标志位->设置一个标志位
- 不要用stop或者destory 过时方法

##### 3.悲观锁与乐观锁

###### 	乐观锁

​		乐观锁不是数据库自带的，需要我们自己去实现。乐观锁是指操作数据库时(更新操作)，想法很乐观，认为这次的操作不会导致冲突，在操作数据时，并不进行任何其他的特殊处理（也就是不加锁），而在进行更新后，再去判断是否有冲突了。

通常实现是这样的：在表中的数据进行操作时(更新)，先给数据表加一个版本(version)字段，每操作一次，将那条记录的版本号加1。

​		不足：两个事务都读取了数据库的某一行，经过修改以后写回数据库，这时就遇到了数据竞争。

​		**java的乐观锁收拾基于CAS(Compare And Swap)实现的**

- CAS(V,A,B),内存值V，期待值A，修改值B（V是否等于A，等于执行，不等于将B赋给V）

- ABA问题

  1. 读取原值
  2. 通过原子操作比较和替换。
  3. 虽然比较和替换是原子性，但是读取原值和比较替换这两部不是原子性的，期间原值可能被其他线程修改；

  对该变量增加一个版本号，每次修改更新其版本号。JUC包提供了AtomicStampedRefenrence

  （2）自旋次数过多

  CAS操作在不成功是会重新读取内存值并自旋尝试，当系统并发量非常高每次读取新值又被改动，导致CAS操作失败不断的自旋重试，此时使用CAS并不能提高效率，反而因为自旋多次不如加锁进行操作的效率高。

  （3）只能保证一个变量的原子性

  当一个变量操作是，CAS可以保证其原子性，但是通多操作多个变量CAS无能为力

  可以封装成对象，再对对象进行CAS操作，或者直接加锁。

###### 	悲观锁

​		悲观锁就是在操作数据时，认为此操作会出现数据冲突，所以在进行每次操作时都要通过获取锁才能进行对相同数据的操作。MySQL InnoDB中使用悲观锁

​		优点与不足：悲观并发控制实际上是”先取锁再访问”的保守策略，为数据处理的安全提供了保证。但是在效率方面，处理加锁的机制会让数据库产生额外的开销，还有增 加产生死锁的机会；另外，在只读型事务处理中由于不会产生冲突，也没必要使用锁，这样做只能增加系统负载；还有会降低了并行性，一个事务如果锁定了某行数 据，其他事务就必须等待该事务处理完才可以处理那行数

- 共享锁是乐观锁的一种：共享锁指的就是对于多个不同的事务，对同一个资源共享同一个锁
- 排它锁：排它锁与共享锁相对应，就是指对于多个不同的事务，对同一个资源只能有一把锁。

###### 	总结

​	乐观锁适用于多读的应用类型，这样可以提高吞吐量，像数据库如果提供类似于write_condition机智的其实都是提供的乐观锁。 相反，如果经常发生冲突，上层应用会不断进行 retry，这样反而降低了性能，所以这种情况下用悲观锁比较合适

#### 公平锁与非公平锁

- 公平锁：按照线程在队列中的排队顺序，先到者先拿到锁；
- 非公平锁：当线程要获取锁是，无视队列顺序直接抢锁，谁抢到就是谁的；

#### 独占锁与共享锁

- 独占锁：当多个线程在挣钱锁的过程中，无论是读还是写，只能一个线程获取，其他线程阻塞等待
- 共享锁：允许多个线程同时获取共享资源，采取是乐观锁的机制，共享锁限制写写操作、读写操作， 但不会限制读读操作；

#### 可重入锁与不可重入锁

- 可重入锁：一个线程可以多次占用同一个锁，但是解锁时，需要执行相同次数的解锁操作
- 不可重入锁：一个线程不能多次占用同一个锁；

#### 死锁

多个线程相互持有对方需要的资源，导致多个线程相互等待，无法继续执行后续任务

产生死锁的4个必要条件：

1. 互斥条件：指进程对所分配到的资源进行排它性使用，一段时间资源只能一个线程占用，其他线程需要资源，需要请求等待，直到占有资源被释放；

2. 请求和保持条件：指进程已经保持至少一个资源，但又提出了新的资源请求，而此时请求线程阻塞，但又对自己已获得的其他资源保持不放。

3. 不可剥夺：指线程获得的资源，在未使用完之前，不能被剥夺，只能在使用完之后自己释放；

4. 循环等待：一个等待一个，产生了一个闭环。

   **饥饿**

   指的是线程由于无法获取需要的资源而无法进行执行。

   **产生饥饿的主要原因**

   1. 高优先级的线程不断抢占资源，低优先级的线程抢不到
   2. 某个线程一直不释放，导致其他线程无法获取资源

   **如何避免饥饿**

   1. 使用公平锁分配资源
   2. 为程序分配足够的系统资源
   3. 避免持有锁的线程长时间占用锁

   **活锁**

   指的是多个线程同时抢占同一个资源，都主动将资源让个其他线程使用，导致这个资源在多个线程来回切换，导致线程因无法获取相应资源而无法继续执行的现象。

   **如何避免活锁**

   可以让多个线程随机等待一段时间后再次抢占资源，这样会大大减少线程抢占资源的冲突次数，有效避免活锁的产生。

#### 多线程锁的升级原理

锁的4种状态

- 无锁状态

  没有对资源进行锁定，所有的线程都能访问并修改同一个资源，但是只有一个线程能修改成功。

  无锁总是假设对共享资源访问没有冲突的理想状态，无锁策略采用一种称为CAS的技术来保证线程执行的安全性，CAS是无锁技术的关键。

- 偏向锁

  对象的代码一直被同一线程执行，不存在多个线程竞争，该线程在后续执行中自动获取锁，降低获取锁带来的性能开销。偏向锁，指的是偏向第一个加锁线程，该线程是不会主动释放偏向锁，只有当其他线程尝试竞争偏向锁才会被释放。

  偏向锁的撤销，需要在某个时间点上没有字节码正在执行时，先暂停偏向锁的线程，然后判断锁对象是否处于被锁定状态，如果线程不处于活动状态，则将对象头设置为无锁状态，并撤销偏向锁。如果处于活动状态，升级为轻量级锁的状态。

- 轻量级锁

  指当锁是偏向锁的时候，被第二个线程B访问，此时偏向锁就会升级为轻量级锁，线程B会通过自旋的形式尝试获取锁，线程不会阻塞，从而提升性能。

  当前只有一个等待线程，则该线程通过自旋进行等待，但是当自旋超过一定次数，轻量级锁便会升级为重量级锁，当一个线程已持有锁，另一个线程在自旋，而此时第三个线程来访是，轻量级锁也会升级为重量级锁。

- 重量级锁

  指当有一个线程获取锁以后，其余所有等待获取该锁的线程都会处于阻塞状态。

  重量级锁通过对象内部的监听器实现，而其中监听器的本质是依赖于底层操作系统的Mutex Lock实现的，操作系统实现线程之间的切换需要从用户态切换到内核态，切换成本非常高。

#### 2.线程池的优势

​	它的主要特点可以总结为：**线程复用**，**控制最大并发数**，**管理线程**

1. 可以降低资源消耗，通过重复使用已经创建的线程避免多次创建和销毁线程所带来的性能开销
2. 可以提高响应速度，任务到达时，如果有空闲线程可以直接执行，而不需要等待线程创建时间
3. 提高线程的可管理性，线程是稀缺资源，如果对于线程的创建和销毁不加以管理，不仅会消耗系统资源，并且会降低系统的稳定性，使用线程池可以对线程进行统一的分配、调节和监控

**ThreadPoolExecutor** 对象的7个参数

- `int corePoolSize`: 线程池中常驻的核心线程数，当线程池线程数达到该值时，就会将任务放入队列
- `int maximumPoolSize`: 线程池中能容纳的同时执行的最大线程数，必须大于等于1
- `long keepAliveTime`: 多余空闲线程的存活时间，当前线程数大于`corePoolSize`且空闲时间达到该时间值时，多余线程会被销毁
- `TimeUnit unit`: `keepAliveTime`的时间单位
- `BlockingQueue<Runnable> workQueue`: 任务队列，保存提交但尚未执行的任务
- `ThreadFactory threadFactory`: 线程池中创建 工作线程的工厂，一般使用默认工厂
- `RejectedExecutionHandler handler`: 拒绝策略，当队列满时且工作线程等于最大线程数并的处理策略

线程池的工作流程：

1. 创建线程池后，等待任务提交
2. 当调用`execute()`提交任务时，线程池做出如下判断：
   - 如果正在运行的线程数小于corePoolSize,立刻创建线程
   - 如果正在运行的线程数等于corePoolSize,将任务放入队列
   - 如果队列已满并且运行的线程数小于maximumPoolSize,创建非核心线程数来执行任务
   - 如果队列已满并且运行的线程数等于maximumPoolSize,按照饱和拒绝策略来拒绝新任务
3. 当一个线程执行完成后，会从队列中取下一个任务来执行
4. 当线程没有运行超过keepAliveTime时，线程池会判断：
   1. 如果当前线程数大于corePoolSize,那么这个线程将会被销毁

线程池的4中拒绝策略：

1. `AbortPolicy`：直接抛出`RejectedExecutionException`异常，该策略为默认策略
2. `CallerRunsPolicy`：”调用者运行策略“，该策略即不会抛弃任务，也不会抛出异常，而是将某些任务回退至调用者，从而降低新的任务流量
3. `DiscardOldestPolicy`：抛弃队列中等待最久的任务，然后把当前任务加入队列中尝试再次尝试提交
4. `DiscardPolicy`：直接丢弃任务，不予处理也不抛出异常。如果允许任务丢失，这是最好的一种方案

以上拒绝策略均实现了`RejectedExecutionHandler`接口

**如何配置线程池**

1. CPU密集型

   CPU密集型任务需要大量的运算，CPU长期保持高负载，阻塞时间较少

   那么对于CPU密集型任务，需要通常配置较少的线程数量，一般核心线程数设置为CPU核心数，减少线程上下文的切换

2. IO密集型

   IO密集型任务需要大量的IO，也就意味着大量的阻塞，所以在单个线程上运行IO密集型任务会因为等待IO结束导致浪费大量的CPU运算能力

   所以在IO密集型任务中使用多线程可以大大加速程序运行，可以配置较多的线程

   参考公式为：核心线程数=CPU核心数/(1-阻塞系数)

   阻塞系数：0.8~0.9

   例如8核心的CPU，则设置核心线程数为8/(1-0.9)=80

创建线程池的7种方式：

1. Executors.newFixedThreadPool：创建⼀个固定⼤⼩的线程池，可控制并发的线程数，超出的线程会在队列中等待；
   2. Executors.newCachedThreadPool：创建⼀个可缓存的线程池，若线程数超过处理所需，缓存⼀段时间后会回收，若线程数不够，则新建线程；
3. Executors.newSingleThreadExecutor：创建单个线程数的线程池，它可以保证先进先出的执⾏顺序；
4. Executors.newScheduledThreadPool：创建⼀个可以执⾏延迟任务的线程池；
5. Executors.newSingleThreadScheduledExecutor：创建⼀个单线程的可以执⾏延迟任务的线程池；
6. Executors.newWorkStealingPool：创建⼀个抢占式执⾏的线程池（任务执⾏顺序不确定）【JDK1.8 添加】。
7. ThreadPoolExecutor：最原始的创建线程池的⽅式，它包含了 7 个参数可供设置，上面有讲

#### ThreadLocal的理解

1. 基本介绍想

   ThreadLocal叫做***线程变量***，意思是ThreadLocal中**填充的变量**属于**当前线程**，该变量对其他线程而言是隔离的，也就是说该变量是当前线程独有的变量。ThreadLocal为变量在每个线程中都创建了一个副本，那么每个线程可以访问自己内部的副本变量。

2. 常见使用场景

   1. 每个线程需要自己单独的实例
   2. 实例需要在多个方法中共享，但不希望被多线程共享

- 场景一：存储用户Session
- 场景二：数据库连接，处理数据库事务
- 场景三：数据跨层传递（controller,service,dao）

#### java守护线程

1. 定义

   与守护线程相对于的就是用户线程，用户线程可以理解为系统工作的线程，而守护线程守护的就是用户线程。当用户线程全部执行完毕，守护线程才会跟着结束。

#### CountDownLatch 使用

​	CountDownLatch是java中一个协调多线程的工具类，假如多线程在执行后，需要等待所有都执行完再执行下一步，那么就可以使用CountDownLatch。

#### RPC应用

1. RPC 是一种框架或者说一种架构，主要目标就是让远程服务调用更简单、透明，调用远程就像调用本地一样。
2. 什么情况下用RPC
   - 如果我们开发简单的应用，业务流程简单、流量不大，根本用不着 RPC。
   - 当我们的应用访问量增加和业务增加时，发现单机已无法承受，此时可以根据不同的业务（划分清楚业务逻辑）拆分成几个互不关联的应用，分别部署在不同的机器上，此时可能也不需要用到 RPC 。
   - 随着我们的业务越来越多，应用也越来越多，应用与应用相互关联调用，发现有些功能已经不能简单划分开，此时可能就需要用到 RPC。
   - 比如，我们开发电商系统**消息中间件[rpc](https://3z0.cn/jishu/274.html)应用场景**，需要拆分出用户服务、商品服务、优惠券服务、支付服务、订单服务、物流服务、售后服务等等，这些服务之间都相互调用，这时内部调用最好使用 RPC ，同时每个服务都可以独立部署，独立上线。
3. RPC 架构主要包括三部分：
   1. 服务提供者启动后主动向服务注册中心（Registry）注册机器IP、端口以及提供的服务列表；
   2. 服务消费者启动时向服务注册中心（Registry）获取服务提供方地址列表。
   3. 服务注册中心（Registry）可实现负载均衡和故障切换。

#### 进程与线程

1. 进程是操作系统分配资源的最小单位，线程是CPU调度的最小单位。
2. 一个进程可以包含多个线程。
3. 进程与进程之间是相对独立，进程中的线程并不完全独立，可以共享进程中的堆内存、方法区内存、系统资源等；
4. 进程上下文切换要比线程的上下文切换慢得多；
5. 某个进程发生异常，不会对其他进程造成影响；但某个线程发生异常，可能会对此进程的其他线程产生影响；

#### 线程组与线程池

1. 线程组

   线程组可以管理多个线程，顾名思义，把功能相同的线程放在一起，方便管理。

2. 线程组与线程池的区别

   - 线程组中线程可以跨线程修改数据，但线程组和线程组之间不可用跨线程修改；
   - 线程池就是创建一定数量的线程，批量处理任务，通过重用已存在的线程，降低线程创建和销毁造成的消耗；
   - 线程池可以有效管理线程的数量，避免线程的无限创建，线程是很耗费系统资源，动不动就产生OOM并且会造成CPU过度切换，也有强大的拓展功能，比如延时定时线程池。

#### 并行与并发

1. 并行指多个线程在一段时间的每个时刻都在同时运行，并发指多个线程在一段时间内同时运行（不是同一时刻，一段时间内交叉执行）
2. 并行的多个线程不会抢占系统资源，并发的多个线程会抢占系统资源；
3. 并行是多cpu的产物，单个CPU中只有并发，没有并行；

#### 原子性、可见性、有序性傻傻分不清楚

![img](https://upload-images.jianshu.io/upload_images/1884094-d63d2229327c50c2.png?imageMogr2/auto-orient/strip|imageView2/2/format/webp)

- **可见性**的定义：一个线程对共享变量的修改，另外一个线程能够立刻看到。

  内存并不直接和CPU打交道，而是通过高速缓存与CPU打交道。

  ```xml
  cpu  <——> 高速缓存  <———>  内存
  ```

  可见性问题都是由Cpu缓存不一致为并发编程带来，而其中的主要有下面三种情况：

  1. 线程交叉执行
  2. 重排序结合线程交叉执行
  3. 共享变量更新后的值没有在工作内存及主存间及时更新，请参考错误例子**VisibilityDemo.java**
     1. **VisibilityDemo1**加入volatile保证可见性，且只保证可见性；
     2. **VisibilityDemo2**换成Atomic相关类，保证原子性和可见性。
     3. **VisibilityDemo3**使用synchronized，Lock，保证可见性和原子性

- Java内存模型对volatile关键字定义了一些特殊的访问规则，当一个变量被volatile修饰后，它将具备两种特性，或者说volatile具有下列两层语义：

  - 第一、保证了不同线程对这个变量进行读取时的可见性。即一个线程修改了某个变量的值， 这个新值对其他线程来说是立即可见的。(volatile解决了线程间共享变量的可见性问题)。
    1. 使用 volatile 关键字会强制将在某个线程中修改的共享变量的值立即写入主内存。
    2. 使用 volatile 关键字的话， 当线程 2 进行修改时， 会导致线程 1 的工作内存中变量的缓存行无效（反映到硬件层的话， 就是 CPU 的 L1或者 L2 缓存中对应的缓存行无效);
    3. 由于线程 1 的工作内存中变量的缓存行无效，所以线程1再次读取变量的值时会去主存读取。基于这一点，所以我们经常会看到文章中或者书本中会说volatile 能够保证可见性。
  - 第二、禁止进行指令重排序， 阻止编译器对代码的优化。

  > **综上所述**：就是用volatile修饰的变量，对这个变量的读写，不能使用 CPU 缓存，必须从内存中读取或者写入。

- **synchronized**

  - 作用域

    - 是某个对象实例，修饰成员方法
    - 是某个类的范围，修饰静态方法

  - 作用于方法中的某个区块

    - 表示只对这个区块的资源实行互斥访问。

  - synchronized关键字是不能继承的，也就是说，基类的方法

    - ```java
      synchronized f(){    // 具体操作}
      ```

      在继承类中并不自动是

      ```java
      synchronized f(){    // 具体操作}
      ```

      而是变成了

      ```java
      f(){    // 具体操作}
      ```

    > 综上3点所述：synchronized关键字主要有以下这3种用法：
    >
    > - **修饰实例方法**：作用于当前实例加锁，进入同步代码前要获得当前实例的锁
    > - **修饰静态方法**：作用于当前类对象加锁，进入同步代码前要获得当前类对象的锁
    > - **修饰代码块**：指定加锁对象，对给定对象加锁，进入同步代码块前要获得给定对象的锁

  - JVM关于synchronized的两条规定：

    1. 线程解锁前，必须把共享变量的最新值刷新到主内存
    2. 线程加锁时，将清空工作内存中共享变量的值，从而是使用共享变量时，需要从主内存中重新读取最新的值（注意：加锁与解锁是同一把锁）

    - 我们在使用synchronized保证可见性的时候也要注意以下几点
      1. 无论synchronized关键字加在方法上还是对象上，它取得的锁都是对象；而不是把一段代码或函数当作锁――而且同步方法很可能还会被其他线程的对象访问。
      2. 每个对象只有一个锁（lock）与之相关联。Java 编译器会在 synchronized 修饰的方法或代码块前后自动加上加锁 lock() 和解锁 unlock()，这样做的好处就是加锁 lock() 和解锁 unlock() 一定是成对出现的，毕竟忘记解锁 unlock() 可是个致命的 Bug（意味着其他线程只能死等下去了）。
      3. 实现同步是要很大的系统开销作为代价的，甚至可能造成死锁，所以尽量避免无谓的同步控制.

#### JVM内存模型、Java内存模型、Java对象模型

### 4.java8新特性
### 5.java代码实例
https://blog.csdn.net/guorui_java/article/details/120098618