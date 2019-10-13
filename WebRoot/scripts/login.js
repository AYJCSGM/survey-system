 var inputs=document.getElementById("loginBox").getElementsByTagName("input");
   for(var i=0;i<inputs.length;i++){
      var txt = inputs[i];
      if(txt.type=="text" || txt.type=="password"){
    	  txt.onfocus = function(){
			  this.className += " input-text-over";
			  
			  var msgBox = this.nextSibling;
		      msgBox.innerHTML="";
		      msgBox.className="";
		  }
		  txt.onblur = function(){
			  this.className = this.className.replace(/input-text-over/, "");
			  
			  var reg,msg;
		         if(this.name=="name"){
		            reg=/^\w{1,}$/gi;   
		            msg="用户名不能为空";         
		         }
		         else if(this.name=="pwd"){
		            reg=/^\w{6,}$/gi;
		            msg="用户名必填且最小长度6位";
		         }
		         var msgBox = this.nextSibling;
		         if(!reg.test(this.value)){
		            msgBox.innerHTML=msg;
		            msgBox.className="errorInfo"; 
		         }			  
		  }
      }if(txt.type == "submit") {
			txt.onmouseover = function(){
				this.className += " input-submit-over";
			}
			txt.onmouseout = function(){
				this.className = this.className.replace(/input-button-over/, "");
			}
	 }
      
      
   }