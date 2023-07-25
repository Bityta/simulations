package Logic;

import java.util.Scanner;

public class Simulation {

    private int moveCounter = 0;
    private Actions actions;

    public Simulation() {
        actions = new Actions();
        actions.initActions();
    }

    public void renderMap(){

        for (int i = 0; i < Map.getWidthMap(); i++) {
            for (int j = 0; j < Map.getHeightMap(); j++) {
                if(actions.map.getMap()[j][i] != null){
                    System.out.print(actions.map.getMap()[j][i].getEmoji() + " ");
                }
                else{
                    System.out.print(" __ " + " ");
                }
            }
            System.out.println("\n");

        }
    }



    //еще не сделано
    public void nextTurn(){
        actions.turnActions();
        renderMap();
    }
    public void startSimulation() throws InterruptedException {


        while (true){
            actions.turnActions();
            renderMap();
            Thread.sleep(1800);
            System.out.println("=".repeat(15));
        }



    }
    public void pauseSimulation(){

    }

}
