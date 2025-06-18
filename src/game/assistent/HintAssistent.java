package game.assistent;

public class HintAssistent {
    private int kamerPositie;

    public HintAssistent(int kamerPositie) {
        this.kamerPositie = kamerPositie;
    }


    public void voerUit() {
        switch (kamerPositie) {
            case 0:
                System.out.println("💡 Hint: Denk aan de drie Scrumrollen en hun verantwoordelijkheden.");
                break;
            case 2:
                System.out.println("💡 Hint: Denk aan hoe het team zich aanpast aan veranderingen tijdens de sprint.");
                break;
            default:
                System.out.println("💡 Hint: Algemeen Scrumprincipe.");
                break;
        }
    }
}