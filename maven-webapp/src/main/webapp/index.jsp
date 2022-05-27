<%@ page import="java.util.Arrays" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<head>
    <title>jsp脚本元素练习</title>
    <style>
        table{
            height: 200px;
            border: 1px solid;
        }
        td{
            width: 200px;
            text-align: center;
        }
    </style>
</head>

<body>
    <p>用两个一维数组存储姓名和成绩,隔行输出</p>
    <%! String[] arrName = new String[]{"张三","李四","王五"};%>
    <%! int[] arrScore = new int[]{60,70,80};%>
    <%
        for(int i=0;i<arrName.length;i++){
            out.println(arrName[i] + arrScore[i] + "<br>");
        }
    %>


    <p>用一个数存储两个数,输出两数的最大,最小,平均,差值</p>
    <%! int[] arrScore2 = new int[]{70,80};%>
    <%
        out.println("最大值为:"+(arrScore2[0]>arrScore2[1]?arrScore2[0]:arrScore2[1])+"<br>");
        out.println("最小值为:"+(arrScore2[0]<arrScore2[1]?arrScore2[0]:arrScore2[1])+"<br>");
        out.println("两数之差:"+(Math.abs(arrScore2[0]-arrScore2[1]))+"<br>");
        out.println("两数平均:"+((arrScore2[0]+arrScore2[1])/2)+"<br>");
    %>


    <p>根据身份证显示生日</p>
    <table>
        <table border="1" cellpadding="0" cellspacing="0">
            <tr>
                <td>身份证号</td>
                <td>出生日期</td>
            </tr>
            <tr>
                <td>
                    <%!
                        String num1 = "120225199804255946";
                    %>
                    <%=num1%>
                </td>
                <td>
                    <% out.println(num1.substring(6, 10)+"-"+num1.substring(10,12)+"-"+num1.substring(12, 14)); %>
                </td>
            </tr>

            <tr>
                <td>
                    <%!
                        String num2 = "120225198701230987";
                    %>
                    <%=num2%>
                </td>
                <td>
                    <% out.println(num2.substring(6, 10)+"-"+num2.substring(10,12)+"-"+num2.substring(12, 14)); %>
                </td>
            </tr>
        </table>
    </table>



    <form name="myForm" action="print" method="post">
        <span>请输入姓名:</span><input type="text" name="info">
        <input type="submit" value="提交" >
    </form>

    <script>
        function fun(){
            alert()
        }
    </script>

</body>
</html>
