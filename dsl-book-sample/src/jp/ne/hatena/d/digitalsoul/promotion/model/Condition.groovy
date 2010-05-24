package jp.ne.hatena.d.digitalsoul.promotion.model;

public abstract class Condition {
    String name
    String value

    abstract boolean isSatisfiedBy(Itinerary itinerary)
}
