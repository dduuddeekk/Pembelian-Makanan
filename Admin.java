import java.util.ArrayList;

public class Admin {
    public static ArrayList<String> address = new ArrayList<String>();
    public static ArrayList<String> restaurant = new ArrayList<>();
    public static ArrayList<ArrayList<String>> foodMenu = new ArrayList<>();
    public static ArrayList<ArrayList<String>> drinkMenu = new ArrayList<>();
    public static ArrayList<ArrayList<Integer>> foodPrice = new ArrayList<>();
    public static ArrayList<ArrayList<Integer>> drinkPrice = new ArrayList<>();

    private static int restaurantAmmount = 0;
    public static void adminMenu(){
        System.out.println("MENU ADMIN");
        System.out.println("1. Lihat Rumah Makan");
        System.out.println("2. Tambah Rumah Makan");
        System.out.println("3. Hapus Rumah Makan");
        System.out.println("0. Kembali");
        System.out.print("Masukkan pilihan: ");
        String choice = Utility.scanString();
        System.out.print("\033[H\033[2J");
        System.out.flush();

        switch(choice){
            case "1":
                System.out.println("LIHAT RUMAH MAKAN");
                System.out.println("Daftar Rumah Makan");
                lookRestaurant();
                restaurantMenu();
                break;
            case "2":
                newRestaurant();
                adminMenu();
                break;
            case "3":
                deleteRestaurant();
                adminMenu();
                break;
            case "0":
                Main.Login();
                break;
            default:
                System.out.println("Anda salah memasukkan pilihan!");
                break;
        }
    }

    private static void newRestaurant(){

        foodPrice.add(new ArrayList<>());
        drinkPrice.add(new ArrayList<>());
        foodMenu.add(new ArrayList<>());
        drinkMenu.add(new ArrayList<>());

        System.out.println("TAMBAH RUMAH MAKAN");
        System.out.print("Masukkan nama rumah makan: ");
        String tempName = Utility.scanString();
        restaurant.add(tempName);

        System.out.print("Masukkan alamat rumah makan: ");
        String tempAddress = Utility.scanString();
        address.add(tempAddress);

        System.out.print("\033[H\033[2J");
        System.out.flush();

        int choice = 0;
        int foodie = 0;
        int drinky = 0;

        do{
            System.out.println("1. Tambah Makanan");
            System.out.println("2. Tambah Minuman");
            System.out.print("Masukkan pilihan Anda: ");
            int menu = Utility.scanInteger();
            System.out.print("\033[H\033[2J");
            System.out.flush();
            if(menu == 1){
                System.out.print("Masukkan nama makanan: ");
                String foods = Utility.scanString();
                foodMenu.get(restaurantAmmount).add(foodie, foods);
                System.out.print("Masukkan harga: ");
                int pricefoods = Utility.scanInteger();
                foodPrice.get(restaurantAmmount).add(foodie, pricefoods);
                foodie += 1;
            }else if(menu == 2){
                System.out.print("Masukkan nama minuman: ");
                String drinks = Utility.scanString();
                drinkMenu.get(restaurantAmmount).add(drinky, drinks);
                System.out.print("Masukkan harga minuman: ");
                int pricedrinks = Utility.scanInteger();
                drinkPrice.get(restaurantAmmount).add(drinky, pricedrinks);
                drinky += 1;
            }else{
                System.out.println("Anda memasukkan pilihan yang salah!");
            }
            System.out.print("\033[H\033[2J");
            System.out.flush();
            lookMenu(restaurantAmmount);

            System.out.println();
            System.out.println("1. Tambah Menu");
            System.out.println("0. Keluar");
            System.out.print("Masukkan pilihan: ");
            choice = Utility.scanInteger();
            System.out.print("\033[H\033[2J");
            System.out.flush();
        }while(choice!=0);

        restaurantAmmount += 1;
    }

    private static void restaurantMenu(){
        System.out.println("0. Keluar");
        System.out.print("Pilih rumah makan untuk melihat detail: ");
        int choice = Utility.scanInteger();
        System.out.print("\033[H\033[2J");
        System.out.flush();
        if (choice == 0){
            adminMenu();
        }else{
            int sendNumber = choice - 1;
            System.out.println(sendNumber);
            System.out.println("Nama rumah makan: " + restaurant.get(sendNumber));
            System.out.println("Alamat        : " + address.get(sendNumber));
            lookMenu(sendNumber);
            System.out.println("Masukkan 0 untuk keluar");
            int leave = Utility.scanInteger();
            System.out.print("\033[H\033[2J");
            System.out.flush();
            if (leave == 0){
                adminMenu();
            }
        }
    }

    private static void lookRestaurant(){
        for(int i = 0; i < restaurant.size(); i++){
            System.out.printf("%d. %s\n", i+1, restaurant.get(i));
        }
    }

    public static void lookMenu(int restauran){
        System.out.println("MENU");
        System.out.println();
        if(foodMenu.get(restauran).size() != 0 && drinkMenu.get(restauran).size() != 0){
            System.out.println("MAKANAN");
            System.out.printf("Menu:\t\t\tHarga:\n");
            for (int foodNumber = 0; foodNumber < foodMenu.get(restauran).size(); foodNumber++){
                System.out.printf("%d. %s\t\t\t%s\n", foodNumber+1, foodMenu.get(restauran).get(foodNumber), foodPrice.get(restauran).get(foodNumber));
            }
            System.out.println();
            System.out.println("MINUMAN");
            System.out.printf("Menu:\t\t\tHarga:\n");
            for (int drinkNumber = 0; drinkNumber < drinkMenu.get(restauran).size(); drinkNumber++){
                System.out.printf("%d. %s\t\t\t%s\n", drinkNumber+1, drinkMenu.get(restauran).get(drinkNumber), drinkPrice.get(restauran).get(drinkNumber));
            }
        }else if(foodMenu.get(restauran).size() != 0 && drinkMenu.get(restauran).size() == 0){
            System.out.println("MAKANAN");
            System.out.printf("Menu:\t\t\tHarga:\n");
            for(int foodNumber = 0; foodNumber < foodMenu.get(restauran).size(); foodNumber++){
                System.out.printf("%d. %s\t\t\t%s\n", foodNumber+1, foodMenu.get(restauran).get(foodNumber), foodPrice.get(restauran).get(foodNumber));
            }
        }else if(drinkMenu.get(restauran).size() != 0 && foodMenu.get(restauran).size() == 0){
            System.out.println("MINUMAN");
            System.out.printf("Menu:\t\t\tHarga:\n");
            for(int drinkNumber = 0; drinkNumber < drinkMenu.get(restauran).size(); drinkNumber++){
                System.out.printf("%d. %s\t\t\t%s\n", drinkNumber+1, drinkMenu.get(restauran).get(drinkNumber), drinkPrice.get(restauran).get(drinkNumber));
            }
        }
    }

    private static void deleteRestaurant(){
        System.out.println("HAPUS RUMAH MAKAN");
        System.out.println("Daftar Rumah Makan");
        lookRestaurant();
        System.out.print("Masukkan rumah makan yang ingin dihapus: ");
        int choice = Utility.scanInteger();
        restaurant.remove(choice-1);
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
