# thrift
apache thrift rpc



# 一、apache thrift rpc 介绍

[Thrift官网](http://thrift.apache.org/)；
[Thrift的github](https://github.com/apache/thrift)。

# 二、thrift rpc 使用注意事项

1、编写服务文件

2、生成Java接口
cd到`.thrift`文件结尾的目录，使用命令：
```shell
thrift -r --gen java HelloService.thrift
```
而后可以看到生成了Java接口文件。

特别注意：
**在macOS上使用`Homebrew`安装`thrift`会比较简单，但是是最新版（目前是`0.11.0`）**。而一般一些demo上的例子都是使用`thrift`的`0.9.3`版本。
如果命令中安装的是最新版本，则使用命令生成的接口文件**也需要使用相应的`thrift`版本，否则会有类型转换或者抛错类型不匹配的问题。**

`0.11.0`版thrift的maven坐标：

```xml
    <properties>
        <lib.thrift.libfb303>0.9.3</lib.thrift.libfb303>
        <lib.thrift.version>0.11.0</lib.thrift.version>
    </properties>

    <!-- apache thrift -->
    <dependency>
      <groupId>org.apache.thrift</groupId>
      <artifactId>libfb303</artifactId>
      <version>${lib.thrift.libfb303}</version>
    </dependency>
    <dependency>
      <groupId>org.apache.thrift</groupId>
      <artifactId>libthrift</artifactId>
      <version>${lib.thrift.version}</version>
    </dependency>

```

**`libfb303`库使用什么版本不是关键，`org.apache.thrift`必须使用和生成接口文件一致的版本！**


# 三、参考：

[Mac OS X 下搭建thrift环境](https://www.cnblogs.com/smartloli/p/4220545.html)；
[Apache Thrift极简入门](https://www.jianshu.com/p/89a4ba971a48)
[Thrift框架学习笔记 IOS/MAC中使用Thrift框架](https://blog.csdn.net/xiaofei125145/article/details/52241803)；
[brew安装指定版本thrift](https://www.jianshu.com/p/aadb54eac0a8);
[mac上安装thrift0.9.3](http://www.zhimengzhe.com/mac/134615.html)