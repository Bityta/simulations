package Logic;


import Models.*;
import Utils.Coordinate;

public class Actions {

    protected Map map;

    public void initActions() {

        map = new Map();

        map.addEntity(new Grass());
        map.addEntity(new Grass());
        map.addEntity(new Grass());
        map.addEntity(new Grass());

        map.addEntity(new Tree());
        map.addEntity(new Tree());

        map.addEntity(new Predator());

        map.addEntity(new Rock());
        map.addEntity(new Rock());

         map.addEntity(new Herbivore());
        map.addEntity(new Herbivore());




        map.fillingMap();


    }


    public void turnActions() {


        map.step();
        map.fillingMap();

    }


}
