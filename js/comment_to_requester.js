// 取得網址參數
var url_string = window.location.href;
var url = new URL(url_string);
var case_id = url.searchParams.get("case_id");

function getComment() {
    // 發出GET的AJAX請求取得原本該會員的資料
    $.ajax({
        type: "GET",
        url: "api/comment.do",
        crossDomain: true,
        data: "case_id=" + case_id,
        cache: false,
        dataType: 'json',
        timeout: 5000,
        success: function (response) {
        	if(response.status == 200){
           		document.getElementById('applicant_comment').value = response['response']['data'][0]['applicant_comment'];
           		document.getElementById('applicant_evaluation').value = response['response']['data'][0]['applicantr_evaluation'];
        	}
        },
        error: function () {
            alert("無法連線到伺服器！");
        }
    });
}

$(document).ready(function() {    
    getComment();
});