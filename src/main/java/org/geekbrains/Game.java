package org.geekbrains;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.Set;

public class Game {
    private static int NUM_DOORS = 3;
    private static int CAR_DOOR = 1;

    private Random random = new Random();


    public int chooseDoorByPlayer(){
        return random.nextInt(NUM_DOORS) + 1;
    }

    public int openDoorByMaster(int chosenDoor){
        int openedDoor;
        for (int i = 0; i < NUM_DOORS; i++) {
            openedDoor = i + 1;
            if (openedDoor != chosenDoor && openedDoor != CAR_DOOR) {
                return openedDoor;
            }
        }
        return 0;
    }

    public int switchDoorByPlayer(int chosenDoor, int openedDoor){
        int newDoor;
        for (int i = 0; i < NUM_DOORS; i++) {
            newDoor = i + 1;
            if (newDoor != chosenDoor && newDoor != openedDoor) {
                return newDoor;
            }
        }
        return 0;
    }

    public void playGame(int numIterations, boolean changeChoice){
        int switchWins = 0;
        int stayWins = 0;

        HashMap<Integer, Double> results = HashMap.newHashMap(numIterations);

        for (int i = 0; i < numIterations; i++) {
            int chosenDoor = chooseDoorByPlayer();
            int openedDoor = openDoorByMaster(chosenDoor);
            int switchedDoor = switchDoorByPlayer(chosenDoor, openedDoor);
            double stayWinPercentage = 0;
            double switchWinPercentage = 0;

            if (chosenDoor == CAR_DOOR){
                stayWins++;
            } else if (switchedDoor == CAR_DOOR){
                switchWins++;
            }
            if (changeChoice) {
                switchWinPercentage = (double) switchWins / (i + 1);
                results.put(i, switchWinPercentage);
            } else {
                stayWinPercentage = (double) stayWins / (i + 1);
                results.put(i, stayWinPercentage);
            }

        }
        getResults(results);
    }

    public void getResults(HashMap<Integer, Double> results){
        for (int i = 0; i < results.size(); i++) {
            System.out.println("Iteration: " + i + ", win percentage: " + results.get(i));
        }
    }

}
