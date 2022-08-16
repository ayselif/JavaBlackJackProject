import java.util.*;

public class Dealer {
    private ArrayList<Card> cards = new ArrayList<Card>();
    List<Card> cardDeck;

    final private Scanner sc = new Scanner(System.in);

    Dealer(List<Card> cardDeck){
        this.cardDeck = cardDeck;
    }

    void setCardForDealer(List<Card> newCards) {
        cards.addAll(newCards);
    }

    void startGame() {
        if(cardDeck.size() < 25) {
            cardDeck = Game.createCardDeck();
        }
        cards.removeAll(cards);
    }

    List<Card> getCards(int count) {
        return cardDeck.subList(0, count);
    }

    void removeCardsFromDeck(int count) {
        for(int i = 0; i<count; i++) {
            cardDeck.remove(0);
        }
    }

    Boolean canUserContinue() {
        return cardDeck.size() > 20;
    }

    GameResult checkResult(int playerScore) {
        int dealerScore = getDealerScore();

        if (playerScore == dealerScore) {
            return GameResult.SCORELESS;
        }

        else if ((playerScore <= 21) && (playerScore > dealerScore)) {
            return GameResult.WIN;
        }
        else if ((playerScore <= 21 && dealerScore > 21)) {
            return GameResult.WIN;
        }
        else {
            return GameResult.LOSE;
        }
     }


     boolean isDealerScoreEnoughForFinish() {
        if (getDealerScore() < 17 ) {
            return false;
        } else {
            return true;
        }
     }

     Boolean isUserContinueToGame() {
        System.out.println("Make a choice: For Exit Press N " + "For continue: Press Y:");
        char input = sc.nextLine().toLowerCase().charAt(0);

        switch (input) {
            case 'y':
                return true;
            case 'n':
                return false;
            default:
                System.out.println("It is unsupported option. .....");
                return isUserContinueToGame();
        }
     }

    Boolean isUserWantNextCart() {
        System.out.println("Make a choice: Done D" + " Take a new cart: Press C:");
        char input = sc.nextLine().toLowerCase().charAt(0);

        switch (input) {
            case 'c':
                return true;
            case 'd':
                return false;
            default:
                System.out.println("It is unsupported option. .....");
                return isUserWantNextCart();
        }
    }

    void showVisibleCardsOfDealer(Boolean showAll) {
        System.out.println("======= Dealer Cards =======");
        for(int i = 0; i<cards.size(); i++) {
            // Mask first card
            if (i == 0 && showAll == false) {
                System.out.println("#########");
                continue;
            }
            Card currentCard = cards.get(i);
            System.out.println(currentCard.getTitle());
        }
        System.out.println("======= ************ =======");
    }

    private Integer getDealerScore() {
        int score = 0;

        for (Card card: cards){
            score += card.value;
        }
        return score;
    }
}
