function editProfile(id){
	var userdetails = {};
	userdetails["id"]=id;
    userdetails["firstName"] = $("#firstname")[0].innerHTML;
    userdetails["lastName"] = $("#lastname")[0].innerHTML;
    userdetails["dob"] = $("#dob")[0].innerHTML;
	$.ajax({
		type : 'POST',
		url : '/editUser',
		contentType: "application/json; charset=utf-8",
		contentType: 'application/json',
		data : JSON.stringify(userdetails),
		success : function(data){
			SuccessCall(data);
		},
		error : function(e){
			console.log(e);
		}
	});
}

function SuccessCall(data){
	alert(data.status);
}
