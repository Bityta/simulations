package Logic;


import Models.*;

public class Actions {

    public Map map;

    public void initActions() {

        map = new Map();

        map.addEntity(new Grass());
        map.addEntity(new Grass());
        map.addEntity(new Grass());
        map.addEntity(new Grass());
        map.addEntity(new Grass());
        map.addEntity(new Grass());
        map.addEntity(new Grass());
        map.addEntity(new Grass());
        map.addEntity(new Grass());
        map.addEntity(new Grass());
        map.addEntity(new Grass());
        map.addEntity(new Grass());
        map.addEntity(new Grass());
        map.addEntity(new Grass());
        map.addEntity(new Grass());
        map.addEntity(new Grass());
        map.addEntity(new Grass());
        map.addEntity(new Grass());


        map.addEntity(new Tree());
        map.addEntity(new Tree());
        map.addEntity(new Tree());
        map.addEntity(new Tree());
        map.addEntity(new Tree());
        map.addEntity(new Tree());
        map.addEntity(new Tree());
        map.addEntity(new Tree());



        map.addEntity(new Rock());
        map.addEntity(new Rock());
        map.addEntity(new Rock());
        map.addEntity(new Rock());
        map.addEntity(new Rock());
        map.addEntity(new Rock());
        map.addEntity(new Rock());
        map.addEntity(new Rock());
        map.addEntity(new Rock());
        map.addEntity(new Rock());
        map.addEntity(new Rock());
        map.addEntity(new Rock());


        map.addEntity(new Herbivore());
        map.addEntity(new Herbivore());
        map.addEntity(new Herbivore());
        map.addEntity(new Herbivore());
        map.addEntity(new Herbivore());
        map.addEntity(new Herbivore());
        map.addEntity(new Herbivore());
        map.addEntity(new Herbivore());
        map.addEntity(new Herbivore());
        map.addEntity(new Herbivore());
        map.addEntity(new Herbivore());
        map.addEntity(new Herbivore());



        map.addEntity(new Predator());
        map.addEntity(new Predator());
        map.addEntity(new Predator());
        map.addEntity(new Predator());
        map.addEntity(new Predator());




        map.fillingMap();


    }


    public void turnActions() {

        map.step();
        map.fillingMap();

        if(map.isSimulationOver){
            System.out.println();
            System.out.println("\u001B[31m" + " ".repeat(20) + "СИМУЛЯЦИЯ ЗАВЕРШЕНА" + "\u001B[0m");

        }

    }


}
