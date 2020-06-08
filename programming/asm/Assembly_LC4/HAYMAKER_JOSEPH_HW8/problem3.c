int main() {

	char c = '' ;		/* ASCII A = x41 in hex, #65 in dec. */
	char enter = 0x0A ;
	
	while(c != enter){
	  lc4_getc (c) ;
	  lc4_putc (c) ;
	} 

	return (0) ;
}
