<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<style type="text/css">
	.divScrollToTop{
				height: 45px;
			    width: 45px;
			    bottom: 210px;
			    right: 50px;
			    position: fixed;
			    cursor: pointer;
				font-size: 14px;
				color: #333333;
				text-align: center;
				text-shadow: 0 1px 1px rgba(255, 255, 255, 0.75);
				vertical-align: middle;
				background-color: #f5f5f5;
				*background-color: #e6e6e6;
				background-image: -moz-linear-gradient(top, #ffffff, #e6e6e6);
				background-image: -webkit-gradient(linear, 0 0, 0 100%, from(#ffffff), to(#e6e6e6));
				background-image: -webkit-linear-gradient(top, #ffffff, #e6e6e6);
				background-image: -o-linear-gradient(top, #ffffff, #e6e6e6);
				background-image: linear-gradient(to bottom, #ffffff, #e6e6e6);
				background-repeat: repeat-x;
				border: 1px solid #cccccc;
				*border: 0;
				border-color: #e6e6e6 #e6e6e6 #bfbfbf;
				border-color: rgba(0, 0, 0, 0.1) rgba(0, 0, 0, 0.1) rgba(0, 0, 0, 0.25);
				border-bottom-color: #b3b3b3;
				-webkit-border-radius: 4px;
				 -moz-border-radius: 4px;
				      border-radius: 4px;
				filter: progid:DXImageTransform.Microsoft.gradient(startColorstr='#ffffffff', endColorstr='#ffe6e6e6', GradientType=0);
				filter: progid:DXImageTransform.Microsoft.gradient(enabled=false);
				*zoom: 1;
				-webkit-box-shadow: inset 0 1px 0 rgba(255, 255, 255, 0.2), 0 1px 2px rgba(0, 0, 0, 0.05);
				 -moz-box-shadow: inset 0 1px 0 rgba(255, 255, 255, 0.2), 0 1px 2px rgba(0, 0, 0, 0.05);
				      box-shadow: inset 0 1px 0 rgba(255, 255, 255, 0.2), 0 1px 2px rgba(0, 0, 0, 0.05);

			}

			.divScrollToTop:hover,
			.divScrollToTop:focus,
			.divScrollToTop:active,{
			  color: #333333;
			  background-color: #e6e6e6;
			  *background-color: #d9d9d9;
			}

			.divScrollToTop:hover,
			.divScrollToTop:focus {
			  color: #333333;
			  text-decoration: none;
			  background-position: 0 -45px;
			  -webkit-transition: background-position 0.1s linear;
			     -moz-transition: background-position 0.1s linear;
			       -o-transition: background-position 0.1s linear;
			          transition: background-position 0.1s linear;
			}

			.divScrollToTop:focus {
			  outline: thin dotted #333;
			  outline: 5px auto -webkit-focus-ring-color;
			  outline-offset: -2px;
			}

			.divScrollToTop:active {
			  background-image: none;
			  outline: 0;
			  -webkit-box-shadow: inset 0 2px 4px rgba(0, 0, 0, 0.15), 0 1px 2px rgba(0, 0, 0, 0.05);
			     -moz-box-shadow: inset 0 2px 4px rgba(0, 0, 0, 0.15), 0 1px 2px rgba(0, 0, 0, 0.05);
			          box-shadow: inset 0 2px 4px rgba(0, 0, 0, 0.15), 0 1px 2px rgba(0, 0, 0, 0.05);
			}

			.offScreen{display: none;}
		</style>		
	</head>
	<body data-spy="scroll" data-target=".bs-docs-sidebar">
		<div id="divScrollToTop" class="offScreen"><span class="divScrollToTop" style="margin-top:10px;">返回<br>顶部</span></div>
		
		<hr/>
		<footer class="bs-docs-footer" role="contentinfo">
			<div class="container">
		        <p class="text-muted" align="center">&copy;homepy&nbsp;&nbsp;2015</p>
			</div>
	    </footer>

	    <script type="text/javascript" src="js/jquery-1.11.1.min.js"></script>

		<script type="text/javascript">
			// 平滑滚动到页面顶部
			var scrollToTop = {
			    setup:function(){
			        var h=100;//$(window).height()/4;临界值，表示滚动超过该值(100px)时，让返回顶部按钮可见
			        var t=$("#divScrollToTop");
			        $(window).scroll(function(){
			        	var offsetH=window.innerWidth?window.pageYOffset:document.documentElement.scrollTop;
			        	//console.log(h2+"-"+h+"="+(h2-h));
			            (offsetH>=h)?$(t).removeClass("offScreen"):$(t).addClass("offScreen");
			        });
			        $(t).click(function(){
			            $("html, body").animate({scrollTop:"0px"},400);
			            return false;
			        });
			    }
			};

			$(function(){
				scrollToTop.setup();
			});

		</script>
	</body>
</html>
