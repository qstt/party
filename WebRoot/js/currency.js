// top nav
function AllTopNav() {
	var TopNav_li = document.getElementById("topnav").getElementsByTagName("li");
	var TwoList_li = document.getElementById("twolist").getElementsByTagName("li");
	
	for( var i=0; i<TopNav_li.length; i++ ){
		
		TopNav_li[i].onclick = function(){
			
			TopNav_li_this = this;
		
				for( p=0; p<TwoList_li.length; p++ ){
					if( TopNav_li_this==TopNav_li[p]){
						TwoList_li[p].className = "show";  TopNav_li[p].getElementsByTagName("dl")[0].className = "onohter";
					}
					else{ TwoList_li[p].className = "";  TopNav_li[p].getElementsByTagName("dl")[0].className = "";}
				}
				
		
		}
			
	}
	
	var TwoList_a = document.getElementById("twolist").getElementsByTagName("a");
	for(var k=0; k < TwoList_a.length; k++ ){
			TwoList_a[k].onclick = function(){
				    _this = this;
					for( l=0; l<TwoList_a.length; l++ ){
						if(_this==TwoList_a[l]){TwoList_a[l].className="on";}
						else{TwoList_a[l].className="";}
				}
			}
		}
	
}


// 通用列表table（多选）
function listcommonly_table(){
	
    $('.listcommonly tr').hover(
        function(){
            $(this).children("td").addClass('mouseon');
        },
        function(){
            $(this).children("td").removeClass('mouseon');
        }
    );
	
    $('.listcommonly tr').bind('click',function (){

    	if ($(this).children("td").length==0){
		return;
	}
    	if ($(this).children("td").eq(1).attr('class').indexOf('clickon')!=-1){
    		$(this).children("td").removeClass('clickon');
    		$(this).children("td").find(":checkbox").removeAttr("checked");
    	}else{
    		$(this).children("td").addClass('clickon');
    		$(this).children("td").find(":checkbox").attr("checked",true);
    	}

    });
		
}


// 通用列表table（单选）
function listcommonly_radio_table(){
	
    $('.listcommonly_radio tr').hover(
        function(){
            $(this).children("td").addClass('mouseon');
        },
        function(){
            $(this).children("td").removeClass('mouseon');
        }
    );

    $('.listcommonly_radio tr').bind('click',function (){
		
		$(this).siblings("tr").children("td").find(":checkbox").removeAttr("checked");

    	if ($(this).children("td").length==0){
		return;
	}
    	if ($(this).children("td").eq(1).attr('class').indexOf('clickon')!=-1){
    		$(this).children("td").removeClass('clickon');
    		$(this).children("td").find(":checkbox").removeAttr("checked");
    	}else{
    		$(this).parent().children("tr").children("td").removeClass('clickon');
    		$(this).children("td").addClass('clickon');
    		$(this).children("td").find(":checkbox:enabled").attr("checked",true);
    	}
    });
	
}

//搜索（查询）框-收缩
function search1_zkhs(){
	$(".search1 dl").css({display:"none"});
	$(".search_show").bind("click", function(){
		$(".search1 dl").css({display:"block"});
		$(".search_hide").css({display:"block"});
		$(".search_show").css({display:"none"});
		$(".search1").removeClass("HideStater");
	});
	$(".search_hide").bind("click", function(){
		$(".search1 dl").css({display:"none"});
		$(".search_show").css({display:"block"});
		$(".search_hide").css({display:"none"});
		$(".search1").addClass("HideStater");
	});
}

//搜索（查询）框-筛选
function setaclickfun(id){
	$('#'+id+' a').unbind('click').bind('click',function (){
		
		var avalue=$(this).attr('value');
		
		if (avalue==0){
			$(this).parent("li").parent("ul").children("li").children("a").removeClass('filter'); 
			$(this).addClass('filter');
		}
		
		else{
			$(this).parent("li").parent("ul").children("li").children("a").eq(0).removeClass('filter');
			if ($(this).attr('class')=='filter'){
				$(this).removeClass('filter');
				var isall=0;
				$(this).parent("li").parent("ul").children("li").children("a").each(function(i){
					if ($(this).attr('class')!='filter'){ isall++; }
				});
				if (isall==$('#'+id+' a').length){ $('#'+id+' a').eq(0).addClass('filter'); };
			}
			else{ $(this).addClass('filter'); }
		}
	  });
}

// 会议保障内容-收缩、展开
function confensure_nrsxzk(){
  	$(".confensure_partit li i").bind("click", function(){
		$(this).parent("li").parent("ul").parent("div").next("div.confensure_content").slideUp("1000");
		$(this).css({display:"none"});
		$(this).next("s").css({display:"block"});
	});
  	$(".confensure_partit li s").bind("click", function(){
		$(this).parent("li").parent("ul").parent("div").next("div.confensure_content").slideDown("1000");
		$(this).css({display:"none"});
		$(this).prev("i").css({display:"block"});
	});
}

//开关设置
function confensure_onoff(kgClass){
	$(kgClass+" span.openl").bind("click", function(){ //参数形式，使用连接符, 注意选择器规范kgClass+" ul span(如：.class ul span)
		$(this).css("display","none");
		$(this).next("span.close").css("display","block");
		return false;
	});
	$(kgClass+" span.close").bind("click", function(){
		$(this).css("display","none");
		$(this).prev("span.openl").css("display","block");
		return false;
	});
}


