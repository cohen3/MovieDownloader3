public interface IRegionStates {
    void fileRequest();
    void downloadAborted();
    void downloadError();
    void errorFixed();
    void movieOn();
    void restartMovie();
    void holdMovie();
    void movieOff();
    void resume();
    void Exit();
    void Enter();
    void handleEvent(Event e);
    void Do();
}
