import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

import java.io.IOException;

public class Game {
    private final TerminalScreen screen;
    private int x = 10;
    private int y = 10;


    public Game(int width, int height) throws IOException {
        TerminalSize terminalSize = new TerminalSize(width, height);
        Terminal terminal = new DefaultTerminalFactory().setInitialTerminalSize(terminalSize).createTerminal(); // terminal inteiro

        screen = new TerminalScreen(terminal);
        screen.setCursorPosition(null);
        screen.startScreen();
        screen.doResizeIfNecessary();

    }

    private void draw() throws IOException {
        screen.clear();
        screen.setCharacter(x, y, TextCharacter.fromCharacter('X')[0]);
        screen.refresh();
    }

    public void run() throws IOException {
        while (true) {
            draw();
            KeyStroke key = screen.readInput();

            // Process the key stroke if one is available
            if (key != null) {
                processKey(key);


            }
        }
    }

    public void processKey(KeyStroke keyStroke) throws IOException {
        KeyType keyType = keyStroke.getKeyType();

        switch (keyType) {
            case ArrowUp:
                // If the ArrowUp key is pressed, decrement y by 1
                y--;
                break;
            case ArrowRight:
                // If the ArrowRight key is pressed, increment x by 1
                x++;
                break;
            case ArrowDown:
                // If the ArrowDown key is pressed, increment y by 1
                y++;
                break;
            case ArrowLeft:
                // If the ArrowLeft key is pressed, decrement x by 1
                x--;
                break;
            case Character:
                if (keyStroke.getCharacter() == 'q') {

                    screen.close();
                    return;
                }
            case EOF:
                break;
            default:
                // Handle other key types or do nothing
                break;
        }


    }
}

