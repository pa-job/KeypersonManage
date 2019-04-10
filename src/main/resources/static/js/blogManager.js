$(function(){
	/**
	 * 变量
	 */
	var table = layui.table,
	layer = layui.layer,
	savaCookieUrl = "../../blog/system",
	showBlogUrl = "../../blog/list",
	initf = {
			createBlogTable:function( data ){
				console.log( '---------加载数据表格-------------');
				table.render({
					elem : '#blogTable',
//					height : '400ox',
					title: '用户微博数据表',
					data : data,
//					toolbar: true,
					page : true,
					even : true,			
					autoSort : false,
					count : data.length,
					curr : 0,
					limit : 10,
					limits : [ 10, 13, 20, 25, 30 ],
					layout : [ 'prev', 'page', 'next', 'skip',
							'count', 'limit' ],
					cols : [ [
						{type: 'numbers',title:'序号', align:'center', minWidth:120}
				    	,{field:'did', title:'id', minWidth:120, fixed: 'left', hide: true, align:'center'}	      
				    	,{field:'remark1', title:'用户姓名', minWidth:200, align:'center'}	   
				    	,{field:'mircoblogNum', title:'微博编号', minWidth:200, align:'center'}        
				    	,{field:'mircoblogContent', title:'微博内容', minWidth:200,align:'center'}
				    	,{field:'creatTime', title: '发布时间', minWidth:150, align:'center'}
					] ],
					id: 'testReload',
					done : function(res, curr, count) {
						
					}
				});
			}
	}
	,sf = {
			savaCookieSF: function( jsonData ){
				console.log( '---------确定按钮请求回调函数-------------');
				layer.msg('保存成功',{icon:1});
				
			},
			showBlogSF: function( jsonData ){
				console.log( '---------微博请求成功回调函数-------------');
				initf.createBlogTable( jsonData );
			}
	}	
	,cf = {
			savaCookieCF: function(){
				var y = new Date().getFullYear()
				,m = new Date().getMonth() + 1			
				,d = new Date().getDay()
				,time = y + "-" + m  + "-" + d;
				console.log( '---------确定按钮单击事件-------------');
				console.log( time );
				var data = $('#blogNum').val();
				console.log( data );
				ajax( 'post', savaCookieUrl, {"cookie":data,"time":time}, sf.savaCookieSF );
			}			
	} 
	

	/**
	 * 事件绑定
	 */
	$( '#sure' ).on( 'click', cf.savaCookieCF );
	
	/**
	 * 页面初始化
	 */
	//表格初始化
	$.ajax({
	     type : "GET",
	     url : showBlogUrl,
	     data : {},
	     cache : true,
	     contentType : "application/x-www-form-urlencoded",
	     dataType : "json",
	     success : function( jsonData ){
	 		if( jsonData ){
	 			var data = jsonData.data;
	 			if( jsonData.code == 0 && data ){
	 				sf.showBlogSF(data);
	 			}else{
	 				layer.msg( jsonData.message, {icon:2} );
	 			}		
	 		}else{
	 			layer.msg( '请求失败', {icon:2} );
	 		}		
	     },
	     error:function(){
	    	 layer.msg('请求失败：');
	     }		       
	});
})