int main() {
/*
;;; TRAP_DRAW_RECT - draws a colored rectangle on the screen
;;; Inputs    - R0 = x coordinate, upper-left corner of rectangle (0-127)
;;; 		  - R1 = y coordinate, upper-left corner of rectangle (0-123)
;;; 		  - R2 = length of the side of the rectangle -- (0-127)
;;; 		  - R3 – width of the side of the rectangle (0-123)
;;;			 -  R4 = color of rectangle  */
	int x_coord = 100;
	int y_coord = 60;
	int len = 40 ;
	int width = 20;
	int color =  0x7C00 ; //red

	lc4_draw_rect (x_coord, y_coord, len, width, color) ;

	return (0) ;
}
