import java.util.ArrayList;

public class Users {
    public static ArrayList<String> address = Admin.address;
    public static ArrayList<Integer> distances = new ArrayList<>();
    public static ArrayList<String> restaurant = Admin.restaurant;
    public static ArrayList<ArrayList<String>> foodMenu = Admin.foodMenu;
    public static ArrayList<ArrayList<String>> drinkMenu = Admin.drinkMenu;
    public static ArrayList<ArrayList<Integer>> foodPrice = Admin.foodPrice;
    public static ArrayList<ArrayList<Integer>> drinkPrice = Admin.drinkPrice;
    public static ArrayList<ArrayList<Integer>> choiceFoodMenu = new ArrayList<>();
    public static ArrayList<ArrayList<Integer>> choiceDrinkMenu = new ArrayList<>();
    public static ArrayList<ArrayList<Integer>> ordersFood = new ArrayList<>();
    public static ArrayList<ArrayList<Integer>> ordersDrink = new ArrayList<>();
    public static ArrayList<Integer> restaurantChoice = new ArrayList<>();
    private static int orders = 0;
    private static int foodie = 0;
    private static int drinky = 0;

    public static void usersMenu(){
        System.out.println("MENU PELANGGAN");
        System.out.println("1. Buat Pesanan");
        System.out.println("2. Detail Pesanan");
        System.out.println("0. Kembali");
        System.out.print("Masukkan pilihan Anda: ");
        String choice = Utility.scanString();
        System.out.print("\033[H\033[2J");
        System.out.flush();
        switch(choice){
            case "1":
                Orders();
                usersMenu();
                break;
            case "2":
                lookOrders();
                usersMenu();
                break;
            case "0":
                Main.Login();
                break;
            default:
                System.out.println("Anda salah menginput pilihan!");
                usersMenu();
                break;
        }
    }

