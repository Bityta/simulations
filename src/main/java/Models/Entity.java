package Models;

import Utils.Coordinate;

public abstract class Entity {

    Coordinate coordinate;

    public abstract void nextStep();


    public void setCoordinate(Coordinate coordinate) {
        this.coordinate = coordinate;
    }

    public abstract String getEmoji();

    public abstract boolean isCreature();

    public Entity() {
        this.coordinate =  new Coordinate();
        coordinate.generateAndInstallRandomCoordinates();
    }

    public Entity(int x, int y) {
        this.coordinate = new Coordinate(x,y);
    }

    public Entity(Coordinate coordinate){
        this.coordinate = coordinate;
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }

    public void setRandomCoordinate() {
        this.coordinate = new Coordinate();
        coordinate.generateAndInstallRandomCoordinates();
    }

}