//资源管理-设备，左侧树状菜单
function resequip_bmlist(){
	//说明 所有的元素以ul li ul li ul li的循环格式嵌套 如果没有下级分类 就用li a结束嵌套
	$(document).ready(function(){
		$(".resgl_bmlist").find("li").not(":has(ul)").children("a").addClass("tree_nozk") //没有子展开
		.click(function(){
			$(this).get(0).location.href="'"+$(this).attr("href")+"'";
		});
		
		$(".resgl_bmlist").find("li:has(ul)").children("a").addClass("tree_yjzk") //可以子展开（默认收缩状态）
		.click(function(){
			if($(this).next("ul").is(":hidden")){
				$(this).next("ul").slideDown("slow");
				if($(this).parent("li").siblings("li").children("ul").is(":visible")){
					//$(this).parent("li").siblings("li").find("ul").slideUp("1000");
					$(this).parent("li").siblings("li:has(ul)").children("a").addClass("tree_kyzk") //点击收缩回去状态
					.end().find("li:has(ul)").children("a").removeClass("tree_kyzk").addClass("tree_yjzk");} //点击某个展开其他反应状态
					$(this).addClass("tree_yjzk");//展开状态
					return false;
				}
				else{
					$(this).next("ul").slideUp("normal");
					//不用toggle()的原因是为了在收缩菜单的时候同时也将该菜单的下级菜单以后的所有元素都隐藏
					$(this).removeClass("tree_yjzk").addClass("tree_kyzk"); //再次点击收缩
					$(this).next("ul").children("li").find("ul").fadeOut("normal");
					$(this).next("ul").find("li:has(ul)").children("a").removeClass("tree_yjzk").addClass("tree_kyzk");//子菜单收缩回去状态
					return false;
				}
		});
	});
}

/*会议主持-功能显示隐藏（传参调用）*/
function ShowAndHidd(theshow,thehidden,theblock){
	$(theshow).click(function(){
		$(this).css("display","none");
		$(thehidden).css("display","block");
		$(theblock).slideDown("1000");
	})
	$(thehidden).click(function(){
		$(this).css("display","none");
		$(theshow).css("display","block");
		$(theblock).slideUp("1000");
	})
}

/*会议主持-分会场选择*/
function conferenceFhroom(){
	$(".fhcroomlist li.sess_room,.fhcroomlist li.sess_ydzd,.fhcroomlist li.notonline")
	   .mouseover(function(){
			   $(this).css({"top":"1px","left":"1px"});
			   $(this).find("dl.fhc_switch").delay(500).fadeIn(300);
			   //console.info($(this));
		   })
	   .mouseout(function(){
		   },function(){
			   $(this).css({"top":"0","left":"0"})
		       $(this).find("dl.fhc_switch").css("display","none");
		       $(this).find("dl.fhc_switch").stop(true,true);
		   })
	   .click(function(){
		  if($(this).hasClass("pitchon")){$(this).removeClass("pitchon");}
		  else{$(this).addClass("pitchon")};
	});
	/*
	$(".fhcroomlist li.notonline").click(function(){
		  if($(this).hasClass("pitchon")){$(this).removeClass("pitchon");}
		  else{$(this).addClass("pitchon")};
	});
	*/
	$("a.allhc_choose").click(function(){
		$(this).css("display","none");
		$("a.allhc_cancel").css("display","block");
		$(".fhcroomlist li.sess_room,.fhcroomlist li.sess_ydzd").addClass("pitchon");
	});
	$("a.allhc_cancel").click(function(){
		$(this).css("display","none");
		$("a.allhc_choose").css("display","block");
		$(".fhcroomlist li.sess_room,.fhcroomlist li.sess_ydzd").removeClass("pitchon");
	});
	$(".fhcroomlist dl.fhc_switch").click(function(){return false;})
	.mouseover(function(){$(this).css("display","block");return false;})
	//防止点击dl出现冒泡
}

/*
————————————————————table添加行————————————————————
参数说明：
ButtonAddId：点击执行添加行数操作的标签ID（如：A标签ID为“getAtr”）
TableID：所要添加行数的表格ID
TableTr：添加新行的TR内容
TableTrRow：允许添加的最大行数
TableRowAlert：超出最大添加行数弹出框ID
*/
function TableAddTr(ButtonAddId,TableID,TableTr,TableTrRow,TableRowAlert){  
    $(ButtonAddId).click(function (){
		var TheTrNum = $(TableID).children("tr");
		if(TheTrNum.length <= TableTrRow){
			$(TableID).append(TableTr);
		}        
		else{
			/*行数限制弹出提示*/
			$(TableRowAlert).show();
			$(TableRowAlert).find("a").click(function (){$(TableRowAlert).hide();})
		}
    });
};
/*删除本行*/
function TableTrgetDel(Trdel){
    $(Trdel).parent("td").parent("tr").remove();    
};

/*————————————————————table添加行 end————————————————————*/


/**
 * 提供一个提示消息静态类 
 * 使用方法
 * 1.message.alert("你好");
 * 2.message.info("你好");
 * 3.message.warning("你好");
 * 4.message.error("你好");
 * 5.message.question("你好");
 * 6.message.confirm("你确定删除？",function(){
 *   选择确定执行回调函数
 * });
 * 
 * 
 */
var MessageBox=function(){
	me=this;
	me.alert=function (message){
		$.messager.alert("提示消息",message);
	};
	me.info=function (message){
		$.messager.alert("提示消息",message,'info');
		};
	me.warning=function (message){
		$.messager.alert('提示消息',message,'warning');
			};
	me.error=function (message){
		$.messager.alert("提示消息",message,'error');
	};
	me.question=function (message){
		$.messager.alert("提示消息",message,'question');
	};
	
	me.confirm=function(message,fun){
		$.messager.confirm('提示消息',message, function(bool){
			if (bool){
				fun();
			}
		});
	};
	
	
};
message=new MessageBox();
