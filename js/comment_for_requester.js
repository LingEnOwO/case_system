$(document).ready(function() {
    // 取得網址參數
    var url_string = window.location.href;
    var url = new URL(url_string);
    var case_id = url.searchParams.get("caseID");
    // 處理表單點擊事件
    var $form = $('#submit');
    $form.click(function() {
    submit();
    });
    function submit() {
        var applicant_comment= $('#comment').val();
        var applicant_evaluation = $('#select').val();
        if (applicant_evaluation == "" ) {
            alert("請選擇有效給分");
        }
        else {
                var data_object = {
                "case_id": case_id,
                "requester_evaluation": "0",
                "requester_comment": "",
                "applicant_evaluation": applicant_evaluation,
                "applicant_comment": applicant_comment
            };

            // 將JSON格式轉換成字串
            var data_string = JSON.stringify(data_object);
            console.log(data_string);
            // 發出PUT的AJAX請求
            $.ajax({
                type: "PUT",
                url: "api/comment.do",
                data: data_string,
                crossDomain: true,
                cache: false,
                dataType: 'json',
                timeout: 5000,
                //成功會進入案件瀏覽網頁或顯示錯誤訊息
                success: function (response) {
                    if(response.status == 200) {
                        console.log(response);
                        alert('提交成功!');
                        window.location.assign("SA_All_Case.html");
                    }else{
                        alert('該案件尚未有人接，所以無法評價');
                    }           
                },
                error: function () {
                    alert("無法連線到伺服器！");
                }
            });
        }
    }
     
});
