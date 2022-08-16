import java.util.*;

public class Card {
    final static String[] cardNames = {"A", "Q", "J", "K", "2","3","4","5","6","7","8","9","10"};

    String name;
    Integer value;
    CardType type;
    Card(String name, CardType type){
        this.name = name;
        this.value = getInitialCardValue(name);
        this.type = type;
    }

    private Integer getInitialCardValue(String name) {
        switch (name) {
            case "K", "Q", "J":
                return  10;
            case "A":
                return 1;
            case "2","3","4","5","6","7","8","9","10":
                return Integer.valueOf(name);
        }
        return 0;
    }

    String getTitle (){
        return this.name + " " + this.type;
    }
}

enum CardType {
    HEARTS,
    DIAMONDS,
    CLUBS,
    SPADES
}

enum GameResult {
    WIN,
    LOSE,
    SCORELESS
}


