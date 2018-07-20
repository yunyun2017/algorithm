package practice03;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 题目：
 * 宠物、 狗和猫的类如下：
 * public class Pet { private String type;
 * public Pet(String type) { this.type = type; }
 * public String getPetType() { return this.type; }}
 * public class Dog extends Pet { public Dog() { super("dog"); } }
 * public class Cat extends Pet { public Cat() { super("cat"); } }
 * 实现一种狗猫队列的结构,add():向其中加入cat或dog类；pollAll():把所有实例按进入的先后顺序弹出;pollDog():弹出最先放入的dog;
 * pollCat():弹出最先放入的cat;isEmpty():队列中是否还；isDogEmp有dog或者catty():队列中是否还有dog;
 * isCatEmpty():队列中是否还有cat
 * <p>
 * 思路：
 * 1. 用两个队列，dog队列和cat队列来实现；
 * 2. 对于pollAll()方法，要想知道先进入队列的是狗还是猫，需要给它们加时间戳，这样就能知道是谁先进入的队列
 * 3. 所以cat和dog队列不能仅仅放pet，需要放一个复合类型，该类型包含pet和时间戳
 */
public class CatAndDogQueue {
    public static class Pet {
        private String type;

        public Pet(String type) {
            this.type = type;
        }

        public String getPetType() {
            return this.type;
        }
    }

    public static class Dog extends Pet {
        public Dog() {
            super("dog");
        }
    }

    public static class Cat extends Pet {
        public Cat() {
            super("cat");
        }
    }

    /**
     * 用于封装pet和进队的时间戳
     */
    public static class PetEnterQueue {
        private Pet pet;
        private int count;

        public PetEnterQueue(Pet pet, int count) {
            this.pet = pet;
            this.count = count;
        }

        public Pet getPet() {
            return this.pet;
        }

        public int getCount() {
            return this.count;
        }

        public String getEnterPetType() {
            return this.pet.getPetType();
        }
    }

    public static class CatDogQueue {
        private Queue<PetEnterQueue> cat;
        private Queue<PetEnterQueue> dog;
        private int count;

        public CatDogQueue() {
            this.cat = new LinkedList<PetEnterQueue>();
            this.dog = new LinkedList<PetEnterQueue>();
            count = 0;
        }

        public void add(Pet pet) {
            if(pet.getPetType().equals("dog")){
                dog.add(new PetEnterQueue(pet,this.count++));
            }else if(pet.getPetType().equals("cat")){
                cat.add(new PetEnterQueue(pet,this.count++));
            }else{
                throw new RuntimeException("the type is error");
            }
        }

        public Pet pollAll(){
            if(dog.isEmpty() && cat.isEmpty()){
                throw new RuntimeException("the queue is empty!");
            }else if(!dog.isEmpty() && cat.isEmpty()){
                return dog.poll().getPet();
            }else if(dog.isEmpty() && !cat.isEmpty()){
                return cat.poll().getPet();
            }else{
                if(dog.peek().count<cat.peek().count){
                    return dog.poll().getPet();
                }else{
                    return cat.poll().getPet();
                }
            }
        }


        public Dog pollDog(){
            if(this.dog.isEmpty()){
                throw new RuntimeException("error, no dog!");
            }

            return (Dog) this.dog.poll().getPet();
        }

        public Cat pollCat(){
            if(this.cat.isEmpty()){
                throw new RuntimeException("error, no cat!");
            }

            return (Cat) this.cat.poll().getPet();
        }

        public boolean isEmpty(){
            if(dog.isEmpty() && cat.isEmpty()){
                return true;
            }
            return false;
        }

        public boolean isDogEmpty(){
            return this.dog.isEmpty();
        }

        public boolean isCatEmpty(){
            return this.cat.isEmpty();
        }

    }

    public static void main(String[] args) {
        CatDogQueue test = new CatDogQueue();

        Pet dog1 = new Dog();
        Pet cat1 = new Cat();
        Pet dog2 = new Dog();
        Pet cat2 = new Cat();
        Pet dog3 = new Dog();
        Pet cat3 = new Cat();

        test.add(dog1);
        test.add(cat1);
        test.add(dog2);
        test.add(cat2);
        test.add(dog3);
        test.add(cat3);

        test.add(dog1);
        test.add(cat1);
        test.add(dog2);
        test.add(cat2);
        test.add(dog3);
        test.add(cat3);

        test.add(dog1);
        test.add(cat1);
        test.add(dog2);
        test.add(cat2);
        test.add(dog3);
        test.add(cat3);
//        while (!test.isDogEmpty()) {
//            System.out.println(test.pollDog().getPetType());
//        }
        while (!test.isEmpty()) {
            System.out.println(test.pollAll().getPetType());
        }
    }

}
