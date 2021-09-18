package rus.google.clientserver;

class Main {

    public static void main(final String ... args) {

        Thread one = new Thread(() -> Server.main(args));
        Thread two = new Thread(() -> Client.main(args));

        one.start();
        two.start();

    }

}
