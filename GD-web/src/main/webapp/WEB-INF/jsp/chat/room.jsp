<html>
<head>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>聊天室</title>
<script type="text/javascript" src="/js/lib/jquery-1.6.4.js"></script>
<script type="text/javascript">
    $(function(){
        (function getMessages(){
            $.ajax({
                dataType: "json",
                url: '/chat/getMessages.do',
                cache: false,
                success: function(data){
                    var v = $('#text').val();
                    v += '\r\n' + data.date + '  ' + data.content;
                    $('#text').val(v);
                }
            }).always(function(){
                getMessages();
            });
        })();
 
        $('#form').submit(function(event){
            event.preventDefault();
            var values = $(this).serialize();
            $.post('/chat/setMessage.do', values, function(data){
                $('#form>[name=content]').val('');
            }, 'json');
        });
 
        $('#logout').click(function(){
            $.ajax({
                dataType: "json",
                url: '/chat/logout.do',
                cache: false,
                success: function(data){
                    window.location.href = '/page/chatLogin.html';
                }
            });
        });

        $('#privateTalk').click(function(){
            event.preventDefault();
            var values = $('#form').serialize();
            values.toUser = $("#toUser").val();
            $.post('/chat/setPrivateMessage.do', values, function(data){
                $('#form>[name=content]').val('');
            }, 'json');
        });
    });
</script>
</head>
<body>
欢迎：${user } <br/>
<textarea id="text" rows="20" style="width: 500;"></textarea>
<form id="form" method="post">
<input type="text" name="content" />
<input value="发送" type="submit"/>
<input id="logout" value="离开" type="button"/><br />
<input type="text" name="toUser" />
<input id="privateTalk" value="私聊" type="button"/>
</form>
</body>
</html>