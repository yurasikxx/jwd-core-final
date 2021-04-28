package com.epam.jwd.core_final.context;

import com.epam.jwd.core_final.domain.FlightMission;

import java.io.IOException;

// todo replace Object with your own types
public interface ApplicationMenu {

    default String printAvailableOptions() {
        return "Print available options";
    }

    default void handleUserInput() {
        System.out.println("Handle user input");
    }

    void crewMemberOperations(byte operation);

    void spaceshipOperations(byte operation);

    void planetOperations(byte operation);

    void flightMissionOperations(byte operation);

    void writeJSONFile(FlightMission flightMission) throws IOException;

    void readJSONFile() throws IOException;

}
