/**
 * 
 */
 // var dateFrFormat = /^(\d{1,2})(\/|-)(\d{1,2})(\/|-)(\d{4})$/;
	var dateFrFormat = "[0-9]{2}-[0-9]{2}-[0-9]{4}";
	//	var dateFrFormat ="(O?[1-9]|[12][0-9]|3[01])/(0?[1-9]|1[012])/((19|20)[0-9][0-9])"
	//var dateFrFormat =" ^(?:(?:31(\/|-|\.)(?:0?[13578]|1[02]))\1|(?:(?:29|30)(\/|-|\.)(?:0?[1,3-9]|1[0-2])\2))(?:(?:1[6-9]|[2-9]\d)?\d{2})$|^(?:29(\/|-|\.)0?2\3(?:(?:(?:1[6-9]|[2-9]\d)?(?:0[48]|[2468][048]|[13579][26])|(?:(?:16|[2468][048]|[3579][26])00))))$|^(?:0?[1-9]|1\d|2[0-8])(\/|-|\.)(?:(?:0?[1-9])|(?:1[0-2]))\4(?:(?:1[6-9]|[2-9]\d)?\d{2})$"
	var isNameValidate = false;
	var isIntriducedValidate = false;
	var isDiscontinued = false;

	var verifInput = function(divId,inputId,inputExpected){		 			
		if($(inputId).val()==inputExpected  || $(inputId).val()==""){
  				$(divId).toggleClass("has-success",true)
  				$(divId).toggleClass("has-error", false)
  				return true;
  			}else{
  				$(divId).toggleClass("has-success",false)
  				$(divId).toggleClass("has-error",true)
  				return false;
  			}
	};

	var checkDateFormat = function(date){
		var tableau = date.split('-')
			alert(tableau[2])
		
	}


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
			checkDateFormat("19-09-1992");

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


		    $("#addComputerButton").click(function() {
		    	var introducedDate = $("#introduced").val().match(dateFrFormat)
		    	var discontinuedDate = $("#discontinued").val().match(dateFrFormat)
		     	alert(str)
		    })
		  })
	