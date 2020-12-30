// 取得網址參數
var url_string = window.location.href;
var url = new URL(url_string);
var case_id = url.searchParams.get("case_id");

$(document).ready(function() {
// 處理表單點擊事件
    var $form = $('#submit');
    $form.click(function() {
    submit();
    });
    function submit() {
        var requester_comment = $('#comment').val();
        var requester_evaluation = $('#select').val();
        if (requester_evaluation == "" ) {
            alert("請選擇有效給分");
        }
        else {;
                var data_object = {
                "case_id": case_id,
                "requester_evaluation": requester_evaluation,
                "requester_comment": requester_comment
            };

            // 將JSON格式轉換成字串
            var data_string = JSON.stringify(data_object);
            // 發出POST的AJAX請求
            $.ajax({
                type: "POST",
                url: "api/comment.do",
                data: data_string,
                crossDomain: true,
                cache: false,
                dataType: 'json',
                timeout: 5000,
                //成功會進入案件瀏覽網頁或顯示錯誤訊息
                success: function (response) {
                  if(response.status == 200) {
                      alart('提交成功!');
                      window.location.assign("SA_All_Case.html");
                    }               
                },
                error: function () {
                    alert("無法連線到伺服器！");
                }
            });
        }
    }
     
});
