[TOC]

# Android高手课


## x.1 高质量开发

### 1. 崩溃优化

- Android两种崩溃

	- 1. Java崩溃的捕获

		- Java内的未捕获异常

	- 2. Native崩溃的捕获流程Signal

		- 1. Native中访问非法地址

		- 2. 地址出了问题

		- 3. 程序主动abort

- Native崩溃从捕获到解析

	https://mp.weixin.qq.com/s/g-WzYF3wWAljok1XjPoo7w

	- 

	- 编译端

	- 客户端

	- 服务端

	- 最核心的是怎么样保证客户端在各种极端情况下依然可以生成崩溃日志。

		- 情况一、 文件句柄泄露，导致创建日志文件失败，怎么办

		- 情况二、因为栈溢出，导致日志生成失败，怎么办？

- 评价崩溃

	- UV 崩溃率

		- UV 崩溃率 = 发生崩溃的 UV / 登录 UV

		- PV 崩溃率、启动崩溃率、重复崩溃率这些指标

			- 因为启动崩溃对用户带来的伤害最大，应用无法启动往往通过热修复也无法拯救。

	- 安全模式

		https://mp.weixin.qq.com/s?__biz=MzUxMzcxMzE5Ng==&mid=2247488429&idx=1&sn=448b414a0424d06855359b3eb2ba8569&source=41#wechat_redirect

	- UV异常率

		- UV 异常率 = 发生异常退出或崩溃的 UV / 登录 UV

	- 在微信我们可以做到 5 分钟级别的崩溃预警，确保能在第一时间发现线上重大问题，尽快决定是通过发版还是动态热修复解决问题。

		- 在解决崩溃的过程，也要做到由点到面，不能只针对这个崩溃去解决，而应该要考虑这一类崩溃怎么解决和预防。

### 2. ANR

- 我们怎么去发现应用中的 ANR 异常

	- 1. 使用 FileObserver 监听 /data/anr/traces.txt 的变化

		- hardcoder

			https://mp.weixin.qq.com/s/9Z8j3Dv_5jgf7LDQHKA0NQ?

	- 2. 监控消息队列的运行时间

- 我们先看看都有哪些应用退出的情形

	主动自杀。Process.killProcess()、exit() 等。崩溃。出现了 Java 或 Native 崩溃。系统重启；系统出现异常、断电、用户主动重启等，我们可以通过比较应用开机运行时间是否比之前记录的值更小。被系统杀死。被 low memory killer 杀掉、从系统的任务管理器中划掉等。ANR。

	- 

### 工具

- Breakpad

### 3. 内存优化

- 两个问题

	- 异常

		- OOM

		- 应用被杀死

		- 设备重启

	- 卡顿

	- 两个误区

		- 误区一：内存占用越少越好

			- Android 8.0 还新增了硬件位图 Hardware Bitmap，它可以减少图片内存并提升绘制效率。

		- 误区二：Native内存不用管

- LPDDR RAM

- Bitmap

	- 既然讲到了将图片的内存放到 Native 中，我们比较熟悉的是 Fresco 图片库在 Dalvik 会把图片放到 Native 内存中。

- 测量方法

	- 1. Java 内存分配

## x.2 高效开发

## x.3 架构演进

## x.3 练习Sample跑起来

## x.4 特别放送

## x.5 结束语

## 1. UI

### 1.1 约束布局-ConstraintLayout

## 2. 性能最佳实践 

## 3. Android文章研读

## 4. Java基础补漏

### 4.1 Java NIO

### 4.2 Java多线程



### 4.3 Java Stream



## 5. Android技术栈

### 5.1 Activity

#### 5.1.1 Fragment

#### 5.1.2 Activity

Activity基础

#### 5.1.3 Layout

#### 5.1.4 Views

##### a. View Binding

[Android View Binding的使用]("https://www.jianshu.com/p/66728b95baaa")

