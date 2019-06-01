public class RWwatch_idle_State implements IRegionStates {

    CompositeOnInternetOn state;
    int region;

    public RWwatch_idle_State(CompositeOnInternetOn state, int region) {
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
        // in(download)
        IRegionStates r = state.getCurrentStateInRegion(1);
        if(r instanceof RDdownload_State && RDdownload_State.p.intValue()>=20)
        {
            state.setState(state.getState("watch", region), region);
            ((RWwatch_State)state.getState("watch", region)).t = 0;
        }
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
        System.out.println("exit watch_idle state");
    }

    @Override
    public void Enter() {
        System.out.println("enter watch_idle state");
    }

    @Override
    public void handleEvent(Event e) {

    }

    @Override
    public void Do() {

    }
}
