$(document).ready(function() {
    function getCookie(name) {
        const value = `; ${document.cookie}`;
        const parts = value.split(`; ${name}=`);
        if (parts.length === 2) return parts.pop().split(';').shift();
    }
    console.log(getCookie('userID'));

    var data_object = {
        'requester_id': 0,
        'applicant_id': 0,
        'case_id': 0,
    };

    // 將JSON格式轉換成字串
    var data_string = JSON.stringify(data_object);

    // 發出GET請求取得所有案件列表
    $.ajax({
        type: "GET",
        url: "api/case.do",
        crossDomain: true,
        data:data_string,
        cache: false,
        dataType: 'json',
        timeout: 5000,
        success: function (response) {
            console.log(response);
            if(response.status == 200){
                updateTable(response.response.data)
            }
        },
        error: function () {
            alert("無法連線到伺服器！");
        }
    });
    
    // 更新案件列表表格
    function updateTable(data) {
        $("#table > tbody").empty();
        var table_html = '';
        $.each(data, function(index, value) {
            table_html +='<tr><td scope="row">' + value['case_id'] +'</td>';
            table_html +='<td>' + value['title'] +'</td>';
            table_html +='<td>' + value['content'] +'</td>';
            table_html +='<td>' + value['area'] +'</td>';
            table_html +='<td>' + value['pay'] +'</td>';
            table_html +='<td>' + '<a href="SA_Detail.html?caseID=' + value['case_id'] + '">查看詳細</a> ';
            table_html +='</td></tr>'
        })
        $("#table > tbody").append(table_html);
    }
});
    
