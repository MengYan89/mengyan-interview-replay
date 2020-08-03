# 面试复盘第三篇
 [返回首页](../README.md)  
描述是50-150人的公司。    
# 目录
- [Java](#Java)
    - [计算表达式](#计算如下表达式)  
    - [请写出如下表达式的输出结果]()  
    - [int数据类型能够表示的大小范围]()  
    - [HashTable,HashMap,TreeMap的区别]()
    - [System.gc()方法的作用是什么？]()  
    - [synchronized和Lock线程安全锁的区别]()
    - [简述分布式集群状态下实现资源线程安全方式有哪些？]()
    - [递归方法]()   
- [数据库题目]()  
    - [查询出平均成绩最高的班级及其平均分]()  
    - [查询出每个班级分数最高的学生]()  
    - [如何在mysql中查看一条sql的执行情况,并简要列举sql优化的场景]()  

# Java  
## 计算如下表达式  
```
第一题
3 & 1
```
与运算符(&)  
运算规则：  
0&0=0  
0&1=0  
1&0=0  
1&1=1  
将上面的表达式转为二进制：  
```
11 & 01 = 01
所以
3 & 1 = 1
```  

```
第二题
3 | 1
```
或运算符(|)  
运算规则：  
0|0=0  
0|1=1  
1|0=1  
1|1=1  
将上面的表达式转为二进制：  
```
11 | 01 = 11
所以
3 | 1 = 3
```

## 请写出如下表达式的输出结果
```java
第一题
String string = null;
boolean result = (string + "welcome").equals("welcome");
System.out.println(result);
```
输出是false  
解析:这道题我们可以将string+"welcome"打印出来看一下:  
```java
System.out.println((string+"welcome"));
```
会发现结果是nullwelcome，所以equals的结果当然是false。  
而为什么会这样呢，因为java在进行相加操作的时候会默认使用StringBuilder进行优化，可以在字节码中看到：  
```
public static void main(java.lang.String[]);
    descriptor: ([Ljava/lang/String;)V
    flags: ACC_PUBLIC, ACC_STATIC
    Code:
      stack=2, locals=3, args_size=1
         0: aconst_null
         1: astore_1
         2: new           #2                  // class java/lang/StringBuilder
         5: dup
         6: invokespecial #3                  // Method java/lang/StringBuilder."<init>":()V
         9: aload_1
        10: invokevirtual #4                  // Method java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        13: ldc           #5                  // String welcome
        15: invokevirtual #4                  // Method java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        18: invokevirtual #6                  // Method java/lang/StringBuilder.toString:()Ljava/lang/String;
        21: invokevirtual #7                  // Method java/lang/String.intern:()Ljava/lang/String;
        24: ldc           #5                  // String welcome
        26: invokevirtual #8                  // Method java/lang/String.equals:(Ljava/lang/Object;)Z
        29: istore_2
        30: getstatic     #9                  // Field java/lang/System.out:Ljava/io/PrintStream;
        33: iload_2
        34: invokevirtual #10                 // Method java/io/PrintStream.println:(Z)V
        37: return
      LineNumberTable:
        line 5: 0
        line 6: 2
        line 7: 30
        line 8: 37
}
```  
而在StringBuilder中append(String str)中有对null进行特殊处理:  
```java
//针对 String 对象
public AbstractStringBuilder append(String str) {
    if (str == null)
        return appendNull();
　　int len = str.length();
　　ensureCapacityInternal(count + len);
　　str.getChars(0, len, value, count);
　　count += len;
　　return this;
}
```
然后我们看一下appendNull：  
```java
private AbstractStringBuilder appendNull() {
　　int c = count;
　　ensureCapacityInternal(c + 4);
　　final char[] value = this.value;
　　value[c++] = 'n';
　　value[c++] = 'u';
　　value[c++] = 'l';
　　value[c++] = 'l';
　　count = c;
　　return this;
　　}
```
会发现如果字符串为null就会填充null进去。  
**相关知识:常量折叠**  

```java
第二题
String str1 = "welcome";
String str2 = "welcome";
System.out.println(str1 == str2);
```
输出是true  
jvm会将直接赋值的字符串加入常量池,所以这两个String的内存地址是一致的输出true  
```java
第三题
Integer int1 = 1;
Integer int2 = 1;
System.out.println(int1 == int2);
```
输出是true  
因为java缓存了-128到127的数字所以这两个int的内存地址相同  

```java
第四题
Integer int1 = 128;
Integer int2 = 128;
System.out.println(int1 == int2);
```
输出是false  
和上一题一样由于128不在缓存区间所以内存地址不同  
