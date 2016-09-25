var express = require('express');
var axios = require('axios');
var async = require('async');
var router = express.Router();

/* GET home page. */
router.get('/', function(req, res, next) {


    // 他サーバにget
    async.series([function(callback) {
        axios.get('http://www.yahoo.co.jp', { timeout: 1000 }).then(function(res) {
            console.log("axios response:" + res.status);
            callback(null, 'success axios');

            // 他サーバへのリクエストに失敗した時
        }).catch(function(err) {
            callback(err, 'error axios');
        });

        //他サーバへのgetに成功した時の次の処理
    }, function(callback) {
        console.log("render get /");
        res.render('index', { title: 'Express' });

        // seriesの中でエラーがあった時
    }], function(err, results) {
        console.error(results);
        console.error(err);
        next(err);
    });
});

module.exports = router;
