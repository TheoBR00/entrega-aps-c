package br.pro.hashi.ensino.desagil.aps.model;

public class Or3Gate extends Gate {
    private final NandGate nand1, nand2, nand3, nand4, nand5, nand6;

    public Or3Gate() {
        super("OR3", 3);

        nand1 = new NandGate();
        nand2 = new NandGate();
        nand3 = new NandGate();
        nand4 = new NandGate();
        nand5 = new NandGate();
        nand6 = new NandGate();

    }

    @Override
    public boolean read() {
        return nand6.read();
    }

    @Override
    public void connect(int inputIndex, Emitter emitter) {
        if (inputIndex < 0 || inputIndex > 2) {
            throw new IndexOutOfBoundsException(inputIndex);
        }
        if (inputIndex == 0) {
            nand1.connect(0, emitter);
            nand1.connect(1, emitter);
        } else {
            if (inputIndex == 1) {
                nand2.connect(0, emitter);
                nand2.connect(1, emitter);
            } else {
                nand3.connect(0, emitter);
                nand3.connect(1, emitter);
            }
        }
        nand4.connect(0, nand1);
        nand4.connect(1, nand2);
        nand5.connect(0, nand4);
        nand5.connect(1, nand4);
        nand6.connect(0, nand5);
        nand6.connect(1, nand3);
    }
}

