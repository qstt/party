function getUserInfo(){
	var user = undefined;
	$.ajax({
		url:"http://"+window.location.host+"/party/index/getUserInfo.do",
   		type:"post",
   		dataType:"json",
   		async:false,
   		success:function(result){
   			if(result.status == 1){
   				user = result.data;
   			}
   		},error:function(){
   			
   		}
	});
	return user;
}