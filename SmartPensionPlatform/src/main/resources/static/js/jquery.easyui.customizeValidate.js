window.onload = function (){
	//easyui的验证插件
	$.extend($.fn.validatebox.defaults.rules, {    
	    emailFormat: {    
	        validator: function(value){  
	        	var format= /^\w+((-\w+)|(\.\w+))*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z0-9]+$/;
	            return format.test(value);    
	        },    
	        message:"请输入正确的Email格式！"
	    },
	    emailOnlycheck: {
			   validator: function(value){ 
				   if(value!=null && value!=''){
					   var ischeck = false;
						$.ajax({
							type : 'post',
							 url : "customerAction_ajaxValidateEmailIsExisted.action",
							 async : false,
							 data : 'email='+value,
							 dataType : 'json',
							 success : function(data){
								 if(!data.b){
										ischeck = true;  
								 } 
								 if($("#oldEmailId").val()==value){
									ischeck = true; 
								 }
							 }
						});
						return ischeck;
					}
		        },
		        message:"存在重複記錄"
		   },
	   telFormat: {
		   validator: function(value){  
	        	var format= /^[2,3,8,5,6][\d]{7}$|^9[0-8][\d]{6}$/;
	            return format.test(value);    
	        }, 
	        message:"請輸入以2,3,5,6,8,90至98開頭的8位固定電話號碼！"
	   },
	   telOnlycheck: {
		   validator: function(value){ 
			   if(value!=null && value!=''){
				   var ischeck = false;
					$.ajax({
						type : 'post',
						url : "customerAction_ajaxValidateTelIsExisted.action",
						 async : false,
						 data : 'tel='+value,
						 dataType : 'json',
						 success : function(data){
							 if(!data.b){
									ischeck = true;  
							 } 
							 if($("#oldTelId").val()==value){
								ischeck = true; 
							 }
						 }
					});
					return ischeck;
				}
	        },
	        message:"存在重複記錄"
	   },
	   phoneFormat: {
		   validator: function(value){  
	        	var format= /^[4,5,6][\d]{7}$|^7[0-3][\d]{6}$|^8[1-3][\d]{6}$|^9[0-8][\d]{6}$/;
	            return format.test(value);    
	        },  
	        message:"請輸入以4,5,6,70至73,81至83,90至98開頭的8位手機電話號碼!"
	   },
	   mobileOnlycheck: {
		   validator: function(value){ 
			   if(value!=null && value!=''){
				   var ischeck = false;
					$.ajax({
						type : 'post',
						url : "customerAction_ajaxValidateMobileIsExisted.action",
						async : false,
						data : 'mobile='+value,
						dataType : 'json',
						success : function(data){
							if(!data.b){
								ischeck = true;  
							}
							if($("#oldMobileId").val()==value){
								ischeck = true; 
							}
						}
					});
					return ischeck;
				}
	        },
	        message:"存在重複記錄"
	   },
	   faxFormat:{
		   validator: function(value){  
	        	var format= /^[2,3,8][\d]{7}$/;
	            return format.test(value);    
	        },
	        message:"請輸入以2,3,8開頭的8位傳真號碼"
	   },
	   passwordFormat:{
		   validator:function(value){
			   var format=/^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,12}$/;
			   return format.test(value);
		   },
		   message:"密碼由6-12位的字母和數字組成！"
	   }
	});
}