package com.company;

public class Main {

    public static void main(String[] args) {
        GUI gui = new GUI();
        gui.makeGUI();
        //ramka.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //ramka.getContentPane().add(button);
        gui.setSize(700, 700);
        gui.setVisible(true);
    }
}
