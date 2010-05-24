package jp.ne.hatena.d.digitalsoul.promotion.model;

public class Itinerary {
    List<PromotionRule> rules = []
    List<Item> items = []

    int score() {
        int sum = 0
        rules.each { sum += it.scoreOf(this) }
        sum
    }
}
