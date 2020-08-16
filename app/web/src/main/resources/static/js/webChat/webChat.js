var ws;
var vm = new Vue({
    el: '#webChatApp',
    data: {
        custUac: {},//当前页面用户
        toCustUac: {},//聊天好友信息
        friendList: [],//

        enterMessage: '',//待发送的消息
        //聊天记录ul
        chatRecordUl: '',
    },
    created: function () {
    },
    mounted: function () {
        $.post("./loadInfo.htm", function (data) {
            if (data.custUac != undefined && data.custUac != null) {
                vm.custUac = data.custUac;
                if (data.friendList != null) {
                    vm.friendList = data.friendList;
                }
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
                    //刷新好友列表
                }
                ws.onmessage = function (e) {
                    //当客户端收到服务端发来的消息时，触发onmessage事件，参数e.data包含server传递过来的数据
                    let data = JSON.parse(e.data);
                    console.log(e.data);
                    //刷新好友列表
                    let toCustUac = vm.toCustUac;
                    let tempLi = '<li>\n' +
                        '                                        <div class="conversation-list">\n' +
                        '                                            <div class="chat-avatar">\n' +
                        '                                                <img th:src="@{/assets/images/users/avatar-4.jpg}" alt="">\n' +
                        '                                            </div>\n' +
                        '                                            <div class="user-chat-content">\n' +
                        '                                                <div class="ctext-wrap">\n' +
                        '                                                    <div class="ctext-wrap-content">\n' +
                        '                                                        <p class="mb-0">\n' +
                        '                                                            ' + data.message + '\n' +
                        '                                                        </p>\n' +
                        '                                                        <p class="chat-time mb-0"><i class="ri-time-line align-middle"></i>\n' +
                        '                                                            <span class="align-middle">10:00</span></p>\n' +
                        '                                                    </div>\n' +
                        '                                                </div>\n' +
                        '                                                <div class="conversation-name"><span>' + toCustUac.userName + '</span>\n' +
                        '                                                </div>\n' +
                        '                                            </div>\n' +
                        '                                        </div>\n' +
                        '                                    </li>';
                    vm.chatRecordUl = vm.chatRecordUl + tempLi;
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
                        toUacId: vm.toCustUac.bid,
                        message: vm.enterMessage
                    };
                    ws.send(JSON.stringify(messageObj));
                    //输出聊天记录Html
                    let my = vm.custUac;
                    let tempLi =
                        '<li class="right">\n' +
                        '                                        <div class="conversation-list">\n' +
                        '                                            <div class="chat-avatar">\n' +
                        '                                                <img th:src="@{/assets/images/users/avatar-1.jpg}" alt="">\n' +
                        '                                            </div>\n' +
                        '                                            <div class="user-chat-content">\n' +
                        '                                                <div class="ctext-wrap">\n' +
                        '                                                    <div class="ctext-wrap-content">\n' +
                        '                                                        <p class="mb-0">\n' +
                        '                                                            ' + vm.enterMessage + '\n' +
                        '                                                        </p>\n' +
                        '                                                        <p class="chat-time mb-0"><i class="ri-time-line align-middle"></i>\n' +
                        '                                                            <span class="align-middle">10:02</span></p>\n' +
                        '                                                    </div>\n' +
                        '                                                </div>\n' +
                        '                                                <div class="conversation-name">' + my.userName + '</div>\n' +
                        '                                            </div>\n' +
                        '                                        </div>\n' +
                        '                                    </li>';
                    vm.chatRecordUl = vm.chatRecordUl + tempLi;
                    //输入框置空
                    vm.enterMessage = '';
                    //存储浏览记录
                    //sessionStorage.setItem("ws", ws);
                }
            } else {
                alert("您已断开连接")
            }
        },
        //选择聊天好友
        selectChatFriend: function (event, friendItem) {
            let currentTarget = event.currentTarget;
            //先移除全部li active，再添加当前的
            currentTarget.parentElement.setAttribute("class", "active");
            if (friendItem != undefined && friendItem != null) {
                let toCustUac = JSON.stringify(friendItem);
                vm.toCustUac = JSON.parse(toCustUac);
            }
        },
        //退出登录 th:href="@{/logout.htm}
        logout: function () {
            console.log("logout", ws != undefined && ws != null && ws.readyState == ws.OPEN)
            if (ws != undefined && ws != null && ws.readyState == ws.OPEN) {
                ws.close();
            }
            window.location.href = "../logout.htm";
        },
    },
})