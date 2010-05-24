package jp.ne.hatena.d.digitalsoul.promotion.model;

public class EqualityCondition extends Condition {

    boolean isSatisfiedBy(Itinerary itinerary) {
        for (Item item in itinerary.items) { 
            if (item.name() == name && item.value == value) { 
                return true
            }
        }
        false
    }
}
