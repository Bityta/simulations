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
        System.out.println("Ваша первоначальная карта:\n");

        Simulation game = new Simulation();
        game.renderMap();

        boolean isGameOver = false;

        while (!isGameOver) {


            System.out.println("Дальнейшие дейтсвия:\n" +
                    "1 - просимулировать и отрендерить один ход\n" +
                    "2 - запустить бесконечный цикл симуляции и рендеринга\n" +
                    "3 - перегенерировать текущую карту\n" +
                    "0 - закончить игру");


            Scanner scanner = new Scanner(System.in);

            System.out.print("Ввод: ");
            switch (scanner.next()) {

                case "1" -> game.nextTurn();
                case "2" -> game.startSimulation();
                case "3" -> {
                    game = new Simulation();
                    game.renderMap();
                }
                case "0" -> isGameOver = true;
                default -> System.out.println("Попробуйте снова");
            }
        }





    }
}