int main() {
	char arr[14] = {'I',' ','L','O','V','E',' ','C','I','T','5','9','3','\0'} ; //variable declaration
	int *arr_ptr = 0 ; //pointer declaration
	//arr_ptr = &arr ; //arr_ptr now addr of where char array to print starts

	lc4_puts (&arr) ;

	return (0) ;
}
