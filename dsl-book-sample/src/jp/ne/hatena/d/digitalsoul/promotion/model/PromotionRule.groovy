package jp.ne.hatena.d.digitalsoul.promotion.model;

public class PromotionRule {
    int score
    List<Condition> conditions = []

    int scoreOf(Itinerary itinerary) {
        boolean matches = true
        for (Condition condition in conditions) {
            if (!condition.isSatisfiedBy(itinerary)) {
                matches = false
                break;
            }
        }
        matches ? score : 0
    }
}
