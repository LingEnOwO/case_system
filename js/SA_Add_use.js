function getCookie(name) {
    const value = `; ${document.cookie}`;
    const parts = value.split(`; ${name}=`);
    if (parts.length === 2) return parts.pop().split(';').shift();
}

// function OnInput (event) {
//     document.getElementById("submit").disabled = false;
// } 

// function load(){
//     var ele = document.getElementsByTagName("div")[0];
//     var content = ele.innerHTML;
//     console.log(content);
// }

// window.onload = load;

$(document).ready(function() {
    // 處理表單點擊事件
    var $form = $('#submit');    
    $form.click(function() {
        var Today=new Date();
        var DateLimitedYear =Today.getFullYear().toString();
        var DateLimitedMonth =(Today.getMonth()+1).toString();
        var DateLimitedDate =Today.getDate();
        var DateLimitedHour =Today.getHours().toString();
        var DateLimitedMinute =Today.getMinutes().toString();
        if(DateLimitedDate<=9){
            var DateLimitedDate = Today.getDate().toString();
            var DateLimited = DateLimitedYear+"-"+DateLimitedMonth+"-0"+DateLimitedDate+"T"+DateLimitedHour+":"+DateLimitedMinute;
            var DeleteTime = document.getElementById("DeleteTime");
        }else{
            var DateLimitedDate = Today.getDate().toString();
            var DateLimited = DateLimitedYear+"-"+DateLimitedMonth+"-"+DateLimitedDate+"T"+DateLimitedHour+":"+DateLimitedMinute;
            var DeleteTime = document.getElementById("DeleteTime");
        }
            if($("#title").val()==""){
                alert("你尚未填寫案件標題");   
            }else if($("#content").val()==""){
                alert("你尚未填寫案件內容");
            }else if($("#area").val()==""){
                alert("你尚未填寫案件地址");     
            }else if($("#case_time").val()==""){
                alert("你尚未填寫案件時間");   
            }else if($("#end_time").val()==""){
                alert("你尚未填寫案件結束時間"); 
            }else if($("#pay").val()==""){
                alert("你尚未填寫酬勞");   
            }else{
                submit();
            }
    });

    function submit() {
        var requester_id = getCookie("userID");
        var title = $("#title").val();
        var content = $("#content").val();
        var phone = $("#phone").val();
        var area = $("#area").val();
        var end_time = $("#end_time").val();
        var case_time = $("#case_time").val();
        var pay = $("#pay").val();

        // 將資料組成JSON格式
        var data_object = {
            "requester_id":requester_id,
            "phone": phone,
            "title": title,
            "content": content,
            "area": area,
            "case_time": case_time,
            "end_time":end_time,
            "pay": pay
        };

        // 將JSON格式轉換成字串
        var data_string = JSON.stringify(data_object);
        console.log(data_string);
        // 發出POST的AJAX請求
        $.ajax({
            type: "POST",
            url: "api/case.do",
            data: data_string,
            crossDomain: true,
            cache: false,
            dataType: 'json',
            timeout: 5000,
            success: function (response) {
                if(response.status == 200){
                    alert('新增成功!');
                    console.log(response);
                    window.location.assign("SA_Case_Manage2.html");
                }
                else if(response.status == 400 ) {
                    console.log(response);
                    alert("新增失敗！");
                    window.location.reload();
                }
            },
            error: function () {
                alert("無法連線到伺服器！");
                window.location.reload();
            }
        });
    }
});