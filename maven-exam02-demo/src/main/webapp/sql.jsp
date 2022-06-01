<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2022/6/1
  Time: 下午 8:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>sql语句</title>
</head>
<body>
    <h1>数据库sql语句</h1>
    create table student(
    id int primary key auto_increment,
    name varchar(20),
    telephone varchar(20)
    );

    create table teacher(
    id int primary key auto_increment,
    name varchar(20),
    telephone varchar(20)
    );

    create table topic(
    id int primary key auto_increment,
    name varchar(20),
    student_id int,
    teacher_id int
    );

    insert into student(name, telephone) VALUES
    ('赵日天','123456767'),('李杀神','123456767'),('刘斩仙','123456767'),('王诛魔','123456767');


    insert into teacher(name, telephone) VALUES
    ('海绵宝宝','123456767'),('章鱼哥','123456767'),('蟹老板','123456767'),('大洋游侠','123456767');

    insert into topic(name, student_id, teacher_id) VALUES
    ('光的传播路径研究',1,2),
    ('如何变成光',3,4),
    ('如果把光传给奥特曼',2,1),
    ('光的传播速度研究',2,2),
    ('怎么阻挡光的传播',4,3);
</body>
</html>
