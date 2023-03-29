package com.example.demo.controller;

import java.util.Random;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class GameController {

    private static Random generator = new Random();
    private int prizeDoor;
    private int openDoor;

    @GetMapping("/play")
    public String play(@RequestParam(name = "door") int userDoor) {
        prizeDoor = generator.nextInt(3) + 1;
        openDoor = generator.nextInt(3) + 1;

        // Make sure that the open door is different from the user's choice and the prize door
        while (openDoor == prizeDoor || openDoor == userDoor) {
            openDoor = generator.nextInt(3) + 1;
        }

        StringBuilder response = new StringBuilder("\nIn a moment, I'll reveal the location of the prize,");
        response.append("\nbut before that, I'll show you what's behind one of the other doors.");
        response.append("\nBehind door number ").append(openDoor).append(" there is goat!");
        response.append("\nYou have selected door number ").append(userDoor).append(".");
        response.append("\nIf you wish to change your choice, you can select the Switch Door option and click the Get Result button. Otherwise, simply click the Get Result button to proceed. :)");

        return response.toString();
    }


    @GetMapping("/result")
    public String result(@RequestParam(name = "door") int userDoor, @RequestParam(name = "switch") boolean switchDoor) {
        StringBuilder response = new StringBuilder("The prize is behind door number: ").append(prizeDoor).append("\n");

        if (switchDoor) {
            userDoor = 6 - userDoor - openDoor;
            response.append("and you switched your door to ").append(userDoor).append("\n");
        }

        //Check to see if user won or lost
        if (userDoor == prizeDoor) {
            response.append("-> Congratulations! You won! :)");
        } else {
            response.append("-> Sorry, You lost! :(");
        }

        return response.toString();
    }
}