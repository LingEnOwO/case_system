var sql_num = 0;
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

function getAllMember() {
    // 發出POST的GET請求取得所有會員列表
    $.ajax({
        type: "GET",
        url: "api/case.do",
        crossDomain: true,
        cache: false,
        dataType: 'json',
        timeout: 5000,
        success: function (response) {
            if(response.status == 200){
                
            }
            console.log(response);
        },
        error: function () {
            alert("無法連線到伺服器！");
        }
    });
}
// 更新案件列表表格
function updateTable(data) {
    $("#table > tbody").empty();
    var table_html = '';
    $.each(data, function(index, value) {
        table_html +='<div class="row">';
        
        table_html +='<div class="col-sm-1 themed-grid-col">' + value['id'] +'</div>';
        table_html +='<div class="col-sm-2 themed-grid-col">' + value['Title'] +'</div>';
        table_html +='<div class="col-sm-4 themed-grid-col">' + value['Content'] +'</div>';
        table_html +='<div class="col-sm-3 themed-grid-col">' + value['address'] +'</div>';
        table_html +='<div class="col-sm-1 themed-grid-col">' + value['reward'] +'</div>';
        table_html +='<div class="col-sm-1 themed-grid-col">' + '<a href="SA_Detail.html?userid=' + value['userID'] + '">查看詳細</a> ';
        table_html +='</div>';
        table_html +='</div>'
    })
    $("#table > tbody").append(table_html);
}
$(document).ready(function() {
    getAllMember();
});



       