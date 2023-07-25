package Models;



public class Herbivore extends Creature {

    @Override
    public boolean isPredator() {
        return false;
    }

    @Override
    protected void makeMove() {

    }
    @Override
    public void nextStep() {

    }

    @Override
    public String getEmoji() {
        return " 🐄 ";
    }




}
