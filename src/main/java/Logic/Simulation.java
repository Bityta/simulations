package Logic;

import java.util.Scanner;

public class Simulation {

    private int numberOfSteps = 1;
    public Actions actions;

    public Simulation() {
        actions = new Actions();
        actions.initActions();
    }

    public void renderMap() {

        for (int i = 0; i < Map.getWidthMap(); i++) {
            for (int j = 0; j < Map.getHeightMap(); j++) {
                if (actions.map.getMap()[j][i] != null) {
                    System.out.print(actions.map.getMap()[j][i].getEmoji() + " ");
                } else {
                    System.out.print(" __ " + " ");
                }
            }
            System.out.println("\n");

        }
    }


    //еще не сделано
    public void nextTurn() {

        System.out.println("=".repeat(50));
        actions.turnActions();
        System.out.println("\n\u001B[32m" + " Количество шагов: " + numberOfSteps + "\u001B[0m\n");
        numberOfSteps++;
        renderMap();
    }

    public void startSimulation() throws InterruptedException {


        while (!actions.map.getIsSimulationOver()) {


            System.out.println("=".repeat(50));
            actions.turnActions();
            System.out.println("\n\u001B[32m" + " Количество шагов: " + numberOfSteps + "\u001B[0m\n");
            numberOfSteps++;
            renderMap();

            ///что то сделать

            if(!actions.map.getIsSimulationOver()){
                Scanner scanner = new Scanner(System.in);
                System.out.println("Дальнейшие дейтсвия:\n" +
                        "1 - поставить на паузу бесконечную симуляцию\n" +
                        "0 - закончить симульяцию");


                System.out.print("Ввод: ");
                System.out.println();
            }


//            switch (scanner.next()) {
//
//                case "1" -> pauseSimulation();
//
//                case "0" -> actions.map.setIsSimulationOver(true);
//
//                default -> System.out.println("Попробуйте снова");
//            }

            Thread.sleep(1800);
        }


    }


    public void pauseSimulation() {

    }


}
