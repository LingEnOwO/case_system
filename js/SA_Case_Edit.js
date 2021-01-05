$(document).ready(function() {
    // 發出GET的AJAX請求取得原本該會員的資料
    $("#sql_log > tbody").empty();
    // 取得網址參數
    var url_string = window.location.href;
    var url = new URL(url_string);
    var case_id = url.searchParams.get("caseID");

    var data_object = {
        'id': 0,
        'case_id': case_id,
    };
    // 將JSON格式轉換成字串
    var data_string = JSON.stringify(data_object);
    console.log(data_string);
    getMember();

    function getMember() {
        $.ajax({
            type: "GET",
            url: "api/case.do",
            crossDomain: true,
            data: data_string,
            cache: false,
            dataType: 'json',
            timeout: 5000,
            success: function (response) {
                if(response.status == 200){
                    console.log(response);
                    document.getElementById('Content').value = response['response']['data'][0]['content'];
                    document.getElementById('Title').value = response['response']['data'][0]['title'];
                    document.getElementById('username').value = response['response']['data'][0]['requester_id'];
                    document.getElementById('phone').value = response['response']['data'][0]['phone'];
                    document.getElementById('address').value = response['response']['data'][0]['area'];
                    document.getElementById('case_time').value = response['response']['data'][0]['case_time'];
                    document.getElementById('end_time').value = response['response']['data'][0]['end_time'];
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
        var url_string = window.location.href;
        var url = new URL(url_string);
        var case_id = url.searchParams.get("caseID");
        updateMember(case_id);

        function updateMember(case_id) {
            var title = $('#Title').val();
            var content = $('#Content').val();
            var requester_id = $('#username').val();
            var phone = $('#phone').val();
            var area = $('#address').val();
            var case_time = $('#case_time').val();
            var end_time = $('#end_time').val();
            var pay = $('#reward').val();
    
            // 將資料組成JSON格式
            var data_object = {
                "case_id":case_id,
                "title": title,
                "content": content,
                "requester_id": requester_id,
                "phone": phone,
                "area": area,
                "case_time": case_time,
                "end_time": end_time,
                "pay": pay
            };
    
            // 將JSON格式轉換成字串
            var data_string = JSON.stringify(data_object);
            console.log(data_string);
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
                        alert("更新成功");
                        window.location.reload();
                    }
                },
                error: function () {
                    alert("無法連線到伺服器！");
                }
            });
        }
    });
});


            