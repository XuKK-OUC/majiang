
/**
 * 提交回复
 */
function postd() {
    var questionId = $("#question_id").val();

    var content = $("#comment_content").val();
    comment2target(questionId,1,content);

}

function comment2target(targetId,type,content) {
    if(!content){
        alert("评论内容不能为空")
    }
    $.ajax({
        type: "post",
        contentType:"application/json",
        url: "/comment",
        data: JSON.stringify({
            "parentId":targetId,
            "content":content,
            "type":type
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

}
function comment(e) {
    var commentId = e.getAttribute("data-id");
    var content = $("#input-"+commentId).val();
    comment2target(commentId,2,content);
}

/**
 * 展开二级评论
 */
function collapeComments(e) {
    var id = e.getAttribute("data-id");
    var comments = $("#comment-"+id);
    //获取二级评论展开状态
    var collapse = e.getAttribute("data-collapse");
    if(collapse){
        //折叠二级评论
        comments.removeClass("in");
        e.removeAttribute("data-collapse");
        e.classList.remove("active");
    }else{
        //展开二级评论
        comments.addClass("in");
        //标记二级评论展开
        e.setAttribute("data-collapse","in");
        e.classList.add("active");
    }


}