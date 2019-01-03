define(function (require, exports, module) {

    var BackboneUtil = require('util/BackboneUtil');

    var ContractRouter = Backbone.Router.extend({
        routes: {
            "home/(:id)": "setHome"
        },

        setApp: function (app) {
            this.app = app;
        },

        setHome: function (id) {
            this.app.renderHome(id);
        },

        defaultRoute: function (args) {
            alert('路由路径不存在！');
        }
    });

    module.exports = ContractRouter;
})
