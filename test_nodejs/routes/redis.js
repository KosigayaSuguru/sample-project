var redis = require('redis');
var express = require('express');
var share = require('../share');

module.exports = function(res, req, next) {

    setup(res, req, next);


    // redisの接続に失敗しても接続処理が非同期処理になっているので、こっちはこっちで処理が走る?
    console.log("set error event");
    share.redisClient.on("error", errorHundler);
    console.log("redis get");
    share.redisClient.get('hoge', function(err, val) {
        // console.error(err);
    });
}

function setup(res, req, next) {
    if (typeof share.redisClient === "undefined" || share.redisClient.connected === false) {
        share.redisClient = redis.createClient({
            retry_strategy: function(options) {
                console.error(options);

                err = options.error;
                console.log(err.message);

                if (true) {
                    // DB接続に失敗した時に例外処理が不要な場合
                    next();

                } else {
                    // DB接続に失敗した時に例外処理が必要な場合
                    next(err);

                }
            }
        });
    }
}

function errorHundler(err, next) {
    console.log("test");
}
