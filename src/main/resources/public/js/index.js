$(document).ready(function() {
    $("button#createBook").on("click", function (){
        $.get("/createaddressbookrest", function (data,status){
            $("#test").html("The id is " + data.id)
            $("#buddies").html("The buddies " + data.buddies)
            console.log(data)
            document.getElementById("buddyForm").style.display = "inline"
            document.getElementById("getAllBooks").style.display = "inline"
            document.getElementById("bookForm").style.display = "inline"
        });
    });

    $("#getAllBooks").on("click", function (data){
        $.get("/getAllBooksrest", function (data,status) {
            console.log(data)
            $("#test").html("All addressbooks: " + data.addressBooks)
            $("#buddies").html(" ")
        });
    });

    $("#bookForm").on("submit", function (e){
        e.preventDefault();
        let bookId = $("#bookid").val()
        $.get("/getaddressbookrest",{id:bookId},function (data){
            console.log(data)
            $("#test").html("Address Id: " + data.id)
            $("#buddies").html("Buddies:  " +data.buddies)

        });
    });


    $("form#buddyForm").on("submit", function (e) {
        e.preventDefault();

        let id= $("#id").val() // Replace with your actual data
        let name= $("#name").val() // Replace with your actual data
        let phone = $("#phone").val() // Replace with your actual data
        let address =$("#address").val() // Replace with your actual data



        // Define the data you want to send in the request



// Make the AJAX POST request
        $.ajax({
            url: "/addbuddyrest",
            type: "post",
            dataType: "json",
            data: {
                id: id, // Replace with your actual data
                name: name, // Replace with your actual data
                phone: phone, // Replace with your actual data
                address: address // Replace with your actual data
            },
            success: function(data) {
                $("#addbud").html(
                    "The AddressBook with id: " + data.id+
                " Added buddy " + data.buddies)
            },
            error : function(xhr, ajaxOptions, thrownError){
                alert(xhr.status);
                alert(thrownError);
            }
        });
    });

});