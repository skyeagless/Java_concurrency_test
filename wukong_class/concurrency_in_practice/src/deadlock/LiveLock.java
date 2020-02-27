package deadlock;


import java.util.Random;

public class LiveLock {
    static class Spoon{
        private Diner owner;

        public Spoon(Diner owner) {
            this.owner = owner;
        }

        public Diner getOwner() {
            return owner;
        }

        public void setOwner(Diner owner) {
            this.owner = owner;
        }

        public synchronized void use(){
            System.out.printf("%s has eaten!",owner.name);
        }
    }

    static class Diner{
        private String name;
        private boolean isHungry = true;

        Diner(String name) {
            this.name = name;
        }

        public void eatWith(Spoon spoon,Diner spouse){
            while(isHungry){
                if(spoon.owner != this){
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    continue;
                }
                Random random = new Random();
                if(spouse.isHungry && random.nextInt(10) < 9){
                    System.out.println(name+":"+spouse.name+"你先吃");
                    spoon.setOwner(spouse);
                    continue;
                }
                spoon.use();
                isHungry = false;
                System.out.println(name+":吃完了");
                spoon.setOwner(spouse);
            }
        }

    }

    public static void main(String[] args) {
        Diner husband = new Diner("NL");
        Diner wife = new Diner("ZN");
        Spoon spoon = new Spoon(husband);
        new Thread(new Runnable() {
            @Override
            public void run() {
                husband.eatWith(spoon,wife);
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                wife.eatWith(spoon,husband);
            }
        }).start();

    }
}
