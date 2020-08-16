var ws;
var vm = new Vue({
    el: '#webChatApp',
    data: {
        custUac: {},//当前页面用户
        toCustUac: {},//聊天好友信息
        enterMessage: '',//待发送的消息
    },
    created: function () {
    },
    mounted: function () {
        $.post("./loadInfo.htm", function (data) {
            if (data.custUac != undefined && data.custUac != null) {
                vm.custUac = data.custUac;
                vm.createWebSocket();
            }
        }, "json")
    },
    methods: {
        createWebSocket: function (sessionWs) {
            if ("WebSocket" in window) {
                ws = new WebSocket("ws://localhost:9797/webChat"); //创建WebSocket连接　
                ws.onopen = function () {
                    //当WebSocket创建成功时，触发onopen事件
                    console.log("open");
                }
                ws.onmessage = function (e) {
                    //当客户端收到服务端发来的消息时，触发onmessage事件，参数e.data包含server传递过来的数据
                    console.log(e.data);
                }
                ws.onclose = function (e) {
                    //当客户端收到服务端发送的关闭连接请求时，触发onclose事件
                    console.log("close");
                    ws.close();
                }
                ws.onerror = function (e) {
                    //如果出现连接、处理、接收、发送数据失败的时候触发onerror事件
                    ws.close();
                    console.log(e);
                }
            } else {
                alert("您的浏览器不支持WebSocket");
            }
        },
        //发送按钮
        sendMessage: function () {
            if (ws != undefined && ws != null && ws.readyState == ws.OPEN) {
                let enterMessage = vm.enterMessage;
                if (enterMessage != undefined & enterMessage != null && enterMessage.length > 0) {
                    let messageObj = {
                        toUacId: 'e0db1df66e314540a7cbb7d572ff95709193',
                        message: vm.enterMessage
                    };
                    ws.send(JSON.stringify(messageObj));
                    //输入框置空
                    vm.enterMessage = '';
                    //存储浏览记录
                    //sessionStorage.setItem("ws", ws);
                }
            } else {
                alert("您已断开连接")
            }
        },
        //退出登录 th:href="@{/logout.htm}
        logout: function () {
            console.log("logout",ws != undefined && ws != null && ws.readyState == ws.OPEN)
            if (ws != undefined && ws != null && ws.readyState == ws.OPEN) {
                ws.close();
            }
            window.location.href = "../logout.htm";
        }
    },
})