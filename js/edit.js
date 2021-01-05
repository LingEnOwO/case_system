$(document).ready(function() {    
    //從cookie抓取使用者ID的函數
    function getCookie(name) {
        const value = `; ${document.cookie}`;
        const parts = value.split(`; ${name}=`);
        if (parts.length === 2) return parts.pop().split(';').shift();
    }
    console.log(getCookie('userID'));
    var $form = $('#submit');
    $form.click(function() {
        updateMember();
    });
    
    function updateMember() {
        var name = $('#name').val();
            var password = $('#password').val();
            var confirm_password = $('#confirm_password').val();

        var password_rule = /^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{8,}$/;

        if(!password_rule.test(password)) {
            alert("密碼格式不符，長度至少8，且至少包含一個數字和英文字母！");
        }
        else if(password != confirm_password ) {
            alert("密碼驗證不相同");
        }
        else {
                // 將資料組成JSON格式
                var data_object = {
                    "id": getCookie('userID'),
                    "name": name,
                    "password": password
                };
                
                // 將JSON格式轉換成字串
                var data_string = JSON.stringify(data_object);
                console.log(data_string);
                // 發出POST的PUT請求
                $.ajax({
                    type: "PUT",
                    url: "api/member.do",
                    data: data_string,
                    crossDomain: true,
                    cache: false,
                    dataType: 'json',
                    timeout: 5000,
                    success: function (response) {
                        if(response.status == 200){
                            alert('修改成功!');
                            window.location.assign("case_all.html");
                        }
                    },
                    error: function () {
                        alert("無法連線到伺服器！");
                    }
                });
        }
    }
});