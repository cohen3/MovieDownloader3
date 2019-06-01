public class RWwatch_State implements IRegionStates {

    CompositeOnInternetOn state;
    int region;
    public int t;

    public RWwatch_State(CompositeOnInternetOn state, int region) {
        this.state = state;
        this.region = region;
        t = 0;
    }

    @Override
    public void fileRequest() {

    }

    @Override
    public void downloadAborted() {

    }

    @Override
    public void downloadError() {
        state.setState(state.getState("hold", region), region);
    }

    @Override
    public void errorFixed() {

    }

    @Override
    public void movieOn() {

    }

    @Override
    public void restartMovie() {
        t = 0;
    }

    @Override
    public void holdMovie() {
        state.setState(state.getState("hold", region), region);
    }

    @Override
    public void movieOff() {
        state.setState(state.getState("watch_idle", region), region);
        t = 0;
    }

    @Override
    public void resume() {

    }

    @Override
    public void Exit() {
        System.out.println("exit watch state");
    }

    @Override
    public void Enter() {
        System.out.println("enter watch state");
    }

    @Override
    public void handleEvent(Event e) {

    }

    @Override
    public void Do() {
        //System.out.println("watch do: "+(RDdownload_State.p.intValue())+", "+!MDownloader.d);
        if(MDownloader.d)
            t++;
        else
            movieOff();
    }
}
