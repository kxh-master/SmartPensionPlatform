//树状图展示
 $(document).ready(function(){
	// 	加载数据
	var userDepartmentId = $("#sessionDepartmentId").val();
    $.ajax({    
        async : false,    
        cache:false,    
        type: 'POST',    
        dataType : 'json',    
        url: 'userDepartmentAction_ztreeList.action', 
        data: 'userDepartmentId='+userDepartmentId,
        error: function () {
            alert('请求失败');
        },
        success:function(data){
        	if(data!=null){
        		zNodes = data.dataRows; 
        	}
        }    
    });   
  	//初始组织树状图
    $.fn.zTree.init($("#treeDemo"),setting, zNodes);
  
  	//回填部門數據
  	var departmentid = $("#orgCode").val();
    if(departmentid!="" && departmentid!='undefined'){
        var treeObj = $.fn.zTree.getZTreeObj("treeDemo");
        var node = treeObj.getNodeByParam("id",departmentid,null);
        treeObj.selectNode(node,false,false);//選中選項
        zTreeOnClick(null,departmentid,node);//回填數據
    }
          
 });
 
   var setting = {
       data: {
           simpleData: {
               enable: true
           }
       },
       //回调
       callback: {
           onClick: zTreeOnClick
       },
       view: {
           fontCss: { fontSize: "14px" },
//         dblClickExpand: false //雙擊展開子集
//     	   showIcon: false // 不显示对应的图标
       }
   };
   //节点点击事件
   function zTreeOnClick(event, treeId, treeNode) {
// 	   if(!treeNode.nocheck){//判斷是否能選中
	   if(treeNode!=null){
		   $('#orgName').val(treeNode.name);
	       $('#orgCode').val(treeNode.id);
	   }
	   hideTree();
	   selectDepartment();
// 	   }
   };
 //下拉框显示 隐藏
  function showTree(){
     if($('.ztree').css('display') == 'none'){
          $('.ztree').css('display','block'); 
      } else{
          $('.ztree').css('display','none'); 
      }
      $("body").bind("mousedown", onBodyDownByActionType); 
  }
  function hideTree() {  
     $('.ztree').css('display','none');
     $("body").unbind("mousedown", onBodyDownByActionType); 
     return false;
 } 

 //区域外点击事件
 function onBodyDownByActionType(event) {  
     if (event.target.id.indexOf('treeDemo') == -1){  
         if(event.target.id != 'selectDevType'){
             hideTree(); 
         } 
     }  
 }
function selectDepartment(){
	var userDepartmentId = $("#orgCode").val();
	var addUserId = $("#addUserId").val();
	if(userDepartmentId!=null && userDepartmentId!=''){
		$.ajax( {
			type : 'post',
			async : false,
			url : 'userDepartmentAction_selectUserList.action',//请求路径
			data :'userdepartmentId='+userDepartmentId,
			dataType : 'json', //设置返回的是json数据
			success:function(data){
	        	if(data!=null){
	        		var userList = data.dataRows; 
					var typesel = document.getElementById('userId');
					$(typesel).empty();
					
					if(addUserId!='undefined' && addUserId!=''){
						for ( var i in userList) {
							var opt = document.createElement("option");
							opt.value = userList[i].accountid;
							opt.text = userList[i].userName;
							if(addUserId==userList[i].accountid){
								opt.selected =true;
							}
							typesel.appendChild(opt);
						}
					}else{
						var opt = document.createElement("option");
						opt.value="";
						opt.text ="請選擇";
						opt.selected =true;
						typesel.appendChild(opt);
						
						for ( var i in userList) {
							var opt = document.createElement("option");
							opt.value = userList[i].accountid;
							opt.text = userList[i].userName;
							typesel.appendChild(opt);
						}
					}
					
	        	}
	        }
		});
	}
}