var ws;
var vm = new Vue({
    el: '#webChatApp',
    data: {
        custUac: {},//当前用户信息
        toCustUac: {},//聊天接收信息
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
        createWebSocket: function () {
            if ("WebSocket" in window) {
                ws = new WebSocket("ws://localhost:9797/webChat"); //创建WebSocket连接　
                ws.onopen = function () {
                    //当WebSocket创建成功时，触发onopen事件
                    console.log("open");
                }
                ws.onmessage = function (e) {
                    //当客户端收到服务端发来的消息时，触发onmessage事件，参数e.data包含server传递过来的数据
                    console.log(e.data);
                    if (e.data == 'true') {
                        ws.close();
                        alert("绑定成功");
                    } else {
                        console.log("没结果" + new Date().getTime());
                    }
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
        sendMessage: function () {
            if (ws != undefined && ws != null && ws.readyState == ws.OPEN) {
                let message = {
                    uacId: '',
                    message: '收到消息了吗'
                };
                ws.send("收到消息了吗？");
            } else {
                alert("您已断开连接")
            }
        }
    },
})