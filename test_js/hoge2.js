var res;

$(function(){
//alert("test");

$("#test").on("click",hoge);

Hoge = function(){};

var hoge = new Hoge();
Hoge.prototype.p1 = "test1";
Hoge.prototype.p2 = "test2";
Hoge.prototype.p3 = "test3";


function hoge(){
	var moge = new Hoge();
	//alert(moge.p1);
	//alert(hoge.p2);
	$.ajax({
	type: "GET",
	dataType: "jsonp",
	url:"https://ajax.googleapis.com/ajax/services/feed/load?v=1.0&q=http://kancolle.doorblog.jp/index.rdf&num=-1",
	success: function(data){
		res = data;
		$.each(res.responseData.feed.entries, function(){ $('#list1').append(this.title + "<br>")})
		},
	error: function(data, a, b){
		$("#error_msg").text("error").css("color","red");
		alert("error");
		}
	});
};
});