    private static void Orders(){
        int ordersMenu = 0;
        int totalPrice = 0;
        int foodList = 0;
        int drinkList = 0;

        choiceFoodMenu.add(new ArrayList<>());
        choiceDrinkMenu.add(new ArrayList<>());
        ordersFood.add(new ArrayList<>());
        ordersDrink.add(new ArrayList<>());

        System.out.println("RESTAURAN");
        for (int i = 0; i < restaurant.size(); i++){
            System.out.printf("%d. %s\n", i+1, restaurant.get(i));
        }

        System.out.println("0. Keluar");
        System.out.println("Pilih rumah makan: ");
        int choice = Utility.scanInteger();
        restaurantChoice.add(choice);

        System.out.print("Masukkan jarak rumah Anda (km): ");
        int range = Utility.scanInteger();
        System.out.print("\033[H\033[2J");
        System.out.flush();
        distances.add(range);
        int tax = range * 3000;
        if(choice == 0){
            usersMenu();
        }else{
            choice -= 1;
            do{
                System.out.println("MENU RUMAH MAKAN");
                System.out.println("Rumah makan: " + restaurant.get(choice));
                System.out.println("Alamat     : " + address.get(choice));
                Admin.lookMenu(choice);
                System.out.printf("\n\n");
                if (foodMenu.get(choice).size() != 0 && drinkMenu.get(choice).size() != 0){
                    System.out.println("1. Tambah pesanan makanan");
                    System.out.println("2. Tambah pesanan minuman");
                    System.out.println("0. Kembali");
                    System.out.print("Masukkan pilihan Anda: ");
                } else if (foodMenu.get(choice).size() != 0 && drinkMenu.get(choice).size() == 0){
                    System.out.println("1. Tambah pesanan makanan");
                    System.out.println("0. Kembali");
                    System.out.print("Masukkan pilihan Anda: ");
                } else if (foodMenu.get(choice).size() == 0 && drinkMenu.get(choice).size() != 0){
                    System.out.println("1. Tambah pesanan minuman");
                    System.out.println("0. Kembali");
                    System.out.print("Masukkan pilihan Anda: ");
                }
                ordersMenu = Utility.scanInteger();
                System.out.print("\033[H\033[2J");
                System.out.flush();

                if(ordersMenu == 0){
                    int allPrice = totalPrice + tax;
                    int history = orders;
                    orders += 1;
                    int foods = foodie;
                    int drinks = drinky;
                    checkDetailsFirst(allPrice, tax, history, range, foods, drinks);
                    if(choiceFoodMenu.size() != 0 && choiceDrinkMenu.size() != 0){
                        foodie += 1;
                        drinky += 1;
                    }else if (choiceFoodMenu.size() == 0 && choiceDrinkMenu.size() != 0){
                        drinky += 1;
                    }else if (choiceFoodMenu.size() != 0 && choiceDrinkMenu.size() == 0){
                        foodie += 1;
                    }
                    break;
                }else if (ordersMenu == 1){
                    Admin.lookMenu(choice);
                    System.out.print("Masukkan pilihan makanan Anda: ");
                    int foodChoices = Utility.scanInteger();
                    foodChoices -= 1;
                    System.out.print("\033[H\033[2J");
                    System.out.flush();
                    if(foodList == 0){
                        choiceFoodMenu.get(foodie).add(0, orders);
                    }
                    choiceFoodMenu.get(foodie).add(foodList, foodChoices);
                    foodList += 1;
                    System.out.println("Nama : " + foodMenu.get(choice).get(foodChoices));
                    System.out.println("Harga: " + foodPrice.get(choice).get(foodChoices));
                    System.out.println("Masukkan banyak pesanan: ");
                    int ammounts = Utility.scanInteger();
                    int listAmmounts = foodList - 1;
                    ordersFood.get(foodie).add(listAmmounts, ammounts);
                    int subtotal = ammounts * foodPrice.get(choice).get(foodChoices);
                    System.out.println("Harga: " + subtotal);
                    System.out.print("\033[H\033[2J");
                    System.out.flush();
                    totalPrice += subtotal;
                }else if (ordersMenu == 2){
                    Admin.lookMenu(choice);
                    System.out.print("Masukkan pilihan minuman Anda: ");
                    int drinkChoices = Utility.scanInteger();
                    drinkChoices -= 1;
                    System.out.print("\033[H\033[2J");
                    System.out.flush();
                    if(drinkList == 0){
                        choiceDrinkMenu.get(drinky).add(0, orders);
                    }
                    choiceDrinkMenu.get(drinky).add(drinkList, drinkChoices);
                    drinkList += 1;
                    System.out.println("Nama : " + drinkMenu.get(choice).get(drinkChoices));
                    System.out.println("Harga: " + drinkPrice.get(choice).get(drinkChoices));
                    System.out.println("Masukkan banyak pesanan: ");
                    int ammounts = Utility.scanInteger();
                    int listAmmounts = drinkList - 1;
                    ordersDrink.get(drinky).add(listAmmounts, ammounts);
                    int subtotal = ammounts * drinkPrice.get(choice).get(drinkChoices);
                    System.out.println("Harga: " + subtotal);
                    System.out.print("\033[H\033[2J");
                    System.out.flush();
                    totalPrice += subtotal;
                }
                else{
                    System.out.println("Anda menginputkan data yang salah!");
                }
            }while(true);
        }
    }

