package org.example;

public class Main {

    public static void main(String[] args) {
        Wall wall = new Wall();
        System.out.println(wall.count());
        System.out.println(wall.findBlockByColor(""));
        wall.findBlocksByMaterial("wood").forEach(System.out::println);
    }
}