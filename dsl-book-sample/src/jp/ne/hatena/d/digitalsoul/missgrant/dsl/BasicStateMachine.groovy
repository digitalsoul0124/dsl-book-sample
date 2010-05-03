package jp.ne.hatena.d.digitalsoul.missgrant.dsl;

import jp.ne.hatena.d.digitalsoul.missgrant.model.Event;

public class BasicStateMachine extends StateMachineBuilder {

    void defineStateMachine() {

        def doorClosed  = event("doorClosed",  "D1CL")
        def drawOpened  = event("drawOpened",  "D2OP")
        def lightOn     = event("lightOn",     "L1ON")
        def doorOpened  = event("doorOpened",  "D1OP")
        def panelClosed = event("panelClosed", "PNCL")

        state("idle")
            .actions(command("unlockDoor", "D1UL"), command("lockPanel", "PNLK"))
            .when(doorClosed).then(state("active")) 

        state("active")
            .when(drawOpened).then(state("waitingForLight"))
            .when(lightOn).then(state("waitingForDraw"))

        state("waitingForLight")
            .when(lightOn).then(state("unlockedPanel"))

        state("waitingForDraw")
            .when(drawOpened).then(state("unlockedPanel"))

        state("unlockedPanel")
            .actions(command("unlockPanel","PNUL"), command("lockDoor", "D1LK"))
            .when(panelClosed).then(state("idle"))

        defaultState "idle"
        startState   "idle"
    }

}
