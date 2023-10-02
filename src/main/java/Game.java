import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

import java.io.IOException;

public class Game {
    private final TerminalScreen screen;
    private int x = 10;
    private int y = 10;


    public Game(int  width, int height) throws IOException {
        TerminalSize terminalSize = new TerminalSize(width, height);
        Terminal terminal = new DefaultTerminalFactory().setInitialTerminalSize(terminalSize).createTerminal(); // terminal inteiro

        screen = new TerminalScreen(terminal);
        screen.setCursorPosition(null);
        screen.startScreen();
        screen.doResizeIfNecessary();

    }

    private void draw() throws IOException{
        screen.clear();
        screen.setCharacter(x,y, TextCharacter.fromCharacter('X')[0]);
        screen.refresh();}

    public void run() throws IOException{
        draw();
        KeyStroke key = screen.readInput();
        processKey(key);
        }

    private void processKey(KeyStroke key){
        System.out.println(key);
    }


}

