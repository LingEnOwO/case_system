$(document).ready(function() {
// 處理表單點擊事件
    var $form = $('#login');
    $form.click(function() {
    submit();
    });
    //新增COOKIE的函數
    function setCookie(cname, cvalue) {
        document.cookie = encodeURIComponent(cname) + "=" + encodeURIComponent(cvalue) + ";" + "path=/";
    }
    function submit() {
        var email = $('#email').val();
        var password = $('#password').val();

        var email_rule = /^\w+((-\w+)|(\.\w+))*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z]+$/;
        var password_rule = /^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{8,}$/;

        if (!email_rule.test(email)) {
            alert("Email格式不符！");
        }
        else if (!password_rule.test(password)) {
            alert("密碼格式不符，長度至少8，且至少包含一個數字和英文字母！");
        }
        else {
            var data_object = {
            'email': email,
            "password": password
            };

            // 將JSON格式轉換成字串
            var data_string = JSON.stringify(data_object);
            // 發出GET的AJAX請求
            $.ajax({
                type: "GET",
                url: "api/member.do",
                data: data_string,
                crossDomain: true,
                cache: false,
                dataType: 'json',
                timeout: 5000,
                //成功會進入案件瀏覽網頁或顯示錯誤訊息
                success: function (response) {
                	if(response['status'] == 200) {
                        //看controler長怎樣抓id進去
                        console.log(response);
                        setCookie("userID", response.data[0]["id"]);
                    	alert(response.message);
                    	window.location.assign("case_all.html");
                    }
                    else if(response.status != 200){
                            alert('此組合不存在!');
                            window.location.reload();
                    }                
                },
                error: function () {
                    alert("無法連線到伺服器！");
                }
            });
        }
    }
     
});