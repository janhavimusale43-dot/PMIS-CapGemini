package Day10;

class Locker<T> {
    private T item;

    void put (T item) {
        this.item = item;
    }

    T get(){
        return item;
    }
}
class generic4 {
    public static void main(String[] args) {
        Locker<String> I1 = new Locker<>();
        I1.put("C++ book");
        System.out.println("The locker has: " + I1.get());

        Locker<Integer> intLocker = new Locker<>();
        intLocker.put(1234);
        intLocker.put(8910);

        System.out.println("IntLocker has: " + intLocker.get());
    }
}
