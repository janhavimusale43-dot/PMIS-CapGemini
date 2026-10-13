package Day07;

class Battery {
    int capacitymaH;

    Battery(int capacitymaH) {
        this.capacitymaH = capacitymaH;
    }

    void drain() {
        System.out.println("Battery is draining...");
    }
}

class Camera {
    int pix;

    Camera(int pix) {
        this.pix = pix;
    }

    void takePhoto() {
        System.out.println("Click... Captured a photo with " + pix + " MP sensor");
    }
}

class Phone {
    String brand;
    Battery battery;
    Camera camera;

    Phone(String brand, Battery battery, Camera camera) {
        this.brand = brand;
        this.battery = battery;
        this.camera = camera;
    }

    void snapPicture() {
        System.out.println("[" + brand + "] is active.");
        camera.takePhoto();
        battery.drain();
    }
}