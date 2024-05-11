import java.util.Random;

public class Main {

    public static void main(String[] args) {
        // Variablen-Deklaration
        int minen;
        int sizeX;
        int sizeY;
        boolean explodiert = false;

        // Variablen-Abfrage später über UI hier jetzt manuell
        minen = 10;
        sizeX = 10;
        sizeY = 10;
        int testX = 8;
        int testY = 8;

        // Initialisierung neues Spielfeld
        Zelle[][] spielfeld = InitNewGame(sizeX, sizeY, minen);

        // Testumgebung
        TestSpielfeldMinen(spielfeld);
        TestMinesAround(spielfeld, testX, testY);
        //spielfeld[3][3].setMine(true);
        //spielfeld[testX][testY].switchFlagge();
        //spielfeld[testX][testY].switchFlagge();
        //TestSetOpen(spielfeld,testX,testY);
        if (spielfeld[testX][testY].MinesAround(spielfeld)==0) spielfeld = spielfeld[testX][testY].OpenAround(spielfeld);
    }

    // Testfunktionen (können nach Beenden des Projekts gelöscht werden)
    private static void TestSetOpen(Zelle[][] spielfeld, int x, int y) {
        // Testet Zelle.setOpen()
         spielfeld = spielfeld[x][y].setOpen(spielfeld);
            if (!spielfeld[x][y].isMine()) System.out.println("Feld "+x+"/"+y+" geöffnet. (setOpen)");
            else System.out.println("BOOOM! (isMine)");

    }

    private static void TestMinesAround(Zelle[][] spielfeld, int x, int y) {
        // Testet Zelle.MinesAround()
        // System.out.println("Aufruf Testfunktion TestMinesAround()");
        if (x < spielfeld.length && y < spielfeld[x].length) System.out.println("Um Feld "+x+"/"+y+" liegen "+spielfeld[x][y].MinesAround(spielfeld)+" Minen!");
    }

    private static void TestSpielfeldMinen(Zelle[][] spielfeld) {
        // Testet Spielfeld und gibt aus, wo Minen liegen!
        // System.out.println("Aufruf Testfunktion TestSpielfeldMinen()");
        for (int x = 0; x < spielfeld.length; x++){
            for (int y = 0; y < spielfeld[x].length; y++){
                if (spielfeld[x][y].isMine()) System.out.println("Auf Feld "+x+"/"+y+" liegt eine Mine!");
            }
        }
    }

    // Funktionen
    private static Zelle[][] InitNewGame(int sizeX, int sizeY, int minen) {
        // Spielfeld initialisieren
        Zelle[][] spielfeld = new Zelle[sizeX][sizeY];
        for (int y = 0; y < sizeY; y++) {
            for (int x = 0; x < sizeX; x++) {
                spielfeld[x][y] = new Zelle(x,y);
            }
        }

        // Minen setzen
        if (sizeX*sizeY > minen) {
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


}