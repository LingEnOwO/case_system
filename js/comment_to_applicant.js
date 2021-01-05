$(document).ready(function(){  
    // 取得網址參數
    var url_string = window.location.href;
    var url = new URL(url_string);
    var case_id = url.searchParams.get("caseID");
    var data_object = {
        "requester_id": 0,
        "applicant_id": 0,
        "case_id" : case_id,
    };
    // 將JSON格式轉換成字串
    var data_string = JSON.stringify(data_object);
    // 發出GET的AJAX請求取得原本該會員的資料
    console.log(data_string);
    $.ajax({
        type: "GET",
        url: "api/comment.do",
        crossDomain: true,
        data: data_string,
        cache: false,
        dataType: 'json',
        timeout: 5000,
        success: function (response) {
            console.log(response);
        	if(response.status == 200){
           		document.getElementById('applicant_comment').value = response['response']['data'][0]['applicant_comment'];
           		document.getElementById('applicant_evaluation').value = response['response']['data'][0]['applicant_evaluation'];
        	}else{
                alert("ERROR");
            }
        },
        error: function () {
            alert("無法連線到伺服器！");
        }
    });
});