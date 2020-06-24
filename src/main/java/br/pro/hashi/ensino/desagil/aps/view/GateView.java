package br.pro.hashi.ensino.desagil.aps.view;

import br.pro.hashi.ensino.desagil.aps.model.Gate;
import br.pro.hashi.ensino.desagil.aps.model.Light;
import br.pro.hashi.ensino.desagil.aps.model.Switch;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.URL;

class GateView extends FixedPanel implements ActionListener, MouseListener {
    private final Gate gate;

    private final JCheckBox input1;
    private final JCheckBox input2;
    private final JCheckBox input3;
    private final Image image;
    private final Switch switch1;
    private final Switch switch2;
    private final Switch switch3;
    private final Light output;
    // Novos atributos necessários para esta versão da interface.
    private Color color;

    public GateView(Gate gate) {

        // Como subclasse de FixedPanel, esta classe agora
        // exige que uma largura e uma altura sejam fixadas.
        super(245, 110);

        this.gate = gate;

        input1 = new JCheckBox();
        input2 = new JCheckBox();
        input3 = new JCheckBox();
        switch1 = new Switch();
        switch2 = new Switch();
        switch3 = new Switch();
        output = new Light(255, 0, 0);
        JLabel img_credit = new JLabel("Images by Wikipedia");


        // Não há mais a chamada de setLayout, pois ela agora
        // acontece no construtor da superclasse FixedPanel.

        // Como subclasse de FixedPanel, agora podemos definir a
        // posição e o tamanho de cada componente ao adicioná-la.
        if (this.gate.getInputSize() == 3) {
            add(input1, 200, 7, 25, 25);
            add(input2, 200, 28, 25, 25);
            add(input3, 200, 50, 25, 25);

        }
        if (this.gate.getInputSize() == 2) {
            add(input1, 200, 18, 25, 25);
            add(input2, 200, 38, 25, 25);
        }
        if (this.gate.getInputSize() == 1) {
            add(input1, 200, 28, 25, 25);
        }
        add(img_credit, 30, 85, 170, 15);

        // Inicializamos o atributo de cor simplesmente como preto.
        color = Color.BLACK;

        // Usamos esse carregamento nos Desafios, vocês lembram?
        String name = gate.toString() + ".png";
        URL url = getClass().getClassLoader().getResource(name);
        image = getToolkit().getImage(url);

        input1.addActionListener(this);
        input2.addActionListener(this);
        input3.addActionListener(this);
        // Toda componente Swing tem uma lista de observadores
        // que reagem quando algum evento de mouse acontece.
        // Usamos o método addMouseListener para adicionar a
        // própria componente, ou seja "this", nessa lista.
        // Só que addMouseListener espera receber um objeto
        // do tipo MouseListener como parâmetro. É por isso que
        // adicionamos o "implements MouseListener" lá em cima.
        addMouseListener(this);

        update();
    }

    private void update() {
        output.connect(0, this.gate);
        if (this.gate.getInputSize() == 3) {
            if (input1.isSelected()) {
                switch1.turnOn();
            } else {
                switch1.turnOff();
            }
            if (input2.isSelected()) {
                switch2.turnOn();
            } else {
                switch2.turnOff();
            }
            if (input3.isSelected()) {
                switch3.turnOn();
            } else {
                switch3.turnOff();
            }
            this.gate.connect(0, switch1);
            this.gate.connect(1, switch2);
            this.gate.connect(2, switch3);
        }
        if (this.gate.getInputSize() == 2) {
            if (input1.isSelected()) {
                switch1.turnOn();
            } else {
                switch1.turnOff();
            }
            if (input2.isSelected()) {
                switch2.turnOn();
            } else {
                switch2.turnOff();
            }
            this.gate.connect(0, switch1);
            this.gate.connect(1, switch2);

        } else {
            if (input1.isSelected()) {
                switch1.turnOn();
            } else {
                switch1.turnOff();
            }
            this.gate.connect(0, switch1);
        }
        repaint();
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        update();
    }

    @Override
    public void mouseClicked(MouseEvent event) {

        // Descobre em qual posição o clique ocorreu.
        int x = event.getX();
        int y = event.getY();
        System.out.println(x + " " + y);

        int x0 = 15;
        int y0 = 39;
        int r = 13;

        // Se o clique foi dentro do quadrado colorido...
        if (Math.sqrt((x - x0) * (x - x0) + (y - y0) * (y - y0)) < r) {

            // ...então abrimos a janela seletora de cor...
            color = JColorChooser.showDialog(this, null, color);
            output.setColor(color);
            // ...e chamamos repaint para atualizar a tela.
            repaint();
        }
    }

    @Override
    public void mousePressed(MouseEvent event) {
        // Não precisamos de uma reação específica à ação de pressionar
        // um botão do mouse, mas o contrato com MouseListener obriga
        // esse método a existir, então simplesmente deixamos vazio.
    }

    @Override
    public void mouseReleased(MouseEvent event) {
        // Não precisamos de uma reação específica à ação de soltar
        // um botão do mouse, mas o contrato com MouseListener obriga
        // esse método a existir, então simplesmente deixamos vazio.
    }

    @Override
    public void mouseEntered(MouseEvent event) {
        // Não precisamos de uma reação específica à ação do mouse
        // entrar no painel, mas o contrato com MouseListener obriga
        // esse método a existir, então simplesmente deixamos vazio.
    }

    @Override
    public void mouseExited(MouseEvent event) {
        // Não precisamos de uma reação específica à ação do mouse
        // sair do painel, mas o contrato com MouseListener obriga
        // esse método a existir, então simplesmente deixamos vazio.
    }

    @Override
    public void paintComponent(Graphics g) {

        // Não podemos esquecer desta linha, pois não somos os
        // únicos responsáveis por desenhar o painel, como era
        // o caso nos Desafios. Agora é preciso desenhar também
        // componentes internas, e isso é feito pela superclasse.
        super.paintComponent(g);

        // Desenha a imagem, passando sua posição e seu tamanho.
        g.drawImage(image, 10, 0, 192, 80, this);

        // Desenha um quadrado cheio.
        g.setColor(output.getColor());
        g.fillOval(3, 27, 25, 25);
    }

}
