# 面试复盘第四篇
 [返回首页](../README.md)  
描述是50-150人规模的公司，题目Java基础偏多，大部分是在问一些坑。    
# 目录
- [选择题](#选择题)  
    * [精度相关](#精度相关)  
    * [方法重载](#方法重载)  
    * [switch](#switch)
- [简答题](#简答题)  


# 选择题  
## 精度相关
1.以下代码输出结果是什么？请选择(A)
```java
public class Test01 {
    public static void main(String[] args) {
        float wb_freight = 4620.125f;
        double wb_freight_gas = 4620.125d;
        System.out.println((wb_freight - wb_freight_gas) == 0.0);
    }
}
```
A.true B.false  

2.以下代码中两个输出结果result01与result02是否相等？请选择(B)  
```java
public class Test02 {
    public static void main(String[] args) {
        double salary1 = 0.8;
        double salary2 = 0.7;
        double salary3 = 0.6;
        double result01 = salary1 - salary2;
        double result02 = salary2 - salary3;
        System.out.println(result01);
        System.out.println(result02);
    }
}
```
A.相等 B.不相等  
解析：这两题主要考察的是浮点数的精度问题,由于在计算机中是使用二进制进行计算,在小数转换为二进制的时候出现了精度损失,就像十进制无法精确表示1/3一样，
所以在相减或者相加的时候会无限趋近于结果，但如果两个相同的浮点数进行相减由于二进制表达也相同所以能够获得精准的0.0  
自己写的csdn文章:[浮点数在计算机中的储存与运算]() (待填坑)
## 方法重载
3.以下代码中有两个重载方法overloadTest01(String s)与overloadTest01(Integer i)那么执行main函数overloadTest01(null)会调用哪个方法？请选择(D)
```java
public class Test03 {
    public static void main(String[] args) {
        Test03 iv = new Test03();
        iv.overloadTest01(null);
    }
    private void overloadTest01(String s) {
        System.out.println("String="+s);
    }
    private void overloadTest01(Integer i) {
        System.out.println("Integer"+i);
    }
}
```
A.overloadTest01(String s) B.overloadTest01(Integer i) C.随机调用 D.编译出错  
解析:这道题答案是D,编译器报错ambiguous method call.both(引用不明确)  
但是如果在jdk7以前的环境应该还会有不同的答案。  

4.以下代码有两个重载方法void overloadTest02(Integer i)与void overloadTest02(double d)，那么执行main函数overloadTest02(6)会调用哪个方法？请选择(A)  
```java
public class Test04 {
    public static void main(String[] args) {
        Test04 vi = new Test04();
        vi.overloadTest02(6);
    }
    private void overloadTest02(Integer i) {
        System.out.println("Integer="+i);
    }
    private void overloadTest02(double d) {
        System.out.println("double="+d);
    }
}
```
A.overloadTest02(double d) B.overloadTest02(Integer i) C.随机调用 D.编译出错  
解析:待补充

## switch  
以下代码会匹配switchTest函数case中的哪一项？请选择(D)
```java
public class Test05 {
    public static void main(String[] args) {
        String s = null;
        iv.switchTest(s);
    }
    private void switchTest(String str) {
        switch (str) {
            case "1":
                System.out.println("1");
                break;
            case "2":
                System.out.println("2");
                break;
            case "null":
                System.out.println("null");
                break;
            default:
                System.out.println("default");
                break;
        }
    }
}
```
A."null" B."default" C.不与任何东西匹配，但不抛出异常 D.直接抛出异常  
解析:这个题就没有一个地方是对的，虽然答案选的是D，但如果选项有无法通过编译，肯定是选无法通过编译。如果创建iv对象，使用的jdk支持switch使用字符串
那会报空指针异常。  


