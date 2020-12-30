//從cookie抓取使用者ID的函數
function getCookie(cname) {
    var name = cname + "=";
    var decodedCookie = decodeURIComponent(document.cookie);
    var ca = decodedCookie.split(';');
    for(var i = 0; i <ca.length; i++) {
        var c = ca[i];
        while (c.charAt(0) == ' ') {
            c = c.substring(1);
         }
         if (c.indexOf(name) == 0) {
            return c.substring(name.length, c.length);
         }
     }
    return "";
} 

function updateMember() {
    var name = $('#name').val();
    var email = $('#email').val();
    var password = $('#password').val();

    var password_rule = /^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{8,}$/;

    if(!password_rule.test(password)) {
        alert("密碼格式不符，長度至少8，且至少包含一個數字和英文字母！");
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
                        alart('修改成功!')
                        getMember();
                    }
                },
                error: function () {
                    alert("無法連線到伺服器！");
                }
            });
    }
}
function getMember() {
    // 發出GET的AJAX請求取得原本該會員的資料
    $.ajax({
        type: "GET",
        url: "api/member.do",
        crossDomain: true,
        data: "id=" + id,
        cache: false,
        dataType: 'json',
        timeout: 5000,
        success: function (response) {
        	if(response.status == 200){
           		document.getElementById('name').value = response['response']['data'][0]['name'];
           		document.getElementById('email').value = response['response']['data'][0]['email'];
            	document.getElementById('password').value = response['response']['data'][0]['password'];
        	}
        },
        error: function () {
            alert("無法連線到伺服器！");
        }
    });
}
//從cookie抓取使用者ID
var id = getCookie("userID");

$(document).ready(function() {    
    getMember();
});

$('#submit').click(function() {
    updateMember();
});