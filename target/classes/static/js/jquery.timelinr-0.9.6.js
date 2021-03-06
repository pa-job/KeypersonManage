/* ----------------------------------
jQuery Timelinr 0.9.55
tested with jQuery v1.6+

Copyright 2011, CSSLab.cl
Free under the MIT license.
http://www.opensource.org/licenses/mit-license.php

instructions: http://www.csslab.cl/2011/08/18/jquery-timelinr/
---------------------------------- */

jQuery.fn.timelinr = function(options){
	// default plugin settings
	settings = jQuery.extend({
		orientation: 							'horizontal',	// value: horizontal | vertical, default to horizontal
		containerDiv: 						'#timeline',	// value: any HTML tag or #id, default to #timeline
		datesDiv: 								'#dates',			// value: any HTML tag or #id, default to #dates
		datesSelectedClass: 			'selected',		// value: any class, default to selected
		datesSpeed: 							'normal',			// value: integer between 100 and 1000 (recommended) or 'slow', 'normal' or 'fast'; default to normal
		issuesDiv: 								'#issues',		// value: any HTML tag or #id, default to #issues
		issuesSelectedClass: 			'selected',		// value: any class, default to selected
		issuesSpeed: 							'fast',				// value: integer between 100 and 1000 (recommended) or 'slow', 'normal' or 'fast'; default to fast
		issuesTransparency: 			0.2,					// value: integer between 0 and 1 (recommended), default to 0.2
		issuesTransparencySpeed: 	500,					// value: integer between 100 and 1000 (recommended), default to 500 (normal)
		prevButton: 							'#prev',			// value: any HTML tag or #id, default to #prev
		nextButton: 							'#next',			// value: any HTML tag or #id, default to #next
		arrowKeys: 								'false',			// value: true | false, default to false
		startAt: 									1,						// value: integer, default to 1 (first)
		autoPlay: 								'false',			// value: true | false, default to false
		autoPlayDirection: 				'forward',		// value: forward | backward, default to forward
		autoPlayPause: 						2000					// value: integer (1000 = 1 seg), default to 2000 (2segs)
	}, options);

	function showDetails( obj ){
		console.log( '-----------showDetails--------------' );
		var url= ipPort + "/country/detail",data={'id':$(this).attr('id')};
		ajax( 'get', url, data, countryDetailSF );
	}
	
	function countryDetailSF( jsonData ){
		console.log( '------------countryDetailSF-------------' );
		console.log( jsonData );		
	}
	
	$(function(){
		// Checks if required elements exist on page before initializing timelinr | improvement since 0.9.55
		if ($(settings.datesDiv).length > 0 && $(settings.issuesDiv).length > 0) {
			// setting variables... many of them
			var howManyDates = $(settings.datesDiv+' li').length;
			var howManyIssues = $(settings.issuesDiv+' li').length;
			var currentDate = $(settings.datesDiv).find('a.'+settings.datesSelectedClass);
			var currentIssue = $(settings.issuesDiv).find('li.'+settings.issuesSelectedClass);
			var widthContainer = $(settings.containerDiv).width();
			var heightContainer = $(settings.containerDiv).height();
			var widthIssues = $(settings.issuesDiv).width();
			var heightIssues = $(settings.issuesDiv).height();
			var widthIssue = $(settings.issuesDiv+' li').width();
			var heightIssue = $(settings.issuesDiv+' li').height();
			var widthDates = $(settings.datesDiv).width();
			var heightDates = $(settings.datesDiv).height();
			var widthDate = $(settings.datesDiv+' li').width();
			var heightDate = $(settings.datesDiv+' li').height();
			// set positions!
			if(settings.orientation == 'horizontal') {
				$(settings.issuesDiv).width(widthIssue*howManyIssues);
				$(settings.datesDiv).width(widthDate*howManyDates).css('marginLeft',widthContainer/2-widthDate/2);
				var defaultPositionDates = parseInt($(settings.datesDiv).css('marginLeft').substring(0,$(settings.datesDiv).css('marginLeft').indexOf('px')));
			} else if(settings.orientation == 'vertical') {
				$(settings.issuesDiv).height(heightIssue*howManyIssues);
				$(settings.datesDiv).height(heightDate*howManyDates).css('marginTop',heightContainer/2-heightDate/2);
				var defaultPositionDates = parseInt($(settings.datesDiv).css('marginTop').substring(0,$(settings.datesDiv).css('marginTop').indexOf('px')));
			}

			$(settings.datesDiv+' a').click(function(event){
				event.preventDefault();
				// first vars
				var whichIssue = $(this).text();
				var currentIndex = $(this).parent().prevAll().length;
				// moving the elements
				if(settings.orientation == 'horizontal') {
					$(settings.issuesDiv).animate({'marginLeft':-widthIssue*currentIndex},{queue:false, duration:settings.issuesSpeed});
				} else if(settings.orientation == 'vertical') {
					$(settings.issuesDiv).animate({'marginTop':-heightIssue*currentIndex},{queue:false, duration:settings.issuesSpeed});
				}
				$(settings.issuesDiv+' li').animate({'opacity':settings.issuesTransparency},{queue:false, duration:settings.issuesSpeed}).removeClass(settings.issuesSelectedClass).eq(currentIndex).addClass(settings.issuesSelectedClass).fadeTo(settings.issuesTransparencySpeed,1);
				// prev/next buttons now disappears on first/last issue | bugfix from 0.9.51: lower than 1 issue hide the arrows | bugfixed: arrows not showing when jumping from first to last date
				if(howManyDates == 1) {
					$(settings.prevButton+','+settings.nextButton).fadeOut('fast');
				} else if(howManyDates == 2) {
					if($(settings.issuesDiv+' li:first-child').hasClass(settings.issuesSelectedClass)) {
						$(settings.prevButton).fadeOut('fast');
					 	$(settings.nextButton).fadeIn('fast');
					}
					else if($(settings.issuesDiv+' li:last-child').hasClass(settings.issuesSelectedClass)) {
						$(settings.nextButton).fadeOut('fast');
						$(settings.prevButton).fadeIn('fast');
					}
				} else {
					if( $(settings.issuesDiv+' li:first-child').hasClass(settings.issuesSelectedClass) ) {
						$(settings.nextButton).fadeIn('fast');
						$(settings.prevButton).fadeOut('fast');
					}
					else if( $(settings.issuesDiv+' li:last-child').hasClass(settings.issuesSelectedClass) ) {
						$(settings.prevButton).fadeIn('fast');
						$(settings.nextButton).fadeOut('fast');
					}
					else {
						$(settings.nextButton+','+settings.prevButton).fadeIn('slow');
					}
				}
				// now moving the dates
				$(settings.datesDiv+' a').removeClass(settings.datesSelectedClass);
				$(this).addClass(settings.datesSelectedClass);
				if(settings.orientation == 'horizontal') {
					$(settings.datesDiv).animate({'marginLeft':defaultPositionDates-(widthDate*currentIndex)},{queue:false, duration:'settings.datesSpeed'});
				} else if(settings.orientation == 'vertical') {
					$(settings.datesDiv).animate({'marginTop':defaultPositionDates-(heightDate*currentIndex)},{queue:false, duration:'settings.datesSpeed'});
				}
				
				//点击时间轴
				console.log('---------click------------');
				console.log(this);
				var url = ipPort + "/country",data = $( this ).attr('id');
				$.ajax({
				     type : "GET",
				     url : url,
				     data : {"id":data.substring(1,data.length)},
				     async : false, 
				     cache : true,
				     contentType : "application/x-www-form-urlencoded",
				     dataType : "json",
				     success : function( jsonData ){
				 		if( jsonData ){
				 			var data = jsonData.data;
				 			if( jsonData.state == 0 && data ){
				 				$( '#historyDetail div p' ).empty();
								$( '#historyDetail  div p' ).text( data.countryDetail );
				 			}else{
				 				console.log(layer);
				 				layer.msg( jsonData.message, {icon:2} );
				 			}		
				 		}else{
				 			layer.msg("<em style='color:red'>" + "请求数据为空" + "</em>", {icon:2} );
				 		}		
				     },
				     error:function(){
				    	 layer.msg("<em style='color:red'>" + "请求失败" + "</em>", {icon:2});
				     }		       
				});
				var viewurl = ipPort + '/view', vdata = $(this).attr('id');
				$.ajax({
				     type : "GET",
				     url : viewurl,
				     data : {"id":vdata.substring(1,data.length)},
				     cache : true,
				     contentType : "application/x-www-form-urlencoded",
				     dataType : "json",
				     success : function( jsonData ){
				 		if( jsonData ){
				 			var data = jsonData.data;
				 			if( jsonData.state == 0 && data ){
				 				$( '#viewDetail source' ).empty();
								$( '#viewDetail source' ).attr( 'src', data.viewUrl );
				 			}else{
				 				console.log( jsonData.message);
				 			}		
				 		}else{
				 			layer.msg( "<em style='color:red'>" + "请求数据为空" + "</em>", {icon:2} );
				 		}		
				     },
				     error:function(){
				    	 layer.msg("<em style='color:red'>" + "请求失败" + "</em>", {icon:2} );
				     }		       
				});
				currentId = vdata.substring(1,data.length);
				console.log('--------------currentId-----------1----');
		    	console.log(currentId);
			});

			$(settings.nextButton).bind('click', function(event){
				event.preventDefault();
				// bugixed from 0.9.54: now the dates gets centered when there's too much dates.
				var currentIndex = $(settings.issuesDiv).find('li.'+settings.issuesSelectedClass).index();
				if(settings.orientation == 'horizontal') {
					var currentPositionIssues = parseInt($(settings.issuesDiv).css('marginLeft').substring(0,$(settings.issuesDiv).css('marginLeft').indexOf('px')));
					var currentIssueIndex = currentPositionIssues/widthIssue;
					var currentPositionDates = parseInt($(settings.datesDiv).css('marginLeft').substring(0,$(settings.datesDiv).css('marginLeft').indexOf('px')));
					var currentIssueDate = currentPositionDates-widthDate;
					if(currentPositionIssues <= -(widthIssue*howManyIssues-(widthIssue))) {
						$(settings.issuesDiv).stop();
						$(settings.datesDiv+' li:last-child a').click();
					} else {
						if (!$(settings.issuesDiv).is(':animated')) {
							// bugixed from 0.9.52: now the dates gets centered when there's too much dates.
							$(settings.datesDiv+' li').eq(currentIndex+1).find('a').trigger('click');
						}
					}
				} else if(settings.orientation == 'vertical') {
					var currentPositionIssues = parseInt($(settings.issuesDiv).css('marginTop').substring(0,$(settings.issuesDiv).css('marginTop').indexOf('px')));
					var currentIssueIndex = currentPositionIssues/heightIssue;
					var currentPositionDates = parseInt($(settings.datesDiv).css('marginTop').substring(0,$(settings.datesDiv).css('marginTop').indexOf('px')));
					var currentIssueDate = currentPositionDates-heightDate;
					if(currentPositionIssues <= -(heightIssue*howManyIssues-(heightIssue))) {
						$(settings.issuesDiv).stop();
						$(settings.datesDiv+' li:last-child a').click();
					} else {
						if (!$(settings.issuesDiv).is(':animated')) {
							// bugixed from 0.9.54: now the dates gets centered when there's too much dates.
							$(settings.datesDiv+' li').eq(currentIndex+1).find('a').trigger('click');
						}
					}
				}
				// prev/next buttons now disappears on first/last issue | bugfix from 0.9.51: lower than 1 issue hide the arrows
				if(howManyDates == 1) {
					$(settings.prevButton+','+settings.nextButton).fadeOut('fast');
				} else if(howManyDates == 2) {
					if($(settings.issuesDiv+' li:first-child').hasClass(settings.issuesSelectedClass)) {
						$(settings.prevButton).fadeOut('fast');
					 	$(settings.nextButton).fadeIn('fast');
					}
					else if($(settings.issuesDiv+' li:last-child').hasClass(settings.issuesSelectedClass)) {
						$(settings.nextButton).fadeOut('fast');
						$(settings.prevButton).fadeIn('fast');
					}
				} else {
					if( $(settings.issuesDiv+' li:first-child').hasClass(settings.issuesSelectedClass) ) {
						$(settings.prevButton).fadeOut('fast');
					}
					else if( $(settings.issuesDiv+' li:last-child').hasClass(settings.issuesSelectedClass) ) {
						$(settings.nextButton).fadeOut('fast');
					}
					else {
						$(settings.nextButton+','+settings.prevButton).fadeIn('slow');
					}
				}
			});

			$(settings.prevButton).click(function(event){
				event.preventDefault();
				// bugixed from 0.9.54: now the dates gets centered when there's too much dates.
				var currentIndex = $(settings.issuesDiv).find('li.'+settings.issuesSelectedClass).index();
				if(settings.orientation == 'horizontal') {
					var currentPositionIssues = parseInt($(settings.issuesDiv).css('marginLeft').substring(0,$(settings.issuesDiv).css('marginLeft').indexOf('px')));
					var currentIssueIndex = currentPositionIssues/widthIssue;
					var currentPositionDates = parseInt($(settings.datesDiv).css('marginLeft').substring(0,$(settings.datesDiv).css('marginLeft').indexOf('px')));
					var currentIssueDate = currentPositionDates+widthDate;
					if(currentPositionIssues >= 0) {
						$(settings.issuesDiv).stop();
						$(settings.datesDiv+' li:first-child a').click();
					} else {
						if (!$(settings.issuesDiv).is(':animated')) {
							// bugixed from 0.9.54: now the dates gets centered when there's too much dates.
							$(settings.datesDiv+' li').eq(currentIndex-1).find('a').trigger('click');
						}
					}
				} else if(settings.orientation == 'vertical') {
					var currentPositionIssues = parseInt($(settings.issuesDiv).css('marginTop').substring(0,$(settings.issuesDiv).css('marginTop').indexOf('px')));
					var currentIssueIndex = currentPositionIssues/heightIssue;
					var currentPositionDates = parseInt($(settings.datesDiv).css('marginTop').substring(0,$(settings.datesDiv).css('marginTop').indexOf('px')));
					var currentIssueDate = currentPositionDates+heightDate;
					if(currentPositionIssues >= 0) {
						$(settings.issuesDiv).stop();
						$(settings.datesDiv+' li:first-child a').click();
					} else {
						if (!$(settings.issuesDiv).is(':animated')) {
							// bugixed from 0.9.54: now the dates gets centered when there's too much dates.
							$(settings.datesDiv+' li').eq(currentIndex-1).find('a').trigger('click');
						}
					}
				}
				// prev/next buttons now disappears on first/last issue | bugfix from 0.9.51: lower than 1 issue hide the arrows
				if(howManyDates == 1) {
					$(settings.prevButton+','+settings.nextButton).fadeOut('fast');
				} else if(howManyDates == 2) {
					if($(settings.issuesDiv+' li:first-child').hasClass(settings.issuesSelectedClass)) {
						$(settings.prevButton).fadeOut('fast');
					 	$(settings.nextButton).fadeIn('fast');
					}
					else if($(settings.issuesDiv+' li:last-child').hasClass(settings.issuesSelectedClass)) {
						$(settings.nextButton).fadeOut('fast');
						$(settings.prevButton).fadeIn('fast');
					}
				} else {
					if( $(settings.issuesDiv+' li:first-child').hasClass(settings.issuesSelectedClass) ) {
						$(settings.prevButton).fadeOut('fast');
					}
					else if( $(settings.issuesDiv+' li:last-child').hasClass(settings.issuesSelectedClass) ) {
						$(settings.nextButton).fadeOut('fast');
					}
					else {
						$(settings.nextButton+','+settings.prevButton).fadeIn('slow');
					}
				}
			});
			// keyboard navigation, added since 0.9.1
			if(settings.arrowKeys=='true') {
				if(settings.orientation=='horizontal') {
					$(document).keydown(function(event){
						if (event.keyCode == 39) {
					       $(settings.nextButton).click();
					    }
						if (event.keyCode == 37) {
					       $(settings.prevButton).click();
					    }
					});
				} else if(settings.orientation=='vertical') {
					$(document).keydown(function(event){
						if (event.keyCode == 40) {
					       $(settings.nextButton).click();
					    }
						if (event.keyCode == 38) {
					       $(settings.prevButton).click();
					    }
					});
				}
			}
			// default position startAt, added since 0.9.3
			$(settings.datesDiv+' li').eq(settings.startAt-1).find('a').trigger('click');
			// autoPlay, added since 0.9.4
			if(settings.autoPlay == 'true') {
				setInterval("autoPlay()", settings.autoPlayPause);
			}
		}
		
		
		/**
		 * 参数定义
		 */	
		console.log(currentId);
		var uploadUrl = ipPort + "/video/uploadMultiFile/";
		var otherF = {
				"checkLoginState": function(){
					console.log('-------checkLoginState------');
					var name = getCookie1( "organ" );
					if( name.length == 0 ){
						layer.open({
							type: 1,
							title:"提示信息",
							btn: ['确定', '取消'],
							content: "<em style='color:#666666;font-size:12px'>" + "&nbsp&nbsp&nbsp&nbsp请先登录或注册登录" + "</em>",
							yes: function(index, layero){
								window.location.href = ipPort + '/html/userCenter/login.html';
							    //layer.close(index); 
							},
							cancel: function(index, layero){ 
								layer.close(index);
								return false; 
							}
						});
						return false;
					}else{
						return true;
					}
					
				},
				"checkUserRole": function(){
					console.log('-------checkUserRole------');
					var state = otherF.checkLoginState();	
					console.log(state);
					if( state ){
						var role = getCookie1( "role" );
						role = role.substring( 1, role.length -1 );
						console.log(role);
						if( !role ||  role.length == 0){
							return false;
						}else if( contains("管理员",role) ){
							return false;
						}
						return true;
					}
				}
		};
		var eventCF = {
				"searchMapCF": function(){
					console.log('-------searchMapCF------');							
					otherF.checkLoginState();		
					var value = $( this ).siblings( 'input' ).val(),flag=0;			
					if( !value || value.length == 0 ){
						return;
					}
					$.each( $( '#timePicShow' ).find( 'label' ), function( index, item ){
						var arr = $( item ).text().split('、');
						if( arr && $.inArray( value ,arr ) != -1 ){
							flag=1;
							eventCF.gotoPic(this);
							return false;
						};								
					})
					flag==0?layer.msg("<em style='color:red'>" + "无此内容" + "</em>",{icon:2}):'';
				},
				"gotoPic": function( obj ){
					console.log('-------gotoPic------');
					var partId = $( obj ).closest( 'li' ).attr( 'id' );
					$( '#a'+partId ).click();
				},
				"areaCF":function(){
					console.log('-------areaToDeail------');
					otherF.checkLoginState();
					$("html,body").animate({scrollTop: $("#historyDetail").offset().top}, 500);
					var url = ipPort + '/area', data = $(this).attr('id');
					$.ajax({
					     type : "GET",
					     url : url,
					     data : {"id":data},
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
					 			}		
					 		}else{
					 			layer.msg( "<em style='color:red'>" + "请求失败" + "</em>", {icon:2} );
					 		}		
					     },
					     error:function(){
					    	 layer.msg( "<em style='color:red'>" + "请求失败" + "</em>", {icon:2} );
					     }		       
					});
					var viewurl = ipPort + '/view', vdata = $(this).attr('value');
					console.log( this);
					console.log( vdata);
					$.ajax({
					     type : "GET",
					     url : viewurl,
					     data : {"id":vdata},
					     cache : true,
					     contentType : "application/x-www-form-urlencoded",
					     dataType : "json",
					     success : function( jsonData ){
					 		if( jsonData ){
					 			var data = jsonData.data;
					 			if( jsonData.state == 0 && data ){
					 				$( '#viewDetail video' ).empty();
									$( '#viewDetail video' ).append('<source src="' + data.viewUrl + '" type="video/webm">');
					 			}else{
					 				console.log( jsonData.message);
					 			}		
					 		}else{
					 			layer.msg( "<em style='color:red'>" + "请求失败" + "</em>", {icon:2} );
					 		}		
					     },
					     error:function(){
					    	 layer.msg( "<em style='color:red'>" + "请求失败" + "</em>", {icon:2} );
					     }		       
					});
					currentId = vdata;
				},
				"openUploadPage":function(){
					console.log('-------uploadVideo------');
					var state = otherF.checkUserRole();
					if( state ){
						//弹出页面
						layer.open({
							type: 1,
							content: $('#uploadDom') 
						});						
					}
					return false;				
				},
				"chooseFile":function(){
					console.log('-------chooseFile------');				
				}
		};
			
		/**
		 * 页面初始化
		 */
		var userName = getCookie1( "organ" );
		if( userName && userName.length > 0 ){
			$( '#userName' ).text();
			$( '#userName' ).text( userName.substring(1,userName.length-1) );
			$( '#login' ).hide();
		}
		layui.upload.render({
		    elem: '#test8'
		    ,url: uploadUrl +  currentId
		    ,method: 'post'
		    ,auto: false
		    ,accept:"video"
		    ,bindAction: '#test9'
		    ,done: function(res){
		    	  layer.closeAll(); //关闭loading
		    },error: function(index, upload){
		        layer.closeAll(); //关闭loading
		    }
		});  
		console.log('--------------currentId-----------2----');
		console.log(currentId);
		/**
		 * 事件绑定
		 */
		$('#searchMap i').on( 'click', eventCF.searchMapCF );
		$('#timeline input').on( 'click', eventCF.areaCF );
		$('#uploadVideo').on( 'click', eventCF.openUploadPage );
		$('.pull-center').on('click',function(){location.href='details1.html'});
	});
};

// autoPlay, added since 0.9.4
function autoPlay(){
	var currentDate = $(settings.datesDiv).find('a.'+settings.datesSelectedClass);
	if(settings.autoPlayDirection == 'forward') {
		if(currentDate.parent().is('li:last-child')) {
			$(settings.datesDiv+' li:first-child').find('a').trigger('click');
		} else {
			currentDate.parent().next().find('a').trigger('click');
		}
	} else if(settings.autoPlayDirection == 'backward') {
		if(currentDate.parent().is('li:first-child')) {
			$(settings.datesDiv+' li:last-child').find('a').trigger('click');
		} else {
			currentDate.parent().prev().find('a').trigger('click');
		}
	}
}
