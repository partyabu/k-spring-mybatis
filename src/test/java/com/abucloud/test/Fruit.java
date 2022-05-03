package com.abucloud.test;

/**
 * ioc原理，通过工厂模式+反射创建对象
 */
interface Fruit {
    public abstract void eat();
}

class Apple implements Fruit {
    public void eat() {
        System.out.println("Apple");
    }
}

class Orange implements Fruit {
    public void eat() {
        System.out.println("Orange");
    }
}

class Factory {

    public static Fruit getInstance(String ClassName) {
        Fruit f = null;
        try {
            f = (Fruit) Class.forName(ClassName).newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return f;
    }
}

class Client {
    public static void main(String[] a) {
        Fruit f = Factory.getInstance("com.abucloud.test.Apple");
        if (f != null) {
            f.eat();
        }
    }
}