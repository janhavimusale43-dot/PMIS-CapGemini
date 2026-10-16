package Day10;

class util{
    static <T> void printTwice(T value) {
        System.out.println(value);
        System.out.println(value);
    }

    static <T> T firstof(T a, T b){
        return b;
    }
}
class generic3 {
    public static void main(String[] args){
        util.printTwice(100);
        System.out.println(util.firstof("hii", "hello"));
    }
}
