package Logic;

import Models.*;
import Utils.ASrar.AStar;
import Utils.ASrar.Node;
import Utils.Coordinate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class Map {

    private Entity[][] map;
    private HashMap<Coordinate, Entity> entityMap;

    public Map() {
        map = new Entity[widthMap][heightMap];
        entityMap = new HashMap<>();
    }

    private static final int widthMap = 10;
    private static final int heightMap = 10;

    public Entity[][] getMap() {
        return map;
    }

    public HashMap<Coordinate, Entity> getEntityMap() {
        return entityMap;
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

    //изменить название метода
    public void addEntity(Entity entity) {


        if (!isOccupiedPosition(entity.getCoordinate())) {
            entityMap.put(entity.getCoordinate(), entity);

        } else {
            entity.setRandomCoordinate();
            addEntity(entity);
        }


    }

    public void deleteEntity(Coordinate coordinate) {


        entityMap.remove(coordinate);
        getMap()[coordinate.getCoordinateX()][coordinate.getCoordinateY()] = null;

    }

    private boolean isOccupiedPosition(Coordinate coordinate) {

        for (var entry : entityMap.entrySet()) {

            if (entry.getKey().getCoordinateX() == coordinate.getCoordinateX() && (entry.getKey().getCoordinateY() == coordinate.getCoordinateY())) {
                return true;
            }
        }

        return false;

    }


    //что то сделать
    public void step() {


        for (var entry : copyCreature(entityMap).entrySet()) {

            //скорость реализовать через цикл


            Node initialNode = new Node(entry.getValue().getCoordinate().getCoordinateY(), entry.getValue().getCoordinate().getCoordinateX());

            int rows = getWidthMap();
            int cols = getHeightMap();

            Grass test = minDistanceGrass(entry.getValue());
            Node finalNode;
            if (test != null) {
                finalNode = new Node(test.getCoordinate().getCoordinateY(), test.getCoordinate().getCoordinateX());
            } else {
                finalNode = new Node(entry.getValue().getCoordinate().getCoordinateY(), entry.getValue().getCoordinate().getCoordinateX());
            }


            AStar aStar = new AStar(rows, cols, initialNode, finalNode);


            int[][] blocksArray = arrBlock(entry.getValue());


            aStar.setBlocks(blocksArray);


            List<Node> path = aStar.findPath();


            if (path.size() > 2) {

                deleteEntity(entry.getKey());

                //изменение координат
                entry.getValue().setCoordinate(new Coordinate(path.get(1).getCol(), path.get(1).getRow()));

                addEntity(entry.getValue());

            } else if (path.size() == 2) {
                //съесть ударить и тд
                //для коровы пока что

                deleteEntity(test.getCoordinate());


                deleteEntity(entry.getKey());

                //изменение координат
                entry.getValue().setCoordinate(new Coordinate(path.get(1).getCol(), path.get(1).getRow()));

                addEntity(entry.getValue());

            }


        }


    }

    //изменить название
    public int[][] newGetCell(Coordinate coordinate) {

        int[][] arr = new int[entityMap.size() - 1][2];

        int i = 0;
        for (var entry : entityMap.entrySet()) {
            if (coordinate != entry.getValue().getCoordinate()) {
                arr[i][0] = entry.getValue().getCoordinate().getCoordinateX();
                arr[i][1] = entry.getValue().getCoordinate().getCoordinateY();
                i++;
            }


        }
        return arr;
    }

    //изменить название
    private ArrayList<Grass> setGrassArray() {

        ArrayList<Grass> grassList = new ArrayList<>();

        for (var entry : entityMap.entrySet()) {

            if (entry.getValue().getClass() == Grass.class) {
                grassList.add((Grass) entry.getValue());
            }

        }
        return grassList;
    }

    //изменить название
    private ArrayList<Herbivore> setHerbivoreArray() {

        ArrayList<Herbivore> herbivoreList = new ArrayList<>();

        for (var entry : entityMap.entrySet()) {

            if (entry.getValue().getClass() == Herbivore.class) {
                herbivoreList.add((Herbivore) entry.getValue());
            }

        }
        return herbivoreList;
    }

    //ошибки + название + что то еще

    private Herbivore minDistanceHerbivore(Entity entity) {

        int min = 1000;
        Herbivore herbivored = null;

        for (Herbivore herbivore : setHerbivoreArray()) {

            if (min > entity.getCoordinate().subtractionCoordinate(herbivore.getCoordinate())) {
                min = entity.getCoordinate().subtractionCoordinate(herbivore.getCoordinate());
                herbivored = herbivore;
            }

        }

        return herbivored;

    }

    //ошибки + название + что то еще
    private Grass minDistanceGrass(Entity entity) {

        int min = 1000;

        Grass grassed = null;

        for (Grass grass : setGrassArray()) {
            if (min > entity.getCoordinate().subtractionCoordinate(grass.getCoordinate())) {
                min = entity.getCoordinate().subtractionCoordinate(grass.getCoordinate());
                grassed = grass;
            }

        }

        return grassed;


    }

    private int intMinDistanceHerbivore(Entity entity) {

        int min = 1000;
        Herbivore herbivored = null;

        for (Herbivore herbivore : setHerbivoreArray()) {

            if (min > entity.getCoordinate().subtractionCoordinate(herbivore.getCoordinate())) {
                min = entity.getCoordinate().subtractionCoordinate(herbivore.getCoordinate());
                herbivored = herbivore;
            }

        }

        return min;

    }

    //ошибки + название + что то еще
    private int intMinDistanceGrass(Entity entity) {

        int min = 1000;

        Grass grassed = null;

        for (Grass grass : setGrassArray()) {
            if (min > entity.getCoordinate().subtractionCoordinate(grass.getCoordinate())) {
                min = entity.getCoordinate().subtractionCoordinate(grass.getCoordinate());
                grassed = grass;
            }

        }

        return min;


    }

    private HashMap<Coordinate, Creature> copyCreature(HashMap<Coordinate, Entity> entityMap) {

        HashMap<Coordinate, Creature> temp = new HashMap<>();

        for (var entry : entityMap.entrySet()) {
            if (entry.getValue().getClass() == Herbivore.class || entry.getValue().getClass() == Predator.class) {
                temp.put(entry.getKey(), (Creature) entry.getValue());
            }
        }

        return temp;
    }

    //еще что то в блок добавить
    private int[][] arrBlock(Creature entity) {

        int[][] arr = new int[entityMap.size() - 1][2];

        int i = 0;
        for (var entry : entityMap.entrySet()) {
            if (entry.getValue() != entity && entry.getValue().getClass() != Grass.class) {
                arr[i][0] = entry.getValue().getCoordinate().getCoordinateY();
                arr[i][1] = entry.getValue().getCoordinate().getCoordinateX();
                i++;
            }


        }
        return arr;


    }

}
