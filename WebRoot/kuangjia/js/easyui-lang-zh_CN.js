if ($.fn.pagination){
	$.fn.pagination.defaults.beforePageText = 'ç¬¬';
	$.fn.pagination.defaults.afterPageText = 'å±{pages}é¡µ';
	$.fn.pagination.defaults.displayMsg = 'æ¾ç¤º{from}å°{to},å±{total}è®°å½';
}
if ($.fn.datagrid){
	$.fn.datagrid.defaults.loadMsg = 'æ­£å¨å¤çï¼è¯·ç¨å¾ããã';
}
if ($.fn.treegrid && $.fn.datagrid){
	$.fn.treegrid.defaults.loadMsg = $.fn.datagrid.defaults.loadMsg;
}
if ($.messager){
	$.messager.defaults.ok = 'ç¡®å®';
	$.messager.defaults.cancel = 'åæ¶';
}
if ($.fn.validatebox){
	$.fn.validatebox.defaults.missingMessage = 'è¯¥è¾å¥é¡¹ä¸ºå¿è¾é¡¹';
	$.fn.validatebox.defaults.rules.email.message = 'è¯·è¾å¥ææççµå­é®ä»¶å°å';
	$.fn.validatebox.defaults.rules.url.message = 'è¯·è¾å¥ææçURLå°å';
	$.fn.validatebox.defaults.rules.length.message = 'è¾å¥åå®¹é¿åº¦å¿é¡»ä»äº{0}å{1}ä¹é´';
	$.fn.validatebox.defaults.rules.remote.message = 'è¯·ä¿®æ­£è¯¥å­æ®µ';
}
if ($.fn.numberbox){
	$.fn.numberbox.defaults.missingMessage = 'è¯¥è¾å¥é¡¹ä¸ºå¿è¾é¡¹';
}
if ($.fn.combobox){
	$.fn.combobox.defaults.missingMessage = 'è¯¥è¾å¥é¡¹ä¸ºå¿è¾é¡¹';
}
if ($.fn.combotree){
	$.fn.combotree.defaults.missingMessage = 'è¯¥è¾å¥é¡¹ä¸ºå¿è¾é¡¹';
}
if ($.fn.combogrid){
	$.fn.combogrid.defaults.missingMessage = 'è¯¥è¾å¥é¡¹ä¸ºå¿è¾é¡¹';
}
if ($.fn.calendar){
	$.fn.calendar.defaults.weeks = ['æ¥','ä¸','äº','ä¸','å','äº','å­'];
	$.fn.calendar.defaults.months = ['ä¸æ','äºæ','ä¸æ','åæ','äºæ','å­æ','ä¸æ','å«æ','ä¹æ','åæ','åä¸æ','åäºæ'];
}
if ($.fn.datebox){
	$.fn.datebox.defaults.currentText = 'ä»å¤©';
	$.fn.datebox.defaults.closeText = 'å³é­';
	$.fn.datebox.defaults.okText = 'ç¡®å®';
	$.fn.datebox.defaults.missingMessage = 'è¯¥è¾å¥é¡¹ä¸ºå¿è¾é¡¹';
	$.fn.datebox.defaults.formatter = function(date){
		var y = date.getFullYear();
		var m = date.getMonth()+1;
		var d = date.getDate();
		return y+'-'+(m<10?('0'+m):m)+'-'+(d<10?('0'+d):d);
	};
	$.fn.datebox.defaults.parser = function(s){
		if (!s) return new Date();
		var ss = s.split('-');
		var y = parseInt(ss[0],10);
		var m = parseInt(ss[1],10);
		var d = parseInt(ss[2],10);
		if (!isNaN(y) && !isNaN(m) && !isNaN(d)){
			return new Date(y,m-1,d);
		} else {
			return new Date();
		}
	};
}
if ($.fn.datetimebox && $.fn.datebox){
	$.extend($.fn.datetimebox.defaults,{
		currentText: $.fn.datebox.defaults.currentText,
		closeText: $.fn.datebox.defaults.closeText,
		okText: $.fn.datebox.defaults.okText,
		missingMessage: $.fn.datebox.defaults.missingMessage
	});
}
