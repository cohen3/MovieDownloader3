public class RDfix_State implements IRegionStates {

    CompositeOnInternetOn state;
    int region;
    boolean fix = false;
    int i;

    public RDfix_State(CompositeOnInternetOn state, int region) {
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
        fix = true;
        state.setState(state.getState("download", region), region);
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
        System.out.println("exit fix state");
    }

    @Override
    public void Enter() {
        System.out.println("enter fix state");
        fix = false;
        i = 0;
//        Thread t = new Thread(()->Do());
//        t.start();
    }

    public void Do()
    {
//        int i = 0;
//        while(i < 8 && !fix) {
//            try {
//                Thread.sleep(500);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            i++;
//        }
//        if(fix) return;
//        CompositeOnInternetOn.po--;
//        EventListener.disk += MDownloader.size;
//        MDownloader.d = false;
//        ((RDdownload_State)state.getState("download",region)).s = 0;
//        state.setState(state.getState("download_idle", region), region);
        i++;
        if(i >= 4 && !fix)
        {
            CompositeOnInternetOn.po--;
            EventListener.disk += MDownloader.size;
            MDownloader.d = false;
            ((RDdownload_State)state.getState("download",region)).s = 0;
            state.setState(state.getState("download_idle", region), region);
        }
    }

    @Override
    public void handleEvent(Event e) {

    }
}
