<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%  
	String path = request.getContextPath();  
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";  
%> 
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Believe love!</title>
</head>
<body>
<style>
	body{
		margin:0;
		background:#393;
		background: 
		radial-gradient(circle closest-side at 60% 43%, #b03 26%, rgba(187,0,51,0) 27%),
		radial-gradient(circle closest-side at 40% 43%, #b03 26%, rgba(187,0,51,0) 27%),
		radial-gradient(circle closest-side at 40% 22%, #d35 45%, rgba(221,51,85,0) 46%),
		radial-gradient(circle closest-side at 60% 22%, #d35 45%, rgba(221,51,85,0) 46%),
		radial-gradient(circle closest-side at 50% 35%, #d35 30%, rgba(221,51,85,0) 31%),

		radial-gradient(circle closest-side at 60% 43%, #b03 26%, rgba(187,0,51,0) 27%) 50px 50px,
		radial-gradient(circle closest-side at 40% 43%, #b03 26%, rgba(187,0,51,0) 27%) 50px 50px,
		radial-gradient(circle closest-side at 40% 22%, #d35 45%, rgba(221,51,85,0) 46%) 50px 50px,
		radial-gradient(circle closest-side at 60% 22%, #d35 45%, rgba(221,51,85,0) 46%) 50px 50px,
		radial-gradient(circle closest-side at 50% 35%, #d35 30%, rgba(221,51,85,0) 31%) 50px 50px;
		background-color:#b03;
		background-size:100px 100px;
		
		
	}
	div{
		color:#fff;
		font-family:微软雅黑;
		font-style:italic;
		margin:0 auto;
		font-size:32px;
		font-weight:bold;
		text-align:center;
		text-shadow: 1px 1px #333;
	}
	
	.title {
		margin: 70px auto;
		padding: 30px 10px;
	}
	.time {
		position: relative;
		height: 50px;
		line-height: 50px;
		font-size: 24px;
		width:320px;
		text-align:left;

	}
	.time span.int {
		
	}
	.time span.dian {
		margin: 0 5px;
	}
	.time span.float {
		font-size: 16px;
	}
	.time span.unit {
		position: absolute;
		right: 0;
		font-size: 22px;
		bottom: 0;
	}
</style>
<div class="title">Love crystal From June 20, 2013 to now, has passed</div>
<div class="time" id="year"></div>
<div class="time" id="day"></div>
<div class="time" id="hours"></div>
<div class="time" id="mins"></div>
<div class="time" id="sec"></div>
<script>
	var start = new Date("2013-06-20T12:00:00") -0;
	
	function trans(times, ws){
		times = (new Number(times)).toFixed(ws);
		var val = (times + '.0').split(".");
		return '<span class="int">' + val[0] + '</span><span class="dian">.</span><span class="float">' + val[1] + '</span>';
	}
	
	function t() {
		var now = new Date() - 0;
		var chaS = (now - start)/1000;
		document.getElementById("year").innerHTML =  trans(chaS/(60*60*24*365), 12)+'<span class="unit">Years</span>';
		document.getElementById("day").innerHTML =  trans(chaS/(60*60*24), 9) +'<span class="unit">Days</span>';
		document.getElementById("hours").innerHTML =  trans(chaS/(60*60), 7) +'<span class="unit">Hours</span>';
		document.getElementById("mins").innerHTML =  trans(chaS/60, 5) +'<span class="unit">Minutes</span>';
		document.getElementById("sec").innerHTML =  trans(chaS, 3) +'<span class="unit">Seconds</span>';
	}
	t();
	setInterval(t,1000);
	
</script>
</body>
</html>
