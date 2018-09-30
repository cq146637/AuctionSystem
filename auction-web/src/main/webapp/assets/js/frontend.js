/*登录判断*/
function loginSubmit(userType){
    let name = $("#name").val();
    let password = $("#password").val();
    let url = "";
    if (userType == "PayUser") {
        url = "/login/getPayUserLogin.json";
    }else if (userType == "SaleUser") {
        url = "sale_login/getSaleUserLogin.json";
    }


    if (name.length == 0 || password.length == 0){
        $("#text-danger").text("用户名或密码不能为空");
        return;
    }
    $("#text-danger").text("");
    $.ajax({
       type:"POST",
       url:url,
       data:{"name":name,"password":password},
       dataType:"json",
        success:function (result) {
            if (result.success == false){
                $("#text-danger").text(result.message);
            }else {
                if (userType == "PayUser") {
                    window.location = "../../getIndex.html";
                }else if (userType == "SaleUser") {
                    window.location = "sale_user_home.html";
                }

            }
        },
        error:function(jqXHR,textStatus,errorThrown){
           alert(jqXHR + "\n" + textStatus + "\n" + errorThrown);
       }
    });
}

/*检查注册用户名是否可用*/
function checkRegisterName(userType){
    let username = $("#name").val();
    let url = "";
    if (userType == "PayUser") {
        url = "/register/checkUsername.json";
    }else if (userType == "SaleUser") {
        url = "/sale_register/checkUsername.json";
    }
    $.ajax({
        type:"POST",
        url:url,
        data:{"username":username},
        dataType:"json",
        success:function (result){
            if (result.success == false){
                $("#existName").text(result.message);
            }else {
                $("#existName").text("");
            }
        },
        error:function(jqXHR,textStatus,errorThrown){
            alert(jqXHR + "\n" + textStatus + "\n" + errorThrown);
        }
    });
}

/*检查两次密码输入是否一致*/
function checkPassword() {
    let password = $("#password").val();
    let repeatpassword = $("#repeatpassword").val();
    if (password != repeatpassword){
        $("#differentPassword").text("两次密码不一致");
    } else {
        $("#differentPassword").text("");
    }
}

/*用户注册提交*/
function registerSubmit() {
    let name = $("#name").val();
    let phone = $("#phone").val();
    let password = $("#password").val();
    let repeatpassword = $("#repeatpassword").val();
    if (name.length == 0 || phone.length == 0 || password.length == 0 || repeatpassword.length == 0){
        alert("所有选项都不能为空");
        return;
    }

    if ($("#password").val() != $("#repeatpassword").val()){
        $("#differentPassword").text("两次密码不一致");
        return;
    }else if($("#existName").text().length != 0){
        return;
    }
    else {
        $("#realname").val(name);
        $("#realpassword").val(password);
        $("#realphone").val(phone);
        console.log($("#realname").val() + "\n"+
        $("#realpassword").val() + "\n"+
        $("#realphone").val());
        $("#registerForm").submit();
    }
}

/*卖家用户注册*/
function saleUserRegisterSubmit() {
    let name = $("#name").val();
    let phone = $("#phone").val();
    let password = $("#password").val();
    let realName = $("#realName").val();
    let email = $("#email").val();
    let address = $("#address").val();
    let zipCode = $("#zipCode").val();
    if (name.length == 0 || phone.length == 0 || password.length == 0 || repeatpassword.length == 0
    || realName.length == 0 || email.length == 0
    || address.length == 0
    || zipCode.length == 0){
        //alert("所有选项都不能为空");
        layui.use('layer', function(){
            var layer = layui.layer;
            layer.msg('所有选项都不能为空');
        });
        return;
    }

    if ($("#password").val() != $("#repeatpassword").val()){
        $("#differentPassword").text("两次密码不一致");
        return;
    }else {
        $("#realname").val(name);
        $("#realpassword").val(password);
        $("#realphone").val(phone);
        $("#trueName").val(realName);
        $("#realemail").val(email);
        $("#realaddress").val(address);
        $("#realzipCode").val(zipCode);
        console.log($("#realname").val() + "\n"+
            $("#realpassword").val() + "\n"+
            $("#realphone").val());
        $("#saleRegisterForm").submit();
    }

}

/*留言提交*/
function submitRemark() {
    $("#submitRemarkForm").submit(function (event) {
        if ($("#remarkTextarea").val().length == 0){
            event.preventDefault();
            $("#remarkMessage").text("不能为空");
            return;
        }
       /* else {
            $("#remarkMessage").text("");
            /!*window.location.href = "/submitRemark.html";*!/
            $("#submitRemarkForm").css("action","/submitRemark.html");
        }*/

    });
}

/*出价提交*/
function offerPrice() {
    $("#offerPriceForm").submit(function (event) {
        if (!$("#terms").prop("checked")){
            event.preventDefault();
            layui.use('layer', function(){
                var layer = layui.layer;
                layer.msg('请先阅读条款和规则');
            });
            return;
        }
        if ($("#offerPriceInput").val().length == 0){
            event.preventDefault();
            layui.use('layer', function(){
                var layer = layui.layer;
                layer.msg('出价不能为空');
            });
            return;
        }
    });
}

/* 用户选择分类时，自动改变id输入框中的val */
function change_category_id (ths) {
    $("#category_id_input").val($(ths).val());
}

/*订单提交*/
// function offerOrder() {
//     $("#offerOrderForm").submit(function (event) {
//         alert("aaaaaaaaaaaaaaaaaaaaaaaa");
//         if (!$("#order_info_is_check").prop("checked")){
//             event.preventDefault();
//             layui.use('layer', function(){
//                 var layer = layui.layer;
//                 layer.msg('请先确认订单信息！');
//             });
//             return;
//         }
//         if (!$("[name='payment']:eq(0)").prop("checked") && !$("[name='payment']:eq(1)").prop("checked")  && !$("[name='payment']:eq(2)").prop("checked")){
//             event.preventDefault();
//             layui.use('layer', function(){
//                 var layer = layui.layer;
//                 layer.msg('请先选择支付方式！');
//             });
//             return;
//         }
//         if (!$("#end_terms").prop("checked")) {
//             event.preventDefault();
//             layui.use('layer', function(){
//                 var layer = layui.layer;
//                 layer.msg('请先阅读条款和规则！');
//             });
//             return;
//         }
//     });
// }

/*搜索商品*/
function searchProduct() {
    if ($("#partProductName").val().length == 0){
        event.preventDefault();
        layui.use('layer', function(){
            var layer = layui.layer;
            layer.msg('请输入商品名称');
        });
        return;
    }else{
        event.preventDefault();
        var options=$("#selectCategoryName option:selected"); //获取选中的项
        $("#realCategoryName").val(options.val());
        $("#realPartProductName").val($("#partProductName").val());
        $("#searchProductForm").submit();

    }


}