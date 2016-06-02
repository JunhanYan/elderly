
$(document).ready(function()
{
$('#submit').click(function(){

    var avaliable='';
        		
        $.ajax({
        		   url: 'http://localhost:8080/elderly/loginController/adminlogin.do',
        		   dataType: 'json',
        		   type: 'POST',
        		   contentType:'application/json;charset=UTF-8',
        		   data: JSON.stringify({
        		            	adminName : $('#username').val(),
        		            	adminPass : $('#password').val(),
        		            	           	
        		            })
        		           	})
        		            .success(function (data, status, headers, config) {
        		            	
        		            	if(data!=null)
        		            		window.location.href = 'index.html';
        		            	else{
        		            		alert("帐号或密码错误");
        		            	}
        		            	
        		            })
        		            .error(function (data, status, headers, config) {
        		            	
        		            });           
   
});



});//document
