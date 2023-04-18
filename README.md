# PEMBELIAN MAKANAN SEDERHANA

Nama: I Kadek Indra Agusta Pratama (Alias Dudek)
NIM: 2205551056

Program pembelian makanan sederhana ini adalah program bahasa Java dengan orientasi pemrograman berbasis objek sebagai tugas kuliah.

## MENU ADMIN

Jika kita adalah seorang administrator, kita bisa menggunakan akun admin dengan username "admin01" dan kata sandi "SayaGanteng01".

```terminal
Log In
Username: admin01
Password: SayaGanteng01
```

Lalu kita akan dialihkan langsung ke menu yang hanya dimiliki oleh administrator.

```terminal
MENU ADMIN
1. Lihat Rumah Makan
2. Tambah Rumah Makan
3. Hapus Rumah Makan
0. Kembali
Masukkan pilihan:
```

Halaman Log In ini sederhana, cukup menggunakan beberapa *methods* saja, seperti.

```java
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
```

Untuk hal-hal yang berbasis objek menggunakan Utility, hanya untuk user input.

```java
public static String scanString(){
    Scanner scanner = new Scanner(System.in);
    String name = scanner.nextLine();
    return name;
}
public static int scanInteger(){
    Scanner scanner = new Scanner(System.in);
    int number = scanner.nextInt();
    return number;
}
```

Di sini seharusnya kita menggunakan method ....

```java
scanner.close();
```

Akan tetapi ini JDK versi 11, kalau versi terbaru mungkin kita akan menggunakannya tapi berpotensi terjadinya bug seperti ....

```terminal
Log In
Username: admin01
Password: Exception in thread "main" java.util.NoSuchElementException: No line found
        at java.base/java.util.Scanner.nextLine(Scanner.java:1656)
        at Utility.scanString(Utility.java:6)
        at Main.Login(Main.java:10)
        at Main.main(Main.java:3)
```

Jadi, oke, lanjut ....

## MENU PELANGGAN

Untuk Log In, saya tidak banyak memberikan pilihan, cukup langsung Log In dengan sembarang username saja (kecuali admin01) akan langsung menjadi user atau customer atau pelanggan karena ini tidak menggunakan berkas berekstensi .txt sebagai basis datanya.

```terminal
Log In
Username: adit
Password: adit
```

Misalnya begitu, dia akan langsung ke menu pelanggan.

```terminal
User adit
MENU PELANGGAN
1. Buat Pesanan
2. Detail Pesanan
0. Kembali
Masukkan pilihan Anda: 
```

Jika admin belum menambahkan data rumah makan, ya ..., kosong. Jika admin sudah menambahkan, ya ..., akan muncul seperti ini.

```terminal
RESTAURAN
1. Salatiga Makan-Makan
0. Keluar
Pilih rumah makan:
1
Masukkan jarak rumah Anda (km): 20  


MENU RUMAH MAKAN
Rumah makan: Salatiga Makan-Makan
Alamat     : Salatiga
MENU

MAKANAN
Menu:                   Harga:
1. Bebek Salatiga                       20000
2. Ayam Telorasin                       30000

MINUMAN
Menu:                   Harga:
1. Susu Putih Hangat                    3000


1. Tambah pesanan makanan
2. Tambah pesanan minuman
0. Kembali
Masukkan pilihan Anda:
```

Intinya ini sederhana.

## PENUTUP

Terima kasih dan mohon maaf bila ada kesalahan. Jika ada bug, harap hubungi email jajajablablabla123@gmail.com, terima kasih sekali lagi.
