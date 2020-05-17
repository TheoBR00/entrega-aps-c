package br.pro.hashi.ensino.desagil.aps.model;

public class Switch implements Emitter {
    protected boolean state;

    public void turnOn() {
        state = true;
    }

    public void turnOff() {
        state = false;
    }

    @Override
    public boolean read() {
        return state;
    }
}
