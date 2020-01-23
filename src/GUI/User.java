package GUI;

public class User {
    private String username;
    private String password;
    private int gebruikersnr;

    public void setGebruikersnr(int gebruikersnr){
        this.gebruikersnr = gebruikersnr;
    }

    public int getGebruikersnr(){
        return gebruikersnr;
    }

    public String getUsername(){
        return this.username;
    }

    public void setUsername(String username){
        this.username = username;
    }

    public String getPassword(){
        return this.password;
    }

    public void setPassword(String password){
        this.password = password;
    }

}
