public interface ICompositeOnState {
    void internetOff();
    void internetOn();
    void Do();
    void Enter();
    void Exit();
    void handleEvent(Event e);
    void stop();
}