> View Binding是Android Studio 3.6推出的新特性，目的是为了替代findViewById(内部实现还是使用findViewById)。。在启动视图绑定后，系统会为改模块中的每个xml文件生成一个绑定类，绑定类的实例包含对在相应布局中具有 ID 的所有视图的直接引用。
>
> 
>
> View Binding 的优点
>
> - **Null 安全**：由于视图绑定会创建对视图的直接引用，因此不存在因视图 ID 无效而引发 Null 指针异常的风险。此外，如果视图仅出现在布局的某些配置中，则绑定类中包含其引用的字段会使用 `@Nullable` 标记。
> - **类型安全**：每个绑定类中的字段均具有与它们在 XML 文件中引用的视图相匹配的类型。这意味着不存在发生类转换异常的风险。

启用View Binding:

```groovy
android {
        ...
        viewBinding {
            enabled = true
        }
    }
} 
```

为用视图绑定功能后，系统会为该模块中包含的每个 XML 布局文件生成一个绑定类。这个类的类名是以xml布局文件名去掉下换线后，单词首字母大写加上Binding命名的。如activity_main.xml生成的类ActivityMainBinding.

- 如何在Activity中设置绑定，请在 Activity 的 onCreate() 方法中执行以下步骤：
  1. 调用生成的绑定类中包含的静态 `inflate()` 方法。此操作会创建该绑定类的实例以供 Activity 使用。
  2. 通过调用 `getRoot()` 方法或使用 [Kotlin 属性语法](https://links.jianshu.com/go?to=https%3A%2F%2Fkotlinlang.org%2Fdocs%2Freference%2Fproperties.html%23declaring-properties)获取对根视图的引用。
  3. 将根视图传递到 setContentView()，使其成为屏幕上的活动视图。



**Activity中使用ViewBinding**

```Java
		@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //关键代码
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View rootView = binding.getRoot();
        setContentView(rootView);
        //如何使用
        binding.textView.setText("这是修改的");
        binding.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("Main","点击了按钮");
            }
        });

    }
```

Fragment中使用ViewBinding

```Java
	// 第一种方法
	@Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
      binding = FragmentMyBinding.inflate(inflater, container, false);
      return binding.getRoot();
  }

  @Override
  public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
      super.onViewCreated(view, savedInstanceState);
      binding.textView.setText("这是Fragment");
      binding.button.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              Log.d("Fragment", "点击了按钮");
          }
      });
  }
  
  //第二种方法
  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
      return inflater.inflate(R.layout.fragment_my,container,false);
  }

  @Override
  public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
      super.onViewCreated(view, savedInstanceState);
      FragmentMyBinding binding = FragmentMyBinding.bind(view);
      this.binding = binding;

      binding.textView.setText("这是Fragment");
      binding.button.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              Log.d("Fragment", "点击了按钮");
          }
      });
  }
```

##### b. Data Binding

1. 使用data binding

   1.1 app/build.gradle

   ```groovy
   android {
           ...
           dataBinding {
               enabled = true
           }
       }
   ```

   1.2 在你想要编写dataBinding的视图xml文件根目录下按住alt+enter 

   选中Convert to data binding layout即可自动转变成DataBinding的xml样式

   [如何使用DataBinding例子](https://blog.csdn.net/qq1161857279/article/details/106325798/ "如何使用DataBinding")

##### c. RecyclerView

#### 5.1.5 Activity Task Infinity

##### BackStack

`栈顶`的概念：只要是页面刚打开，不管他是什么模式的，它都是栈顶页面。

`栈底`的概念：首先打开的页面位于最底下，然后后面打开的页面依次往上堆。只有最底下的页面关闭，程序才算完成关闭。



##### taskAffinity

`task`是一个具有栈结构的对象，一个task可以管理多个activity实例，启动一个应用，也就创建一个与之对应的task。task里面的activity是按照先进后出的形式保存的。比如：你打开一个页面，在页面中打开另一个页面，另一个页面退出后，返回的是第一个打开的页面。这就是任务栈的简单原理。

##### android:launchMode

在多个Activity跳转的过程中扮演着非常重要的作用，它可以决定是否生成新的activity实例，是否重用已存在的activity实例，是否和其他的activity实例公用一个task（任务栈）。

`standard`

`singleTop`

`singleTask`

`singleInstance`



#### 5.1.6 Android Process





### 5.2 Service

### 5.3 Broadcast Receiver

### 5.4 Content Provider

### 5.5 Intent

### 5.6 Application

### 5.7 RxJava

### 5.8 自定义View

### 5.9 事件体系

- 触摸

- 滑动

- 传递

### 5.10 绘制流程

- Measure Spec

- Measure

- layout

- draw

### 5.11 动画机制

逐帧动画

补间动画

贝塞尔曲线

### 5.12 Logger

[Orhanobut Logger]("https://github.com/orhanobut/logger")

**Setup**

Download

```java
implementation 'com.orhanobut:logger:2.2.0'
```

**Initialize**

```java
Logger.addLogAdapter(new AndroidLogAdapter());
```

**And use**

```java
Logger.d("hello");
```

**Options**

```java
Logger.d("debug");
Logger.e("error");
Logger.w("warning");
Logger.v("verbose");
Logger.i("information");
Logger.wtf("What a Terrible Failure");
```

**String format arguments are supported**

```java
Logger.d("hello %s", "world");
```

**Collections are supported (only available for debug logs)**

```java
Logger.d(MAP);
Logger.d(SET);
Logger.d(LIST);
Logger.d(ARRAY);
```

**Json and Xml support (output will be in debug level)**

```java
Logger.json(JSON_CONTENT);
Logger.xml(XML_CONTENT);
```

**Advanced**

```java
FormatStrategy formatStrategy = PrettyFormatStrategy.newBuilder()
  .showThreadInfo(false)  // (Optional) Whether to show thread info or not. Default true
  .methodCount(0)         // (Optional) How many method line to show. Default 2
  .methodOffset(7)        // (Optional) Hides internal method calls up to offset. Default 5
  .logStrategy(customLog) // (Optional) Changes the log strategy to print out. Default LogCat
  .tag("My custom tag")   // (Optional) Global tag for every log. Default PRETTY_LOGGER
  .build();

Logger.addLogAdapter(new AndroidLogAdapter(formatStrategy));
```

**Loggable**

Log adapter checks whether the log should be printed or not by checking this function. If you want to disable/hide logs for output, override `isLoggable` method. `true` will print the log message, `false` will ignore it.

```java
Logger.addLogAdapter(new AndroidLogAdapter() {
  @Override public boolean isLoggable(int priority, String tag) {
    return BuildConfig.DEBUG;
  }
});
```

**Save logs to the file**

//TODO: More information will be added later

```java
Logger.addLogAdapter(new DiskLogAdapter());
```

**Add custom tag to Csv format strategy**

```java
FormatStrategy formatStrategy = CsvFormatStrategy.newBuilder()
  .tag("custom")
  .build();
  
Logger.addLogAdapter(new DiskLogAdapter(formatStrategy));
```

![原理](/Users/guojialin/Workspace/Android-Projects/Relearn-Android/md-files/logger_how_it_works.png)

### 5.12 屏幕

### 5.13 API适配原理

### 5.14 Material Design？

### 5.14 资源访问

### 5.15 不同语言

### 5.16 API适配原理

### 5.17 Handler的使用

### 5.18 数据存储

- SharedPreference

- SQLite

	- SQL

	- 索引

	- 表Join

	- GreenDao

	- ORM

	- 数据库锁

- 文件存储

### 5.19 系统API使用

### 5.20 异步处理技术

- Thread

- Async Task

- Handler Thread

- Intent Service

### 5.21 网络框架

- httpClient

- OkHttp

- retrofit

### 5.22 多媒体技术

- 系统API及特性

- 图片

  - Glide

  - Picasso

  - Fresco

  - UniversalImageLoader


### 5.23 视频播放器

- FFMPEG

  - 应用

- 图片压缩原理

- 录制编辑机型适配

- 音视频编码原理

- SurfaceView特性

### 5.24 编程范式

- RxJava

### 5.25 文件下载

- DownloadManager

### 5.26 构建与版本控制

### 5.27 Gradle

- 共享变量的定义

- aar函数库的应用

- 签名与混淆

### 5.28 Java进阶

- 泛型、反射、枚举、字节码

- 多线程、虚拟机特性、并发机制、集合原理

### 5.29 View进阶

#### 事件分发

#### 滑动冲突

#### 自定义View

#### Drawable

```xml
<?xml version="1.0" encoding="utf-8"?>
<selector xmlns:android="http://schemas.android.com/apk/res/android">
    <item android:drawable="@drawable/white_color" android:state_pressed="true" />
    <item android:drawable="@drawable/blue_color" android:state_pressed="false" />
</selector>
```

<img src="/Users/guojialin/Workspace/Android-Projects/Relearn-Android/md-files/drawable_attrs.png" alt="Drawable Types" style="zoom:80%;" />

<img src="/Users/guojialin/Workspace/Android-Projects/Relearn-Android/md-files/drawable_states.png" alt="drawble attrs" style="zoom:80%;" />

**constantSize**

表示StateListDrawable的固有大小是否会随着状态的改变而改变，状态的切换会导致切换到不同的Drawable，而不同的Drawable有不同的固有大小。此属性是一个布尔值，true表示StateListDrawable的固有大小保持不变，StateListDrawable的固有大小为Drawable集合的固有大小的最大值，false表示StateListDrawable的固有大小根据Drawable的不同而改变。默认为false。

**dither**

开启抖动效果，开启后可以让图片在低质量的屏幕上仍然有较好的显式效果，默认为true

**variablePadding**

StateListDrawable的padding属性是否会根据状态的改变而改变，true表示会根据状态的改变而改变，false表示不会跟随状态的改变而改变，此时StateListDrawable的padding的最大值，是Drawable集合的最大值。默认为false，不建议开启。

**Item**

代表View的状态和与状态相对应的Drawable。一个View主要以下的状态，每一个状态都是一个布尔值。

| 状态                                                         | 描述                                                         |
| ------------------------------------------------------------ | ------------------------------------------------------------ |
| [android:state_activated](https://developer.android.google.cn/reference/android/graphics/drawable/StateListDrawable.html#attr_android:state_activated) | 表示View是否处于激活状态                                     |
| [android:state_active](https://developer.android.google.cn/reference/android/graphics/drawable/StateListDrawable.html#attr_android:state_active) | 当一个View被视为是活动的                                     |
| [android:state_checkabl](https://developer.android.google.cn/reference/android/graphics/drawable/StateListDrawable.html#attr_android:state_checkable) | 是否是可以被想选中的，用于ChekBox                            |
| [android:state_checked](https://developer.android.google.cn/reference/android/graphics/drawable/StateListDrawable.html#attr_android:state_checked) | CheckBox/RadioButton是否被选中                               |
| [android:state_enabled](https://developer.android.google.cn/reference/android/graphics/drawable/StateListDrawable.html#attr_android:state_enabled) | View 是否是可用状态                                          |
| [android:state_first](https://developer.android.google.cn/reference/android/graphics/drawable/StateListDrawable.html#attr_android:state_first) | 当一个视图为有序列表的第一个元素的时候                       |
| [android:state_focused](https://developer.android.google.cn/reference/android/graphics/drawable/StateListDrawable.html#attr_android:state_focused) | 当View获得焦点的时候                                         |
| [android:state_last](https://developer.android.google.cn/reference/android/graphics/drawable/StateListDrawable.html#attr_android:state_last) | 当一个视图为有序列表的最后一个元素的时候                     |
| [android:state_middle](https://developer.android.google.cn/reference/android/graphics/drawable/StateListDrawable.html#attr_android:state_middle) | 当View位于有序集合的中间位置时                               |
| [android:state_pressed](https://developer.android.google.cn/reference/android/graphics/drawable/StateListDrawable.html#attr_android:state_pressed) | View是否被按压                                               |
| [android:state_selected](https://developer.android.google.cn/reference/android/graphics/drawable/StateListDrawable.html#attr_android:state_selected) | View是否被选中                                               |
| [android:state_single](https://developer.android.google.cn/reference/android/graphics/drawable/StateListDrawable.html#attr_android:state_single) | State value for StateListDrawable, set when a view or drawable is considered "single" by its host. |
| [android:state_window_focused](https://developer.android.google.cn/reference/android/graphics/drawable/StateListDrawable.html#attr_android:state_window_focused) | 当Window获得焦点时                                           |

##### shape

```xml
<?xml version="1.0" encoding="utf-8"?>  
<!--定义一个带圆角,白色背景,蓝色边框的矩形-->  
<shape xmlns:android="http://schemas.android.com/apk/res/android"  
    android:shape="rectangle">  
    <!-- 圆角 -->  
    <corners android:radius="5dp" />  
    <!--填充颜色-->  
    <solid android:color="@color/white" />  
    <!-- 描边 -->  
    <stroke
        android:width="1dp"  
        android:color="@color/blue" />  
</shape>  
```



### 5.30 Android注解

- 标准注解

- Retrofit

- Dagger2

### 5.31 设计模式

- 23种设计模式

- 熟悉SOLID设计原则

- 架构

  - MVC
  - MVP
  - MVVM

### 5.32 版本特性

- 权限管理

  - 各版本权限差异变化

- doze模式：了解Android的打盹模式

### 5.33 IPC模式

- Android多进程模式

- 基础概念：序列化和Binder

- 跨进程通信模式

  - Bundle

  - 文件共享

  - Messager

  - AIDL

  - ContentProvider

  - Socket

### 5.34 JNI & NDK

- 性能优化

  - 布局优化

  - 绘制优化

  - listview和bitmap优化

  - 内存泄露优化

  - 响应速度优化

  - ANR日志分析

  - 线程优化

### 5.35 自动化构建

- Jenkins + Gradle持续集成

### 5.36 热修复方案

- 基本流程和原理

- Sophix

- Tinker

- Robust

- Amigo

### 5.37 AOP面向切面编程

- AspectJ

### 5.38 AndroidX

[AndroidX官方文档](https://developer.android.com/jetpack/androidx)

[AndroidX API]("https://developer.android.com/reference/androidx/packages")

#### 5.38.1 AndroidX的系统性学习

##### Support包的兼容性：[文档](https://developer.android.com/topic/libraries/support-library#api-versions)

Support Library release 26.0.0 (July 2017) to Android 4.0 (**API level 14**) 

```shell
gradle -q dependencies your-app-project:dependencies
```

**最后一个版本：修订版 28.0.0 正式版**（2018 年 9 月 21 日）

该版本是支持库 28.0.0 的稳定版，适合在生产环境中使用。这将是 `android.support` 包下的最后一次功能发布，建议开发者迁移到 [AndroidX](https://developer.android.com/jetpack/androidx)。



##### Android X与Support包的映射关系

[包映射关系](https://developer.android.com/jetpack/androidx/migrate/artifact-mappings)

[类映射关系](https://developer.android.com/jetpack/androidx/migrate/class-mappings)

##### AndroidX支持的最低版本是多少？

参考Support包

##### AndroidX的编译版本最低是多少？

如果想使用AndroidX创建一个新的项目，需要将SDK编译版本设置为Android9.0 (28)以上



##### refrences

[Support 迁移到 AndroidX](https://www.jianshu.com/p/41de8689615d)

[理解性简单阐述](https://blog.csdn.net/weixin_39069034/article/details/100169879)

[Support历史阐述](https://juejin.cn/post/6898150034353684487)

#### 5.38.2 androidx.work

[AndroidX Work | WorkManager]("https://developer.android.google.cn/reference/androidx/work/package-summary")

注意 `@data` annotation



#### 5.39 沧海拾遗

```xml
tools:srcCompat
```



### 5.40 组件化

[CC渐进式组件化]("https://github.com/luckybilly/CC")



## 6. 大前端拓展

### 6.1 React Native

### 6.2 Flutter

### 6.3 Hybrid混合开发

#### 6.3.1 WebView与JS互相调用

### 6.4 小程序技术

## 7. Kotlin





## 8. Android Studio Treaks



## 9. 兼容性话题

[兼容性官方文档](https://developer.android.com/guide/practices/compatibility)





## Appendix

### 1 Awsome Androids

[1. Awsome Android]("https://github.com/JStumpp/awesome-android")

### 2 Awsome Projects



### 3 Best Practice



### 4 语义化版本控制

https://semver.org/lang/zh-CN/

