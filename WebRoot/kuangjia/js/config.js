// JavaScript Document
$(document).ready(function(){
//	alert("width:"+window.screen.width+";height:"+window.screen.height);
	$("body").find("a").click(function(){ 
		return false;
	})
	$("#button .off").hide();
	$("#button ul li a").click(function(){
		var id = $(this).attr("id").split('_')[1];
		var temp = $(this).attr("id").split('_')[0];
		if( temp == "on"){
//			alert("temp:"+temp);
			$("#"+id).children("#on_"+id).hide();
			$("#"+id).children("#off_"+id).show();
		}else{
//			alert("temp:"+temp);
			$("#"+id).children("#off_"+id).hide();
			$("#"+id).children("#on_"+id).show();
		}
	})
	
	/*$("a").click(function(){alert("1");})*/
/*	
	$("#button ul li a").click(function(){
		var temp = $("#button #off").is(":hidden");
		if(temp == true){
			alert("on");
			$("#button #off").show();
			$("#button #on").hide();
		}else{
			alert("off");
			$("#button #off").hide();
			$("#button #on").show();
		}
	})
*/	
})