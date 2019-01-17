define(function (require, exports, module) {

    seajs.use('module/quill/core/quill.snow.css');
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
            quill.clipboard.paste('<b>dd</b>')
        },

        events: {
            'click .insert_html': 'insertHtml'
        },

        insertHtml: function () {
            var htm = this.$el.find('.html_code').val();
            //quill.getHTML();
            quill.clipboard.dangerouslyPasteHTML(htm);
        },

        request: function () {

        }
    });

    module.exports = QuillView;

});