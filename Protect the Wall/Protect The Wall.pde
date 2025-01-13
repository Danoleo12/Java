color backgroundColor = color(250);
boolean up = false;
boolean down = false;
int count;//score counter
color circleColor = color (30);
color rectColor = color(90);
int rWidth= 10;
int rHeight = 60;

// First paddle(player1) variables
int x1 = 10;
int y1 = 220;

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
  cVelX=(int) random(10,10);
  cVelY=(int) random(3,5);
}

void draw(){
  background(backgroundColor);
  fill ( rectColor);
  
  // calculate the position of the ball based on the angle and draw our ball
  x= x + cVelX;
  y = y + cVelY;
  
  // Check the collision between the ball and the paddle and decrement counter
  if (x - cSize <= x1 &&  y+cSize >= y1 && y + cSize <= y1 +rHeight) {
    cVelX = -cVelX;
    if(count > 0){
      count--;
    }
  }
  if (count==500 && x - cSize > x1+rWidth) {
    text ( "You lose!", 150, 150);
    textSize(50);
    cVelX=0;
    cVelY=0;
  }
  
  //Check collision between the ball and the edge behind the paddles, increment counter
  if(x-cSize<=0){
    count++;
  }
  
  //Check the collision between the ball and our edges
  if ( x+cSize <= x1+rWidth || x >= width || x-cSize<=0 || x-cSize < x1-rWidth){
    cVelX = -cVelX;
  }
  if ( y+cSize>=height || y-cSize<=0){
    cVelY=-cVelY;
  }
  circle ( x, y, cSize);
  
   // Let's draw the Player1
  rect(x1, y1, rWidth, rHeight);
  textSize(20);
  text ( " Player1: " +count, 10, 20);
  
  if (up) {
    y1 = y1-5;
  }
  if(down){
    y1 = y1 + 5;
  }
}

void keyPressed() {
  // Let's move the first player
  // w is for up and s for down
  if (key == 'w'){
    up =true;
  }
  if ( key == 's') {
    down = true;
  }
}
void keyReleased() {
  if (key == 'w') {
    up = false;
  }
  if (key == 's') {
    down = false;
  }
}
