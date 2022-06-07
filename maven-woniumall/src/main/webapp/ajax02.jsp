<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>测试Ajax</title>
    <script src="js/axios.min.js"></script>
</head>
<body>
    <div>
        <p>请输入账号:<input type="text" name="account" id="account"></p>
        <span id="check"></span>
    </div>

    <script>
        //DOM2级事件注册
        document.getElementById("account").addEventListener("blur",function(){
            axios.get("ajax?account="+document.getElementById("account").value)
                .then(function(result){
                    alert(result.data)
                }).catch(function(e){
                    console.log(e);
                    alert("服务器维护中")
            })
        })
    </script>
</body>
</html>
