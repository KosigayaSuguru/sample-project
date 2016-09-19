var redis = require('redis');
var express = require('express');
var share = require('../share');

var callback;
module.exports = function(res, req, next) {

    callback = next;
    share.redisClient = setup();
    share.redisClient.on("error", errorHundler);
}

function setup() {
    if (typeof share.redisClient === "undefined" || share.redisClient.connected === false) {
        return redis.createClient();

        // 接続失敗時の動作を細かく指定したい時
        // share.redisClient = redis.createClient({
        //     retry_strategy: function(options) {
        //         console.error(options);

        //         err = options.error;
        //         console.log(err.message);
        //         // DB接続に失敗した時に例外処理が不要な場合
        //         // next();
        //         // DB接続に失敗した時に例外処理が必要な場合
        //         next(err);
        //     }
        // });
    }
}

function errorHundler(err) {
    console.error(err);
    share.redisClient.quit();
    callback(err);
}
