package br.pro.hashi.ensino.desagil.aps.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class Or3GateTest {
    @Test
    public void whenReceivingFalseFalseFalseShouldReturnFalse() {
        Or3Gate gate = new Or3Gate();
        gate.connect(0, new EmitterFalse());
        gate.connect(1, new EmitterFalse());
        gate.connect(2, new EmitterFalse());
        Assertions.assertFalse(gate.read());
    }

    @Test
    public void whenReceivingFalseFalseTrueShouldReturnTrue() {
        Or3Gate gate = new Or3Gate();
        gate.connect(0, new EmitterFalse());
        gate.connect(1, new EmitterFalse());
        gate.connect(2, new EmitterTrue());
        Assertions.assertTrue(gate.read());
    }

    @Test
    public void whenReceivingFalseTrueFalseShouldReturnTrue() {
        Or3Gate gate = new Or3Gate();
        gate.connect(0, new EmitterFalse());
        gate.connect(1, new EmitterTrue());
        gate.connect(2, new EmitterFalse());
        Assertions.assertTrue(gate.read());
    }

    @Test
    public void whenReceivingFalseTrueTrueShouldReturnTrue() {
        Or3Gate gate = new Or3Gate();
        gate.connect(0, new EmitterFalse());
        gate.connect(1, new EmitterTrue());
        gate.connect(2, new EmitterTrue());
        Assertions.assertTrue(gate.read());
    }

    @Test
    public void whenReceivingTrueFalseFalseShouldReturnTrue() {
        Or3Gate gate = new Or3Gate();
        gate.connect(0, new EmitterTrue());
        gate.connect(1, new EmitterFalse());
        gate.connect(2, new EmitterFalse());
        Assertions.assertTrue(gate.read());
    }

    @Test
    public void whenReceivingTrueFalseTrueShouldReturnTrue() {
        Or3Gate gate = new Or3Gate();
        gate.connect(0, new EmitterTrue());
        gate.connect(1, new EmitterFalse());
        gate.connect(2, new EmitterTrue());
        Assertions.assertTrue(gate.read());
    }

    @Test
    public void whenReceivingTrueTrueFalseShouldReturnTrue() {
        Or3Gate gate = new Or3Gate();
        gate.connect(0, new EmitterTrue());
        gate.connect(1, new EmitterTrue());
        gate.connect(2, new EmitterFalse());
        Assertions.assertTrue(gate.read());
    }

    @Test
    public void whenReceivingTrueTrueTrueShouldReturnTrue() {
        Or3Gate gate = new Or3Gate();
        gate.connect(0, new EmitterTrue());
        gate.connect(1, new EmitterTrue());
        gate.connect(2, new EmitterTrue());
        Assertions.assertTrue(gate.read());
    }
}
