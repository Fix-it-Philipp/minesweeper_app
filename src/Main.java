import javax.swing.*;
import java.awt.event.*;

public class Main {

    static Game game = new Game();

    public static void main(String[] args) {
        run();
    }

    private static void run() {
        // Initialisiere Fenster/Starte Programm
        // Erzeugung eines neuen Frames
        JFrame rootFrame = new JFrame();
        rootFrame.setTitle("Minesweeper");
        rootFrame.setSize(800,800);
        rootFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        rootFrame.setLocationRelativeTo(null);

        // Erstellen eines leeren Spielfeld Panels
        JPanel spielfeldPanel = new JPanel();
        spielfeldPanel.setSize(800,800);

        // Erstellen einer Menüleiste
        JMenuBar menuBar = new JMenuBar();

        // Erzeugung eines Objektes in der Menüleiste
        JMenu menu = new JMenu("Menu");

        // Menupunkt: Neues Spiel
        JMenuItem item = new JMenu("Neues Spiel");

        // Menupunkt: Neues Spiel -> Leicht
        JMenuItem startLeicht = new JMenuItem("Leicht");
        startLeicht.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // Leichtes Spiel wird initialisiert
                System.out.println("Starte Spiel: Leicht");
                game.GameEnde();
                game = new Game(10,10,15);
                rootFrame.setSize(300,300);
                rootFrame.setLocationRelativeTo(null);
                rootFrame.remove(spielfeldPanel);
                rootFrame.add(game.getSpielfeldPanel() ,0);
                rootFrame.setVisible(true);
            }
        });
        item.add(startLeicht);

        // Menupunkt: Neues Spiel -> Mittel
        JMenuItem startMittel = new JMenuItem("Mittel");
        startMittel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Starte Spiel: Mittel");
                game.GameEnde();
                game = new Game(15,15,40);
                rootFrame.setSize(400,420);
                rootFrame.setLocationRelativeTo(null);
                rootFrame.remove(spielfeldPanel);
                rootFrame.add(game.getSpielfeldPanel(),0);
                rootFrame.setVisible(true);
            }
        });
        item.add(startMittel);

        // Menupunkt: Neues Spiel -> Hart
        JMenuItem startHart = new JMenuItem("Hart");
        startHart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Starte Spiel: Hart");
                game.GameEnde();
                game = new Game(20,20,50);
                rootFrame.setSize(550,550);
                rootFrame.setLocationRelativeTo(null);
                rootFrame.remove(spielfeldPanel);
                rootFrame.add(game.getSpielfeldPanel(),0);
                rootFrame.setVisible(true);
            }
        });
        item.add(startHart);

        // Menupunkt: Neues Spiel -> Expert
        JMenuItem startExpert = new JMenuItem("Expert");
        startExpert.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Starte Spiel: Expert");
                game.GameEnde();
                game = new Game(20,50,100);
                rootFrame.setSize(1200,600);
                rootFrame.setLocationRelativeTo(null);
                rootFrame.remove(spielfeldPanel);
                rootFrame.add(game.getSpielfeldPanel(),0);
                rootFrame.setVisible(true);
            }
        });
        item.add(startExpert);
        menu.add(item);

        // Beenden-Button im Menu erstellen
        JMenuItem beenden = new JMenuItem("Beenden");
        beenden.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        menu.add(beenden);

        // Menü wird der Menüleiste hinzugefügt
        menuBar.add(menu);

        // Menüleiste wird für den Dialog gesetzt
        rootFrame.setJMenuBar(menuBar);

        // Spielfeldframe wird angeheftet
        rootFrame.add(spielfeldPanel);

        // Wir lassen unseren Frame anzeigen
        rootFrame.setVisible(true);
    }









}