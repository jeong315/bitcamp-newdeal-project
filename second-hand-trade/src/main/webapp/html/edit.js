'use strict'
  $.getJSON(`${serverApiAddr}/json/main/mainTest`, (result) => {
        if (result.status !== 'success') {
            return;
        }
       console.log(result.loginUser.name);
       console.log(result.loginUser.no);
       console.log(result.loginUser.profilephoto)
       var name =  $('#login-name').html();
       var name1 =  $('._gvhl0').html();
       $('#login-name').html(result.loginUser.name);
       $('._gvhl0').html(result.loginUser.name);
  	 $.getJSON(`${serverApiAddr}/json/edit/`+name, (result) => {
			console.log(result);
			
//		    if (result.status !== 'success') {
//		 
//	            return;
//	        }
		   $('#f-name').val(result.data.name);
	       $('#f-email').val(result.data.email);
	       $('#f-nickname').val(result.data.nickname);
	       $('#f-website').val(result.data.website);
	       $('#f-tel').val(result.data.phonenumber);
	       $('#f-email').val(result.data.email);
	       //$('#f-intro').val(result.data.intro);
	       $('#f-intro').html(result.data.intro);
	       console.log(result.data.intro);
	       console.log(result.data.profilephoto);
	       
	       var fileInfo = getFileInfo(result.data.profilephoto);
			//프로필 사진 수정
			$("#btnChangePhoto").children("img").attr("src", fileInfo.imgsrc);
    })
  });
    
    $('#login-name').on('click',()=>{
    		console.log('이름')
    		 var name =  $('#login-name').html();
    		console.log()
    		
    		 $.getJSON(`${serverApiAddr}/json/edit/`+name, (result) => {
    			console.log(result);
    			console.log("----")
    			console.log(result.status);
//    		    if (result.status !== 'success') {
//    		 
//    	            return;
//    	        }
    		   	  $('#f-name').val(result.data.name);
   		       $('#f-email').val(result.data.email);
   		       $('#f-nickname').val(result.data.nickname);
   		       $('#f-website').val(result.data.website);
   		       $('#f-tel').val(result.data.phonenumber);
   		       $('#f-email').val(result.data.email);
   		       $('#f-intro').val(result.data.intro);
   		       console.log(result.data.intro);
   		    location.href=`${serverApiAddr}/html/edit.html`;
    			
    		 })
    })	

 

