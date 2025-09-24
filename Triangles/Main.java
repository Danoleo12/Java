public class Main {
    
    public static void main(String[] args){
        Draw1();

        Draw2();
    }

    /*
     * Draw1()
     * 
     * Draw the triangle below
     * 
     * *
     * ***
     * *****
     * *******
     */

    public static void Draw1(){

        String star = "*";
        int j = 1; // Variable that keeps count of the number of stars being printed on each line

        for(int i=1; i<=4; i++){ //This loops goes from the first line to the last

            int temp = j; //Temporary variable to store the current number of stars
    
            while(j>0){ //Print a star while there are any (number of star > 0), then decrement the number of star
                System.out.print(star);
                j--; 
            }

            j = temp+2; //Update the number of star to the previous value (Value stored in the temp) + 2

            temp = j; //Update the temp to the new number of star
            System.out.println();   
        }

        System.out.println("End Draw1()");
    }


    /*
     * Draw2()
     * 
     * Draw the triangle below
     * 
     *            *
     *           ***
     *          *****
     *         *******
     */

     public static void Draw2(){
        String star = "*";
        int space = 12; // Variable to keep count of the number of space before stars
        int j = 1; // Variable that keeps count of the number of stars being printed on each line

        for(int i=1; i<=4; i++){ //This loops goes from the first line to the last

            int tempSpaceValue = space; // temporary value to store current space

            while(space > 0){ //pritn a space while there are any then decrement the number space
                System.out.print(" ");
                space -- ;
            }

            space = tempSpaceValue - 1;

            tempSpaceValue = space;

            int temp = j; //Temporary variable to store the current number of stars
    
            while(j>0){ //Print a star while there are any (number of star > 0), then decrement the number of star
                System.out.print(star);
                j--; 
            }

            j = temp+2; //Update the number of star to the previous value (Value stored in the temp) + 2

            temp = j; //Update the temp to the new number of star
            System.out.println();   
        }

        System.out.println("end Draw2()");
    }
}