    private static void detailsOfOrders(int tax, int history, int range, int foods, int drinks){
        int foodOrders = 0;
        int drinkOrders = 0;
        if(choiceFoodMenu.get(foods).size() != 0 && choiceDrinkMenu.get(drinks).size() != 0){
            foodOrders = choiceFoodMenu.get(foods).get(0);
            drinkOrders = choiceDrinkMenu.get(drinks).get(0);
        }else if(choiceFoodMenu.get(foods).size() != 0 && choiceDrinkMenu.get(drinks).size() == 0){
            drinkOrders = choiceDrinkMenu.get(drinks).get(0);
        }else if(choiceFoodMenu.get(foods).size() == 0 && choiceDrinkMenu.get(drinks).size() != 0){
            foodOrders = choiceFoodMenu.get(foods).get(0);
        }

        if (choiceFoodMenu.get(foods).size() != 0 && choiceDrinkMenu.get(drinks).size() != 0 && history == foodOrders && history == drinkOrders){
            System.out.println("PESANAN");
            System.out.println();
            System.out.println("MAKANAN");
            System.out.printf("Menu\t\t\tBanyak Pesanan\t\t\tSubtotal\n");
            for(int i = 0; i < choiceFoodMenu.get(foods).size() - 1; i++){
                int dataNumber = i+1;
                int restaurantName = restaurantChoice.get(history) - 1;
                int menu = choiceFoodMenu.get(foods).get(dataNumber);
                int subtotal = ordersFood.get(foods).get(i) * foodPrice.get(restaurantName).get(menu);
                System.out.printf("%d. %s\t\t\t%d\t\t\t%d\n", dataNumber, foodMenu.get(restaurantName).get(menu), ordersFood.get(foods).get(i), subtotal);
            }
            System.out.println();
            System.out.println("MINUMAN");
            System.out.printf("Menu\t\t\tBanyak Pesanan\t\t\tSubtotal\n");
            for(int j = 0; j < choiceDrinkMenu.get(drinks).size() - 1; j++){
                int dataNumber = j+1;
                int restaurantName = restaurantChoice.get(history) - 1;
                int menu = choiceDrinkMenu.get(foods).get(dataNumber);
                int subtotal = ordersDrink.get(foods).get(j) * drinkPrice.get(restaurantName).get(menu);
                System.out.printf("%d. %s\t\t\t%d\t\t\t%d\n", dataNumber, drinkMenu.get(restaurantName).get(menu), ordersDrink.get(drinks).get(j), subtotal);
            }
            System.out.println("JARAK : " + range);
            System.out.println("ONGKIR: " + tax);
        } else if (choiceFoodMenu.get(foods).size() != 0 && choiceDrinkMenu.get(drinks).size() != 0 && history != foodOrders && history == drinkOrders){
            System.out.println("PESANAN");
            System.out.println();
            System.out.println("MINUMAN");
            System.out.printf("Menu\t\t\tBanyak Pesanan\t\t\tSubtotal\n");
            for(int j = 0; j < choiceDrinkMenu.get(drinks).size() - 1; j++){
                int dataNumber = j+1;
                int restaurantName = restaurantChoice.get(history) - 1;
                int menu = choiceDrinkMenu.get(foods).get(dataNumber);
                int subtotal = ordersDrink.get(foods).get(j) * drinkPrice.get(restaurantName).get(menu);
                System.out.printf("%d. %s\t\t\t%d\t\t\t%d\n", dataNumber, drinkMenu.get(restaurantName).get(menu), ordersDrink.get(drinks).get(j), subtotal);
            }
            System.out.println("JARAK : " + range);
            System.out.println("ONGKIR: " + tax);
        } else if (choiceFoodMenu.get(foods).size() != 0 && choiceDrinkMenu.get(drinks).size() != 0 && history == foodOrders && history != drinkOrders){
            System.out.println("PESANAN");
            System.out.println();
            System.out.println("MAKANAN");
            System.out.printf("Menu\t\t\tBanyak Pesanan\t\t\tSubtotal\n");
            for(int i = 0; i < choiceFoodMenu.get(foods).size() - 1; i++){
                int dataNumber = i+1;
                int restaurantName = restaurantChoice.get(history) - 1;
                int menu = choiceFoodMenu.get(foods).get(dataNumber);
                int subtotal = ordersFood.get(foods).get(i) * foodPrice.get(restaurantName).get(menu);
                System.out.printf("%d. %s\t\t\t%d\t\t\t%d\n", dataNumber, foodMenu.get(restaurantName).get(menu), ordersFood.get(foods).get(i), subtotal);
            }
            System.out.println("JARAK : " + range);
            System.out.println("ONGKIR: " + tax);
        } else if (choiceFoodMenu.get(foods).size() == 0 && choiceDrinkMenu.get(drinks).size() != 0 && history == drinkOrders){
            System.out.println("PESANAN");
            System.out.println();
            System.out.println("MINUMAN");
            System.out.printf("Menu\t\t\tBanyak Pesanan\t\t\tSubtotal\n");
            for(int j = 0; j < choiceDrinkMenu.get(drinks).size() - 1; j++){
                int dataNumber = j+1;
                int restaurantName = restaurantChoice.get(history) - 1;
                int menu = choiceDrinkMenu.get(foods).get(dataNumber);
                int subtotal = ordersDrink.get(foods).get(j) * drinkPrice.get(restaurantName).get(menu);
                System.out.printf("%d. %s\t\t\t%d\t\t\t%d\n", dataNumber, drinkMenu.get(restaurantName).get(menu), ordersDrink.get(drinks).get(j), subtotal);
            }
            System.out.println("JARAK : " + range);
            System.out.println("ONGKIR: " + tax);
        } else if (choiceFoodMenu.get(foods).size() != 0 && choiceDrinkMenu.get(drinks).size() == 0 && history == foodOrders){
            System.out.println("PESANAN");
            System.out.println();
            System.out.println("MAKANAN");
            System.out.printf("Menu\t\t\tBanyak Pesanan\t\t\tSubtotal\n");
            for(int i = 0; i < choiceFoodMenu.get(foods).size() - 1; i++){
                int dataNumber = i+1;
                int restaurantName = restaurantChoice.get(history) - 1;
                int menu = choiceFoodMenu.get(foods).get(dataNumber);
                int subtotal = ordersFood.get(foods).get(i) * foodPrice.get(restaurantName).get(menu);
                System.out.printf("%d. %s\t\t\t%d\t\t\t%d\n", dataNumber, foodMenu.get(restaurantName).get(menu), ordersFood.get(foods).get(i), subtotal);
            }
            System.out.println("JARAK : " + range);
            System.out.println("ONGKIR: " + tax);
        }
    }

