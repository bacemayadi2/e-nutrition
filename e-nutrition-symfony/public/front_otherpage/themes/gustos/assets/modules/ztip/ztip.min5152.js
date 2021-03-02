!function(t){"use strict";t.fn.zTip=function(i){if(this.length>1)return this.each(function(){t(this).zTip(i)}),this;var e,o=t.extend({theme:"default",source:"attr:title",position:"top"},i),r=this;t.each(o,function(t){var i=t.replace(/([A-Z])/g,"-$1").toLowerCase().toString(),e=r.data(i);(e||!1===e)&&(o[t]=e)});var n=function(){r.firstInit(),r.build()};return this.firstInit=function(){if(t("body").find(".ztip-holder").length<1){var i='<span class="zt-text"></span><span class="zt-arrow"></span>';t("body").append('<div class="ztip-holder ztip-position-top">'+i+"</div>")}e=t("body").find(".ztip-holder")},this.build=function(){r.on("mouseover",function(){var i=t(this),o=r.getElemTip(i);if(o){r.refreshHolderTheme(),e.children(".zt-text").html(o);var n=r.getElementCoordinates(i);e.outerWidth()<n.width&&e.css({"max-width":n.width+"px"}),r.autoPosition(n),e.addClass("ztip-show")}}),r.on("mouseout",function(){e&&e.removeClass("ztip-show").css({top:0,right:"",bottom:"",left:"-110%","max-width":""})}),t(window).on("scroll resize",function(){e&&e.removeClass("ztip-show")})},this.getElemTip=function(i){var e=i.attr("title");return e&&i.attr("data-ztip-title",e).removeAttr("title"),"function"==typeof o.source?o.source.call(this,i):r.stringStartsWith(o.source,"attr:title")?i.attr("data-ztip-title"):r.stringStartsWith(o.source,"attr:")?i.attr(o.source.replace("attr:","")):r.stringStartsWith(o.source,">")?i.children(o.source.replace(">","")).html():t(o.source).html()},this.autoPosition=function(t){var i="",n="",s=r.getViewport();e.outerWidth()>s.width&&e.css({"max-width":s.width}),"bottom"===o.position?e.outerHeight()+10>t.fromBottom?(i=t.top-e.outerHeight()-10,r.changeHolderPosition("top")):(i=t.bottom+10,r.changeHolderPosition("bottom")):e.outerHeight()+10<t.top?(i=t.top-e.outerHeight()-10,r.changeHolderPosition("top")):(i=t.bottom+10,r.changeHolderPosition("bottom"));var h=e.outerWidth()/2,a=e.outerWidth()<s.width,l=(s.width-e.outerWidth())/2;h>t.centerX?(n=0,a&&l<t.left?n=l:t.fromRight+t.width>e.outerWidth()&&(n=t.left)):h<t.centerX&&s.width-t.centerX<h?(n=s.width-e.outerWidth(),a&&l<t.fromRight?n=l:t.right>e.outerWidth()&&(n=t.right-e.outerWidth())):n=t.centerX-e.outerWidth()/2,r.holderCss({top:""!==i?i+"px":"",left:""!==n?n+"px":""});var p=e[0].getBoundingClientRect(),d=t.centerX-p.left,c=e.children(".zt-arrow");c.css({left:d,"margin-left":-c.outerWidth()/2})},this.holderCss=function(t){var i={top:t.top||"",right:t.right||"",bottom:t.bottom||"",left:t.left||""};e.css(i)},this.getElementCoordinates=function(i){var e=i;i instanceof jQuery&&(e=i[0]);var o=e.getBoundingClientRect(),r=o.right-o.left,n=o.bottom-o.top;return{width:r,height:n,top:o.top,left:o.left,bottom:o.bottom,right:o.right,fromTop:o.top,fromLeft:o.left,fromBottom:t(window).innerHeight()-o.bottom,fromRight:t(window).innerWidth()-o.right,centerX:o.left+r/2,centerY:o.top+n/2}},this.getViewport=function(){return{width:t(window).innerWidth(),height:t(window).innerHeight()}},this.changeHolderPosition=function(t){r.replaceClass(/\bztip-position-\S+/g,"ztip-position-"+t)},this.refreshHolderTheme=function(){r.replaceClass(/\bztip-theme-\S+/g,"ztip-theme-"+o.theme)},this.replaceClass=function(t,i){e.hasClass(i)||e.removeClass(function(i,e){return(e.match(t)||[]).join(" ")}).addClass(i)},this.stringStartsWith=function(t,i,e){return t.substr(e||0,i.length)===i},n(),this}}(jQuery);