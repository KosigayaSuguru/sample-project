$(function(){
	//alert("test");

	var res;
	var entryId = 0;

	$("#test").on("click",createList);

	Hoge = function(){};

	var hoge = new Hoge();
	Hoge.prototype.p1 = "test1";
	Hoge.prototype.p2 = "test2";
	Hoge.prototype.p3 = "test3";

	function createList(){
		var moge = new Hoge();
		//alert(moge.p1);
		//alert(hoge.p2);
		$.ajax({
		type: "GET",
		dataType: "jsonp",
		async: false,
		url:"https://ajax.googleapis.com/ajax/services/feed/load?v=1.0&q=http://kancolle.doorblog.jp/index.rdf&num=-1",
		success: function(data){
			res = data;

			$.each(res.responseData.feed.entries, function(){
				var apend = addList(this);
				$('#list1').append(apend)})
			},
		error: function(data, a, b){
			$("#error_msg").text("error").css("color","red");
			alert("error");
			}
		});
	};

	 function addList(arg){
	 	var arg1 = arg;
		var tmp = "<span style=\"display:block;margin-bottom:4px;\" id=entryId" + entryId + "><a href=\""+ arg1.link + "\"target=_blank>" + arg1.title + "</a></span>";
		entryId++;
		return tmp;
	}
});

