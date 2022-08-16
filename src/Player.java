import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Player  {
    List<Card> cards = new ArrayList<Card>();

    void setCards(List<Card> newCards) {
        cards.addAll(newCards);
    }

    void showCardsDetail() {
        System.out.println("======= Player Cards =======");
        for(Card card: cards) {
            System.out.println(card.getTitle());
        }
        System.out.println("======= ************ =======");
    }

    void startGame() {
        cards.removeAll(cards);
    }

    int getPlayerScore(){
        int score = 0;
        for(Card card: cards) {
            score += card.value;
        }
        return score;
    }
}
