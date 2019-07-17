function expt(grid){  
	var year=$("#pro_year").val();
	var obj =  
    {  
      area:"",    
      old:"",  
      new_add:""  ,
      base:"",
      tech:"",
      other:"",
      reduce:"",
      repair:"",
    }
		var colName="";
        var columns = grid.datagrid("options").columns;    // 得到columns对象    
        var nameList = new Array();
        console.log(columns);
    // 载入title    
    if (typeof columns != 'undefined' && columns != '') {    
        $(columns).each(function (index) {    
            for (var i = 0; i < columns[index].length; ++i) {    
                if (!columns[index][i].hidden) {  
                	if (typeof columns[index][i].field != 'undefined' && columns[index][i].field != '') {    
                        nameList.push(columns[index][i]);    
                    } 
                	colName+=columns[index][i].title.replace("（万元）", "")+",";
                }    
            }    
        });    
    } 
   
    // 载入内容    
    var rows = grid.datagrid("getRows"); // 这段代码是获取当前页的所有行   
    var temp="";
    var data = new Array();
    for (var i = 0; i < rows.length; ++i) {  
    	var obj=new Object();
        for (var j = 0; j < nameList.length; ++j) {    
            var e = nameList[j].field.lastIndexOf('_0');    
            if (e + 2 == nameList[j].field.length) {    
                temp= rows[i][nameList[j].field.substring(0, e)];    
            }    
            else    
                temp= rows[i][nameList[j].field];  
            switch(j){
	            case 0:
	            	obj.area=temp;break;
	            case 1:
	            	obj.old=temp;break;
	            case 2:
	            	obj.new_add=temp;break;
	            case 3:
	            	obj.base=temp;break;
	            case 4:
	            	obj.tech=temp;break;
	            case 5:
	            	obj.other=temp;break;
	            case 6:
	            	obj.reduce=temp;break;
	            case 7:
	            	obj.repair=temp;break;
            }
        }
        data.push(obj);
    }    
    console.log(data);
    console.log(colName);
    console.log(year);
    data=JSON.stringify(data);//转成json,数组传不过去
  
   $("#data").val(data);
   $("#year").val(year);
   $("#form").submit();
}   
  
  
