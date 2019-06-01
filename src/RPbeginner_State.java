public class RPbeginner_State implements IRegionStates {

    CompositeOnInternetOn state;
    int region;

    public RPbeginner_State(CompositeOnInternetOn state, int region) {
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
        System.out.println("exit beginner state");
    }

    @Override
    public void Enter() {
        System.out.println("enter beginner state");
        MDownloader.speed = 1;
    }

    @Override
    public void handleEvent(Event e) {

    }

    @Override
    public void Do() {
        if(CompositeOnInternetOn.po > 3)
            state.setState(state.getState("advanced", region), region);
    }
}
