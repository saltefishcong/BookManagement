var stat = new rxjs.Subject();
stat.subscribe( function (value) {
    if(value.message.indexOf('成功') != -1){
        location.href="/findAdministratorIndex";
    }else{
        alert(value.message);
    }
});

$("#login").click(function () {
    var username = $("#Administrator").val();
    if (username == '') {
        alert("用户名不能为空!");
        return;
    }
    var password = $("#exampleInputPassword1").val();
    if (password == '') {
        alert("密码不能为空!");
        return;
    }
    var data = {"identification": username, "password": password};
    ajaxs("http://127.0.0.1:8080/loginAdministrator", "POST", true, data, 'json',stat);
});

$("#register").click(function () {
    window.open("http://127.0.0.1:8080/administratorAdd");
});

