import java.util.*;

public class Game {
    final private Player player = new Player();
    final private Dealer dealer = new Dealer(Game.createCardDeck());

    public void startGame(){
        System.out.println("Welcome BlackJack Table");
        newGameSession();
    }

    private void newGameSession() {
        System.out.println("****** Started New Session *******");

        dealer.startGame();
        player.startGame();

        dealer.setCardForDealer(dealer.getCards(2));
        dealer.removeCardsFromDeck(2);

        player.setCards(dealer.getCards(2));
        dealer.removeCardsFromDeck(2);

        // Show Cards Detail
        dealer.showVisibleCardsOfDealer(false);
        player.showCardsDetail();

        while (dealer.isUserWantNextCart() && dealer.canUserContinue()) {
            // Set new cards
            dealer.setCardForDealer(dealer.getCards(1));
            dealer.removeCardsFromDeck(1);

            player.setCards(dealer.getCards(1));
            dealer.removeCardsFromDeck(1);

            // Show Cards Detail
            dealer.showVisibleCardsOfDealer(false);
            player.showCardsDetail();
        }

        this.checkGameStatus();
        if (dealer.isUserContinueToGame()) {
            newGameSession();
        }
    }

    private void checkGameStatus() {
        while(dealer.isDealerScoreEnoughForFinish() == false) {
            dealer.setCardForDealer(dealer.getCards(1));
            dealer.removeCardsFromDeck(1);
        }

        System.out.println("****** End Game *******");

        GameResult gameResult = dealer.checkResult(player.getPlayerScore());

        switch (gameResult) {
            case LOSE:
                System.out.println("User LOSE");
                break;
            case SCORELESS:
                System.out.println("User SCORELESS");
                break;
            case WIN:
                System.out.println("User WIN");
                break;
        }

        // Show Cards Detail
        System.out.println("End Result");
        dealer.showVisibleCardsOfDealer(true);
        player.showCardsDetail();
    }

    public static List<Card> createCardDeck() {
        List<Card> cardDeck = new ArrayList<Card>();

        for(String name: Card.cardNames) {
            cardDeck.add(new Card(name, CardType.HEARTS));
            cardDeck.add(new Card(name, CardType.DIAMONDS));
            cardDeck.add(new Card(name, CardType.CLUBS));
            cardDeck.add(new Card(name, CardType.SPADES));
        }

        Collections.shuffle(cardDeck);
        return cardDeck;
    }
}
