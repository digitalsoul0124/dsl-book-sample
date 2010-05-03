package jp.ne.hatena.d.digitalsoul.missgrant.dsl;

import jp.ne.hatena.d.digitalsoul.missgrant.model.Command;
import jp.ne.hatena.d.digitalsoul.missgrant.model.Event;

public interface IAbstractEventBuilder {

    IAbstractEventBuilder actions(Command... actions)

    IStateBuilder when(Event trigger)
}
