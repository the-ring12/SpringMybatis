<%--
  Created by IntelliJ IDEA.
  User: Tan
  Date: 2021/12/2
  Time: 9:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>网站数据</title>
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <script src="js/jquery-3.2.1.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script src="js/echarts.js"></script>
    <style>
        body {
            background-color: rgb(240, 242, 245);
        }
    </style>
</head>
<body>
<nav style="position:fixed;z-index: 999;width: 100%;background-color: #fff" class="navbar navbar-default"
     role="navigation">
    <div class="container-fluid">
        <div class="navbar-header" style="margin-left: 8%;margin-right: 1%">
            <a class="navbar-brand" href="admin_main.html">图书管理系统</a>
        </div>
        <div class="collapse navbar-collapse">
            <ul class="nav navbar-nav navbar-left">
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                        图书管理
                        <b class="caret"></b>
                    </a>
                    <ul class="dropdown-menu">
                        <li><a href="allbooks.html">全部图书</a></li>
                        <li class="divider"></li>
                        <li><a href="book_add.html">增加图书</a></li>
                    </ul>
                </li>
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                        读者管理
                        <b class="caret"></b>
                    </a>
                    <ul class="dropdown-menu">
                        <li><a href="allreaders.html">全部读者</a></li>
                        <li class="divider"></li>
                        <li><a href="reader_add.html">增加读者</a></li>
                    </ul>
                </li>
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                        借还管理
                        <b class="caret"></b>
                    </a>
                    <ul class="dropdown-menu">
                        <li><a href="lendlist.html">借还日志</a></li>
                    </ul>
                </li>
                <li>
                    <a href="admin_repasswd.html">
                        密码修改
                    </a>
                </li>
                <li>
                    <a href="data.html">
                        网站数据
                    </a>
                </li>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <li><a href="login.html"><span class="glyphicon glyphicon-user"></span>&nbsp;${admin.adminId}，已登录</a>
                </li>
                <li><a href="logout.html"><span class="glyphicon glyphicon-log-in"></span>&nbsp;退出</a></li>
            </ul>
        </div>
    </div>
</nav>

<div id="visit" style="width: 600px;height:400px;position: relative;top: 10%; margin-left: 600px"></div>

<script type="text/javascript">
    var myChart = echarts.init(document.getElementById('visit'));

    var datas = [{"value":1,"name":"SpringMybatis_war_exploded"},{"value":1,"name":"admin_main.html"},{"value":4,"name":"admin_repasswd.html"},{"value":1,"name":"allbooks.html"},{"value":1,"name":"allreaders.html"},{"value":1,"name":"loginCheck"},{"value":6,"name":"book_add.html"},{"value":2,"name":"deletebook.html"},{"value":7,"name":"getData"},{"value":11,"name":"lendlist.html"},{"value":2,"name":"login.html"},{"value":5,"name":"querybook.html"},{"value":4,"name":"reader_add.html"},{"value":1,"name":"returnbook.html"}];
    console.log(datas);
    var option = {
        title: {
            text: "Visit",
            x: "center",
            margin: 10,
            padding: 0,
            textStyle: {
                fontsize: 10
            }
        },

        series: [{
            type: 'pie',
            data: datas
        }]

    };
    myChart.setOption(option);


    var data = [];

    $.ajax({
        type: "get",
        timeout: 10 * 60 * 100,
        async: true,
        url: "${pageContext.request.contextPath}/getData.html",
        data: {},
        dataType: "json",

        success: function (result) {
            if (result) {
                var tmp = result.pageCountList;
                for (var i = 0; i < tmp.length; i++) {
                    data.push({
                        name: tmp[i].page,
                        value: tmp[i].count
                    })
                }
                myChart.showLoading();

                var option = ({
                    title: {
                        text: "Visit",
                        x: "center",
                        margin: 10,
                        padding: 0,
                        textStyle: {
                            fontsize: 10
                        }
                    },
                    tooltip: {},
                    legend: {
                        orient: 'vertical',
                        left: 'left'
                    },
                    series: [{
                        name: 'page',
                        type: 'pie',
                        data: data
                    }]
                });
                myChart.hideLoading();
                myChart.setOption(option);
            } else {
                alert("后台获取数据失败");
            }
        },
    }, 20000);
</script>
</body>
</html>
