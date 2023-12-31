package Logic;

import Models.*;
import Utils.AStar;
import Utils.Node;
import Utils.Coordinate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;


public class Map {

    private Entity[][] map;
    private HashMap<Coordinate, Entity> entityMap;

    protected boolean isSimulationOver = false;

    public Map() {
        map = new Entity[widthMap][heightMap];
        entityMap = new HashMap<>();
    }

    private static final int widthMap = 10;
    private static final int heightMap = 10;


    public Entity[][] getMap() {
        return map;
    }

    public void fillingMap() {


        for (var entry : entityMap.entrySet()) {

            getMap()[entry.getKey().getCoordinateX()][entry.getKey().getCoordinateY()] = entry.getValue();

        }


    }

    public static int getWidthMap() {
        return widthMap;
    }

    public static int getHeightMap() {
        return heightMap;
    }

    public void addEntity(Entity entity) {


        if (!isOccupiedPosition(entity.getCoordinate())) {
            entityMap.put(entity.getCoordinate(), entity);

        } else {
            entity.setRandomCoordinate();
            addEntity(entity);
        }


    }

    private boolean isOccupiedPosition(Coordinate coordinate) {

        for (var entry : entityMap.entrySet()) {

            if (entry.getKey().getCoordinateX() == coordinate.getCoordinateX() && (entry.getKey().getCoordinateY() == coordinate.getCoordinateY())) {
                return true;
            }
        }

        return false;

    }

    public void deleteEntity(Coordinate coordinate) {

        entityMap.remove(coordinate);
        getMap()[coordinate.getCoordinateX()][coordinate.getCoordinateY()] = null;

    }

    public void step() {


        for (var entry : getCreatureHashMap().entrySet()) {



            Coordinate coordinatesToTarget = getNearestTargetCoordinate(entry.getValue());


            if (coordinatesToTarget == null || !checkExistenceEntity(entry.getKey())) continue;


            Node initialNode = new Node(entry.getValue().getCoordinate().getCoordinateY(), entry.getValue().getCoordinate().getCoordinateX());
            Node finalNode = new Node(coordinatesToTarget.getCoordinateY(), coordinatesToTarget.getCoordinateX());


            AStar aStar = new AStar(getWidthMap(), getHeightMap(), initialNode, finalNode);
            aStar.setBlocks(getBlocksArray(entry.getValue()));


            List<Node> path = aStar.findPath();

            if (path.size() == 2) deleteEntity(coordinatesToTarget);
            if (path.size() == 0) continue;

            deleteEntity(entry.getKey());
            entry.getValue().setCoordinate(new Coordinate(path.get(1).getCol(), path.get(1).getRow()));
            addEntity(entry.getValue());

            updateSimulationOver();

        }

    }

    private ArrayList<Grass> getGrassArray() {

        ArrayList<Grass> grassList = new ArrayList<>();

        for (var entry : entityMap.entrySet()) {

            if (entry.getValue().getClass() == Grass.class) {
                grassList.add((Grass) entry.getValue());
            }

        }
        return grassList;
    }

    private ArrayList<Herbivore> getHerbivoreArray() {

        ArrayList<Herbivore> herbivoreList = new ArrayList<>();

        for (var entry : entityMap.entrySet()) {

            if (entry.getValue().getClass() == Herbivore.class) {
                herbivoreList.add((Herbivore) entry.getValue());
            }

        }
        return herbivoreList;
    }

    private ArrayList<Predator> getPredatorArray() {

        ArrayList<Predator> predatorList = new ArrayList<>();

        for (var entry : entityMap.entrySet()) {

            if (entry.getValue().getClass() == Predator.class) {
                predatorList.add((Predator) entry.getValue());
            }

        }
        return predatorList;
    }

    private Coordinate getNearestTargetCoordinate(Entity myEntity) {

        int distance = 1000;
        Entity entity = null;

        //неправильный мосив
        if (myEntity.getClass() == Predator.class) {
            for (Herbivore herbivore : getHerbivoreArray()) {

                if (distance > myEntity.getCoordinate().subtractionCoordinate(herbivore.getCoordinate())) {
                    distance = myEntity.getCoordinate().subtractionCoordinate(herbivore.getCoordinate());
                    entity = herbivore;
                }

            }
        } else if (myEntity.getClass() == Herbivore.class) {
            for (Grass grass : getGrassArray()) {
                if (distance > myEntity.getCoordinate().subtractionCoordinate(grass.getCoordinate())) {
                    distance = myEntity.getCoordinate().subtractionCoordinate(grass.getCoordinate());
                    entity = grass;
                }

            }
        }


        if (entity == null) {
            return null;
        }

        return entity.getCoordinate();

    }

    private HashMap<Coordinate, Creature> getCreatureHashMap() {

        HashMap<Coordinate, Creature> temp = new HashMap<>();

        for (var entry : entityMap.entrySet()) {
            if (entry.getValue().getClass() == Herbivore.class || entry.getValue().getClass() == Predator.class) {
                temp.put(entry.getKey(), (Creature) entry.getValue());
            }
        }

        return temp;
    }

    private int[][] getBlocksArray(Creature entity) {

        int[][] arr = null;

        if (entity.getClass() == Predator.class) {
            arr = new int[entityMap.size() - 1 - getHerbivoreArray().size()][2];
        } else if (entity.getClass() == Herbivore.class) {
            arr = new int[entityMap.size() - 1 - getGrassArray().size()][2];
        }


        int i = 0;
        for (var entry : entityMap.entrySet()) {

            if (entry.getValue() == entity) {
                continue;
            }
            if (entity.getClass() == Predator.class && entry.getValue().getClass() != Herbivore.class) {
                arr[i][0] = entry.getValue().getCoordinate().getCoordinateY();
                arr[i][1] = entry.getValue().getCoordinate().getCoordinateX();

                i++;
            }

            if (entity.getClass() == Herbivore.class && entry.getValue().getClass() != Grass.class) {
                arr[i][0] = entry.getValue().getCoordinate().getCoordinateY();
                arr[i][1] = entry.getValue().getCoordinate().getCoordinateX();

                i++;
            }


        }
        return arr;


    }

    private boolean checkExistenceEntity(Coordinate entity) {

        boolean flag = false;

        for (var entry : entityMap.entrySet()) {
            if (Objects.equals(entry.getKey(), entity)) flag = true;
        }

        return flag;
    }

    private void updateSimulationOver(){

        if(getGrassArray().size() == 0 && getPredatorArray().size() == 0  ) {
            isSimulationOver = true;
        }

        if(getHerbivoreArray().size() ==0 && getPredatorArray().size() >0 ){
            isSimulationOver = true;
        }

    }

    public boolean getIsSimulationOver() {
        return isSimulationOver;
    }

    public void setIsSimulationOver(boolean isSimulationOver) {
        this.isSimulationOver = isSimulationOver;
    }


}
