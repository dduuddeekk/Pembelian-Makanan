public class Main {
    public static void main(String[] args){
        Login();
    }
    public static void Login(){
        System.out.println("Log In");
        System.out.print("Username: ");
        String username = Utility.scanString();
        System.out.print("Password: ");
        String password = Utility.scanString();
        System.out.print("\033[H\033[2J");
        System.out.flush();
        if(username.equals("admin01") && password.equals("SayaGanteng01")){
            //Admin Admin = new Admin();
            Admin.adminMenu();
        }else{
            System.out.println("User " + username);
            Users.usersMenu();
        }
    }
}
