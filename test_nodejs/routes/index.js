var express = require('express');
var axios = require('axios');
var async = require('async');
var router = express.Router();

/* GET home page. */
router.get('/', function(req, res, next) {

    axios.get('http://www.yahoo.co.jp').then(function(res) {
        console.log("axios response:" + res.status);
    });
    console.log("render get /");
    res.render('index', { title: 'Express' });
});

module.exports = router;
