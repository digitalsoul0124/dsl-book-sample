package jp.ne.hatena.d.digitalsoul.promotion.unit;

import static org.junit.Assert.*;

import jp.ne.hatena.d.digitalsoul.promotion.dsl.RuleEvaluator;
import jp.ne.hatena.d.digitalsoul.promotion.model.Flight;
import jp.ne.hatena.d.digitalsoul.promotion.model.Hotel;
import jp.ne.hatena.d.digitalsoul.promotion.model.Itinerary;
import org.junit.Test;

public class PromotionPointWithTextualPolishingTest {

    @Test
    public void shouldBuildConditionByPuseudoNaturalLanguage() {

        // RULE:from "BOS" & brand "Hyatt" -> 600
        RuleEvaluator evaluator = new RuleEvaluator()
        evaluator.rule("score 600 when from equals BOS and brand equals Hyatt")

        Itinerary itinerary = evaluator.itinerary

        // ITINERARY:from "BOS" & brand "Hyatt"
        Flight flight = new Flight(value:"BOS")
        Hotel hotel = new Hotel(value:"Hyatt")
        itinerary.items.add flight
        itinerary.items.add hotel

        assertEquals 600, itinerary.score()   
        
    }
}
