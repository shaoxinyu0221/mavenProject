<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2022/6/7
  Time: 下午 7:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>三级联动菜单</title>
    <script src="js/axios.min.js"></script>
</head>
<body>
省:<select name="province" id="province"></select>
市:<select name="city" id="city"></select>
区:<select name="area" id="area"></select>


<script>
    window.addEventListener("load",function(){
        axios.get("code").then(function(result){
            let provs = result.data;
            let options = document.getElementById("province").options;
            options.add(new Option("---请选择---",""));
            for (let province of provs) {
                options.add(new Option(province.content,province.code));
            }
        }).catch(function(e){
            alert("服务器繁忙")
        })
    })

    document.getElementById("province").addEventListener("change",function(){
        let code = document.getElementById("province").value;
        axios.get("code?superior="+code).then(function(result){
            let citys = result.data;
            let options = document.getElementById("city").options;
            document.getElementById("city").length = 0;
            document.getElementById("area").length = 0;
            for (let city of citys) {
                options.add(new Option(city.content,city.code));
            }
        }).catch(function(e){
            alert("服务器繁忙")
        })
    })

    document.getElementById("city").addEventListener("change",function(){
        let code = document.getElementById("city").value;
        axios.get("code?superior="+code).then(function(result){
            let areas = result.data;
            let options = document.getElementById("area").options;
            document.getElementById("area").length = 0;
            for (let area of areas) {
                options.add(new Option(area.content,area.code));
            }
        }).catch(function(e){
            alert("服务器繁忙")
        })
    })
</script>
</body>
</html>