    private static void checkDetailsFirst(int total, int tax, int history, int range, int foods, int drinks){
        System.out.println(history);
        detailsOfOrders(tax, history, range, foods, drinks);
        System.out.println("TOTAL: " + total);
        System.out.println();

        System.out.println("1. Tambah Pesanan");
        System.out.println("2. Pembayaran");
        System.out.println("0. Batal Pesan");
        System.out.print("Masukkan pilihan Anda: ");
        int choice = Utility.scanInteger();
        System.out.print("\033[H\033[2J");
        System.out.flush();
        switch(choice){
            case 1:
                int restaurantID = restaurantChoice.get(history) - 1;
                addOrders(restaurantID, history, total, tax, range, foods, drinks);
                break;
            case 2:
                paid(total, tax, history, range, foods, drinks);
                break;
            case 0:
                usersMenu();
                break;
            default:
                checkDetailsFirst(total, tax, history, range, foods, drinks);
                break;
        }
    }

    private static void paid(int total, int tax, int history, int range, int foods, int drinks){
        detailsOfOrders(tax, history, range, foods, drinks);
        System.out.printf("TOTAL: %d\n", total);
        System.out.println();
        System.out.println("Metode pembayaran");
        System.out.println("1. Tunai");
        System.out.println("2. Non-Tunai");
        System.out.print("Masukkan pilihan Anda: ");
        int choice = Utility.scanInteger();
        System.out.print("\033[H\033[2J");
        System.out.flush();
        switch(choice){
            case 1:
                System.out.println("PESANAN KAMU SEGERA SAMPAI");
                System.out.println("TERIMA KASIH SUDAH MENGGUNAKAN LAYANAN KAMI");
                break;
            case 2:
                System.out.println("Silakan masukkan nomor e-Wallet kamu");
                System.out.print(">> ");
                String eNumber = Utility.scanString();
                System.out.println("PENGGUNA DENGAN NOMOR: " + eNumber);
                System.out.println("PESANAN KAMU SEGERA SAMPAI");
                System.out.println("TERIMA KASIH SUDAH MENGGUNAKAN LAYANAN KAMI");
                break;
            default:
                System.out.println("Inputan Anda salah!");
                paid(total, tax, history, range, foods, drinks);
                break;
        }
        usersMenu();
    }

