define(function (require, exports, module) {

    //seajs.use('./css/module/homelist.css');

    var Template = require('module/home/tpl/HomeView.tpl');

    var Home = require('module/home/model/Home');

    var HomeView = Backbone.View.extend({
        template: Template,

        initialize: function () {
            this.model = new Home();
            this.$el.append(this.template);
        },

        events: {
            'click .send': 'send'
        },

        send: function () {
            var content = this.$el.find(".content").val();
            this.model.websocket.send(content);
        },

        request: function (id) {
            var el = this.$el;
            //判断当前浏览器是否支持WebSocket
            if ('WebSocket' in window) {
                this.model.websocket = new WebSocket("ws://localhost:8081/websocket/" + id);
                var name = "ROOM_" + id;
                this.model.websocket.onopen = function () {
                    el.find('.msg_area').append(name + "连接成功<br/>");
                };

                this.model.websocket.onmessage = function (event) {
                    el.find('.msg_area').append(event.data + "</br>");
                };

                this.model.websocket.onerror = function (event) {
                    console.error("WebSocket error observed:", event);
                };

                this.model.websocket.onclose = function () {
                    el.find('.msg_area').append(name + "连接关闭<br/>");
                };

                var ws = this.model.websocket;
                window.onbeforeunload = function () {
                    ws.close();
                }
            } else {
                alert('当前浏览器不支持websocket协议！请退出当前页面！')
            }
        }
    });

    module.exports = HomeView;

});