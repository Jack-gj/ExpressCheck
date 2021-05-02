/**
 * 
 */
function login(){
	if(CookieUtil.get("uid")!=null){
		document.write('<a href="javascript:void(0); onclick =logout()">退出登录</a>');
  //  $('#a_login').attr('href','javascript:void(0); onclick =logout()');

  //  $('#a_login').text('退出登录');
   }else{
	document.write('<a href="login.html">登录/注册</a>');
}
//	alert("hello");
}


function logout(){
	CookieUtil.unset("uid");
	$.ajax({
		type: "GET",//请求方式
		url:"ClearSession",
		charset: "utf-8",
		async:true,
		success: function(res) {//响应成功执行的
			console.log(res);
		},
		error: function(error){
			console.log(error);
			}						
		});
	location.reload();
}

function footer(){
	document.write('<div class="footer"><div class="footer_link"></div><div class="footer_cont">');
	document.writeln("<p>Copyright &copy; 2015 ExpressCheck All Rights Reserved.</p>");
	document.write ("<p>ZZU</p>");
	document.write("</div></div></div>");
}