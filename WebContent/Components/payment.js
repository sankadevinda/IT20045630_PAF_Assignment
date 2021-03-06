$(document).ready(function()
{
if ($("#alertSuccess").text().trim() == "")
 {
 $("#alertSuccess").hide();
 }
 $("#alertError").hide();
});




//SAVE ============================================
$(document).on("click", "#btnSave", function(event)
{
// Clear alerts---------------------
 $("#alertSuccess").text("");
 $("#alertSuccess").hide();
 $("#alertError").text("");
 $("#alertError").hide();
// Form validation-------------------
var status = validateUserForm();
if (status != true)
 {
 $("#alertError").text(status);
 $("#alertError").show();
 return;
 }

var type = ($("#hididSave").val() == "") ? "POST" : "PUT";


$.ajax(
		{
		 url : "PaymentAPI",
		 type : type,
		 data : $("#formUser").serialize(),
		 dataType : "text",
		 complete : function(response, status)
		 {
		 onUserSaveComplete(response.responseText, status);
		 }
		});

});
function onUserSaveComplete(response, status)
{
if (status == "success")
 {
	var resultSet = JSON.parse(response);
	if (resultSet.status.trim() == "success")
	{
		$("#alertSuccess").text("Successfully saved.");
		$("#alertSuccess").show();
		
		$("#divUserGrid").html(resultSet.data);
	} else if (resultSet.status.trim() == "error")
	{
		$("#alertError").text(resultSet.data);
		$("#alertError").show();
	}
 	} else if (status == "error")
 	{
 		$("#alertError").text("Error while saving.");
 		$("#alertError").show();
 	} else
 	{
 		$("#alertError").text("Unknown error while saving..");
 		$("#alertError").show();
 	}
		$("#hididSave").val("");
		$("#formUser")[0].reset();
}

//UPDATE==========================================
$(document).on("click", ".btnUpdate", function(event)
{
 $("#hididSave").val($(this).closest("tr").find('#hididUpdate').val());
 $("#customer_name").val($(this).closest("tr").find('td:eq(0)').text());
 $("#account_number").val($(this).closest("tr").find('td:eq(1)').text());
 $("#date").val($(this).closest("tr").find('td:eq(2)').text());
 $("#payment_type").val($(this).closest("tr").find('td:eq(3)').text());
 $("#total_price").val($(this).closest("tr").find('td:eq(4)').text());
});


$(document).on("click", ".btnRemove", function(event)
		{
		 $.ajax(
		 {
		 url : "PaymentAPI",
		 type : "DELETE",
		 data : "id=" + $(this).data("itemid"),
		 dataType : "text",
		 complete : function(response, status)
		 {
		 onUserDeleteComplete(response.responseText, status);
		 }
		 });
		});

function onUserDeleteComplete(response, status)
{
if (status == "success")
 {
 var resultSet = JSON.parse(response);
 if (resultSet.status.trim() == "success")
 {
 $("#alertSuccess").text("Successfully deleted.");
 $("#alertSuccess").show();
 $("#divUserGrid").html(resultSet.data);
 } else if (resultSet.status.trim() == "error")
 {
 $("#alertError").text(resultSet.data);
 $("#alertError").show();
 }
 } else if (status == "error")
 {
 $("#alertError").text("Error while deleting.");
 $("#alertError").show();
 } else
 {
 $("#alertError").text("Unknown error while deleting..");
 $("#alertError").show();
 }
}

//CLIENTMODEL=========================================================================
function validateUserForm()
{
 if ($("#customer_name").val().trim() == "")
	 {
		return "Insert customer name.";
	 }
 
 if ($("#account_number").val().trim() == "")
	 {
		 return "Insert acc number name.";
	 }
 
 if ($("#date").val() == "0")
	 {
	 	return "Select date.";
	 }
 if ($("#payment_type").val() == "0")
	 {
	 	return "Select type.";
	 }
 
  if ($("#total_price").val() == "0")
	 {
	 	return "input price.";
	 }
return true;
}


