package Models;

public class Predator extends Creature {

    public int attackPower = 3;


    @Override
    public boolean isPredator() {
        return true;
    }
    @Override
    public void nextStep(){
//        getCoordinate().getCoordinateX() и getCoordinate().getCoordinateY()
        // a* for (entry.getValue() , getMap());



    }
    @Override
    protected void makeMove() {

    }

    protected void makeAttack() {
    }

    public int getAttackPower() {
        return attackPower;
    }

    public void setAttackPower(int attackPower) {
        this.attackPower = attackPower;
    }

    @Override
    public String getEmoji() {
        return " 🐺 ";
    }

}
