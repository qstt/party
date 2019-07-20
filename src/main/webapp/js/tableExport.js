function fnExcelReport()
{
	var obj={
		project:"",
		unit:"",
		price:"",
		rate:"",
	}
	var array=new Array();
    for(var i = 0 ; i < 19 ; i++) 
    {     
       obj=new Object();
       obj.project=$("table tr:eq("+(i+2)+") td:eq(0) div").html();
       obj.unit=$("table tr:eq("+(i+2)+") td:eq(1) div").html();
       obj.price=$("table tr:eq("+(i+2)+") td:eq(2) input").val();
       obj.rate=$("table tr:eq("+(i+2)+") td:eq(3) input").val();
       array.push(obj);
    }
    array=JSON.stringify(array);//转成json,数组传不过去
    
    console.log(array);
    $("#array").val(array);
    $("#form").submit();

    
}