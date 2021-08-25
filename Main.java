package battleship;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Field field1 = new Field();
        field1.fillField();
        Field field2 = new Field();
        field2.fillField();
        System.out.println("Player 1, place your ships on the game field");
        field1.getCoordinatesOfShips();
        System.out.println("Press Enter and pass the move to another player");
        scanner.nextLine();
        System.out.println("Player 2, place your ships on the game field");
        field2.getCoordinatesOfShips();
        System.out.println("Press Enter and pass the move to another player");
        scanner.nextLine();

        while (true) {
            field2.printField(true);
            System.out.println("---------------------");
            field1.printField(false);
            System.out.println("Player 1, it's your turn:");
            if (field2.takeShot()) {
                System.out.println("Player 1 won. Congratulations!");
                break;
            }
            System.out.println("Press Enter and pass the move to another player");
            scanner.nextLine();
            field1.printField(true);
            System.out.println("---------------------");
            field2.printField(false);
            System.out.println("Player 2, it's your turn:");
            if (field1.takeShot()) {
                System.out.println("Player 2 won. Congratulations!");
                break;
            }
            System.out.println("Press Enter and pass the move to another player");
            scanner.nextLine();
        }
    }
}
