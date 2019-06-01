public class RWhold_State implements IRegionStates {

    CompositeOnInternetOn state;
    int region;

    public RWhold_State(CompositeOnInternetOn state, int region) {
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
        resume();
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
        state.setState(state.getState("watch", region), region);
    }

    @Override
    public void Exit() {
        System.out.println("exit hold state");
    }

    @Override
    public void Enter() {
        System.out.println("enter hold state");
    }

    @Override
    public void handleEvent(Event e) {

    }

    @Override
    public void Do() {
        if(RDdownload_State.p.intValue() >= 100 || !MDownloader.d) {
            ((RWwatch_State)state.getState("watch", region)).t = 0;
            state.setState(state.getState("watch_idle", region), region);
        }
    }
}
