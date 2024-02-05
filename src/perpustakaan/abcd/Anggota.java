package perpustakaan.abcd;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Anggota {
    ArrayList<Buku> buku   = new ArrayList<Buku>();
    ArrayList<Buku> bukuPinjam = new ArrayList<Buku>();

    public int id;
    public  String nama;
    private String username;
    private String password;
    Role role = Role.anggota;
    Buku bukubfr;
    Feedback feedback;
    History history;
    KartuAnggota kartuAnggota;

    public Anggota (Buku bukubfr) throws IOException{
        this.bukubfr = bukubfr;
        generate(bukubfr);
    }
    public Anggota(int id, String nama){
        this.id = id;
        this.nama = nama;
        this.role = Role.anggota;
    }
    //setters
    public void setKartuAnggota(KartuAnggota kartuAnggota) {
        this.kartuAnggota = kartuAnggota;
    }
    public void setHistory(History history) {
        this.history = history;
    }

    public void setFeedback(Feedback feedback) {
        this.feedback = feedback;
    }

    public void setNama() {
        System.out.print("Masukkan Nama : ");
        Scanner terminalInput = new Scanner(System.in);
        this.nama = terminalInput.nextLine();
    }

    public void setId() {
        System.out.print("Masukkan Id : ");
        Scanner terminalInput = new Scanner(System.in);
        this.id = terminalInput.nextInt();
    }
    public void setKartu(KartuAnggota kartuAnggota){
        this.kartuAnggota = kartuAnggota;
    }

    public void setRole() {
        this.role = Role.anggota;
    }

    public void setUsername() {
        System.out.print("Masukkan Username Akun: ");
        Scanner terminalInput = new Scanner(System.in);
        this.username = terminalInput.nextLine();
    }

    public void setPassword() {
        System.out.print("Masukkan Password Akun: ");
        Scanner terminalInput = new Scanner(System.in);
        this.password = terminalInput.nextLine();
    }
    //getter
    public String getUsername() {
        return username;
    }

    public String getNama() {
        return nama;
    }

    public int getId() {
        return id;
    }

    public Role getRole() {
        return Role.anggota;
    }

    public int getBukubfr() {
        return bukubfr.getNoBukuBfr();
    }

     public void login(Pengurus admin){
         System.out.println("==== USER LOGIN ====");
         String uname;
         String pword;
         System.out.print("Masukkan Username : ");
         Scanner terminalInput = new Scanner(System.in);
         uname = terminalInput.nextLine();
         System.out.print("Masukkan Password : ");
         pword = terminalInput.nextLine();

         if (this.username.equals(uname) && this.password.equals(pword)){
             this.mainMenuMember(admin);
         }else {
             clearScreen();
             System.out.println("Autentikasi Gagal, Silahkan Periksa Kembali Username dan Password Anda");
             this.login(admin);
         }
    }
    public void register(){
        System.out.println("==== FORM REGISTRASI ====");
        setId();
        setNama();
        setUsername();
        setPassword();
        this.kartuAnggota.id = getId();
        this.kartuAnggota.nama =getNama();
        this.kartuAnggota.role = getRole();
        System.out.println("==== EOF  REGISTRASI ====");

    }

    public void mainMenuMember(Pengurus admin){
        System.out.println("===== Login Berhasil =====");
        Scanner terminalInput = new Scanner(System.in);
        boolean isLanjutkan = true;
        do {
            clearScreen();
            System.out.println("\n==========================");
            try {
                admin.pengumuman.display();
            }catch (Exception e){
                System.out.println("Belum ada Pengumuman dari Petugas Perpustakaan");
            }
            System.out.println("==========================");
            System.out.println("====== Member Access =====\n");
            System.out.println("1. Pinjam Buku");
            System.out.println("2. Sumbang Buku");
            System.out.println("3. Cari Buku");
            System.out.println("4. Berikan Feedback");
            System.out.println("5. Tampilkan Buku yang Dipinjam");
            System.out.println("6. History Peminjaman Buku");
            System.out.println("7. Cetak Kartu Anggota");


            System.out.print("\n\nPilihan anda: ");
            int pilihanUser = terminalInput.nextInt();
            switch (pilihanUser){
                case 1:
                    System.out.println("\n=================");
                    System.out.println("  PEMINJAMAN BUKU  ");
                    System.out.println("=================");
                    pinjamBuku();
                    break;
                case 2:
                    clearScreen();
                    System.out.println("\n=================");
                    System.out.println(" PENYUMBANGAN BUKU ");
                    System.out.println("=================");
                    sumbangBuku(bukubfr);
                    break;
                case 3:
                    clearScreen();
                    System.out.println("\n=================");
                    System.out.println("  PENCARIAN  BUKU  ");
                    System.out.println("=================");
                    cariBuku();
                    break;
                case 4:
                    clearScreen();
                    System.out.println("\n=================");
                    System.out.println("  INPUT  FEEDBACK  ");
                    System.out.println("=================");
                    try {
                    Feedback feedback = new Feedback();
                    setFeedback(feedback);
                    System.out.println("Silahkan Masukkan Feedback Anda : ");
                    this.feedback.setFeedback();
                    System.out.println("");
                    System.out.println("Terima Kasih Atas Feedback Anda :)");
                    }catch (Exception e) {
                        System.err.println("Gagal Membuat Feedback!");
                    }
                    break;
                case 5:
                    clearScreen();
                    System.out.println("\n=================");
                    System.out.println("  BUKU DIPINJAM  ");
                    System.out.println("=================");
                    menuDipinjam();
                    break;
                case 6:
                    System.out.println("\n===================");
                    System.out.println("RIWAYAT PINJAM BUKU");
                    System.out.println("===================");
                    history.tampilkanRiwayat();
                    clearScreen();
                    break;
                case 7:
                    System.out.println("\n===================");
                    System.out.println("CETAK KARTU ANGGOTA");
                    System.out.println("===================");
                    System.out.println(kartuAnggota.cetakKartuAnggota());
                    break;
                default:
                    clearScreen();
                    System.err.println("\nInput anda tidak ditemukan\nSilahkan pilih [1-4]");
            }

            isLanjutkan = getYesorNo("Apakah Anda ingin Melanjutkan Menu User");
        }while(isLanjutkan == true);
    }
    private static void clearScreen(){
        try {
            new ProcessBuilder("cmd","/c","cls").inheritIO().start().waitFor();
        } catch (Exception ex){
            System.err.println("tidak bisa clear screen");
        }
    }
    private static boolean getYesorNo(String message){
        Scanner terminalInput = new Scanner(System.in);
        System.out.print("\n"+message+" (y/n)? ");
        String pilihanUser = terminalInput.next();

        while(!pilihanUser.equalsIgnoreCase("y") && !pilihanUser.equalsIgnoreCase("n")) {
            System.err.println("Pilihan anda bukan y atau n");
            System.out.print("\n"+message+" (y/n)? ");
            pilihanUser = terminalInput.next();
        }
        return pilihanUser.equalsIgnoreCase("y");
    }
    public void sumbangBuku(Buku bukubfr){
        clearScreen();
        Scanner sc = new Scanner(System.in);
        boolean isLanjutkan = true;
        do {
            Buku buku1 =  new Buku();
            System.out.print("Masukkan Kategori Buku : ");
            String kategori = sc.nextLine();
            System.out.print("Masukkan Judul Buku : ");
            String judul = sc.nextLine();
            System.out.print("Masukkan Penulis Buku : ");
            String penulis = sc.nextLine();
            System.out.print("Masukkan Penerbit Buku : ");
            String penerbit = sc.nextLine();

            buku1.setNomorBuku(++bukubfr.noBukuBfr);
            buku1.setKategori(kategori);
            buku1.setJudul(judul);
            buku1.setPenulis(penulis);
            buku1.setPenerbit(penerbit);

            buku.add(buku1);
            System.out.println("===========================");
            System.out.println("Berhasil Menyumbang Buku " + buku1.getJudul());
            System.out.println("Terimakasih " + getNama());
            System.out.println("===========================");

            isLanjutkan = getYesorNo("Apakah Anda ingin Menyumbang Buku Lagi");
        }while(isLanjutkan == true);
        }

    public void menuBuku(){
            System.out.println("==== MENU BUKU ====");
            for (Buku buku : buku) {
                System.out.println("Nomor Buku : "+ buku.nomorBuku);
                System.out.println("Kategori : " + buku.kategori);
                System.out.println("Judul : " + buku.judul);
                System.out.println("Penulis : " + buku.penulis);
                System.out.println("Penerbit : " + buku.penerbit);
                System.out.println("===================");
            }
        }
        public void generate(Buku bukubfr) throws IOException {
            FileReader fileInput;
            BufferedReader bufferInput;

            try {
                fileInput = new FileReader("input.txt");
                bufferInput = new BufferedReader(fileInput);
            } catch (Exception e){
                System.err.println("Menu Buku Tidak ditemukan");
                return;
            }
            String data = bufferInput.readLine();
            bukubfr.noBukuBfr = 0 ;
            while(data != null){
                StringTokenizer token = new StringTokenizer(data,"|");
                Buku buku1 =  new Buku();
                ++bukubfr.noBukuBfr;
                buku1.setNomorBuku(getBukubfr());
                buku1.setPenulis(token.nextToken());
                buku1.setPenerbit(token.nextToken());
                buku1.setJudul(token.nextToken());
                buku1.setKategori(token.nextToken());
                buku.add(buku1);
                data = bufferInput.readLine();
            }
        }

    public void pinjamBuku(){
        clearScreen();
        boolean isLanjutkan = true;
        Scanner input = new Scanner(System.in);
        try {
            do {
                menuBuku();
                System.out.print("Masukkan Nomor Buku Yang Akan Dipinjam : ");
                int pilih = input.nextInt();
                for (Buku buku : buku) {
                    if(buku.nomorBuku == pilih){
                        buku.setTanggalPinjam();
                        buku.setTanggalKembali();
                        bukuPinjam.add(buku);
                        history.bukuPinjam.add(buku);
                        System.out.println("Buku Dengan Judul "+buku.judul+ " Berhasil dipinjam");
                        break;
                    }else {
                        continue;
                    }
                }
                isLanjutkan = getYesorNo("Apakah Anda Menambah Buku Yang Ingin Dipinjam ?");
            }while (isLanjutkan == true);
        }catch (Exception e){
            System.err.println("Gagal Meminjam Buku");
        }
    }

    public void menuDipinjam(){
        clearScreen();
        System.out.println("==== BUKU DIPINJAM ====");
        for (Buku bukuPinjam : bukuPinjam){
            System.out.println("No. Buku : "+ bukuPinjam.nomorBuku);
            System.out.println("Kategori : " + bukuPinjam.kategori);
            System.out.println("Judul : " + bukuPinjam.judul);
            System.out.println("Penulis : " + bukuPinjam.penulis);
            System.out.println("Penerbit : " + bukuPinjam.penerbit);
            System.out.println("===================");
        }
    }

    public void cariBuku(){
        clearScreen();
        boolean isLanjutkan = true;
        do {
            System.out.println("=== KATEGORI ===");
            System.out.println("1. Programming");
            System.out.println("2. Novel");
            System.out.println("3. Matematika");
            System.out.println("4. Wiraswasta");
            Scanner sc = new Scanner(System.in);
            System.out.print("Pilih Kategori :");
            int input = sc.nextInt();
            switch (input){
                case 1:
                    clearScreen();
                    System.out.println("\nBuku-Buku Kategori Programming : ");
                    for (Buku buku: buku) {
                        if(buku.getKategori().equalsIgnoreCase("Programming")) {
                            System.out.println("No. Buku : " + buku.getNomorBuku());
                            System.out.println("Judul : " + buku.getJudul());
                            System.out.println("=======================\n");
                        }
                    }
                    break;
                case 2:
                    clearScreen();
                    System.out.println("\nBuku-Buku Kategori Novel : ");
                    for (Buku buku: buku) {
                        if(buku.getKategori().equalsIgnoreCase("Novel")) {
                            System.out.println("No. Buku : " + buku.getNomorBuku());
                            System.out.println("Judul : " + buku.getJudul());
                            System.out.println("=======================\n");
                        }
                    }
                    break;
                    case 3:
                        clearScreen();
                        System.out.println("\nBuku-Buku Kategori Matematika : ");
                    for (Buku buku: buku) {
                        if(buku.getKategori().equalsIgnoreCase("Matematika")) {
                            System.out.println("No. Buku : " + buku.getNomorBuku());
                            System.out.println("Judul : " + buku.getJudul());
                            System.out.println("=======================\n");
                        }
                    }
                    break;
                    case 4:
                        clearScreen();
                        System.out.println("\nBuku-Buku Kategori Wiraswasta : ");
                    for (Buku buku: buku) {
                        if(buku.getKategori().equalsIgnoreCase("Wiraswasta")) {
                            System.out.println("No. Buku : " + buku.getNomorBuku());
                            System.out.println("Judul : " + buku.getJudul());
                            System.out.println("=======================\n");
                        }
                    }
                    break;
                default:
                    System.err.println("\nInput anda tidak ditemukan\nSilahkan pilih [1-4]");
            }
            System.out.println("===== PILIHAN =====");
            System.out.println("1. Lanjut Melakukan Cari Buku ");
            System.out.println("2. Lanjut Pinjam Buku ");

            int input2 = sc.nextInt();
            switch (input2){
                case 2:
                    isLanjutkan = false;
                    break;
            }
        }while (isLanjutkan == true);
    }

//    public void tampilkanRiwayat(){
//        clearScreen();
//        System.out.println("===== RIWAYAT PEMINJAMAN =====\n");
//        int num = 0;
//        for (Buku bukuPinjam : bukuPinjam){
//            ++num;
//            System.out.println("Peminjaman Ke-"+num);
//            System.out.println("Pada               : "+bukuPinjam.getTanggalPinjam());
//            System.out.println("Batas Pengembalian : "+bukuPinjam.getTanggalKembali()+ " 23:59:59");
//            System.out.println("Nomor Buku         : "+bukuPinjam.getNomorBuku());
//            System.out.println("Judul              : "+bukuPinjam.getJudul());
//            System.out.println("");
//        }
//    }

    public void update(){
        clearScreen();
        boolean isLanjutkan = true;
        Scanner inputuser = new Scanner(System.in);
        try {
            do {
                menuBuku();
                System.out.print("Masukkan Nomor Buku Yang Akan Diupdate : ");
                int pilih = inputuser.nextInt();

                Buku buku1 = new Buku();
                buku1.setNomorBuku(pilih);
                String a = inputuser.nextLine();
                System.out.println("Masukkan Judul Buku :");
                buku1.setJudul(inputuser.nextLine());
                System.out.println("Masukkan Penulis Buku :");
                buku1.setPenulis(inputuser.nextLine());
                System.out.println("Masukkan Penerbit Buku :");
                buku1.setPenerbit(inputuser.nextLine());
                System.out.println("Masukkan Kategori Buku :");
                buku1.setKategori(inputuser.nextLine());
                buku.set(pilih-1, buku1);
                isLanjutkan = getYesorNo("Apakah Anda Mengedit Buku Yang Lain ?");
            }while (isLanjutkan == true);
        }catch (Exception e){
            System.err.println("Gagal Mengedit Buku");
        }
    }

    public void delete(){
        clearScreen();
        boolean isLanjutkan = true;
        Scanner input = new Scanner(System.in);
        try {
            do {
                menuBuku();
                System.out.print("Masukkan Nomor Buku Yang Akan Dihapus : ");
                int pilih = input.nextInt();
                buku.remove(pilih-1);
                System.out.println("Berhasil Menghapus Buku");
                isLanjutkan = getYesorNo("Apakah Anda Mengedit Buku Yang Lain ?");
            }while (isLanjutkan == true);
        }catch (Exception e){
            System.err.println("Gagal Menghapus Buku");
        }
    }

    public void add(Buku bukubfr){
        clearScreen();
        Scanner sc = new Scanner(System.in);
        boolean isLanjutkan = true;
        do {
            Buku buku1 =  new Buku();
            System.out.print("Masukkan Kategori Buku : ");
            String kategori = sc.nextLine();
            System.out.print("Masukkan Judul Buku : ");
            String judul = sc.nextLine();
            System.out.print("Masukkan Penulis Buku : ");
            String penulis = sc.nextLine();
            System.out.print("Masukkan Penerbit Buku : ");
            String penerbit = sc.nextLine();

            buku1.setNomorBuku(++bukubfr.noBukuBfr);
            buku1.setKategori(kategori);
            buku1.setJudul(judul);
            buku1.setPenulis(penulis);
            buku1.setPenerbit(penerbit);

            buku.add(buku1);
            System.out.println("===========================");
            System.out.println("Berhasil Menambah Buku " + buku1.getJudul());
            System.out.println("===========================");

            isLanjutkan = getYesorNo("Apakah Anda ingin Menambah Buku Lagi");
        }while(isLanjutkan == true);
    }
}
