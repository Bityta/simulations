package Logic;

public class Simulation {

    private int numberOfSteps = 1;
    public Actions actions;

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

        System.out.println("=".repeat(50));
        actions.turnActions();
        System.out.println("\n\u001B[32m" + " Количество шагов: " + numberOfSteps + "\u001B[0m\n");
        numberOfSteps++;
        renderMap();
    }
    public void startSimulation() throws InterruptedException {


        while (true){
            System.out.println("Количество шагов: " + numberOfSteps);
            numberOfSteps++;
            actions.turnActions();
            renderMap();
            Thread.sleep(1800);
            System.out.println("=".repeat(15));
        }



    }
    public void pauseSimulation(){

    }



}
