<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2022/6/9
  Time: 下午 6:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>学生成绩新增</title>
    <script src="js/axios.min.js"></script>
    <script>
        //获取班级列表
        window.addEventListener("load",function() {
            axios.get("exam?opr=getClassList").then(function(res) {
                var options = document.getElementById("classes").options;
                let classes = res.data;
                options.add(new Option("---请选择---",""));
                for (let cla of classes) {
                    options.add(new Option(cla.claname,cla.id));
                }
            }).catch(function(e){
                alert("服务器繁忙")
            })
        });
        //获取学科列表
        window.addEventListener("load",function() {
            axios.get("exam?opr=getSubjectList").then(function(res) {
                var options = document.getElementById("subject").options;
                let subs = res.data;
                options.add(new Option("---请选择---",""));
                for (let sub of subs) {
                    options.add(new Option(sub.subname,sub.id));
                }
            }).catch(function(e){
                alert("服务器繁忙")
            })
        });

    </script>
</head>
<body>
<form action="exam?opr=addScore" method="post">
    <table>
        <tr>
            <td>班级</td>
            <td><select id="classes" name="classes"></select></td>
        </tr>
        <tr>
            <td>科目</td>
            <td><select id="subject" name="subject"></select></td>
        </tr>
      <tr>
        <td>学生</td>
        <td><select id="student" name="student"></select></td>
      </tr>
      <tr>
        <td>成绩</td>
        <td><input type="text" name="score"/></td>
      </tr>
      <tr>
        <td colspan="2">
          <input type="submit" value="提交"/>
        </td>
      </tr>
    </table>
  </form>

<p style="color:red">${success}</p>

<script>
    //获取学生列表
    document.getElementById("classes").addEventListener("change",function() {
        let classId = document.getElementById("classes").value;
        axios.get("exam?opr=getStudentList&classId="+classId).then(function(res) {
            var options = document.getElementById("student").options;
            options.length = 0;
            let stus = res.data;
            options.add(new Option("---请选择---",""));
            for (let stu of stus) {
                options.add(new Option(stu.name,stu.id));
            }
        }).catch(function(e){
            alert("服务器繁忙")
        })
    });
</script>
</body>
</html>
