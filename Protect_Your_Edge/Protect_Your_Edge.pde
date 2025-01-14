color backgroundColor = color(250);
boolean up1 = false;
boolean down1 = false;
boolean up2 = false;
boolean down2 = false;
int count1;//score1 counter
int count2; //score2 counter
color circleColor = color (30);
color rectColor = color(90);
int rWidth= 10;
int rHeight = 60;

// First paddle(player1) variables
int x1 = 10;
int y1 = 220;

//Second Paddle (Player 2)
int x2 = 480;
int y2 = 220;

// Ball variables
float x, y;
float speed = 5;
float cSize = 20; 
int cVelX=0;
int cVelY=0;

void setup() {
  size (500, 500);
  x = 30 ;
  y = 250 ;
  cVelX=(int) random(5,5);
  cVelY=(int) random(3,5);
}

void draw(){
  background(backgroundColor);
  fill ( rectColor);
  
     // Let's draw the Player1
  rect(x1, y1, rWidth, rHeight);
  textSize(20);
  text ( " Player1: " +count1, 10, 20);
  
     // Let's draw the Player2
  rect(x2, y2, rWidth, rHeight);
  textSize(20);
  text ( " Player2: " +count2, 400, 20);
  
  // Let's draw the ball
  fill (0, 0, 250);
  circle ( x, y, cSize);
  
  // calculate the position of the ball based on the velocity and draw our ball
  x= x + cVelX;
  y = y + cVelY;
  
  // Check the collision between the ball and the paddle2
   if (x + cSize >= x2 &&  y+cSize >= y2 && y+cSize <= y2 +rHeight) {
    cVelX = -cVelX;
  }
  
  // Let's check the collision between the ball and the paddle1
   if (x - cSize <= x1 &&  y+cSize >= y1 && y + cSize <= y1 +rHeight) {
    cVelX = -cVelX;
  }
 
 //Let's set the positions max of the padddle
 if( y1 == 20){
   up1 = false;
 }
 if(y1+rWidth == height){
   down1 = false;
 }
 if( y2 == 20){
   up2 = false;
 }
 if(y2+rWidth == height){
   down2 = false;
 }
  
  //if (count1==10 && x - cSize > x1+rWidth) {
  //  text ( "You win!", 150, 150);
  //  textSize(50);
   // cVelX=0;
  //  cVelY=0;
  //}
  // Collision with the up and down edges
  if ( y+cSize>=height || y-cSize<=0){
    cVelY=-cVelY;
  }
  if ( x-cSize == 0){
    count2++;
    cVelX = -cVelX;
  }
  if (x+cSize == width){
    count1++;
    cVelX = -cVelX;
  }
  
  
  // Let's move the first paddle
  if (up1 == true) {
    y1 = y1-5;
  }
  if(down1 == true){
    y1 = y1 + 5;
  }
  // Let's move the second paddle
  if (up2 == true) {
    y2 = y2-5;
  }
  if(down2 == true){
    y2 = y2 + 5;
  }
}

void keyPressed() {
  // Let's move the first player
  // w is for up and s for down
  if (key == 'w'){
    up1 =true;
  }
  if ( key == 's') {
    down1 = true;
  }
  if (key == 'o'){
    up2 =true;
  }
  if ( key == 'l') {
    down2 = true;
  }
}
void keyReleased() {
  if (key == 'w') {
    up1 = false;
  }
  if (key == 's') {
    down1 = false;
  }
  if (key == 'o') {
    up2 = false;
  }
  if (key == 'l') {
    down2 = false;
  }
}
