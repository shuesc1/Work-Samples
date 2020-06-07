// josepma
// Joseph Haymaker
// run on Chrome on MacOS

// global vars
var num = '';
var old_number = '';
var operator = '';
var second_number = false;
var previous_oper = 'none'; // 'none', 'number', 'operator', 'equals', 'clear'

/* helper function for when 'clear' is pressed */
function reset(){
	//console.log('reset()');
	num = '';
	old_number = '';
	operator = '';
	second_number = false;
	previous_oper = 'none'; 
}

// =========== <<<<| 0 - 9 |>>>> ================
$('.numButton').click(handleNumber);
function handleNumber(){
	if(previous_oper == 'clear' || previous_oper == 'equals'){
		//console.log('number clicked, calling reset()');
		reset();
	} else if (previous_oper == 'operator'){
		second_number = true;
		//console.log('second_number: ' + second_number);
		num = '';
	}
	//console.log('num currently equals: ' + num);
	//whichever button is pressed, get its value 
	var which = $(this).val(); 
	// sanity check
	if(num == 0 || num == '') { 
		num = which ;
	} else {
		num += which ;
	}
	$('#output').html('value is ' + num);
	$('#display').val(num); // show in display on calculator
	//console.log('num now equals: ' + num);
	//console.log('second_number: ' + second_number);
	// set flag of previous operation
	previous_oper = 'number' ;
}

// =========== <<<<| +, -, *, / |>>>> ================
$('.operButton').click(handleOper);
function handleOper(){
	// case 7 - 1 operator clicked then another clicked right after (|6|,|+|,|*|,|2|,|=| --> '12')
	if(previous_oper == 'operator'){
		operator = $(this).text(); // just update operator, no other changes necessary
		//if($(this)==$('divideButton')){operator = '/'}
		//console.log('operator updated to: '+ operator);
	} else if (second_number == true && previous_oper == 'number'){ 
		// old_number, num and operator all have values
		//console.log('getOperResult() being called with args : '+ operator + ', ' + Number(old_number) + ', ' + Number(num));
		var result2 = getOperResult(operator, Number(old_number), Number(num)); //process results
		$('#output').html('value is ' + result2);
		$('#display').val(result2);
		old_number = result2 ;
		//console.log('old_number updated to: '+ old_number);
		operator = $(this).text();
		//if($(this)==$('divideButton')){operator = '/'}
		//console.log('operator updated to: '+ operator);
		previous_oper = 'operator';
	} else {
		operator = $(this).text();
		//if($(this)==$('divideButton')){operator = '/'}
		//console.log('operator updated to: '+ operator);
		if(previous_oper == 'number'){
			old_number = Number(num);
			//console.log('old_number is: '+ old_number);
		}
		//console.log('old_number is: '+ old_number);
		second_number = false;
		//console.log('second_number updated to: '+ second_number);
		previous_oper = 'operator';
	}
}

// =========== <<<<| = |>>>> ================
$('#equalsButton').click(handleEquals);
function handleEquals(){
	//console.log('handleEquals called: second_number==='+second_number, ', previous_oper=='+previous_oper);
	if(second_number === true && (previous_oper=='number'||previous_oper=='equals')){
		// have an old and current number and operator
		//console.log('getOperResult() being called with args : '+ operator + ', ' + old_number + ', ' + num);
		//console.log('getOperResult() being called with args : '+ operator + ', ' + Number(old_number) + ', ' + Number(num));
		var result1 = getOperResult(operator, Number(old_number), Number(num)); //process results
		$('#output').html('value is ' + result1);
		$('#display').val(result1);
		old_number = result1 ;
		previous_oper = 'equals';
	}

}

/* helper function to carry out desired operation */
function getOperResult(indicated_operator, prev_number, current_number){
	var result = 0;
	if(indicated_operator == '+'){
		result = prev_number + current_number ;
	} else if(indicated_operator == '-'){
		result = prev_number - current_number ;
	} else if(indicated_operator == '*'){
		result = prev_number * current_number ;
	} else if(indicated_operator == '/' || indicated_operator == '&#247;'){
		result = (prev_number/current_number) ;
	}
	//console.log("result from operation: " + result) ;

	return result;
}

// /* another helper function for possible floating point numbers */
// function to_float(float_num) {
// 	return (parseFloat(float_num).toFixed(2));
// }

// =========== <<<<| C |>>>> ================
$('#clearButton').click(handleClear);
function handleClear(){
	//console.log(num);
	reset();
	$('#output').html('value is ' + num);
	//$('#output').html('clearing values ' + num);
	$('#display').val(num);
	previous_oper = 'clear';
}

