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

function OnInput (event) {
    document.getElementById("submit").disabled = false;
} 
function load(){
    var ele = document.getElementsByTagName("div")[0];
    var content = ele.innerHTML;
    console.log(content);
}
window.onload = load;
$(document).ready(function(){
    $("#submit").click(function(){  
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
        if($("#Title").val()==""){
            alert("你尚未填寫案件標題");
            eval("document.form1['Title'].focus()");       
        }else if($("#Content").val()==""){
            alert("你尚未填寫案件內容");
            eval("document.form1['Content'].focus()");    
        }else if($("#username").val()==""){
            alert("你尚未填寫案件客戶名");
            eval("document.form1['username'].focus()");    
        }else if($("#address").val()==""){
            alert("你尚未填寫案件地址");
            eval("document.form1['address'].focus()");       
        }else if($("#TimeLimited").val()==""){
            alert("你尚未填寫案件時間");
            eval("document.form1['TimeLimited'].focus()");       
        }else if($("#DeleteTime").val()==""){
            alert("你尚未填寫案件下架時間");
            eval("document.form1['DeleteTime'].focus()");    
        }else if(DeleteTime.value<=DateLimited ){
        	document.getElementById("submit").disabled = true;
            DeleteTime.setAttribute('class', "form-control is-invalid");
            alert("下架時間需要大於現在");
            eval("document.form1['DeleteTime'].focus()"); 
        }else if($("#reward").val()==""){
            alert("你尚未填寫酬勞");
            eval("document.form1['reward'].focus()");       
        }else{
        	alert("案件已新增"); 
            document.form1.submit();
            
        }
    })
 })

 $(document).ready(function() {
    // 處理表單點擊事件
    var $form = $('#submit');
    $form.click(function() {
        submit();
});

function submit() {
    var Title = $('#Title').val();
    var Content = $('#Content').val();
    var username = $('#username').val();
    var phone = $('#phone').val();
    var address = $('#address').val();
    var TimeLimited = $('#TimeLimited').val();
    var DeleteTime = $('#DeleteTime').val();
    var reward = $('#reward').val();


        // 將資料組成JSON格式
        var data_object = {
            "title": Title,
            "content": Content,
            "requester_id": username,
            "phone": phone,
            "area": address,
            "case_time": DateLimited,
            "pay": reward
        };

        // 將JSON格式轉換成字串
        var data_string = JSON.stringify(data_object);

        // 發出POST的AJAX請求
        $.ajax({
                type: "POST",
                url: "api/case.do",
                data: data_string,
                crossDomain: true,
                cache: false,
                dataType: 'json',
                timeout: 5000,
                success: function () {
                },
                error: function () {
                    alert("無法連線到伺服器！");
                }
        });
    
}

});