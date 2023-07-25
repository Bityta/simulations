package Models;

public abstract class Creature extends Entity{

    private int speed = 2;
    private int HP = 5;

    @Override
    public boolean isCreature() {
        return true;
    }




    protected abstract void makeMove();

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getHP() {
        return HP;
    }

    public void setHP(int HP) {
        this.HP = HP;
    }
}
