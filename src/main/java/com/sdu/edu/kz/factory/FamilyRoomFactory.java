package com.sdu.edu.kz.factory;

public class FamilyRoomFactory implements RoomFactory {
    public Room createRoom() {
        return new FamilyRoom();
    }
}
