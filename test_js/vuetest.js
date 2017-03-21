window.onload = function() {

    Vue.component('my-component', {
        template: '<div v-on:click="hoge += 1">A custom component! {{hoge}}</div>',
        data: function() {
            return {
                hoge: 0,
                message: "my-component message"
            }
        }
    });

    var vm = new Vue({
        el: '#app',
        data: {
            message: "hogehoge",
            message_list: ["abc", "defg", "hijkl"],
            obj: {
                "key1": "value1"
            }
        }
    });
}
