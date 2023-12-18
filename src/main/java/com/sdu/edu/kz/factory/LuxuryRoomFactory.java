package com.sdu.edu.kz.factory;

public class LuxuryRoomFactory implements RoomFactory {
    public Room createRoom() {
        return new LuxuryRoom();
    }
}

