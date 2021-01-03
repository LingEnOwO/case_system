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
var id = url.searchParams.get("caseID");
var sql_num = 0;

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