    private static void addOrders(int choice, int history, int totalPrice, int tax, int range, int foods, int drinks){
        int orderMenu = 0;
        int foodOrders = choiceFoodMenu.get(foods).size();
        int drinkOrders = choiceDrinkMenu.get(drinks).size();
        do{
            System.out.println("MENU RUMAH MAKAN");
            System.out.println("Nama Rumah Makan: " + restaurant.get(choice));
            System.out.println("Alamat          : " + address.get(choice));
            Admin.lookMenu(choice);
            if(foodMenu.get(choice).size() != 0 && drinkMenu.get(choice).size() != 0){
                System.out.println("1. Tambah pesanan makanan");
                System.out.println("2. Tambah pesanan minuman");
                System.out.println("0. Kembali");
                System.out.print("Masukkan pilihan Anda: ");
            }else if(foodMenu.get(choice).size() != 0 && drinkMenu.get(choice).size() == 0){
                System.out.println("1. Tambah pesanan makanan");
                System.out.println("0. Kembali");
                System.out.print("Masukkan pilihan Anda: ");
            }else if(foodMenu.get(choice).size() == 0 && drinkMenu.get(choice).size() != 0){
                System.out.println("1. Tambah pesanan minuman");
                System.out.println("0. Kembali");
                System.out.print("Masukkan pilihan Anda: ");
            }
            orderMenu = Utility.scanInteger();
            System.out.print("\033[H\033[2J");
            System.out.flush();

            if(orderMenu == 0){
                checkDetailsFirst(totalPrice, tax, history, range, foods, drinks);
                break;
            }else if(orderMenu == 1){
                Admin.lookMenu(choice);
                System.out.println("Masukkan pilihan Anda: ");
                int foodChoices = Utility.scanInteger();
                foodChoices -= 1;

                if(foodOrders == 0){
                    choiceFoodMenu.get(foods).add(0, history);
                }

                choiceFoodMenu.get(foods).add(foodOrders, foodChoices);
                System.out.println("Nama : " + foodMenu.get(choice).get(foodChoices));
                System.out.println("Harga: " + foodPrice.get(choice).get(foodChoices));
                System.out.println("Masukkan banyak pesanan: ");
                int ammounts = Utility.scanInteger();
                int listAmmounts = foodOrders - 1;
                ordersFood.get(foods).add(listAmmounts, ammounts);
                int subtotal = ammounts * foodPrice.get(choice).get(foodChoices);
                System.out.println("Harga: " + subtotal);
                System.out.print("\033[H\033[2J");
                System.out.flush();
                foodOrders += 1;
                totalPrice += subtotal;
            }else if(orderMenu == 2){
                Admin.lookMenu(choice);
                System.out.println("Masukkan pilihan Anda: ");
                int drinkChoices = Utility.scanInteger();
                drinkChoices -= 1;

                if(drinkOrders == 0){
                    choiceDrinkMenu.get(drinks).add(0, history);
                }

                choiceDrinkMenu.get(drinks).add(drinkOrders, drinkChoices);
                System.out.println("Nama : " + drinkMenu.get(choice).get(drinkChoices));
                System.out.println("Harga: " + drinkPrice.get(choice).get(drinkChoices));
                System.out.println("Masukkan banyak pesanan: ");
                int ammounts = Utility.scanInteger();
                int listAmmounts = drinkOrders - 1;
                ordersDrink.get(drinks).add(listAmmounts, ammounts);
                int subtotal = ammounts * drinkPrice.get(choice).get(drinkChoices);
                System.out.println("Harga: " + subtotal);
                System.out.print("\033[H\033[2J");
                System.out.flush();
                drinkOrders += 1;
                totalPrice += subtotal;
            } else{
                System.out.println("Anda salah menginputkan data!");
            }
        }while(true);
    }

