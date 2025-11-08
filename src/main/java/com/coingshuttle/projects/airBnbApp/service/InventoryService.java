package com.coingshuttle.projects.airBnbApp.service;

import com.coingshuttle.projects.airBnbApp.entity.Room;

public interface InventoryService {
    public void InitializeRoomForAYear(Room room);
    public void deleteFutureInventories(Room room);
}
