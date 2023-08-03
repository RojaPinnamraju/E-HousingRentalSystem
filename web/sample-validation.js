function formValidation()
{
var fname = document.registration.FirstName;
//var mname = document.registration.MiddleName;
//var lname = document.registration.LastName;
var adaarnum = document.registration.AadharNo;
//var dog = document.registration.Date_Of_Reg;
//var dob = document.registration.DOB;
var mobile = document.registration.MobileNo;
var email = document.registration.Email;

//var addr_1 = document.registration.Address_Line1;
//var addr_2 = document.registration.Address_Line2;
//var state = document.registration.State;
//var district = document.registration.District;
//var zipcode = document.registration.Zipcode;
var username = document.registration.UserName;
var pass = document.registration.Password;
//var confpass = document.registration.Confirm_Pass;
if(fname_validation(fname))
{
if(Aadar_validation(adaarnum))
{
if(mobile_validation(mobile))
{
if(Email_validation(email))
{ 
if(userid_validation(username,8,15))
{
if(passid_validation(pass,8,15))
{
{
}
} 
}
} 
}
return false;

} 
function fname_validation(fname)
{ 
var letters = /^[A-Za-z]+$/;
if(fname.value.match(letters))
{
return true;
}
else
{
alert('Username must have alphabet characters only');
fname.focus();
return false;
}
}
function Aadar_validation(adaarnum)
{ 
var numbers = /^[0-9]+$/;
if(adaarnum.value.match(numbers) || adaarnum.length == 12)
{
return true;
}
else
{
alert('adhar num must have numeric characters only and length equals to 12');
adaarnum.focus();
return false;
}
}
function mobile_validation(mobile)
{ 
var numbers = /^[0-9]+$/;
if(mobile.value.match(numbers))
{
return true;
}
else
{
alert('num must have numeric characters only');
mobile.focus();
return false;
}
}
function Email_validation(email)
{
var mailformat = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;
if(email.value.match(mailformat))
{
return true;
}
else
{
alert("You have entered an invalid email address!");
email.focus();
return false;
}
} 

function userid_validation(username,mx,my)
{
var uid_len = username.value.length;
if (uid_len == 0 || uid_len >= my || uid_len < mx)
{
alert("User Id should not be empty / length be between "+mx+" to "+my);
username.focus();
return false;
}
return true;
}
function passid_validation(pass,mx,my)
{
var passid_len = pass.value.length;
if (passid_len == 0 ||passid_len >= my || passid_len < mx)
{
alert("Password should not be empty / length be between "+mx+" to "+my);
pass.focus();
return false;
}
return true;
}

/*
function allLetter(uname)
{ 
var letters = /^[A-Za-z]+$/;
if(uname.value.match(letters))
{
return true;
}
else
{
alert('Username must have alphabet characters only');
uname.focus();
return false;
}
}
function alphanumeric(uadd)
{ 
var letters = /^[0-9a-zA-Z]+$/;
if(uadd.value.match(letters))
{
return true;
}
else
{
alert('User address must have alphanumeric characters only');
uadd.focus();
return false;
}
}

function countryselect(ucountry)
{
if(ucountry.value == "Default")
{
alert('Select your country from the list');
ucountry.focus();
return false;
}
else
{
return true;
}
}
function allnumeric(uzip)
{ 
var numbers = /^[0-9]+$/;
if(uzip.value.match(numbers))
{
return true;
}
else
{
alert('ZIP code must have numeric characters only');
uzip.focus();
return false;
}
}
function ValidateEmail(uemail)
{
var mailformat = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;
if(uemail.value.match(mailformat))
{
return true;
}
else
{
alert("You have entered an invalid email address!");
uemail.focus();
return false;
}
} function validsex(umsex,ufsex)
{
x=0;

if(umsex.checked) 
{
x++;
} if(ufsex.checked)
{
x++; 
}
if(x==0)
{
alert('Select Male/Female');
umsex.focus();
return false;
}
else
{
alert('Form Succesfully Submitted');
window.location.reload()
return true;
}
}
*/ 
/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
}
}

