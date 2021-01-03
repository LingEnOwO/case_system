$(document).ready(function() {
// 處理表單點擊事件
    var $form = $('#submit');
    $form.click(function() {
        submit();
    });

    function submit() {
        var name = $('#name').val();
        var email = $('#email').val();
        var password = $('#password').val();
        var confirm_password = $('#confirm_password').val();


        var email_rule = /^\w+((-\w+)|(\.\w+))*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z]+$/;
        var password_rule = /^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{8,}$/;

        if (!email_rule.test(email)) {
            alert("Email格式不符！");
        }
        else if(!password_rule.test(password)) {
            alert("密碼格式不符，長度至少8，且至少包含一個數字和英文字母！");
        }
        else if(password != confirm_password ) {
            alert("密碼驗證不相同");
        }
        else {
            // 將資料組成JSON格式
            var data_object = {
                "name": name,
                "email": email,
                "password": password
            };

            // 將JSON格式轉換成字串
            var data_string = JSON.stringify(data_object);

            // 發出POST的AJAX請求
            $.ajax({
                type: "POST",
                url: "api/member.do",
                data: data_string,
                crossDomain: true,
                cache: false,
                dataType: 'json',
                timeout: 5000,
                success: function (response) {
                    if(response.status == 200){
                        alert('註冊成功!');
                        window.location.assign("index.html");
                    }
                    else if(response.status == 400 ) {
                        console.log(response);
                        alert("新增帳號失敗，帳號重複！");
                        window.location.reload();
                        }
                },
                error: function () {
                    alert("無法連接伺服器！");
                }
            });
        }
    }
});