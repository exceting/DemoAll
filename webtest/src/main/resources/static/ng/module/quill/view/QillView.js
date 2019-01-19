define(function (require, exports, module) {

    seajs.use('module/quill/core/quill.snow.css');
    seajs.use('module/quill/core/blog_read.css');
    seajs.use('module/quill/core/monokai-sublime.min.css');

    var mainTemp = require('module/quill/tpl/QuillView.tpl');

    require('module/quill/core/quill.js');
    require('module/quill/core/katex.min.js');
    require('module/quill/core/highlight.min.js');

    var quill;

    var QuillView = Backbone.View.extend({
        mainTpl: mainTemp,
        initialize: function () {
            this.$el.append(this.mainTpl);
            quill = new Quill('#editor-container', {
                modules: {
                    formula: true,
                    syntax: true,
                    toolbar: '#toolbar-container'
                },
                placeholder: 'Compose an epic...',
                theme: 'snow'
            });
            // 获取html: quill.container.firstChild.innerHTML
        },

        events: {
            'click .insert_html': 'insertHtml'
        },

        insertHtml: function () {
            var htm = this.$el.find('.html_code').val();
            //<img src="http://imgcdn.wxeditor.com/Uploads/201901/234991/5c2f0694c7253.jpg"/>
            this.$el.find('.ql-editor').append(htm);
        },

        request: function () {

        }
    });

    module.exports = QuillView;

});