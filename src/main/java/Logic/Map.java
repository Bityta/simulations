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

    private static final int widthMap = 6;
    private static final int heightMap = 6;

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

        HashMap<Coordinate, Entity> temp = new HashMap<>(entityMap);
        Node finalNode = null;


        for (var entry : temp.entrySet()) {


            if (entry.getValue().getClass().equals(Herbivore.class)) {

                Coordinate coordinate = null;

                if (minDistanceGrass(entry.getValue()).getCoordinate() == null) {
                    coordinate = entry.getValue().getCoordinate();
                } else {
                    coordinate = minDistanceGrass(entry.getValue()).getCoordinate();

                }


                finalNode = new Node(coordinate.getCoordinateY(), coordinate.getCoordinateX());

                //по горизонтале баг
                if (intMinDistanceGrass(entry.getValue()) <= 2) {
                    System.out.println("1 ход");
                    System.out.println(intMinDistanceGrass(entry.getValue()));
                    deleteEntity(coordinate);
                }

                System.out.println(coordinate.getCoordinateX() + " " + coordinate.getCoordinateY() + " финальные корды травоядного");

            } else if (entry.getValue().getClass().equals(Predator.class)) {

                Coordinate coordinate = minDistanceHerbivore(entry.getValue()).getCoordinate();
                finalNode = new Node(coordinate.getCoordinateY(), coordinate.getCoordinateX());

                if (intMinDistanceHerbivore(entry.getValue()) == 1) {
                    System.out.println("1 ход");
                }

                System.out.println(coordinate.getCoordinateX() + " " + coordinate.getCoordinateY() + " финальные корды хищника");


            } else {
                break;

            }


            Node initialNode = new Node(entry.getValue().getCoordinate().getCoordinateY(), entry.getValue().getCoordinate().getCoordinateX());

            int rows = getWidthMap() - 1;
            int cols = getHeightMap() - 1;


            AStar aStar = new AStar(rows, cols, initialNode, finalNode);

            int[][] blocksArray = new int[][]{{0, 0}};
            aStar.setBlocks(blocksArray);


            List<Node> path = aStar.findPath();

            //изменить
            int i = 0;

            for (Node node : path) {


                System.out.println(node);
                if (i == 1) {


                    deleteEntity(entry.getKey());


                    //изменение координат
                    entry.getValue().setCoordinate(new Coordinate(node.getCol(), node.getRow()));

                    System.out.println(entry.getValue().getCoordinate().getCoordinateX() + " " + entry.getValue().getCoordinate().getCoordinateY() + " новые корды");
                    addEntity(entry.getValue());

                    break;
                }
                i++;


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
        if (min == 1000) {
            System.out.println("Error!!!!!!!!!!!");
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
        if (min == 1000) {
            System.out.println("Error!!!!!!!!!!!");
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
        if (min == 1000) {
            System.out.println("Error!!!!!!!!!!!");
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
        if (min == 1000) {
            System.out.println("Error!!!!!!!!!!!");
        }

        return min;


    }

}
