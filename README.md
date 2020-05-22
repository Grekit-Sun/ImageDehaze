### 泛型

- 泛型类
  - 简单的泛型（一个对象）
  - 泛型元组（多个对象）
- 泛型接口
- 泛型方法
  - 注意，当使用泛型类时，必须在创建对象的时候指定类型参数的值，而使用泛型方法的时候，通常不必指明参数类型，因为编译器会为我们找出具体的类型。这称为类型参数推断（type argument inference）。因此，我们可以像调用普通方法一样调用f()，而且就好像是f()被无限次地
- 泛型数组

![img](https://images2015.cnblogs.com/blog/690292/201609/690292-20160923095944481-1758567758.png)

### 单例模式

#### 懒汉

- 单例类的实例在第一次被引用时候才被初始化。

```java
public class Singleton {  
    private static Singleton instance=null;  
     
    private Singleton() {  
    }  
    public static Singleton getInstance(){  
        if (instance == null) {  
            instance = new Singleton();  
        } 
        return instance;  
    }  
}
```



#### 饿汉

- 单例类的实例在加载的时候就被初始化。

```java
public class Singleton {  
    private static Singleton instance = new Singleton();  
     
    private Singleton() {  
         
    }  
     
    public static Singleton getInstance(){  
        return instance;  
    }  
}
```

在单线程程序中，上面两种形式基本可以满足要求了，但是在多线程环境下，单例类就有可能会失效，这个时候就要对其加锁了，来确保线程安全。

```java
public class NetWorkManager {

    private static volatile NetWorkManager mNetWorkManager;

    private NetWorkManager() {
    }

    public static NetWorkManager getInstance() {
        if (mNetWorkManager == null) {
            synchronized (NetWorkManager.class) {
                if (mNetWorkManager == null) {
                    mNetWorkManager = new NetWorkManager();
                }
            }
        }
        return mNetWorkManager;
    }
}
```

注：当A与B同时调用getSingleton时，判断第一个if都为空，这时A拿到锁，进行第二层if判断，条件成立new了一个对象；

B在外层等待，A创建完成，释放锁，B拿到锁，进行第二层if判断，条件不成立，结束释放锁。C调用getSingleton时第一层判断不成立，直接拿到singleton对象返回，避免进入锁，减少性能开销。

```
单例中 volatile 修饰符的作用：
代码中 private static volatile Singleton sin = null;  volatile修饰符的作用是什么呢？

volatile修饰变量只是为了禁止指令重排序，因为在 sin = new Singleton(); 创建对象时，底层会分为四个指令执行：（下面是正确的指令执行顺序）
①、如果类没有被加载过，则进行类的加载
②、在堆中开辟内存空间 adr，用于存放创建的对象
③、执行构造方法实例化对象
④、将堆中开辟的内存地址 adr 赋值给被volatile修饰的引用变量 sin

如果sin引用变量不使用volatile修饰的话，则可能由于编译器和处理器对指令进行了重排序，导致第④步在第③步之前执行，此时sin引用变量不为null了，但是sin这个引用变量所指向的堆中内存地址中的对象是还没被实例化的，实例对象还是null的；那么在第一次判空时就不为null了，然后去使用时就会报NPE空指针异常了。
```



### synchronized关键字用法

1. 修饰方法：即一次只能有一个线程进入该方法,其他线程要想在此时调用该方法,只能排队等候,当前线程(就是在synchronized方法内部的线程)执行完该方法后,别的线程才能进入.

```java
   public synchronized void synMethod() {
        //方法体
      }
```

2. 修饰代码块：synchronized后跟括号,括号里是变量,这样,一次只有一个线程进入该代码块.此时,线程获得的是成员锁.例如:

```java
    public int synMethod(int a1){
        synchronized(a1) {
          //一次只能有一个线程进入
        }
      }
```

3. synchronized后面括号里是一对象,此时,线程获得的是对象锁.例如:

```java
public class MyThread implements Runnable {
    public static void main(String args[]) {
    MyThread mt = new MyThread();
    Thread t1 = new Thread(mt, "t1");
    Thread t2 = new Thread(mt, "t2");
    t1.start();
    t2.start();
  }

  public void run() {
    synchronized (this) {
      System.out.println(Thread.currentThread().getName());
    }
  }
} 
```

对于3,如果线程进入,则得到当前对象锁,那么别的线程在该类所有对象上的任何操作都不能进行.在对象级使用锁通常是一种比较粗糙的方法。为什么要将整个对象都上锁，而不允许其他线程短暂地使用对象中其他同步方法来访问共享资源？如果一个对象拥有多个资源，就不需要只为了让一个线程使用其中一部分资源，就将所有线程都锁在外面。由于每个对象都有锁，可以如下所示使用虚拟对象来上锁：

4. 对于3,如果线程进入,则得到当前对象锁,那么别的线程在该类所有对象上的任何操作都不能进行.在对象级使用锁通常是一种比较粗糙的方法。为什么要将整个对象都上锁，而不允许其他线程短暂地使用对象中其他同步方法来访问共享资源？如果一个对象拥有多个资源，就不需要只为了让一个线程使用其中一部分资源，就将所有线程都锁在外面。由于每个对象都有锁，可以如下所示使用虚拟对象来上锁：

注：

- synchronized后面的括号即对象锁，即a、b线程持有的锁，建议是静态的确保唯一性。如果不是唯一的也要在代码中控制其唯一性；
- 括号中用`this`，本质上没有区别，对于同步线程来说都只是一个锁而已，那么，还是那个问题，必须保证锁是唯一的，this指的是当前类的当前实例，用this作为锁，就必须保证各个线程所持有的ShutDown类的实例对象是同一个。
- 类名.class:字节码对象，不是该类的对象。例如：person.class表示的是获得 person类的字节码对象。关于字节码对象有作用：调用getClassLoader（）方法获得类加载器Class clazz=person.class;ClassLoader classLoader=clazz.getClassLoader();其他的我暂时也想不起来，你可再去网上找字节码对象的其他作用这些知识都和java里面的反射、注解和动态代理方面的知识有关，要是想深度研究的话，可以去找这些方面的知识。希望对你有帮助^_^

### Retrofit

Retrofit 是一个 RESTful 的 HTTP 网络请求框架的封装，网络请求的工作本质上是 OkHttp 完成，而 Retrofit 仅负责 网络请求接口的封装



### 变换调用：map、flatMap、lift、compose

通常在发出事件之后，处理事件之前，会有一些操作，如请求网络，String变Bitmap等等，可以称之为变换操作；
\###**变换，就是将事件序列中的对象或整个序列进行加工处理，转换成不同的事件或事件序列。**

#### 1 map() 事件对象的直接变换

将参数中的 String 对象转换成一个 Bitmap 对象后返回，而在经过 map() 方法后，事件的参数类型也由 String转为了 Bitmap

```java
Observable.just("img_url")
    .map(new Func1<String, Bitmap>() {
        @Override
        public Bitmap call(String filePath) { // 参数类型 String
            return getBitmapFromPath(filePath); // 返回类型 Bitmap
        }
    })
    .subscribe(new Action1<Bitmap>() {
        @Override
        public void call(Bitmap bitmap) { // 参数类型 Bitmap
            showBitmap(bitmap);
        }
    });

```

Func1 和 Action1 非常相似，也是 RxJava 的一个接口，用于包装含有一个参数的方法。 Func1 和 Action的区别在于， Func1 包装的是有返回值的方法。另外，和 ActionX 一样， FuncX 也有多个，用于不同参数个数的方法。

#### 2 flatMap():

flatMap() 和 map() 有一个相同点：它也是把传入的参数转化之后返回另一个对象。但需要注意，和 map() 不同的是， flatMap() 中返回的是个 Observable 对象，并且这个 Observable 对象并不是被直接发送到了 Subscriber 的回调方法中。
**flatMap() 的原理：**

- 使用传入的事件对象创建一个 Observable 对象；
- 并不发送这个 Observable, 而是将它激活，于是它开始发送事件；
- 每一个创建出来的 Observable 发送的事件，都被汇入同一个 Observable ，而这个 Observable 负责将这些事件统一交给 Subscriber 的回调方法

假设这么一种需求：假设有一个数据结构『学生』，每个学生只有一个名字，但却有多个课程，如果要打印出每个学生所需要修的所有课程的名称。

```java
Student[] students = ...;
Subscriber<Course> subscriber = new Subscriber<Course>() {
    @Override
    public void onNext(Course course) {
        Log.d(tag, course.getName());
    }
    ...
};
Observable.from(students)
    .flatMap(new Func1<Student, Observable<Course>>() {
        @Override
        public Observable<Course> call(Student student) {
            return Observable.from(student.getCourses());
        }
    })
    .subscribe(subscriber);

```

#### 3 变换的原理：lift()

RxJava 都不建议开发者自定义 Operator 来直接使用 lift()，而是建议尽量使用已有的 lift() 包装方法（如 map() flatMap() 等）进行组合来实现需求，因为直接使用 lift() 非常容易发生一些难以发现的错误

#### 4 compose: 对 Observable 整体的变换

compose() 是针对 Observable 自身进行变换。举个例子，假设在程序中有多个 Observable ，并且他们都需要应用一组相同的 lift() 变换

```java
 observable1
    .lift1()
    .lift2()
    .lift3()
    .lift4()
    .subscribe(subscriber1);
observable2
    .lift1()
    .lift2()
    .lift3()
    .lift4()
    .subscribe(subscriber2);
```

用 compose() 来解决：

```java
public class LiftAllTransformer implements Observable.Transformer<Integer, String> {
    @Override
    public Observable<String> call(Observable<Integer> observable) {
        return observable
            .lift1()
            .lift2()
            .lift3()
            .lift4();
    }
}
...
Transformer liftAll = new LiftAllTransformer();
observable1.compose(liftAll).subscribe(subscriber1);
observable2.compose(liftAll).subscribe(subscriber2);
observable3.compose(liftAll).subscribe(subscriber3);
observable4.compose(liftAll).subscribe(subscriber4);
```

### compose

#### 使用场景一

我们可以用 compose 操作符来进行线程的切换，一般用在网络请求的地方。

原始的写法为：

```java
.subscribeOn(Schedulers.io())
.observeOn(AndroidSchedulers.mainThread())
```

我们可以将上面的操作封装成一个简单的工具类来使用.

```java
import io.reactivex.FlowableTransformer;
import io.reactivex.MaybeTransformer;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by x-sir on 2019/4/19 :)
 * Function:线程调度
 */
public class RxThreadUtils {

    /**
     * Flowable 切换到主线程
     */
    public static <T> FlowableTransformer<T, T> flowableToMain() {
        return upstream -> upstream.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * Observable 切换到主线程
     */
    public static <T> ObservableTransformer<T, T> observableToMain() {
        return upstream -> upstream.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * Maybe 切换到主线程
     */
    public static <T> MaybeTransformer<T, T> maybeToMain() {
        return upstream -> upstream.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
```

调用起来也比较简单，我们在网络请求的时候就可以这样调用（假设网络请求返回的是Flowable 对象）：

```java
.compose(RxThreadUtils.flowableToMain())
```

#### 使用场景二

compose 操作符可以和 Transformer 结合使用，一方面可以让代码看起来更加简洁，另一方面能够提高代码的复用性，因为 RxJava 提倡链式调用，我们可以合理的使用 compose 操作符来防止链式调用被打破。

RxLifecycle 是 trello 开源的一个配置 RxJava 使用的开源库，我们知道 RxJava 有个缺点就是会导致内存泄露，此时，RxLifecycle 横空出世了，它可以配合 RxJava 一起使用，可以有效防止内存泄漏发生，使用起来也是非常方便，举个简单的例子：

```java
myObservable
    .compose(RxLifecycle.bind(lifecycle))
    .subscribe();
```

### Retrofit常用注解

#### 1、什么是Retrofit?

Retrofit 是一个Square开发的安卓客户端请求库。其中内部封装了okhttp库。官方的介绍是使用非常简短 Retrofit使用注解,能够极大的简化网络请求数据的代码。
 Retrofit常用注解包括：@Query，@QueryMap，@Field，@FieldMap，@FormUrlEncoded，@Path，@Url

#### 2、**@Query，@QueryMap**

@Query主要用于Get请求数据，用于拼接在拼接在Url路径后面的查询参数，一个@Query相当于拼接一个参数，多个参数中间用，隔开。
**使用示例代码：**

```java
Retrofit retrofit = new Retrofit.Builder() 
.baseUrl("http://ms.csdn.net/")
.build();public interface csdnService { //如果没有参数 @GET("api/ask/all_questions") 
Call<List<Repo>> getData(); 
//只有少数参数 
@GET("api/ask/all_questions") 
Call<List<Repo>> getData(@Query("page") int page, @Query("is_reward") int is_reward);
}
```

@QueryMap：主要的效果等同于多个@Query参数拼接，主要也用于Get请求网络数据。

```java
@GET("http://ms.csdn.net/api/ask/all_questions")
Call<List<Repo>> getData(@QueryMap Map<String,String> params);
Map<String,String>params=newHashMap();
params.put("name","liming");
params.put("age",24);
params.put("sex","man");
params.put("city","Shanghai");
```

#### 3、**@Field，@FieldMap**

@Field的用法类似于@Query，就不在重复列举了，主要不同的是@Field主要用于Post请求数据。
 @FieldMap的用法类似于@QueryMap。
 两者主要区别是：如果请求为post实现，那么最好传递参数时使用@Field、@FieldMap和@FormUrlEncoded。因为@Query和或QueryMap都是将参数拼接在url后面的，而@Field或@FieldMap传递的参数时放在请求体的。

#### 4、**@FormUrlEncoded**

我们在代码中使用是不是发现了@POST比起@GET多了一个@FromUrlEncoded的注解。
 如果去掉@FromUrlEncoded在post请求中使用@Field和@FieldMap，那么程序会抛出**Java.lang.IllegalArgumentException: @Field parameters can only be used with form encoding**. 的错误异常。
 所以如果平时公司如果是Post请求的话，千万别忘记了加这@FromUrlEncoded注解。

```java
@FormUrlEncoded
@POST("users/user/question")
Call<TnGou> getTngouPost(@Field("page") int page);
```

#### 5、**@Path**

@Path主要用于Get请求，用于替换Url路径中的变量字符。

```java
public interface csdnService {
@GET("users/{user}/question") 
Call<List<Repo>> getData(@Path("user") String user);}
```

该接口定义了一个getData方法，该方法通过GET请求去访问服务器的users/{user}/question路径，其中通过@Path注解会把路径中的{user}替换成参数user的具体值。比如:user的值如果是zhangsan,那么Url的路径就是users/zhangsan/question.

#### 6、**@Url**

@Url是动态的Url请求数据的注解。需要注意的是使用@Path时，path对应的路径不能包含”/”，不然每个加到host Url后面的东西都会被省略掉。千万注意了

```java
Retrofit retrofit = new Retrofit.Builder() 
.baseUrl("http://ms.csdn.net/") 
.build(); 
public interface csdnService {
@GET 
Call<List<Repo>> getData(@Url String user);
 }
```

