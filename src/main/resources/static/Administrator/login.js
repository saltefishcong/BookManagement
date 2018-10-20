$("#login").click(function () {
    var username = $("#Username").val();
    if (username == '') {
        alert("用户名不能为空!");
        return;
    }
    var password = $("#exampleInputPassword1").val();
    if (password == '') {
        alert("密码不能为空!");
        return;
    }
    var data = {"identification": username, "password": password, "action": "login"};
    ajaxs("http://127.0.0.1:8080/loginAdministrator", "POST", true, data, 'text');
});

