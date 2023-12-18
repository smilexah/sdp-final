package com.sdu.edu.kz.factory;

public class StandardRoomFactory implements RoomFactory {
    public Room createRoom() {
        return new StandardRoom();
    }
}
