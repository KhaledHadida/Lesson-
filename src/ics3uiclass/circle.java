package ics3uiclass;




//creates a circle when user specifies radius given as a parameter 
public class circle {

    public static void main(String[] args) { 
        int n = Integer.parseInt(args[0]);

        for (int i = -n; i <= n; i++) {
            for (int j = -n; j <= n; j++) {
                if (i*i + j*j <= n*n) System.out.print("* ");
                else                  System.out.print(". ");
            }
            System.out.println();
        }
    }
}