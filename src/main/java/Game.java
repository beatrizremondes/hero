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
    enum State {
        Pause,
        Options,
        GameOver,
        Play
    }
    private final TerminalScreen screen;
    private final hero Hero;

    private State state = State.Play;


    public Game(int width, int height) throws IOException {
        TerminalSize terminalSize = new TerminalSize(width, height);
        Terminal terminal = new DefaultTerminalFactory().setInitialTerminalSize(terminalSize).createTerminal(); // terminal inteiro

        screen = new TerminalScreen(terminal);
        screen.setCursorPosition(null);
        screen.startScreen();
        screen.doResizeIfNecessary();
        Position heroPosition = new Position(10, 10);
        Hero = new hero(heroPosition);
    }

    private void draw() throws IOException {
        screen.clear();
        Hero.draw(screen);
        screen.refresh();
    }

    private void exitGame() throws IOException{
        screen.close();
        state = null;
    }

    private void moveHero(Position position) {
        Hero.setPosition(position);
    }

    public void run() throws IOException {
        while (state != null) {
            draw();
            KeyStroke key = screen.readInput();

            // Process the key stroke if one is available
            if (key != null) {
                processKey(key);


            }
        }
        screen.close();
    }

    public void processKey(KeyStroke keyStroke) throws IOException {
        KeyType keyType = keyStroke.getKeyType();

        switch (keyType) {
            case ArrowUp:
                // If the ArrowUp key is pressed, decrement y by 1
                moveHero(Hero.moveUp());
                break;
            case ArrowRight:
                // If the ArrowRight key is pressed, increment x by 1
                moveHero(Hero.moveRight());
                break;
            case ArrowDown:
                // If the ArrowDown key is pressed, increment y by 1
                moveHero(Hero.moveDown());
                break;
            case ArrowLeft:
                // If the ArrowLeft key is pressed, decrement x by 1
                moveHero(Hero.moveLeft());
                break;
            case Character:
                if (keyStroke.getCharacter() == 'q') {

                    exitGame();
                    break;
                }
            case EOF:
                exitGame();
                break;
            default:
                // Handle other key types or do nothing
                break;
        }


    }
}

