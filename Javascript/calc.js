// josepma
// Joseph Haymaker
// run on Chrome on MacOS

// global vars
var num = '';
var old_number = '';
var operator = '';
var second_number = false;
var previous_oper = 'none'; // 'none', 'number', 'operation', 'equals', 'clear'

/* helper function for when 'clear' is pressed */
function reset(){
	num = '';
	old_number = '';
	operator = '';
	second_number = false;
	previous_oper = 'none'; 
}

// =========== <<<<| 0 - 9 |>>>> ================
$('.numButton').click(handleNumber);
function handleNumber(){
	console.log(num);
	//whichever button is pressed, get its value 
	var which = $(this).val(); 
	// sanity check
	$('#output').html('which is ' + which);
	if(num==0) { 
		num= which ;
	} else {
		num += which ;
	}
	$('#output').html('value is ' + num);
	$('#display').val(num); // show in display on calculator
	//old_number = Number(num);
	// set flag 
	previous_oper = 'number' ;
}



// =========== <<<<| +, -, *, / |>>>> ================
$('.operButton').click(handleOper);
function handleOper(){
	
}



// =========== <<<<| = |>>>> ================
$('#equalButton').click(handleEquals);
function handleEquals(){

}



// =========== <<<<| C |>>>> ================
$('#clearButton').click(handleClear);
function handleClear(){
	console.log(num);
	reset();
	$('#output').html('value is ' + num);
	//$('#output').html('clearing values ' + num);
	$('#display').val(num);
}

