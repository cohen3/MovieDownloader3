public class RPadvanced_State implements IRegionStates {

    CompositeOnInternetOn state;
    int region;

    public RPadvanced_State(CompositeOnInternetOn state, int region) {
        this.state = state;
        this.region = region;
    }

    @Override
    public void fileRequest() {

    }

    @Override
    public void downloadAborted() {

    }

    @Override
    public void downloadError() {

    }

    @Override
    public void errorFixed() {

    }

    @Override
    public void movieOn() {

    }

    @Override
    public void restartMovie() {

    }

    @Override
    public void holdMovie() {

    }

    @Override
    public void movieOff() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void Exit() {
        System.out.println("exit advanced state");
    }

    @Override
    public void Enter() {
        System.out.println("enter advanced state");
        MDownloader.speed = 1.2;
    }

    @Override
    public void handleEvent(Event e) {

    }

    @Override
    public void Do() {
        if(CompositeOnInternetOn.po > 6)
            state.setState(state.getState("pro", region), region);
        if(CompositeOnInternetOn.po < 4)
            state.setState(state.getState("beginner", region), region);
    }
}
