public class Zelle {

    // Variablen der Klasse
    private boolean flagge = false;
    private boolean mine = false;
    private boolean open = false;
    private final int x;
    private final int y;

    // Konstruktor
    public Zelle(int x, int y) {
        this.x = x;
        this.y = y;
    }

    // Funktionen der Klasse
    public int MinesAround(Zelle[][] spielfeld) {
        // System.out.println("Aufruf MinesAround()");
        int anzahl = 0;
        if (this.x > 0) if (spielfeld[this.x-1][this.y].isMine()) anzahl++;                                                // Links
        if (this.x < spielfeld.length-1) if (spielfeld[this.x+1][this.y].isMine()) anzahl++;                                 // Rechts
        if (this.x > 0 && y > 0) if (spielfeld[this.x-1][this.y-1].isMine()) anzahl++;                                     // Oben Links
        if (this.y > 0) if (spielfeld[this.x][this.y-1].isMine()) anzahl++;                                                // Oben
        if (this.x < spielfeld.length-1 && this.y > 0) if (spielfeld[this.x+1][this.y-1].isMine()) anzahl++;                      // Oben Rechts
        if (this.x > 0 && this.y < spielfeld[this.x].length-1) if (spielfeld[this.x-1][this.y+1].isMine()) anzahl++;                   // Unten Links
        if (this.y < spielfeld[this.x].length-1) if (spielfeld[this.x][this.y+1].isMine()) anzahl++;                              // Unten
        if (this.x < spielfeld.length-1 && this.y < spielfeld[this.x].length-1) if (spielfeld[this.x+1][this.y+1].isMine()) anzahl++;    // Unten Rechts
        return anzahl;
    }

    public Zelle[][] OpenAround(Zelle[][] spielfeld) {
        // System.out.println("Aufruf OpenRound()");
        if (this.x > 0) spielfeld = spielfeld[this.x-1][this.y].setOpen(spielfeld);// Links
        if (this.x < spielfeld.length-1) spielfeld = spielfeld[this.x+1][this.y].setOpen(spielfeld);// Rechts
        if (this.x > 0 && this.y > 0) spielfeld = spielfeld[this.x-1][this.y-1].setOpen(spielfeld); // Oben Links
        if (this.y > 0) spielfeld = spielfeld[this.x][this.y-1].setOpen(spielfeld);   // Oben
        if (this.x < spielfeld.length-1 && this.y > 0) spielfeld = spielfeld[this.x+1][this.y-1].setOpen(spielfeld);    // Oben Rechts
        if (this.x > 0 && this.y < spielfeld[this.x].length-1) spielfeld = spielfeld[this.x-1][this.y+1].setOpen(spielfeld); // Unten Links
        if (this.y < spielfeld[this.x].length-1) spielfeld = spielfeld[this.x][this.y+1].setOpen(spielfeld);  // Unten
        if (this.x < spielfeld.length-1 && this.y < spielfeld[this.x].length-1) spielfeld = spielfeld[this.x+1][this.y+1].setOpen(spielfeld); // Unten Rechts
        return spielfeld;
    }

    public Zelle[][] setOpen(Zelle[][] spielfeld) {
        // System.out.println("Aufruf setOpen("+this.x+"/"+this.y+")");
        if (!this.flagge && !this.open) {
            this.open = true;
            if (spielfeld[this.x][this.y].MinesAround(spielfeld)==0) spielfeld = spielfeld[this.x][this.y].OpenAround(spielfeld);
            // System.out.println("Feld "+this.x+"/"+this.y+" geöffnet!");
        }
        return spielfeld;
    }

    // Getter und Setter
    public boolean isFlagge() {
        return flagge;
    }

    public void switchFlagge() {
        this.flagge = !this.flagge;
    }

    public boolean isMine() {
        return mine;
    }

    public void setMine() {
        this.mine = true;
    }

    public boolean isOpen() {
        return open;
    }

}
