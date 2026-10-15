package awesomecucumber.constants;



public enum Endpoint {

    STORE("/store");

    public String url;

     Endpoint(String url){
        this.url=url;
    }
}
