# 面试复盘第一篇
 [返回首页](../README.md)  
这是一家规模在15人左右的小公司。  
# 目录
- [数据库试题](#数据库试题)
    * [查询一节课程比另一节课程高的所有学生的学号](#查询一节课程比另一节课程高的所有学生的学号)
    * [查询平均成绩大于60分的同学的学号和平均成绩](#查询平均成绩大于60分的同学的学号和平均成绩)
    * [查询姓李的老师的个数](#查询姓李的老师的个数)
    * [查询各科成绩最高分和最低分](#查询各科成绩最高分和最低分)
    * [删除某学号同学的某科课程的成绩](#删除某学号同学的某科课程的成绩)
# 数据库试题
可以通过每个SQL代码块上的SQL超链接文本进入对应的sql文件。  
现有学生、课程、成绩、教师四张表。包含以下信息：  
1.学生表(STUDENT)  

| 字段名 | 注释 |  
| :-----:| :---: |  
| S_NO | 学号 |  
| S_NAME | 学生姓名 |
| S_AGE | 学生年龄 |  

2.课程表(COURSE)  

| 字段名 | 注释 |  
| :-----:| :---: |  
| C_NO | 课程编号 |
| C_NAME | 课程名称 |
| T_NO | 教师编号 |

3.成绩表(SC)  

| 字段名 | 注释 |  
| :-----:| :---: |  
| S_NO | 学号 |
| C_NO | 课程编号 |
| SCORE | 成绩 |

4.教师表(TEACHER)  

| 字段名 | 注释 |  
| :-----:| :---: |  
| T_NO | 教师编号 |
| T_NAME | 教师名字 |

## 查询一节课程比另一节课程高的所有学生的学号
    查询"111"课程比"112"课程成绩高的所有学生的学号。
一般来说有两种查询方法一种是用EXISTS的，一种是不用EXISTS的。  
**EXISTS**：用于鉴测子查询中是否至少返回一行数据，如果至少返回一行数据则返回True否则返回False。  
不用EXISTS的方法[SQL](sql/sql_1.sql):
```sql
SELECT
	a.S_NO 
FROM
	( SELECT * FROM SC WHERE C_NO = 111 ) a
	INNER JOIN 
	( SELECT * FROM SC WHERE C_NO = 112 ) b ON a.S_NO = b.S_NO 
WHERE
	a.SCORE > b.SCORE;
```
使用EXISTS的方法[SQL](sql/sql_2.sql):
```sql
SELECT
	t1.S_NO 
FROM
	SC t1 
WHERE
	t1.C_NO = 111 
	AND EXISTS (
SELECT
	1 
FROM
	SC t2 
WHERE
	t2.C_NO = 112 
	AND t1.S_NO = t2.S_NO 
	AND t1.SCORE > t2.SCORE 
	);
```
也许还有一种情况，比如SC表中有可能缺失数据比如

| S_NO | C_NO | SCORE |
| :-----:| :---: | :---: |  
| 1 | 111 | 110 |
| 1 | 112 | 90 |
| 2 | 111 | 90 |

这样2号学生就缺失了112科目的成绩，这样就有可能对查询造成影响。那么我们首先要使用STUDENT，COURSE，SC三张表将数据补全
将空的数据填补为0。
```sql
SELECT
	s.S_NO,
	c.C_NO,
	IFNULL( sc.SCORE, 0 ) AS SCORE 
FROM
	( STUDENT s, COURSE c )
	LEFT JOIN SC sc ON s.S_NO = sc.S_NO 
	AND sc.C_NO = c.C_NO 
GROUP BY
	s.S_NO,
	c.C_NO;
```

| S_NO | C_NO | SCORE |
| :-----:| :---: | :---: |  
| 1 | 111 | 110 |
| 1 | 112 | 90 |
| 2 | 111 | 90 |
| 2 | 112 | 0 |

然后在进行查询就是正确的结果，完整[SQL](sql/sql_3.sql):
```sql
SELECT
	t1.S_NO 
FROM
	(
  SELECT
	s.S_NO,
	c.C_NO,
	IFNULL( sc.SCORE, 0 ) AS SCORE 
  FROM
	  ( STUDENT s, COURSE c )
  LEFT JOIN SC sc ON s.S_NO = sc.S_NO 
	  AND sc.C_NO = c.C_NO 
  GROUP BY
	s.S_NO,
	c.C_NO 
	) t1 
WHERE
	t1.C_NO = 111 
	AND EXISTS (
          SELECT
	        1 
          FROM
	      (
            SELECT
	          s.S_NO,
	          c.C_NO,
	          IFNULL( sc.SCORE, 0 ) AS SCORE 
            FROM
	          ( STUDENT s, COURSE c )
	        LEFT JOIN SC sc ON s.S_NO = sc.S_NO 
	        AND sc.C_NO = c.C_NO 
            GROUP BY
	          s.S_NO,
	          c.C_NO 
	      ) t2 
          WHERE
	        t2.C_NO = 112 
	        AND t1.S_NO = t2.S_NO 
	        AND t1.SCORE > t2.SCORE 
	);
```
这个最后这个SQL是我的个人的理解感觉有很多可以优化的地方待改良，所以不建议生产环境下直接使用，请先进行效率测试。  
如果有测试结果或者优化建议希望能发评论或Issues大家一起讨论。
## 查询平均成绩大于60分的同学的学号和平均成绩
这个只要用GROUP BY对S_NO分组然后使用聚合函数AVG就可以得到结果了。  
[SQL](sql/sql_4.sql):
```sql
SELECT
	t1.S_NO,
	AVG( t1.SCORE ) AS AVG 
FROM
	SC t1 
GROUP BY
	t1.S_NO 
HAVING
	AVG( t1.SCORE ) > 60;
```
同样还是会存在上个问题时数据缺失的问题，如果缺失了一条数据求平均数的时候如果原本有4个科目会除以4，而缺失后会变成除以3。  
所以我们可以先使用子查询查询到科目的总数，然后除以SUM。  
获取科目的总数:
```sql
SELECT COUNT( 1 ) FROM COURSE
```
完整[SQL](sql/sql_5.sql):
```sql
SELECT
	s.S_NO,
	( SUM( s.SCORE ) / ( SELECT COUNT( 1 ) FROM COURSE ) ) AS AVG 
FROM
	SC s 
GROUP BY
	s.S_NO 
HAVING
	( SUM( s.SCORE ) / ( SELECT COUNT( 1 ) FROM COURSE ) ) > 60;
```
## 查询姓李的老师的个数
这个感觉没什么可说的，LIKE就完事了。  
[SQL](sql/sql_6.sql):
```sql
SELECT
	COUNT( 1 ) AS 数量 
FROM
	TEACHER T 
WHERE
	T.T_NAME LIKE '李%';
```
## 查询各科成绩最高分和最低分
    查询各科成绩最高分和最低分:以如下形式显示：课程ID，最高分，最低分。
首先还是不考虑数据缺失的情况，直接使用GROUP BY对C_NO进行分组然后使用MAX与MIN聚合函数。  
[SQL](sql/sql_7.sql):
```sql
SELECT
	t1.C_NO,
	MIN( t1.SCORE ) AS MIN_SCORE,
	MAX( t1.SCORE ) AS MAX_SCORE 
FROM
	SC t1 
GROUP BY
	t1.C_NO;
```
在面对数据缺失的情况还是像第一题一样先将数据补全然后在进行分组与聚合函数。  
[SQL](sql/sql_8.sql):
```sql
SELECT
	t.C_NO,
	MIN( t.SCORE ) AS MIN_SCORE,
	MAX( t.SCORE ) AS MAX_SCORE 
FROM
	(
    SELECT
	  s.S_NO,
	  c.C_NO,
	  IFNULL( sc.SCORE, 0 ) AS SCORE 
    FROM
	  ( STUDENT s, COURSE c )
	LEFT JOIN SC sc ON s.S_NO = sc.S_NO 
	                  AND sc.C_NO = c.C_NO 
    GROUP BY
	  s.S_NO,
	  c.C_NO 
	) t 
GROUP BY
	t.C_NO;
```
在写sql的时候发生了一些趣事，一开始建表的时候设置错了，将SC表中的SCORE设置为了字符串
这样在使用MAX与MIN的时候会出错，如果实际上出现了这种情况可以使用[CONVERT](https://blog.csdn.net/qq_23633427/article/details/107374449)，将字符串转换为数字。
## 删除某学号同学的某科课程的成绩
    删除'2'同学的'111'课程的成绩。
这个直接DELETE就行了。  
[SQL](sql/sql_9.sql):
```sql
DELETE 
FROM
	sc s 
WHERE
	s.S_NO = 2 
	AND s.C_NO = 111;
```