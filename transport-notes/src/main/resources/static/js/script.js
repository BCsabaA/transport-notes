function setAddressDetails(groupIdentifier) {
   var textAddress = document.getElementById(groupIdentifier + "Address");
   var textComment = document.getElementById(groupIdentifier + "Comment");
   var addressName = document.getElementById(groupIdentifier + "NameDropdown").value;

   addressesArray = [...addresses];
   console.log(addressesArray);


   console.log(addressName);

   addressesArray.forEach(element => {
      console.log(addressesArray);
      if (element.name === addressName) {
         console.log("element found;");
         console.log(element);
            textAddress.value = element.address;
            textComment.value = element.comment;
      }
   });


}

