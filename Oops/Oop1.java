package Oops;

public class Oop1 {
        public static void main(String args[]){
            Pen p1 = new Pen();
             p1.setColor("Blue");
            System.out.println(p1.color);

            appStudent a1 = new appStudent();
                a1.username="janhavi";
                // a1.password="dbvhd";
        }
    }
    
    class Pen{
        String color;
        void getColor(String getColor){
            color = getColor;
        }
        void setColor(String setColor){
            color = setColor;
        }
    }

    class appStudent{
        public static final char[] uNAme = null;
        public String username;
        private String password;

        
    


    }
    
