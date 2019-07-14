function postd() {
    var questionId = $("#question_id").val();

    var content = $("#comment_content").val();
    if(!content){
        alert("评论内容不能为空")
    }
    $.ajax({
        type: "post",
        contentType:"application/json",
        url: "/comment",
        data: JSON.stringify({
            "parentId":questionId,
            "content":content,
            "type":1
        }),
        success: function (response) {
            if(response.code==200){
                //$("#comment_section").hide();
                window.location.reload();
            }
            else{
                if(response.code==203){
                    var isAccept = confirm(response.message);
                    if(isAccept){
                        window.open("https://github.com/login/oauth/authorize?client_id=a5ab359c706921bbbfc6&redirect_uri=http://localhost:8087/callback&state=1&scope=user")
                        window.localStorage.setItem("closable",true);
                    }
                    else{
                        alert(response.message)
                    }
                }
            }
            console.log(response)
        },
        dataType: "json"
    });
    console.log(questionId)
    console.log(content)

}