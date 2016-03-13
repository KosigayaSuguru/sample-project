$(function(){
    var idx = 1;
    $(".js-add-item").on("click",function(){
        console.log("hogehoge");
        $(".inputs0").clone().insertBefore(".js-add-point").attr("class","inputs"+idx);
        $(".inputs"+idx).children(".aaa[name='name[0].name1']").attr("name","name["+idx+"].name1");
        $(".inputs"+idx).children(".aaa[name='name[0].name2']").attr("name","name["+idx+"].name2");
        idx++;
    });
});
