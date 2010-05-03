package jp.ne.hatena.d.digitalsoul.missgrant.unit;

import static org.junit.Assert.*;
import jp.ne.hatena.d.digitalsoul.missgrant.model.Command;
import jp.ne.hatena.d.digitalsoul.missgrant.model.Event;
import jp.ne.hatena.d.digitalsoul.missgrant.model.State;
import jp.ne.hatena.d.digitalsoul.missgrant.model.StateMachine;
import jp.ne.hatena.d.digitalsoul.missgrant.model.StateMachineController;
import jp.ne.hatena.d.digitalsoul.missgrant.dsl.BasicStateMachine;
import org.junit.BeforeClass;
import org.junit.Test;

public class MissGrantsControllerTest {
   
    private static StateMachineController gate;

    /**
     * 簡易な状態遷移の設定（モデルのテスト）
     * [LOCKED/lockGate] -> coinInserted -> [UNLOCKED/unlockGate] -> passengerPassed -> [LOCKED GATE]
     */
    @BeforeClass
    public static void setUpStateMachine() {

        State locked = new State(name:"LOCKED")
        State unlocked = new State(name:"UNLOCKED")

        Command lockGateCmd = new Command(name:"lockGate", code:"LG")
        Command unlockGateCmd = new Command(name:"unlockGate", code:"UG")

        Event coinInserted = new Event(name:"coinInserted", code:"CI")
        Event passengerPassed = new Event(name:"passengerPassed", code:"PP")

        locked.addTransition(coinInserted, unlocked)
        locked.addAction(lockGateCmd)

        unlocked.addTransition(passengerPassed, locked)
        unlocked.addAction(unlockGateCmd)
        
        StateMachine stateMachine = new StateMachine(defaultState:locked)

        StateMachineController gate = new StateMachineController(stateMachine:stateMachine, currentState:locked)
        MissGrantsControllerTest.gate = gate
    }

    /* モデルのテスト */
    
    @Test
    public void shouldTransitToUnlockedWhenCoinInserted() throws Exception {

        // given locked
        assertEquals "LOCKED", gate.currentState.name
        
        // when coin inserted
        gate.handle "CI"

        // then UNLOCKED state
        assertEquals "UNLOCKED", gate.currentState.name
    }

    @Test
    public void shouldTransitToLockedWhenPassengerPassed() throws Exception {

        // given unlocked
        assertEquals "UNLOCKED", gate.currentState.name
        
        // when passenger passed
        gate.handle "PP"

        // then LOCKED state
        assertEquals "LOCKED", gate.currentState.name
        
    }

    @Test
    public void shouldNotTransitWhenUnknownCommand() throws Exception {

        // given locked
        assertEquals "LOCKED", gate.currentState.name

        // when unknown command sent
        gate.handle "UNKNOWN"

        // then LOCKED state
        assertEquals "LOCKED", gate.currentState.name
        
    }

    /* DSLのテスト */

    @Test
    public void shouldBuildMissGrantsController() throws Exception {

        // setup
        StateMachineController controller = new StateMachineController()
        new BasicStateMachine().build(controller)

        // idle
        assertEquals "idle", controller.currentState.name

        // when doorClosed
        controller.handle "D1CL"

        // then active state
        assertEquals "active", controller.currentState.name

        // when lightOn
        controller.handle "L1ON"

        // then waitingForDraw state
        assertEquals "waitingForDraw", controller.currentState.name

        // when drowOpened
        controller.handle "D2OP"

        // then unlockedPanel state
        assertEquals "unlockedPanel", controller.currentState.name

        // when panelClosed
        controller.handle "PNCL"

        // then idle state
        assertEquals "idle", controller.currentState.name
    }

    
}
