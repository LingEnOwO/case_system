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
var id = url.searchParams.get("id");
var sql_num = 0;

function getMember() {
    $.ajax({
        type: "GET",
        url: "api/case.do",
        crossDomain: true,
        data: "id=" + id,
        cache: false,
        dataType: 'json',
        timeout: 5000,
        success: function (response) {
            if(response.status == 200){
                updateSQLTable(response.response);
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

$("#accept").click(function ()  {
    var Today=new Date();
    var DateLimitedYear =Today.getFullYear().toString();
    var DateLimitedMonth =(Today.getMonth()+1).toString();
    var DateLimitedDate =Today.getDate();
    var DateLimitedHour =Today.getHours().toString();
    var DateLimitedMinute =Today.getMinutes().toString();
    if(DateLimitedDate<=9){
        var DateLimitedDate = Today.getDate().toString();
        var DateLimited = DateLimitedYear+"-"+DateLimitedMonth+"-0"+DateLimitedDate+"T"+DateLimitedHour+":"+DateLimitedMinute;
        
    }else{
        var DateLimitedDate = Today.getDate().toString();
        var DateLimited = DateLimitedYear+"-"+DateLimitedMonth+"-"+DateLimitedDate+"T"+DateLimitedHour+":"+DateLimitedMinute;
        
    }
    var username = $('#username').val();
    var applicantid = ['userID'];

        // 將資料組成JSON格式
        var data_object = {
            "requester_id": username,
            "applicant_id": applicantid,/*此處需要取得接案人的id,還需要改*/
            "case_id" : case_id
        };

        // 將JSON格式轉換成字串
        var data_string = JSON.stringify(data_object);

        // 發出POST的AJAX請求
        $.ajax({
                type: "POST",
                url: "api/progress.do",
                data: data_string,
                crossDomain: true,
                cache: false,
                dataType: 'json',
                timeout: 5000,
                success: function (response) {
                    if (response.status == 200) {
                        $.confirm({
                            theme: 'modern',
                            icon: 'fa fa-check-circle-o',
                            type: 'green',
                            animation: 'scale',
                            title: '系統提醒',
                            content: "已接案",
                            buttons: {
                                確定: {
                                    text: '確定',
                                    btnClass: 'btn-blue',
                                    action: function () {
                                        cleanAllData();
                                        document.location.href = "SA_All_Case.html";
                                    }
                                }
                            }
                        });
                    }
                },
                error: function () {
                    alert("無法連線到伺服器！");
                }
        });
    
}




)
