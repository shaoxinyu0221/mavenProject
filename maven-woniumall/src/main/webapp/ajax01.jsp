<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>测试Ajax</title>
</head>
<body>
    <div>
        <p>请输入账号:<input type="text" name="account" id="account"
                        onblur="checkAccount()"></p>
    </div>

    <script>
        let xhr = new XMLHttpRequest();
        function checkAccount(){
            let account = document.getElementById("account").value;
            //建立连接
            xhr.open("GET","ajax?account="+account,true);

            //设置回调方法
            xhr.onreadystatechange = accountIsExist;

            //发送请求
            xhr.send(null);
        }

        function accountIsExist(){
            let acount = document.getElementById("account").value;
            let checkAcc = "${account}";
            if (xhr.readyState==4 && xhr.status==200){
                let result = xhr.responseText;
                alert(result);
            }
        }

    </script>
</body>
</html>
