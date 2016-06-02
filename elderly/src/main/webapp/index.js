
$(document).ready(function()
{

    var contents = '';
		/*<div class="users">
		 <h4>姓名 <strong>XXX</strong></h4>
		<img src="images/photos/6.png" alt="" />
		<p>400 New Today </p>
		<a href="#"><i class="go"></i></a>
		<div class="clearfix"></div>
	</div>    	*/	
	$.ajax({
	   url: 'http://localhost:8080/elderly/userController/getEmergencyUsers.do',        
	   dataType: 'json',        
	   type: 'GET',       
	   contentType:'application/json;charset=UTF-8',   		      
	})       		       
	.success(function (datas, status, headers, config) {        		        		        		            
		$.each(datas, function(i, data){ 
		contents+='<div class="users">'+
		  '<h4>姓名: <strong>'+data.uName+'</strong></h4>'+
		  '<img src="'+data.pic+'" alt="" />'+
		  '<p>住址：'+data.uAddress+'</p>'+
		  '<a href="map.html?uid='+data.uId+'"><i class="go"></i></a><div class="clearfix"></div></div>';
		}); 
		$('.list_of_members').html(contents);
	})        		           	
	.error(function (data, status, headers, config) {        		                    		            	        		          
	});           
   



});//document
