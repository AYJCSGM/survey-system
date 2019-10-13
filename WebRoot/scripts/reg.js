 var inputs=document.getElementById("register").getElementsByTagName("input");
   for(var i=0;i<inputs.length;i++){
      var txt = inputs[i];
      txt.onfocus=function(){
         var msgBox = this.nextSibling;
         msgBox.innerHTML="";
         msgBox.className="";
      }
      txt.onblur=function(){
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
   }