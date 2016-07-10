
$(document).ready(function()
{

    var contents = '';
    var uid=location.search.substring(1);  
    var lng='';
    var lat='';
    uid=uid.replace('uid=','');
	$.ajax({
	   url: 'http://localhost:8080/elderly/userController/getUserById/'+uid+'.do',        
	   dataType: 'json',        
	   type: 'GET',       
	   contentType:'application/json;charset=UTF-8',   		      
	})       		       
	.success(function (data, status, headers, config) {        		        		        		            
		lng=data.lng;
		lat=data.lat;
		var map = new BMap.Map("map1");          // 创建地图实例  
		var point = new BMap.Point(lng, lat);  // 创建点坐标  
		map.centerAndZoom(point, 15);                 // 初始化地图，设置中心点坐标和地图级别  
		map.addControl(new BMap.NavigationControl());
		var marker = new BMap.Marker(point);        // 创建标注    
		map.addOverlay(marker);                     // 将标注添加到地图中
		var local = new BMap.LocalSearch(map,   
              { renderOptions:{map: map}});      
		local.searchInBounds("医院", map.getBounds());
		var name=data.uName;
		$('#name').html(data.uName);
		$('#children').html("子女电话："+data.children);
		var geoc = new BMap.Geocoder(); 
		geoc.getLocation(point, function(rs){
			var addComp = rs.addressComponents;
			var address=addComp.province + ", " + addComp.city + ", " + addComp.district + ", " + addComp.street + ", " + addComp.streetNumber;
			$('#currentaddr').html("事发位置： "+address);
			var myDate = new Date();
			var month=myDate.getMonth()+1;
			var datatime=myDate.getFullYear()+'-'+month+'-'+myDate.getDate()+','+myDate.getHours()+':'+myDate.getMinutes();
			$('#message').html("您好,"+name+"老人于"+datatime+"在"+address+"发生紧急情况。");
		});        
		
		//$('.list_of_members').html(contents);
	})        		           	
	.error(function (data, status, headers, config) {        		                    		            	        		          
	});           
   

});//document
