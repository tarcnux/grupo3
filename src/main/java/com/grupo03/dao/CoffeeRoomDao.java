package com.grupo03.dao;

import com.grupo03.model.CoffeeRoom;

import java.util.List;
import java.util.Optional;

public class CoffeeRoomDao implements DaoPattern<CoffeeRoom> {
    @Override
    public Optional<CoffeeRoom> getById(int id) {
        return Optional.empty();
    }

    @Override
    public List<CoffeeRoom> getAll() {
        return null;
    }

    @Override
    public void save(CoffeeRoom coffeeRoom) {

    }
}
