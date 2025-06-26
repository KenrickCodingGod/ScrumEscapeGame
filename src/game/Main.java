package game;
public class Main {
    public static void main(String[] args) {
        GameUI ui = new GameUI();
        KamerFactory kamerFactory = new KamerFactory();
        GameEngine engine = new GameEngine(ui, kamerFactory);
        engine.start();
    }
}