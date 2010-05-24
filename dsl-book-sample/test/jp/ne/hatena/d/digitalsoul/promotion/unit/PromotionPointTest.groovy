package jp.ne.hatena.d.digitalsoul.promotion.unit;

import static org.junit.Assert.*

import jp.ne.hatena.d.digitalsoul.promotion.dsl.RuleBuilder;
import jp.ne.hatena.d.digitalsoul.promotion.model.EqualityCondition;
import jp.ne.hatena.d.digitalsoul.promotion.model.Flight;
import jp.ne.hatena.d.digitalsoul.promotion.model.Hotel;
import jp.ne.hatena.d.digitalsoul.promotion.model.Itinerary;
import jp.ne.hatena.d.digitalsoul.promotion.model.PromotionRule;
import org.junit.Test;

public class PromotionPointTest {

    /* Model */
    
    @Test
    public void shouldMatchScoreWhenSingleConditionMatches() throws Exception {

        // from "BOS" -> 300
        EqualityCondition condition = new EqualityCondition(name:"from", value:"BOS")
        PromotionRule rule = new PromotionRule(score:300)
        rule.conditions.add condition

        Itinerary itinerary = new Itinerary()
        itinerary.rules.add rule
        
        // from "BOS"
        Flight flight = new Flight(value:"BOS")
        itinerary.items.add flight

        assertEquals 300, itinerary.score()
        
    }

    @Test
    public void shouldScore0WhenSingleConditionNotMatches() throws Exception {

        // RULE:from "BOS" -> 300
        EqualityCondition condition = new EqualityCondition(name:"from", value:"BOS")
        PromotionRule rule = new PromotionRule(score:300)
        rule.conditions.add condition

        Itinerary itinerary = new Itinerary()
        itinerary.rules.add rule
        
        // ITINERARY:from "BOS"
        Flight flight = new Flight(value:"HOGE")
        itinerary.items.add flight

        assertEquals 0, itinerary.score()
        
    }

    @Test 
    public void shouldMatchScoreWhenAllOfComplexConditionMatches() throws Exception {

        // RULE:from "BOS" & brand "Hyatt" -> 500
        EqualityCondition conditionFrom = new EqualityCondition(name:"from", value:"BOS")
        EqualityCondition conditionBrand = new EqualityCondition(name:"brand", value:"Hyatt")
        PromotionRule rule = new PromotionRule(score:500)

        Itinerary itinerary = new Itinerary()
        itinerary.rules.add rule

        // ITINERARY:from "BOS" & brand "Hyatt"
        Flight flight = new Flight(value:"BOS")
        Hotel hotel = new Hotel(value:"Hyatt")
        itinerary.items.add flight
        itinerary.items.add hotel

        assertEquals 500, itinerary.score()
        
    }

    @Test 
    public void shouldScore0WhenNotAllOfMultipleConditionMatches() throws Exception {

        // RULE:from "BOS" & brand "Hyatt" -> 500
        EqualityCondition conditionFrom = new EqualityCondition(name:"from", value:"BOS")
        EqualityCondition conditionBrand = new EqualityCondition(name:"brand", value:"Hyatt")
        PromotionRule rule = new PromotionRule(score:500)
        rule.conditions.add conditionFrom
        rule.conditions.add conditionBrand

        Itinerary itinerary = new Itinerary()
        itinerary.rules.add rule

        // ITINERARY:from "BOS" & brand "Hyatt"
        Flight flight = new Flight(value:"BOS")
        Hotel hotel = new Hotel(value:"HOGE")
        itinerary.items.add flight
        itinerary.items.add hotel

        assertEquals 0, itinerary.score()        
    }

    /** DSL */

    @Test
    public void shouldBuildSingleConditionRule() {

        // RULE:from "BOS" -> 400
        RuleBuilder builder = new RuleBuilder()
        builder.score._400.when.from.equals.BOS

        Itinerary itinerary = builder.itinerary

        // ITINERARY:from "BOS" & brand "Hyatt"
        Flight flight = new Flight(value:"BOS")
        Hotel hotel = new Hotel(value:"Hyatt")
        itinerary.items.add flight
        itinerary.items.add hotel

        assertEquals 400, itinerary.score()        
        
    }

    @Test
    public void shouldBuildMultipleConditionRule() {

        // RULE:from "BOS" -> 400 / brand "Hyatt" -> 200
        RuleBuilder builder = new RuleBuilder()
        builder.score._400.when.from.equals.BOS
        builder.score._200.when.brand.equals.Hyatt

        Itinerary itinerary = builder.itinerary

        // ITINERARY:from "BOS" & brand "Hyatt"
        Flight flight = new Flight(value:"BOS")
        Hotel hotel = new Hotel(value:"Hyatt")
        itinerary.items.add flight
        itinerary.items.add hotel

        assertEquals 600, itinerary.score()        
        
    }

    @Test
    public void shouldBuildComplexConditionRule() {

        // RULE:from "BOS" & brand "Hyatt" -> 600
        RuleBuilder builder = new RuleBuilder()
        builder.score._600.when.from.equals.BOS.and.brand.equals.Hyatt

        Itinerary itinerary = builder.itinerary

        // ITINERARY:from "BOS" & brand "Hyatt"
        Flight flight = new Flight(value:"BOS")
        Hotel hotel = new Hotel(value:"Hyatt")
        itinerary.items.add flight
        itinerary.items.add hotel

        assertEquals 600, itinerary.score()   
        
    }

}
