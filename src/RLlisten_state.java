public class RLlisten_state implements IRegionStates, Observable{

    CompositeOnInternetOn state;
    int region;
    Observer observer;

    public RLlisten_state(CompositeOnInternetOn state, int region, Observer o) {
        this.state = state;
        this.region = region;
        this.observer = o;
    }

    @Override
    public void fileRequest() {
        if(MDownloader.d || MDownloader.size > EventListener.disk) return;
        EventListener.disk -= MDownloader.size;
        MDownloader.d = true;
        notify(Event.when);
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
        System.out.println("exit listen state");
    }

    @Override
    public void Enter() {
        System.out.println("enter listen state");
    }

    @Override
    public void handleEvent(Event e) { }

    @Override
    public void Do() { }

    @Override
    public void notify(Event e) {
        observer.update(e);
    }
}
