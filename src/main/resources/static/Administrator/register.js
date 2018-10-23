var stat = new rxjs.Subject();
stat.subscribe( function (value) {
    if(value.message.indexOf('成功')!=-1){
        alert(value.message);
        location.href='/AdministratorLogin';
    }else{
        alert(value.message);
    }
});

$("#register").click(function () {
    var username = $("#AdministratorName").val();
    if (username == '') {
        alert("用户名不能为空!");
        return;
    }
    var password = $("#exampleInputPassword1").val();
    if (password == '') {
        alert("密码不能为空!");
        return;
    }
    var data = {"name": username, "password": password};
    ajaxs("http://127.0.0.1:8080/addAdministrator", "POST", true, data, 'json',stat);
});