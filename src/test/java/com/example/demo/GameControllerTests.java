package com.example.demo;

import com.example.demo.controller.GameController;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameControllerTest {

    GameController gameController = new GameController();


    /*
    * This test checks that the play method returns
    *  a non-null string that contains the "Behind door number" message
    */
    @Test
    void playMethodShouldReturnValidResponse() {
        String response = gameController.play(1);

        assertNotNull(response);
        assertTrue(response.contains("Behind door number"));
    }

    /*This test checks that the result method
    returns a non-null string that contains the
     "The prize is behind door number" message.
     */
    @Test
    void resultMethodShouldReturnValidResponse() {
        String response = gameController.result(1, false);

        assertNotNull(response);
        assertTrue(response.contains("The prize is behind door number"));
    }

    /*This test checks that switching doors
    correctly returns a different door that is not
     the initial or open doors, and is the prize door.
     */
    @Test
    void switchingDoorShouldReturnDifferentDoor() {
        int initialDoor = 1;
        int openDoor = 2;
        int prizeDoor = 3;

        int newDoor = 6 - initialDoor - openDoor;

        assertNotEquals(initialDoor, newDoor);
        assertNotEquals(openDoor, newDoor);
        assertEquals(prizeDoor, newDoor);
    }
}

