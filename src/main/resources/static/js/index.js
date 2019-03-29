
$(function(){
	/**
	 * 参数定义
	 */	
	var uploadUrl = ipPort + "/upload"
	var otherF = {
			"checkLoginState": function(){
				console.log('-------checkLoginState------');
				if( getCookie1( name ) ){
					window.location.href = ipPort + '/html/userCenter/login.html';
				}
			}
	}
	var eventCF = {
			"searchMapCF": function(){
				console.log('-------searchMapCF------');
				//otherF.checkLoginState();
				var value = $( this ).siblings( 'input' ).val(),flag=0;
				console.log( value );
				console.log( $( '#timePicShow' ).filter( 'label' ) );
				$.each( $( '#timePicShow' ).find( 'label' ), function( index, item ){
					var arr = $( item ).text().split('、');
					console.log( arr );
					if( arr && $.inArray( value ,arr ) != -1 ){
						flag=1;
						eventCF.gotoPic(this);
						return false;
					};								
				})
				flag==0?layer.msg('无此内容',{icon:2}):'';
			},
			"gotoPic": function( obj ){
				console.log('-------gotoPic------');
				var partId = $( obj ).closest( 'li' ).attr( 'id' );
				$( '#a'+partId ).click();
			},
			"areaCF":function(){
				console.log('-------areaToDeail------');
				var url = ipPort + '/area', data = $(this).attr('id');
				$.ajax({
				     type : "GET",
				     url : url,
				     data : {"id":data},
				     async : false, 
				     cache : true,
				     contentType : "application/x-www-form-urlencoded",
				     dataType : "json",
				     success : function( jsonData ){
				 		if( jsonData ){
				 			var data = jsonData.data;
				 			if( jsonData.state == 0 && data ){
				 				$( '#historyDetail div p' ).empty();
								$( '#historyDetail  div p' ).text( data.areaDetail );
				 			}else{
				 				console.log( jsonData.message);
				 				layer.msg( jsonData.message, {icon:2} );
				 			}		
				 		}else{
				 			layer.msg( '请求失败', {icon:2} );
				 		}		
				     },
				     error:function(){
				    	 layer.msg('请求失败');
				     }		       
				});
			},
			"openUploadPage":function(){
				console.log('-------uploadVideo------');
				//弹出页面
				layer.open({
					type: 1,
					content: $('#uploadDom') 
				});	
				return false;
			},
			"chooseFile":function(){
				console.log('-------chooseFile------');
				
			}
	};
		
	/**
	 * 页面初始化
	 */
	var userName = getCookie1( name );
	if( userName ){
		$( '#userName' ).text();
		$( '#userName' ).text( userName );
	}
	console.log(layui.upload);
	layui.upload.render({
	    elem: '#test8'
	    ,url: uploadUrl
	    ,method: 'post'
	    ,auto: false
	    ,accept:"video"
	    ,bindAction: '#test9'
	    ,done: function(res){
	      console.log(res);
	    },error: function(index, upload){
	        layer.closeAll('loading'); //关闭loading
	    }
	});  
	
	/**
	 * 事件绑定
	 */
	$('#searchMap i').on( 'click', eventCF.searchMapCF );
	$('#timeline input').on( 'click', eventCF.areaCF );
	$('#uploadVideo').on( 'click', eventCF.openUploadPage );
	
	
})