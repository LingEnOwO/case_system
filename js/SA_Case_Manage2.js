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

function deleteMember(id) {
    var check = window.confirm("確認刪除案件？");
    if (check == true) {
    console.log("已確認刪除!");
    var request = {'case_id': id};
    var data_string = JSON.stringify(request);
    $.ajax({
        type: "DELETE",
        url: "api/progress.do",
        crossDomain: true,
        data: data_string,
        cache: false,
        dataType: 'json',
        timeout: 5000,
        success: function (response) {
            if(response.status == 200){
            
                getAllMember();
            }
            console.log(response);
        },
        error: function () {
            alert("無法連線到伺服器！");
        }
    });
    }
    else {
        console.log("已取消!");
    }
}
function getAllMember() {
    // 發出POST的GET請求取得所有會員列表
    $.ajax({
        type: "GET",
        url: "api/progress.do",
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
        table_html +='<div class="col-sm-2 themed-grid-col">' + value['requester_id'] +'</div>';
        table_html +='<div class="col-sm-3 themed-grid-col">' + value['applicant_id'] +'</div>';
        table_html +='<div class="col-sm-2 themed-grid-col">' + value['applicated_time'] +'</div>';
        table_html +='<div class="col-sm-1 themed-grid-col">' + value['finished'] +'</div>';
           
        table_html +='<div class="col-sm-2 themed-grid-col">' + '<a id=edit href="SA_Edit.html?caseid=' + value['case_id'] + '">編輯</a>';
        table_html +='<a id=delete href="javascript: deleteMember(' + value['case_id'] + ');">刪除</a>'+'<a id=complete href="SA_Edit.html?id=' + value['id'] + '">案件完成</a>';/*案件完成尚未做好，需更改，deleteMember須改為自己的function*/
        table_html +='<div class="col-sm-1 themed-grid-col">' + '<a id=comment_for_requester href="comment_for_requester.html?caseid=' + value['case_id'] + '">評價</a> ';
        table_html +='</div>';
        table_html +='</div>'
    })
    
    $("#table > tbody").append(table_html);
}
/*若還沒有人接案才可以使用編輯、刪除，有人接案才能完成案件以及評價*/
if(value['applicant_id']!=null){
    $("#edit").attr("disabled", true).css("pointer-events","none");
    $("#delete").attr("disabled", true).css("pointer-events","none");
    $("#complete").attr("disabled", false);/*complete功能還沒做*/
    $("#comment_for_requester").attr("disable",false);
}else{
    $("#edit").attr("disabled", false);
    $("#delete").attr("disabled", false);
    $("#complete").attr("disabled", true).css("pointer-events","none");
    $("#comment_for_requester").attr("disable",true).css("pointer-events","none");

}


$(document).ready(function() {
    getAllMember();
});





            