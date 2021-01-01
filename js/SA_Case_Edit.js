// 取得網址參數
var url_string = window.location.href;
var url = new URL(url_string);
var id = url.searchParams.get("id");
var sql_num = 0;

function updateMember(id) {
    var title = $('#Title').val();
    var content = $('#Content').val();
    var requester_id = $('#username').val();
    var phone = $('#phone').val();
    var area = $('#address').val();
    var start_time = $('#DateLimited').val();
    var pay = $('#reward').val();

    // 將資料組成JSON格式
    var data_object = {
        "title": title,
        "content": content,
        "requester_id": requester_id,
        "phone": phone,
        "area": area,
        "start_time": start_time,
        "pay": pay
    };

    // 將JSON格式轉換成字串
    var data_string = JSON.stringify(data_object);

    // 發出POST的PUT請求
    $.ajax({
        type: "PUT",
        url: "api/case.do",
        data: data_string,
        crossDomain: true,
        cache: false,
        dataType: 'json',
        timeout: 5000,
        success: function (response) {
                                    
            if(response.status == 200){
                updateSQLTable(response.response);
                getMember();
            }
        },
        error: function () {
            alert("無法連線到伺服器！");
        }
    });
}
                
function getMember() {
    $.ajax({
        type: "GET",
        url: "api/case.do",
        crossDomain: true,
        data: "case_id=" + case_id,
        cache: false,
        dataType: 'json',
        timeout: 5000,
        success: function (response) {
            if(response.status == 200){
                updateSQLTable(response.response);
                document.getElementById('Title').value = response['response']['data'][0]['title'];
                document.getElementById('Content').value = response['response']['data'][0]['content'];
                document.getElementById('username').value = response['response']['data'][0]['requester_id'];
                document.getElementById('phone').value = response['response']['data'][0]['phone'];
                document.getElementById('address').value = response['response']['data'][0]['area'];
                document.getElementById('reward').value = response['response']['data'][0]['pay'];
            }
            console.log(response);
        },
        error: function () {
            alert("無法連線到伺服器！");
        }
    });
}

$('#submit').click(function() {
    updateMember(id)
});
                
$(document).ready(function() {
    // 發出GET的AJAX請求取得原本該會員的資料
    $("#sql_log > tbody").empty();
    getMember();
});
            