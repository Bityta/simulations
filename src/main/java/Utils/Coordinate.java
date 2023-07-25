package Utils;

import Logic.Map;

public class Coordinate {

    private int x;
    private int y;

    public Coordinate() {

    }

    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getCoordinateX() {
        return x;
    }

    public int getCoordinateY() {
        return y;
    }

    public void setCoordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void generateAndInstallRandomCoordinates() {

        final int x = (int) (Math.random() * Map.getWidthMap());
        final int y = (int) (Math.random() * Map.getHeightMap());

        setCoordinate(x, y);


    }


    //изменить название
    public int subtractionCoordinate(Coordinate coordinate) {

        int ans = 0;

        if (this.x > coordinate.getCoordinateX()) {
            ans += this.x - coordinate.getCoordinateX();
        } else {
            ans += coordinate.getCoordinateX() - this.x;

        }

        if (this.y > coordinate.getCoordinateY()) {
            ans += this.y - coordinate.getCoordinateY();
        } else {
            ans += coordinate.getCoordinateY() - this.y;

        }

        return ans;


    }

}
