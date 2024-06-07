import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GameButton extends JButton {

    // Variablen
    private Icon buttonIcon;
    private Icon[] iconArray;
    private int pos_x;
    private int pos_y;
    private Zelle[][] spielfeld;
    private Game game;

    //Konstruktor
    public GameButton(Game game, int pos_x, int pos_y, Icon buttonIcon, Zelle[][] spielfeld, Icon[] iconArray){
        this.pos_x = pos_x;
        this.pos_y = pos_y;
        this.buttonIcon = buttonIcon;
        this.iconArray = iconArray;
        this.spielfeld = spielfeld;
        this.game = game;
        setBorderPainted(false);
        setMargin(new Insets(-2, -2, -2, -2));
        setSize(20,20);
        setIcon(buttonIcon);

        addMouseListener(new MouseAdapter() { // MouseEvent wird angelegt
            public void mouseReleased(MouseEvent e) {
                if (e.getButton() == 3) { // Rechte Maustaste
                    pressRightButton();
                }
                if (e.getButton() == 1) { // Linke Maustaste
                    pressLeftButton();
                }
            }
        });
    }

    // Mousebutton Funktionen
    public void pressRightButton() {
        // Beim drücken der Rechten Maustaste soll die Flagge gesetzt werden und zurückgesetzt werden
        spielfeld[this.pos_x][this.pos_y].switchFlagge();
        if (spielfeld[this.pos_x][this.pos_y].isFlagge()) this.setButtonIcon(this.iconArray[10]);
        else this.setButtonIcon(this.iconArray[9]);
        game.updateButtons();
    }

    public void pressLeftButton(){
        // Beim drücken der linken Maustaste soll das Feld geöffnet werden, sofern keine Flagge gesetzt wurde
        if (!spielfeld[pos_x][pos_y].isFlagge()) {
            if (isEnabled()) {
                if (spielfeld[pos_x][pos_y].isMine()) {
                    setButtonIcon(iconArray[12]);
                    game.GameLose();
                } else {
                    setButtonIcon(iconArray[spielfeld[pos_x][pos_y].MinesAround(spielfeld)]);
                }
                spielfeld[pos_x][pos_y].setOpen(spielfeld);
            }
        }
        game.updateButtons();
    }

    // Getter und Setter
    public void setButtonIcon(Icon icon) {
        this.buttonIcon = icon;
        this.setIcon(buttonIcon);
    }
}
