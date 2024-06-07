import javax.swing.*;
import java.util.Random;

public class Game {
    // Spielfeld-Panel
    private JPanel spielfeldPanel = new JPanel();

    // Wir definieren die Icon-Vorlagen
    private Icon icon[] = LoadIcons();

    // Neues Panel für die Buttons
    private JPanel buttons = new JPanel();

    // Spielfeld
    private int sizeX;
    private int sizeY;
    private boolean running = true;
    private Zelle[][] spielfeld;
    private GameButton[][] button;

    // Konstruktoren
    public Game() {

    }

    public Game(int sizeX, int sizeY, int minen){

        // Konstruktor für ein neues Spiel. Benötigt die Variablen für die Spielfeldgröße und die Anzahl der Minen
        this.sizeX = sizeX;
        this.sizeY = sizeY;

        // Spielfeld im Backend wird initialisiert
        this.spielfeld = InitNewGame(sizeX, sizeY, minen);

        // Buttongrid wird generiert (JPanel)
        buttons.setLayout( new java.awt.GridLayout( sizeX, sizeY ) );

        // Buttons werden erstellt
        this.button = new GameButton[sizeX][sizeY];
        for(int x=0; x<sizeX; x++) {
            for(int y=0; y<sizeY; y++) {
                button[x][y] = new GameButton(this, x, y, icon[9], this.spielfeld, icon);
                buttons.add(button[x][y]);
            }
        }
        this.spielfeldPanel.add(buttons);
        this.spielfeldPanel.setSize(800,800);
        this.buttons.revalidate();
        this.buttons.repaint();
        this.spielfeldPanel.setVisible(true);
    }

    // Funktionen
    public void updateButtons() {
        int counter = 0;
        int anzahlFelder = this.sizeX * this.sizeY;
        int anzahlFlaggen = 0;
        // Hier sollen die Icons und Events der Buttons aktualisiert werden
        for(int x=0; x<sizeX; x++) {
            for(int y=0; y<sizeY; y++) {
                if (this.spielfeld[x][y].isOpen()){
                    this.button[x][y].setButtonIcon(icon[spielfeld[x][y].MinesAround(spielfeld)]);;
                    counter++;
                }
                if (this.spielfeld[x][y].isFlagge() && this.spielfeld[x][y].isMine()) anzahlFlaggen++;
            }
        }
        int geloesteFelder = counter + anzahlFlaggen;
        if (geloesteFelder == anzahlFelder) {
            GameGewonnen();
        }
    }

    private static Icon[] LoadIcons() {
        Icon[] icon = {
                new ImageIcon("res/0.gif", "0 Minen"),
                new ImageIcon("res/1.gif", "1 Mine"),
                new ImageIcon("res/2.gif", "2 Minen"),
                new ImageIcon("res/3.gif", "3 Minen"),
                new ImageIcon("res/4.gif", "4 Minen"),
                new ImageIcon("res/5.gif", "5 Minen"),
                new ImageIcon("res/6.gif", "6 Minen"),
                new ImageIcon("res/7.gif", "7 Minen"),
                new ImageIcon("res/8.gif", "8 Minen"),
                new ImageIcon("res/verdeckt.gif", "Geschlossenes Feld"),
                new ImageIcon("res/flagge.gif", "Geflaggtes Feld"),
                new ImageIcon("res/explosion.gif", "Game Over"),
                new ImageIcon("res/mine.gif", "Mine"),
        };
        return icon;
    }

    private static Zelle[][] InitNewGame(int sizeX, int sizeY, int minen) {
        // Spielfeld initialisieren
        Zelle[][] spielfeld = new Zelle[sizeX][sizeY];
        for (int y = 0; y < sizeY; y++) {
            for (int x = 0; x < sizeX; x++) {
                spielfeld[x][y] = new Zelle(x, y);
            }
        }

        // Minen setzen
        if (sizeX * sizeY > minen) {
            int resultX;
            int resultY;

            for (int i = 0; i < minen; i++) {
                Random r = new Random();
                do {
                    resultX = r.nextInt(sizeX);
                    resultY = r.nextInt(sizeY);
                }
                while (spielfeld[resultX][resultY].isMine());
                spielfeld[resultX][resultY].setMine();
            }

        } else System.out.println("Fehler! Spielfeld zu klein / Zu viele Minen!");
        return spielfeld;
    }

    public void GameEnde() {
        setRunning(false);
        this.spielfeldPanel.remove(buttons);
    }

    private void GameGewonnen() {
        if (isRunning()) {

            // Spielendebildschirm
            JDialog meinJDialog = new JDialog();
            meinJDialog.setTitle("Yeah! Die Menschheit wurde gerettet!");
            meinJDialog.setSize(300, 300);
            meinJDialog.setModal(true);
            meinJDialog.setLocationRelativeTo(null);
            BackgroundPanel backgroundPanel = new BackgroundPanel("res/sieg.jpg");
            meinJDialog.add(backgroundPanel);
            meinJDialog.setSize(300, 300);
            meinJDialog.setVisible(true);
        }
        GameEnde();
    }

    public void GameLose() {

        if (isRunning()) {
            // Spielendebildschirm
            JDialog meinJDialog = new JDialog();
            meinJDialog.setTitle("KABOOOM!!! Du hast verloren!");
            meinJDialog.setSize(300, 300);
            meinJDialog.setModal(true);
            meinJDialog.setLocationRelativeTo(null);
            BackgroundPanel backgroundPanel = new BackgroundPanel("res/explosion.jpg");
            meinJDialog.add(backgroundPanel);
            meinJDialog.setSize(300, 300);
            meinJDialog.setVisible(true);
        }
        GameEnde();
    }

    // Getter und Setter
    public boolean isRunning() {
        return running;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    public JPanel getSpielfeldPanel() {
        return spielfeldPanel;
    }
}
