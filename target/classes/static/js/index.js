
//$(function(){
//	/**
//	 * 参数定义
//	 */	
//	console.log(currentId);
//	var uploadUrl = ipPort + "/upload";
//	var otherF = {
//			"checkLoginState": function(){
//				console.log('-------checkLoginState------');
//				var name = getCookie1( "organ" );
//				if( name.length == 0 ){
//					layer.open({
//						type: 1,
//						title:"提示信息",
//						btn: ['确定', '取消'],
//						content: "<em style='color:#666666;font-size:12px'>" + "&nbsp&nbsp&nbsp&nbsp请先登录或注册登录" + "</em>",
//						yes: function(index, layero){
//							window.location.href = ipPort + '/html/userCenter/login.html';
//						    //layer.close(index); 
//						},
//						cancel: function(index, layero){ 
//							layer.close(index);
//							return false; 
//						}
//					});
//					return false;
//				}else{
//					return true;
//				}
//				
//			},
//			"checkUserRole": function(){
//				console.log('-------checkUserRole------');
//				var state = otherF.checkLoginState();	
//				console.log(state);
//				if( state ){
//					var role = getCookie1( "role" );
//					role = role.substring( 1, role.length -1 );
//					console.log(role);
//					if( !role ||  role.length == 0){
//						return false;
//					}else if( contains("管理员",role) ){
//						return false;
//					}
//					return true;
//				}
//			}
//	};
//	var eventCF = {
//			"searchMapCF": function(){
//				console.log('-------searchMapCF------');							
//				otherF.checkLoginState();		
//				var value = $( this ).siblings( 'input' ).val(),flag=0;			
//				if( !value || value.length == 0 ){
//					return;
//				}
//				$.each( $( '#timePicShow' ).find( 'label' ), function( index, item ){
//					var arr = $( item ).text().split('、');
//					if( arr && $.inArray( value ,arr ) != -1 ){
//						flag=1;
//						eventCF.gotoPic(this);
//						return false;
//					};								
//				})
//				flag==0?layer.msg("<em style='color:red'>" + "无此内容" + "</em>",{icon:2}):'';
//			},
//			"gotoPic": function( obj ){
//				console.log('-------gotoPic------');
//				var partId = $( obj ).closest( 'li' ).attr( 'id' );
//				$( '#a'+partId ).click();
//			},
//			"areaCF":function(){
//				console.log('-------areaToDeail------');
//				otherF.checkLoginState();
//				$("html,body").animate({scrollTop: $("#historyDetail").offset().top}, 500);
//				var url = ipPort + '/area', data = $(this).attr('id');
//				$.ajax({
//				     type : "GET",
//				     url : url,
//				     data : {"id":data},
//				     cache : true,
//				     contentType : "application/x-www-form-urlencoded",
//				     dataType : "json",
//				     success : function( jsonData ){
//				 		if( jsonData ){
//				 			var data = jsonData.data;
//				 			if( jsonData.state == 0 && data ){
//				 				$( '#historyDetail div p' ).empty();
//								$( '#historyDetail  div p' ).text( data.areaDetail );
//				 			}else{
//				 				console.log( jsonData.message);
//				 			}		
//				 		}else{
//				 			layer.msg( "<em style='color:red'>" + "请求失败" + "</em>", {icon:2} );
//				 		}		
//				     },
//				     error:function(){
//				    	 layer.msg( "<em style='color:red'>" + "请求失败" + "</em>", {icon:2} );
//				     }		       
//				});
//				var viewurl = ipPort + '/view', vdata = $(this).attr('value');
//				console.log( this);
//				console.log( vdata);
//				$.ajax({
//				     type : "GET",
//				     url : viewurl,
//				     data : {"id":vdata},
//				     cache : true,
//				     contentType : "application/x-www-form-urlencoded",
//				     dataType : "json",
//				     success : function( jsonData ){
//				 		if( jsonData ){
//				 			var data = jsonData.data;
//				 			if( jsonData.state == 0 && data ){
//				 				$( '#viewDetail video' ).empty();
//								$( '#viewDetail video' ).append('<source src="' + data.viewUrl + '" type="video/webm">');
//				 			}else{
//				 				console.log( jsonData.message);
//				 			}		
//				 		}else{
//				 			layer.msg( "<em style='color:red'>" + "请求失败" + "</em>", {icon:2} );
//				 		}		
//				     },
//				     error:function(){
//				    	 layer.msg( "<em style='color:red'>" + "请求失败" + "</em>", {icon:2} );
//				     }		       
//				});
//				currentId = vdata;
//			},
//			"openUploadPage":function(){
//				console.log('-------uploadVideo------');
//				var state = otherF.checkUserRole();
//				if( state ){
//					//弹出页面
//					layer.open({
//						type: 1,
//						content: $('#uploadDom') 
//					});						
//				}
//				return false;				
//			},
//			"chooseFile":function(){
//				console.log('-------chooseFile------');				
//			}
//	};
//		
//	/**
//	 * 页面初始化
//	 */
//	var userName = getCookie1( "organ" );
//	if( userName && userName.length > 0 ){
//		$( '#userName' ).text();
//		$( '#userName' ).text( userName.substring(1,userName.length-1) );
//		$( '#login' ).hide();
//	}
//	layui.upload.render({
//	    elem: '#test8'
//	    ,url: uploadUrl + "?currentId=" + currentId
//	    ,method: 'post'
//	    ,auto: false
//	    ,accept:"video"
//	    ,bindAction: '#test9'
//	    ,done: function(res){
//	    },error: function(index, upload){
//	        layer.closeAll('loading'); //关闭loading
//	    }
//	});  
//	console.log('--------------currentId-----------2----');
//	console.log(currentId);
//	/**
//	 * 事件绑定
//	 */
//	$('#searchMap i').on( 'click', eventCF.searchMapCF );
//	$('#timeline input').on( 'click', eventCF.areaCF );
//	$('#uploadVideo').on( 'click', eventCF.openUploadPage );
//	
//})