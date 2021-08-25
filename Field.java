package battleship;

import java.util.ArrayList;
import java.util.Scanner;

public class Field {
    Scanner scanner = new Scanner(System.in);
    int rows = 10;
    int columns = 10;
    char[][] field = new char[rows][columns];
    int startFirst, endFirst, startSecond, endSecond;
    int r1, c1, r2, c2;
    int shipSankCount = 0;
    Ship ship = new Ship();

    boolean takeShot() {
        String input = scanner.nextLine();
        if (!input.matches("[A-J]([0-9]|10)")) {
            System.out.println("Error! You entered the wrong coordinates!");
            return false;
        }
        int row = getIndex(input.charAt(0));
        input = input.substring(1);
        int col = Integer.parseInt(input) - 1;
        if (field[row][col] == 'O') {
            field[row][col] = 'X';
            shipSankCount++;
            boolean temp = didAShipSink();
            if (shipSankCount == 17) {
                System.out.println("You sank the last ship!");
                return true;
            } else if (temp) {
                System.out.println("You sank a ship!");
                return false;
            } else {
                System.out.println("You hit a ship!");
                return false;
            }
        } else if (field[row][col] == '~') {
            field[row][col] = 'M';
            System.out.println("You missed! Try again:");
            return false;
        } else {
            System.out.println("You missed! Try again:");
            return false;
        }
    }

    boolean didAShipSink() {
        for (int i = 0; i < 5; i++) {
            if (!ship.isSankArray[i]) {
                ArrayList<int[]> temp = ship.coordinatesArray[i];
                boolean isDead = true;
                for (int[] coordinates : temp) {
                    if (field[coordinates[0]][coordinates[1]] == 'O') {
                        isDead = false;
                        break;
                    }
                }
                if (isDead) {
                    ship.isSankArray[i] = true;
                    return true;
                }
            }
        }
        return false;
    }

    void getCoordinatesOfShips() {
        printField(false);
        for (int i = 0; i < 5; i++) {
            System.out.printf("Enter the coordinates of the %s (%d cells):", ship.shipNameArray[i], ship.shipSizeArray[i]);
            getCoordinates(ship.shipNameArray[i], ship.shipSizeArray[i]);
            ship.coordinatesArray[i] = placeShip();
            printField(false);
        }
    }

    ArrayList<int[]> placeShip() {
        ArrayList<int[]> temp = new ArrayList<>();
        for (int i = r1; i <= r2; i++) {
            for (int j = c1; j <= c2; j++) {
                field[i][j] = 'O';
                temp.add(new int[] {i, j});
            }
        }
        return temp;
    }

    void getCoordinates(String name, int cells) {
        String input;
        String[] inputArray;
        boolean correctCoordinates = false;
        while (!correctCoordinates) {
            input = scanner.nextLine();
            if (!input.matches("[A-J]([0-9]|10)\\s[A-J]([0-9]|10)")) {
                System.out.println("Error! Coordinates are in incorrect format! Try again:");
                continue;
            }
            inputArray = input.split(" ");

            startFirst = getIndex(inputArray[0].charAt(0));
            inputArray[0] = inputArray[0].substring(1);
            endFirst = Integer.parseInt(inputArray[0]) - 1;

            startSecond = getIndex(inputArray[1].charAt(0));
            inputArray[1] = inputArray[1].substring(1);
            endSecond = Integer.parseInt(inputArray[1]) - 1;

            if (startFirst == startSecond && endFirst == endSecond) {
                System.out.println("Error! row and column both match! Try again:");
                continue;
            }
            if (startFirst == startSecond) {
                if (Math.abs(endFirst - endSecond) + 1 != cells) {
                    System.out.printf("Error! Wrong length of the %s! Try again:", name);
                    continue;
                }
                correctCoordinates = checkCoordinatesOfShip();
            } else if (endFirst == endSecond) {
                if (Math.abs(startFirst - startSecond) + 1 != cells) {
                    System.out.printf("Error! Wrong length of the %s! Try again:", name);
                    continue;
                }
                correctCoordinates = checkCoordinatesOfShip();
            } else {
                System.out.println("Error! Wrong ship location! Try again:");
            }
        }
    }

    boolean checkCoordinatesOfShip() {
        if (startFirst == startSecond) {
            r1 = startFirst;
            r2 = startSecond;
            c1 = Math.min(endFirst, endSecond);
            c2 = Math.max(endFirst, endSecond);
        } else {
            c1 = endFirst;
            c2 = endSecond;
            r1 = Math.min(startFirst, startSecond);
            r2 = Math.max(startFirst, startSecond);
        }
        for (int i = r1 - 1; i <= r2 + 1; i++) {
            for (int j = c1 - 1; j <= c2 + 1; j++) {
                if (i >= 0 && i < rows && j >= 0 && j < columns) {
                    if (field[i][j] == 'O') {
                        System.out.println("Error! You placed it too close to another one. Try again:");
                        return false;
                    }
                }
            }
        }
        return true;
    }

    void fillField() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                field[i][j] = '~';
            }
        }
    }

    void printField(boolean isFog) {
        char c = 'A';
        System.out.println("  1 2 3 4 5 6 7 8 9 10");
        for (int i = 0; i < rows; i++) {
            System.out.print(c++ + " ");
            for (int j = 0; j < columns; j++) {
                if (isFog && field[i][j] == 'O') {
                    System.out.print("~ ");
                } else {
                    System.out.print(field[i][j] + " ");
                }
            }
            System.out.println();
        }
    }

    static int getIndex(char ch) {
        String possibleStr = "ABCDEFGHIJ";
        return possibleStr.indexOf(ch);
    }
}