    private static void lookOrders(){
        int doOrders = restaurantChoice.size();
        for(int i = 0; i < doOrders; i++){
            int foodOrders = -1;
            int drinkOrders = -1;
            int restaurantName = restaurantChoice.get(i) - 1;
            System.out.println("PESANAN KE - " + (i+1));
            System.out.println("Rumah Makan: " + restaurant.get(restaurantName));
            System.out.println("Alamat     : " + address.get(restaurantName));

            if(choiceFoodMenu.size() != 0 && choiceDrinkMenu.size() != 0){
                for(int j = 0; j < choiceFoodMenu.size(); j++){
                    if (choiceFoodMenu.get(j).size() != 0 && choiceFoodMenu.get(j).get(0) == i){
                        foodOrders = choiceFoodMenu.get(j).get(0);
                    }
                }
                for(int k = 0; k < choiceDrinkMenu.size(); k++){
                    if (choiceDrinkMenu.get(k).size() != 0 && choiceDrinkMenu.get(k).get(0) == i){
                        drinkOrders = choiceDrinkMenu.get(k).get(0);
                    }
                }
            } else if (choiceFoodMenu.size() != 0 && choiceDrinkMenu.size() == 0){
                for(int j = 0; j < choiceFoodMenu.size(); j++){
                    if (choiceFoodMenu.get(j).size() != 0 && choiceFoodMenu.get(j).get(0) == i){
                        foodOrders = choiceFoodMenu.get(j).get(0);
                    }
                }
            } else if (choiceFoodMenu.size() == 0 && choiceDrinkMenu.size() != 0){
                for(int k = 0; k < choiceDrinkMenu.size(); k++){
                    if (choiceDrinkMenu.get(k).size() != 0 && choiceDrinkMenu.get(k).get(0) == i){
                        drinkOrders = choiceDrinkMenu.get(k).get(0);
                    }
                }
            }
            
            if (choiceFoodMenu.size() != 0 && choiceDrinkMenu.size() != 0 && i == foodOrders && i == drinkOrders){
                System.out.println("PESANAN");
                System.out.println();
                System.out.println("MAKANAN");
                System.out.printf("Menu\t\t\tBanyak Pesanan\t\t\tSubtotal\n");
                int total = 0;
                for(int l = 0; l < choiceFoodMenu.get(i).size() - 1; l++){
                    int dataNumber = i+1;
                    int restaurantNames = restaurantChoice.get(i) - 1;
                    int menu = choiceFoodMenu.get(i).get(dataNumber);
                    int subtotal = ordersFood.get(i).get(l) * foodPrice.get(restaurantNames).get(menu);
                    total += subtotal;
                    System.out.printf("%d. %s\t\t\t%d\t\t\t%d\n", dataNumber, foodMenu.get(restaurantNames).get(menu), ordersFood.get(i).get(l), subtotal);
                }
                System.out.println();
                System.out.println("MINUMAN");
                System.out.printf("Menu\t\t\tBanyak Pesanan\t\t\tSubtotal\n");
                for(int l = 0; l < choiceDrinkMenu.get(i).size() - 1; l++){
                    int dataNumber = i+1;
                    int restaurantNames = restaurantChoice.get(i) - 1;
                    int menu = choiceDrinkMenu.get(i).get(dataNumber);
                    int subtotal = ordersDrink.get(i).get(l) * drinkPrice.get(restaurantNames).get(menu);
                    total += subtotal;
                    System.out.printf("%d. %s\t\t\t%d\t\t\t%d\n", dataNumber, drinkMenu.get(restaurantNames).get(menu), ordersDrink.get(i).get(l), subtotal);
                }
                System.out.println("JARAK : " + distances.get(i));
                System.out.println("TOTAL : " + total);
            } else if (choiceFoodMenu.size() != 0 && choiceDrinkMenu.size() != 0 && i != foodOrders && i == drinkOrders){
                System.out.println("PESANAN");
                System.out.println();
                System.out.println("MINUMAN");
                System.out.printf("Menu\t\t\tBanyak Pesanan\t\t\tSubtotal\n");
                int total = 0;
                for(int l = 0; l < choiceDrinkMenu.get(i).size() - 1; l++){
                    int dataNumber = i+1;
                    int restaurantNames = restaurantChoice.get(i) - 1;
                    int menu = choiceDrinkMenu.get(i).get(dataNumber);
                    int subtotal = ordersDrink.get(i).get(l) * drinkPrice.get(restaurantNames).get(menu);
                    total += subtotal;
                    System.out.printf("%d. %s\t\t\t%d\t\t\t%d\n", dataNumber, drinkMenu.get(restaurantNames).get(menu), ordersDrink.get(i).get(l), subtotal);
                }
                System.out.println("JARAK : " + distances.get(i));
                System.out.println("TOTAL : " + total);
            } else if (choiceFoodMenu.size() != 0 && choiceDrinkMenu.size() != 0 && i == foodOrders && i != drinkOrders){
                System.out.println("PESANAN");
                System.out.println();
                System.out.println("MAKANAN");
                System.out.printf("Menu\t\t\tBanyak Pesanan\t\t\tSubtotal\n");
                int total = 0;
                for(int l = 0; l < choiceFoodMenu.get(i).size() - 1; l++){
                    int dataNumber = i+1;
                    int restaurantNames = restaurantChoice.get(i) - 1;
                    int menu = choiceFoodMenu.get(i).get(dataNumber);
                    int subtotal = ordersFood.get(i).get(l) * foodPrice.get(restaurantNames).get(menu);
                    total += subtotal;
                    System.out.printf("%d. %s\t\t\t%d\t\t\t%d\n", dataNumber, foodMenu.get(restaurantNames).get(menu), ordersFood.get(i).get(l), subtotal);
                }
                System.out.println("JARAK : " + distances.get(i));
                System.out.println("TOTAL : " + total);
            } else if (choiceFoodMenu.size() != 0 && choiceDrinkMenu.size() != 0 && i == drinkOrders){
                System.out.println("PESANAN");
                System.out.println();
                System.out.println("MINUMAN");
                System.out.printf("Menu\t\t\tBanyak Pesanan\t\t\tSubtotal\n");
                int total = 0;
                for(int l = 0; l < choiceDrinkMenu.get(i).size() - 1; l++){
                    int dataNumber = i+1;
                    int restaurantNames = restaurantChoice.get(i) - 1;
                    int menu = choiceDrinkMenu.get(i).get(dataNumber);
                    int subtotal = ordersDrink.get(i).get(l) * drinkPrice.get(restaurantNames).get(menu);
                    total += subtotal;
                    System.out.printf("%d. %s\t\t\t%d\t\t\t%d\n", dataNumber, drinkMenu.get(restaurantNames).get(menu), ordersDrink.get(i).get(l), subtotal);
                }
                System.out.println("JARAK : " + distances.get(i));
                System.out.println("TOTAL : " + total);
            } else if (choiceFoodMenu.size() != 0 && choiceDrinkMenu.size() != 0 && i == foodOrders){
                System.out.println("PESANAN");
                System.out.println();
                System.out.println("MAKANAN");
                System.out.printf("Menu\t\t\tBanyak Pesanan\t\t\tSubtotal\n");
                int total = 0;
                for(int l = 0; l < choiceFoodMenu.get(i).size() - 1; l++){
                    int dataNumber = i+1;
                    int restaurantNames = restaurantChoice.get(i) - 1;
                    int menu = choiceFoodMenu.get(i).get(dataNumber);
                    int subtotal = ordersFood.get(i).get(l) * foodPrice.get(restaurantNames).get(menu);
                    total += subtotal;
                    System.out.printf("%d. %s\t\t\t%d\t\t\t%d\n", dataNumber, foodMenu.get(restaurantNames).get(menu), ordersFood.get(i).get(l), subtotal);
                }
                System.out.println("JARAK : " + distances.get(i));
                System.out.println("TOTAL : " + total);
            }
            System.out.println();
        }

        System.out.println("Tekan 0 untuk kembali!");
        int back = Utility.scanInteger();
        System.out.print("\033[H\033[2J");
        System.out.flush();
        if(back == 0){
            usersMenu();
        }
    }
}
