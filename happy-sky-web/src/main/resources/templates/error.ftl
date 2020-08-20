<html lang="en" xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8"/>
    <title>广播式WebSocket</title>
</head>
<body>
    <div>
        <div>
            <button id="connect">连接</button>
            <button id="disconnect" disabled="disabled">断开连接</button>
        </div>

        <div id="conversationDiv">
            <label>输入你的名字</label>
            <input type="text" id="name"/>
            <button id="sendName" >发送</button>
            <p id="response"></p>
        </div>
    </div>
</body>
</html>