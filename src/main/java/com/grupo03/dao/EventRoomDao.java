package com.grupo03.dao;

import com.grupo03.model.EventRoom;

import java.util.List;
import java.util.Optional;

public class EventRoomDao implements DaoPattern<EventRoom> {
    @Override
    public Optional<EventRoom> getById(int id) {
        return Optional.empty();
    }

    @Override
    public List<EventRoom> getAll() {
        return null;
    }

    @Override
    public void save(EventRoom eventRoom) {

    }

    @Override
    public void update(EventRoom eventRoom) {

    }

    @Override
    public void delete(EventRoom eventRoom) {

    }
}
