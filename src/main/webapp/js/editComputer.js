/**
 * 
 */

	var dateFrFormat ="(O?[1-9]|[12][0-9]|3[01])-(0?[1-9]|1[012])-((19|20)[0-9][0-9])"
	var isNameValidate = false;
	var isIntriducedValidate = false;
	var isDiscontinued = false;

	var verifInput = function(divId,inputId,inputExpected){	
		if(inputExpected!=null  || $(inputId).val()==""){
  				$(divId).toggleClass("has-success",true)
  				$(divId).toggleClass("has-error", false)
  				return true;
  			}else{
  				$(divId).toggleClass("has-success",false)
  				$(divId).toggleClass("has-error",true)
  				return false;
  			}
	};


	var verifyInputNotEmpty = function(divId,inputId){		
  			
		if($(inputId).val()!=""){
  				$(divId).toggleClass("has-success",true)
  				$(divId).toggleClass("has-error", false)
  				return true;
  			}else{
  				$(divId).toggleClass("has-success",false)
  				$(divId).toggleClass("has-error",true)
  				return false;
  			}
  			
	};


	var checkForm = function(){
		var introducedDate = $("#introduced").val().match(dateFrFormat)
	  	var discontinuedDate = $("#discontinued").val().match(dateFrFormat)
	  	
	  	var nameFull = verifyInputNotEmpty("#divName","#computerName")
	  	var expectedIntroduced2 = verifInput("#divIntroduced","#introduced",introducedDate)
		var expectedDiscontinued2 = verifInput("#divDiscontinued","#discontinued",discontinuedDate)

		return (nameFull&&expectedIntroduced2&&expectedDiscontinued2)
	}



	  $(function() {
			
			checkForm()
			console.log("ok");
	  		$("#computerName").keyup(function(){
	  		verifyInputNotEmpty("#divName","#computerName");
	  		})

			$("#introduced").keyup(function(){
	  			var introducedDate = $("#introduced").val().match(dateFrFormat)			  
	  			verifInput("#divIntroduced","#introduced",introducedDate);	  	   		
	
	  		})

	  	    $("#discontinued").keyup(function(){
		    	var discontinuedDate = $("#discontinued").val().match(dateFrFormat)
				verifInput("#divDiscontinued","#discontinued",discontinuedDate);
	  		})


		    
		  })
	