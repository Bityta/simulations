import Logic.Simulation;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws InterruptedException {

        System.out.println("");
        System.out.println("Привет, это 2д симульция мира.");
        System.out.println("Все довольно просто, для вас генерируется уникальная карта, на которой случайным образом расположены юниты за которымы Вы можете наблюдать.");
        System.out.println("Существует 2 типы объектов:");
        System.out.println("  - Животные (травоядные и хищники)");
        System.out.println("  - Предметы (деревья, трава и горы) ");


        System.out.println("");
        System.out.println("=".repeat(50));


        while (true) {
            Simulation game = new Simulation();
            Scanner scanner = new Scanner(System.in);
            System.out.print("\nДальнейшие дейтсвия:\n" +
                    "1 - начать новую симуляцию\n" +
                    "2 - установить настройки симуляции\n" +
                    "0 - завершить програму\n" +
                    "Ввод: ");


            String ans = scanner.next();

            if (ans.equals("0")) break;
            if (!ans.equals("1") && !ans.equals("2")){
                System.out.println("\nError: Попробуйте снова!");
                continue;
            }

            //настройки игры
            if(ans.equals("2")){

            }


            System.out.println("\n" + "=".repeat(50) + "\n");

            System.out.println("\u001B[36m" + " Ваша первоначальная карта: " + "\u001B[0m\n");
            game.renderMap();

            while (!game.actions.map.getIsSimulationOver()) {



                System.out.println("Дальнейшие дейтсвия:\n" +
                        "1 - просимулировать и отрендерить один ход\n" +
                        "2 - запустить бесконечный цикл симуляции и рендеринга\n" +
                        "3 - перегенерировать текущую карту\n" +
                        "0 - закончить симульяцию");


                scanner = new Scanner(System.in);

                System.out.print("Ввод: ");
                switch (scanner.next()) {

                    case "1" -> {
                        game.nextTurn();
                    }

                    case "2" -> game.startSimulation();

                    case "3" -> {
                        System.out.println("=".repeat(50) + "\n");
                        game = new Simulation();
                        System.out.println("\u001B[36m" + " Ваша новая первоначальная карта: " + "\u001B[0m\n");
                        game.renderMap();
                    }
                    case "0" -> game.actions.map.setIsSimulationOver(true);

                    default -> System.out.println("\nError: Попробуйте снова!\n");
                }
            }

        }


    }
}