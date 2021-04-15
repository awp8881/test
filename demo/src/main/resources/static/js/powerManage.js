var userType = "";
var hasPower = false;
var powerList = new Array();
$(function(){
	var id = 0;
	$.getJSON("../getUserFromSession",function(data){
		id = data.id;
		$.getJSON("../user/getManagerById/" + id, formdata);
	});
});
	
    
function formdata(data){
	userType = data.userType;//得到用户类型	
	if(userType != "超级管理员"){
		
		for(i = 0;i < data.power.length;i++){//得到权限
			$(".powerList").val($(".powerList").val()+data.power[i]+"_");
			
		}	
	}
	
}

//操作
function powerAllocationManage(){
	if(userType != "超级管理员"){
		$(".powerAllocationManage").css({display:"none"})
	}
}

function userManage(obj){

	hasPower = false;
	judgePower("用户管理权限");
	if(!hasPower){
		$(".userManage").css({display:"none"})
		layer.msg("没有当前模块权限", {icon: 2});
	}
	
}
	
function contractManage(obj){
	hasPower = false;
	judgePower("合同管理权限");
	if(!hasPower){
		$(".contractManage").css({display:"none"})
		layer.msg("没有当前模块权限", {icon: 2});
	}
}

function complaintManage(obj){
	hasPower = false;
	judgePower("投诉管理权限");
	if(!hasPower){
		$(".complaintManage").css({display:"none"})
		layer.msg("没有当前模块权限", {icon: 2});
	}
}

function parkingManage(obj){
	hasPower = false;
	judgePower("车位管理权限");
	if(!hasPower){
		$(".parkingManage").css({display:"none"})
		layer.msg("没有当前模块权限", {icon: 2});
	}
}

function judgePower(power){
	if(userType == "超级管理员"){
		hasPower = true;
	}else{
		var powerList = $(".powerList").val().split("_");
		for (var i = 0; i < powerList.length-1; i++) {
			if (powerList[i] == power) {
				hasPower = true;
			}
		}
	}
}
