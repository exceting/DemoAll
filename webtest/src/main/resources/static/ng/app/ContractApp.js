define(function(require, exports, module) {

    var Top = require('component/Top');

    var ContractApp = Backbone.View.extend({

        main$el: $('.main'),
        pageEl: '#page',

        initialize: function(options) {
            this.top = new Top();
            this.lastPage = null;
            if(bootbox){
                bootbox.setDefaults({title:'请求结果'});
            }
        },

        renderHome: function (id) {
            var app = this;

            require.async('module/home/HomePage', function (HomePage) {
                if (!(app.lastPage instanceof HomePage)) {
                    app.reset();
                    app.lastPage = new HomePage({
                        el: app.pageEl
                    });
                }
                app.lastPage.go(id);
            });
        },

        renderQuillTest: function () {
            var app = this;

            require.async('module/quill/QuillPage', function (QuillPage) {
                if (!(app.lastPage instanceof QuillPage)) {
                    app.reset();
                    app.lastPage = new QuillPage({
                        el: app.pageEl
                    });
                }
                app.lastPage.go();
            });
        },

        reset: function() {
            if (this.lastPage) {
                this.lastPage.remove();
                this.main$el.append('<div id="page"/>');
            }
        }
    });

    module.exports = ContractApp;
});
