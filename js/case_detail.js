$(document).ready(function() {
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
    // 取得網址參數
    var url_string = window.location.href;
    var url = new URL(url_string);
    var case_id = url.searchParams.get("caseID");

    var data_object = {
        'requester_id': 0,
        'applicant_id': 0,
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
                    document.getElementById('Content').value = response['response']['data'][0]['content'];
                    document.getElementById('Title').value = response['response']['data'][0]['title'];
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

    $("#accept").click(function(){
        var username = $('#username').val();
        var applicantid = getCookie('userID');
    
        // 將資料組成JSON格式
        var data_object = {
            "requester_id": username,
            "applicant_id": applicantid,
            "case_id" : case_id
        };

        // 將JSON格式轉換成字串
        var data_string = JSON.stringify(data_object);
        console.log(data_string);

        //發出POST的AJAX請求
        $.ajax({
            type: "POST",
            url: "api/progress.do",
            data: data_string,
            crossDomain: true,
            cache: false,
            dataType: 'json',
            timeout: 5000,
            success: function (response) {
                if (response.response.status == 400) {
                    console.log(response);
                    alert("太晚了，此案已被接走");
                }else{
                    alert("接案成功");
                    window.location.assign("case_all.html");
                    console.log(response);
                }
            },
            error: function () {
                alert("無法連線到伺服器！");
            }
        });

        $.ajax({
            type: "POST",
            url: "api/comment.do",
            data: data_string,
            crossDomain: true,
            cache: false,
            dataType: 'json',
            timeout: 5000,
            success: function (response) {
                if (response.status == 200) {
                    console.log(response);
                    console.log("comment table create success");
                }else{
                    alert("接案成功");
                    console.log(response);
                }
            },
            error: function () {
                alert("無法連線到伺服器！");
            }
        });
    })
});


